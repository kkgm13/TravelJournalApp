package com.kkgmdevelopments.traveljournalapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabExploreFragment extends Fragment {

    public static View view;

    public TabExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (view != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null)
//                parent.removeView(view);
//        }
        // Inflate the layout for this fragment
        try {
            view =  inflater.inflate(R.layout.fragment_tab_explore, container, false);

        } catch (InflateException e){
            Log.e("TabFragError", "Error: " + e.getMessage());
        }
        return view;
    }

}
