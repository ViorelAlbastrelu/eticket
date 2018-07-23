package com.faciee.cti.valbastrelu.eticket.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.faciee.cti.valbastrelu.eticket.R;

import java.io.Serializable;

public class SignOutDialog extends DialogFragment implements Serializable {
	
	private SignOutCallback signOutCallback;
	
	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		signOutCallback = (SignOutCallback) getArguments().getSerializable("callback");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.signout_message)
				.setPositiveButton(R.string.afirmativ, (dialog, which) -> {
//					ETicketApp.toastMessageShort("OK");
					signOutCallback.confirm();
				})
				.setNegativeButton(R.string.negativ, (dialog, which) -> {
					ETicketApp.toastMessageShort("Canceled");
				});
		return builder.create();
	}
}
