#!/bin/bash

echo "=> Setup Mail Session for fakeSMTP"
$JBOSS_CLI -c << EOF
batch

# Create the custom fakeSMTP mail session
/subsystem=mail/mail-session=fakeSMTP:add(jndi-name=java:/jboss/mail/fakeSMTP)

# Configure the custom SMTP socket binding groups
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-mail-smtp:add(host=host.docker.internal,port=25)

# Add the custom socket binding groups to the custom fakeSMTP mail session
/subsystem=mail/mail-session=fakeSMTP/server=smtp:add(outbound-socket-binding-ref=my-mail-smtp)

# Run the batch commands
run-batch

# Reload the server configuration
reload
EOF
