# Set up a mongodb server

## Start a mongodb server

```shell
docker network create mongo-net

docker container run --name my-mongo \
--network mongo-net \
-p 27017:27017 \
-d mongo:latest
```

## Start mongo shell

```shell
docker container run -it --network mongo-net --rm mongo mongosh --host my-mongo tacocloud
```

## Query collection

```shell
use tacocloud
db.tacos.findOne({})
```
