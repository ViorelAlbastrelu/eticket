package com.faciee.cti.valbastrelu.eticket;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BusViewModelTest {
	
	BusActivityModel busActivityModel;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
//		busActivityModel = new BusActivityModel();
	}
}
