#!/bin/bash

echo "=> Setup Keystore"

# Access the folder where the keystore file will be stored
pushd $WILDFLY_HOME/standalone/configuration

# Generate the keystore file
keytool -genkey \
-alias $JWT_ALIAS \
-keyalg $JWT_ALGORITHM \
-keysize $JWT_KEYSIZE \
-keystore $JWT_KEYSTORE \
-storepass $JWT_STOREPASS \
-keypass $JWT_KEYPASS \
-dname "cn=$JWT_CANONICAL_NAME, ou=$JWT_ORGANIZATIONAL_UNIT, o=$JWT_ORGANIZATION, c=$JWT_COUNTRY"

# Return to the previous folder location
popd