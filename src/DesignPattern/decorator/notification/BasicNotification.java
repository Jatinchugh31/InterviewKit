package src.DesignPattern.decorator.notification;

public  class BasicNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Basic notification : "+ message);
    }
}
