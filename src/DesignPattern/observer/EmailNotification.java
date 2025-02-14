package src.DesignPattern.observer;

public class EmailNotification {

    public void sendMessage(User  user,String message){
        System.out.println("Sending email to "+user.getEmail());
        System.out.println("Subject: "+message);
    }
}
