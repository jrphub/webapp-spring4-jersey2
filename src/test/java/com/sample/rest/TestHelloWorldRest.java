package com.sample.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelloWorldRest extends AppSubJerseyTest {

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyTestContainerFactory();
	}

	@Override
	public Application configure() {
		setTestProperties();

		ResourceConfig config = new ResourceConfig(HelloWorldREST.class)
				.property("contextConfig", new ClassPathXmlApplicationContext(
						"applicationContext_test.xml"));

		registerBinder(config);
		return config;
	}

	@Test
	public void testHello() {
		Response response = target("hello/Guido").request().get();
		System.out.println(response);
		assertEquals(200, response.getStatus());
		String out = response.readEntity(String.class);
		assertEquals("Hello Guido!", out);
	}
	
	
	@Test
	public void testHelloJSON() {
		Response response = target("hello/json/Guido").request().get();
		System.out.println(response);
		assertEquals(200, response.getStatus());
		String out = response.readEntity(String.class);
		assertEquals("{\"hello\":\"Guido\"}", out);
	}

}