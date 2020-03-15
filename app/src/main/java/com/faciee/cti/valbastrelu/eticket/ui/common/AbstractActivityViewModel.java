package com.faciee.cti.valbastrelu.eticket.ui.common;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;

public abstract class AbstractActivityViewModel extends AndroidViewModel{
	
	public AbstractActivityViewModel(@NonNull Application application) {
		super(application);
	}
}
