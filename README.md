# Expense Tracker App

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-Latest-red)](https://maven.apache.org/)

## 📋 Descrição

Expense Tracker App é uma aplicação backend para rastreamento e gerenciamento de despesas. Construída com Spring Boot, oferece uma API RESTful completa com documentação automática via OpenAPI/Swagger, permitindo o controle eficiente de gastos pessoais ou empresariais com persistência em banco de dados PostgreSQL.

## 🎯 Características

- ✅ API RESTful completa para gerenciamento de despesas
- ✅ Validação de dados com Bean Validation
- ✅ Documentação automática com OpenAPI 3.0 e Swagger UI
- ✅ Persistência de dados com Spring Data JPA
- ✅ Filtro de despesas por período (semana/mês)
- ✅ Docker Compose para ambiente de desenvolvimento
- ✅ Suporte a DEV Tools para desenvolvimento rápido
- ✅ Testes automatizados
- ✅ Java 21 com recursos modernos

## 🛠️ Tecnologias

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| **Java** | 21 | Linguagem principal |
| **Spring Boot** | 4.0.5 | Framework web |
| **Spring Data JPA** | Latest | Persistência de dados |
| **PostgreSQL** | Latest | Banco de dados |
| **Spring Validation** | Latest | Validação de dados |
| **SpringDoc OpenAPI** | 3.0.2 | Documentação da API |
| **Docker Compose** | Latest | Orquestração de containers |
| **Maven** | Latest | Gerenciador de dependências |

## 📊 Estrutura do Projeto

```
expense-tracker-app/
├── src/
│   ├── main/
│   │   ├── java/com/fiap/expensetracker/
│   │   │   ├── ExpenseTrackerAppApplication.java    # Classe principal
│   │   │   ├── controller/
│   │   │   │   └── ExpenseController.java            # Endpoints da API
│   │   │   ├── entity/
│   │   │   │   └── Expense.java                      # Entidade JPA
│   │   │   ├── repository/
│   │   │   │   └── ExpenseRepository.java            # Acesso a dados
│   │   │   └── service/
│   │   │       └── ExpenseService.java               # Lógica de negócio
│   │   └── resources/
│   │       ├── application.properties                # Configurações
│   │       └── openapi/
│   │           └── expenses-api.yaml                 # Especificação OpenAPI
│   └── test/
│       └── java/com/fiap/expensetracker/
│           └── ExpenseTrackerAppApplicationTests.java
├── pom.xml                                           # Configuração Maven
├── compose.yaml                                      # Docker Compose
├── mvnw                                              # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                                          # Maven Wrapper (Windows)
└── README.md
```

## 🚀 Primeiros Passos

### Pré-Requisitos

- **Java 21** ou superior
  ```bash
  java -version
  ```
- **Maven 3.9.0+** (incluído via Maven Wrapper)
- **Docker** e **Docker Compose** (opcional, para executar PostgreSQL)
  ```bash
  docker --version
  docker-compose --version
  ```

### Clonar o Projeto

```bash
git clone https://github.com/seu-usuario/expense-tracker-app.git
cd expense-tracker-app
```

### Instalação

1. **Instalar dependências:**
   ```bash
   ./mvnw clean install
   ```
   Ou no Windows:
   ```bash
   mvnw.cmd clean install
   ```

2. **Configurar banco de dados (opcional):**

   Se usar Docker Compose:
   ```bash
   docker-compose up -d
   ```

   Ou configure manualmente no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
   spring.datasource.username=myuser
   spring.datasource.password=secret
   spring.jpa.hibernate.ddl-auto=update
   ```

## 🏃 Como Executar

### Iniciar a Aplicação

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### Acessar o Swagger UI

Documentação interativa da API:
```
http://localhost:8080/swagger-ui.html
```

### Acessar os documentos OpenAPI

- **JSON**: `http://localhost:8080/v3/api-docs`
- **YAML**: `http://localhost:8080/v3/api-docs.yaml`

## 📡 Endpoints da API

### Criar Despesa

```http
POST /v1/expenses
Content-Type: application/json

{
  "description": "Almoço no restaurante",
  "amount": 50.00,
  "category": "Alimentação",
  "date": "2024-04-01"
}
```

**Resposta:**
```http
HTTP/1.1 201 Created
```

### Listar Despesas

Listar todas as despesas:
```http
GET /v1/expenses
```

Filtrar por período (WEEK ou MONTH):
```http
GET /v1/expenses?period=MONTH
```

**Resposta:**
```json
[
  {
    "id": 1,
    "description": "Almoço no restaurante",
    "amount": 50.00,
    "category": "Alimentação",
    "date": "2024-04-01"
  }
]
```

## 🧪 Testes

### Executar todos os testes

```bash
./mvnw test
```

### Executar testes de uma classe específica

```bash
./mvnw test -Dtest=ExpenseControllerTest
```

### Gerar relatório de cobertura

```bash
./mvnw jacoco:report
```

## ⚙️ Configuração

### Arquivo application.properties

Configurações principais:

```properties
# Banco de Dados
spring.datasource.url=jdbc:postgresql://postgres:5432/mydatabase
spring.datasource.username=myuser
spring.datasource.password=secret
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
```

## 🐳 Docker Compose

O projeto inclui configuração para PostgreSQL via Docker Compose.

### Iniciar container

```bash
docker-compose up -d
```

### Parar container

```bash
docker-compose down
```

### Verificar status

```bash
docker-compose ps
```

## 📚 Arquitetura

A aplicação segue a arquitetura em camadas (Layered Architecture):

```
┌─────────────────────────────────────┐
│   Controller (REST API)             │
│   (ExpenseController)               │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│   Service (Business Logic)          │
│   (ExpenseService)                  │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│   Repository (Data Access)          │
│   (ExpenseRepository)               │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│   Entity (Domain Model)             │
│   (Expense)                         │
└─────────────┬───────────────────────┘
              │
┌─────────────▼───────────────────────┐
│   Database (PostgreSQL)             │
└─────────────────────────────────────┘
```

## 🔒 Validações

A aplicação utiliza Bean Validation para garantir integridade dos dados:

- ✅ Validação de campos obrigatórios
- ✅ Validação de formato de dados
- ✅ Validação de valores monetários
- ✅ Validação de datas

Mensagens de erro detalhadas são retornadas em caso de validação falha (HTTP 400).

## 🛠️ Desenvolvimento

### Usar DEV Tools

Spring Boot DevTools está configurado para:
- **Auto-restart** da aplicação ao salvar arquivos
- **Live reload** de recursos estáticos
- **Configurações de aplicativo em cache**

Funciona automaticamente ao executar via `./mvnw spring-boot:run`

### IDE Recomendadas

- **IntelliJ IDEA** (Community ou Ultimate)
- **Eclipse STS** (Spring Tool Suite)
- **Visual Studio Code** + Extension Pack for Java

## 📝 Padrões de Código

O projeto segue as convenções:

- **NomeClasses**: PascalCase (ex: `ExpenseController`)
- **nomeMetodos**: camelCase (ex: `getExpenses()`)
- **NOMES_CONSTANTES**: UPPER_SNAKE_CASE
- **Indentação**: 4 espaços
- **Charset**: UTF-8

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## 📧 Contato

Para dúvidas ou sugestões, abra uma [issue](https://github.com/seu-usuario/expense-tracker-app/issues).

---

**Desenvolvido com ❤️ usando Spring Boot**
