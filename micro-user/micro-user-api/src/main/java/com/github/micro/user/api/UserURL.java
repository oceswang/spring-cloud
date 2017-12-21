package com.github.micro.user.api;

public interface UserURL {
	
	public String SERVICE_NAME = "user";
	
	public String GET_USERS = "/user/get";
	
	public String GET_USER_BY_ID = "/user/get/{userId}";
	
	public String REGISTER = "/user/register";

}
