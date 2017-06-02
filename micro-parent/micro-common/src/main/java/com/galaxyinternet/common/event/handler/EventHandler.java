package com.galaxyinternet.common.event.handler;

import com.galaxyinternet.api.event.entity.BaseEvent;

public interface EventHandler<T extends BaseEvent>
{
	public void handle(T event);
}
