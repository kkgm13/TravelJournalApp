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
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlaceActivity;

import java.util.List;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.HolidayViewHolder> {

    private final LayoutInflater mInflater; // Layout Inflater
    private List<Holiday> mHolidays;        // Cached Copy of Holidays
    private Context mContext;               // Context
    public static final String EXTRA_REPLY = "com.kkgmdevelopments.traveljournalapp.extra.REPLY";

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
        View itemView = mInflater.inflate(R.layout.card_item, parent, false);
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
    public void onBindViewHolder(HolidayListAdapter.HolidayViewHolder holder, int position) {
        Holiday holiday = mHolidays.get(position);
        holder.bindTo(holiday);
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
     * Get the Holiday position in the List
     * @param position Current list position
     * @return Holiday Position in list
     */
    public Holiday getHolidayPosition(int position){
        return mHolidays.get(position);
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

    /**
     * Holiday View Holder
     *  This is the Holder information system for the Holiday View
     */
    class HolidayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView holidayItemNameView;         // Name Item View of Holiday
        private final TextView holidayItemNoteView;         // Notes Item View of Holiday
        private final TextView holidayItemLastUpdated;      // Last Updated Item View of Holiday

        /**
         * Constructor
         *
         * @param itemView Relation to the view
         */
        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            holidayItemNameView = itemView.findViewById(R.id.cardItemName);
            holidayItemNoteView = itemView.findViewById(R.id.cardItemNotes);
            holidayItemLastUpdated = itemView.findViewById(R.id.cardItemLastUpdated);
            itemView.setOnClickListener(this);
        }

        /**
         * Click listener action
         * @param v
         */
        @Override
        public void onClick(View v) {
            Holiday selectedHoliday = mHolidays.get(getAdapterPosition());
            Intent detailedIntent = new Intent(mContext, VisitedPlaceActivity.class);
            detailedIntent.putExtra("holiday", selectedHoliday);
            detailedIntent.putExtra(EXTRA_REPLY, selectedHoliday.getMHolidayName());
            mContext.startActivity(detailedIntent);
        }

        /**
         * Bind Data to the View Entities
         * @param holiday
         */
        void bindTo(Holiday holiday){
            holidayItemNameView.setText(holiday.getMHolidayName());
            if(holiday.getMHolidayNotes() != null || holiday.getMHolidayNotes() != ""){
                holidayItemNoteView.setText(holiday.getMHolidayNotes());
            } else{
                holidayItemNoteView.setText(R.string.no_notes);
            }
        }

    }
}
