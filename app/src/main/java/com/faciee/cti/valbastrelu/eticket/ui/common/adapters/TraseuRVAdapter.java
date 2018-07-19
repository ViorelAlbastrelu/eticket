package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.RowTraseuBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.TraseuClickCallback;
import com.faciee.cti.valbastrelu.eticket.room.entities.Traseu;

import java.util.List;

public class TraseuRVAdapter extends RecyclerView.Adapter<TraseuRVAdapter.TraseuViewHolder> {
	private static final String TAG = "TraseuRecyclerViewAdapt";
	
	private List<Traseu> mTrasee;
	private final TraseuClickCallback clickCallback;
	
	public TraseuRVAdapter(TraseuClickCallback clickCallback) {
		this.clickCallback = clickCallback;
	}
	
	public void setTrasee(List<Traseu> trasee) {
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
					return mTrasee.get(oldItemPosition).getNrTraseu() ==
							trasee.get(newItemPosition).getNrTraseu();
				}
				
				@Override
				public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
					Traseu newTraseu = trasee.get(newItemPosition);
					Traseu oldTraseu = mTrasee.get(oldItemPosition);
					return newTraseu.equals(oldTraseu);
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
		holder.rowTraseuBinding.setTraseu(mTrasee.get(position));
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
