package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;

public class TeleopModel extends ScoutModel {
    public TeleopModel(Question... questions) {
        super(questions);
        setFileNameHeader("Match");
    }

    @Override
    public String getCSVRowHeader() {
        // TODO: this should be in the endgame model once that becomes a thing
        return Scouting.getSandstormData();
    }
}
