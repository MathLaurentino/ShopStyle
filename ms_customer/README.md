# Microserviço: Customer

## Descrição
O microserviço **Customer** é responsável por gerenciar os dados dos usuários da aplicação e seus respectivos endereços. Ele permite operações como cadastro, atualização, e recuperação de informações de clientes, além do gerenciamento de endereços associados.

## Estrutura de Domínio

### Entidade: `Customer`
- **ID**
- **CPF**
- **FIRST_NAME**
- **LAST_NAME**
- **SEX**
- **BIRTHDATE**
- **EMAIL**
- **PASSWORD**
- **ACTIVE**

### Entidade: `Address`
- **ID**
- **STATE*
- **CITY**
- **DISTRICT**
- **STREET**
- **NUMBER**
- **CEP**
- **COMPLEMENT**
- **CUSTOMER_ID**

## Endpoints

### Autenticação

#### Autentica o usuário

```http
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

### Gerenciamento de Clientes

#### Cadastra um novo cliente

```http
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

#### Retorna os dados de um cliente 

```http
GET /v1/customers/:id
```

- **Path Params**
  - id: id do cliente

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

```http
PUT /v1/customers/:id
```
- **Path Params**
  - id: id do cliente
- **Request Body:** 
  - Campos a serem atualizados.

#### Atualiza a senha do cliente (ainda não implementado).

```http
PUT /v1/customers/:id/password
```

- **Request Body:**
  ```json
  {
    "password": "novaSenha123"
  }
  ```

### Gerenciamento de Endereços

#### Cadastra um novo endereço para um cliente

```http
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

#### Atualiza um endereço existente

```http
PUT /v1/address/:id
```
- **Path Params**
  - id: id do endereço (address)
- **Request Body:** 
  - Campos a serem atualizados.

#### Remove um endereço

```http
DELETE /v1/address/:id
```
- **Path Params**
  - id: id do endereço (address)
