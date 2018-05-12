package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.ui.bus.FrgTb02TraseuStatii;
import com.faciee.cti.valbastrelu.eticket.ui.model.Traseu;

import java.util.ArrayList;

public class TraseuRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private static final String TAG = "TraseuRecyclerViewAdapt";
	
	private ArrayList<Traseu> mTrasee;
	private Context mContext;
	
	public TraseuRVAdapter(Context mContext, ArrayList<Traseu> lTrasee) {
		this.mContext = mContext;
		this.mTrasee = lTrasee;
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
		viewHolder.icon.setImageResource(mTrasee.get(position).getTransportType().getTransportTypeIcon());
		viewHolder.timeStamp.setText(mContext.getString(R.string.ora_traseu_fmt,
				mTrasee.get(position).getOra()));
		viewHolder.traseu.setText(mContext.getString(R.string.nr_traseu_fmt, mTrasee.get(position).getNrTraseu()));
		viewHolder.infoBtn.setOnClickListener(v -> {
			Toast.makeText(mContext, mTrasee.get(position).getOra() + " - "
					+  mTrasee.get(position).getNrTraseu(), Toast.LENGTH_SHORT).show();
		});
	}
	
	@Override
	public int getItemCount() {
		return mTrasee.size();
	}
	
	public class TraseuViewHolder extends RecyclerView.ViewHolder{
		ImageView icon;
		TextView timeStamp;
		TextView traseu;
		Button infoBtn;
		
		public TraseuViewHolder(View itemView) {
			super(itemView);
			icon = itemView.findViewById(R.id.traseuTypeIcon);
			timeStamp = itemView.findViewById(R.id.time_stamp);
			traseu = itemView.findViewById(R.id.traseu_no);
			infoBtn = itemView.findViewById(R.id.infoBtn);
		}
	}
}
