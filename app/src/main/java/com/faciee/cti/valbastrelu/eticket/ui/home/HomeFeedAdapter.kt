package com.faciee.cti.valbastrelu.eticket.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket

class HomeFeedAdapter : RecyclerView.Adapter<FeedItemViewHolder<*>>() {

	var feedItems = listOf<Any>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder<*> {
		return when (viewType) {
			TICKET_VIEW_TYPE -> {
				FeedItemViewHolder.TicketFeedHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ticket, parent, false))
			}
			else -> {
				FeedItemViewHolder.TicketFeedHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ticket, parent, false))
			}
		}
	}

	override fun getItemCount(): Int = feedItems.size

	override fun getItemViewType(position: Int) = when (feedItems[position]) {
		is Ticket -> TICKET_VIEW_TYPE
		is Route -> TRIP_VIEW_TYPE
		else -> UNKNOWN_VIEW_TYPE
	}

	override fun onBindViewHolder(holder: FeedItemViewHolder<*>, position: Int) {
		when (holder) {
			is FeedItemViewHolder.TicketFeedHolder -> holder.bind(feedItems[position] as Ticket)
			is FeedItemViewHolder.TripFeedHolder -> TODO()
		}
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