#!/bin/bash

echo "=> Downloading PostgreSQL Driver v$POSTGRES_DRIVER_VERSION"
POSTGRES_DRIVER_FILENAME=postgresql-$POSTGRES_DRIVER_VERSION.jar
curl https://jdbc.postgresql.org/download/$POSTGRES_DRIVER_FILENAME --output /tmp/$POSTGRES_DRIVER_FILENAME