package com.kkgmdevelopments.traveljournalapp.places;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.images.HorizontalAdapter;
import com.kkgmdevelopments.traveljournalapp.images.Photo;
import com.kkgmdevelopments.traveljournalapp.placeimage.ImageGalleryAdapter;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImage;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImageViewModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Detailed Activity for a Visited Place
 */
public class VisitedPlaceDetailedActivity extends AppCompatActivity implements OnMapReadyCallback {
    private VisitedPlace vp;        // Visited Place Object
    private GoogleMap googleMap;    // Google Maps Object
    private LatLng placeLtLg;       // Latitude-Longitude Object
    private List<PlaceImage> mPlacePicture = new ArrayList<>();   // List of Place Images
    private PlaceImageViewModel placeImgModel;      // Place Image View Model
    private String curImgPath;      // Photo Image Path
    private HorizontalAdapter horAdapter;       // Horizonatal Image Adapter

    /**
     * Create the Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_show);

        // UI Initialization
        TextView placeName = findViewById(R.id.placeName);
        TextView placeNotes = findViewById(R.id.placeNotes);
        TextView placeDate = findViewById(R.id.placesDate);
        final TextView placeLoc = findViewById(R.id.placesLocation);
        placeImgModel = ViewModelProviders.of(this).get(PlaceImageViewModel.class);
//        ImageView placeImg = findViewById(R.id.placeImg);
//        Glide.with(this).load(getIntent().getIntExtra("image", 0)).into(placeImg);

        // Create VP information
        vp = (VisitedPlace) getIntent().getSerializableExtra("selectedPlace");

        // Initialise Places
        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(), getString(R.string.places_api));
        }
        PlacesClient placesClient = Places.createClient(this);

        // Place & Map Data
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS_COMPONENTS);
        FetchPlaceRequest request = FetchPlaceRequest.newInstance(vp.getPlaceLocation(), placeFields);
        placesClient.fetchPlace(request).addOnSuccessListener(this, new OnSuccessListener<FetchPlaceResponse>() {
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                // Get the Place based from provided PlaceID context
                Place place = fetchPlaceResponse.getPlace();
                // Set Location Information
                placeLoc.setText(place.getName() + ", "+place.getAddressComponents().asList().get(place.getAddressComponents().asList().size() - 1).getName());
                placeLtLg = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                // Set the Map camera & marker information
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(placeLtLg));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                googleMap.addMarker(new MarkerOptions().position(placeLtLg).title(vp.getPlaceName()));
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.placeMap);
        mapFragment.getMapAsync(this);

        // UI Data Initialization
        placeName.setText(vp.getPlaceName());
        placeDate.setText("Visited At: "+ DateFormat.getDateInstance().format(vp.getPlaceDate()));
        if(vp.getPlaceNotes() != ""){
            placeNotes.setText(vp.getPlaceNotes());
        } else {
            placeNotes.setText("No Additional Notes");
        }

        // Create Related Image Gallery
        horAdapter = new HorizontalAdapter(new ArrayList<Bitmap>(), getApplicationContext());
        final LinearLayoutManager hozLayMan = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recView = (RecyclerView) findViewById(R.id.imgGallery);
        recView.setLayoutManager(hozLayMan);
        recView.setAdapter(horAdapter);
        placeImgModel.getAllImages().observe(this, new Observer<List<PlaceImage>>() {
            @Override
            public void onChanged(List<PlaceImage> placeImages) {
                for(PlaceImage p : placeImages){
                    mPlacePicture.add(p);
                    curImgPath = p.getImage().getURL();
                    Bitmap b = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(curImgPath), 200, 200);
                    horAdapter.addBitmap(b);
                }
                horAdapter.notifyDataSetChanged();
            }
        });
        placeImgModel.getPlaceImages(vp.getPlaceID());

        // Sharing Button
        final Button button = findViewById(R.id.btn_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Join me in visiting "+vp.getPlaceName()+ " and we can catch up 😊");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share Visited Place with...");
                startActivity(shareIntent);
            }
        });
    }

    /**
     * Google Map View Provider
     *  This provides a Google Map object to be presented with any additional data
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
