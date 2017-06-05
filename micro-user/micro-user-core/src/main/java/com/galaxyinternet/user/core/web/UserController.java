package com.galaxyinternet.user.core.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.galaxyinternet.user.api.UserDTO;
import com.galaxyinternet.user.api.UserURL;
import com.galaxyinternet.user.core.entity.User;
import com.galaxyinternet.user.core.service.UserService;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	@Autowired
	DiscoveryClient discoveryClient;
	@Autowired
	private UserService service;
	
	@RequestMapping(UserURL.GET_USERS)
	@HystrixCommand(fallbackMethod="findAllFallback")
	public List<UserDTO> findAll(HttpSession session)
	{
		ServiceInstance inst = discoveryClient.getLocalServiceInstance();
		System.err.println("Port - "+inst.getPort());
		
		Iterable<User> list = service.findAll();
		return Lists.newArrayList(list).stream().map(this::convert2DTO).collect(Collectors.toList());
	}
	@RequestMapping(value=UserURL.REGISTER, method = RequestMethod.POST)
	public UserDTO register(@RequestBody UserDTO dto)
	{
		User vo = service.registr(dto);
		dto.setId(vo.getId());
		return dto;
	}
	
	public List<UserDTO> findAllFallback(HttpSession session)
	{
		Iterable<User> list = new ArrayList<>();
		return Lists.newArrayList(list).stream().map(this::convert2DTO).collect(Collectors.toList());
	}
	
	public UserDTO convert2DTO(User user)
	{
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
	
	

}
