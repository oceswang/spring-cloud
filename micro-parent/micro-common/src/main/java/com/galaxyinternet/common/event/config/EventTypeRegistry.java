package com.galaxyinternet.common.event.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;

public class EventTypeRegistry implements InitializingBean
{

	private Set<String> eventTypes = new HashSet<>();

	public void regist(String eventType)
	{
		eventTypes.add(eventType);
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		//eventTypes.stream().forEach( type -> binderAwareChannelResolver.resolveDestination(type));
		
	}

	public Set<String> getEventTypes()
	{
		return eventTypes;
	}
	
	

}
