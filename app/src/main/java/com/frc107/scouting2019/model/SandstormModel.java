package com.frc107.scouting2019.model;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.NumberQuestion;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.ToggleQuestion;

public class SandstormModel extends ScoutModel {
    private boolean startedWithGamePiece;
    private boolean placedStartingGamePiece;

    public SandstormModel() {
        super();
    }

    @Override
    public Question[] getQuestions() {
        NumberQuestion matchNumQuestion = new NumberQuestion("sandstormMatchNumber", R.id.matchNumberEditText, true);
        NumberQuestion teamNumQuestion = new NumberQuestion("sandstormTeamNumber", R.id.teamNumberEditText, true);
        matchNumQuestion.addIllegalValue(0);
        teamNumQuestion.addIllegalValue(0);

        Question[] questions = {
                matchNumQuestion,
                teamNumQuestion,
                new RadioQuestion("sandstormStartPos", R.id.sandstormStartingPositionRadioQuestion, true,
                        new RadioQuestion.Option(R.id.habTwoSandstorm_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.habOneSandstorm_Radiobtn, 0)),
                new RadioQuestion("sandstormStartPiece", R.id.sandstormStartingGamePieceRadioQuestion, true,
                        new RadioQuestion.Option(R.id.cargoSandstormStartingGamePiece_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.panelSandstormStartingGamePiece_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.noSandstormStartingGamePiece_Radiobtn, 0)),
                new RadioQuestion("sandstormItemPlaced", R.id.sandstormItemPlacedRadioQuestion, true,
                        new RadioQuestion.Option(R.id.sandstormTopRocketItemPlaced_Radiobtn, 3),
                        new RadioQuestion.Option(R.id.sandstormMiddleRocketItemPlaced_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.sandstormBottomRocketItemPlaced_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.sandstormCargoshipItemPlaced_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.sandstormFloorItemPlaced_Radiobtn, 4),
                        new RadioQuestion.Option(R.id.sandstormNothingPlacedItemPlaced_Radiobtn, 5)),
                new ToggleQuestion("sandstormBaseline", R.id.sandstormBaseline_chkbx)
        };
        return questions;
    }

    @Override
    public void onNumberQuestionAnswered(int questionId, Integer answer) {
        /*if (questionId == R.id.teamNumberEditText) {
            int teamNum = answer == null ? -1 : answer;
            Scouting.getInstance().setTeamNumber(teamNum);
        }*/
    }

    @Override
    public void onTextQuestionAnswered(int questionId, String answer) { }

    @Override
    public void onRadioQuestionAnswered(int questionId, int answerId) {
        switch (questionId) {
            case R.id.sandstormStartingGamePieceRadioQuestion:
                startedWithGamePiece = answerId != R.id.noSandstormStartingGamePiece_Radiobtn;
                break;
            case R.id.sandstormItemPlacedRadioQuestion:
                placedStartingGamePiece = answerId != R.id.sandstormNothingPlacedItemPlaced_Radiobtn;
                break;
        }
    }

    public int getTeamNumber() {
        Integer teamNumber = (Integer) getAnswer(R.id.teamNumberEditText);
        if (teamNumber == null)
            return -1;

        return teamNumber;
    }

    public void finish() {
        String sandstormData = getAnswerCSVRow();
        Scouting.getInstance().setSandstormData(sandstormData);
    }

    public boolean shouldAllowStartingPiece() {
        return startedWithGamePiece && !placedStartingGamePiece;
    }
}
