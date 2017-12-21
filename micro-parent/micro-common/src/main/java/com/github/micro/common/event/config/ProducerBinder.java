package com.github.micro.common.event.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;

public class ProducerBinder implements InitializingBean
{
	Set<String> eventTypes = new HashSet<>();
	@Autowired
    private BinderAwareChannelResolver binderAwareChannelResolver;
	
	public void bind(String eventType)
	{
		eventTypes.add(eventType);
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		eventTypes.stream().forEach( type -> binderAwareChannelResolver.resolveDestination(type));
		
	}
	
	
}
