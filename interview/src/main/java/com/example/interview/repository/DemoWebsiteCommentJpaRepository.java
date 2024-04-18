package com.example.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.interview.entity.Comment;

@Repository
public interface DemoWebsiteCommentJpaRepository extends JpaRepository<Comment, Long>{
	
	@Procedure("create_comment")
    void createComment(Long postId, Long userId, String content);
}
