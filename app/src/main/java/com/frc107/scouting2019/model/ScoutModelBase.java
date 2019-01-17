package com.frc107.scouting2019.model;

import com.frc107.scouting2019.model.data.CheckBoxQuestion;
import com.frc107.scouting2019.model.data.NumberQuestion;
import com.frc107.scouting2019.model.data.Question;
import com.frc107.scouting2019.model.data.RadioButtonQuestion;
import com.frc107.scouting2019.model.data.TextQuestion;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoutModelBase {
    private int teamNumber;
    private int matchNumber;
    private ArrayList<Question> questions;
    private String name;

    public ScoutModelBase(String name, Question... questions) {
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

    public Question getQuestion(String name) {
        for (Question question : questions) {
            if (question.getName().equals(name)) {
                return question;
            }
        }
        return null;
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

                if (question instanceof RadioButtonQuestion) {
                    ((RadioButtonQuestion) question).setAnswer(answer);
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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            stringBuilder.append(questions.get(i).getAnswer());
            if (i < questions.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
