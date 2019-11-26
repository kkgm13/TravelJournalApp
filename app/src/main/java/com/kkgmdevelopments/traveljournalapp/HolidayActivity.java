package com.kkgmdevelopments.traveljournalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HolidayActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY";

    private EditText mCreateHolidayName;
    private EditText mCreateHolidayStartDate;
    private EditText mCreateHolidayEndDate;
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
