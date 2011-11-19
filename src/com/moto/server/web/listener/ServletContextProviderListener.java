package com.moto.server.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextProviderListener implements ServletContextListener {
	private volatile static ServletContext context;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		context = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
	}

	public static ServletContext getServletContext()
	{
		return context;
	}
}
