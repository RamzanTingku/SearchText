package com.example.ramzan.searchtext;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramzan.searchtext.dataModel.Datum;

import java.util.ArrayList;


public class SearchTextAdapter extends RecyclerView.Adapter<SearchTextAdapter.SearchTextViewHolder>{


    Context context;
    private ArrayList<Datum> searchList;


    public SearchTextAdapter(Context context, ArrayList<Datum> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @Override
    public SearchTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list, parent, false);
        return new SearchTextViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTextViewHolder holder, int position) {

        final Datum datum = searchList.get(position);

        holder.searcText.setText(datum.getKey());
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class SearchTextViewHolder extends RecyclerView.ViewHolder {

        TextView searcText;

        public SearchTextViewHolder(View itemView) {
            super(itemView);

            searcText = itemView.findViewById(R.id.item_searched_text_tv);
        }
    }

}



