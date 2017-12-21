package com.github.micro.account.handler;

import com.github.micro.account.service.AccountService;
import com.github.micro.common.event.handler.EventHandler;
import com.github.micro.common.spring.ApplicationContextHolder;
import com.github.micro.user.api.event.UserCreatedEvent;

public class UserCreatedHandler implements EventHandler<UserCreatedEvent>
{
	@Override
	public void handle(UserCreatedEvent event)
	{
		AccountService accountService = ApplicationContextHolder.getInstance().getBean(AccountService.class);
		accountService.init(event.getUserId());
		
	}

}
