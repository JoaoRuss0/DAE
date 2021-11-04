#!/bin/bash

# This script was based on: https://github.com/wildfly/quickstart/tree/main/jaxrs-jwt
echo "=> Setup Elytron with JWT"

$JBOSS_CLI -c << EOF

# Batch script to enable Elytron on the server for the quickstart application

batch

# Add a keystore to elytron for loading signature public key
/subsystem=elytron/key-store=jwt-key-store:add(type="JKS",relative-to=jboss.server.config.dir,path="$JWT_KEYSTORE",credential-reference={clear-text="$JWT_STOREPASS"})

# Add a new token security realm to elytron for authentication using JWTs
/subsystem=elytron/token-realm=jwt-realm:add(jwt={issuer=["$JWT_ISSUER"],audience=["$JWT_AUDIENCE"],key-store=jwt-key-store,certificate="$JWT_ALIAS"},principal-claim="sub")

# Add a new security domain, which uses the jwt security realm
/subsystem=elytron/security-domain=jwt-domain:add(realms=[{realm=$JWT_REALM,role-decoder=groups-to-roles}],permission-mapper=default-permission-mapper,default-realm=$JWT_REALM)

# Create http authentication factory that uses BEARER_TOKEN authentication
/subsystem=elytron/http-authentication-factory=jwt-http-authentication:add(security-domain=jwt-domain,http-server-mechanism-factory=global,mechanism-configurations=[{mechanism-name="BEARER_TOKEN",mechanism-realm-configurations=[{realm-name="$JWT_REALM"}]}])

# Configure Undertow to use our http authentication factory for authentication
/subsystem=undertow/application-security-domain=other:add(http-authentication-factory=jwt-http-authentication)

run-batch

reload

EOF
