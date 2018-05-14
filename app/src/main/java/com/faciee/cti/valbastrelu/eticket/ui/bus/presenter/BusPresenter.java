package com.faciee.cti.valbastrelu.eticket.ui.bus.presenter;

import android.view.View;

import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentPresenterI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.AbstractFrgTabPresenter;

public class BusPresenter {
	private static final BusActivityModel busActivityModel = new BusActivityModel();
	
	public static class FrgTb01Presenter extends AbstractFrgTabPresenter<FragmentViewI> implements FragmentPresenterI {
		
		public FrgTb01Presenter(FragmentViewI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView(View view) {
			fragmentViewI.buildRecyclerView(view, busActivityModel.getListaBilete());
		}
	}
	
	public static class FrgTb02MainPresenter extends AbstractFrgTabPresenter<FragmentViewI> implements FragmentPresenterI {
		
		public FrgTb02MainPresenter(FragmentViewI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView(View view) {
			fragmentViewI.buildRecyclerView(view, busActivityModel.getListaTrasee());
		}
	}
	
	public static class FrgTb02StatiiPresenter extends AbstractFrgTabPresenter<FragmentViewI> implements FragmentPresenterI{
		
		public FrgTb02StatiiPresenter(FragmentViewI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView(View view) {
			fragmentViewI.buildRecyclerView(view, busActivityModel.getListaStatii());
		}
	}
	
	public static class FrgTb03Presenter extends AbstractFrgTabPresenter<FragmentViewI> implements FragmentPresenterI {
		
		public FrgTb03Presenter(FragmentViewI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView(View view) {
			fragmentViewI.buildRecyclerView(view, busActivityModel.getListaIstorice());
		}
	}
}
