package com.m1xs0n.practical2task2.service;

import com.m1xs0n.practical2task2.model.Cart;
import com.m1xs0n.practical2task2.model.Product;
import com.m1xs0n.practical2task2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;

    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Cart getNewCart() {
        return new Cart();
    }

    @Override
    public void addProduct(Cart cart, Product product, Integer quantity) {
        cart.addProduct(product, quantity);
    }

    @Override
    public void addProduct(Cart cart, Long prodId, Integer quantity) {
        Product product = productRepository.findById(prodId);
        if (product != null) {
            cart.addProduct(product, quantity);
        }
    }

    @Override
    public void delProduct(Cart cart, Product product, Integer quantity) {
        cart.delProduct(product, quantity);
    }

    @Override
    public BigDecimal getSum(Cart cart) {
        return cart.getSum();
    }

    @Override
    public void printCart(Cart cart) {
        for (Map.Entry<Product, Integer> entry : cart.getCartMap().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println("Product: " + product.getName() + ", Quantity: " + quantity);
        }
    }

    @Override
    public int getProductQuantity(Cart cart, Product product) {
        return cart.getCartMap().getOrDefault(product, 0);
    }

    @Override
    public int getProductQuantity(Cart cart, Long prodId) {
        Product product = productRepository.findById(prodId);
        return product != null ? cart.getCartMap().getOrDefault(product, 0) : 0;
    }
}