package com.github.micro.user.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.micro.user.core.entity.User;

public interface UserDAO extends JpaRepository<User, Long> {

}
