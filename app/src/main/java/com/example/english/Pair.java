package com.example.english;
public class Pair {
    private String word;
    private String correctTranslation;
    private String userTranslation;
    private String[] possibleTranslations;

    public Pair(String word, String correctTranslation, String[] possibleTranslations) {
        this.word = word;
        this.correctTranslation = correctTranslation;
        this.possibleTranslations = possibleTranslations;
    }

    public String getWord() {
        return word;
    }

    public String getCorrectTranslation() {
        return correctTranslation;
    }

    public String getUserTranslation() {
        return userTranslation;
    }

    public void setUserTranslation(String userTranslation) {
        this.userTranslation = userTranslation;
    }

    public String[] getPossibleTranslations() {
        return possibleTranslations;
    }

    public boolean isCorrect() {
        return correctTranslation.equalsIgnoreCase(userTranslation);
    }
}