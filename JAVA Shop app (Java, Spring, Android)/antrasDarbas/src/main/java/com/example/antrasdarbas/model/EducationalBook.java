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
public class EducationalBook extends Product{
    private String subject;
    private String edition;
    private String level;

    public EducationalBook(String title,
                           String author,
                           double price,
                           String manufacturer,
                           int recommendedAge,
                           String topic,
                           String description,
                           //LocalDate releaseDate,
                           Warehouse warehouse,
                           String subject,
                           String edition,
                           String level) {
        super(title, author, price, manufacturer, recommendedAge, topic, description, /*releaseDate,*/ warehouse);
        this.subject = subject;
        this.edition = edition;
        this.level = level;
    }

    @Override
    public String toString() {
        return title + " by: " + author + "Edu";
    }
}

