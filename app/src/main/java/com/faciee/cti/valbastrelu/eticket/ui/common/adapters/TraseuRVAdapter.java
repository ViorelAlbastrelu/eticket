package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.content.Context;
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
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Traseu;

import java.util.List;

public class TraseuRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private static final String TAG = "TraseuRecyclerViewAdapt";
	
	private List<Traseu> mTrasee;
	private Context mContext;
	
	public TraseuRVAdapter(Context mContext) {
		this.mContext = mContext;
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
				mTrasee.get(position).getOraFormat()));
		viewHolder.traseu.setText(mContext.getString(R.string.nr_traseu_fmt, mTrasee.get(position).getNrTraseu()));
		viewHolder.infoBtn.setOnClickListener(v -> {
			Toast.makeText(mContext, mTrasee.get(position).getOraFormat() + " - "
					+  mTrasee.get(position).getNrTraseu(), Toast.LENGTH_SHORT).show();
		});
	}
	
	public void setTrasee(List<Traseu> trasee){
		mTrasee = trasee;
		notifyDataSetChanged();
	}
	
	@Override
	public int getItemCount() {
		return (mTrasee != null ? mTrasee.size() : 0);
	}
	
	public class TraseuViewHolder extends RecyclerView.ViewHolder{
		ImageView icon;
		TextView timeStamp;
		TextView traseu;
		Button infoBtn;
		
		TraseuViewHolder(View itemView) {
			super(itemView);
			icon = itemView.findViewById(R.id.traseuTypeIcon);
			timeStamp = itemView.findViewById(R.id.time_stamp);
			traseu = itemView.findViewById(R.id.traseu_no);
			infoBtn = itemView.findViewById(R.id.infoBtn);
		}
	}
}
