package src.DesignPattern.observer;

import java.util.TreeMap;

public class ObserverMailClass {


    public static void main(String[] args) throws InterruptedException {
        User user = new User("ABC",30,"abc@mailsac.com",123445667);
        StockObserver stockObserver1 = new StockObserver(user,false,true);

        User user2 = new User("ABC2",30,"abc2@mailsac.com",9999);
        StockObserver stockObserver12 = new StockObserver(user2,true,false);

        LaptopStockObservable laptopStockObservable = new LaptopStockObservable();
        laptopStockObservable.addObserver(stockObserver12,"MACOS");


        PhoneStockObservable phoneStockObservable = new PhoneStockObservable();
        phoneStockObservable.addObserver(stockObserver1,"iphone16");
        phoneStockObservable.addObserver(stockObserver12,"iPhone17");

        phoneStockObservable.updatePhoneStock("iphone16",10);
        Thread.sleep(1000);
        phoneStockObservable.updatePhoneStock("iPhone17",17);
        laptopStockObservable.updatePhoneStock("MACOS",17);


    }
}
