package com.galaxyinternet.user.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxyinternet.user.core.entity.Role;

public interface RoleDAO extends JpaRepository<Role , Long> {

}
