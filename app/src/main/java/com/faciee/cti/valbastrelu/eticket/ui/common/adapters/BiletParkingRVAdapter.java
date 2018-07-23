package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.RowBiletPBinding;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;

import java.util.List;

public class BiletParkingRVAdapter extends RecyclerView.Adapter<BiletParkingRVAdapter.BiletParkingViewHolder>{
	private static final String TAG = "BiletParkingRVAdapter";
	
	List<BiletP> bilete;
	
	public void setBilete(List<BiletP> biletep){
		if (bilete == null){
			bilete = biletep;
			notifyItemRangeChanged(0, bilete.size());
		}else {
			DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
				@Override
				public int getOldListSize() {
					return bilete.size();
				}
				
				@Override
				public int getNewListSize() {
					return biletep.size();
				}
				
				@Override
				public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
					return bilete.get(oldItemPosition).getIdbiletp() ==
							biletep.get(newItemPosition).getIdbiletp();
				}
				
				@Override
				public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
					BiletP newBilet = biletep.get(newItemPosition);
					BiletP oldBilet = bilete.get(oldItemPosition);
					return newBilet.equals(oldBilet);
				}
			});
			bilete = biletep;
			result.dispatchUpdatesTo(this);
		}
	}
	@Override
	public void onBindViewHolder(BiletParkingViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		holder.rowBiletPBinding.setBilet(bilete.get(position));
		holder.rowBiletPBinding.executePendingBindings();
	}
	
	@NonNull
	@Override
	public BiletParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		RowBiletPBinding rowBiletBinding = DataBindingUtil.
				inflate(LayoutInflater.from(parent.getContext()), R.layout.row_bilet_p, parent, false);
		//TODO  rowBiletBinding.setCallBack(something)
		// set variable callback in layout
		// create callback object here, initialize in contructor
		return new BiletParkingViewHolder(rowBiletBinding);
	}
	
	@Override
	public int getItemCount() {
		return (bilete == null ? 0 : bilete.size());
	}
	
	class BiletParkingViewHolder extends RecyclerView.ViewHolder{
		
		final RowBiletPBinding rowBiletPBinding;
		
		public BiletParkingViewHolder(RowBiletPBinding rowBiletPBinding) {
			super(rowBiletPBinding.getRoot());
			this.rowBiletPBinding = rowBiletPBinding;
		}
	}
}
