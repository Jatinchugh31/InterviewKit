package src.dataStructure.basic;

public class ClassB {
public static void main(String[] args) {
    ClassA a = new ClassA();
    a.setI(1);
    ClassA b = new ClassA();
    System.out.println(b.getI());
    ClassA c = new ClassA();
    c.setI(2);
    System.out.println(c.getI());
    System.out.println(a.getI());
}
}
