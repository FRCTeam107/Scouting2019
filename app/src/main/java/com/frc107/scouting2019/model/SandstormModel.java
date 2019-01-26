package com.frc107.scouting2019.model;

import com.frc107.scouting2019.model.question.Question;

public class SandstormModel extends ScoutModel {
    public SandstormModel(Question... questions) {
        super(questions);
    }

    @Override
    public String getCSVRowHeader() {
        return getTeamNumber() + ',' + getMatchNumber();
    }
}
