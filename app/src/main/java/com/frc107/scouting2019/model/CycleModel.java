package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;

import java.util.Locale;

public class CycleModel extends ScoutModel {
    private boolean isTeleop;
    private int cycleNum;

    public CycleModel(Question... questions) {
        super(questions);
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
}
