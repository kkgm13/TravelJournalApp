package com.kkgmdevelopments.traveljournalapp.places;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kkgmdevelopments.traveljournalapp.R;

import java.util.List;

public class VisitedPlaceListAdapter extends RecyclerView.Adapter<VisitedPlaceListAdapter.VisitedPlaceViewHolder> {

    private final LayoutInflater mInflater;
    private List<VisitedPlace> mPlaces;
    private Context mContext;
    public static final String EXTRA_REPLY = "com.kkgmdevelopments.traveljournalapp.extra.REPLY";

    public VisitedPlaceListAdapter(Context context){
//        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Create a new View
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public VisitedPlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.card_item, parent, false);
        return new VisitedPlaceViewHolder(itemView);
    }

    /**
     * Bind the information to the View
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull VisitedPlaceViewHolder holder, int position) {
        VisitedPlace place = mPlaces.get(position);
        holder.bindTo(place);
    }

    /**
     * Get Visited Places Size
     *
     * @return Amount of Visited Places
     */
    @Override
    public int getItemCount() {
        if(mPlaces != null){
            return mPlaces.size();
        } else {
            return 0;
        }
    }

    /**
     * Set the Place on the list and notify DataSet alterations
     * @param mPlaces
     */
    public void setPlaces(List<VisitedPlace> mPlaces) {
        this.mPlaces = mPlaces;
        notifyDataSetChanged();
    }

    /**
     * Visited Place View Holder
     *  This is the Holder information system for the Visited Place View
     */
    class VisitedPlaceViewHolder extends RecyclerView.ViewHolder{

        private final TextView placesItemName;         // Name Item View of Visited Place
        private final TextView placesItemNote;         // Notes Item View of Visited Place
        private final TextView placesItemLastUpdated;  // Last Updated Item View of Visited Place

        public VisitedPlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            placesItemName = itemView.findViewById(R.id.cardItemName);
            placesItemNote = itemView.findViewById(R.id.cardItemNotes);
            placesItemLastUpdated = itemView.findViewById(R.id.cardItemLastUpdated);
        }

        void bindTo(VisitedPlace place){
            placesItemName.setText(place.getPlaceName());
            if(place.getPlaceNotes() != null || place.getPlaceNotes() != ""){
                placesItemNote.setText(place.getPlaceNotes());
            } else {
                placesItemNote.setText(R.string.no_notes);
            }
        }
    }
}
