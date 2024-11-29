package DesignPattern.decorator.logger;

import java.util.Scanner;

public class Sample {
 public static void main(String[] args) {
     System.out.println("Hello World");
     Logger consoleLogger = new ConsoleLogger();
     System.out.println(" do you want to append time in logs");
     Scanner scanner = new Scanner(System.in);
     boolean time = scanner.nextBoolean();
     System.out.println("Enter Log Level");
     String logLevel =  scanner.next();
     if(time){
       Logger timeStampLogger = new TimeStampLoggerDecorator(consoleLogger);
       Logger finalLogger  = new LogLevelDecorator(timeStampLogger, logLevel);
         finalLogger.log("here is your log with time");

     }else {
         Logger finalLogger = new LogLevelDecorator(consoleLogger, logLevel);
         finalLogger.log("here is your log");
     }



 }

}
