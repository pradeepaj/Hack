package com.ing.controller;




import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.dto.LoginReqDto;
import com.ing.dto.LoginResDto;
import com.ing.service.LoginService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestCase.class, LoginController.class })
@WebAppConfiguration
public class LoginControllerTest {

	@InjectMocks
	private LoginController loginController;
	@Mock
	private LoginService creditService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void testUserLogin() throws Exception {
		LoginReqDto req=new LoginReqDto();
		req.setEmail("pradeep.aj28@gmail.com");
		req.setPassword("qwerty");
		LoginResDto res=new LoginResDto();
		res.setMessage("succes");
		ResponseEntity<LoginResDto> expResult = new ResponseEntity<>(res, HttpStatus.OK);
		when(creditService.userLogin(Mockito.anyObject())).thenReturn(res);
		mockMvc.perform(get("bank/user/login", req).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(req))).andReturn();
		ResponseEntity<LoginResDto> actResult = loginController.userLogin(req);
		assertEquals(expResult, actResult);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

