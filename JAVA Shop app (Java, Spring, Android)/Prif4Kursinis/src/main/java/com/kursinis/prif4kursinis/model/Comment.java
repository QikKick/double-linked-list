package com.kursinis.prif4kursinis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne  // This establishes the many-to-one relationship
    @JoinColumn(name = "product_id", nullable = false)  // This is the foreign key column in the Comment table.
    private Product product;
    private String commentTitle;
    private String commentBody;
    private LocalDate dateCreated;
    private int rating;

    public Comment(Product product, String commentTitle, String commentBody,int rating) {
        this.product = product;
        this.commentTitle = commentTitle;
        this.commentBody = commentBody;
        this.dateCreated = LocalDate.now();
        this.rating = rating;
    }

    @Override
    public String toString() {
        return commentTitle + ":" + dateCreated;
    }
}
