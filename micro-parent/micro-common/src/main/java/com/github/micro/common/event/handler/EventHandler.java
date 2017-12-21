package com.github.micro.common.event.handler;

import com.github.micro.api.event.entity.BaseEvent;

public interface EventHandler<T extends BaseEvent>
{
	public void handle(T event);
}
