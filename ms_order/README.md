# MS Order

O **MS Order** é responsável por gerenciar todos os pedidos de compra realizados na aplicação. Ele se comunica com outros micro-serviços, como o MS Customer, MS Catalog, e MS Payment, para validar e processar os pedidos. O armazenamento dos pedidos é feito no banco de dados MongoDB.

## Funcionalidades
- Gerenciamento de pedidos de compra.
- Comunicação com os micro-serviços para validação de usuários, endereços, produtos, e pagamentos.
- Processamento de pagamento com atualização de status do pedido.
- Envio de mensagens para atualização do estoque no MS Catalog.

## Classes de Domínio

### Pedido (Order)
- **Campos:**
    - `id`: Identificador único do pedido.
    - `customer`: Objeto representando o cliente que fez o pedido.
    - `payment`: Objeto representando o método de pagamento utilizado.
    - `cart`: Lista de itens do pedido, cada item contendo `skuId` e `quantity`.
    - `date`: Data e hora do pedido.
    - `status`: Status atual do pedido (ex: `PROCESSING_PAYMENT`, `PAYMENT_SUCCESSFUL`).
    - `total`: Valor total do pedido calculado a partir do carrinho.

## Endpoints

### Cria um novo pedido

```http
POST /v1/orders
```

- **Payload:**
```json
{
  "customer": {
    "id": 1,
    "addressId": 1
  },
  "payment": {
    "id": 1,
    "installments": 0
  },
  "cart": [
    { "skuId": 1, "quantity": 1 },
    { "skuId": 2, "quantity": 5 }
  ]
}
```
- **Validações:**
    - O cliente deve existir e estar ativo.
    - O endereço do cliente deve ser válido.
    - Todos os itens do carrinho devem ter estoque suficiente.
    - O método de pagamento deve ser válido.
- **Ações:**
    - Calcula o valor total do pedido.
    - Insere a data e hora do pedido.
    - Define o status inicial como `PROCESSING_PAYMENT`.
    - Envia mensagem ao MS Payment via Kafka para processar o pagamento.

### Retorna uma lista de pedidos com base nos filtros fornecidos 

```http
GET /v1/orders
```

- **Query Params:**
    - `startDate` (obrigatório): Data inicial para filtrar os pedidos.
    - `endDate` (opcional): Data final para filtrar os pedidos.
    - `status` (opcional): Status do pedido para filtro.

### Retorna uma lista de pedidos realizados por um cliente específico

```http
GET /v1/orders/customers/:customerId
```

- **Path Params:**
    - `customerId`: ID do cliente.
- **Query Params:**
    - `startDate` (opcional): Data inicial para filtrar os pedidos.
    - `endDate` (opcional): Data final para filtrar os pedidos.
    - `status` (opcional): Status do pedido para filtro.

## Processamento de Pedidos
1. Valida o cliente e o endereço com o MS Customer.
2. Valida os itens do carrinho e o estoque disponível com o MS Catalog.
3. Envia o pedido para o MS Payment para processar o pagamento.
4. Recebe o resultado do MS Payment e atualiza o status do pedido:
    - **Sucesso:** Atualiza o status para `COMPLETED` e envia mensagem ao MS Catalog para reduzir o estoque.
    - **Falha:** Atualiza o status para o erro correspondente (ex: `PAYMENT_INACTIVE`).

## Banco de Dados
- **Tipo:** MongoDB
- **Coleção:** `orders`
- **Estrutura do Documento:**
```json
{
  "id": "6294d4b66f71221237b4d211",
  "customer": {
    "id": 1,
    "addressId": 1
  },
  "payment": {
    "id": 1,
    "installments": 0
  },
  "cart": [
    { "skuId": 1, "quantity": 1 },
    { "skuId": 2, "quantity": 5 }
  ],
  "date": "2023-10-15T14:30:00Z",
  "status": "PROCESSING_PAYMENT",
  "total": 299.99
}
```

## Tecnologias Utilizadas
- **Linguagem:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** MongoDB
- **Mensageria:** Kafka
- **Documentação:** Swagger
