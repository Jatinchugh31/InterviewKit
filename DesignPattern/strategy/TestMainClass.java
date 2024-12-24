package DesignPattern.strategy;

import java.util.Date;
import java.util.Scanner;

public class TestMainClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first number");
        double num1 = sc.nextDouble();
        System.out.println("choose operation");
        System.out.println("+,-,*");
        String operation = sc.next();
        System.out.println("Enter second number");
        double num2 = sc.nextDouble();

       System.out.println(new MyCalculator().getCalculator(operation).calculate(num1, num2));
    }
}
