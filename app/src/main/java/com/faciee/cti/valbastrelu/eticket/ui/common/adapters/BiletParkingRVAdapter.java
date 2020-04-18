package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.RowBiletPBinding;
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking;

import java.util.List;

public class BiletParkingRVAdapter extends RecyclerView.Adapter<BiletParkingRVAdapter.BiletParkingViewHolder>{
	private static final String TAG = "BiletParkingRVAdapter";
	
	List<TicketParking> bilete;
	
	public void setBilete(List<TicketParking> biletep){
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
					return bilete.get(oldItemPosition).getId() ==
							biletep.get(newItemPosition).getId();
				}
				
				@Override
				public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
					TicketParking newBilet = biletep.get(newItemPosition);
					TicketParking oldBilet = bilete.get(oldItemPosition);
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
