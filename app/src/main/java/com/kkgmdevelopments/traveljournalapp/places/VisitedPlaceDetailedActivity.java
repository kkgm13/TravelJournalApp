package com.kkgmdevelopments.traveljournalapp.places;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkgmdevelopments.traveljournalapp.R;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;

public class VisitedPlaceDetailedActivity extends AppCompatActivity implements OnMapReadyCallback {
    private VisitedPlace vp;
    private double placeLat;
    private double placeLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_show);
        placeLat = 9.2;
        placeLng = 22.1;

        // Place Name
        TextView placeName = findViewById(R.id.placeName);
        TextView placeNotes = findViewById(R.id.placeNotes);
        TextView placeDate = findViewById(R.id.placesDate);
        final TextView placeLoc = findViewById(R.id.placesLocation);

        // Initialise Places
        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(), getString(R.string.places_api));
        }
        PlacesClient placesClient = Places.createClient(this);

        // Create VP information
        vp = (VisitedPlace) getIntent().getSerializableExtra("selectedPlace");

        // Map Data
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.ADDRESS_COMPONENTS);
        FetchPlaceRequest request = FetchPlaceRequest.newInstance(vp.getPlaceLocation(), placeFields);
        placesClient.fetchPlace(request).addOnSuccessListener(this, new OnSuccessListener<FetchPlaceResponse>() {
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                Place place = fetchPlaceResponse.getPlace();
                // Set Location Information
                placeLoc.setText(place.getName() + ", "+place.getAddressComponents().asList().get(6).getName());
                mapLatLng(place.getLatLng().latitude, place.getLatLng().longitude);
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ApiException){
                   ApiException exception = (ApiException) e;
                   int statusCode = exception.getStatusCode();
                    placeLoc.setText("Place not known to Google");
                   Log.e("tja-pAPIERR", "Code: "+statusCode+" - Place not found: " + exception.getMessage());
                }
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // UI Elements
        placeName.setText(vp.getPlaceName());
        placeDate.setText("Visited At: "+ DateFormat.getDateInstance().format(vp.getPlaceDate()));
        if(vp.getPlaceNotes() != ""){
            placeNotes.setText(vp.getPlaceNotes());
        } else {
            placeNotes.setText("No Additional Notes");
        }

        // Sharing Button
        final Button button = findViewById(R.id.btn_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Join me in visiting "+vp.getPlaceName());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share Visited Place with...");
                startActivity(shareIntent);
            }
        });
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
    }

    /**
     * Passing Lat Lng Info
     *  Passes the Place LatLng from the PlaceID search
     *  ISSUE
     *
     * @param lat Latitude Info
     * @param lng Longitude Info
     *
     */
    private void mapLatLng(double lat, double lng){
        placeLng = lng;
        placeLat = lat;
        Log.i("BEFORE", String.valueOf(placeLat));
        Log.i("BEFORE", String.valueOf(placeLng));
    }

    /**
     * Google Map View Provider
     *  This provides a Google Map object to be presented with any additional data
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.w("TestIOP", placeLat+"\n"+placeLng);
        googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(placeLat,placeLng))
            .title("Marker"));
    }
}
