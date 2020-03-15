package com.faciee.cti.valbastrelu.eticket.ui.common.i;

import androidx.annotation.StringRes;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public interface TransportViewActivity {
	
	void setActivityTitle(String activityTitle);
	void setActivityTitle(@StringRes int activityResId);
	void setToolbarText(String toolbarText);
	void setToolbarText(@StringRes int toolbarResId);
}
