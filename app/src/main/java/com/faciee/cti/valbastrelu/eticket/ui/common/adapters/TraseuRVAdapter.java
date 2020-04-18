package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.RowTraseuBinding;
import com.faciee.cti.valbastrelu.eticket.room.entities.Route;
import com.faciee.cti.valbastrelu.eticket.ui.bus.TraseuClickCallback;

import java.util.List;

public class TraseuRVAdapter extends RecyclerView.Adapter<TraseuRVAdapter.TraseuViewHolder> {
	private static final String TAG = "TraseuRecyclerViewAdapt";
	
	private List<Route> mTrasee;
	private final TraseuClickCallback clickCallback;
	
	public TraseuRVAdapter(TraseuClickCallback clickCallback) {
		this.clickCallback = clickCallback;
	}
	
	public void setTrasee(List<Route> trasee) {
		if (mTrasee == null) {
			mTrasee = trasee;
			notifyItemRangeChanged(0, mTrasee.size());
		} else {
			DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
				@Override
				public int getOldListSize() {
					return mTrasee.size();
				}
				
				@Override
				public int getNewListSize() {
					return trasee.size();
				}
				
				@Override
				public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
					return mTrasee.get(oldItemPosition).getNumber() ==
							trasee.get(newItemPosition).getNumber();
				}
				
				@Override
				public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
					Route newRoute = trasee.get(newItemPosition);
					Route oldRoute = mTrasee.get(oldItemPosition);
					return newRoute.equals(oldRoute);
				}
			});
			mTrasee = trasee;
			result.dispatchUpdatesTo(this);
		}
	}
	
	@Override
	public TraseuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RowTraseuBinding traseuBinding = DataBindingUtil.
				inflate(LayoutInflater.from(parent.getContext()),R.layout.row_traseu, parent, false);
		traseuBinding.setCallback(clickCallback);
		return new TraseuViewHolder(traseuBinding);
	}
	
	@Override
	public void onBindViewHolder(TraseuViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		holder.rowTraseuBinding.setRoute(mTrasee.get(position));
		holder.rowTraseuBinding.executePendingBindings();
	}
	
	@Override
	public int getItemCount() {
		return (mTrasee == null ? 0: mTrasee.size());
	}
	
	class TraseuViewHolder extends RecyclerView.ViewHolder{
		
		final RowTraseuBinding rowTraseuBinding;
		
		TraseuViewHolder(RowTraseuBinding binding) {
			super(binding.getRoot());
			rowTraseuBinding = binding;
		}
	}
}
