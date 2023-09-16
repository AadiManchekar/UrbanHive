package com.aadi.productservice.builder;

import com.aadi.productservice.dto.ProductRequest;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestBuilder {

  private String name;
  private String description;
  private BigDecimal price;

  private ProductRequestBuilder() {
    // Private constructor to prevent direct instantiation
  }

  public static ProductRequestBuilder create() {
    return new ProductRequestBuilder();
  }

  public ProductRequestBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ProductRequestBuilder description(String description) {
    this.description = description;
    return this;
  }

  public ProductRequestBuilder price(BigDecimal price) {
    this.price = price;
    return this;
  }

  public ProductRequest build() {
    return ProductRequest
      .builder()
      .name(this.name)
      .description(this.description)
      .price(this.price)
      .build();
  }
}
