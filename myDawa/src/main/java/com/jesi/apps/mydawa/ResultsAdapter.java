package com.jesi.apps.mydawa;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>{
	
	private List<ResultsInfo> resultsList;
	
	public ResultsAdapter(List<ResultsInfo> resultsList) {
		this.resultsList = resultsList;
	}
	
	@Override
	public int getItemCount() {
		return resultsList.size();
	}
	
	@Override
	public ResultsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.results_card_layout, viewGroup, false);
		return new ResultsViewHolder(itemView);
	}
	
	@Override
	public void onBindViewHolder(ResultsViewHolder resultsViewHolder, int i) {
		ResultsInfo ci = resultsList.get(i);
		
		resultsViewHolder.vName.setText(ci.name);
		resultsViewHolder.vDesc.setText(ci.desc);
	}
	
	public static class ResultsViewHolder extends RecyclerView.ViewHolder {
		protected TextView vName;
		protected TextView vDesc;
		
		public ResultsViewHolder(View v){
			super(v);
			
			vName = (TextView) v.findViewById(R.id.txtresultname);
			vDesc = (TextView) v.findViewById(R.id.txtresultdesc);
		}		
	}
}