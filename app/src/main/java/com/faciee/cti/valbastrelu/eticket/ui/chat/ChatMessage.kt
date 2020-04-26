package com.faciee.cti.valbastrelu.eticket.ui.chat

class ChatMessage(var content: String, var isMine: Boolean, var isImage: Boolean) {

	override fun equals(other: Any?): Boolean {
		if (other == null) return false
		if (other === this) return true
		if (other !is ChatMessage) return false
		return content == other.content &&
				isMine == other.isImage
	}

	override fun hashCode(): Int = (41 * (41 + content.hashCode()) + isMine.hashCode())
}