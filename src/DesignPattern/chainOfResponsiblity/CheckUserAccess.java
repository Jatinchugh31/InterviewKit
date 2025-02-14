package src.DesignPattern.chainOfResponsiblity;

public class CheckUserAccess extends Middleware{
    //DBInstance

//    CheckUserAccess(Server server){
//        this.server = server;
//    }

    @Override
    public boolean check(String email, String password) {
        if(!checkInDB(email,password)){
            return false;
        }
        return true;
    }

    private boolean checkInDB(String email, String password) {
      return true;
    }
}
