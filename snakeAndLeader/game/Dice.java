package snakeAndLeader.game;

import java.util.Random;

public class Dice {

    public static int roleDice(){
        Random rand = new Random();
        return rand.nextInt(1,7);
    }
}
