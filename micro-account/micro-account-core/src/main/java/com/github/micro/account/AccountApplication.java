package com.github.micro.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.github.micro.api.event.constants.EventType;
import com.github.micro.common.config.BaseConfiguration;
import com.github.micro.common.config.HystrixConfiguration;
import com.github.micro.common.event.config.EventConfiguration;
import com.github.micro.common.event.config.ProducerBinder;

@SpringBootApplication
@Import({ BaseConfiguration.class, HystrixConfiguration.class, EventConfiguration.class })
public class AccountApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(AccountApplication.class, args);

	}

	@Bean
	public ProducerBinder binder()
	{
		ProducerBinder binder = new ProducerBinder();
		binder.bind(EventType.USER_CREATED.name());
		return binder;
	}

}
