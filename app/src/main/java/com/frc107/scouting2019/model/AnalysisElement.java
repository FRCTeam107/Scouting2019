package com.frc107.scouting2019.model;

public class AnalysisElement {
    private String teamNumber;
    private String attribute;

    public AnalysisElement(String teamNumber, String attribute) {
        this.teamNumber = teamNumber;
        this.attribute = attribute;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public String getAttribute() {
        return attribute;
    }
}
