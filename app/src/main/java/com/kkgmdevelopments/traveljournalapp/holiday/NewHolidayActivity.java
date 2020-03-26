package com.kkgmdevelopments.traveljournalapp.holiday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

    // Intent String Information
    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY";
    public static final String EXTRA_REPLY_NAME =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_NAME";
    public static final String EXTRA_REPLY_START_DATE =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_STARTDATE";
    public static final String EXTRA_REPLY_END_DATE =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_ENDDATE";
    public static final String EXTRA_REPLY_COMPANIONS =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_COMPANIONS";
    public static final String EXTRA_REPLY_NOTES =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_NOTES";
    public static final String EXTRA_REPLY_ID =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_ID";
    public static final String EXTRA_REPLY_CREATED =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY_CREATED";

    // UI Information
    private EditText mHolidayName;              // Text Input Holiday Name
    private TextView mHolidayStartDateField;                         // Text Holiday Start Date
    private DatePickerDialog.OnDateSetListener mStartDateListener;   // Date Picker Dialog Listener Start Date
    private DatePickerDialog startDialog;       // Date Picker Dialog Starting
    private Date mHolidayStartDate;             // Holiday Start Date
    private TextView mHolidayEndDateField;     // Text Holiday End Date
    private DatePickerDialog.OnDateSetListener mEndDateListener;     // Date Picker Dialog Listener End Date
    private DatePickerDialog endDialog;         // Date Picker Dialog Ending
    private Date mHolidayEndDate;             // Holiday Start Date
    private EditText mHolidayNotes;       // Text Input Holiday Notes
    private EditText mHolidayCompanions;        // Text Input for Holiday Companions
    private Holiday eHoliday;

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
        mHolidayCompanions = findViewById(R.id.holidayCompanions);

        // Edit Section
        final Bundle extras = getIntent().getExtras();
        // IF bundle has something, it is a holiday edit, else its a new one
        if(extras != null){
            eHoliday = (Holiday) extras.getSerializable(MainActivity.EXTRA_DATA_UPDATE_HOLIDAY);
            if(eHoliday != null){
                mHolidayName.setText(eHoliday.getMHolidayName());
                mHolidayStartDateField.setText(DateFormat.getDateInstance().format(eHoliday.getMStartDate()));
                mHolidayEndDateField.setText(DateFormat.getDateInstance().format(eHoliday.getMEndDate()));
                mHolidayCompanions.setText(eHoliday.getMCompanions());
                mHolidayNotes.setText(eHoliday.getMHolidayNotes());
                mHolidayStartDate = eHoliday.getMStartDate();
                mHolidayEndDate = eHoliday.getMEndDate();
            }
            // Set Action Bar Info
            getSupportActionBar().setTitle("Edit "+ eHoliday.getMHolidayName()+" Holiday"); // Override Action Bar title
        } else {
            // Set Action Bar Info
            getSupportActionBar().setTitle(R.string.holiday_create_title); // Override Action Bar title
        }

        // Open Calendar Dialog for Start Date
        mHolidayStartDateField.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                startDialog = new DatePickerDialog(NewHolidayActivity.this, R.style.DialogTheme, mStartDateListener,year, month, day);
                startDialog.show();
            }
        });
        // Convert Selected Date for Start Date
        mStartDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                mHolidayStartDate = calendar.getTime();
                mHolidayStartDateField.setText(DateFormat.getDateInstance().format(mHolidayStartDate));
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

                endDialog = new DatePickerDialog(NewHolidayActivity.this, R.style.DialogTheme, mEndDateListener,year, month, day);
                endDialog.getDatePicker().setMinDate(mHolidayStartDate.getTime());
                endDialog.show();
            }
        });
        // Convert Selected Date for Start Date
        mEndDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                mHolidayEndDate = calendar.getTime();
                mHolidayEndDateField.setText(DateFormat.getDateInstance().format(mHolidayEndDate));
            }
        };

        // Save button
        final Button button = findViewById(R.id.holiday_save);
        // On Click Action System
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mHolidayName.getText()) || mHolidayStartDate == null || mHolidayEndDate == null){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String notes = "", companions = "";
                    if(! mHolidayNotes.getText().toString().isEmpty()){
                        notes = mHolidayNotes.getText().toString();
                    }
                    if(!mHolidayCompanions.getText().toString().isEmpty()){
                        companions = mHolidayCompanions.getText().toString();
                    } else{
                        companions = "Just me";
                    }

                    Holiday holiday = new Holiday(
                            mHolidayName.getText().toString(),
                            mHolidayStartDate, // Get Selected Start Date
                            mHolidayEndDate, // Get Selected End Date
                            companions,
                            notes,
                            new Date(),
                            new Date()
                    );
                    replyIntent.putExtra(EXTRA_REPLY, holiday);
                    replyIntent.putExtra(EXTRA_REPLY_COMPANIONS, companions);
                    replyIntent.putExtra(EXTRA_REPLY_NAME, mHolidayName.getText().toString());
                    replyIntent.putExtra(EXTRA_REPLY_NOTES, notes);
                    replyIntent.putExtra(EXTRA_REPLY_START_DATE, mHolidayStartDate);
                    replyIntent.putExtra(EXTRA_REPLY_END_DATE, mHolidayEndDate);
                    // If the Bundle had something and was known to edit by the Data ID
                    if(extras != null && extras.containsKey(MainActivity.EXTRA_DATA_ID)){
                        int id = extras.getInt(MainActivity.EXTRA_DATA_ID, -1);
                        if(id != -1){
                            // Add Information Intent
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                            replyIntent.putExtra(EXTRA_REPLY_CREATED, eHoliday.getMHolidayCreatedAt()); // java.lang.ClassCastException: java.util.Date cannot be cast to java.lang.String
                        }
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
