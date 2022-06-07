## Start a single-node cluster
```
docker network create cassandra-net

docker container run --name my-cassandra \
--network cassandra-net \
-p 9042:9042 \
-d cassandra:latest
```

## Start CQL shell
```
docker container run -it --network cassandra-net --rm cassandra cqlsh my-cassandra
```
## Create keyspace
```
cqlsh> create keyspace tacocloud with replication={'class':'SimpleStrategy', 'replication_factor':1} and durable_writes=true;
```
## Query keyspace
```
cqlsh> use tacocloud;
cqlsh:tacocloud> select id, name, createdAt, ingredients from tacos;
```