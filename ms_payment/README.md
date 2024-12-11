# MS Payment

O MS Payment é o microserviço responsável por gerenciar os métodos de pagamento disponíveis no sistema, 
assim como o processamento de pagamentos de pedidos realizados pelos clientes. Ele interage com outros 
microserviços para validar informações de pagamento e atualizar o status do pedido.

## Funcionalidades

- Cadastro e gerenciamento de métodos de pagamento.

- Cadastro e gerenciamento de parcelas (installments).

- Processamento de pagamentos.

- Escuta de mensagens de processamento de pedidos via RabbitMQ.

- Retorno de status de pagamento para o MS Order.

## Classes de Domínio

### Payment

- **ID**
- **TYPE**
- **INSTALLMENTS**
- **ACTIVE**

### Installment

- **ID**
- **AMOUNT**
- **BRAND**
- **PAYMENT_ID**

## Endpoints

### Payment Endpoints

#### Criar Método de Pagamento

```http
POST /v1/payments
```

Request Body:
```json
{
  "type": "credit card",
  "installments": true,
  "active": true
}
```

#### Listar Métodos de Pagamento
```http
GET /v1/payments
```
Descrição: Retorna todos os métodos de pagamento cadastrado

#### Atualizar Método de Pagamento

```http
PUT /v1/payments/:id
```

Path Param:
```
    id: ID do método de pagamento.
```
Request Body:

```json
    {
    "type": "debit card",
    "installments": false,
    "active": true
    }
```

#### Excluir Método de Pagamento

```http 
DELETE /v1/payments/:id
```

Path Param:
```
    id: ID do método de pagamento.
```


### Installment Endpoints

#### Criar Parcelamento
```http
POST /v1/installments
```
Request Body:
```json
    {
        "amount": 5,
        "brand": "mastercard",
        "paymentId": 1
    }
```

Validações:
- Validar se o campo installments do paymentId informado é true.

#### Atualizar Parcelamento

```http
PUT /v1/installments/:id
```

Path Param:
- id: ID do parcelamento.

Request Body:
```json
    {
        "amount": 10,
        "brand": "visa",
        "paymentId": 1
    }
```
### Excluir Parcelamento

```http
DELETE /v1/installments/:id
```

Path Param:
- id: ID do parcelamento.


## Mensageria com Kafka

O MS Payment é responsável por escutar mensagens relacionadas ao processamento 
de pagamento enviadas pelo MS Order. As mensagens possuem o seguinte formato:

**Mensagem Recebida**:
```json
    {
        "orderId": "6294d4b66f71221237b4d211",
        "payment": {
            "id": 1,
            "installments": 0
        }
    }
```

Após processar o pagamento, o MS Payment envia o status para o MS Order no seguinte formato:

**Mensagem Enviada**:
```json
    {
        "orderId": "6294d4b66f71221237b4d211",
        "status": "PAYMENT_SUCCESSFUL"
    }
```

**Possíveis Status de Retorno**:

- PAYMENT_SUCCESSFUL: Pagamento realizado com sucesso.
- PAYMENT_NOT_FOUND: Pagamento não encontrado no banco.
- PAYMENT_INACTIVE: Pagamento inativo.
- PAYMENT_NOT_INSTALLMENT: Pagamento não aceita parcelamento.
- PAYMENT_AMOUNT_NOT_AVAILABLE: Parcelas informadas não estão dentro do limite definido.