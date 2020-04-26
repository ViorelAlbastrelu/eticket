package com.faciee.cti.valbastrelu.eticket.util

import androidx.recyclerview.widget.DiffUtil
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.ui.chat.ChatMessage

class TicketDiffUtil(
		private val tickets: List<Ticket>,
		private val newTickets: List<Ticket>
) : DiffUtil.Callback() {
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
		val newTicket = newTickets[newItemPosition]
		val oldTicket = tickets[oldItemPosition]
		return newTicket == oldTicket
	}
}

class RouteDiffUtil(
		private val routes: List<Route>,
		private val newRoutes: List<Route>
) : DiffUtil.Callback() {
	override fun getOldListSize(): Int = routes.size

	override fun getNewListSize(): Int = newRoutes.size

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
			routes[oldItemPosition].number == newRoutes[newItemPosition].number

	//check visible content
	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val newRoute = newRoutes[newItemPosition]
		val oldRoute = routes[oldItemPosition]
		return newRoute == oldRoute
	}
}

class ChatMessageDiffUtil(
		private val messages: List<ChatMessage>,
		private val newMessages: List<ChatMessage>
) : DiffUtil.Callback() {
	override fun getOldListSize(): Int = messages.size

	override fun getNewListSize(): Int = newMessages.size

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return messages[oldItemPosition] == newMessages[newItemPosition]
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
			messages[oldItemPosition].content == newMessages[newItemPosition].content

}