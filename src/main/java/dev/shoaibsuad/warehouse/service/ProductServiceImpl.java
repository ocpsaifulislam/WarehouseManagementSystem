package dev.shoaibsuad.warehouse.service;

import dev.shoaibsuad.warehouse.entity.Product;
import dev.shoaibsuad.warehouse.exception.ResourceNotFoundException;
import dev.shoaibsuad.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product updateStock(Long id, Integer quantity) {
        Product product = getProductById(id);
        product.setQuantity(quantity);
        return repository.save(product);
    }
}