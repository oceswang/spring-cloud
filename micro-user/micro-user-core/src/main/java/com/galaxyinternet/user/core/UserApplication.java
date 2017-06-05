package com.galaxyinternet.user.core;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;

import com.galaxyinternet.common.config.BaseConfiguration;
import com.galaxyinternet.common.event.config.EventConfiguration;

@SpringCloudApplication
@Import({BaseConfiguration.class,EventConfiguration.class})
public class UserApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(UserApplication.class, args);
    }
    
}
