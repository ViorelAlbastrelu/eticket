package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.extensions.dp
import kotlin.math.roundToInt

class SpacingItemDecoration(private val distance: Int, @RecyclerView.Orientation private val  orientation: Int) : ItemDecoration() {

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
								state: RecyclerView.State) {
		when (orientation){
			RecyclerView.HORIZONTAL -> {
				outRect.right = distance.dp
			}
			RecyclerView.VERTICAL -> {
				outRect.bottom = distance.dp
			}
		}
	}

}