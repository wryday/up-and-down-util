package com.wryday.upanddownscoring;

public class Constant {

    public static int getCardsDealtByRound(int roundCount, int round) {
        switch (roundCount) {
            case 13:
                return sevenCardRounds[round];
            case 11:
                return fiveCardRounds[round];
            default:
                return 0;
        }
    }

    private static int[] fiveCardRounds = {5, 4, 3, 2, 1, 2, 3, 4, 5};
    private static int[] sevenCardRounds = {7, 6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7};
}
