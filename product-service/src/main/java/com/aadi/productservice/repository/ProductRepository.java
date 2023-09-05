package com.aadi.productservice.repository;

import com.aadi.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
  Product findFirstByOrderByIdAsc();
}
