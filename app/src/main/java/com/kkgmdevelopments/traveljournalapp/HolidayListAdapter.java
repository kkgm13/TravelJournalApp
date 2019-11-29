package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.HolidayViewHolder> {

    /**
     * Holiday View Holder
     */
    class HolidayViewHolder extends RecyclerView.ViewHolder{

        private final TextView holidayItemView; // Item View of Holiday

        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            holidayItemView = itemView.findViewById(R.id.holiday);
        }
    }

    private final LayoutInflater mInflater;
    private List<Holiday> mHolidays; // Cached Copy of Holidays

    HolidayListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public HolidayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.holiday_item, parent, false);
        return new HolidayViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HolidayViewHolder holder, int position) {
        Holiday current = mHolidays.get(position);
        holder.holidayItemView.setText(current.getMHolidayName());
    }

    /**
     * Set the Holidays
     *
     * @param holidays Holiday List
     */
    void setHolidays(List<Holiday> holidays){
        mHolidays = holidays;
        notifyDataSetChanged(); // Allow to notify the Database Manipulations
    }

    /**
     * Get the Holiday Item Size
     * @return Holiday Size
     */
    @Override
    public int getItemCount() {
        if (mHolidays != null){
            return mHolidays.size();
        } else {
            return 0;
        }
    }
}
