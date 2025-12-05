# Tech Challenge Fase 2 - Restaurant Management API

API de gestão de restaurantes desenvolvida com **Clean Architecture** utilizando Java 21 e Spring Boot 4.0.0.

## 🏗️ Arquitetura

Projeto estruturado seguindo os princípios de **Clean Architecture**:

```
src/main/java/br/com/fiap/clean_arch/
├── domain/              # Regras de negócio puras (zero frameworks)
├── application/         # Casos de uso e ports
├── infrastructure/      # Implementações técnicas (JPA, adapters)
└── presentation/        # Controllers REST e DTOs
```

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4.0.0**
- **PostgreSQL**
- **Maven**
- **Lombok**
- **SpringDoc OpenAPI (Swagger)**

## 📋 Pré-requisitos

- Java 21
- Maven 3.8+
- PostgreSQL 15+

## ⚙️ Configuração

### Opção 1: Docker (Recomendado)

```bash
# Subir aplicação + PostgreSQL
docker-compose up -d
```

### Opção 2: Local

1. Configure o PostgreSQL:
```sql
CREATE DATABASE restaurant_db;
```

2. Execute:
```bash
mvn spring-boot:run
```

### Opção 3: Apenas PostgreSQL no Docker

```bash
# Subir apenas o banco
docker-compose up -d postgres

# Rodar aplicação local
mvn spring-boot:run
```

## 📚 Documentação da API

Acesse o Swagger UI: `http://localhost:8080/swagger-ui.html`

## 🔗 Endpoints

### Criar Restaurante
```http
POST /api/restaurants
Content-Type: application/json

{
  "name": "Pizza Place",
  "address": "Rua Augusta, 123 - São Paulo",
  "cuisineType": "Italian"
}
```

## 🧪 Testes

```bash
mvn test
```

## 📦 Build

```bash
mvn clean package
```

## 👥 Autores

Projeto desenvolvido para o Tech Challenge Fase 2 - FIAP Pós-Tech
