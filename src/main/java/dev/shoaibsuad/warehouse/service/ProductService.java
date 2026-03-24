package dev.shoaibsuad.warehouse.service;

import dev.shoaibsuad.warehouse.entity.Product;

public interface ProductService {
    Product saveProduct(Product product);

    Product getProductById(Long id);

    Product updateStock(Long id, Integer quantity);
}