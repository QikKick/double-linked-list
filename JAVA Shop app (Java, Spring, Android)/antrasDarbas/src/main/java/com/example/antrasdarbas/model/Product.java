package com.example.antrasdarbas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String author;
    double price;
    String topic;
    int recommendedAge;
    String description;
    String manufacturer;
    //LocalDate releaseDate;
    @ManyToOne
@JoinColumn(name = "warehouse_id")
            @JsonIgnore
    Warehouse warehouse;

    @ManyToOne
    @JsonIgnore
    Cart cart;

    public Product(String title,
                   String author,
                   double price,
                   String manufacturer) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Product(String title, // V Im
                   String author,//V Im
                   double price,//
                   String manufacturer,// V
                   int recommendedAge,//
                   String topic,//
                   String description,// V
                   //LocalDate releaseDate,
                   Warehouse warehouse) { // V IM
        this.title = title;
        this.author = author;
        this.price = price;
        this.manufacturer = manufacturer;
        this.recommendedAge = recommendedAge;
        this.topic = topic;
        this.description = description;
        //this.releaseDate = releaseDate;
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return author + "; " + title + "; price: " + price+ "€";
    }

}

/**
 *     String title; Implemented
 *     String author; Implemented
 *     double price;Implemented
 *     String topic;
 *     int recommendedAge;Implemented
 *     String description; Implemented
 *     String manufacturer; Implemented
 *     LocalDate releaseDate; Implemented
 *     Warehouse; Implemented
 *     Type; Implemented
 *     Genre; Implemented
 */
