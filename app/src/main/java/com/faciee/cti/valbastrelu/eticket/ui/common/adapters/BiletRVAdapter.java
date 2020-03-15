package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.RowBiletBinding;
import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;

import java.util.List;

public class BiletRVAdapter extends  RecyclerView.Adapter<BiletRVAdapter.BiletViewHolder>{
	private static final String TAG = "IstoricRVAdapter";
	
	private List<Bilet> mBilete;
	
	public void setBilete(List<Bilet> bilete){
		if (mBilete == null){
			mBilete = bilete;
			notifyItemRangeChanged(0, mBilete.size());
		}else {
			DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
				@Override
				public int getOldListSize() {
					return mBilete.size();
				}

				@Override
				public int getNewListSize() {
					return bilete.size();
				}

				@Override
				public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
					return mBilete.get(oldItemPosition).getIdbilet() ==
							bilete.get(newItemPosition).getIdbilet();
				}

				@Override
				public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
					Bilet newBilet = bilete.get(newItemPosition);
					Bilet oldBilet = mBilete.get(oldItemPosition);
					return newBilet.equals(oldBilet);
				}
			});
			mBilete = bilete;
			result.dispatchUpdatesTo(this);
		}
	}
	
	@Override
	public void onBindViewHolder(BiletViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		holder.biletBinding.setBilet(mBilete.get(position));
		holder.biletBinding.executePendingBindings();
	}
	
	@NonNull
	@Override
	public BiletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		RowBiletBinding rowBiletBinding = DataBindingUtil.
				inflate(LayoutInflater.from(parent.getContext()),R.layout.row_bilet, parent, false);
		//TODO  rowBiletBinding.setCallBack(something)
		// set variable callback in layout
		// create callback object here, initialize in contructor
		return new BiletViewHolder(rowBiletBinding);
	}
	
	@Override
	public int getItemCount() {
		return (mBilete == null ? 0 : mBilete.size());
	}
	
	class BiletViewHolder extends RecyclerView.ViewHolder{
		
		final RowBiletBinding biletBinding;
		
		BiletViewHolder(RowBiletBinding biletBinding) {
			super(biletBinding.getRoot());
			this.biletBinding = biletBinding;
		}
	}
}
