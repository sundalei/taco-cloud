https://github.com/spring-projects/spring-security/issues/5341

## create a running db instance via docker
```docker
docker network create mariadb-network
```

```
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

```
docker run -it --network mariadb-network --rm mariadb mysql -hmariadb-db -uroot -p
create database tacocloud;
```