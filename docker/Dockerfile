FROM openjdk:11
COPY ldap-service.jar /usr/src/ldap/ldap-service.jar
WORKDIR /usr/src/ldap
ENTRYPOINT [ "java","-jar","ldap-service.jar" ]