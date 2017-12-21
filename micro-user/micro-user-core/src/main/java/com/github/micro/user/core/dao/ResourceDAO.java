package com.github.micro.user.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.micro.user.core.entity.Resource;

public interface ResourceDAO extends JpaRepository<Resource, Long> {

}
