package src.theory.java.searlisation;

import java.io.Serializable;

public class Student implements Serializable {
//public class Student  {
     public String name;
     public int age;
     public Student(String name) {
         this.name = name;
     }

}
