package com.faciee.cti.valbastrelu.eticket.ui.bus.i;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public interface FragmentWithListI extends FragmentViewI{
	
	void buildRecyclerView(ArrayList list);
	void showDataInList(List list);
}
