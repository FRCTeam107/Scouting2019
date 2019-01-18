package com.frc107.scouting2019.model;

import com.frc107.scouting2019.model.question.CheckBoxQuestion;
import com.frc107.scouting2019.model.question.NumberQuestion;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.TextQuestion;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoutModel {
    private int teamNumber;
    private int matchNumber;
    private ArrayList<Question> questions;
    private String name;

    public ScoutModel(String name, Question... questions) {
        this.name = name;
        this.questions = new ArrayList<>(Arrays.asList(questions));
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public String getName() {
        return name;
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
        for (Question question : questions) {
            if (question.getId() == questionId) {
                if (question instanceof TextQuestion) {
                    ((TextQuestion) question).setAnswer(answer);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setAnswer(int questionId, int answer) {
        for (Question question : questions) {
            if (question.getId() == questionId) {
                if (question instanceof NumberQuestion) {
                    ((NumberQuestion) question).setAnswer(answer);
                    return true;
                }

                if (question instanceof RadioQuestion) {
                    ((RadioQuestion) question).setAnswer(answer);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setAnswer(int questionId, boolean answer) {
        for (Question question : questions) {
            if (question.getId() == questionId) {
                if (question instanceof CheckBoxQuestion) {
                    ((CheckBoxQuestion) question).setAnswer(answer);
                    return true;
                }
            }
        }
        return false;
    }

    public String getResult() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(teamNumber);
        stringBuilder.append(',');
        stringBuilder.append(matchNumber);
        stringBuilder.append(',');

        for (int i = 0; i < questions.size(); i++) {
            stringBuilder.append(questions.get(i).getAnswer());
            if (i < questions.size() - 1) {
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }
}
