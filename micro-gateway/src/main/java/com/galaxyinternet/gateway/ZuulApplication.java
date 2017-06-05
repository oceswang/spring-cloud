package com.galaxyinternet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.galaxyinternet.gateway.filters.ZuulPreFilter;

@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ZuulApplication.class, args);
    }
    
    @Bean
    public ZuulPreFilter zuulPreFilter()
    {
    	return new ZuulPreFilter();
    }
}
