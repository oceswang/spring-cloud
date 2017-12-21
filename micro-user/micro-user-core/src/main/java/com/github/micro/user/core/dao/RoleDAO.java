package com.github.micro.user.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.micro.user.core.entity.Role;

public interface RoleDAO extends JpaRepository<Role , Long> {

}
