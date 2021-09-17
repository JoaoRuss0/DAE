#!/bin/bash

ECLIPSELINK_PATH=$WILDFLY_HOME/modules/system/layers/base/org/eclipse/persistence/main

echo "=> Downloading EclipseLink v$ECLIPSELINK_VERSION"
curl https://repo1.maven.org/maven2/org/eclipse/persistence/eclipselink/$ECLIPSELINK_VERSION/eclipselink-$ECLIPSELINK_VERSION.jar --output $ECLIPSELINK_PATH/eclipselink.jar

echo "=> Installing EclipseLink v$ECLIPSELINK_VERSION"
sed -i "s/<\/resources>/\n\
        <resource-root path=\"eclipselink.jar\">\n \
            <filter>\n \
                <exclude path=\"javax\/**\" \/>\n \
            <\/filter>\n \
        <\/resource-root>\n \
    <\/resources>/" $ECLIPSELINK_PATH/module.xml

sed -i "s/<\/dependencies>/\
    <module name=\"javax.ws.rs.api\"\/>\n\
    <module name=\"javax.json.api\"\/>\n\
    <\/dependencies>/" $ECLIPSELINK_PATH/module.xml
