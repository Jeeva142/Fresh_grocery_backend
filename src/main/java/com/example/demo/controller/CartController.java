package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("http://localhost:3000")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    // Add Item
    @PostMapping
    public Cart addToCart(@RequestBody Cart cart) {

        return cartRepository.save(cart);
    }

    // Get All Cart Items
    @GetMapping
    public List<Cart> getCartItems() {

        return cartRepository.findAll();
    }

    // Update Quantity
    @PutMapping("/{id}")
    public Cart updateQuantity(
            @PathVariable Long id,
            @RequestBody Cart updatedCart
    )
    {

        Cart cart = cartRepository.findById(id).orElse(null);

        if (cart == null) {
            return null;
        }

        cart.setQuantity(updatedCart.getQuantity());

        return cartRepository.save(cart);
    }
    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {

        cartRepository.deleteById(id);
    }
}