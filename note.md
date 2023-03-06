# Create a running db instance via docker

Set up a working mysql server instance and client via docker compose.

```docker
version: '3'

services:
  db:
    image: mariadb:latest
    restart: always
    container_name: mariadb-db
    environment:
      - MARIADB_ROOT_PASSWORD=oarnud9I
    ports:
      - 3306:3306
    volumes:
      - mariadb:/var/lib/mysql
  adminer:
    image: adminer
    restart: always
    ports:
      - 8080:8080

volumes:
  mariadb:
```

## Create database tacocloud

```docker
> docker container exec -it mariadb-db bash
> mysql -u root -p
> create database tacocloud;
```

## Create a user and set permissions

```shell
CREATE USER 'tacouser'@'%' IDENTIFIED BY 'tacopassword';

GRANT ALL ON tacocloud.* TO 'tacouser'@'%';
```

run with profile `mysql`

```bash
java -jar -Dspring.profiles.active=mysql target/taco-cloud-0.0.1-SNAPSHOT.jar
```

run different profile via maven

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
