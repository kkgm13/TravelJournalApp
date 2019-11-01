package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.DiaryListHolder> {

    private final LinkedList<String> mHolidayList;
    private LayoutInflater mInflater;

    public DiaryListAdapter(Context context, LinkedList<String> holidayList){
        mInflater = LayoutInflater.from(context);
        this.mHolidayList = holidayList;
    }

    @NonNull
    @Override
    public DiaryListAdapter.DiaryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.holiday_item, parent,false);
        return new DiaryListHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListAdapter.DiaryListHolder holder, int position) {
        String current =  mHolidayList.get(position);
        holder.holidayItemView.setText(current);
    }

    @Override
    public int getItemCount() {
        return mHolidayList.size();
    }

    /**
     * Diary List Holder
     *
     * Allows context of the Information
     */
    class DiaryListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView holidayItemView;
        final DiaryListAdapter mAdapter;

        public DiaryListHolder(View itemView, DiaryListAdapter adapter){
            super(itemView);
            holidayItemView = itemView.findViewById(R.id.holiday);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(null, "Hello ", Toast.LENGTH_SHORT).show();
        }
    }
}
