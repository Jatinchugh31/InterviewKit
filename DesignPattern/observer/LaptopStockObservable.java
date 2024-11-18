package DesignPattern.observer;

import java.util.*;

public class LaptopStockObservable implements ISubscriber{

    Map<String, List<IStockObserver>> observers = new HashMap<>();
    Map<String,Integer> stocks = new HashMap<>();


    public void updatePhoneStock(String model, int stock){
        stocks.put(model,stock);
        if(stocks.getOrDefault(model,0) >0){
            notifyObservers(model);
        }
    }



    @Override
    public void addObserver(IStockObserver observer,String model) {
        List<IStockObserver> list = observers.getOrDefault(model,new ArrayList<>());
        list.add(observer);
        observers.put(model,list);
    }

    @Override
    public void removeObserver(IStockObserver observer,String model) {
        List<IStockObserver> list = observers.getOrDefault(model,new ArrayList<>());
        list.remove(observer);
        observers.put(model,list);
    }



    @Override
    public void notifyObservers(String model) {
        List<IStockObserver> list = observers.getOrDefault(model,new ArrayList<>());
        for(IStockObserver observer:list){
            observer.update(model);
        }
    }

}
