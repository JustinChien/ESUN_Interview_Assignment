package com.example.interview.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.interview.entity.User;
import com.example.interview.repository.DemoWebsiteUserJpaRepository;

public class TokenUtil {
	
	@Autowired
	private DemoWebsiteUserJpaRepository userRepo;
	
    public String generateToken(String phoneNumber) {
        // Dummy token generation logic (can be replaced with JWT token generation)
        return "dummy_token_for_" + phoneNumber;
    }
    
    public Long getUserIdFromToken(String token) {
        // For a dummy token, extract phone number from the token string
        // Here, we assume the token format is "dummy_token_for_{phoneNumber}"
        String[] parts = token.split("_");
        if (parts.length >= 4) {
            // Extract the phone number from the token
            String phoneNumber = parts[3];

            // Retrieve the user entity by phone number
            User user = userRepo.getUserbyPhone(phoneNumber);
            if (user != null) {
                // Return the user ID if the user entity is found
                return user.getUserId();
            }
        }
        // Return null if user ID cannot be extracted or user not found
        return null;
    }
}
