package com.example.interview.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.interview.entity.User;
import com.example.interview.model.UserRegistrationRequest;
import com.example.interview.repository.DemoWebsiteUserJpaRepository;
import com.example.interview.util.PasswordEncryptUtil;
import com.example.interview.util.TokenUtil;
import com.example.interview.util.ValidateUtil;

@Service
public class UserService {
	
	@Autowired
	private DemoWebsiteUserJpaRepository userRepo ;
	private PasswordEncryptUtil pwdUtil;
	private ValidateUtil validateUtil;
	private TokenUtil tokenUtil;

	public void registerUser(UserRegistrationRequest request) {
        // Check if the user already exists
        if (userRepo.existsByPhone(request.getPhone())) {
            throw new RuntimeException("User already exists");
        }

        //Check if phone valid
        if (!validateUtil.isValidPhoneNumber(request.getPhone())) {
        	throw new RuntimeException("Phone not Valid");
        }
        
        // Check if email valid
        if (!validateUtil.isValidEmail(request.getEmail())) {
        	throw new RuntimeException("Email not Valid.");
        }
        
        // Check if username valid
        if (!validateUtil.isValidEmail(request.getUname())) {
        	throw new RuntimeException("Username not Valid.");
        }
        
        // Create a new user entity and save it to the database
        User user = new User(
        		request.getPhone(),
        		request.getUname(), 
        		request.getEmail(), 
        		request.getPassword(), 
        		null, 
        		request.getBiography()
        		);

        userRepo.createNewUser(user);
    }
	
	
	
	
	public String loginUser(String phone, String password) {
	    // Find the user in the database by phone number
	    User user = userRepo.getUserbyPhone(phone);
	    
	    // Check if the user exists
	    if (user == null) {
	        throw new RuntimeException("User not found");
	    }
	    
	    // Retrieve the hashed password stored in the database
	    String hashedPasswordFromDb = user.getPassword();
	    
	    // Hash the provided password using the same algorithm and salt
	    String hashedPasswordProvided;
	    try {
	        hashedPasswordProvided = pwdUtil.hashPassword(password);
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Error hashing password");
	    }
	    
	    // Compare the hashed password provided by the user with the hashed password from the database
	    if (!hashedPasswordProvided.equals(hashedPasswordFromDb)) {
	        throw new RuntimeException("Invalid credentials");
	    }
	    
	    // Generate and return a dummy authentication token (for demonstration purposes)
	    return tokenUtil.generateToken(phone);
    }


}
