package com.m1xs0n.practical2task2;

import com.m1xs0n.practical2task2.model.Cart;
import com.m1xs0n.practical2task2.model.Product;
import com.m1xs0n.practical2task2.repository.ProductRepository;
import com.m1xs0n.practical2task2.service.CartService;
import com.m1xs0n.practical2task2.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl(new ProductRepository());
    }

    @Test
    void testAddProductToCart() {
        Cart cart = cartService.getNewCart();
        Product product = new Product(1L, "Product 1", new BigDecimal("10.00"));
        cartService.addProduct(cart, product, 3);

        int quantity = cartService.getProductQuantity(cart, product);
        assertEquals(3, quantity);
    }

    @Test
    void testRemoveProductFromCart() {
        Cart cart = cartService.getNewCart();
        Product product = new Product(2L, "Product 2", new BigDecimal("15.00"));
        cartService.addProduct(cart, product, 5);
        cartService.delProduct(cart, product, 2);

        int quantity = cartService.getProductQuantity(cart, product);
        assertEquals(3, quantity);
    }

    @Test
    void testCalculateCartTotal() {
        Cart cart = cartService.getNewCart();
        Product product1 = new Product(3L, "Product 3", new BigDecimal("20.00"));
        Product product2 = new Product(4L, "Product 4", new BigDecimal("30.00"));

        cartService.addProduct(cart, product1, 2);
        cartService.addProduct(cart, product2, 1);

        BigDecimal total = cartService.getSum(cart);
        assertEquals(new BigDecimal("70.00"), total);
    }
}
