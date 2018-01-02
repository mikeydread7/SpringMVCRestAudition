package com.mike.audition.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mike.audition.dao.AccountDao;
import com.mike.audition.model.LookUpForm;
import com.mike.audition.service.QueryDBService;

/**
 * @author Michael Somers Date: 11/3/2017
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class LookUpRestControllerTest {

	@Mock
	private HttpServletRequest mockHttpServletRequest;
	@Mock
	private QueryDBService mockQueryDBService;
	@Mock
	private AccountDao mockAccountDao;

	@InjectMocks
	private LookUpRestController lookUpRestController;
	
	private static Map<String, LookUpForm> mockDBMap;

	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception {
		lookUpRestController = new LookUpRestController(mockQueryDBService);
		mockDBMap = Collections.unmodifiableMap(new HashMap<String, LookUpForm>() {
			{
				put("11111111", new LookUpForm( true, UUID.randomUUID().toString()));
				put("123123123", new LookUpForm(true, UUID.randomUUID().toString()));
			}
		});
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEndPointAccessOK() {

		String account = "123123123";
		
		LookUpForm lookUpForm = new LookUpForm();
		lookUpForm.setId(account);
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/account/".concat(account));
		when((mockQueryDBService.getAccountById(account))).thenReturn(mockDBMap.get(account));
		ResponseEntity<?> results = lookUpRestController.getAccountById(account);
		assertTrue("Test account Found", ((LookUpForm) results.getBody()).isResult());
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}

	@Test
	public void testEndPointAccessOKFoundAnother() {

		String account = "11111111";
		
		LookUpForm lookUpForm = new LookUpForm();
		lookUpForm.setId(account);
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/account/".concat(account));
		when((mockQueryDBService.getAccountById(account))).thenReturn(mockDBMap.get(account));
		ResponseEntity<?> results = lookUpRestController.getAccountById(account);
		assertTrue("Test account Found", ((LookUpForm) results.getBody()).isResult());
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}
 
	@Test
	public void testEndPointAccessOkAccountNotFound() {

		String account = "111111";
		
		LookUpForm lookUpForm = new LookUpForm();
		lookUpForm.setId(account);
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/account/".concat(account));
		when((mockQueryDBService.getAccountById(account))).thenReturn(null == mockDBMap.get(account)? lookUpForm: mockDBMap.get(account));
		ResponseEntity<?> results = lookUpRestController.getAccountById(account);
		assertFalse("Test account not Found ", ((LookUpForm) results.getBody()).isResult());
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testEndPointAccessOkAllAccountsFound() {
		
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/account");
		when((mockQueryDBService.getAllAccounts())).thenReturn(mockDBMap);
		ResponseEntity<?> results = lookUpRestController.getAllAcconts();
		assertTrue("Found all accounts",((Map<String, LookUpForm>)results.getBody()).size() == 2);
		assertEquals(HttpStatus.OK, results.getStatusCode());
	}
	
	@Test
	public void testEndPointAccessOkBadString() {
		String account ="2 @ ^&A";
		
		when(mockHttpServletRequest.getAttribute(anyString())).thenReturn("/account/".concat(account));
		when((mockQueryDBService.getAllAccounts())).thenReturn(mockDBMap);
		ResponseEntity<?> results = lookUpRestController.getAccountById(account);
		assertEquals(HttpStatus.BAD_REQUEST, results.getStatusCode());
	}
	
	//sample negative test that should  expect fail null pointer
	@Test(expected = NullPointerException.class)
    public void expectNullPointerException()
    {
        //some code which throw NullPointerException in run time
        throw new NullPointerException();
    }
}
