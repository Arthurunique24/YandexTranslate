package com.example.arthur.yandextranslate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.HistoryViewHolder> {

    public static class HistoryViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView historyTranslatedText;
        TextView historyTextToTranslate;

        HistoryViewHolder(View itemView){
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            historyTranslatedText = (TextView) itemView.findViewById(R.id.historyTranslatedText);
            historyTextToTranslate = (TextView) itemView.findViewById(R.id.historyTextToTranslate);
        }
    }

    private List<History> historyList;

    RVAdapter(List<History> historyList){
        this.historyList = historyList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        HistoryViewHolder pvh = new HistoryViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder historyViewHolder, int position) {
        historyViewHolder.historyTranslatedText.setText(historyList.get(position).translatedText);
        historyViewHolder.historyTextToTranslate.setText(historyList.get(position).textToTranslate);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}
