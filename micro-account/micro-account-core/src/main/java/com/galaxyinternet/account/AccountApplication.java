package com.galaxyinternet.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.galaxyinternet.common.config.BaseConfiguration;
import com.galaxyinternet.common.config.WebConfiguration;
import com.galaxyinternet.common.event.config.EventConfiguration;
import com.galaxyinternet.common.event.config.ProducerBinder;
import com.galaxyinternet.common.event.entity.EventType;

@SpringBootApplication
@Import({ BaseConfiguration.class, WebConfiguration.class, EventConfiguration.class })
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