package com.frc107.scouting2019.model.question;

public class TextQuestion extends Question<String> {
    private String answer;

    /**
     * Create a question with a text answer.
     * @param name The name of the question. At this point, it's only used for debugging.
     * @param id The id of the question to differentiate it.
     * @param needsAnswer Does it need an answer?
     */
    public TextQuestion(String name, int id, boolean needsAnswer) {
        super(name, id, needsAnswer);
        this.answer = "";
    }

    @Override
    public boolean hasAnswer() {
        return answer.length() > 0;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public String getAnswerAsString() {
        return answer;
    }
}
