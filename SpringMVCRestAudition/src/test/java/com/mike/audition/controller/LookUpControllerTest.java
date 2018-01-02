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
import org.springframework.ui.Model;

import com.mike.audition.model.LookUpForm;
import com.mike.audition.service.QueryDBService;



/**
 * @author Michael Somers Date: 11/3/2017
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LookUpControllerTest {
	@Mock
	private HttpServletRequest mockHttpServletRequest;
	@Mock
	private QueryDBService mockQueryDBService;
    
	@Mock
	private Model mockModel;

	private LookUpController lookUpController;

	@Before
	public void setUp() throws Exception {
		lookUpController = new LookUpController(mockQueryDBService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormAvailable() {
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/form");
		assertEquals("form", lookUpController.lookUpForm(mockModel));
		
	}

	@Test
	public void testResultsSubmitAccess() {

		String account = "111111111";

		LookUpForm lookUpResults = new LookUpForm();
		lookUpResults.setResult(true);
		lookUpResults.setId(account);
		Map<String, Boolean> results = new HashMap<>();
		results.put(account, true);
		mockModel.addAllAttributes(results);
		
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/form");
		assertEquals("results", lookUpController.LookUpResultsSubmit(lookUpResults, mockModel));
	
	}
	
	@Test
	public void testResultsNullSubmitAccess() {

		String account = null;

		LookUpForm lookUpResults = new LookUpForm();
		lookUpResults.setResult(true);
		lookUpResults.setId(account);
		Map<String, Boolean> results = new HashMap<>();
		results.put(account, true);
		mockModel.addAllAttributes(results);
		
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/form");
		assertEquals("results", lookUpController.LookUpResultsSubmit(lookUpResults, mockModel));
	
	}

}
