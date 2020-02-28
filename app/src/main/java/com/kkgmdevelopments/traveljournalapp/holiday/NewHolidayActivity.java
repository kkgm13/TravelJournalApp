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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Holiday Form Activity
 *
 * This is the activity to create a new Holiday.
 * This also serves as editing a holiday as well.
 */
public class NewHolidayActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY";

    public static final String EXTRA_REPLY_NAME =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_NAME";
    public static final String EXTRA_REPLY_START_DATE =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_STARTDATE";
    public static final String EXTRA_REPLY_END_DATE =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_ENDDATE";
    public static final String EXTRA_REPLY_NOTES =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_NOTES";
    public static final String EXTRA_REPLY_ID =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_ID";

    private EditText mHolidayName;        // Text Input Holiday Name

    private TextView mHolidayStartDateField;   // Text Holiday Start Date
    private DatePickerDialog.OnDateSetListener mHolidayStartDate;   // Date Picker Dialog Listener Start Date
    private DatePickerDialog startDialog;       // Date Picker Dialog Starting

    private TextView mHolidayEndDateField;     // Text Holiday End Date
    private DatePickerDialog.OnDateSetListener mHolidayEndDate;     // Date Picker Dialog Listener End Date
    private DatePickerDialog endDialog;         // Date Picker Dialog Ending

    private EditText mHolidayNotes;       // Text Input Holiday Notes

    private Holiday holiday;

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

        // IF bundle has something, it is a holiday edit, else its a new one
        if(extras != null){
            Holiday holiday = (Holiday) extras.getSerializable(MainActivity.EXTRA_DATA_UPDATE_HOLIDAY);
            if(holiday != null){
                mHolidayName.setText(holiday.getMHolidayName());
                mHolidayStartDateField.setText(DateFormat.getDateInstance().format(holiday.getMStartDate()));
                mHolidayEndDateField.setText(DateFormat.getDateInstance().format(holiday.getMEndDate()));
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
//                holiday.setMStartDate();

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
                cal.set(year,month,day);

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

        // Save button
        final Button button = findViewById(R.id.holiday_save);
        // On Click Action System
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mHolidayName.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String holidayName = mHolidayName.getText().toString();
                    String holidayNotes = mHolidayNotes.getText().toString();
//                    Date startDate = ;

                    holiday = new Holiday(
                            mHolidayName.getText().toString(),
                            new Date(), // Get Selected Start Date
                            new Date(), // Get Selected End Date
                            mHolidayNotes.getText().toString(),
                            new Date(),
                            new Date()
                    );

                    replyIntent.putExtra(EXTRA_REPLY, holiday);
                    replyIntent.putExtra(EXTRA_REPLY_NAME, holidayName);
                    replyIntent.putExtra(EXTRA_REPLY_NOTES, holidayNotes);
                    // Get Date Fixed
                    replyIntent.putExtra(EXTRA_REPLY_START_DATE, new Date());
                    replyIntent.putExtra(EXTRA_REPLY_END_DATE, new Date());
                    // If the Bundle had something and was known to edit by the Data ID
                    if(extras != null && extras.containsKey(MainActivity.EXTRA_DATA_ID)){
                        int id = extras.getInt(MainActivity.EXTRA_DATA_ID, -1);
                        if(id != -1){
                            // Add Information Intent
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                        }
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
