package dev.shoaibsuad.warehouse.service;

import dev.shoaibsuad.warehouse.entity.Product;
import dev.shoaibsuad.warehouse.exception.ResourceNotFoundException;
import dev.shoaibsuad.warehouse.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        Product product = new Product(1L, "Mouse", 10, 20.0);

        when(repository.save(product)).thenReturn(product);

        Product saved = service.saveProduct(product);

        assertNotNull(saved);
        verify(repository, times(1)).save(product);
    }

    @Test
    void testGetProductById_Found() {
        Product product = new Product(1L, "Mouse", 10, 20.0);

        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Product result = service.getProductById(1L);

        assertEquals("Mouse", result.getName());
    }

    @Test
    void testGetProductById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getProductById(1L));
    }
}