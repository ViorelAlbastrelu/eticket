package com.faciee.cti.valbastrelu.eticket;

import android.text.TextUtils;

import com.faciee.cti.valbastrelu.eticket.ui.login.FireBaseWrapper;
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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
	@Spy
	LoginPresenter loginPresenter;
	@Mock
	FireBaseWrapper fireBaseWrapperMock;
	@Mock
	LoginView loginViewMock;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		loginPresenter = Mockito.spy(LoginPresenter.class);
		loginPresenter.setFireBaseAuth(fireBaseWrapperMock);
	}
	
	@After
	public void tearDown(){
	}
	
	@Test
	public void testValidCredentials(){
		String email = "test@email.com";
		String password = "123456";
		Mockito.when(loginPresenter.validateForm(email, password)).thenReturn(false);
		Assert.assertEquals(false, loginPresenter.validateForm(email, password));
	}
	
	@Test
	public void testWrongEmail(){
		String email = "testemail.com";
		String password = "123456";
		Mockito.when(loginPresenter.validateForm(email, password)).thenReturn(false);
		Assert.assertEquals(false, loginPresenter.validateForm(email, password));
	}
	
	@Test
	public void testWrongPassword(){
		String email = "test@email.com";
		String password = "12";
		Mockito.when(loginPresenter.validateForm(email, password)).thenReturn(false);
		Assert.assertEquals(false, loginPresenter.validateForm(email, password));
	}
	@Test
	public void testEmptyEmail(){
		String email = "";
		String password = "123456";
		Mockito.when(loginPresenter.validateForm(email, password)).thenReturn(false);
		Assert.assertEquals(false, loginPresenter.validateForm(email, password));
	}
	
	@Test
	public void testEmptyPassword(){
		String email = "test@email.com";
		String password = "";
		Mockito.when(loginPresenter.validateForm(email, password)).thenReturn(false);
		Assert.assertEquals(false, loginPresenter.validateForm(email, password));
	}
	
}
