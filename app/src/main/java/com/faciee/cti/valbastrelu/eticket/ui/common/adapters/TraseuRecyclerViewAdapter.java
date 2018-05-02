package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;

import java.util.ArrayList;

public class TraseuRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private static final String TAG = "TraseuRecyclerViewAdapt";
	
	private ArrayList<String> mTimp;
	private ArrayList<String> mTraseu;
	Context mContext;
	
	public TraseuRecyclerViewAdapter(Context mContext, ArrayList<String> mTimp, ArrayList<String> mTraseu) {
		this.mContext = mContext;
		this.mTimp = mTimp;
		this.mTraseu = mTraseu;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus_fragment_traseu, parent, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		ViewHolder viewHolder = (ViewHolder) holder;
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
	
	public class ViewHolder extends RecyclerView.ViewHolder{
		TextView timeStamp;
		TextView traseu;
		Button infoBtn;
		ConstraintLayout randTraseu;
		
		public ViewHolder(View itemView) {
			super(itemView);
			timeStamp = itemView.findViewById(R.id.time_stamp);
			traseu = itemView.findViewById(R.id.traseu_no);
			infoBtn = itemView.findViewById(R.id.infoBtn);
			randTraseu = itemView.findViewById(R.id.traseu_row);
		}
	}
}
