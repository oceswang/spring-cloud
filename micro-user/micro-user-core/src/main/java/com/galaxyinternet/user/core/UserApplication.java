package com.galaxyinternet.user.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.galaxyinternet.common.config.BaseConfiguration;
import com.galaxyinternet.common.config.WebConfiguration;
import com.galaxyinternet.common.event.config.EventConfiguration;

@SpringBootApplication
@Import({BaseConfiguration.class, WebConfiguration.class,EventConfiguration.class})
public class UserApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(UserApplication.class, args);
    }
    
}
