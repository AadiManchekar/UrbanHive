package com.aadi.productservice.service;

import com.aadi.productservice.dto.ProductRequest;
import com.aadi.productservice.dto.ProductResponse;
import com.aadi.productservice.model.Product;
import com.aadi.productservice.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  public void createProduct(ProductRequest productRequest) {
    Product product = Product
      .builder()
      .name(productRequest.getName())
      .description(productRequest.getDescription())
      .price(productRequest.getPrice())
      .build();

    productRepository.save(product);
    log.info("Product {} is saved", product.getId());
  }

  public List<ProductResponse> productService() {
    List<Product> products = productRepository.findAll();

    return products.stream().map(this::mapProductToProductResponse).toList();
  }

  public ProductResponse mapProductToProductResponse(Product product) {
    return ProductResponse
      .builder()
      .id(product.getId())
      .name(product.getName())
      .description(product.getDescription())
      .price(product.getPrice())
      .build();
  }
}
