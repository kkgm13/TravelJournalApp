package com.kkgmdevelopments.traveljournalapp;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlaceAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabPlacesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Holiday holiday;
//    private String mParam2;

    private TabPlacesFragment.OnFragmentInteractionListener mListener;

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
    // TODO: Rename and change types and number of parameters
    public static TabPlacesFragment newInstance(Holiday holiday) {
        TabPlacesFragment fragment = new TabPlacesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, holiday);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_tab_places, container, false);

        // Get Data somehow???

        // Visited Places RecyclerView
        RecyclerView placeRecycler = v.findViewById(R.id.places_list);
        final VisitedPlaceAdapter placeAdapter = new VisitedPlaceAdapter(getActivity());
        placeRecycler.setAdapter(placeAdapter);
        placeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
