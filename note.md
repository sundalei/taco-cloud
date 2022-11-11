# Create a running db instance via docker

```docker
docker network create mariadb-network
```

```docker
docker container run --detach \
--network mariadb-network \
--name mariadb-db \
--env MARIADB_USER=tacouser \
--env MARIADB_PASSWORD=tacopassword \
--env MARIADB_ROOT_PASSWORD=oarnud9I \
--publish 3306:3306 \
--volume mariadb:/var/lib/mysql \
mariadb:latest
```

```docker
docker run -it --network mariadb-network --rm mariadb mysql -hmariadb-db -uroot -p
create database tacocloud;
```

run with profile `mysql`
```
java -jar -Dspring.profiles.active=mysql target/taco-cloud-0.0.1-SNAPSHOT.jar
```