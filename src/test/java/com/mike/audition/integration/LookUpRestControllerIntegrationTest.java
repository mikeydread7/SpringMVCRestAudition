package com.mike.audition.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mike.audition.SpringBootWebApplication;
import com.mike.audition.dao.AccountDao;
import com.mike.audition.service.QueryDBService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@ActiveProfiles("dev")
public class LookUpRestControllerIntegrationTest {
	
	private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext webApplicationContext;	
    @Mock
    private QueryDBService mockQueryDBService;
    @Mock
	private AccountDao mockAccountDao;
    
	@Before
    public void init(){
       MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void testGetAll() throws Exception {
		
		assertThat(this.mockQueryDBService).isNotNull();
		mockMvc.perform(get("/account").accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk())
         .andDo(print());
    }
	
	@Test
    public void testGet() throws Exception {
		
		String account = "111111111";
		assertThat(this.mockQueryDBService).isNotNull();
		mockMvc.perform(get("/account/".concat(account)).accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.result", is(true)))
		 .andDo(print()); 
		
		account = "11111111144";
		assertThat(this.mockQueryDBService).isNotNull();
		mockMvc.perform(get("/account/".concat(account)).accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.result", is(false)))
		 .andDo(print());
		
		mockMvc.perform(get("/accounts/" + account)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
    }

}
