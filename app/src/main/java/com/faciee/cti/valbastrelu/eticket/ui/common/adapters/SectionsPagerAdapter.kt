package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*


class SectionsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
	private val mFragmentList: MutableList<Fragment> = ArrayList()
	private val mFragmentTitleList: MutableList<String> = ArrayList()

	override fun getPageTitle(position: Int): CharSequence = mFragmentTitleList[position]

	override fun getItem(position: Int): Fragment = mFragmentList[position]

	override fun getCount(): Int = mFragmentList.size

	fun addFragment(fragment: Fragment, title: String) {
		mFragmentList.add(fragment)
		mFragmentTitleList.add(title)
	}
}