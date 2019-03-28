package de.stl.saar.internetentw1.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Klasse, die eine Gericht Kategorie beschreibt.
 *
 * @see Dish
 */
@RequiredArgsConstructor
public enum Category {

    SOUP("Suppe"),
    DESSERT("Nachtisch"),
    FRUIT("Obst"),
    SALAD("Salat"),
    MAIN_DISH("Hauptgericht");

    @Getter
    private final String label;
}
