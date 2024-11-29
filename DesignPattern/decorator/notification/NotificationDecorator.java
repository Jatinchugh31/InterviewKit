package DesignPattern.decorator.notification;

public abstract class NotificationDecorator extends BasicNotification {

    protected Notification notification;
    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }
    @Override
    public void send(String message) {
        notification.send(message); // Delegate to the base notification
    }
}
