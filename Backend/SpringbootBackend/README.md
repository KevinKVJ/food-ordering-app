## Food Ordering App Backend

```shell
# to deploy all services and the app in docker containers
# to clean all cache before building images (optional)
docker system prune --all --force
# to build:
docker-compose build --no-cache
# to run:
docker-compose up -d
# to quit and clean
docker-compose down --rmi all
# to remove local volumes mapping
rm -rfv ${HOME}/volumes_mapping
```

```shell
# to deploy services only in docker containers
docker run -d --name redis01 -p 16379:6379 redis:latest
docker run --name mongodb01 -p 17017:27017 -d -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo:latest
docker run -d -p 13306:3306 --name mysql01 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
rm -rfv ~/minio
docker run -d \
  -p 9000:9000 \
  -p 9001:9001 \
  --name minio1 \
  -v ~/minio/data1:/data1 \
  -v ~/minio/data2:/data2 \
  -v ~/minio/data3:/data3 \
  -v ~/minio/data4:/data4 \
  -v ~/minio/data5:/data5 \
  -v ~/minio/data6:/data6 \
  -v ~/minio/data7:/data7 \
  -v ~/minio/data8:/data8 \
  -e "MINIO_ROOT_USER=AKIAIOSFODNN7EXAMPLE" \
  -e "MINIO_ROOT_PASSWORD=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" \
  quay.io/minio/minio server /data{1...8} --console-address ":9001"
docker exec -i -t mysql01 mysql -u root -p123456 -e "create database if not exists food_ordering_app"
```