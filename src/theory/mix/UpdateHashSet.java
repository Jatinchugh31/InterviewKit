package src.theory.mix;


import java.util.HashSet;
import java.util.Set;

class Student {

    public int id;

    public String name;

    public Student(String name, int id) {

        this.name = name;

        this.id = id;

    }

    public int hashCode() {

        return this.id;

    }

    public String toString() {

        return "Student: " + this.name + "@" + Integer.toHexString(hashCode());

    }

    public boolean equals(Object o) {

        if (o instanceof Student) {

            Student s = (Student) o;

            return s.id == this.id ? true : false;

        }

        return false;

    }

}

public class UpdateHashSet {

    public static void main(String[] args) {

        Set<Student> studentList = new HashSet<>();

        Student st1 = new Student("Nimit", 1);

        Student st2 = new Student("Rahul", 3);

        Student st3 = new Student("Nimit", 2);

        studentList.add(st1);

        studentList.add(st2);

        studentList.add(st3);

        System.out.println(studentList.size());

        st1.id = 3;

        System.out.println(studentList.size());
         //as the student class is not immutatble class it is not a good practice to make a class as a key.
        studentList.add(st1);
        System.out.println(studentList.size());
        studentList.remove(st1);
        System.out.println(studentList.size());

    }

}
