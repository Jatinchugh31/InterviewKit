package src.companies.epam;

public class HelloPrint {
    public static void main(String[] args) {

        HelloWorld helloWorld = () -> System.out.println("Hello World");
        helloWorld.sayHello();
    }
}
