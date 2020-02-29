package com.kkgmdevelopments.traveljournalapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.places.NewVisitedPlaceActivity;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlace;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlaceListAdapter;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlaceViewModel;

import java.util.Date;
import java.util.List;


/**
 * Tab Places Fragment
 *  Fragment for All Visited Places for a Holiday
 */
public class TabPlacesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    public static final String EXTRA_PLACEDATA_ID = "extra_data_id";
    public static final String EXTRA_DATA_UPDATE_PLACE = "extra_place_to_update";
    public static final String EXTRA_PLACEDATA_CREATED = "extra_place_created";

    public VisitedPlaceViewModel mPlacesViewModel; // View Model Instance
    private Holiday holiday; // Holiday Data
    public VisitedPlaceListAdapter placeAdapter;

    public static final int NEW_VISITED_PLACES_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_VISITED_PLACES_ACTIVITY_REQUEST_CODE = 2;

    public TabPlacesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param holiday Holiday Instance
     * @return A new instance of fragment TestBlankFragment.
     */
    public static TabPlacesFragment newInstance(Holiday holiday) {
        TabPlacesFragment fragment = new TabPlacesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, holiday);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * On Create Method
     *  Create a new Fragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            holiday = (Holiday) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    /**
     * Create a New View of the Fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_places, container, false);

        // Visited Places RecyclerView
        RecyclerView placeRecycler = v.findViewById(R.id.places_list);
        placeAdapter = new VisitedPlaceListAdapter(getActivity());
        placeRecycler.setAdapter(placeAdapter);
        placeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Places View Model
        mPlacesViewModel = ViewModelProviders.of(this).get(VisitedPlaceViewModel.class);
        mPlacesViewModel.getAllPlaces().observe(this, new Observer<List<VisitedPlace>>(){
            @Override
            public void onChanged(@Nullable List<VisitedPlace> visitedPlaces) {
                // Set the Adapter as Visited Place
                placeAdapter.setPlaces(visitedPlaces);
            }
        });

        // Floating Action Button
        FloatingActionButton fab = v.findViewById(R.id.fab);
        // On Click Action Listener
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Clickable Action
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                // Proceed to Visited Place Create Page
                Intent intent = new Intent(getContext(), NewVisitedPlaceActivity.class);
                startActivityForResult(intent, NEW_VISITED_PLACES_ACTIVITY_REQUEST_CODE);
            }
        });

        final ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                VisitedPlace selectedPlace = placeAdapter.getPlaceAtPosition(position);
                if(direction == ItemTouchHelper.RIGHT){
                    Toast.makeText(getContext(), "Deleting "+selectedPlace.getPlaceName(), Toast.LENGTH_SHORT).show();
                    mPlacesViewModel.deletePlace(selectedPlace);
                }
                if(direction == ItemTouchHelper.DOWN){
                    Intent intent = new Intent(getActivity(), NewVisitedPlaceActivity.class);

                    intent.putExtra(EXTRA_DATA_UPDATE_PLACE ,selectedPlace);
                    intent.putExtra(EXTRA_PLACEDATA_ID, selectedPlace.getPlaceID());
                    intent.putExtra(EXTRA_PLACEDATA_CREATED, selectedPlace.getPlaceCreatedAt());
                    startActivityForResult(intent, UPDATE_VISITED_PLACES_ACTIVITY_REQUEST_CODE);
                }
            }
        });
        helper.attachToRecyclerView(placeRecycler);

        return v;
    }

    /**
     * Fragment Activity Result
     *  If an activity result has come back, act upon it
     *
     * @param requestCode   Request Code
     * @param resultCode    Result Code
     * @param data          Intent Data that is passed
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If Request code is true and OK
        if(requestCode == NEW_VISITED_PLACES_ACTIVITY_REQUEST_CODE && resultCode == -1){
            // Create Visited Place Data
            VisitedPlace place = (VisitedPlace) data.getSerializableExtra(NewVisitedPlaceActivity.EXTRA_REPLY);
            // Place to the RecyclerView
            mPlacesViewModel.insert(place);
            // Notify user of Place Added
            Toast.makeText(getActivity(), place.getPlaceName()+" has been created", Toast.LENGTH_LONG).show();
        } else if(requestCode == UPDATE_VISITED_PLACES_ACTIVITY_REQUEST_CODE && resultCode == -1) {
            int id = data.getIntExtra(NewVisitedPlaceActivity.EXTRA_REPLY_ID, -1);
            String ePName = data.getStringExtra(NewVisitedPlaceActivity.EXTRA_REPLY_NAME);
            String ePNotes = data.getStringExtra(NewVisitedPlaceActivity.EXTRA_REPLY_NOTES);
            Date ePDate = (Date) data.getSerializableExtra(NewVisitedPlaceActivity.EXTRA_REPLY_DATE);
            Date ePCreated = (Date) data.getSerializableExtra(NewVisitedPlaceActivity.EXTRA_REPLY_CREATED);
            if (id != -1){
                mPlacesViewModel.updatePlace(new VisitedPlace(id,
                        ePName,
                        ePDate,
                        ePNotes,
                        ePCreated,
                        new Date()
                ));
                Toast.makeText(getActivity(), "Updated "+ ePName +" Place", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Unable to update this place", Toast.LENGTH_SHORT).show();
            }
        } else {
            placeAdapter.notifyDataSetChanged();
            // Notify user that it hasn't been saved
            Toast.makeText(getActivity(), R.string.empty_place_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}
