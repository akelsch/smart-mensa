package de.stl.saar.internetentw1.model;

import lombok.Data;

/**
 * Klasse, die einen Raum beschreibt.
 */
@Data
public class Room {

    private int building;

    private int floor;

    private int room;

    /**
     * Konstruiert ein {@link Room} Objekt mit Hilfe eines Strings. Es wird
     * vorausgesetzt, dass der String numerisch ist und die Länge vier besitzt.
     * <p>
     * Die erste Zeichen steht für das Gebäude, das zweite für die Etage und
     * die letzten zwei Zeichen stehen für die Nummer des Raums.
     *
     * @param s Der Raum als String
     */
    public Room(String s) {
        building = Integer.parseInt(s.substring(0, 1));
        floor = Integer.parseInt(s.substring(1, 2));
        room = Integer.parseInt(s.substring(2, 4));
    }

    @Override
    public String toString() {
        return String.format("%d%d%02d", building, floor, room);
    }
}
