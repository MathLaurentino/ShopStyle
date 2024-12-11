# MS Catalog - Microserviço de Catálogo

O **MS Catalog** é responsável por gerenciar os produtos, SKUs e categorias disponíveis na aplicação. Este serviço organiza os produtos por categorias e gerencia as informações de estoque e atributos detalhados de cada SKU.

## Classes de Domínio

### Product
Representa um produto cadastrado no catálogo.
- **ID**
- **NAME**
- **DESCRIPTION**
- **BRAND**
- **MATERIAL**
- **ACTIVE**
- **CATEGORY_ID**

### SKU
Define uma unidade específica de estoque de um produto.
- **ID**
- **PRICE**
- **QUANTITY**
- **COLOR**
- **SIZE**
- **HEIGHT**
- **WIDTH**
- **PRODUCT_ID**

### Media
Gerencia as imagens vinculadas aos SKUs.
- **ID**
- **IMAGE_URL**
- **SKU_ID**

### Category
Representa uma categoria de produtos, que pode ser hierarquizada.
- **ID**
- **NAME**
- **ACTIVE**
- **PARENT_ID**: Relacionamento com a categoria pai (se aplicável).

## Endpoints

### Produtos
- **POST - /v1/products**
    - Cadastra um novo produto.
    - **Body Exemplo**:
      ```json
      {
        "name": "Camisa Oficial",
        "description": "Camisa do time oficial",
        "brand": "Nike",
        "material": "Algodão",
        "active": true,
        "categoryId": 1
      }
      ```

- **GET - /v1/products**
    - Retorna a lista de todos os produtos cadastrados.

- **GET - /v1/products/:id**
    - Retorna os detalhes de um produto específico, incluindo suas SKUs.

- **PUT - /v1/products/:id**
    - Atualiza os dados de um produto.

- **DELETE - /v1/products/:id**
    - Remove um produto.

### SKUs
- **POST - /v1/skus**
    - Cadastra um novo SKU para um produto.
    - **Body Exemplo**:
      ```json
      {
        "price": 199.99,
        "quantity": 15,
        "color": "Azul",
        "size": "M",
        "height": 30,
        "width": 25,
        "productId": 1,
        "images": [ // não implementado
          "http://example.com/image1.png",
          "http://example.com/image2.png"
        ]
      }
      ```

- **PUT - /v1/skus/:id**
    - Atualiza os dados de um SKU específico.

- **DELETE - /v1/skus/:id**
    - Remove um SKU.

### Categorias
- **POST - /v1/categories**
    - Cadastra uma nova categoria.
    - **Body Exemplo**:
      ```json
      {
        "name": "Roupas",
        "active": true
      }
      ```

- **GET - /v1/categories**
    - Retorna a lista de categorias em formato de árvore.

- **GET - /v1/categories/:id/products**
    - Retorna todos os produtos vinculados a uma categoria específica.

- **PUT - /v1/categories/:id**
    - Atualiza os dados de uma categoria.

- **DELETE - /v1/categories/:id**
    - Remove uma categoria.

## Integrações

O MS Catalog está integrado com os seguintes serviços:
- **Kafka**: Recebe mensagens do **MS Order** para gerenciar o estoque das SKUs ao processar pedidos.
    - **Formato da Mensagem**:
      ```json
      {
        "orderId": "6294d4b66f71221237b4d211",
        "skus": [
          { "id": 1, "quantity": 2 },
          { "id": 2, "quantity": 1 }
        ]
      }
      ```

- **MS Order**: Fornece detalhes dos SKUs e verifica o estoque disponível para os pedidos.

## Observações
- Utiliza PostgreSQL como banco de dados principal.
- Comunicação assíncrona via Kafka para troca de mensagens entre serviços.
- Swagger disponível para documentação e testes dos endpoints.
