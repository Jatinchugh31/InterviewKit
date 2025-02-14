package src.DesignPattern.observer;

public class PhoneNotification {
    public void sendMessage(User  user,String message){
        System.out.println("Sending message to "+user.getPhone());
        System.out.println("Subject: "+message);
    }

}
