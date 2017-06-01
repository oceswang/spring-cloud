package com.galaxyinternet.common.event.entity;

import java.time.LocalDateTime;

public class UserCreatedEvent extends BaseEvent
{
	private Long userId;
	private LocalDateTime createdTime;
	public UserCreatedEvent(Long userId, LocalDateTime createdTime)
	{
		this.userId = userId;
		this.createdTime = createdTime;
		this.eventType = EventType.USER_CREATED;
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
	
	
}
