package com.kkgmdevelopments.traveljournalapp.places;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.kkgmdevelopments.traveljournalapp.R;

import java.util.Calendar;
import java.util.Date;

public class NewVisitedPlaceActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY";
    public static final String EXTRA_REPLY_ID =
            "com.kkgmdevelopments.traveljournalapp.roomPlaces.REPLY_ID";

    private EditText mPlaceNameField;           // Text Input Place Name

    private TextView mPlaceDateText;            // Text Visited Place Date
    private DatePickerDialog.OnDateSetListener mPlaceStartDate;   // Date Picker Dialog Listener Start Date
    private DatePickerDialog dateDialog;        // Date Picker Dialog Starting

    private EditText mPlacesNotesField;         // Text Input Place Notes
    private VisitedPlace vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visited_place_create);

        mPlaceNameField = findViewById(R.id.place_name);
        mPlacesNotesField = findViewById(R.id.place_notes);
        mPlaceDateText = findViewById(R.id.place_date);

        int id = -1;
        final Bundle extras = getIntent().getExtras();

        if(extras != null){
            VisitedPlace vpEdit = (VisitedPlace) extras.getSerializable();
            if(vpEdit != null){
                mPlaceNameField.setText(vpEdit.getPlaceName());
                mPlacesNotesField.setText(vpEdit.getPlaceNotes());
            }
            getSupportActionBar().setTitle("Edit" +vpEdit.getPlaceName()+" Place"); // Override Action Bar title
        } else {
            getSupportActionBar().setTitle("Create New Place"); // Override Action Bar title
        }

        //////////////////////////

        // Open Calendar Dialog for Start Date
        mPlaceDateText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                dateDialog = new DatePickerDialog(NewVisitedPlaceActivity.this, R.style.DialogTheme, mPlaceStartDate ,year, month, day);
                dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dateDialog.show();
            }
        });

        // Convert Selected Date for Start Date
        mPlaceStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mPlaceDateText.setText(date);
//                holiday.setMStartDate();

            }
        };

        /////////////////////////

        final Button button = findViewById(R.id.place_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mPlaceNameField.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    if(extras != null && extras.containsKey(EXTRA_DATA_ID)){
                        int id = extras.getInt(EXTRA_DATA_ID, -1);
                        if(id != -1){
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                        }
                    } else {
                        VisitedPlace vp = new VisitedPlace(
                                mPlaceNameField.getText().toString(),
                                mPlacesNotesField.getText().toString(),
                                new Date(),
                                new Date()
                        );
                    }
                    replyIntent.putExtra(EXTRA_REPLY, vp);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }
}
