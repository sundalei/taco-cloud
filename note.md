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
curl -v -H"Content-type: application/json" -d'{"id":"CRKT", "name":"Legless Crickets", "type":"PROTEIN"}' -H"Authorization: Bearer eyJraWQiOiJiM2I5MWQ3ZC1kOTI3LTQzNjktYTdmOC03MmM0YTg4M2NkNjEiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdW5kYWxlaSIsImF1ZCI6InRhY28tYWRtaW4tY2xpZW50IiwibmJmIjoxNjgwODc3Mzk3LCJzY29wZSI6WyJkZWxldGVJbmdyZWRpZW50cyIsIndyaXRlSW5ncmVkaWVudHMiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjgwODc3Njk3LCJpYXQiOjE2ODA4NzczOTd9.fw9rYPhSiuG7xDYKUx54TnwhBWwni1cVXVE5gTHzzoNL1mPvRArFz4M4wGjeODKVX_mzrbpZCv37QKSjpNmN7LvTachdXAPqE-28vVisdNRpBVk4UWtlF22uSdcpNLjjNgizjiZrTjWM3u1frbaSeVihiBJnxZTQcLmNtqfpFubn8lPFCrMJ4C5SMS1TgzchlAdKBV24ZuemL66Pat3Ns9qaQXvdAKncsInVQqKwDLJ5RCJC4t76qSg8151L8exwsNR7UJbouZR4Dv_C2sl9p0BWo5WZShreAl1DXHQZxaHOBi6fwDc8AyIQx5ijJbpMflxOPwzFvHOEhHN7FC3GNQ" localhost:8080/api/ingredients
```
