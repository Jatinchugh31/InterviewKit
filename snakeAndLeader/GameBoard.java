package snakeAndLeader;

import snakeAndLeader.game.Board;
import snakeAndLeader.game.Dice;
import snakeAndLeader.game.Player;

import java.util.*;

public class GameBoard {


    public static void main(String[] args) {

        Deque<Player> players = new LinkedList<>();
        Board playingBoard = new Board();
        Player winner = null;


        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of players");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the name of the player");
            String name = sc.next();
            players.add(new Player(name, 0));
        }

        System.out.println("Lets Start");
        System.out.println("player who reach to"+ playingBoard.getPlayingBoard().length +" will qinw");
        while (winner == null) {

            Player currentPlayer = players.removeFirst();
            System.out.println(currentPlayer.getName() + "'s turn rolling dice");
            int rollingDice = Dice.roleDice();
            System.out.println("yeeh you get" + rollingDice);
            sc.next();
            int currentPosition = currentPlayer.getCurrentPosition() + rollingDice;
            currentPlayer.setCurrentPosition(currentPosition);
            if (playingBoard.getSnakes().containsKey(currentPosition)) {
                int currentSnakePosition = playingBoard.getSnakes().get(currentPosition);
                System.out.println("oohhh you found the snake and going to " + currentSnakePosition);
                currentPlayer.setCurrentPosition(currentSnakePosition);
            }

            if (playingBoard.getLeaders().containsKey(currentPosition)) {
                int currentLeaderPosition = playingBoard.getLeaders().get(currentPosition);
                System.out.println("waooahhh you found the leader and jumping to " + currentLeaderPosition);
                currentPlayer.setCurrentPosition(currentLeaderPosition);
            }

            if (playingBoard.isWinner(currentPosition)) {
                winner = currentPlayer;
            } else {
                players.addLast(currentPlayer);
            }
            System.out.println(currentPlayer.getName() + "you reached at " +currentPlayer.getCurrentPosition());

        }
        System.out.println("winner is " + winner.getName());

    }

}
