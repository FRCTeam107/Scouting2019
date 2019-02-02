package com.frc107.scouting2019.model;

import com.frc107.scouting2019.model.question.Question;

public class EndGameModel extends ScoutModel {
    public EndGameModel(Question... questions) {
        super(questions);
    }

    @Override
    public String getCSVRowHeader() {
        return getTeamNumber() + ',' + getMatchNumber();
    }
}
