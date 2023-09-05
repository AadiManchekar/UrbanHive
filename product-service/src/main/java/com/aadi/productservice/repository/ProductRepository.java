package com.aadi.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aadi.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
