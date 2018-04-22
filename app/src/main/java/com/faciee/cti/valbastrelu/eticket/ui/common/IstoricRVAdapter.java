package com.faciee.cti.valbastrelu.eticket.ui.common;

import android.content.Context;
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
	
	ArrayList<String> mData;
	ArrayList<String> mTipBilete;
	ArrayList<String> mSuma;
	Context mContext;
	
	public IstoricRVAdapter(Context mContext, ArrayList<String> mData, ArrayList<String> mTipBilete, ArrayList<String> mSuma) {
		this.mContext = mContext;
		this.mData = mData;
		this.mTipBilete = mTipBilete;
		this.mSuma = mSuma;
	}
	
	@Override
	public IstoricHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
