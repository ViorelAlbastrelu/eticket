package com.faciee.cti.valbastrelu.eticket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.faciee.cti.valbastrelu.eticket.base.BaseFragment
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentHomeBinding
import com.faciee.cti.valbastrelu.eticket.util.DummyData

class HomeFragment : BaseFragment<HomeVM>(), SwipeRefreshLayout.OnRefreshListener {

	lateinit var homeBinding: FragmentHomeBinding
	lateinit var homeFeedAdapter: HomeFeedAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
		homeBinding.homeRefreshLayout.setOnRefreshListener(this)
		homeFeedAdapter = HomeFeedAdapter()

		homeBinding.homeFeedRecycler.adapter = homeFeedAdapter
		homeFeedAdapter.feedItems = DummyData.loadBilete()
		return homeBinding.root
	}

	override fun onRefresh() {
		viewModel.refreshItems()
	}
}