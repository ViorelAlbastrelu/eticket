package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.ui.bus.FrgTb02TraseuStatii;

import java.util.ArrayList;

public class TraseuRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private static final String TAG = "TraseuRecyclerViewAdapt";
	
	private ArrayList<String> mTimp;
	private ArrayList<String> mTraseu;
	Context mContext;
	
	public TraseuRVAdapter(Context mContext, ArrayList<String> mTimp, ArrayList<String> mTraseu) {
		this.mContext = mContext;
		this.mTimp = mTimp;
		this.mTraseu = mTraseu;
	}
	
	@Override
	public TraseuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus_fragment_traseu, parent, false);
		return new TraseuViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		TraseuViewHolder viewHolder = (TraseuViewHolder) holder;
		viewHolder.timeStamp.setText(mTimp.get(position));
		viewHolder.traseu.setText(mTraseu.get(position));
		viewHolder.infoBtn.setOnClickListener(v -> {
			Toast.makeText(mContext, mTimp.get(position) + " - "+  mTraseu.get(position), Toast.LENGTH_SHORT).show();
		});
	}
	
	@Override
	public int getItemCount() {
		return mTraseu.size();
	}
	
	public class TraseuViewHolder extends RecyclerView.ViewHolder{
		TextView timeStamp;
		TextView traseu;
		Button infoBtn;
		
		public TraseuViewHolder(View itemView) {
			super(itemView);
			timeStamp = itemView.findViewById(R.id.time_stamp);
			traseu = itemView.findViewById(R.id.traseu_no);
			infoBtn = itemView.findViewById(R.id.infoBtn);
		}
	}
}
