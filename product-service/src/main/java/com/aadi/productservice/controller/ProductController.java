package com.aadi.productservice.controller;

import com.aadi.productservice.dto.ProductRequest;
import com.aadi.productservice.dto.ProductResponse;
import com.aadi.productservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduct(@RequestBody ProductRequest productRequest) {
    productService.createProduct(productRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> productService() {
    return productService.productService();
  }
}
