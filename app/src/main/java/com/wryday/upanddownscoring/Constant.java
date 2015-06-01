package com.wryday.upanddownscoring;

public class Constant {

    public static int getCardsDealtByRound(int roundCount, int round) {
        switch (roundCount) {
            case 13:
                return sevenCardRounds[round];
            case 9:
                return fiveCardRounds[round];
            default:
                return 0;
        }
    }

    private static int[] fiveCardRounds = {5, 4, 3, 2, 1, 2, 3, 4, 5};
    private static int[] sevenCardRounds = {7, 6, 5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 7};

    public static final int FIVE_CARD_GAME_START_COUNT = 5;
    public static final int SEVEN_CARD_GAME_START_COUNT = 7;

    public static final String GAME_TYPE = "game_type";
    public static final String GAME_TYPE_5_CARD = "game_type_5_card";
    public static final String GAME_TYPE_7_CARD = "game_type_7_card";

    public static final String PLAYER_NAMES = "player_names";

}
