package com.frc107.scouting2019.model;

import com.frc107.scouting2019.model.question.Question;

public class TeleopModel extends ScoutModel {
    private String autonData;

    public TeleopModel(String autonData, Question... questions) {
        super(questions);
        this.autonData = autonData;
        setFileNameHeader("Match");
    }

    @Override
    public String getCSVRowHeader() {
        return autonData;
    }
}
