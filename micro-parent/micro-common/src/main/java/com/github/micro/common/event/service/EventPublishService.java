package com.github.micro.common.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.micro.common.event.dao.EventPublishDAO;
import com.github.micro.common.event.entity.EventPublish;
import com.github.micro.common.event.entity.EventPublishStatus;

@Service
public class EventPublishService {
	@Autowired
	EventPublishDAO dao;
	
	@Transactional
	public List<EventPublish> findUnpublishedEvent()
	{
		return dao.findByStatus(EventPublishStatus.NEW);
	}
	
	@Transactional
	public EventPublish save(EventPublish entity)
	{
		return dao.save(entity);
	}
	@Transactional
	public int updateWithLock(Integer version, Long id)
	{
		return dao.updateWithLock(version, id);
	}
	

}
