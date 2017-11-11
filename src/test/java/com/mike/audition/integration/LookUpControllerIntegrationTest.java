package com.mike.audition.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mike.audition.SpringBootWebApplication;
import com.mike.audition.model.LookUpForm;
import com.mike.audition.service.QueryDBService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@ActiveProfiles("dev")
public class LookUpControllerIntegrationTest {
	
	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;	
    @Mock
    private QueryDBService mockQueryDBService;
  
    private static Map<String, LookUpForm> mockDBMap;

	@SuppressWarnings("serial") 
	@Before
    public void init(){
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockDBMap = Collections.unmodifiableMap(new HashMap<String, LookUpForm>() {
			{
				put("111111111", new LookUpForm( true, UUID.randomUUID().toString()));
				put("123123123", new LookUpForm(true, UUID.randomUUID().toString()));
			}
		});
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void testPostEmpytString() throws Exception {
		String account = " ";
		LookUpForm modelResults = new LookUpForm();
		modelResults.setId(account);
		modelResults.setResult(true);
        assertThat(this.mockQueryDBService).isNotNull();
        when(this.mockQueryDBService.getAccountById(account)).thenReturn(mockDBMap.get(account));
        mockMvc.perform(MockMvcRequestBuilders
        			.post("/form")
        			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        			.param("id", account)
        		)
                .andExpect(status().isOk())
                .andExpect(view().name("results"))
                .andExpect(MockMvcResultMatchers.view().name("results"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lookUpForm"))
                .andExpect(model().attribute("lookUpForm", hasProperty("result", is(false))))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andDo(print());
    }
	
	@Test
    public void testPost() throws Exception {
		String account = "111111111";
		LookUpForm modelResults = new LookUpForm();
		modelResults.setId(account);
		modelResults.setResult(true);
        assertThat(this.mockQueryDBService).isNotNull();
        when(this.mockQueryDBService.getAccountById(account)).thenReturn(mockDBMap.get(account));
        mockMvc.perform(MockMvcRequestBuilders
        			.post("/form")
        			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
        			.param("id", account)
        		)
                .andExpect(status().isOk())
                .andExpect(view().name("results"))
                .andExpect(MockMvcResultMatchers.view().name("results"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lookUpForm"))
                .andExpect(model().attribute("lookUpForm", hasProperty("result", is(true))))
                //.andExpect(content().string(Matchers.containsString("Spring")))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andDo(print());
    }
	
	//@Test
    public void testGet() throws Exception {
		
		assertThat(this.mockQueryDBService).isNotNull();
        mockMvc.perform(MockMvcRequestBuilders
        			.get("/form")
        		)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lookUpForm"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andDo(print());
    }

}
