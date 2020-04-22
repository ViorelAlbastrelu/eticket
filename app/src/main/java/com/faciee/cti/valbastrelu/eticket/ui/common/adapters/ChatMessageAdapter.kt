package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.ui.chat.ChatMessage

class ChatMessageAdapter(context: Context, data: List<ChatMessage>) : ArrayAdapter<ChatMessage>(context, R.layout.chatbot_item_mine_message, data) {

	override fun getViewTypeCount(): Int = 4

	override fun getItemViewType(position: Int): Int {
		val item = getItem(position)
		return if (item !!.isMine && ! item.isImage) MY_MESSAGE
				else if (! item.isMine && ! item.isImage) OTHER_MESSAGE
				else if (item.isMine && item.isImage) MY_IMAGE
				else OTHER_IMAGE
	}

	override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
		var view = convertView
		val viewType = getItemViewType(position)
		when (viewType) {
			MY_MESSAGE -> {
				view = LayoutInflater.from(context).inflate(R.layout.chatbot_item_mine_message, parent, false)
				val textView = view.findViewById<TextView>(R.id.text)
				textView.text = getItem(position) !!.content
			}
			OTHER_MESSAGE -> {
				view = LayoutInflater.from(context).inflate(R.layout.chatbot_item_bot_message, parent, false)
				val textView = view.findViewById<TextView>(R.id.text)
				textView.text = getItem(position) !!.content
			}
			MY_IMAGE -> { //convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mine_image, parent, false);
			}
			else -> { // convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_other_image, parent, false);
			}
		}
		view.findViewById<View>(R.id.chatMessageView).setOnClickListener { Toast.makeText(context, "onClick", Toast.LENGTH_LONG).show() }
		return view
	}

	companion object {
		private const val MY_MESSAGE = 0
		private const val OTHER_MESSAGE = 1
		private const val MY_IMAGE = 2
		private const val OTHER_IMAGE = 3
	}
}