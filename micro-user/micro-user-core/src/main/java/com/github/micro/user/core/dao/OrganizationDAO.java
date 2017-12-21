package com.github.micro.user.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.micro.user.core.entity.Organization;

public interface OrganizationDAO extends JpaRepository<Organization, Long> {

}
