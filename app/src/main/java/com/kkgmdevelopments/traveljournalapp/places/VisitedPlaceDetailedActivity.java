package com.kkgmdevelopments.traveljournalapp.places;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

import com.kkgmdevelopments.traveljournalapp.R;

public class VisitedPlaceDetailedActivity extends AppCompatActivity {
    public static final String EXTRA_PLACEDATA_ID = "extra_data_id";
    public static final String EXTRA_DATA_UPDATE_PLACE = "extra_place_to_update";

    public static final int UPDATE_VISITED_PLACES_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_show);

        // Place Name
        TextView placeName = findViewById(R.id.placeName);
        TextView placeNotes = findViewById(R.id.placeNotes);

        placeName.setText(getIntent().getStringExtra("name"));

        if(getIntent().getStringExtra("notes") != null){
            placeNotes.setText(getIntent().getStringExtra("notes"));
        } else {
            placeNotes.setText("No Additional Notes");
        }

        final Button button = findViewById(R.id.btn_edit);


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
