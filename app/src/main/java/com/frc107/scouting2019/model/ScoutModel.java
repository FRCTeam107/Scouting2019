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

    /**
     * Create a scout model. You should create classes extending this class.
     */
    public ScoutModel() {
        this.questions = new ArrayList<>(Arrays.asList(getQuestions()));
    }

    /**
     * Implement this on your model. Have it return an array of all the questions you want.
     * @return An array of Question objects.
     */
    public abstract Question[] getQuestions();

    /**
     * Set the header on your saved file name. For example, if you wanted the file name to be in the format Match(id).csv, you would pass in "Match" for fileNameHeader.
     * @param fileNameHeader The name preceding the device ID in the saved file name.
     */
    public void setFileNameHeader(String fileNameHeader) {
        this.fileNameHeader = fileNameHeader;
    }

    /**
     * TODO: This also will need to use the isQuestionComplete method.
     * @return The ID of the first question that is found to be unfinished.
     */
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
    // TODO: More todo, make this just call a method called isQuestionComplete for each question that'll be implemented in each model.
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

    public abstract void onNumberQuestionAnswered(int questionId, Integer answer);
    public abstract void onTextQuestionAnswered(int questionId, String answer);
    public abstract void onRadioQuestionAnswered(int questionId, int answerId);

    public boolean setAnswer(int questionId, String answer) {
        Question question = getQuestion(questionId);
        if (question == null)
            return false;

        if (question instanceof NumberQuestion) {
            Integer numAnswer = answer.length() == 0 ? null : Integer.valueOf(answer);
            onNumberQuestionAnswered(questionId, numAnswer);
            question.setAnswer(numAnswer);
            return true;
        } else if (question instanceof TextQuestion) {
            onTextQuestionAnswered(questionId, answer);
            question.setAnswer(answer);
            return true;
        }
        return false;
    }

    public boolean setAnswer(int questionId, int answer) {
        Question question = getQuestion(questionId);
        if (question == null)
            return false;

        if (question instanceof RadioQuestion) {
            onRadioQuestionAnswered(questionId, answer);
            question.setAnswer(answer);
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

    /**
     * Get the answer to a question in a string representation.
     * @param id The question id.
     * @return The answer as a string.
     */
    public String getAnswerAsString(int id) {
        Question question = getQuestion(id);
        if (question == null)
            return null;

        return question.getAnswerAsString();
    }

    /**
     * Get the answer to a question as the object that the question is represented by.
     * @param id The question id.
     * @return The answer.
     */
    public Object getAnswer(int id) {
        Question question = getQuestion(id);
        if (question == null)
            return null;

        return question.getAnswer();
    }

    /**
     * @return The answers to all the questions formatted nicely separated by commas.
     */
    public String getAnswerCSVRow() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (question.answerCanBeIgnored())
                continue;

            if (i > 0)
                stringBuilder.append(',');

            if (Scouting.SAVE_QUESTION_NAMES_AS_ANSWERS)
                stringBuilder.append(question.getName());
            else
                stringBuilder.append(question.getAnswerAsString());
        }
        return stringBuilder.toString();
    }

    public String save() {
        String dataToWrite = getAnswerCSVRow() + '\n';
        String result = Scouting.FILE_UTILS.writeData(fileNameHeader, dataToWrite);
        return result;
    }

    public void clearAllQuestions() {
        for (Question question : questions) {
            question.setAnswer(null);
            // TODO: Implement a method in the question classes called clearAnswer.
        }
    }
}
