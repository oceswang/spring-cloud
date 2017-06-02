package com.galaxyinternet.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.galaxyinternet.common.spring.ApplicationContextHolder;

@EntityScan(basePackages = {
        "com.galaxyinternet.**.entity",
        "org.springframework.data.jpa.convert.threeten"
})
@EnableJpaRepositories("com.galaxyinternet.**.dao")
@ComponentScan({"com.galaxyinternet.**.service", "com.galaxyinternet.**.web"})
public class BaseConfiguration {
	@Bean
	public ApplicationContextHolder applicationContextHolder()
	{
		return ApplicationContextHolder.getInstance();
	}

}
