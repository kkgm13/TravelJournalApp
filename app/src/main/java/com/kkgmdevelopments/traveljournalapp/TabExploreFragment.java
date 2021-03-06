package com.kkgmdevelopments.traveljournalapp;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;

import java.util.concurrent.Executor;


/**
 * Explore Tab Fragment
 *  One of the 3 Tabs in the Visited Place Section.
 *  This provides a full map to see interesting places nearby.
 *  TODO: Provide Map Cluster for Points of Interest based on Attractions
 */
public class TabExploreFragment extends Fragment implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    public static View view;                // Static View object
    private SupportMapFragment mapFrag;     // Google Map Fragment Support Object
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap googleMap;            // Google Map Object

    /**
     * Constructor
     */
    public TabExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of the fragment with provided parameters
     * @param holiday Holiday object
     * @return
     */
    public static TabExploreFragment newInstance(Holiday holiday) {
        TabExploreFragment fragment = new TabExploreFragment();
        Bundle args = new Bundle();
        args.putSerializable("ARG_PARAM1", holiday);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Create The Fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        getLocation(); // Check the Android Runtime permissions (Can lead to crash due to compile)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }

    /**
     * Create the View of the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            view =  inflater.inflate(R.layout.fragment_tab_explore, container, false);
            fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // If Location is null
                    if(location != null){
                        // Set the Location
                        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        // Update the Camera Map
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                    }
                }
            });
            // Set the Explore Map Fragment
            mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps_fragment);
            mapFrag.getMapAsync(this);
        } catch (InflateException e){
            Log.e("TabFragError", "Error: " + e.getMessage());
        }
        return view;
    }

    /**
     * Get the Geographical Location
     *  This provides the device with an idea of where the user is based on Location Services and GPS
     */
    private void getLocation(){
        // Allow the Permissions
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    /**
     * Permission Request Results
     *  This takes consideration with Actions against the System Permissions
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                } else {
                    Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * Set the Map Preferences once ready
     *
     * @param googleMap Google Map Reference
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMinZoomPreference(13);     // Camera Zoom Limit
        googleMap.setMyLocationEnabled(true);   // Gets location everytime
    }
}
