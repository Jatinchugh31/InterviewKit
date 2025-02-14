package src.HldLldMix.snakeAndLeader.game;

import java.util.HashMap;
import java.util.Map;

public class Board {
    int[] playingBoard;
    Map<Integer,Integer> snakes;
    Map<Integer,Integer> leaders;

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLeaders() {
        return leaders;
    }

    public Board() {
        playingBoard = new int[100];
        for (int i = 0; i < 100; i++) {
            playingBoard[i] = i+1;
        }
        addSnakesAndLeaders();
    }

    private void addSnakesAndLeaders() {
        snakes = new HashMap<Integer,Integer>();
        snakes.put(99,9);
        snakes.put(88,77);
        snakes.put(64,55);
        snakes.put(44,30);
        snakes.put(27,8);
        
        leaders = new HashMap<>();
        leaders.put(10,30);
        leaders.put(17,47);
        leaders.put(18,45);
        leaders.put(65,95);
        leaders.put(35,75);
    }

    public int[] getPlayingBoard() {
        return playingBoard;
    }

    public boolean isWinner(int currentPosition) {
      return  playingBoard.length >= currentPosition;

    }
}
