package com.faciee.cti.valbastrelu.eticket.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.databinding.ItemMessageChatBotBinding
import com.faciee.cti.valbastrelu.eticket.databinding.ItemMessageChatMeBinding
import com.faciee.cti.valbastrelu.eticket.util.ChatMessageDiffUtil

private const val MY_MESSAGE = 0
private const val BOT_MESSAGE = 1

class ChatMessagesAdapter : RecyclerView.Adapter<MessageViewHolder>() {

	var messages: List<ChatMessage> = listOf()
		set(value) {
			if (! value.isNullOrEmpty()) {
				field = value
				notifyDataSetChanged()
			}
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
		return when (viewType) {
			MY_MESSAGE -> MessageViewHolder.MyMessageViewHolder(ItemMessageChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
			else -> MessageViewHolder.BotMessageVieHolder(ItemMessageChatBotBinding.inflate(LayoutInflater.from(parent.context), parent, false))
		}
	}

	override fun getItemViewType(position: Int): Int =
			if (messages[position].isMine) MY_MESSAGE else BOT_MESSAGE

	override fun getItemCount(): Int = messages.size

	override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
		holder.bind(messages[position])
	}

	fun add(message: ChatMessage) {
		val newMessages = arrayListOf<ChatMessage>().apply {
			addAll(messages)
			add(message)
		}
		updateMessageList(newMessages)
	}

	fun updateMessageList(newMessages: List<ChatMessage>) {
		if (newMessages.isNotEmpty()) {
			val result = DiffUtil.calculateDiff(ChatMessageDiffUtil(messages, newMessages))
			messages = newMessages
			result.dispatchUpdatesTo(this)
		}
	}

}

sealed class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
	abstract fun bind(message: ChatMessage)

	class MyMessageViewHolder(private val itemMessageChatMeBinding: ItemMessageChatMeBinding) : MessageViewHolder(itemMessageChatMeBinding.root) {
		override fun bind(message: ChatMessage) {
			itemMessageChatMeBinding.text.text = message.content
		}
	}

	class BotMessageVieHolder(private val itemMessageChatBotBinding: ItemMessageChatBotBinding) : MessageViewHolder(itemMessageChatBotBinding.root) {
		override fun bind(message: ChatMessage) {
			itemMessageChatBotBinding.text.text = message.content
		}
	}
}