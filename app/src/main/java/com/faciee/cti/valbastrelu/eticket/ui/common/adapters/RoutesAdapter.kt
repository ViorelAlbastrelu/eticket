package com.faciee.cti.valbastrelu.eticket.ui.common.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.databinding.ItemRouteBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.ui.bus.RouteClickListener
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.RoutesAdapter.RouteViewHolder

class RoutesAdapter(private val clickCallback: RouteClickListener) : RecyclerView.Adapter<RouteViewHolder>() {

	var routes: List<Route> = listOf()
		set(value) {
			if (! value.isNullOrEmpty()) {
				field = value
				notifyDataSetChanged()
			}
		}

	fun updateRoutes(newRoutes: List<Route>) {
		val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
			override fun getOldListSize(): Int {
				return routes.size
			}

			override fun getNewListSize(): Int {
				return newRoutes.size
			}

			override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
				return routes[oldItemPosition].number ==
						newRoutes[newItemPosition].number
			}

			override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
				val newRoute = newRoutes[newItemPosition]
				val oldRoute = routes[oldItemPosition]
				return newRoute == oldRoute
			}
		})
		routes = newRoutes
		result.dispatchUpdatesTo(this)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
		val routeBinding: ItemRouteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_route, parent, false)
		routeBinding.callback = clickCallback
		return RouteViewHolder(routeBinding)
	}

	override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
		Log.d(TAG, "onBindViewHolder: called.")
		holder.routeBinding.route = routes[position]
		holder.routeBinding.executePendingBindings()
	}

	override fun getItemCount(): Int = routes.size

	inner class RouteViewHolder(val routeBinding: ItemRouteBinding) : RecyclerView.ViewHolder(routeBinding.root)

	companion object {
		private const val TAG = "RoutesAdapter"
	}
}