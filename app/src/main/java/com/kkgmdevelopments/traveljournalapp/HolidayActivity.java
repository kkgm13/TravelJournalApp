package com.kkgmdevelopments.traveljournalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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

import java.util.Calendar;

public class HolidayActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY";

    private EditText mCreateHolidayName;
    private TextView mCreateHolidayStartDate;
    private DatePickerDialog.OnDateSetListener mHolidayStartDate;
    private TextView mCreateHolidayEndDate;
    private DatePickerDialog.OnDateSetListener mHolidayEndDate;
    private EditText mCreateHolidayNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_create);

        // Set Info based from UI ids
        mCreateHolidayName = findViewById(R.id.holiday_create_name);
        mCreateHolidayStartDate = findViewById(R.id.holiday_start_date);
        mCreateHolidayEndDate = findViewById(R.id.holiday_end_date);
        mCreateHolidayNotes = findViewById(R.id.holiday_notes);

        mCreateHolidayStartDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(HolidayActivity.this, R.style.DialogTheme, mHolidayStartDate ,year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        mHolidayStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mCreateHolidayStartDate.setText(date);
            }
        };

        mCreateHolidayEndDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(HolidayActivity.this, R.style.DialogTheme, mHolidayEndDate ,year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//                dialog.getDatePicker().setMinDate();
                dialog.show();
            }
        });

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
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mCreateHolidayName.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String holiday = mCreateHolidayName.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, holiday);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
