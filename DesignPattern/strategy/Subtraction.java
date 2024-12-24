package DesignPattern.strategy;

public class Subtraction implements Calculator {


    @Override
    public double calculate(double a, double b) {
        return a -b;
    }
}
