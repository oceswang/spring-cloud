package com.galaxyinternet.user.core.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.common.event.service.EventService;
import com.galaxyinternet.user.api.UserDTO;
import com.galaxyinternet.user.api.event.UserCreatedEvent;
import com.galaxyinternet.user.core.dao.UserDAO;
import com.galaxyinternet.user.core.entity.User;

@Service
public class UserService 
{
	@Autowired
	UserDAO dao;
	@Autowired
	EventService eventService;
	@Transactional(readOnly=true)
	public User getById(Long id)
	{
		return dao.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Iterable<User> findAll()
	{
		return dao.findAll();
	}
	
	@Transactional
	public User save(User user)
	{
		return dao.save(user);
	}
	@Transactional
	public User registr(UserDTO dto)
	{
		User vo = new User();
		vo.setLoginName(dto.getLoginName());
		vo.setCreatedTime(LocalDateTime.now());
		User po = dao.save(vo);
		UserCreatedEvent event = new UserCreatedEvent(po.getId(),po.getCreatedTime());
		eventService.publish(event);
		return po;
	}

}
