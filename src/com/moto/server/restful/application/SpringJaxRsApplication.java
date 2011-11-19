package com.moto.server.restful.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.ext.jaxrs.InstantiateException;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.restlet.ext.jaxrs.ObjectFactory;
import org.restlet.security.MapVerifier;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.moto.server.restful.authenticate.DefaultAuthenticator;
import com.moto.server.restful.resource.AvatarResource;
import com.moto.server.restful.resource.BaseResource;
import com.moto.server.restful.resource.DiaryResource;
import com.moto.server.restful.resource.LoginResource;
import com.moto.server.restful.resource.RegisterResource;
import com.moto.server.restful.resource.UserResource;

@org.springframework.stereotype.Component("springJaxRsApplication")
public class SpringJaxRsApplication extends JaxRsApplication implements
		InitializingBean,BeanFactoryAware  {
	@Autowired
	@Qualifier("component")
	private Component component;
	
	public SpringJaxRsApplication() {
		MapVerifier verifier = new MapVerifier();
        verifier.getLocalSecrets().put("scott", "tiger".toCharArray());

        DefaultAuthenticator authenticator = new DefaultAuthenticator(
                getContext(), "Cookie Test", "Love LBS");
        authenticator.setWrappedVerifier(verifier);
        authenticator.setNext(this);
        setGuard(authenticator);
	}
	
	public void afterPropertiesSet() throws Exception {

		setContext(component.getContext().createChildContext());

		final Set<Class<?>> classes = new HashSet<Class<?>>();

		// NOTE: Here we must list all the provider JAX RS classes
		classes.add(BaseResource.class);
		classes.add(UserResource.class);
		classes.add(LoginResource.class);
		classes.add(RegisterResource.class);
		classes.add(DiaryResource.class);
		classes.add(AvatarResource.class);
		

		add(new Application() {
			@Override
			public Set<Class<?>> getClasses() {
				return classes;
			}
		});
		
		component.getClients().add(Protocol.CLAP);
		component.getDefaultHost().attach(this);
	}

	public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
		setObjectFactory(new ObjectFactory() {   
			            public <T> T getInstance(Class<T> jaxRsClass) throws InstantiateException {   
			            // NOTE: We delegated bean instantiation to spring   
			                return beanFactory.getBean(jaxRsClass);   
			            }   
			        }); 
	}

}
