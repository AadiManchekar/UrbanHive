package com.aadi.productservice.controller;

import com.aadi.productservice.dto.ProductRequest;
import com.aadi.productservice.dto.ProductResponse;
import com.aadi.productservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<String> createProduct(
    @RequestBody ProductRequest productRequest
  ) {
    // Check if any of the required fields are missing
    if (
      productRequest.getName() == null ||
      productRequest.getDescription() == null ||
      productRequest.getPrice() == null
    ) {
      return ResponseEntity
        .badRequest()
        .body("All fields (name, description, and price) are required.");
    }
    productService.createProduct(productRequest);

    // 201 (CREATED) response without a body
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> getAllProducts() {
    return productService.productService();
  }
}
