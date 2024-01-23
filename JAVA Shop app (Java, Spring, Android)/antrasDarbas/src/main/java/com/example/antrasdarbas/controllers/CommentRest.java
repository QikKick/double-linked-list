package com.example.antrasdarbas.controllers;

import com.example.antrasdarbas.exceptions.CommentNotFound;
import com.example.antrasdarbas.exceptions.ProductNotFound;
import com.example.antrasdarbas.model.Comment;
import com.example.antrasdarbas.model.Product;
import com.example.antrasdarbas.repos.CommentRep;
import com.example.antrasdarbas.repos.ProductRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CommentRest {

    @Autowired
    private CommentRep commentRepository;
    @Autowired
    private ProductRep productRepository;


    // Get all comments
    @GetMapping(value = "/getAllComments")
    public @ResponseBody Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Add a new comment
    @PostMapping(value = "/addComment")
    public @ResponseBody Comment addComment(@RequestBody Map<String, Object> payload) {
        Product product = productRepository.findById((Integer) payload.get("productId"))
                .orElseThrow(() -> new ProductNotFound((Integer) payload.get("productId")));

        Comment comment = new Comment();
        comment.setCommentTitle((String) payload.get("commentTitle"));
        comment.setCommentBody((String) payload.get("commentBody"));
        comment.setDateCreated(LocalDate.parse((String) payload.get("dateCreated")));
        comment.setRating((Integer) payload.get("rating"));

        return commentRepository.saveAndFlush(comment);
    }


    // Update a comment by ID
    @PutMapping(value = "/updateCommentById/{id}")
    public @ResponseBody Comment updateComment(@PathVariable int id, @RequestBody Comment commentDetails) {
        Comment commentToUpdate = commentRepository.findById(id).orElseThrow(() -> new CommentNotFound(id));

        commentToUpdate.setCommentTitle(commentDetails.getCommentTitle());
        commentToUpdate.setCommentBody(commentDetails.getCommentBody());
        commentToUpdate.setDateCreated(commentDetails.getDateCreated());
        commentToUpdate.setRating(commentDetails.getRating());

        return commentRepository.saveAndFlush(commentToUpdate);
    }


    // Delete a comment by ID
    @DeleteMapping(value = "/deleteCommentById/{id}")
    public @ResponseBody void deleteComment(@PathVariable int id) {
        commentRepository.deleteById(id);
    }

    // Get a single comment by ID
    @GetMapping(value = "/getCommentById/{id}")
    public EntityModel<Comment> one(@PathVariable int id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFound(id));
        return EntityModel.of(comment,
                linkTo(methodOn(CommentRest.class).one(id)).withSelfRel(),
                linkTo(methodOn(CommentRest.class).getAllComments()).withRel("comments"));
    }
}

