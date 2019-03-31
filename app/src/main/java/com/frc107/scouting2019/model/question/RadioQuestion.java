package com.frc107.scouting2019.model.question;

import java.util.ArrayList;
import java.util.Arrays;

public class RadioQuestion extends Question<Integer> {
    private ArrayList<Option> options;
    private Option selectedOption;

    /**
     * Create a radio question.
     * @param name The name of the question. At this point, it's only used for debugging.
     * @param id The id of the question to differentiate it.
     * @param needsAnswer Does it need an answer?
     * @param options A list of options for the radio question.
     */
    public RadioQuestion(String name, int id, boolean needsAnswer, Option... options) {
        super(name, id, needsAnswer);
        this.options = new ArrayList<>(Arrays.asList(options));
    }

    @Override
    public boolean hasAnswer() {
        return selectedOption != null;
    }

    /**
     * @param answerId The ID of the option you're setting the answer to.
     */
    @Override
    public void setAnswer(Integer answerId) {
        // We check for -1 here because when the check is cleared from the RadioGroup, it sets it as being checked to id -1. It's
        // more efficient to just set the answer to null when the id is -1 because then we avoid looping through the options.
        if (answerId == null || answerId == -1) {
            selectedOption = null;
            return;
        }

        for (Option option : options) {
            if (option.getId() == answerId) {
                selectedOption = option;
            }
        }
    }

    /**
     * @return The ID of the option that is the answer.
     */
    @Override
    public Integer getAnswer() {
        return selectedOption.getId();
    }

    /**
     * @return A string containing either -1 for no answer, or the number representation of the answer.
     */
    @Override
    public String getAnswerAsString() {
        if (selectedOption == null)
            return "-1";

        return selectedOption.getNum() + "";
    }

    public static class Option {
        private int id;
        private int num;

        /**
         * Create an option for a radio question.
         * @param id The ID.
         * @param num The numerical representation of this option when saved to the CSV file.
         */
        public Option(int id, int num) {
            this.id = id;
            this.num = num;
        }

        public int getId() {
            return id;
        }

        public int getNum() {
            return num;
        }
    }
}

