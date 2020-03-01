package com.kkgmdevelopments.traveljournalapp.places;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.TabPlacesFragment;

import java.text.DateFormat;
import java.util.Date;

public class VisitedPlaceDetailedActivity extends AppCompatActivity {
    private VisitedPlace vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_show);

        // Place Name
        TextView placeName = findViewById(R.id.placeName);
        TextView placeNotes = findViewById(R.id.placeNotes);
        TextView placeDate = findViewById(R.id.placesDate);

        // Create VP information
        vp = (VisitedPlace) getIntent().getSerializableExtra("selectedPlace");

        placeName.setText(vp.getPlaceName());
        placeDate.setText("Visited At: "+ DateFormat.getDateInstance().format(vp.getPlaceDate()));
        if(vp.getPlaceNotes() != null){
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
}
