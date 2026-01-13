# Tech Challenge Fase 2 - Restaurant Management API

Sistema de gestão de restaurantes desenvolvido pela nossa equipe utilizando **Clean Architecture** com Java 21 e Spring Boot 3.3.6.

## 👥 Equipe de Desenvolvimento

Este projeto foi desenvolvido colaborativamente por nossa equipe para atender aos requisitos do Tech Challenge Fase 2 - FIAP Pós-Tech.

## 📋 Sobre o Projeto

O **Restaurant Management API** é um sistema completo para gestão de restaurantes que permite:

- 🍽️ **Gestão de Restaurantes**: Cadastro e consulta de restaurantes com horários de funcionamento
- 👥 **Gestão de Usuários**: Sistema completo de usuários com diferentes perfis (admin, cliente, proprietário)
- 🍕 **Gestão de Cardápio**: CRUD completo para itens do menu com preços e fotos
- 📊 **Documentação Interativa**: Interface Swagger para testes das APIs

## 🏗️ Arquitetura

Implementamos **Clean Architecture** para garantir separação de responsabilidades e facilitar manutenção:

```
src/main/java/br/com/fiap/clean_arch/
├── domain/              # 🎯 Regras de negócio puras
│   ├── entities/        # Entidades principais (Restaurant, User, MenuItem)
│   └── exceptions/      # Exceções de domínio
├── application/         # 🔄 Casos de uso
│   ├── usecases/        # Lógica de aplicação
│   └── ports/           # Contratos/Interfaces
├── infrastructure/      # 🔧 Implementações técnicas
│   ├── adapters/        # Implementação dos contratos
│   ├── persistence/     # Entidades JPA e repositórios
│   └── config/          # Configurações Spring
└── presentation/        # 🌐 Interface REST
    ├── controllers/     # Endpoints da API
    ├── dto/            # Objetos de transferência
    └── mappers/        # Conversores DTO ↔ Domain
```

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem principal
- **Spring Boot 3.3.6** - Framework web
- **PostgreSQL 15** - Banco de dados
- **Maven** - Gerenciamento de dependências
- **Lombok** - Redução de boilerplate
- **SpringDoc OpenAPI** - Documentação automática
- **JaCoCo** - Cobertura de testes (81%)

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- Docker e Docker Compose
- (Opcional) Java 21 e Maven para desenvolvimento local

### 🐳 Opção 1: Docker Compose (Recomendado)
```bash
# Clone o repositório
git clone <repository-url>
cd clean-arch-tech-challenge

# Suba toda a aplicação (banco + API)
docker-compose up -d

# Aguarde alguns segundos para a aplicação inicializar
# Acesse: http://localhost:8080/swagger-ui.html
```

### 💻 Opção 2: Desenvolvimento Local
```bash
# Suba apenas o banco
docker-compose up -d postgres

# Execute a aplicação local
./mvnw spring-boot:run

# Acesse: http://localhost:8080/swagger-ui.html
```

### 3. Acessar a Documentação
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **Health Check**: http://localhost:8080/actuator/health

## 📚 Documentação das APIs

### 🍽️ Restaurantes

#### Criar Restaurante
```http
POST /api/restaurants
Content-Type: application/json

{
  "name": "Pizzaria Bella Vista",
  "address": "Rua Augusta, 1234 - Consolação, São Paulo - SP",
  "cuisineType": "Italiana",
  "userIds": [1, 2],
  "openingHours": [
    {
      "dayOfWeek": "MONDAY",
      "openTime": "18:00",
      "closeTime": "23:30"
    },
    {
      "dayOfWeek": "FRIDAY",
      "openTime": "18:00",
      "closeTime": "00:30"
    }
  ]
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "name": "Pizzaria Bella Vista",
  "address": "Rua Augusta, 1234 - Consolação, São Paulo - SP",
  "cuisineType": "Italiana",
  "openingHours": [
    {
      "dayOfWeek": "MONDAY",
      "openTime": "18:00",
      "closeTime": "23:30"
    }
  ],
  "owners": [
    {
      "id": 1,
      "name": "João Silva",
      "email": "joao@email.com"
    }
  ]
}
```

#### Buscar Restaurante
```http
GET /api/restaurants/1
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "name": "Pizzaria Bella Vista",
  "address": "Rua Augusta, 1234 - Consolação, São Paulo - SP",
  "cuisineType": "Italiana",
  "openingHours": [...],
  "owners": [...]
}
```

#### Atualizar Restaurante
```http
PUT /api/restaurants/1
Content-Type: application/json

{
  "name": "Pizzaria Bella Vista Premium",
  "address": "Rua Augusta, 1234 - Consolação, São Paulo - SP",
  "cuisineType": "Italiana Gourmet",
  "userIds": [1, 2, 3],
  "openingHours": [
    {
      "dayOfWeek": "MONDAY",
      "openTime": "17:00",
      "closeTime": "23:30"
    }
  ]
}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "name": "Pizzaria Bella Vista Premium",
  "address": "Rua Augusta, 1234 - Consolação, São Paulo - SP",
  "cuisineType": "Italiana Gourmet",
  "openingHours": [...],
  "owners": [...]
}
```

### 👥 Usuários

#### Criar Usuário
```http
POST /api/users
Content-Type: application/json

{
  "name": "Maria Santos",
  "email": "maria@email.com",
  "userIdentification": "12345678901",
  "address": "Rua das Flores, 456 - Vila Madalena, São Paulo - SP",
  "profile": "client",
  "username": "maria.santos",
  "password": "senha123456"
}
```

