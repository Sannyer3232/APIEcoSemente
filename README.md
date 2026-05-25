# ecosementeAPI

Uma API RESTful desenvolvida com Spring Boot para o aplicativo **EcoSemente**.

## 📝 Sobre o Projeto

O **ecosementeAPI** é o backend responsável por gerenciar as operações do ecossistema EcoSemente. A API permite o gerenciamento de compradores, sementes e o processamento de vendas, seguindo as melhores práticas de desenvolvimento com o framework Spring.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Data JPA** (Persistência de dados)
- **Spring Web** (Endpoints REST)
- **Spring HATEOAS** (Navegabilidade da API)
- **MySQL** (Banco de dados de produção/desenvolvimento)
- **H2 Database** (Banco de dados em memória para testes)
- **Maven** (Gerenciador de dependências)
- **Swagger/OpenAPI** (Documentação da API)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas bem definida:
- **Controllers**: Gerenciamento das rotas e requisições HTTP.
- **Services**: Lógica de negócio da aplicação.
- **Repositories**: Interface de comunicação com o banco de dados via JPA.
- **Models/Entities**: Representação das tabelas do banco de dados.
- **DTOs (Data Transfer Objects)**: Separação dos dados de entrada (`InputDTO`) e saída (`OutputDTO`) da camada de domínio.

## 🔧 Como Executar

### Pré-requisitos
- JDK 21
- MySQL (configurado conforme `src/main/resources/application.properties`)

### Comandos Maven (Windows)

1. **Compilar o projeto:**
   ```bash
   mvnw.cmd clean compile
   ```

2. **Gerar o arquivo JAR:**
   ```bash
   mvnw.cmd clean package
   ```

3. **Executar a aplicação:**
   ```bash
   mvnw.cmd spring-boot:run
   ```

## 📚 Documentação da API

Após iniciar a aplicação, você pode acessar a documentação interativa do Swagger através do link:
`http://localhost:8080/swagger-ui.html`

## 👤 Autor

Desenvolvido por **Sannyer Cardoso Carvalho Nery**.

---
*Este projeto foi gerado para fins educacionais e de gestão de sementes.*
