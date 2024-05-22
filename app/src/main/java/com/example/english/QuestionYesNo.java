package com.example.english;

public class QuestionYesNo {
    private final String text;
    private final boolean isCorrect;

    public QuestionYesNo(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}