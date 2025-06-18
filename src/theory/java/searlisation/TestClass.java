package src.theory.java.searlisation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestClass {
   public static void main(String[] args) {
       Student s = new Student("Jatin");
       try{
//           FileOutputStream fileOut = new FileOutputStream("Student.ser");
//           ObjectOutputStream out = new ObjectOutputStream(fileOut);
//           out.writeObject(s); // if you will not searlise the object it will fail whe you try to write
//           System.out.println("Object written to file");
//           out.close();
           FileInputStream fileIn = new FileInputStream("Student.ser");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           s = (Student) in.readObject();
           System.out.println("Object read from file"+s);

       }catch(Exception e){
           e.printStackTrace();
       }

   }
}
