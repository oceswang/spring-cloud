package com.galaxyinternet.common.event.config;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.reflect.FieldUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import com.galaxyinternet.api.event.constants.EventType;
import com.galaxyinternet.api.event.entity.BaseEvent;

public class EventTypeRegistry implements InitializingBean
{

	private Set<String> eventTypes = new HashSet<>();


	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception
	{
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AssignableTypeFilter(BaseEvent.class));
		
		Set<BeanDefinition> beanDefs = provider.findCandidateComponents("com/galaxyinternet");
		for(BeanDefinition def : beanDefs)
		{
			String className = def.getBeanClassName();
			Class<? extends BaseEvent> eventClass = (Class<? extends BaseEvent>)Class.forName(className);
			Field field = FieldUtils.getField(eventClass, "EVENT_TYPE");
			if(field == null)
			{
				throw new RuntimeException("EVENT_TYPE is required for class "+className);
			}
			EventType eventType = (EventType)field.get(null);
			eventTypes.add(eventType.name());
		}
	}

	public Set<String> getEventTypes()
	{
		return eventTypes;
	}
	
	

}
