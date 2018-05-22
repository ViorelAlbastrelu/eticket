package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.util.model.Bilet;

import java.util.ArrayList;
import java.util.List;

public class BiletRVAdapter extends  RecyclerView.Adapter<BiletRVAdapter.BiletViewHolder>{
	private static final String TAG = "IstoricRVAdapter";
	
	private List<Bilet> mBilete;
	
	public BiletRVAdapter(List<Bilet> mBilete) {
		this.mBilete = new ArrayList<>(mBilete);
	}
	
	@NonNull
	@Override
	public BiletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus_fragment_bilet, parent, false);
		return new BiletViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(BiletViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		boolean activ = mBilete.get(position).isActiv();
		holder.mTraseu.setText((String.valueOf(mBilete.get(position).getTraseu())));
		holder.mStatus.setText(activ ? "Activ" : "Expirat");
		holder.setColorForStatus(activ);
		holder.mCalatorii.setText(ETicketApp.getCurrentETicketApp().getString(
				R.string.nr_calatorii,
				mBilete.get(position).getCalatorii(),
				mBilete.get(position).getPret()));
	}
	
	@Override
	public int getItemCount() {
		return mBilete != null ? mBilete.size() : 0;
	}
	
	public class BiletViewHolder extends RecyclerView.ViewHolder{
		TextView mTraseu;
		TextView mStatus;
		TextView mOra;
		TextView mData;
		TextView mCalatorii;
		
		public BiletViewHolder(View itemView) {
			super(itemView);
			mTraseu = itemView.findViewById(R.id.traseu_bilet);
			mStatus = itemView.findViewById(R.id.status_bilet);
			mOra = itemView.findViewById(R.id.ora_bilet);
			mData = itemView.findViewById(R.id.data_bilet);
			mCalatorii = itemView.findViewById(R.id.calatorii_bilet);
		}
		
		public void setColorForStatus(boolean status){
			mTraseu.setTextColor(status ? Color.GREEN : Color.RED);
			mStatus.setTextColor(status ? Color.GREEN : Color.RED);
			mOra.setTextColor(status ? Color.GREEN : Color.RED);
			mData.setTextColor(status ? Color.GREEN : Color.RED);
			mCalatorii.setTextColor(status ? Color.GREEN : Color.RED);
		}
	}
	
	public static class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
		
		private final int verticalSpaceHeight;
		
		public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
			this.verticalSpaceHeight = verticalSpaceHeight;
		}
		
		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
		                           RecyclerView.State state) {
			outRect.bottom = verticalSpaceHeight;
		}
	}
}
