package com.frc107.scouting2019.model;

import com.frc107.scouting2019.model.data.IQuestion;
import com.frc107.scouting2019.utils.FormatStringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ScoutModelBase {
    private ArrayList<IQuestion> questions;
    private String name;

    public ScoutModelBase(String name, IQuestion... questions) {
        this.name = name;
        this.questions = new ArrayList<>(Arrays.asList(questions));
    }

    public String getName() {
        return name;
    }

    public String getUnfinishedQuestionName() {
        for (IQuestion question : questions) {
            if (question.needsAnswer() && !question.hasAnswer()) {
                return question.getName();
            }
        }
        return null;
    }

    public IQuestion getQuestion(String name) {
        for (IQuestion question : questions) {
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

    public ArrayList<IQuestion> getQuestions() {
        return questions;
    }
}
