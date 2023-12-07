package com.m1xs0n.practical2task2;

import com.m1xs0n.practical2task2.model.Product;
import com.m1xs0n.practical2task2.repository.ProductRepository;
import com.m1xs0n.practical2task2.service.ProductService;
import com.m1xs0n.practical2task2.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(new ProductRepository());
    }

    @Test
    void testAddProduct() {
        Product product = new Product(1L, "New Product", new BigDecimal("25.00"));
        productService.saveOrUpdate(product);

        Product savedProduct = productService.getProductById(1L);
        assertEquals("New Product", savedProduct.getName());
        assertEquals(new BigDecimal("25.00"), savedProduct.getPrice());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product(2L, "Product 2", new BigDecimal("15.00"));
        productService.saveOrUpdate(product);

        Product updatedProduct = new Product(2L, "Updated Product", new BigDecimal("18.00"));
        productService.saveOrUpdate(updatedProduct);

        Product savedProduct = productService.getProductById(2L);
        assertEquals("Updated Product", savedProduct.getName());
        assertEquals(new BigDecimal("18.00"), savedProduct.getPrice());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product(3L, "Product 3", new BigDecimal("20.00"));
        productService.saveOrUpdate(product);

        productService.deleteById(3L);

        Product deletedProduct = productService.getProductById(3L);
        assertNull(deletedProduct);
    }
}

