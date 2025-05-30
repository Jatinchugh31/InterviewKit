package src.theory.java.inheritence;

public class TestClass {

     public static void main(String[] args) {
         ClassA a = new ClassB();  //upcasting   upcasting is when child class is referenced to super class
         ClassB b = new ClassB();
         ClassB c = (ClassB) a;  // this is valid because  class  because down casting  It is the process of converting a superclass reference back to its subclass type.

         // ClassB c = (ClassB) new ClassA(); error becuase we can convert parent class to child class;

         a.print();  // this will call parent class as there is no overriding

         //a is holding reference of class b so it is daynmic dispatching
         a.print2();  // this will call child class or parent class ?? answer it will call child class but why ??

         //This is due to dynamic method dispatch (Java’s implementation of polymorphism).
         b.print2();  // this overriding  child class will print

     }

}
