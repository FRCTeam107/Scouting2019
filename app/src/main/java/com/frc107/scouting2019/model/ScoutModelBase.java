package com.frc107.scouting2019.model;

import com.frc107.scouting2019.model.data.QuestionBase;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoutModelBase {
    private ArrayList<QuestionBase> questions;
    private String name;

    public ScoutModelBase(String name, QuestionBase... questions) {
        this.name = name;
        this.questions = new ArrayList<>(Arrays.asList(questions));
    }

    public String getName() {
        return name;
    }

    public String getUnfinishedQuestionName() {
        for (QuestionBase question : questions) {
            if (question.needsAnswer() && !question.hasAnswer()) {
                return question.getName();
            }
        }
        return null;
    }

    public QuestionBase getQuestion(String name) {
        for (QuestionBase question : questions) {
            if (question.getName().equals(name)) {
                return question;
            }
        }
        return null;
    }

    public String getResult() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < questions.size(); i++) {
            stringBuilder.append(questions.get(i).getAnswer());
            if (i < questions.size() - 1) {
                stringBuilder.append(",");
            }
        }

        return stringBuilder.toString();
    }

    public ArrayList<QuestionBase> getQuestions() {
        return questions;
    }
}
