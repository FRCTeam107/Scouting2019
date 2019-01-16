package com.frc107.scouting2019;

public class Question {
    private String name;
    private Type type;

    public Question(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    enum Type {
        CHECKBOX,
        NUMERICAL,
        MULTIPLE_CHOICE,
        TEXT
    }
}
