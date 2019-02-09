package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;

import java.util.Locale;

public class CycleModel extends ScoutModel {
    private boolean isTeleop;
    private int cycleNum;

    public CycleModel(Question... questions) {
        super(questions);
        setFileNameHeader("Cycle");
    }

    @Override
    public String getCSVRowHeader() {
        String type = isTeleop ? "Teleop" : "Sandstorm";
        String header = String.format(Locale.getDefault(), "%d, %d, %d, %s",
                                                        Scouting.getMatchNumber(),
                                                        Scouting.getTeamNumber(),
                                                        cycleNum,
                                                        type);
        return header;
    }

    public void finish() {
        save();
    }

    public void enterTeleop() {
        isTeleop = true;
    }

    public String newCycle() {
        cycleNum++;
        String saveResponse = save();
        return saveResponse;
    }
}
