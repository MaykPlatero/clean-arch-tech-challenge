# Tech Challenge Fase 2 - Restaurant Management API

Sistema de gestão de restaurantes desenvolvido utilizando **Clean Architecture** com Java 21 e Spring Boot 3.3.6.

## 👥 Equipe de Desenvolvimento

- **Carlos Eduardo Ferreira Lins**
- **João Victor Morito da Silva**
- **Júlio Gurgel Fontes**
- **Mayk Pintor Platero**
- **Rodrigo Antônio Bagnara**

**Instituição**: FIAP - Pós-Tech Arquitetura de Software  
**Fase**: Tech Challenge Fase 2

---

## 📋 Sobre o Projeto

O **Restaurant Management API** é um sistema completo para gestão de restaurantes que permite:

- 🍽️ **Gestão de Restaurantes**: Cadastro, consulta e atualização de restaurantes com horários de funcionamento
- 👥 **Gestão de Usuários**: Sistema completo de usuários com diferentes perfis (CLIENT, OWNER, ADMIN)
- 🍕 **Gestão de Cardápio**: CRUD completo para itens do menu com preços, descrições e fotos
- 📊 **Documentação Interativa**: Interface Swagger/OpenAPI para testes e exploração das APIs

---

## 🏗️ Arquitetura

Implementamos **Clean Architecture** para garantir separação de responsabilidades, baixo acoplamento e alta coesão:

```
src/main/java/br/com/fiap/clean_arch/
├── domain/              # 🎯 Regras de negócio puras
│   ├── entities/        # Entidades (Restaurant, User, MenuItem, OpeningHours)
│   └── exceptions/      # Exceções de domínio
├── application/         # 🔄 Casos de uso
│   ├── usecases/        # Lógica de aplicação
│   └── ports/           # Contratos/Interfaces (Repositories)
├── infrastructure/      # 🔧 Implementações técnicas
│   ├── adapters/        # Implementação dos contratos
│   ├── persistence/     # Entidades JPA e repositórios Spring Data
│   └── config/          # Configurações Spring
└── presentation/        # 🌐 Interface REST
    ├── controllers/     # Endpoints da API
    ├── dto/            # Objetos de transferência (Request/Response)
    ├── mappers/        # Conversores DTO ↔ Domain
    └── exception/      # Tratamento global de exceções
```

### Princípios Aplicados

- ✅ **Clean Architecture**: Dependências apontam para dentro
- ✅ **SOLID**: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- ✅ **DDD**: Domain-Driven Design com entidades e agregados
- ✅ **Repository Pattern**: Abstração da persistência
- ✅ **Use Case Pattern**: Encapsulamento de lógica de aplicação

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Propósito |
|------------|--------|-----------|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.3.6 | Framework web |
| Spring Data JPA | 3.3.6 | Persistência de dados |
| PostgreSQL | 15 | Banco de dados |
| H2 Database | 2.2.224 | Testes in-memory |
| Lombok | 1.18.30 | Redução de boilerplate |
| SpringDoc OpenAPI | 2.3.0 | Documentação automática |
| JaCoCo | 0.8.11 | Cobertura de testes |
| Maven | 3.9+ | Gerenciamento de dependências |
| Docker | 24+ | Containerização |

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- **Docker** e **Docker Compose** instalados
- (Opcional) **Java 21** e **Maven** para desenvolvimento local

### 🐳 Opção 1: Docker Compose (Recomendado)

```bash
# 1. Clone o repositório
git clone <repository-url>
cd clean-arch-tech-challenge

# 2. Suba toda a aplicação (banco + API)
docker-compose up -d

# 3. Aguarde a inicialização (15-20 segundos)
docker-compose logs -f app

# 4. Acesse a aplicação
# Swagger UI: http://localhost:8080/swagger-ui/index.html
# API Docs: http://localhost:8080/api-docs
# Health Check: http://localhost:8080/actuator/health
```

**Comandos Úteis:**
```bash
# Parar a aplicação
docker-compose down

# Ver logs
docker-compose logs -f

# Reiniciar apenas a aplicação
docker-compose restart app

# Reconstruir imagens
docker-compose up -d --build
```

### 💻 Opção 2: Desenvolvimento Local

```bash
# 1. Suba apenas o banco de dados
docker-compose up -d postgres

# 2. Execute a aplicação localmente
./mvnw spring-boot:run

# 3. Acesse: http://localhost:8080/swagger-ui/index.html
```

---

## 📚 Documentação das APIs

### Base URL
```
http://localhost:8080
```

### Documentação Interativa
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### 🍽️ Endpoints - Restaurantes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/restaurants` | Criar restaurante |
| GET | `/api/restaurants/{id}` | Buscar por ID |
| PUT | `/api/restaurants/{id}` | Atualizar restaurante |

### 👥 Endpoints - Usuários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/users` | Criar usuário |
| GET | `/api/users/{id}` | Buscar por ID |
| PUT | `/api/users/{id}` | Atualizar usuário |
| DELETE | `/api/users/{id}` | Deletar usuário |

**Perfis disponíveis**: `CLIENT`, `OWNER`, `ADMIN`

### 🍕 Endpoints - Menu Items

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/menu-items` | Criar item do menu |
| GET | `/api/menu-items/{id}` | Buscar por ID |
| GET | `/api/menu-items/restaurant/{id}` | Listar por restaurante |
| PUT | `/api/menu-items/{id}` | Atualizar item |
| DELETE | `/api/menu-items/{id}` | Deletar item |

### 📊 Códigos de Status HTTP

| Código | Status | Descrição |
|--------|--------|-----------|
| 200 | OK | Requisição bem-sucedida (GET, PUT) |
| 201 | Created | Recurso criado com sucesso (POST) |
| 204 | No Content | Deleção bem-sucedida (DELETE) |
| 400 | Bad Request | Dados inválidos na requisição |
| 404 | Not Found | Recurso não encontrado |
| 500 | Internal Server Error | Erro interno do servidor |

---

## 🧪 Testes

### Cobertura Atual: **85%**

```bash
# Executar todos os testes
./mvnw test

# Gerar relatório de cobertura
./mvnw jacoco:report

# Visualizar relatório
open target/site/jacoco/index.html
```

### Cobertura por Camada

| Camada | Cobertura | Descrição |
|--------|-----------|-----------|
| **Domain** | 91% | Regras de negócio bem testadas |
| **Application** | 92% | Casos de uso cobertos |
| **Infrastructure** | 80% | Adapters e persistência |
| **Presentation** | 75% | Controllers e mappers |

**Total**: 88 testes passando

---

## 📦 Postman Collection

O projeto inclui uma collection completa do Postman com todos os endpoints testados:

```
Restaurant-API.postman_collection.json
```

**Como usar:**
1. Importe o arquivo no Postman
2. Configure a variável `baseUrl` para `http://localhost:8080`
3. Execute os requests na ordem sugerida

---

## 📖 Documentação Completa

Para documentação detalhada sobre arquitetura, endpoints, validações e exemplos completos, consulte:

```
DOCUMENTACAO_PROJETO.md
```

Este documento contém:
- Descrição detalhada da arquitetura
- Exemplos completos de requests e responses
- Validações de cada campo
- Configurações de ambiente
- Estrutura completa de diretórios

---

## 🔧 Configuração do Banco de Dados

### PostgreSQL (Produção)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

### H2 (Testes)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
```

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais como parte do Tech Challenge Fase 2 - FIAP Pós-Tech.

---

**Última atualização**: Janeiro de 2026
