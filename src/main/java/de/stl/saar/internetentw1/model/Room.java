package de.stl.saar.internetentw1.model;

import lombok.Data;

@Data
public class Room {

    private int building;

    private int floor;

    private int room;

    @Override
    public String toString() {
        return String.format("%d%d%02d", building, floor, room);
    }
}
