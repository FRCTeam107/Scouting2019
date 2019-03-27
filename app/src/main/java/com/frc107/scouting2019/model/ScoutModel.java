package com.frc107.scouting2019.model;

import com.frc107.scouting2019.Scouting;
import com.frc107.scouting2019.model.question.ToggleQuestion;
import com.frc107.scouting2019.model.question.NumberQuestion;
import com.frc107.scouting2019.model.question.Question;
import com.frc107.scouting2019.model.question.RadioQuestion;
import com.frc107.scouting2019.model.question.TextQuestion;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ScoutModel {
    private ArrayList<Question> questions;
    private String fileNameHeader;

    public ScoutModel() {
        this.questions = new ArrayList<>(Arrays.asList(getQuestions()));
    }

    public abstract Question[] getQuestions();

    public void setFileNameHeader(String fileNameHeader) {
        this.fileNameHeader = fileNameHeader;
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

    // TODO: This should probably have an implementation per-model, so we can avoid things like cycleCanBeFinished. It's bad pattern.
    public boolean isFormComplete() {
        for (Question question : questions) {
            if (!question.needsAnswer())
                continue;

            if (!question.hasAnswer())
                return false;
        }
        return true;
    }

    public boolean areNoQuestionsAnswered() {
        for (Question question : questions) {
            if (!question.needsAnswer())
                continue;

            if (question instanceof ToggleQuestion)
                continue;

            if (question.hasAnswer())
                return false;
        }
        return true;
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

    public Question getQuestion(int id) {
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

        return question.getAnswerAsString();
    }

    public abstract String getCSVRowHeader();

    public String getAnswerCSVRow() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (!question.answerCanBeIgnored()) {
                if (i > 0)
                    stringBuilder.append(',');

                if (Scouting.SAVE_QUESTION_NAMES_AS_ANSWERS)
                    stringBuilder.append(question.getName());
                else
                    stringBuilder.append(question.getAnswerAsString());
            }
        }
        return stringBuilder.toString();
    }

    public String save() {
        String header = getCSVRowHeader();
        if (header.length() > 0)
            header += ',';

        String dataToWrite = header + getAnswerCSVRow() + '\n';
        String result = Scouting.FILE_UTILS.writeData(fileNameHeader, dataToWrite);
        return result;
    }

    public void clearAllQuestions() {
        for (Question question : questions) {
            question.setAnswer(null);
        }
    }
}
