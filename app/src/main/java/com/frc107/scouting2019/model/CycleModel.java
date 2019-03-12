package com.frc107.scouting2019.model;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.ToggleQuestion;

import java.util.Locale;

public class CycleModel extends ScoutModel {
    private boolean isTeleop;
    private int cycleNum;

    @Override
    public Question[] getQuestions() {
        ToggleQuestion allDefenseQuestion = new ToggleQuestion(R.id.allDefense_chkbx);
        allDefenseQuestion.setIgnoreAnswer(true);

        Question[] questions = {
                new RadioQuestion(R.id.pickupLocationRadioQuestion, true,
                        new RadioQuestion.Option(R.id.portPickupLocation_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.floorPickupLocation_Radiobtn, 1)),
                new RadioQuestion(R.id.itemPickedUpRadioQuestion, true,
                        new RadioQuestion.Option(R.id.cargoItemPickedUp_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.hatchItemPickedUp_Radiobtn, 1)),
                new RadioQuestion(R.id.itemPlacedRadioQuestion, true,
                        new RadioQuestion.Option(R.id.topRocketItemPlaced_Radiobtn, 3),
                        new RadioQuestion.Option(R.id.middleRocketItemPlaced_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.bottomRocketItemPlaced_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.cargoshipItemPlaced_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.floorItemPlaced_Radiobtn, 4)),
                new ToggleQuestion(R.id.defense_chkbx),
                allDefenseQuestion
        };
        return questions;
    }

    @Override
    public String getCSVRowHeader() {
        return "";
    }

    public void turnTeleopOn() {
        isTeleop = true;
        cycleNum = 0;
    }

    public boolean isTeleop() {
        return isTeleop;
    }

    public void finishCycle() {
        cycleNum++;
        saveCycle();
    }

    private void saveCycle() {
        String type = isTeleop ? "1" : "0";
        String csvRow = getAnswerCSVRow();
        Scouting.getInstance().addCycle(cycleNum + "," + type + "," + csvRow);
    }

    public void setAllDefense(boolean allDefense) {
        Question pickupLocationQuestion = getQuestion(R.id.pickupLocationRadioQuestion);
        Question itemPickedUpQuestion = getQuestion(R.id.itemPickedUpRadioQuestion);
        Question itemPlacedRadioQuestion = getQuestion(R.id.itemPlacedRadioQuestion);

        boolean needsAnswers = !allDefense;
        pickupLocationQuestion.setNeedsAnswer(needsAnswers);
        itemPickedUpQuestion.setNeedsAnswer(needsAnswers);
        itemPlacedRadioQuestion.setNeedsAnswer(needsAnswers);
    }
}
