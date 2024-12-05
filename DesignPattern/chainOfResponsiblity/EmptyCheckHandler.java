package DesignPattern.chainOfResponsiblity;

public class EmptyCheckHandler extends Middleware{

    @Override
    public boolean check(String email, String password) {
        if(email == null || email.isEmpty() || password == null || password.isEmpty())
        {
            return false;
        }
        return checkNext(email, password);
    }
}
