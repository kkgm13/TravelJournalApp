package com.kkgmdevelopments.traveljournalapp.holiday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kkgmdevelopments.traveljournalapp.R;

import java.util.List;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.HolidayViewHolder> {

    /**
     * Holiday View Holder
     */
    class HolidayViewHolder extends RecyclerView.ViewHolder{

        private final TextView holidayItemNameView; // Item View of Holiday
        private final TextView holidayItemNoteView; // Item View of Holiday

        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            holidayItemNameView = itemView.findViewById(R.id.holidayName);
            holidayItemNoteView = itemView.findViewById(R.id.holidayNotes);
        }
    }

    private final LayoutInflater mInflater;
    private List<Holiday> mHolidays; // Cached Copy of Holidays

    public HolidayListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Create a new View
     *
     * @param parent
     * @param viewType
     * @return HolidayViewHolder The Holiday View Holder Class instance
     */
    @Override
    public HolidayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.holiday_item, parent, false);
        return new HolidayViewHolder(itemView);
    }

    /**
     * Bind the Database Data to View UI items
     *
     * This shows the actual data to the users
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(HolidayViewHolder holder, int position) {
        Holiday current = mHolidays.get(position);
        holder.holidayItemNameView.setText(current.getMHolidayName());
        if(current.getMHolidayNotes() != null || current.getMHolidayNotes() == ""){
            holder.holidayItemNoteView.setText(current.getMHolidayNotes());
        } else{
            holder.holidayItemNoteView.setText(R.string.no_notes);
        }
    }

    /**
     * Set the Holidays
     *
     * @param holidays Holiday List
     */
    public void setHolidays(List<Holiday> holidays){
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
