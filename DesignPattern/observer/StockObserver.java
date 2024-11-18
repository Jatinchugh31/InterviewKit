package DesignPattern.observer;

public class StockObserver implements IStockObserver {

    private User user;
    private boolean notifyObserverUsingEmail;
    private boolean notifyObserverUsingMobile;

    public StockObserver(User user, boolean notifyObserverUsingEmail, boolean notifyObserverUsingMobile) {
        this.user = user;
        this.notifyObserverUsingEmail = notifyObserverUsingEmail;
        this.notifyObserverUsingMobile = notifyObserverUsingMobile;
    }

    @Override
    public void update(String model) {
        if (notifyObserverUsingEmail) {
            EmailNotification emailNotification = new EmailNotification();
            emailNotification.sendMessage(user, model + "is in stock");
        }
        if (notifyObserverUsingMobile) {
            PhoneNotification phoneNotification = new PhoneNotification();
            phoneNotification.sendMessage(user, model + "is in stock");
        }
    }
}
