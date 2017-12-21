package com.github.micro.common.event.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binder.BinderFactory;
import org.springframework.cloud.stream.binding.AbstractBindingTargetFactory;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.cloud.stream.binding.BindingService;
import org.springframework.cloud.stream.binding.DynamicDestinationsBindable;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.github.micro.common.event.scheduler.EventScheduler;
import com.github.micro.common.spring.cloud.stream.CustomBindingService;

@Configuration
@EnableBinding(Processor.class)
public class EventConfiguration
{

	
	@Bean
	public EventTypeRegistry eventRegistry()
	{
		return new EventTypeRegistry();
	}

	@Bean
	public BindingService bindingService(BindingServiceProperties bindingServiceProperties, BinderFactory binderFactory, EventTypeRegistry eventRegistry)
	{
		return new CustomBindingService(bindingServiceProperties, binderFactory, eventRegistry);
	}

	@Bean
	public BinderAwareChannelResolver binderAwareChannelResolver(
			BindingService bindingService, 
			AbstractBindingTargetFactory<? extends MessageChannel> bindingTargetFactory,
			DynamicDestinationsBindable dynamicDestinationsBindable)
	{
		return new BinderAwareChannelResolver(bindingService, bindingTargetFactory, dynamicDestinationsBindable);
	}
	
	@Bean 
	public EventScheduler eventScheduler()
	{
		return new EventScheduler();
	}
	@Bean 
	public TaskExecutor taskExecutor()
	{
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix("EventExecutor-");
        executor.initialize();
        return executor;
	}
}
