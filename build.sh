#!/bin/bash
mvn clean compile package spring-boot:repackage
cp target/ldap-0.0.1-SNAPSHOT-spring-boot.jar docker/ldap-service.jar
cd docker
./docker-restart.sh
cd ..