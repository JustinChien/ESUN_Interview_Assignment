package com.example.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.interview.entity.Comment;
import com.example.interview.entity.Post;
import com.example.interview.repository.DemoWebsiteCommentJpaRepository;
import com.example.interview.repository.DemoWebsitePostJpaRepository;

@Service
public class SocialMediaService {

	@Autowired
	private DemoWebsitePostJpaRepository postRepo;
	private DemoWebsiteCommentJpaRepository commentRepo;

	public void createPost(Post post) {
        if (post.getContent() == null || post.getContent().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        
        if (post.getUserId() == null) {
            throw new IllegalArgumentException("UserId not specified");
        }
        
        if (post.getImage() == null) {
        	postRepo.createPost(post.getUserId(), post.getContent());
        }
        else {
        	postRepo.createPost(post.getUserId(), post.getContent(), post.getImage());
        }
        
    }

    public List<Post> getAllPosts() {
        return postRepo.getAllPosts();
    }

    public void editPost(Long postId, Post post) {
        if (postId == null || post.getContent() == null || post.getContent().isEmpty()) {
            throw new IllegalArgumentException("Post ID, and content cannot be null or empty");
        }
                
        postRepo.updatePost(postId, post.getContent());
    }

    public void deletePost(Long postId) {
        if (postId == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }
        postRepo.deletePost(postId);
    }

	public void createComment(Long postId, Long userId, Comment comment) {
		
		if (postId == null) {
			throw new IllegalArgumentException("PostId is Null");
		}
		
		if (comment.getContent().isBlank() || comment.getContent().isEmpty()) {
			throw new IllegalArgumentException("Comment.content Can Not be Null or Empty");
		}
		
		commentRepo.createComment(postId, userId, comment.getContent());
	}
	
	
}
