package com.frc107.scouting2019.model;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;

public class SandstormModel extends ScoutModel {
    public SandstormModel() {
        super();
    }

    @Override
    public Question[] getQuestions() {
        Question[] questions = {
                new RadioQuestion(R.id.sandstormStartingPositionRadioQuestion, true,
                        new RadioQuestion.Option(R.id.habTwoSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.habOneSandstorm_Radiobtn, 0)),
                new RadioQuestion(R.id.sandstormStartingGamePieceRadioQuestion, true,
                        new RadioQuestion.Option(R.id.cargoSandstormStartingGamePiece_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.panelSandstormStartingGamePiece_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noSandstormStartingGamePiece_Radiobtn, 0)),
                new RadioQuestion(R.id.sandstormItemPlacedRadioQuestion, true,
                        new RadioQuestion.Option(R.id.sandstormTopRocketItemPlaced_Radiobtn, 3),
                        new RadioQuestion.Option(R.id.sandstormMiddleRocketItemPlaced_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.sandstormBottomRocketItemPlaced_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.sandstormCargoshipItemPlaced_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.sandstormFloorItemPlaced_Radiobtn, 4)),
        };
        return questions;
    }

    @Override
    public String getCSVRowHeader() {
        return Scouting.getInstance().getMatchNumber() + "," + Scouting.getInstance().getTeamNumber();
    }

    public void finish() {
        String sandstormData = getCSVRowHeader() + ',' + getAnswerCSVRow();
        Scouting.getInstance().setSandstormData(sandstormData);
    }
}
