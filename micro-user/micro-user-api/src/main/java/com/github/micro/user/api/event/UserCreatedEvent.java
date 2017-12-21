package com.github.micro.user.api.event;

import java.time.LocalDateTime;

import com.github.micro.api.event.constants.EventType;
import com.github.micro.api.event.entity.BaseEvent;



public class UserCreatedEvent extends BaseEvent
{
	public static final EventType EVENT_TYPE = EventType.USER_CREATED;
	private Long userId;
	private LocalDateTime createdTime;
	public UserCreatedEvent(){}
	public UserCreatedEvent(Long userId, LocalDateTime createdTime)
	{
		this.userId = userId;
		this.createdTime = createdTime;
	}
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public LocalDateTime getCreatedTime()
	{
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime)
	{
		this.createdTime = createdTime;
	}
	@Override
	public EventType getEventType()
	{
		return EVENT_TYPE;
	}
	
	
	
}
