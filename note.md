## test credit card number
```
38520000023237
```
## Start a mongodb server
```
docker network create mongo-net

docker container run --name my-mongo \
--network mongo-net \
-p 27017:27017 \
-d mongo:latest
```

## Start mongo shell
```
docker container run -it --network mongo-net --rm mongo mongo --host my-mongo tacocloud
```

## Query collection
```
use tacocloud
db.tacos.findOne({})
```