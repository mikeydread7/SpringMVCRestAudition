package com.mike.audition.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;



/**
 * @author Michael Somers Date: 11/3/2017
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WelcomeControllerTest {
	@Mock
	private HttpServletRequest mockHttpServletRequest;
    

	
	private WelcomeController welcomeController;

	@Before
	public void setUp() throws Exception {
		welcomeController = new WelcomeController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormAvailable() {
		Map<String, Object> model = new HashMap<>();
		model.put("message", "test mesage");
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/");
		assertEquals("welcome", welcomeController.welcome(model));
	}

	

}
