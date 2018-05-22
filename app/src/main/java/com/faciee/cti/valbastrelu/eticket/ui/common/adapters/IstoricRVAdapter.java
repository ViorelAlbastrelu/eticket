package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.util.model.Tranzactie;

import java.util.ArrayList;
import java.util.List;

public class IstoricRVAdapter extends  RecyclerView.Adapter<IstoricRVAdapter.IstoricHolder>{
	private static final String TAG = "IstoricRVAdapter";
	
	private List<Tranzactie> mIstorice;
	private Context mContext;
	
	public IstoricRVAdapter(Context context, List<Tranzactie> lIstorice) {
		this.mContext = context;
		this.mIstorice = new ArrayList<>(lIstorice);
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
		String[] numeLuni = mContext.getResources().getStringArray(R.array.numeLuni);
		holder.mDataHolder.setText(
				mContext.getString(R.string.istoric_data,
				mIstorice.get(position).getData().getDate(),
				numeLuni[mIstorice.get(position).getData().getMonth()]));
		holder.mTipBiletHolder.setText(
				mContext.getString(R.string.istoric_bilet,
				mIstorice.get(position).getTransportType().getTypeName()));
		holder.mSumaHolder.setText(
				mContext.getString(R.string.istoric_pret,
				mIstorice.get(position).getSuma()));
		holder.mSumaHolder.setTextColor(mIstorice.get(position).getSuma() < 0 ? Color.RED : Color.GREEN);
	}
	
	@Override
	public int getItemCount() {
		return mIstorice.size();
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
