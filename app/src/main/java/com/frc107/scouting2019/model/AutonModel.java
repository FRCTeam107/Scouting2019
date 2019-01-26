package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;

public class AutonModel extends ScoutModel {
    public AutonModel(Question... questions) {
        super(questions);
    }

    @Override
    public String getCSVRowHeader() {
        return Scouting.getTeamNumber() + "," + Scouting.getMatchNumber();
    }
}
