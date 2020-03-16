package com.kkgmdevelopments.traveljournalapp.places;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.TabPlacesFragment;
import com.kkgmdevelopments.traveljournalapp.images.HorizontalAdapter;
import com.kkgmdevelopments.traveljournalapp.images.Photo;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImage;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImageViewModel;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewVisitedPlaceActivity extends AppCompatActivity {

    // Reply Codes
    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY";
    public static final String EXTRA_REPLY_NAME =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY_NAME";
    public static final String EXTRA_REPLY_NOTES =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY_NOTES";
    public static final String EXTRA_REPLY_DATE =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY_DATE";
    public static final String EXTRA_REPLY_ID =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY_ID";
    public static final String EXTRA_REPLY_CREATED =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY_CREATED";
    public static final String EXTRA_REPLY_LOCATION =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY_LOCATION";
    // Request Codes
    private static final int REQUEST_LOCATION_PERMISSION = 0;
    private static final int REQUEST_CAMERA_PERMISSION = 0;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final int REQUEST_TAKE_PHOTO = 2;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    // GUI Elements
    private EditText mPlaceNameField;           // Text Input Place Name
    private TextView mPlaceDateText;            // Text Visited Place Date
    private DatePickerDialog.OnDateSetListener mPlaceDateListener;   // Date Picker Dialog Listener Start Date
    private DatePickerDialog dateDialog;        // Date Picker Dialog Starting
    private Date mPlaceDate;                    // Place Visited Date
    private EditText mPlacesNotesField;         // Text Input Place Notes
    private ImageButton camButton;                   // Camera Button
    private RecyclerView horizontal_recycler;   // RecyclerView
    private HorizontalAdapter adapter;          // Gallery Adapter
    private String currentPhotoPath;            // Photo Path

    // Location Based Elements
    private PlacesClient placesClient;
    private AutocompleteSupportFragment autocompleteSupportFragment;

    // Object Elements
    private VisitedPlace vpEdit;                // VisitedPlace Object
    private Place placeLocation;                // Place Object
    private PlaceImageViewModel placeImageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_create);

        // View Information
        mPlaceNameField = findViewById(R.id.place_name);
        mPlacesNotesField = findViewById(R.id.place_notes);
        mPlaceDateText = findViewById(R.id.place_date);
        camButton = findViewById(R.id.camerabutton);
        int id = -1;

        // Initialize Places API
        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(), getString(R.string.places_api));
        }
        placesClient = Places.createClient(this);
        autocompleteSupportFragment =
                (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME));
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                placeLocation = place;
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i("TJA-pAPI", "Error Occured: " + status);
            }
        });

        // Location
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        locationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getLocation();
//            }
//        });

        // Bundle Information
        final Bundle extras = getIntent().getExtras();
        // If Bundle is known, Set information
        if(extras != null){
            vpEdit = (VisitedPlace) extras.getSerializable(TabPlacesFragment.EXTRA_DATA_UPDATE_PLACE);
            if(vpEdit != null){
                mPlaceNameField.setText(vpEdit.getPlaceName());
                mPlaceDateText.setText(DateFormat.getDateInstance().format(vpEdit.getPlaceDate()));
                mPlacesNotesField.setText(vpEdit.getPlaceNotes());
                mPlaceDate = vpEdit.getPlaceDate();
                // Place Data
                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.ADDRESS_COMPONENTS);
                FetchPlaceRequest request = FetchPlaceRequest.newInstance(vpEdit.getPlaceLocation(), placeFields);
                placesClient.fetchPlace(request).addOnSuccessListener(this, new OnSuccessListener<FetchPlaceResponse>() {
                    @Override
                    public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                        placeLocation = fetchPlaceResponse.getPlace();
//                placeLatLng = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(e instanceof ApiException){
                            ApiException exception = (ApiException) e;
                            int statusCode = exception.getStatusCode();
                            Log.e("TJAPlaceErr", "Place not found: " + exception.getMessage());
                        }
                    }
                });
            }
            getSupportActionBar().setTitle("Edit " + vpEdit.getPlaceName() + " Place"); // Override Action Bar title
            camButton.setEnabled(true);
        } else {
            getSupportActionBar().setTitle("Create New Place"); // Override Action Bar title
            camButton.setEnabled(false);
        }

        // Open Calendar Dialog for Date
        mPlaceDateText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                dateDialog = new DatePickerDialog(NewVisitedPlaceActivity.this, R.style.DialogTheme, mPlaceDateListener,year, month, day);
                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dateDialog.show();
            }
        });

        // Convert Selected Date for Date
        mPlaceDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                mPlaceDate = calendar.getTime();
                mPlaceDateText.setText(DateFormat.getDateInstance().format(mPlaceDate));
            }
        };

        // Camera section
        if(camButton.isEnabled()){
            camButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchTakePictureIntent();
                }
            });
        }

        // Gallery Information
        horizontal_recycler = findViewById(R.id.hor_img_recycler);
        adapter = new HorizontalAdapter(new ArrayList<Bitmap>(), getApplicationContext());
        placeImageViewModel = ViewModelProviders.of(this).get(PlaceImageViewModel.class);
        LinearLayoutManager horizonLayManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler.setLayoutManager(horizonLayManager);
        horizontal_recycler.setAdapter(adapter);

        placeImageViewModel.getAllImages().observe(this, new Observer<List<PlaceImage>>() {
            @Override
            public void onChanged(List<PlaceImage> placeImages) {
                for(PlaceImage img : placeImages){
                    currentPhotoPath = img.getImage().getURL();
                    Bitmap image = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(currentPhotoPath), 200, 200);
                    adapter.addBitmap(image);
                }
                adapter.notifyDataSetChanged();
            }
        });
        if(vpEdit != null){
            placeImageViewModel.getPlaceImages(vpEdit.getPlaceID());
        }

        // Saving Button
        final Button button = findViewById(R.id.place_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                // If Nothing for Place Name
                if(TextUtils.isEmpty(mPlaceNameField.getText()) || mPlaceDate == null){
                    // Cancel Edit
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String notes = "";
                    if(!mPlacesNotesField.getText().toString().isEmpty()){
                        notes = mPlacesNotesField.getText().toString();
                    }
                    // Create New Object
                    VisitedPlace vp = new VisitedPlace(mPlaceNameField.getText().toString(), placeLocation.getId(), mPlaceDate, notes, new Date(), new Date()
                    );
                    replyIntent.putExtra(EXTRA_REPLY, vp);
                    replyIntent.putExtra(EXTRA_REPLY_NAME, mPlaceNameField.getText().toString());
                    replyIntent.putExtra(EXTRA_REPLY_NOTES, notes);
                    replyIntent.putExtra(EXTRA_REPLY_DATE, mPlaceDate);
                    replyIntent.putExtra(EXTRA_REPLY_LOCATION, placeLocation.getId());

                    // If action is an Edit to Update
                    if(extras != null && extras.containsKey(TabPlacesFragment.EXTRA_PLACEDATA_ID)){
                        int id = extras.getInt(TabPlacesFragment.EXTRA_PLACEDATA_ID, -1);
                        // If extras provided an ID
                        if(id != -1){
                            replyIntent.putExtra(EXTRA_REPLY_ID, id); // Important to properly consider the intended data to update
                            replyIntent.putExtra(EXTRA_REPLY_CREATED, vpEdit.getPlaceCreatedAt());
                        }
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    /**
     * Get the Geographical Location
     *  This provides the device with an idea of where the user is based on Location Services and GPS
     */
//    private void getLocation(){
//        // Allow the Permissions
//        if (ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]
//                            {Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_LOCATION_PERMISSION);
//        } else {
//            // Start grabbing Information
//            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    if (location != null){
//                        mLastLocation = location;
//                        new FetchAddressTask(NewVisitedPlaceActivity.this, NewVisitedPlaceActivity.this).execute(location);
//                        mPlaceLocation.setText(
//                                getString(
//                                        R.string.address_text,
//                                        getString(R.string.loading),
//                                        System.currentTimeMillis()
//                                )
//                        );
//                    } else {
//                        mPlaceLocation.setText(R.string.no_location);
//                    }
//                }
//            });
//        }
//    }

//    @Override
//    public void onTaskCompleted(String result) {
//        mPlaceLocation.setText(getString(R.string.address_text,result,System.currentTimeMillis()));
//    }

    /**
     *  Activity Result Handler
     *
     * @param requestCode Request Code from Google API
     * @param resultCode  Result Code from Google API
     * @param data        Google Data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Place API Activity Result Handler
        if(requestCode == AUTOCOMPLETE_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("tja-pAPI", "Place: "+place.getName()+", "+place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR){
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("tja-pAPI", "Code "+status.getStatusCode()+": "+status.getStatusMessage());
            } else if(resultCode == RESULT_CANCELED){
                // Nothing
            }
        }
        // Camera Activity Result Handler
        else if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bitmap image = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(currentPhotoPath), 200, 200);
            // Save to Database
            Photo placeImg = new Photo(currentPhotoPath, vpEdit.getPlaceName()+" "+adapter.getItemCount());
            PlaceImage placePic = new PlaceImage(vpEdit.getPlaceID(), placeImg);
            placeImageViewModel.insertImage(placePic);
            // Keep in order for adapter to see
            adapter.addBitmap(image);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Camera Intent Handler
     */
    private void dispatchTakePictureIntent() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePicIntent.resolveActivity(getPackageManager()) != null){
                File photoFile = null;
                try{
                    photoFile = createImageFile();
                }catch (IOException ex){
                    Log.e("tja-err", ex.getMessage());
                }
                // If File is NOT null, take the picture
                if(photoFile != null){
                    Uri photoURI = FileProvider.getUriForFile(this, "com.kkgmdevelopments.traveljournalapp.fileprovider", photoFile);// ISSUE
                    takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePicIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }
    }

    /**
     * Create the Image with specific Name
     *
     * @return File Image
     * @throws IOException
     */
    private File createImageFile() throws IOException{
        String timestamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        String imageName = "JPEG_"+timestamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
