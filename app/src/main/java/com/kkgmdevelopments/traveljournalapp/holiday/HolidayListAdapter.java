package com.kkgmdevelopments.traveljournalapp.holiday;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.places.PlaceActivity;

import java.util.List;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.HolidayViewHolder> {

    /**
     * Holiday View Holder
     *  This is the Holder information system for the Holiday View
     */
    class HolidayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView holidayItemNameView; // Name Item View of Holiday
        private final TextView holidayItemNoteView; // Notes Item View of Holiday
        private final TextView holidayItemStartDateView; // Notes Item View of Holiday

        /**
         * Constructor
         *
         * @param itemView Relation to the view
         */
        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            holidayItemNameView = itemView.findViewById(R.id.holidayName);
            holidayItemNoteView = itemView.findViewById(R.id.holidayNotes);
            holidayItemStartDateView = itemView.findViewById(R.id.holiday_start_date);
        }

        /**
         * Click listener action
         * @param v
         */
        @Override
        public void onClick(View v) {
            Holiday selectedHoliday = mHolidays.get(getAdapterPosition());
            Intent detailedIntent = new Intent(mContext, PlaceActivity.class);
            detailedIntent.putExtra("holiday", selectedHoliday);
            mContext.startActivity(detailedIntent);
        }
    }

    private final LayoutInflater mInflater; // Layout Inflater
    private List<Holiday> mHolidays;        // Cached Copy of Holidays
    private Context mContext;               // Context

    public HolidayListAdapter(Context context) {
        this.mContext = context;
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
        if(current.getMHolidayNotes() != null || current.getMHolidayNotes() != ""){
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
     * Get the Holiday Database Size
     *
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
