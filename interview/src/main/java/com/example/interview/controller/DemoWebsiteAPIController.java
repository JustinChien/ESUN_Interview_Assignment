package com.example.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.interview.entity.Comment;
import com.example.interview.entity.Post;
import com.example.interview.model.UserLoginRequest;
import com.example.interview.model.UserRegistrationRequest;
import com.example.interview.service.SocialMediaService;
import com.example.interview.service.UserService;
import com.example.interview.util.TokenUtil;
import com.example.interview.vo.ApiResponse;
import com.example.interview.vo.LoginResponse;

@RestController
public class DemoWebsiteAPIController {

	@Autowired
	private UserService userService;
	private SocialMediaService socialMediaService;
	private TokenUtil tokenUtil;
	
	// FOR USERS
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
		userService.registerUser(request);
        return ResponseEntity.ok(new ApiResponse("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {
        String token = userService.loginUser(request.getPhone(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse("Login successful", token));
    }
    
    
    
    
    // FOR POSTS
    // Create Post
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        socialMediaService.createPost(post);
        return ResponseEntity.ok(new ApiResponse("Post created successfully"));
    }

    // Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = socialMediaService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Edit Post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody Post post) {
        socialMediaService.editPost(postId, post);
        return ResponseEntity.ok(new ApiResponse("Post edited successfully"));
    }

    // Delete Post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {    	
        socialMediaService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse("Post deleted successfully"));
    }

    
    
    
    // FOR COMMENTS
    // Create Comment
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Long postId, @RequestBody Comment comment,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        // Extract user ID from the token
        Long userId = tokenUtil.getUserIdFromToken(token);

        // Check if the user ID is valid
        if (userId == null) {
            // Handle invalid token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Invalid token"));
        }

        // Pass postId, userId, and comment to the service to create the comment
        socialMediaService.createComment(postId, userId, comment);
        
        // Return success response
        return ResponseEntity.ok(new ApiResponse("Comment created successfully"));
    }
}
