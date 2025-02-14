package src.DesignPattern.decorator.notification;

public class SMSNotification  extends NotificationDecorator{
    public SMSNotification(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS Notification: " + message);
    }
}
