package com.example.antrasdarbas.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter


public class FictionBook extends Product{

    private String type;
    private String bookSetting;
    private Boolean bookClassic;
    public FictionBook(String title,
                       String author,
                       double price,
                       String manufacturer,
                       int recommendedAge,
                       String topic,
                       String description,
                       //LocalDate releaseDate,
                       Warehouse warehouse,
                       String type,
                       String bookSetting,
                       Boolean bookClassic

                       ) {
        super(title, author, price, manufacturer, recommendedAge, topic, description, /*releaseDate,*/ warehouse);
        this.type = type;
        this.bookSetting = bookSetting;
        this.bookClassic = bookClassic;
    }


    @Override
    public String toString() {
        return title + " by: " + author + "Fiction";
    }
}
