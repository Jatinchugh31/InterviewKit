package DesignPattern.strategy;

public class MultiPlication implements Calculator {
    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}

