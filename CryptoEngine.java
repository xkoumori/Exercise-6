/*
 * @(#)Exercise 6
 * @author Sammie Ortiz
 * @version 1.0 2020/10/25
 * Program Purpose: To learn more about input and output, via files specified on the command line or in
 * the program itself.
 * Resources for the questions: https://www.cs.rice.edu/~javaplt/drjava/docs/user/ch05.html
 * https://www.w3schools.com/java/java_packages.asp
 * https://docs.oracle.com/javase/7/docs/api/java/nio/file/package-summary.html
 * https://docs.oracle.com/javase/7/docs/api/java/io/FileNotFoundException.html
 * https://docs.oracle.com/javase/7/docs/api/java/security/InvalidKeyException.html
 */
// Why are these packages needed in this program?
 
//This makes us use all of the classes and their methods in our program.
import java.util.*; 
//This makes us import the File class that is used to alter anything in the file.
import java.nio.file.Files;
//This creates a Path to represent the instance for a file or directory.
import java.nio.file.Paths;
//This package includes all of the cryptography classes that are being used in this program.
import javax.crypto.*;
//An Exception to open the file denoted by a specified pathname that has failed.
import java.io.FileNotFoundException;
//An Exception arises when the input is not present or invalidInput.
import java.io.IOException;
//This is the exception for invalid Keys (invalid encoding, wrong length, uninitialized, etc).
import java.security.InvalidKeyException;
//This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
import java.security.NoSuchAlgorithmException;

public class CryptoEngine {
  // What is the function of these four variables?

  //Provides the functionality of a secret (symmetric) key generator.
  KeyGenerator keyGen;
  //Strings file names that are being encrypted in encFile and decrypted in decFile.
  String encFile, decFile;
  //This groups and keeps secKeys safe in an interface.
  SecretKey secKey;
  //This performs the encryption and decryption of data in a security key.
  Cipher aesCipher; 
 
 CryptoEngine(String inFile, String outFile) throws NoSuchAlgorithmException, NoSuchPaddingException {
    encFile = inFile;
    decFile = outFile;
   
    keyGen = KeyGenerator.getInstance("AES");
           keyGen.init(128);
           secKey = keyGen.generateKey();
           aesCipher = Cipher.getInstance("AES");
 }
 
 public void encrypt() throws InvalidKeyException, IOException {
           byte[] byteText = "This is for Java 2, and I am testing to see if this works!".getBytes();
        
           aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
 
           byte[] byteCipherText = null;
    
           try {
        byteCipherText = aesCipher.doFinal(byteText);
         } catch (IllegalBlockSizeException | BadPaddingException e) {
        e.printStackTrace();
           }
    Files.write(Paths.get(encFile), byteCipherText);
 }
 
        // You will decrypt the encrypted file using the same general principles
        // For each line that you supply, leave a detailed comment of what that line is doing, including the functionality of the methods and variables 
 public void decrypt() throws IOException, InvalidKeyException {
           // assign "Files.readAllBytes(Paths.get(encFile))" to the cipherText array (fill in the blank) 
           byte[] cipherText = Files.readAllBytes(Paths.get(encFile));
   
           // call aesCipher.init as in the encrypt method, but this time, you will use DECRYPT_MODE!  
           aesCipher.init(Cipher.DECRYPT_MODE, secKey);
   
           // declare and initialize a byte array just like in the encrypt method, but this time, call it bytePlainText
           byte[] bytePlainText = null;        

           // I have commented out this try-catch block to make your code compilable, but you'll need to decomment it after filling in the code
           try {
                // Decrypt the cipherText byte array with the same aesCipher.doFinal method as in encrypt method
                // But this time, the byte arrays will be reversed!
                bytePlainText = aesCipher.doFinal(cipherText);
  
           } catch (IllegalBlockSizeException | BadPaddingException e) {
             e.printStackTrace();
           }
           Files.write(Paths.get(decFile), bytePlainText);
        }
 
}