package dev.shoaibsuad.warehouse.controller;

import dev.shoaibsuad.warehouse.entity.Product;
import dev.shoaibsuad.warehouse.exception.ResourceNotFoundException;
import dev.shoaibsuad.warehouse.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product(1L, "Mouse", 10, 20.0);

        Mockito.when(service.saveProduct(Mockito.any()))
                .thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mouse"));
    }

    @Test
    void testGetProduct() throws Exception {
        Product product = new Product(1L, "Mouse", 10, 20.0);

        Mockito.when(service.getProductById(1L))
                .thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk());
    }
    @Test
    void testGetProduct_NotFound() throws Exception {

        Mockito.when(service.getProductById(1L))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound());
    }
}