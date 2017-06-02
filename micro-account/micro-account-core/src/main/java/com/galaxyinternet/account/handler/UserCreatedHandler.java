package com.galaxyinternet.account.handler;

import com.galaxyinternet.account.service.AccountService;
import com.galaxyinternet.common.event.handler.EventHandler;
import com.galaxyinternet.common.spring.ApplicationContextHolder;
import com.galaxyinternet.user.api.event.UserCreatedEvent;

public class UserCreatedHandler implements EventHandler<UserCreatedEvent>
{
	@Override
	public void handle(UserCreatedEvent event)
	{
		AccountService accountService = ApplicationContextHolder.getInstance().getBean(AccountService.class);
		accountService.init(event.getUserId());
		
	}

}
