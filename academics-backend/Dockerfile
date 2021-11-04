FROM jboss/wildfly:24.0.0.Final

ENV WILDFLY_HOME=/opt/jboss/wildfly
ENV DEPLOYMENTS_DIR=${WILDFLY_HOME}/standalone/deployments

COPY scripts ${WILDFLY_HOME}/bin

USER root

RUN yum install -y dos2unix nano
RUN dos2unix ${WILDFLY_HOME}/bin/*.sh

RUN chown jboss:jboss ${WILDFLY_HOME}/bin/*.sh
RUN chmod 755 ${WILDFLY_HOME}/bin/*.sh

USER jboss

ENTRYPOINT ${WILDFLY_HOME}/bin/start_wildfly.sh
