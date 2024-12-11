# Shop Style - Projeto de E-commerce com Arquitetura de Microserviços 🛒

## Descrição

O **Shop Style** é um projeto de e-commerce para uma loja de roupas. A solução foi desenhada utilizando uma arquitetura de **microserviços**, com o objetivo de implementar uma loja virtual escalável e de fácil manutenção.

A aplicação é composta por **6 micro-serviços** principais:

1. **MS Customer**: Gerencia os dados dos clientes e seus endereços.
2. **MS Catalog**: Gerencia os produtos, categorias e skus disponíveis na loja.
3. **MS Payment**: Gerencia os métodos de pagamento disponíveis na loja (não contempla verificação de pagamento).
4. **MS Order**: Gerencia os pedidos de compra realizados pelos clientes.
5. **MS Audit**: Realiza auditoria e registro dos eventos de processamento dos pedidos.
6. **MS BFF-Shop**: Funciona como ponto de entrada para os clientes, consolidando as funcionalidades de todos os micro-serviços.

Cada micro-serviço foi projetado para ser autônomo, possuindo suas próprias bases de dados, validações e integrações.

![img](https://github.com/user-attachments/assets/8ceb7340-cc4c-4d29-a4b8-a7ea2c26c5e8)

## Tecnologias Usadas

O projeto utiliza as seguintes tecnologias e ferramentas:

- **Spring Boot**: Framework para criação de micro-serviços em Java.
- **PostgreSQL**: Banco de dados relacional utilizado em vários micro-serviços (MS Customer, MS Catalog, MS Payment).
- **MongoDB**: Banco de dados NoSQL utilizado para armazenar dados de pedidos e auditorias (MS Order, MS Audit).
- **Kafka**: Sistema de mensageria utilizado para integração assíncrona entre micro-serviços.
- **JWT (JSON Web Token)**: Autenticação e autorização nos endpoints.
- **Swagger**: Documentação automática para os endpoints dos micro-serviços.
- **JUnit**: Framework de testes unitários utilizado para garantir a qualidade do código.
- **Eureka Server**: Serviço de descoberta de micro-serviços, utilizado para registrar e localizar os serviços em um ambiente distribuído.
- **Gateway**: Um API Gateway que centraliza e roteia as requisições para os diferentes micro-serviços, fornecendo funcionalidades como autenticação, controle de acesso e balanceamento de carga.
- **Docker**: Plataforma de containerização que facilita a criação, distribuição e execução de aplicações de forma isolada, garantindo consistência entre ambientes de desenvolvimento, teste e produção.

## Como Rodar o Projeto Localmente

1. Certifique-se de ter o Docker e o Docker Compose funcionando em sua máquina (no meu caso, utilizei o Rancher para gerenciar os containers)


2. Clone este repositório:
```
  git clone https://github.com/MathLaurentino/ShopStyle.git
```

3. Navegue até o diretório do projeto:
```
    cd ShopStyle
```

4. Rode o comando Docker para iniciar o projeto:
```
    docker-compose up --build
```
- Esse comando vai:
  - Construir os containers de acordo com o Dockerfile de cada serviço.
  - Iniciar todos os containers definidos no docker-compose.yml, incluindo os micro-serviços, banco de dados, Kafka, Eureka, entre outros.

5. Acesse a aplicação:
  
    Após o `docker-compose up --build` completar, você pode acessar os micro-serviços através das portas mapeadas no docker-compose.yml. Por exemplo:
   ```
      - Eureka: http://localhost:8761
      - API Gateway: http://localhost:8085
    ```
   Outros serviços podem ser acessados nas portas definidas, como 8081 para o MS Customer, 8082 para o MS Payment, etc.


6. Utilize o **Insomnia** para testar a aplicação:
    
   Na raiz do projeto, há um arquivo chamado `Insomnia_ShopStyle`, que contém todas as rotas do api. Importe esse arquivo para o insomnia e teste a aplicação.
