package com.kkgmdevelopments.traveljournalapp.places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.TabPlacesFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewVisitedPlaceActivity extends AppCompatActivity {

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
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private EditText mPlaceNameField;           // Text Input Place Name

    private TextView mPlaceDateText;            // Text Visited Place Date
    private DatePickerDialog.OnDateSetListener mPlaceDateListener;   // Date Picker Dialog Listener Start Date
    private DatePickerDialog dateDialog;        // Date Picker Dialog Starting
    private Date mPlaceDate;                    // Place Visited Date

    private TextView mPlacesTextField;          // Place Geolocation

    private EditText mPlacesNotesField;         // Text Input Place Notes
    private VisitedPlace vpEdit;                // VisitedPlace Object

    private Location mLastLocation;
    private Button locationButton;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_create);

        mPlaceNameField = findViewById(R.id.place_name);
        mPlacesNotesField = findViewById(R.id.place_notes);
        mPlaceDateText = findViewById(R.id.place_date);
        mPlacesTextField = findViewById(R.id.mLocationText);
        int id = -1;

        locationButton = findViewById(R.id.btn_location);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });


        final Bundle extras = getIntent().getExtras();
        // If Bundle is known, Set information
        if(extras != null){
            vpEdit = (VisitedPlace) extras.getSerializable(TabPlacesFragment.EXTRA_DATA_UPDATE_PLACE);
            if(vpEdit != null){
                mPlaceNameField.setText(vpEdit.getPlaceName());
                mPlaceDateText.setText(DateFormat.getDateInstance().format(vpEdit.getPlaceDate()));
                mPlacesNotesField.setText(vpEdit.getPlaceNotes());
                mPlaceDate = vpEdit.getPlaceDate();
            }
            getSupportActionBar().setTitle("Edit " + vpEdit.getPlaceName() + " Place"); // Override Action Bar title
        } else {
            getSupportActionBar().setTitle("Create New Place"); // Override Action Bar title
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

        final Button button = findViewById(R.id.place_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                // If Nothing for Place Name
                if(TextUtils.isEmpty(mPlaceNameField.getText())){
                    // Cancel Edit
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    // Create Object
                    VisitedPlace vp = new VisitedPlace(
                            mPlaceNameField.getText().toString(),
                            mPlaceDate,
                            mPlacesNotesField.getText().toString(),
                            new Date(),
                            new Date()
                    );
                    replyIntent.putExtra(EXTRA_REPLY, vp);
                    replyIntent.putExtra(EXTRA_REPLY_NAME, mPlaceNameField.getText().toString());
                    replyIntent.putExtra(EXTRA_REPLY_NOTES, mPlacesNotesField.getText().toString());
                    replyIntent.putExtra(EXTRA_REPLY_DATE, mPlaceDate);

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

    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        mLastLocation = location;
                        mPlacesTextField.setText(
                                getString(
                                        R.string.location_text,
                                        mLastLocation.getLatitude(),
                                        mLastLocation.getLongitude()
//                                        mLastLocation.getTime()
                                )
                        );
                    }
                }
            });
        }
    }

}
