package com.aadi.productservice;

import com.aadi.productservice.builder.ProductRequestBuilder;
import com.aadi.productservice.dto.ProductRequest;
import com.aadi.productservice.dto.ProductResponse;
import com.aadi.productservice.model.Product;
import com.aadi.productservice.repository.ProductRepository;
import com.aadi.productservice.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add(
      "spring.data.mongodb.uri",
      mongoDBContainer::getReplicaSetUrl
    );
  }

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository productRepository;

  @Test
  void shouldCreateProduct() throws Exception {
    ProductRequest productRequest = ProductRequest
      .builder()
      .name("Iphone 13")
      .description("Iphone 13")
      .price(BigDecimal.valueOf(82000))
      .build();

    String productRequestString = objectMapper.writeValueAsString(
      productRequest
    );

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/v1/product")
          .contentType(MediaType.APPLICATION_JSON)
          .content(productRequestString)
      )
      .andExpect(MockMvcResultMatchers.status().isCreated());

    Assertions.assertEquals(1, productRepository.findAll().size());
  }

  @Test
  void shouldNotCreateProduct() throws Exception {
    // intentionally i didnt add price in here
    ProductRequest productRequest = ProductRequestBuilder
      .create()
      .name("Iphone 13")
      .description("Iphone 13")
      .build();

    String productRequestString = objectMapper.writeValueAsString(
      productRequest
    );

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/api/v1/product")
          .contentType(MediaType.APPLICATION_JSON)
          .content(productRequestString)
      )
      .andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void shouldGetProduct() throws Exception {
    List<ProductResponse> productResponse = getProductResponse();

    MvcResult result = mockMvc
      .perform(
        MockMvcRequestBuilders
          .get("/api/v1/product")
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andReturn();

    String content = result.getResponse().getContentAsString();
    List<Product> actualResponses = objectMapper.readValue(
      content,
      new TypeReference<List<Product>>() {}
    );

    Assertions.assertEquals(productResponse, actualResponses);
  }

  private List<ProductResponse> getProductResponse() {
    List<Product> productResponses = productRepository.findAll();

    return productResponses
      .stream()
      .map(productService::mapProductToProductResponse)
      .toList();
  }
}
