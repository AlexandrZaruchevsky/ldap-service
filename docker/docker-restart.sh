#!/bin/bash
docker compose stop
docker rm docker-ldap-service-1
docker rmi docker-ldap-service:latest
docker compose up -d