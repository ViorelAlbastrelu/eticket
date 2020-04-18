package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class IstoricRVAdapter extends  RecyclerView.Adapter<IstoricRVAdapter.IstoricHolder>{
	private static final String TAG = "IstoricRVAdapter";
	
	private List<Transaction> mIstorice;
	private Context mContext;
	
	public IstoricRVAdapter(Context context) {
		this.mContext = context;
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
				mIstorice.get(position).getDate().getDate(),
				numeLuni[mIstorice.get(position).getDate().getMonth()]));
		holder.mTipBiletHolder.setText(
				mContext.getString(R.string.istoric_bilet,
				mIstorice.get(position).getTransportType().getTypeName()));
		holder.mSumaHolder.setText(
				mContext.getString(R.string.istoric_pret,
				mIstorice.get(position).getAmount()));
		holder.mSumaHolder.setTextColor(mIstorice.get(position).getAmount().compareTo(BigDecimal.ZERO) < 0 ? Color.RED : Color.GREEN);
	}
	
	public void setIstoric(List<Transaction> tranzactii){
		mIstorice = tranzactii;
		notifyDataSetChanged();
	}
	
	@Override
	public int getItemCount() {
		return (mIstorice != null ? mIstorice.size() : 0);
	}
	
	class IstoricHolder extends RecyclerView.ViewHolder{
		TextView mDataHolder;
		TextView mTipBiletHolder;
		TextView mSumaHolder;
		
		IstoricHolder(View itemView) {
			super(itemView);
			mDataHolder = itemView.findViewById(R.id.istoricData);
			mTipBiletHolder = itemView.findViewById(R.id.istoricBiletInfo);
			mSumaHolder = itemView.findViewById(R.id.istoricSuma);
		}
	}
}
