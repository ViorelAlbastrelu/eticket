package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.databinding.ItemTicketBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TicketsAdapter.TicketViewHolder

class TicketsAdapter : RecyclerView.Adapter<TicketViewHolder>() {

	var tickets: List<Ticket> = listOf()
		set(value) {
			if (!value.isNullOrEmpty()) {
				field = value
				notifyDataSetChanged()
			}
		}

	fun updateTickets(newTickets: List<Ticket>) {
		if (newTickets.isNotEmpty()) {
			val result = DiffUtil.calculateDiff(TicketDiffUtil(tickets, newTickets))
			tickets = newTickets
			result.dispatchUpdatesTo(this)
		}
	}

	override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
		Log.d(TAG, "onBindViewHolder: called.")
		holder.itemTicketBinding.ticket = tickets[position]
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
		return TicketViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ticket, parent, false))
	}

	override fun getItemCount(): Int = tickets.size

	inner class TicketViewHolder(val itemTicketBinding: ItemTicketBinding) : RecyclerView.ViewHolder(itemTicketBinding.root)

	companion object {
		private const val TAG = "TicketsAdapter"
	}
}