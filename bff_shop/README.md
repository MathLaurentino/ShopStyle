# MS BFF-Shop

O MS BFF-Shop atua como a camada de interface entre os clientes e os micro-serviços internos do projeto Shop Style. Ele fornece endpoints centralizados para facilitar o acesso às funcionalidades principais, como gerenciamento de usuários, produtos, pedidos, categorias e pagamentos.

## Funcionalidades Principais

- Gerenciamento de usuários e endereços.
- Consulta e navegação por produtos e categorias.
- Processamento de pedidos.
- Autenticação e autorização de clientes via JWT.

## Endpoints

### Autenticação e Cadastro

#### Autentica de cliente
``` 
    POST /v1/login
```
- **Request Body:**
```json
  {
    "email": "maria@email.com",
    "password": "12345678"
  }
 ```
- **Response:** Token JWT para autenticação.

#### Cadastra de cliente

```
    POST /v1/customers
```
- **Request Body:**
```json
  {
    "firstName": "Maria",
    "lastName": "Oliveira",
    "sex": "Feminino",
    "cpf": "000.000.000-00",
    "birthdate": "2000-01-01",
    "email": "maria@email.com",
    "password": "12345678",
    "active": true
  }
```
- **Validações:**
    - Campos obrigatórios.
    - Email único.
    - CPF no formato correto.
    - Senha com no mínimo 6 caracteres.

### Gerenciamento de Clientes

#### Retorna os dados de um cliente

```
    GET /v1/customers/:id
```
- **Response:**
```json
  {
    "id": 1,
    "firstName": "Maria",
    "lastName": "Oliveira",
    "email": "maria@email.com",
    "addresses": [
      {
        "id": 1,
        "state": "Ceará",
        "city": "Fortaleza",
        "street": "Rua 202B",
        "number": "902"
      }
    ]
  }
```

#### Atualiza os dados de um cliente

```
    PUT /v1/customers/:id
```

- **Request Body:** 
  - Campos a serem atualizados.

#### Atualiza a senha (AINDA NÃO IMPLEMENTADO)

```
PUT /v1/customers/:id/password
```

- **Request Body:**
```json
  {
    "password": "novaSenha123"
  }
```

### Gerenciamento de Endereços

#### Cadastra Endereço

```
    POST /v1/address
```

- **Request Body:**
```json
  {
    "state": "Ceará",
    "city": "Fortaleza",
    "district": "Conjunto Ceará",
    "street": "Rua 202B",
    "number": "902",
    "cep": "60530-280",
    "complement": "",
    "customerId": 1
  }
```

- **Validações:**
    - Estado válido.
    - Campos obrigatórios, exceto `complement`.

#### Atualiza Endereço

```
    PUT /v1/address/:id
```
- Path Params 
  - id: id do endereço (address)
- **Request Body:** 
  - Campos a serem atualizados.

#### Remove um endereço
```
    DELETE /v1/address/:id
```

### Produtos e Categorias


#### Lista todos os produtos
```
    GET - /v1/products
```

#### Retorna os detalhes de um produto
```
    GET - /v1/products/:id
```


#### Lista todas as categorias no formato de árvore hierárquica
```
    GET - /v1/categories
```

#### Retorna os produtos de uma categoria específica (AINDA NÃO IMPLEMENTADO)
```
    GET - /v1/categories/:id/products
```

### Pedidos

### Cria um novo pedido

```
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

### Retorna uma lista de pedidos realizados por um cliente específico

```
    GET /v1/orders/customers/:customerId
```

- **Path Params:**
    - `customerId`: ID do cliente.
- **Query Params:**
    - `startDate` (opcional): Data inicial para filtrar os pedidos.
    - `endDate` (opcional): Data final para filtrar os pedidos.
    - `status` (opcional): Status do pedido para filtro.

## Tecnologias Utilizadas

- Java
- Spring Boot
- JWT para autenticação.
- Swagger para documentação dos endpoints.
- Feign para requisições entre microserviços