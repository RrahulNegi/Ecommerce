package com.ecom.user.repositroies;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.user.bean.User;

public interface UserRepositroies extends JpaRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);

}
