package com.galaxyinternet.user.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.galaxyinternet.user.api.UserDTO;
import com.galaxyinternet.user.api.UserURL;

@FeignClient(UserURL.SERVICE_NAME)
public interface UserClient
{
	@RequestMapping(value=UserURL.GET_USER_BY_ID, method=RequestMethod.GET)
	public UserDTO getById(@PathVariable("id") Long id);
}
