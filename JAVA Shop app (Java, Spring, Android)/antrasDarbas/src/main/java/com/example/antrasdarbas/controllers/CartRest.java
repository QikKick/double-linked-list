package com.example.antrasdarbas.controllers;

import com.example.antrasdarbas.exceptions.CartNotFound;
import com.example.antrasdarbas.exceptions.UserNotFound;
import com.example.antrasdarbas.model.Cart;
import com.example.antrasdarbas.model.User;
import com.example.antrasdarbas.repos.CartRep;
import com.example.antrasdarbas.repos.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CartRest {

    @Autowired
    private CartRep cartRepository;
    @Autowired
    private UserRep userRepository;


    // Get all carts
    @GetMapping(value = "/getAllCarts")
    public @ResponseBody Iterable<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // Add a new cart
    @PostMapping(value = "/addCart")
    public @ResponseBody Cart addCart(@RequestBody Map<String, Object> payload) {
        User user = userRepository.findById((Integer) payload.get("customer_id"))
                .orElseThrow(() -> new UserNotFound((Integer) payload.get("customer_id")));

        Cart cart = new Cart();
        cart.setDateCreated(LocalDate.parse((String) payload.get("dateCreated")));
        cart.setUser(user);

        return cartRepository.saveAndFlush(cart);
    }


    // Update a cart by ID
    @PutMapping(value = "/updateCartById/{id}")
    public @ResponseBody Cart updateCart(@PathVariable int id, @RequestBody Cart cartDetails) {
        Cart cartToUpdate = cartRepository.findById(id).orElseThrow(() -> new CartNotFound(id));

        // Update basic fields
        cartToUpdate.setDateCreated(cartDetails.getDateCreated());

        // Update the customer (user) associated with the cart
        if (cartDetails.getUser() != null) {
            User user = userRepository.findById(cartDetails.getUser().getId())
                    .orElseThrow(() -> new UserNotFound(cartDetails.getUser().getId()));
            cartToUpdate.setUser(user);
        }


        return cartRepository.saveAndFlush(cartToUpdate);
    }


    // Delete a cart by ID
    @DeleteMapping(value = "/deleteCartById/{id}")
    public @ResponseBody void deleteCart(@PathVariable int id) {
        cartRepository.deleteById(id);
    }

    // Get a single cart by ID
    @GetMapping(value = "/getCartById/{id}")
    public EntityModel<Cart> one(@PathVariable int id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFound(id));
        return EntityModel.of(cart,
                linkTo(methodOn(CartRest.class).one(id)).withSelfRel(),
                linkTo(methodOn(CartRest.class).getAllCarts()).withRel("carts"));
    }
}
