package com.faciee.cti.valbastrelu.eticket.ui.bus.presenter;

import android.view.View;

import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentPresenterI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentWithListI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.AbstractFrgTabPresenter;
import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.model.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.ui.model.Traseu;

import java.util.List;

public class BusPresenter {
	
	private final BusActivityModel busActivityModel = new BusActivityModel();
	
	public class FrgTb01Presenter extends AbstractFrgTabPresenter<FragmentWithListI> implements FragmentPresenterI {
		
		public FrgTb01Presenter(FragmentWithListI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView() {
			fragmentViewI.buildRecyclerView(busActivityModel.getListaBilete());
		}
		
		@Override
		public void refreshData() {
			List<Bilet> bileteleCAlculate = busActivityModel.getListaBilete();
			fragmentViewI.showDataInList(bileteleCAlculate);
			
		}
	}
	
	public class FrgTb02MainPresenter extends AbstractFrgTabPresenter<FragmentWithListI> implements FragmentPresenterI {
		
		public FrgTb02MainPresenter(FragmentWithListI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView() {
			fragmentViewI.buildRecyclerView(busActivityModel.getListaTrasee());
		}
		
		@Override
		public void refreshData() {
			List<Traseu> traseuList = busActivityModel.getListaTrasee();
			fragmentViewI.showDataInList(traseuList);
		}
	}
	
	public class FrgTb02StatiiPresenter extends AbstractFrgTabPresenter<FragmentViewI> implements FragmentPresenterI{
		
		public FrgTb02StatiiPresenter(FragmentWithListI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView() {
		}
		
		@Override
		public void refreshData() {
		
		}
	}
	
	public class FrgTb03Presenter extends AbstractFrgTabPresenter<FragmentWithListI> implements FragmentPresenterI {
		
		public FrgTb03Presenter(FragmentWithListI fragmentViewI) {
			super(fragmentViewI);
		}
		
		@Override
		public void populateRecyclerView() {
			fragmentViewI.buildRecyclerView(busActivityModel.getListaIstorice());
		}
		
		@Override
		public void refreshData() {
			List<Tranzactie> tranzactieList = busActivityModel.getListaIstorice();
			fragmentViewI.showDataInList(tranzactieList);
			
		}
	}
}
