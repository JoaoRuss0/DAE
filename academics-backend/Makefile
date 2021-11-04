.DEFAULT_GOAL := deploy

ifneq (,$(wildcard ./.env))
    include .env
    export
    APPLICATION_NAME ?= academics
endif

up:
	docker compose up -d

down:
	docker compose down --rmi local --volumes

down-all:
	docker compose down --rmi all --volumes

build:
	mvn clean package

bash:
	docker compose exec webserver bash

logs: up
	docker compose logs -f webserver

sql: up
	docker compose exec db psql --username ${DB_USER} --password ${DB_NAME}

ps:
	docker compose ps

deploy: up build
	docker compose cp target/${APPLICATION_NAME}.war webserver:/opt/jboss/wildfly/standalone/deployments

monitor: deploy logs
