package com.galaxyinternet.user.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxyinternet.user.core.entity.Resource;

public interface ResourceDAO extends JpaRepository<Resource, Long> {

}
