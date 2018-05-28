package com.faciee.cti.valbastrelu.eticket;

import com.faciee.cti.valbastrelu.eticket.ui.login.LoginPresenter;
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginView;

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
	LoginView loginView;
	LoginPresenter loginPresenter;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		loginView = Mockito.mock(LoginView.class);
		loginPresenter = new LoginPresenter(loginView);
	}
	
	@After
	public void tearDown(){
	
	}
	
	@Test
	public void testValidationOnEmail(){
		Assert.assertEquals(false, loginPresenter.validateForm("test", "123456"));
	}
}
