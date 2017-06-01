package com.galaxyinternet.common.event.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxyinternet.common.event.entity.EventProcess;
import com.galaxyinternet.common.event.entity.EventProcessStatus;

public interface EventProcessDAO extends JpaRepository<EventProcess, Long> {
	
	List<EventProcess> findByStatus(EventProcessStatus status);
	
	EventProcess getById(Long id);

}