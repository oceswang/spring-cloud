package com.galaxyinternet.common.event.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.galaxyinternet.common.event.entity.EventProcess;
import com.galaxyinternet.common.event.entity.EventProcessStatus;

public interface EventProcessDAO extends JpaRepository<EventProcess, Long> {
	
	List<EventProcess> findByStatus(EventProcessStatus status);
	
	EventProcess getById(Long id);
	
	@Modifying
	@Query("update EventProcess e set e.version = e.version+1 where e.version = ?1 and e.id = ?2")
	int updateWithLock(Integer version, Long id);

}
