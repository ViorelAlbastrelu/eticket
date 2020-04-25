package com.faciee.cti.valbastrelu.eticket.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.databinding.ItemRouteBinding
import com.faciee.cti.valbastrelu.eticket.databinding.ItemTicketBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import kotlinx.coroutines.channels.ticker

const val UNKNOWN_VIEW_TYPE = 0
const val TICKET_VIEW_TYPE = 1
const val TRIP_VIEW_TYPE = 2

sealed class FeedItemViewHolder<ITEM>(itemView: View): RecyclerView.ViewHolder(itemView) {
	abstract fun bind(item: ITEM)

	class TicketFeedHolder(val ticketBinding: ItemTicketBinding): FeedItemViewHolder<Ticket>(ticketBinding.root){
		override fun bind(item: Ticket) {
			ticketBinding.ticket = item
		}
	}
	class TripFeedHolder(routeBinding: ItemRouteBinding): FeedItemViewHolder<Route>(routeBinding.root){
		override fun bind(item: Route) {

		}
	}
}