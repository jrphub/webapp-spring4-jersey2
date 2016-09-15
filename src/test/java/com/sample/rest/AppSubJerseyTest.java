package com.sample.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Application;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.springframework.mock.web.MockHttpServletRequest;

public class AppSubJerseyTest extends JerseyTest {

	@Override
	public Application configure() {
		setTestProperties();

		ResourceConfig config = new ResourceConfig(String.class);

		registerBinder(config);

		return config;

	}

	protected void setTestProperties() {
		// Find first available port to run multiple test containers in parallel
		// to avoid TestContainerException: java.net.BindException: Address
		// already in use: bind
		forceSet(TestProperties.CONTAINER_PORT, "0");

		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

	}

	protected void registerBinder(ResourceConfig config) {
		config.register(new AbstractBinder() {
			@Override
			public void configure() {
				bindFactory(HttpServletRequestFactory.class).to(
						HttpServletRequest.class);
			}
		});

	}

	public static class HttpServletRequestFactory implements
			Factory<HttpServletRequest> {

		@Override
		public HttpServletRequest provide() {
			return new MockHttpServletRequest();
		}

		@Override
		public void dispose(HttpServletRequest t) {
		}
	}

}
