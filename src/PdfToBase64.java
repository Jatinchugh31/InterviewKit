package src;

import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PdfToBase64 {
    public static void main(String[] args) {
        // Path to the PDF file
        String filePath = "C:\\Users\\user\\Downloads/agreement (2).pdf";
        
        try {
            // Read the PDF file into a byte array
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            
            // Convert the byte array into a Base64 encoded string
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);
            byte[] hashBytes = MessageDigest.getInstance("SHA-1").digest(fileBytes);

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            // Print the Base64 encoded string
            System.out.println(fileBytes.length);
            System.out.println(hexString);
            System.out.println("Base64 Encoded String: ");
            System.out.println(base64Encoded);
        } catch (IOException e) {
            // Handle exception
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
