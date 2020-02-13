package com.kkgmdevelopments.traveljournalapp.places;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kkgmdevelopments.traveljournalapp.R;

public class NewVisitedPlaceActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY";

    private EditText mCreatePlaceName;          // Text Input Place Name
    private EditText mCreatePlacesNotes;        // Text Input Place Notes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_create);

        getSupportActionBar().setTitle("Create New Place"); // Override Action Bar title

        mCreatePlaceName = findViewById(R.id.place_create_name);
        mCreatePlacesNotes = findViewById(R.id.place_create_notes);


        final Button button = findViewById(R.id.place_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mCreatePlaceName.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    VisitedPlace vp = new VisitedPlace(
                            mCreatePlaceName.getText().toString()
                            ,mCreatePlacesNotes.getText().toString()
                    );
                    replyIntent.putExtra(EXTRA_REPLY, vp);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }
}
