package DesignPattern.strategy;

public class MyCalculator {

    public Calculator calculator;
    public  Calculator getCalculator(String operation){

        switch(operation){
            case "+" -> calculator =new Addition();
            case "-" ->  calculator= new Subtraction();
            case "*" -> calculator = new  MultiPlication();
            default -> System.out.println("Invalid operation");
        }
        return calculator;
    }
}
