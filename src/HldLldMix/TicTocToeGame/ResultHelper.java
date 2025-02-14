package src.HldLldMix.TicTocToeGame;

import src.HldLldMix.snakeAndLeader.game.Board;

public class ResultHelper {

    public static Result claculateResult(Sign[][] boards, int i, int j, Sign sign) {
        if (anyMatch(boards, sign)) {
            return Result.WIN;
        } else if (allFull(boards)) {
            return Result.DRAW;
        } else {
            return Result.CONTINUE;
        }
    }

    private static boolean allFull(Sign[][] boards) {
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                if (boards[i][j] == Sign.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean anyMatch(Sign[][] boards, Sign sign) {

        for (int i = 0; i < boards.length; i++) {
            if (sign == boards[i][0] && sign == boards[i][1] && sign == boards[i][2]) {
                return true;
            }
            if (sign == boards[0][i] && sign == boards[1][i] && sign == boards[2][i]) {
                return true;
            }
        }
        if (sign == boards[0][0] && sign == boards[1][1] && sign == boards[2][2]) {
            return true;
        }
        if (sign == boards[0][2] && sign == boards[1][1] && sign == boards[2][0]) {
            return true;
        }
        return false;


    }
}


