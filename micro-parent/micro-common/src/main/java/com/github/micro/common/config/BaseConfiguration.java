package com.github.micro.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.github.micro.common.spring.ApplicationContextHolder;

@EntityScan(basePackages = {
        "com.github.micro.**.entity",
        "org.springframework.data.jpa.convert.threeten"
})
@EnableJpaRepositories("com.github.micro.**.dao")
@ComponentScan({"com.github.micro.**.service", "com.github.micro.**.web"})
public class BaseConfiguration {
	@Bean
	public ApplicationContextHolder applicationContextHolder()
	{
		return ApplicationContextHolder.getInstance();
	}

}
