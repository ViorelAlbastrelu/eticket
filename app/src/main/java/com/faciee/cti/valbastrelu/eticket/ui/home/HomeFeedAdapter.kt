package com.faciee.cti.valbastrelu.eticket.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel
import com.google.firebase.auth.FederatedAuthProvider

class HomeFeedAdapter : RecyclerView.Adapter<FeedItemViewHolder<*>>() {

	var feedItems = listOf<Any>()
		set(value) {
			if (! value.isNullOrEmpty()) {
				field = value
				notifyDataSetChanged()
			}
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder<*> {
		return when (viewType) {
			TICKET_VIEW_TYPE -> {
				FeedItemViewHolder.TicketFeedHolder(inflateDataBinding(parent, R.layout.item_ticket))
			}
			PTICKET_VIEW_TYPE -> {
				FeedItemViewHolder.ParkingTicketFeedHolder(inflateDataBinding(parent, R.layout.item_ticket_parking))
			}
			ROUTE_VIEW_TYPE -> {
				FeedItemViewHolder.RouteFeedHolder(inflateDataBinding(parent, R.layout.item_scheduled_route))
			}
			else -> {
				FeedItemViewHolder.TicketFeedHolder(inflateDataBinding(parent, R.layout.item_ticket))
			}
		}
	}

	override fun getItemCount(): Int = feedItems.size

	override fun getItemViewType(position: Int) = when (feedItems[position]) {
		is Ticket -> TICKET_VIEW_TYPE
		is TicketParking -> PTICKET_VIEW_TYPE
		is Route -> ROUTE_VIEW_TYPE
		else -> UNKNOWN_VIEW_TYPE
	}

	override fun onBindViewHolder(holder: FeedItemViewHolder<*>, position: Int) {
		when (holder) {
			is FeedItemViewHolder.TicketFeedHolder -> holder.bind(feedItems[position] as Ticket)
			is FeedItemViewHolder.ParkingTicketFeedHolder -> holder.bind(feedItems[position] as TicketParking)
			is FeedItemViewHolder.RouteFeedHolder -> holder.bind(feedItems[position] as Route)
		}
	}

	private fun <VDB : ViewDataBinding> inflateDataBinding(parent: ViewGroup, @LayoutRes layout: Int): VDB {
		return DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
	}

	fun <T> updateFeed(newFeedItems: List<T>) {
		val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
			override fun getOldListSize(): Int = feedItems.size

			override fun getNewListSize(): Int = newFeedItems.size

			override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
				val oldItem = feedItems[oldItemPosition]
				val newItem = newFeedItems[newItemPosition]
				if (oldItem is Ticket && newItem is Ticket) {
					return oldItem.id == newItem.id
				}
				return false
			}

			//check visible items
			override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
				val oldItem = feedItems[oldItemPosition]
				val newItem = newFeedItems[newItemPosition]
				if (oldItem is Ticket && newItem is Ticket) {
					return oldItem == newItem
				}
				return false
			}

		})
	}
}