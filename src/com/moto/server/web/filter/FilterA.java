package com.moto.server.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class FilterA implements Filter{
	private final static Logger LOGGER = LoggerFactory.getLogger(FilterA.class);
	private static Marker fatalMarker = MarkerFactory.getMarker("fatal");
	private static Marker errorMarker = MarkerFactory.getMarker("error");
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		fatalMarker.add(errorMarker);
		LOGGER.debug(fatalMarker, "filter {} has filtered", this.getClass().getName());
		filterChain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
}
