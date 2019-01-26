package com.frc107.scouting2019.model;

import android.os.Environment;
import android.util.Log;

import com.frc107.scouting2019.model.question.ToggleQuestion;
import com.frc107.scouting2019.model.question.NumberQuestion;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.TextQuestion;
import com.frc107.scouting2019.utils.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ScoutModel {
    private String teamNumber;
    private String matchNumber;
    private ArrayList<Question> questions;
    private String fileNameHeader;

    public ScoutModel(Question... questions) {
        this.questions = new ArrayList<>(Arrays.asList(questions));
    }

    public void setFileNameHeader(String fileNameHeader) {
        this.fileNameHeader = fileNameHeader;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(String matchNumber) {
        this.matchNumber = matchNumber;
    }

    public int getFirstUnfinishedQuestionId() {
        for (Question question : questions) {
            if (!question.needsAnswer())
                continue;

            if (!question.hasAnswer())
                return question.getId();
        }
        return -1;
    }

    public boolean setAnswer(int questionId, String answer) {
        Question question = getQuestion(questionId);
        if (question == null)
            return false;

        if (question instanceof TextQuestion) {
            ((TextQuestion) question).setAnswer(answer);
            return true;
        }

        return false;
    }

    public boolean setAnswer(int questionId, int answer) {
        Question question = getQuestion(questionId);
        if (question == null)
            return false;

        if (question instanceof NumberQuestion) {
            ((NumberQuestion) question).setAnswer(answer);
            return true;
        }

        if (question instanceof RadioQuestion) {
            ((RadioQuestion) question).setAnswer(answer);
            return true;
        }

        return false;
    }

    public boolean setAnswer(int questionId, boolean answer) {
        Question question = getQuestion(questionId);
        if (question == null)
            return false;

        if (question instanceof ToggleQuestion) {
            ((ToggleQuestion) question).setAnswer(answer);
            return true;
        }

        return false;
    }

    private Question getQuestion(int id) {
        for (Question question : questions) {
            if (question.getId() == id)
                return question;
        }
        return null;
    }

    public String getAnswerForQuestion(int id) {
        Question question = getQuestion(id);
        if (question == null)
            return null;

        return question.getAnswer();
    }

    public abstract String getCSVRowHeader();

    public String getAnswerCSVRow() {
        StringBuilder stringBuilder = new StringBuilder();
        String header = getCSVRowHeader();
        stringBuilder.append(header);

        if (!StringUtils.isEmptyOrNull(header))
            stringBuilder.append(',');

        for (int i = 0; i < questions.size(); i++) {
            stringBuilder.append(questions.get(i).getAnswer());
            if (i < questions.size() - 1) {
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }

    public String save(String uniqueDeviceId) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting");
            dir.mkdirs();

            File file = new File(dir, fileNameHeader + uniqueDeviceId + ".csv");

            String result = getAnswerCSVRow();
            String message = result + "\n";

            try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
                fileOutputStream.write(message.getBytes());
                fileOutputStream.close();

                return "Saved successfully.";
            } catch (IOException e) {
                Log.d("Scouting", e.getMessage());
                return "IOException! Go talk to the programmers.";
            }
        } else {
            return "SD card not found.";
        }
    }
}
