# UrbanHive

## Inorder to start mongodb for product-service
docker run -d -p 27017:27017 --name mongodb-container -e MONGO_INITDB_DATABASE=product-service mongo

## swagger 
http://10.82.135.246:8081/swagger-ui/index.html