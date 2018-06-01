package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;

public class BindingAdapters {
	
	@BindingAdapter("visibleGone")
	public static void showHide(View view, boolean show) {
		view.setVisibility(show ? View.VISIBLE : View.GONE);
	}
	
	@BindingAdapter("statusColor")
	public static void statusColor(TextView textView, boolean active) {
		textView.setTextColor(active ? Color.GREEN : Color.RED);
	}
	
	@BindingAdapter("statusBilet")
	public static void statusBilet(TextView textView, boolean active) {
		textView.setText(active ? R.string.status_bilet_activ : R.string.status_bilet_expirat);
	}
}
