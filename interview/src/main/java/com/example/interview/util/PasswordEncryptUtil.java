package com.example.interview.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptUtil {
	private static final String HASHING_ALGORITHM = "SHA-256";
	
    private String salt="zkG1xGnvT0KWrKMBE+XZRw==";

	
    // Method to hash a password with the fixed salt
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        // Decode the salt from Base64
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        
        // Concatenate password and salt
        byte[] passwordWithSalt = new byte[password.length() + saltBytes.length];
        System.arraycopy(password.getBytes(), 0, passwordWithSalt, 0, password.length());
        System.arraycopy(saltBytes, 0, passwordWithSalt, password.length(), saltBytes.length);

        // Hash the concatenated password and salt
        MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
        byte[] hashedBytes = messageDigest.digest(passwordWithSalt);

        // Convert the hashed bytes to a base64-encoded string
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    // Method to verify a password against a hashed password
    public boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        // Hash the input password with the fixed salt
        String hashedInputPassword = hashPassword(password);
        // Compare the hashed input password with the provided hashed password
        return hashedInputPassword.equals(hashedPassword);
    }
    
    public static void main(String[] args) {
        // Example usage
        String password = "MangoPP1213";
        PasswordEncryptUtil passwdUtil = new PasswordEncryptUtil();

        try {
            String hashedPassword = passwdUtil.hashPassword(password);
            System.out.println("Hashed Password: " + hashedPassword);
            System.out.println("Salt: " + passwdUtil.salt); 
            
            // Verify a password against the hashed password
            boolean isMatch = passwdUtil.verifyPassword(password, hashedPassword);
            System.out.println("Password Match: " + isMatch);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


