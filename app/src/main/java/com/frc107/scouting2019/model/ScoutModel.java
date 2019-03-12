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

    public boolean areNoQuestionsAnswered() {
        for (Question question : questions) {
            if (question instanceof ToggleQuestion)
                continue;

            if (question.hasAnswer())
                return false;
        }
        return true;
    }

    public abstract boolean handleTextAnswer(int questionId, String answer);

    public boolean setAnswer(int questionId, String answer) {
        Question question = getQuestion(questionId);
        if (question == null)
            return false;

        if (!(question instanceof TextQuestion))
            return false;

        if (!handleTextAnswer(questionId, answer))
            question.setAnswer(answer);

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

        return question.getAnswerAsString();
    }

    public abstract String getCSVRowHeader();

    public String getAnswerCSVRow() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            stringBuilder.append(questions.get(i).getAnswerAsString());
            if (i < questions.size() - 1) {
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }

    public String save() {
        String dataToWrite = getCSVRowHeader() + ',' + getAnswerCSVRow();
        String result = Scouting.FILE_UTILS.writeData(fileNameHeader, dataToWrite);
        return result;
    }

    public void clearAllQuestions() {
        for (Question question : questions) {
            question.setAnswer(null);
        }
    }
}
