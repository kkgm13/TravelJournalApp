package com.kkgmdevelopments.traveljournalapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.DiaryListHolder> {

    public DiaryListAdapter(){

    }

    @NonNull
    @Override
    public DiaryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DiaryListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public DiaryListHolder(View itemView, DiaryListAdapter adapter){
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
