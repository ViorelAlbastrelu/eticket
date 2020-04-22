package com.faciee.cti.valbastrelu.eticket.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes

object AlertDialogs {
	fun signoutDialog(context: Context,
					  @StringRes messageId: Int,
					  @StringRes positiveMessage: Int,
					  @StringRes negativeMessage: Int,
					  positiveAction: DialogInterface.OnClickListener,
					  negativeAction: DialogInterface.OnClickListener
	): Dialog{
		return AlertDialog
				.Builder(context)
				.setMessage(context.getString(messageId))
				.setPositiveButton(context.getString(positiveMessage), positiveAction)
				.setNegativeButton(context.getString(negativeMessage), negativeAction)
				.create()
	}
}