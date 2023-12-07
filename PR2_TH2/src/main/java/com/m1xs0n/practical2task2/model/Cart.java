package com.m1xs0n.practical2task2.model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    private final Map<Product, Integer> cartMap = new HashMap<>();

    public Map<Product, Integer> getCartMap() {
        return cartMap;
    }

    public void addProduct(Product product, Integer quantity) {
        if (product != null && quantity > 0) {
            cartMap.put(product, cartMap.getOrDefault(product, 0) + quantity);
        }
    }

    public void delProduct(Product product, Integer quantity) {
        if (product != null && quantity > 0) {
            Integer currentQuantity = cartMap.getOrDefault(product, 0);
            if (currentQuantity > quantity) {
                cartMap.put(product, currentQuantity - quantity);
            } else {
                cartMap.remove(product);
            }
        }
    }

    public BigDecimal getSum() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            BigDecimal price = product.getPrice();
            sum = sum.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        return sum;
    }
}
