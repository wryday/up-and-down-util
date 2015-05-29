package com.wryday.upanddownscoring;

import java.util.List;

public class Round {

    private List<Player> mPlayers;
    private int mUserCount;

    public Round(List<Player> players) {
        mPlayers = players;
        mUserCount = players.size();
    }

    public String getState() {

        String state = "";

        for (Player player : mPlayers) {
            state += player.getName() + ": " + player.getScore() + "\t";
        }

        return state;
    }

}
