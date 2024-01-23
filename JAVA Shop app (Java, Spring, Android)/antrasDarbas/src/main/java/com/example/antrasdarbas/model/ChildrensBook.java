package com.example.antrasdarbas.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChildrensBook extends Product{
    private String ageRange;
    private String illustrator;
    private String interactiveFeatures;

    public ChildrensBook(String title,
                         String author,
                         double price,
                         String manufacturer,
                         int recommendedAge,
                         String topic,
                         String description,
                        // LocalDate releaseDate,
                         Warehouse warehouse,
                         String ageRange,
                         String illustrator,
                         String interactiveFeatures) {
        super(title, author, price, manufacturer, recommendedAge, topic, description, /*releaseDate,*/ warehouse);
        this.ageRange = ageRange;
        this.illustrator = illustrator;
        this.interactiveFeatures = interactiveFeatures;
    }

    @Override
    public String toString() {
        return title + " by: " + author + "Childrens";
    }
}