**Resposta (201 Created):**
```json
{
  "id": 2,
  "name": "Maria Santos",
  "userIdentification": "12345678901",
  "email": "maria@email.com",
  "address": "Rua das Flores, 456 - Vila Madalena, São Paulo - SP",
  "profile": "client"
}
```

#### Buscar Usuário
```http
GET /api/users/2
```

#### Atualizar Usuário
```http
PUT /api/users/2
Content-Type: application/json

{
  "name": "Maria Santos Silva",
  "email": "maria.silva@email.com",
  "userIdentification": "12345678901",
  "address": "Rua das Flores, 789 - Vila Madalena, São Paulo - SP",
  "profile": "owner",
  "username": "maria.silva",
  "password": "novaSenha123"
}
```

#### Deletar Usuário
```http
DELETE /api/users/2
```

**Resposta (204 No Content)**

### 🍕 Itens do Menu

#### Criar Item do Menu
```http
POST /api/menu-items
Content-Type: application/json

{
  "restaurantId": 1,
  "name": "Pizza Margherita",
  "description": "Pizza tradicional italiana com molho de tomate San Marzano, mussarela de búfala, manjericão fresco e azeite extra virgem",
  "price": 42.90,
  "deliveryItem": true,
  "photoUrl": "https://exemplo.com/images/pizza-margherita.jpg"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "restaurantId": 1,
  "name": "Pizza Margherita",
  "description": "Pizza tradicional italiana com molho de tomate San Marzano, mussarela de búfala, manjericão fresco e azeite extra virgem",
  "price": 42.90,
  "deliveryItem": true,
  "photoUrl": "https://exemplo.com/images/pizza-margherita.jpg",
  "lastUpdate": "2024-01-13T15:30:00Z"
}
```

#### Buscar Item do Menu
```http
GET /api/menu-items/1
```

#### Buscar Itens por Restaurante
```http
GET /api/menu-items/restaurant/1
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "restaurantId": 1,
    "name": "Pizza Margherita",
    "description": "Pizza tradicional italiana...",
    "price": 42.90,
    "deliveryItem": true,
    "photoUrl": "https://exemplo.com/images/pizza-margherita.jpg",
    "lastUpdate": "2024-01-13T15:30:00Z"
  },
  {
    "id": 2,
    "restaurantId": 1,
    "name": "Pizza Pepperoni",
    "description": "Pizza com pepperoni...",
    "price": 48.90,
    "deliveryItem": true,
    "photoUrl": "https://exemplo.com/images/pizza-pepperoni.jpg",
    "lastUpdate": "2024-01-13T15:35:00Z"
  }
]
```

#### Atualizar Item do Menu
```http
PUT /api/menu-items/1
Content-Type: application/json

{
  "restaurantId": 1,
  "name": "Pizza Margherita Premium",
  "description": "Pizza tradicional italiana com ingredientes premium",
  "price": 52.90,
  "deliveryItem": true,
  "photoUrl": "https://exemplo.com/images/pizza-margherita-premium.jpg"
}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "restaurantId": 1,
  "name": "Pizza Margherita Premium",
  "description": "Pizza tradicional italiana com ingredientes premium",
  "price": 52.90,
  "deliveryItem": true,
  "photoUrl": "https://exemplo.com/images/pizza-margherita-premium.jpg",
  "lastUpdate": "2024-01-13T16:30:00Z"
}
```

#### Deletar Item do Menu
```http
DELETE /api/menu-items/1
```

**Resposta (204 No Content)**

## 🧪 Testes

Nossa equipe implementou uma suíte robusta de testes com **81% de cobertura**:

```bash
# Executar todos os testes
./mvnw test

# Gerar relatório de cobertura
./mvnw jacoco:report

# Visualizar relatório: target/site/jacoco/index.html
```

**Cobertura por camada:**
- **Domain**: 96% - Regras de negócio bem testadas
- **Application**: 81% - Casos de uso cobertos
- **Infrastructure**: 77% - Adapters e persistência
- **Presentation**: 75% - Controllers e mappers

## 🔧 Configuração do Ambiente

### Banco de Dados
O projeto utiliza PostgreSQL em produção e H2 para testes:

```properties
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Docker
```yaml
# docker-compose.yml
services:
  postgres:
    image: postgres:15-alpine
    environment:
      POSTGRES_DB: restaurant_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
```

## 📊 Códigos de Status HTTP

| Status | Descrição | Quando ocorre |
|--------|-----------|---------------|
| 200 | OK | Busca bem-sucedida |
| 201 | Created | Recurso criado com sucesso |
| 204 | No Content | Deleção bem-sucedida |
| 400 | Bad Request | Dados inválidos na requisição |
| 404 | Not Found | Recurso não encontrado |
| 500 | Internal Server Error | Erro interno do servidor |

## 🏛️ Princípios Aplicados

Nossa equipe seguiu as melhores práticas de desenvolvimento:

- **Clean Architecture**: Separação clara entre camadas
- **SOLID**: Princípios de design orientado a objetos
- **DDD**: Domain-Driven Design
- **Repository Pattern**: Abstração da persistência
- **Dependency Inversion**: Baixo acoplamento entre camadas

## 📦 Desenvolvedores

**Desenvolvido por**: 
- **Carlos Edurado Ferreira Lins**
- **João Victor Morito da Silva**
- **Júlio Gurgel Fontes**
- **Mayk Pintor Platero**
- **Rodrigo Antônio Bagnara**
