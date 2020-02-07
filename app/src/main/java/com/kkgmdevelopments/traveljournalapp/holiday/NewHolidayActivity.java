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

import com.kkgmdevelopments.traveljournalapp.R;

import java.util.Calendar;

public class NewHolidayActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY";

    private EditText mCreateHolidayName;        // Text Input Holiday Name

    private TextView mCreateHolidayStartDate;   // Text Holiday Start Date
    private DatePickerDialog.OnDateSetListener mHolidayStartDate;   // Date Picker Dialog Listener Start Date

    private DatePickerDialog startDialog;       // Date Picker Dialog Starting
    private TextView mCreateHolidayEndDate;     // Text Holiday End Date
    private DatePickerDialog.OnDateSetListener mHolidayEndDate;     // Date Picker Dialog Listener End Date

    private DatePickerDialog endDialog;         // Date Picker Dialog Ending
    private EditText mCreateHolidayNotes;       // Text Input Holiday Notes

    /**
     * Creation Instance
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Layout View
        setContentView(R.layout.holiday_create);

        getSupportActionBar().setTitle(R.string.holiday_create_title); // Override Action Bar title
        // Set Info based from UI ids
        mCreateHolidayName = findViewById(R.id.holiday_create_name);
        mCreateHolidayStartDate = findViewById(R.id.holiday_start_date);
        mCreateHolidayEndDate = findViewById(R.id.holiday_end_date);
        mCreateHolidayNotes = findViewById(R.id.holiday_notes);

        // Open Calendar Dialog for Start Date
        mCreateHolidayStartDate.setOnClickListener(new View.OnClickListener(){
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
                mCreateHolidayStartDate.setText(date);
            }
        };

        // Open Calendar Dialog for End Dates
        mCreateHolidayEndDate.setOnClickListener(new View.OnClickListener(){
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
                mCreateHolidayEndDate.setText(date);
            }
        };

        //Save button
        final Button button = findViewById(R.id.holiday_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KKGM", mHolidayEndDate.toString() );
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mCreateHolidayName.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
//                else if(startDialog.getDatePicker(). <= endDialog.getDatePicker()) {
//
//                }
                else {
                    Holiday holiday = new Holiday(
                            mCreateHolidayName.getText().toString(),
                            mCreateHolidayNotes.getText().toString()
                    );
                    replyIntent.putExtra(EXTRA_REPLY, holiday);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
