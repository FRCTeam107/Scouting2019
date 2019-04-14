package com.frc107.scouting2019.endgame;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.ScoutModel;
import com.frc107.scouting2019.question.Question;
import com.frc107.scouting2019.question.RadioQuestion;
import com.frc107.scouting2019.question.ToggleQuestion;

import java.util.ArrayList;

public class EndGameModel extends ScoutModel {
    private static final String FILE_NAME_HEADER = "Match";

    @Override
    public Question[] getQuestions() {
        Question[] questions = {

                new RadioQuestion("endgameHabitatLevel", R.id.endGameHabitatLevelRadioQuestion, true,
                        new RadioQuestion.Option(R.id.habOneEndGame_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.habTwoEndGame_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.habThreeEndGame_Radiobtn, 3),
                        new RadioQuestion.Option(R.id.habNoneEndGame_Radiobtn, 0)),
                new ToggleQuestion("endgameDefenseAllMatch", R.id.endGameDefenseAllMatch_chkbx),
                new RadioQuestion("endgameDefense", R.id.endGameDefenseRadioQuestion, true,
                        new RadioQuestion.Option(R.id.endGameDefenseEffective_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.endGameDefenseIneffective_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.endGameDefenseNone_Radiobtn, 0)),
                new ToggleQuestion("endgameFouls", R.id.endGameFouls_chkbx)

        };
        return questions;
    }

    @Override
    public void onNumberQuestionAnswered(int questionId, Integer answer) { }

    @Override
    public void onTextQuestionAnswered(int questionId, String answer) { }

    @Override
    public void onRadioQuestionAnswered(int questionId, int answerId) { }

    public String finish() {
        StringBuilder builder = new StringBuilder();

        String sandstormData = Scouting.getInstance().getSandstormData();
        ArrayList<String> cycles = Scouting.getInstance().getCycles();
        if (cycles.size() == 0) {
            String cycle = "-1,-1,-1,-1,-1";
            String maxCycles = cycles.size() + "";
            if (Scouting.SAVE_QUESTION_NAMES_AS_ANSWERS)
                maxCycles = "maxCycles";

            String row = sandstormData + "," + cycle + "," + getAnswerCSVRow() + "," + maxCycles + "," + Scouting.getInstance().getInitials();
            builder.append(row);
            builder.append("\n");
        }

        for (int i = 0; i < cycles.size(); i++) {
            String cycle = cycles.get(i);
            String maxCycles = cycles.size() + "";
            if (Scouting.SAVE_QUESTION_NAMES_AS_ANSWERS)
                maxCycles = "maxCycles";

            String row = sandstormData + "," + cycle + "," + getAnswerCSVRow() + "," + maxCycles + "," + Scouting.getInstance().getInitials();
            builder.append(row);
            builder.append("\n");
        }

        Scouting.getInstance().clearCycles();
        int matchNum = Scouting.getInstance().getMatchNumber();
        Scouting.getInstance().setMatchNumber(matchNum + 1);

        return Scouting.FILE_UTILS.writeData(FILE_NAME_HEADER, builder.toString());
    }
}
