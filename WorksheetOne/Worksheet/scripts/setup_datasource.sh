#!/bin/bash

echo "=> Setup Datasource"
$JBOSS_CLI -c << EOF
batch

# Add PostgreSQL driver
module add --name=org.postgresql --resources=/tmp/$POSTGRES_DRIVER_FILENAME --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)

# Add the datasource
data-source add \
  --jndi-name=$DATASOURCE_JNDI \
  --name=$DATASOURCE_NAME \
  --connection-url=jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME \
  --driver-name=postgres \
  --user-name=$DB_USER \
  --password=$DB_PASS \
  --check-valid-connection-sql="SELECT 1" \
  --background-validation=true \
  --background-validation-millis=60000 \
  --flush-strategy=IdleConnections \
  --min-pool-size=10 --max-pool-size=100  --pool-prefill=false

# Execute the batch
run-batch
EOF