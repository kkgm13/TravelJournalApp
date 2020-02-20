package com.kkgmdevelopments.traveljournalapp.holiday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.kkgmdevelopments.traveljournalapp.MainActivity;
import com.kkgmdevelopments.traveljournalapp.R;

import java.util.Calendar;

public class NewHolidayActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY";

    private EditText mHolidayName;        // Text Input Holiday Name

    private TextView mHolidayStartDateField;   // Text Holiday Start Date
    private DatePickerDialog.OnDateSetListener mHolidayStartDate;   // Date Picker Dialog Listener Start Date
    private DatePickerDialog startDialog;       // Date Picker Dialog Starting

    private TextView mHolidayEndDateField;     // Text Holiday End Date
    private DatePickerDialog.OnDateSetListener mHolidayEndDate;     // Date Picker Dialog Listener End Date
    private DatePickerDialog endDialog;         // Date Picker Dialog Ending

    private EditText mHolidayNotes;       // Text Input Holiday Notes

    /**
     * Creation Instance
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Layout View
        setContentView(R.layout.holiday_create);

        // Set Info based from UI ids
        mHolidayName = findViewById(R.id.holiday_create_name);
        mHolidayStartDateField = findViewById(R.id.holiday_start_date);
        mHolidayEndDateField = findViewById(R.id.holiday_end_date);
        mHolidayNotes = findViewById(R.id.holiday_notes);

        // Edit Section
        final Bundle extras = getIntent().getExtras();

        // IF bundle has something, it is a holiday edit
        if(extras != null){
            Holiday holiday = new Holiday();
//                    extras.getString(MainActivity.EXTRA_DATA_UPDATE_HOLIDAY, "");
            if(holiday != null){
                mHolidayName.setText(holiday.getMHolidayName());
//                mHolidayStartDateField.setText(holiday);
                mHolidayNotes.setText(holiday.getMHolidayNotes());
            }
            // Set Action Bar Info
            getSupportActionBar().setTitle("Edit "+ holiday.getMHolidayName()+" Holiday"); // Override Action Bar title
        } else {
            // Set Action Bar Info
            getSupportActionBar().setTitle(R.string.holiday_create_title); // Override Action Bar title
        }

        /////////////////////////////////////////

        // Open Calendar Dialog for Start Date
        mHolidayStartDateField.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                startDialog = new DatePickerDialog(NewHolidayActivity.this, R.style.DialogTheme, mHolidayStartDate ,year, month, day);
                startDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                startDialog.show();
            }
        });

        // Convert Selected Date for Start Date
        mHolidayStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mHolidayStartDateField.setText(date);
            }
        };

        // Open Calendar Dialog for End Dates
        mHolidayEndDateField.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                endDialog = new DatePickerDialog(NewHolidayActivity.this, R.style.DialogTheme, mHolidayEndDate ,year, month, day);
                endDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//                endDialog.getDatePicker().setMinDate(startDialog.getDatePicker().);
                endDialog.show();
            }
        });
        // Convert Selected Date for Start Date
        mHolidayEndDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mHolidayEndDateField.setText(date);
            }
        };

        //////////////////////////

        //Save button
        final Button button = findViewById(R.id.holiday_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KKGM", mHolidayEndDate.toString() );
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mHolidayName.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
//                else if(startDialog.getDatePicker(). <= endDialog.getDatePicker()) {
//
//                }
                else {
                    Holiday holiday = new Holiday(
                            mHolidayName.getText().toString(),
                            mHolidayNotes.getText().toString()
                    );
                    replyIntent.putExtra(EXTRA_REPLY, holiday);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
