package com.faciee.cti.valbastrelu.eticket.ui.common.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Bilet;

import java.util.List;

public class BiletRVAdapter extends  RecyclerView.Adapter<BiletRVAdapter.BiletViewHolder>{
	private static final String TAG = "IstoricRVAdapter";
	
	private List<Bilet> mBilete;
	
	@NonNull
	@Override
	public BiletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus_fragment_bilet, parent, false);
		return new BiletViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(BiletViewHolder holder, int position) {
		Log.d(TAG, "onBindViewHolder: called.");
		if (mBilete != null){
			Bilet biletCurent = mBilete.get(position);
			boolean activ = biletCurent.isActiv();
			holder.mTraseu.setText((String.valueOf(biletCurent.getTraseu())));
			holder.mStatus.setText(activ ?
					ETicketApp.getStringResource(R.string.status_bilet_activ) :
					ETicketApp.getStringResource(R.string.status_bilet_expirat));
			holder.setColorForStatus(activ);
			holder.mCalatorii.setText(ETicketApp.getCurrentETicketApp().getString(
					R.string.nr_calatorii,biletCurent.getCalatorii(),biletCurent.getPret()));
			holder.mSeria.setText(ETicketApp.getCurrentETicketApp().getString(
					R.string.serie_bilet,biletCurent.getIdbilet()));
		}else{
			holder.mTraseu.setText(0);
			holder.mStatus.setText(R.string.zero_bilete);
			holder.mCalatorii.setText(ETicketApp.getCurrentETicketApp().getString(
					R.string.nr_calatorii,0,0));
		}
	}
	
	public void setBilete(List<Bilet> bilete){
		mBilete = bilete;
		notifyDataSetChanged();
	}
	
	@Override
	public int getItemCount() {
		return (mBilete != null ? mBilete.size() : 0);
	}
	
	class BiletViewHolder extends RecyclerView.ViewHolder{
		TextView mTraseu, mStatus, mOra, mData, mCalatorii, mSeria;
		
		BiletViewHolder(View itemView) {
			super(itemView);
			mTraseu = itemView.findViewById(R.id.traseu_bilet);
			mStatus = itemView.findViewById(R.id.status_bilet);
			mOra = itemView.findViewById(R.id.ora_bilet);
			mData = itemView.findViewById(R.id.data_bilet);
			mCalatorii = itemView.findViewById(R.id.calatorii_bilet);
			mSeria = itemView.findViewById(R.id.serie_bilet);
		}
		
		void setColorForStatus(boolean status){
			mTraseu.setTextColor(status ? Color.GREEN : Color.RED);
			mStatus.setTextColor(status ? Color.GREEN : Color.RED);
		}
	}
}
