package DesignPattern.decorator.notification;

public class NotificationSystem {
  public static void main(String[] args) {
    Notification basicNotification = new BasicNotification();

    // Add email functionality
    Notification emailNotification = new EmailNotification(basicNotification);

    // Add SMS and push functionalities dynamically
    Notification fullNotification = new PushNotification(new SMSNotification(emailNotification));

    // Send notification with all added features
        fullNotification.send("This is a critical alert!");
  }
}
