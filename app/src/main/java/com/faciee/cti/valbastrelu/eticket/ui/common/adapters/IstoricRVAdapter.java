package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;

import java.util.ArrayList;

public class IstoricRVAdapter extends  RecyclerView.Adapter<IstoricRVAdapter.IstoricHolder>{
	private static final String TAG = "IstoricRVAdapter";
	
	private ArrayList<String> mData;
	private ArrayList<String> mTipBilete;
	private ArrayList<String> mSuma;
	
	public IstoricRVAdapter(ArrayList<String> mData, ArrayList<String> mTipBilete, ArrayList<String> mSuma) {
		this.mData = mData;
		this.mTipBilete = mTipBilete;
		this.mSuma = mSuma;
	}
	
	@NonNull
	@Override
	public IstoricHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_istoric, parent, false);
		return new IstoricHolder(view);
	}
	
	@Override
	public void onBindViewHolder(IstoricHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		holder.mDataHolder.setText(mData.get(position));
		holder.mTipBiletHolder.setText(mTipBilete.get(position));
		holder.mSumaHolder.setText(mSuma.get(position));
	}
	
	@Override
	public int getItemCount() {
		return mTipBilete.size();
	}
	
	public class IstoricHolder extends RecyclerView.ViewHolder{
		TextView mDataHolder;
		TextView mTipBiletHolder;
		TextView mSumaHolder;
		
		public IstoricHolder(View itemView) {
			super(itemView);
			mDataHolder = itemView.findViewById(R.id.istoricData);
			mTipBiletHolder = itemView.findViewById(R.id.istoricBiletInfo);
			mSumaHolder = itemView.findViewById(R.id.istoricSuma);
		}
	}
}
