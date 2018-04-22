package com.faciee.cti.valbastrelu.eticket.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;

import java.util.ArrayList;

public class IstoricRVA {
	private static final String TAG = "IstoricRVA";
	
	ArrayList<String> mData;
	ArrayList<String> mTipBilete;
	ArrayList<String> mSuma;
	Context mContext;
	
	public IstoricRVA(Context mContext, ArrayList<String> mData, ArrayList<String> mTipBilete, ArrayList<String> mSuma) {
		this.mContext = mContext;
		this.mData = mData;
		this.mTipBilete = mTipBilete;
		this.mSuma = mSuma;
	}
	
	public class IstoricHolder extends RecyclerView.ViewHolder{
		TextView mData;
		TextView mTipBilet;
		TextView mSuma;
		public IstoricHolder(View itemView) {
			super(itemView);
			mData = itemView.findViewById(R.id.istoricData);
			mTipBilet= itemView.findViewById(R.id.istoricBiletInfo);
			mSuma = itemView.findViewById(R.id.istoricSuma);
		}
	}
}
