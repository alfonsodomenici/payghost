FROM quay.io/wildfly/wildfly:30.0.1.Final-jdk17
ADD ./target/payghost.war /opt/jboss/wildfly/standalone/deployments/