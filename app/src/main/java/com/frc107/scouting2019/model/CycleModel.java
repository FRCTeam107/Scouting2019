package com.frc107.scouting2019.model;

import com.frc107.scouting2019.R;
import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.TextQuestion;
import com.frc107.scouting2019.model.question.ToggleQuestion;

public class CycleModel extends ScoutModel {
    private int cycleNum;
    private boolean isFirstCycle = true;
    private boolean hasUsedStartingItem;

    @Override
    public Question[] getQuestions() {
        ToggleQuestion allDefenseQuestion = new ToggleQuestion("cycleAllDefense", R.id.allDefense_chkbx);
        allDefenseQuestion.setIgnoreAnswer(true);

        Question[] questions = {
                new RadioQuestion("cyclePickupLoc", R.id.pickupLocationRadioQuestion, true,
                        new RadioQuestion.Option(R.id.portPickupLocation_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.floorPickupLocation_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.startedWithItem_Radiobtn, 2)),
                new RadioQuestion("cycleItemPickedUp", R.id.itemPickedUpRadioQuestion, true,
                        new RadioQuestion.Option(R.id.cargoItemPickedUp_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.hatchItemPickedUp_Radiobtn, 1)),
                new RadioQuestion("cycleItemPlaced", R.id.itemPlacedRadioQuestion, true,
                        new RadioQuestion.Option(R.id.topRocketItemPlaced_Radiobtn, 3),
                        new RadioQuestion.Option(R.id.middleRocketItemPlaced_Radiobtn, 2),
                        new RadioQuestion.Option(R.id.bottomRocketItemPlaced_Radiobtn, 1),
                        new RadioQuestion.Option(R.id.cargoshipItemPlaced_Radiobtn, 0),
                        new RadioQuestion.Option(R.id.floorItemPlaced_Radiobtn, 4),
                        new RadioQuestion.Option(R.id.nothingPlacedItemPlaced_Radiobtn, 5)),
                new ToggleQuestion("cycleDefense", R.id.defense_chkbx),
                allDefenseQuestion
        };
        return questions;
    }

    @Override
    public void onTextQuestionAnswered(int questionId, String answer) { }

    @Override
    public void onRadioQuestionAnswered(int questionId, int answerId) {
        if (questionId == R.id.pickupLocationRadioQuestion) {
            if (answerId == R.id.startedWithItem_Radiobtn) {
                hasUsedStartingItem = true;
            } else {
                hasUsedStartingItem = false;
            }
        }
    }

    public void finishCycle() {
        cycleNum++;
        saveCycle();
        isFirstCycle = false;
    }

    private void saveCycle() {
        // TODO: This would be cleaner if you:
        // 1: renamed Questions to Fields
        // 2: for type and cycleNum, made a kind of Field that's just called VariableField or something, something that's just controlled in-code, not outside of code
        String csvRow = getAnswerCSVRow();
        String cycle = cycleNum + "," + csvRow;

        if (Scouting.SAVE_QUESTION_NAMES_AS_ANSWERS) {
            cycle = "cycleNum," + csvRow;
        }

        Scouting.getInstance().addCycle(cycle);
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

    public boolean cycleCanBeFinished() {
        if (areNoQuestionsAnswered() || isFormComplete())
            return true;

        return false;
    }

    public boolean hasUsedStartingItem() {
        return hasUsedStartingItem;
    }

    public void disableStartingItem() {
        hasUsedStartingItem = true;
    }
}
