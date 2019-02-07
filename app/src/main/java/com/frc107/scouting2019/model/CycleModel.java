package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;

public class CycleModel extends ScoutModel {
    private boolean isTeleop;

    public CycleModel(Question... questions) {
        super(questions);
        setFileNameHeader("Cycle");
    }

    @Override
    public String getCSVRowHeader() {
        String header = isTeleop ? "Teleop" : "Sandstorm";
        return header;
    }

    public void finish() {
        save();
    }

    public void enterTeleop() {
        isTeleop = true;
    }
}
