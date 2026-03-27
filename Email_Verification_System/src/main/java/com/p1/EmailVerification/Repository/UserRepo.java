package com.p1.EmailVerification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p1.EmailVerification.Entity.User;

public interface UserRepo extends JpaRepository<User,Long> {

User findByEmail(String email);

}
