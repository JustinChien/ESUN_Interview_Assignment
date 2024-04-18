package com.example.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.interview.entity.User;

@Repository
public interface DemoWebsiteUserJpaRepository extends JpaRepository<User, Long> {
	
	@Procedure(name = "get_user_by_Phone")
	User getUserbyPhone(String phone);

	boolean existsByPhone(String phone);

	@Procedure(name = "create_user")
	void createNewUser(User user);
	
	@Procedure(name = "get_password_by_phone")
	String getPasswordByPhone(String phone);


}
