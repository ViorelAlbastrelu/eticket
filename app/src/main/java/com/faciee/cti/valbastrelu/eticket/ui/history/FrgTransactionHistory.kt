package com.faciee.cti.valbastrelu.eticket.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.faciee.cti.valbastrelu.eticket.base.BaseFragment
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentHistoryBinding
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TransactionsAdapter
import com.faciee.cti.valbastrelu.eticket.util.DummyData

/**
 * Created by valbastrelu on 09-Apr-18.
 */
class FrgTransactionHistory : BaseFragment<TransactionHistoryVM>() {

	private lateinit var historyBinding: FragmentHistoryBinding

	private lateinit var transactionsAdapter: TransactionsAdapter

	//TODO filter by date
	//TODO filter by transport number
	//TODO filter by type
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		historyBinding = FragmentHistoryBinding.inflate(inflater, container, false)
		transactionsAdapter = TransactionsAdapter()
		initRecyclerView()
		return historyBinding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		initViewModel(TransactionHistoryVM::class.java)
//		sharedBusModel !!.liveDataTranzactii !!.observe(viewLifecycleOwner, Observer { tranzactii: List<Transaction?>? -> adapter.setIstoric(tranzactii) })
	}

	private fun initRecyclerView() {
		historyBinding.historyRecycler.adapter = transactionsAdapter
		transactionsAdapter.transactions = DummyData.mockTransactionsHistory()
	}

	companion object {
		private const val TAG = "FrgTransactionHistory"
		fun newInstance() = FrgTransactionHistory()
	}
}