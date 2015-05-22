package com.wryday.upanddownscoring;

public class Player {

    private String mName;
    private int mScore;
    private int mHighScore;

    public Player(String name, int score, int highScore) {
        mName = name;
        mScore = score;
        mHighScore = highScore;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public int getHighScore() {
        return mHighScore;
    }

    public void setHighScore(int highScore) {
        mHighScore = highScore;
    }
}
