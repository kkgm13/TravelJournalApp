package com.kkgmdevelopments.traveljournalapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

import java.util.List;

import static android.app.Activity.RESULT_OK;



/**
 * A simple {@link Fragment} subclass.
 */
public class TabPlacesFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private VisitedPlaceViewModel mPlacesViewModel;
    private Holiday holiday;

    public static final int NEW_VISITED_PLACES_ACTIVITY_REQUEST_CODE = 1;

//    private TabPlacesFragment.OnFragmentInteractionListener mListener;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            holiday = (Holiday) getArguments().getSerializable(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_places, container, false);

        // Get Data somehow???

        // places

        // Visited Places RecyclerView
        RecyclerView placeRecycler = v.findViewById(R.id.places_list);
        final VisitedPlaceListAdapter placeAdapter = new VisitedPlaceListAdapter(getActivity());
        placeRecycler.setAdapter(placeAdapter);
        placeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPlacesViewModel = ViewModelProviders.of(this).get(VisitedPlaceViewModel.class);
        mPlacesViewModel.getAllPlaces().observe(this, new Observer<List<VisitedPlace>>(){
            @Override
            public void onChanged(@Nullable List<VisitedPlace> visitedPlaces) {
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
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == NEW_VISITED_PLACES_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            VisitedPlace place = (VisitedPlace) data.getSerializableExtra("com.kkgmdevelopments.traveljournalapp.roomPlace.REPLY");
            mPlacesViewModel.insert(place);
            Toast.makeText(getActivity(), place.getPlaceName()+" has been created and linked to this holiday", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), R.string.empty_place_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}
