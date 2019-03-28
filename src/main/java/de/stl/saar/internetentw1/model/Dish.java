package de.stl.saar.internetentw1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.*;

/**
 * Klasse, die ein Gericht beschreibt.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Wither
    private Long id;

    private String name;

    private double price;

    @Enumerated
    private Category category;

    private String image;

    public Dish(String name, double price, Category category, String image) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Dish(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
