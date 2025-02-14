package src.HldLldMix.TicTocToeGame;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class PlayBoard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sign[][] boards = new Sign[3][3];
        fillArray(boards);
        List<User> users = new LinkedList<>();
        User user1 = new User("Jatin", Sign.CIRCLE);
        users.add(user1);
        User user2 = new User("Pardeep", Sign.CROSS);
        users.add(user2);
        Result currentResult = Result.CONTINUE;
        User currentUser = null;
        do {
            currentUser = users.removeFirst();
            System.out.println(currentUser.getName() + "your turn");
            printBoard(boards);

            System.out.println("choose your location");
            System.out.print("Enter row (0-2): ");
            int i = sc.nextInt();
            System.out.print("Enter column (0-2): ");
            int j = sc.nextInt();
            if (i > boards.length - 1 || j > boards[0].length - 1 || i < 0 || j < 0 || boards[i][j] != Sign.EMPTY) {
                System.out.println("You can't place at this box");
                users.addFirst(currentUser);
                continue;
            }

            boards[i][j] = currentUser.getSign();
            currentResult = ResultHelper.claculateResult(boards, i, j, currentUser.sign);
            users.addLast(currentUser);
        } while (currentResult == Result.CONTINUE);
        System.out.println("game over");
        if (currentResult == Result.WIN) {
            System.out.println(currentUser.getName() + " is winner");
        } else {
            System.out.println("game draw");
        }
    }

    private static void fillArray(Sign[][] boards) {

        for (int i = 0; i < boards.length; i++) {
            Arrays.fill(boards[i], Sign.EMPTY);
        }
    }

    private static void printBoard(Sign[][] boards) {


        for (int i = 0; i < boards.length; i++) {
            System.out.println(getSymbol(boards[i][0]) + " | " + getSymbol(boards[i][1]) + " | " + getSymbol(boards[i][2]));
            if (i < 2) {
                System.out.println("--------------");
            }
        }

    }

    private static String getSymbol(Sign sign) {
        if (sign == Sign.CIRCLE) {
            return "O";
        } else if (sign == Sign.CROSS) {
            return "X";
        } else {
            return " ";
        }
    }

}
