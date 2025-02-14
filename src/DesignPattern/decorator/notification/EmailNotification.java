package src.DesignPattern.decorator.notification;

public class EmailNotification extends NotificationDecorator{
    public EmailNotification(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Email Notification: " + message);
    }
}
