package src.HldLldMix.TicTocToeGame;


public class User {
    String name;
    Sign sign;

    public User(String name, Sign sign) {
        this.name = name;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }


    public Sign getSign() {
        return sign;
    }


}
