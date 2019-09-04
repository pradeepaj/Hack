package com.ing.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.dto.LoginReqDto;
import com.ing.dto.LoginResDto;
import com.ing.entity.User;
import com.ing.exception.EnterValidCredentials;
import com.ing.exception.UserNotFound;
import com.ing.repository.UserRepository;
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

	@Mock
	UserRepository userRepository;

	
	@InjectMocks
	LoginServiceImpl userLoginService;
	
	LoginReqDto regExc =null;
	@Before
	public void setUp() {
		regExc = new LoginReqDto();
		regExc.setEmail("");
	}
	
	@Test
	public void Logintest() {
		LoginReqDto req=new LoginReqDto();
		req.setEmail("pradeep.aj28@gmail.com");
		req.setPassword("qwerty");
		LoginResDto res=new LoginResDto();
		res.setMessage("succes");
		res.setStatusCode(200);
		User ud=new User();
		ud.setEmail("pradeep.aj28@gmail.com");
		ud.setPassword("qwerty");
		ud.setUserId(1);
		Mockito.when(userRepository.findByEmailAndPassword(req.getEmail(),req.getPassword())).thenReturn(ud);
		LoginResDto actualValue = userLoginService.userLogin(req);
		assertEquals(200, actualValue.getStatusCode());
		
	}
	
	@Test(expected = UserNotFound.class)
	public void testUserNotFoundException() {
		userLoginService.userLogin(regExc);
	}

}
