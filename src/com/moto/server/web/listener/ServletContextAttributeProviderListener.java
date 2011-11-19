package com.moto.server.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class ServletContextAttributeProviderListener implements
		ServletContextAttributeListener {
	
	private static Logger logger = LoggerFactory.getLogger(ServletContextAttributeProviderListener.class);
	private static Marker fatalMarker = MarkerFactory.getMarker("fatal");
	private static Marker errorMarker = MarkerFactory.getMarker("error");

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		fatalMarker.add(errorMarker);
		logger.debug(fatalMarker, "the event name is {}, value is {}", event.getName(), event.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		event.getName();
		event.getValue();
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		event.getName();
		event.getValue();
	}

}
