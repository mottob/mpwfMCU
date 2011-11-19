package com.moto.server.restful.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

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

@org.springframework.stereotype.Component("springJaxRsApplication")
public class SpringJaxRsApplication extends JaxRsApplication implements
		InitializingBean,BeanFactoryAware  {
	@Autowired
	@Qualifier("component")
	private Component component;
	
	public SpringJaxRsApplication() {
		MapVerifier verifier = new MapVerifier();
        verifier.getLocalSecrets().put("scott", "tiger".toCharArray());

        CookieAuthenticator authenticator = new CookieAuthenticator(
                getContext(), "Cookie Test");
        authenticator.setVerifier(verifier);
        authenticator.setNext(this);
        setGuard(authenticator);
	}
	public static void main(String[] args) throws Exception {
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);
		component.getClients().add(Protocol.CLAP);
		final JaxRsApplication application = new JaxRsApplication(){
			@Override
			public Restlet createInboundRoot() {
		        MapVerifier verifier = new MapVerifier();
		        verifier.getLocalSecrets().put("scott", "tiger".toCharArray());

		        CookieAuthenticator authenticator = new CookieAuthenticator(
		                getContext(), "Cookie Test");
		        authenticator.setVerifier(verifier);
		        authenticator.setNext(this);
		        return authenticator;
			}
		};
		application.add(new Application() {
			
			@Override
			public Set<Class<?>> getClasses() {
				final Set<Class<?>> classes = new HashSet<Class<?>>();

				// NOTE: Here we must list all the provider JAX RS classes
				classes.add(BaseResource.class);
				return classes;
			}
		});
		component.getDefaultHost().attach(application);
		component.start();
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
