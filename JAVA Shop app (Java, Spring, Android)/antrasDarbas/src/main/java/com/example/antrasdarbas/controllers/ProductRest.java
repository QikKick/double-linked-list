package com.example.antrasdarbas.controllers;

import com.example.antrasdarbas.exceptions.ProductNotFound;
import com.example.antrasdarbas.model.Product;
import com.example.antrasdarbas.repos.ProductRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductRest {

    @Autowired
    private ProductRep productRepository;

    // Get all products
    @GetMapping(value = "/getAllProducts")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add a new product
    @PostMapping(value = "/addProduct")
    public @ResponseBody Product addProduct(@RequestBody Product product) {
        return productRepository.saveAndFlush(product);
    }

    // Update a product by ID
    @PutMapping(value = "/updateProductById/{id}")
    public @ResponseBody Product updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));

        productToUpdate.setTitle(productDetails.getTitle());
        productToUpdate.setAuthor(productDetails.getAuthor());
        productToUpdate.setDescription(productDetails.getDescription());
        productToUpdate.setManufacturer(productDetails.getManufacturer());
        productToUpdate.setPrice(productDetails.getPrice());

        return productRepository.saveAndFlush(productToUpdate);
    }


    // Delete a product by ID
    @DeleteMapping(value = "/deleteProductById/{id}")
    public @ResponseBody void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    // Get a single product by ID
    @GetMapping(value = "/getProductById/{id}")
    public EntityModel<Product> one(@PathVariable int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));
        return EntityModel.of(product,
                linkTo(methodOn(ProductRest.class).one(id)).withSelfRel(),
                linkTo(methodOn(ProductRest.class).getAllProducts()).withRel("products"));
    }
}
