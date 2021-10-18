#!/bin/bash

echo "=> Setup Wildfly Admin Password"
$WILDFLY_HOME/bin/add-user.sh admin $WILDFLY_ADMIN_PASSWORD --silent
unset $WILDFLY_ADMIN_PASSWORD