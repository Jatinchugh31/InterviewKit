package src.dataStructure;

public class TestClass {
public static void main(String[] args) {
    E  test = E.valueOf("A");

    if(E.A == test){
        System.out.println(" == test passed");
    }else if(E.A.equals(test)){
        System.out.println(" .equsls pass test passed");
    }else {
        System.out.println("Test failed");
    }
}
}
