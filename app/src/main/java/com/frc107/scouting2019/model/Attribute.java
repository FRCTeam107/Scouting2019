package com.frc107.scouting2019.model;

public class Attribute {
    private String name;
    private int index;

    public Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public static final Attribute AVG_CARGO = new Attribute("Average Cargo", 0),
                                    AVG_HATCH = new Attribute("Average Hatch", 1);
}
