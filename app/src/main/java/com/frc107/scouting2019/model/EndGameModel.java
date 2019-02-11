package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.Question;

import java.util.ArrayList;

public class EndGameModel extends ScoutModel {
    private static final String FILE_NAME_HEADER = "Match";

    public EndGameModel(Question... questions) {
        super(questions);
    }

    @Override
    public String getCSVRowHeader() {
        return Scouting.getInstance().getSandstormData();
    }

    public String finish() {
        StringBuilder builder = new StringBuilder();

        String sandstormData = Scouting.getInstance().getSandstormData();
        ArrayList<String> cycles = Scouting.getInstance().getCycles();
        for (String cycle : cycles) {
            String row = sandstormData + "," + cycle + "," + getAnswerCSVRow();
            builder.append(row);
            builder.append("\n");
        }
        String saveResponse = Scouting.CSV_GENERATOR.writeData(FILE_NAME_HEADER, builder.toString());
        return saveResponse;
    }
}
