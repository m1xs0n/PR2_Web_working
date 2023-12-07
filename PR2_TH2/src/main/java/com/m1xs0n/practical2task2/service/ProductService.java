package com.m1xs0n.practical2task2.service;

import com.m1xs0n.practical2task2.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    void saveOrUpdate(Product product);
    Product getProductById(Long id);
    void deleteById(Long id);
}
