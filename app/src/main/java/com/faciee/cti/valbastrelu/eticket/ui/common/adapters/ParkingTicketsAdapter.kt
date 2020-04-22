package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.databinding.ItemTicketParkingBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.ParkingTicketsAdapter.ParkingTicketViewHolder

class ParkingTicketsAdapter : RecyclerView.Adapter<ParkingTicketViewHolder>() {
	var tickets: List<TicketParking> = listOf()
		set(value) {
			if (! value.isNullOrEmpty()) {
				field = value
				notifyDataSetChanged()
			}
		}

	fun updateTickets(newTickets: List<TicketParking>) {
		val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
			override fun getOldListSize(): Int {
				return tickets.size
			}

			override fun getNewListSize(): Int {
				return newTickets.size
			}

			override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
				return tickets[oldItemPosition].id ==
						newTickets[newItemPosition].id
			}

			override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
				val newBilet = newTickets[newItemPosition]
				val oldBilet = tickets[oldItemPosition]
				return newBilet.equals(oldBilet)
			}
		})
		tickets = newTickets
		result.dispatchUpdatesTo(this)
	}

	override fun onBindViewHolder(holderTicket: ParkingTicketViewHolder, position: Int) {
		holderTicket.rowBiletPBinding.ticket = tickets[position]
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingTicketViewHolder =
			ParkingTicketViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ticket_parking, parent, false))

	override fun getItemCount(): Int = tickets.size

	inner class ParkingTicketViewHolder(val rowBiletPBinding: ItemTicketParkingBinding) : RecyclerView.ViewHolder(rowBiletPBinding.root)

	companion object {
		private const val TAG = "BiletParkingRVAdapter"
	}
}