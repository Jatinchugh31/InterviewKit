package src.DesignPattern.observer;

import java.util.List;
import java.util.Observer;

public interface ISubscriber {

    void addObserver(IStockObserver observer,String model);
    void removeObserver(IStockObserver observer, String model);
    void notifyObservers(String model);

}
