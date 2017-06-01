package com.galaxyinternet.api.event.entity;

import com.galaxyinternet.api.event.constants.EventType;

public abstract class BaseEvent
{
	protected String eventId;

	public abstract EventType getEventType();

	public String getEventId()
	{
		return eventId;
	}

	public void setEventId(String eventId)
	{
		this.eventId = eventId;
	}
	
	

}
