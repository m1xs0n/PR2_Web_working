package com.m1xs0n.practical2task2.repository;

import com.m1xs0n.practical2task2.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductRepository {

    private final Map<Long, Product> productMap = new HashMap<>();

    @PostConstruct
    public void initialize() {
        // Додати 5 прикладів товарів
        saveOrUpdate(new Product(1L, "Круасан", new BigDecimal("25.00")));
        saveOrUpdate(new Product(2L, "Піцца", new BigDecimal("200.00")));
        saveOrUpdate(new Product(3L, "iPhone 15", new BigDecimal("800000.00")));
        saveOrUpdate(new Product(4L, "coca-cola", new BigDecimal("44.00")));
        saveOrUpdate(new Product(5L, "Чіпси", new BigDecimal("50.00")));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public void saveOrUpdate(Product product) {
        productMap.put(product.getId(), product);
    }

    public Product findById(Long id) {
        return productMap.get(id);
    }

    public void deleteById(Long id) {
        productMap.remove(id);
    }
}
