package com.example.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.interview.entity.Post;

public interface DemoWebsitePostJpaRepository extends JpaRepository<Post, Long>{

	
	@Procedure("create_post")
    void createPost(Long userId, String content);
    
    @Procedure("create_post")
    void createPost(Long userId, String content, byte[] image);
	
	@Procedure("get_all_posts")
    List<Post> getAllPosts();
	
	@Procedure("update_post")
    void updatePost(Long postId, String content);
	
	@Procedure("delete_post")
    void deletePost(Long postId);
}
