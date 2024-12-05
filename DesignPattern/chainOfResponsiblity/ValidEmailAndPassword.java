package DesignPattern.chainOfResponsiblity;

public class ValidEmailAndPassword extends Middleware{
    //DBInstance db

    @Override
    public boolean check(String email, String password) {
        if(!checkInDb(email,password)){
            return false;
        }
        return checkNext(email,password);
    }

    private boolean checkInDb(String email, String password) {
      // check is user name and password as per DB
        return true;
    }


}
