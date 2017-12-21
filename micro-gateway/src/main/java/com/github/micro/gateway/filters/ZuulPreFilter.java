package com.github.micro.gateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ZuulPreFilter extends ZuulFilter
{
	private static final Logger logger = LoggerFactory.getLogger(ZuulPreFilter.class);

	@Override
	public Object run()
	{
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		return null;
	}

	@Override
	public boolean shouldFilter()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType()
	{
		// TODO Auto-generated method stub
		return "pre";
	}

}
