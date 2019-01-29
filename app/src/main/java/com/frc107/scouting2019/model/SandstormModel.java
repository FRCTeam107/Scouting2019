package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;

public class SandstormModel extends ScoutModel {
    public SandstormModel(Question... questions) {
        super(questions);
    }

    @Override
    public String getCSVRowHeader() {
        return Scouting.getTeamNumber() + "," + Scouting.getMatchNumber();
    }

    public void finish() {
        String csvRow = getAnswerCSVRow();
        Scouting.setSandstormData(csvRow);
    }
}
