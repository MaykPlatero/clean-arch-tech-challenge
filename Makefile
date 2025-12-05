.PHONY: help build run test clean docker-up docker-down docker-logs db-only

help:
	@echo "Comandos disponíveis:"
	@echo "  make build        - Compila o projeto"
	@echo "  make run          - Executa a aplicação localmente"
	@echo "  make test         - Executa os testes"
	@echo "  make clean        - Limpa o build"
	@echo "  make docker-up    - Sobe aplicação + PostgreSQL no Docker"
	@echo "  make docker-down  - Para os containers"
	@echo "  make docker-logs  - Exibe logs dos containers"
	@echo "  make db-only      - Sobe apenas o PostgreSQL"

build:
	./mvnw clean package -DskipTests

run:
	./mvnw spring-boot:run

test:
	./mvnw test

clean:
	./mvnw clean

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down

docker-logs:
	docker-compose logs -f

db-only:
	docker-compose up -d postgres
