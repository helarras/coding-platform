SHELL := /bin/bash
.DEFAULT_GOAL := help

BACKEND_DIR := backend
FRONTEND_DIR := frontend
COMPOSE := docker compose

.PHONY: help backend frontend postgres-up postgres-down postgres-down-v

help:
	@echo "make backend      - run Spring Boot"
	@echo "make frontend     - run React app"
	@echo "make postgres-up  - start Postgres"
	@echo "make postgres-down- stop Postgres"
	@echo "make postgres-down-v- stop Postgres and remove volumes"

backend:
	@cd $(BACKEND_DIR) && ./mvnw spring-boot:run

frontend:
	@cd $(FRONTEND_DIR) && npm run dev

postgres-up:
	@$(COMPOSE) up -d postgres

postgres-down:
	@$(COMPOSE) down

postgres-down-v:
	@$(COMPOSE) down -v


