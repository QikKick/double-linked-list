package com.example.antrasdarbas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateCreated;
    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> itemsInCart;
    @ManyToOne // Many carts can belong to one user
    @JoinColumn(name = "user_id") // This is the foreign key column in the Cart table
    @JsonIgnore
    private User user;

    public Cart() {
        this.dateCreated = LocalDate.now();
    }

    public Cart(Product selectedProduct) {
        this();
        this.itemsInCart = new ArrayList<>();
        this.itemsInCart.add(selectedProduct);

    }
}
