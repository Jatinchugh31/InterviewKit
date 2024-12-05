package DesignPattern.chainOfResponsiblity;

import java.util.Scanner;

public class ClientClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of your person");
        String name = sc.nextLine();
        System.out.println("Enter the  password");
        String password = sc.nextLine();

        Middleware middleware = Middleware.link(new EmptyCheckHandler(),
                new ValidEmailAndPassword(),
                new CheckUserAccess());

        boolean res = middleware.check(name, password);
        System.out.println(res);
    }

}
