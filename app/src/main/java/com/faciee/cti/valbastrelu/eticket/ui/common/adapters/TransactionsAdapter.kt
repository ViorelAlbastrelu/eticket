package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.databinding.ItemTransactionBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TransactionsAdapter.TransactionViewHolder

class TransactionsAdapter : RecyclerView.Adapter<TransactionViewHolder>() {

	var transactions: List<Transaction> = listOf()
		set(value) {
			if (! value.isNullOrEmpty()) {
				field = value
				notifyDataSetChanged()
			}
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder =
			TransactionViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_transaction, parent, false))

	override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
		holder.transactionBinding.transaction = transactions[position]
	}

	override fun getItemCount(): Int = transactions.size

	inner class TransactionViewHolder(val transactionBinding: ItemTransactionBinding) : RecyclerView.ViewHolder(transactionBinding.root)

	companion object {
		private const val TAG = "TransactionsAdapter"
	}

}