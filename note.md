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

## Access ingredients api protected by resource server

```bash
curl -v -H"Content-type: application/json" -d'{"id":"CRKT", "name":"Legless Crickets", "type":"PROTEIN"}' -H"Authorization: Bearer eyJraWQiOiJiM2I5MWQ3ZC1kOTI3LTQzNjktYTdmOC03MmM0YTg4M2NkNjEiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdW5kYWxlaSIsImF1ZCI6InRhY28tYWRtaW4tY2xpZW50IiwibmJmIjoxNjgwODc3ODU2LCJzY29wZSI6WyJkZWxldGVJbmdyZWRpZW50cyIsIndyaXRlSW5ncmVkaWVudHMiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjgwODc4MTU2LCJpYXQiOjE2ODA4Nzc4NTZ9.bGE7HZUSp0LCGe81-lU7xTf08AJ7gKDtbYAhQMzSMTUsryAPDxWyiAnHp7Z5RqwtxIONuLopP2G25m-A703_sRggA4i_TvL3ebUlFgy63hsibTrJ3OysObzaO_vt1QMyHgsUVQdB_LQ8hvq1NLfnJ9dVvds-GKfTPnZRhh8OzRvZl6I-byAmGWe5i8EGr54wCIKaU5EuN_q6MvlMJDduSAg_iryy21R5ZFT2TwJTjtyCvQ07qDo0WAitsmsPf0m0wdDhylEDBfiEEo-4xNmA9gNQv925E5vKPhlbQ7L0BkYBGHeEbO18U4n9_ombKxeQYUvHhAUsXccMLzSlD0otfQ" localhost:8080/api/ingredients
```
