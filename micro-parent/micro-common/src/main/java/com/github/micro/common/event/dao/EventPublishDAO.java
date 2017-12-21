package com.github.micro.common.event.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.github.micro.common.event.entity.EventPublish;
import com.github.micro.common.event.entity.EventPublishStatus;

public interface EventPublishDAO extends JpaRepository<EventPublish, Long> {
	
	List<EventPublish> findByStatus(EventPublishStatus status);
	
	EventPublish getById(Long id);
	
	EventPublish getByEventId(String eventId);
	@Modifying
	@Query("update EventPublish e set e.version = e.version+1 where e.version = ?1 and e.id = ?2")
	int updateWithLock(Integer version, Long id);
	
}
