package com.galaxyinternet.common.event.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.galaxyinternet.common.event.service.EventService;

public class EventScheduler
{
	@Autowired
	EventService eventService;
	
	@Scheduled(fixedRate=500L)
	public void sendUnpublishedEvent()
	{
		eventService.sendUnpublishEvent();
	}
}
