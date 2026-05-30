package com.example.demo.controller;

import com.example.demo.entity.Wishlist;
import com.example.demo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin("http://localhost:3000")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Add Wishlist
    @PostMapping
    public Wishlist addWishlist(
            @RequestBody Wishlist wishlist
    ) {

        return wishlistRepository.save(wishlist);
    }

    // Get Wishlist
    @GetMapping
    public List<Wishlist> getWishlist() {

        return wishlistRepository.findAll();
    }

    // Remove Wishlist
    @DeleteMapping("/{id}")
    public String removeWishlist(
            @PathVariable Long id
    ) {

        wishlistRepository.deleteById(id);

        return "Wishlist Removed";
    }


}