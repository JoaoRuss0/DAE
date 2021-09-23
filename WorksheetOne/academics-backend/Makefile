.DEFAULT_GOAL := deploy

ifneq (,$(wildcard ./.env))
    include .env
    export
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

logs:
	docker compose logs -f webserver

sql:
	docker compose exec db psql --username ${DB_USER} --password ${DB_NAME}

ps:
	docker compose ps

deploy: up build
	docker compose cp target/academics.war webserver:/opt/jboss/wildfly/standalone/deployments

monitor: deploy logs