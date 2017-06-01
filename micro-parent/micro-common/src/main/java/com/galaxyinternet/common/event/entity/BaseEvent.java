package com.galaxyinternet.common.event.entity;

public class BaseEvent
{
	protected EventType eventType;
	protected String eventId;

	public EventType getEventType()
	{
		return eventType;
	}

	public void setEventType(EventType eventType)
	{
		this.eventType = eventType;
	}

	public String getEventId()
	{
		return eventId;
	}

	public void setEventId(String eventId)
	{
		this.eventId = eventId;
	}
	
	

}
