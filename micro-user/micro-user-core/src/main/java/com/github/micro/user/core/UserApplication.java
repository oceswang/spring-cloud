package com.github.micro.user.core;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Import;

import com.github.micro.common.config.BaseConfiguration;
import com.github.micro.common.config.SwaggerConfiguration;
import com.github.micro.common.event.config.EventConfiguration;

@SpringCloudApplication
@EnableHystrixDashboard
@Import({BaseConfiguration.class, SwaggerConfiguration.class, EventConfiguration.class})
public class UserApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(UserApplication.class, args);
    }
    
}
