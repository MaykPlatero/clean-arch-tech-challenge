.PHONY: help run stop reset

help:
	@echo "=== Restaurant Management API - Comandos Disponíveis ==="
	@echo ""
	@echo "  make run     - Inicia a aplicação (Docker Compose com build)"
	@echo "  make stop    - Para a aplicação"
	@echo "  make reset   - Remove containers, volumes e dados"
	@echo ""

run:
	@echo "🚀 Iniciando aplicação..."
	docker-compose up -d --build
	@echo "✅ Aplicação iniciada!"
	@echo "📚 Swagger: http://localhost:8080/swagger-ui/index.html"
	@echo "🏥 Health: http://localhost:8080/actuator/health"

stop:
	@echo "⏸️  Parando aplicação..."
	docker-compose down
	@echo "✅ Aplicação parada!"

reset:
	@echo "🗑️  Removendo containers, volumes e dados..."
	docker-compose down -v
	@echo "✅ Tudo limpo! Execute 'make run' para reiniciar."
