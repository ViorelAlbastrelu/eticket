package com.faciee.cti.valbastrelu.eticket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.faciee.cti.valbastrelu.eticket.base.BaseFragment
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentHomeBinding
import com.faciee.cti.valbastrelu.eticket.repo.HomeRepository

class HomeFragment : BaseFragment<HomeVM>(), SwipeRefreshLayout.OnRefreshListener {

	lateinit var homeBinding: FragmentHomeBinding
	lateinit var homeFeedAdapter: HomeFeedAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
		homeBinding.homeRefreshLayout.setOnRefreshListener(this)
		initViewModel(HomeVM::class.java, HomeVM.getFactory(eTicketApp, HomeRepository(eTicketApp.database)))


		homeFeedAdapter = HomeFeedAdapter()
		homeBinding.homeFeedRecycler.adapter = homeFeedAdapter
		return homeBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.feedItems.observe(viewLifecycleOwner, Observer {
			if (homeBinding.homeRefreshLayout.isRefreshing) {
				homeBinding.homeRefreshLayout.isRefreshing = false
			}
			homeFeedAdapter.feedItems = it
		})
	}

	override fun onRefresh() {
		viewModel.refreshItems()
	}
}