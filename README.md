# UrbanHive

## Inorder to start mongodb for product-service
docker run -d -p 27017:27017 --name mongodb-container -e MONGO_INITDB_DATABASE=product-service mongo:7.0

## swagger for product-service
http://10.82.135.246:8081/swagger-ui/index.html

## Inorder to start mysql container for order-service
docker run -d --name mysql_order_service -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:5.7.43

docker exec -it <container_id> bash

mysql -u root -p 

enter "root"

CREATE DATABASE `order-service`;

## swagger for order-service
http://10.82.135.246:8082/swagger-ui/index.html
