package DesignPattern.strategy;

public class Addition implements Calculator{
    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
