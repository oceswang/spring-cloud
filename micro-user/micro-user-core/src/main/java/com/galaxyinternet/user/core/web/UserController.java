package com.galaxyinternet.user.core.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(UserURL.GET_USERS)
	public List<UserDTO> findAll(HttpSession session)
	{
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
	
	public UserDTO convert2DTO(User user)
	{
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
	
	

}
