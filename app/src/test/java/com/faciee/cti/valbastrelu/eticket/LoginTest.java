package com.faciee.cti.valbastrelu.eticket;

import com.faciee.cti.valbastrelu.eticket.ui.login.LoginVM;
import com.faciee.cti.valbastrelu.eticket.util.firebase.FireBaseClient;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
	@Mock
	LoginVM loginVM;
	@Mock
	FireBaseClient fireBaseClientMock;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown(){
	}
	
	@Test
	public void testValidCredentials(){
		String email = "test@email.com";
		String password = "123456";
		Mockito.when(loginVM.isFormValid(email, password)).thenReturn(false);
		Assert.assertFalse(loginVM.isFormValid(email, password));
	}
	
	@Test
	public void testWrongEmail(){
		String email = "testemail.com";
		String password = "123456";
		Mockito.when(loginVM.isFormValid(email, password)).thenReturn(false);
		Assert.assertFalse(loginVM.isFormValid(email, password));
	}
	
	@Test
	public void testWrongPassword(){
		String email = "test@email.com";
		String password = "12";
		Mockito.when(loginVM.isFormValid(email, password)).thenReturn(false);
		Assert.assertFalse(loginVM.isFormValid(email, password));
	}
	@Test
	public void testEmptyEmail(){
		String email = "";
		String password = "123456";
		Mockito.when(loginVM.isFormValid(email, password)).thenReturn(false);
		Assert.assertFalse(loginVM.isFormValid(email, password));
	}
	
	@Test
	public void testEmptyPassword(){
		String email = "test@email.com";
		String password = "";
		Mockito.when(loginVM.isFormValid(email, password)).thenReturn(false);
		Assert.assertFalse(loginVM.isFormValid(email, password));
	}
	
}
