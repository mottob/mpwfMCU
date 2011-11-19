package com.moto.server.restful.application;

import java.util.HashSet;
import java.util.Set;


import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.ext.jaxrs.InstantiateException;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.restlet.ext.jaxrs.ObjectFactory;
import org.restlet.routing.Router;
import org.restlet.security.Authenticator;
import org.restlet.security.MapVerifier;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.moto.server.restful.authenticate.CookieAuthenticator;
import com.moto.server.restful.authenticate.DefaultAuthenticator;
import com.moto.server.restful.resource.AvatarResource;
import com.moto.server.restful.resource.BaseResource;
import com.moto.server.restful.resource.DiaryResource;
import com.moto.server.restful.resource.LoginResource;
import com.moto.server.restful.resource.RegisterResource;
import com.moto.server.restful.resource.UserResource;

public class TestApplication extends Application {
	public TestApplication() {
		
	}
	public static void main(String[] args) throws Exception {
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);
		TestApplication application = new TestApplication();
		
		component.getDefaultHost().attach(application);
		component.start();
	}
	
	@Override
	public Restlet createInboundRoot() {
        MapVerifier verifier = new MapVerifier();
        verifier.getLocalSecrets().put("scott", "tiger".toCharArray());

        CookieAuthenticator authenticator = new CookieAuthenticator(
                getContext(), "Cookie Test");
        authenticator.setVerifier(verifier);
        return authenticator;
	}
	
	
}