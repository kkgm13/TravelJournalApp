package com.kkgmdevelopments.traveljournalapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.images.Photo;
import com.kkgmdevelopments.traveljournalapp.placeimage.ImageGalleryAdapter;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImage;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImageViewModel;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlace;
import java.util.ArrayList;
import java.util.List;

/**
 *  Gallery Fragment
 *  Fragment for relevant images for the associated holiday and visited place of the holidays
 */
public class TabGalleryFragment extends Fragment {

    private PlaceImageViewModel placeImageViewModel;        // Place Image Relationship View Holder
    private Holiday holiday;                                // Potential: Holiday object to get specific places
    private ImageGalleryAdapter adapter;                    // Photo Gallery List Adapter
    private VisitedPlace vp;                                // Potential: Visited Place Object
    private List<PlaceImage> placeImageList;                // List of PlaceImage Objects

    /**
     * Create the Fragment Constructor
     */
    public TabGalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabGalleryFragment.
     */
    public static TabGalleryFragment newInstance(String param1, String param2) {
        TabGalleryFragment fragment = new TabGalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create a new instance of the fragment with provided parameters
     * @param holiday Holiday object
     * @return
     */
    public static TabGalleryFragment newInstance(Holiday holiday) {
        TabGalleryFragment fragment = new TabGalleryFragment();
        Bundle args = new Bundle();
        args.putSerializable("ARG_PARAM1", holiday);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create the Instance
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    /**
     * Create the information when View is created
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        placeImageList = new ArrayList<>();
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_tab_gallery, container, false);
        placeImageViewModel = ViewModelProviders.of(this).get(PlaceImageViewModel.class);

        // Create a Recycler View that uses a Grid Layout manager that will propose the amount of images
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView recyclerView = v.findViewById(R.id.gallery_imgs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // Create Adapter to collect images
        adapter = new ImageGalleryAdapter(getContext(), placeImageList);
        placeImageViewModel.getAllImages().observe(getActivity(), new Observer<List<PlaceImage>>() {
            @Override
            public void onChanged(List<PlaceImage> placeImages) {
                for(PlaceImage img : placeImages){
                    placeImageList.add(img);
                }
                adapter.notifyDataSetChanged();
            }
        });
        placeImageViewModel.getPlaceAllImages();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        return v;
    }
}
