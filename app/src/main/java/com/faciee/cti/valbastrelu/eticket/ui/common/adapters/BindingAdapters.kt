package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.faciee.cti.valbastrelu.eticket.R
import java.math.BigDecimal
import java.util.*

object BindingAdapters {

	@JvmStatic
	@BindingAdapter("shortDate")
	fun setShortDate(view: TextView, date: Date) {
		val monthNameShort = view.context.resources.getStringArray(R.array.month_short)[date.month]
		view.text = view.context.getString(R.string.istoric_data, date.date, monthNameShort)
	}

	@JvmStatic
	@BindingAdapter("priceColor")
	fun setPriceColor(view: TextView, amount: BigDecimal){
		view.setTextColor(if (amount < BigDecimal.ZERO) Color.RED else Color.GREEN)
	}

	@JvmStatic
	@BindingAdapter("visibleGone")
	fun showHide(view: View, show: Boolean) {
		view.visibility = if (show) View.VISIBLE else View.GONE
	}

	@JvmStatic
	@BindingAdapter("statusColor")
	fun statusColor(textView: TextView, active: Boolean) {
		textView.setTextColor(if (active) Color.GREEN else Color.RED)
	}

	@JvmStatic
	@BindingAdapter("statusBilet")
	fun statusBilet(textView: TextView, active: Boolean) {
		textView.setText(if (active) R.string.status_bilet_activ else R.string.status_bilet_expirat)
	}

	@JvmStatic
	@BindingAdapter("transportIcon")
	fun setTransportIcon(imageView: ImageView, @DrawableRes icon: Int) {
		imageView.setImageResource(icon)
	}

}