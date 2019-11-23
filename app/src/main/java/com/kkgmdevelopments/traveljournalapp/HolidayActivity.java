package com.kkgmdevelopments.traveljournalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HolidayActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY";

    private EditText mEditHolidayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_create);
        // Get the
        mEditHolidayView = findViewById(R.id.holiday_create_name);

        final Button button = findViewById(R.id.holiday_save);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create new intent
                Intent replyIntent = new Intent();
                // If the Text Edit is Empty
                if(TextUtils.isEmpty(mEditHolidayView.getText())){
                    // Return the intent with a Cancelled result
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    // Get the holiday String
                    String holiday = mEditHolidayView.getText().toString();
                    // Reply to the intent with the holiday
                    replyIntent.putExtra(EXTRA_REPLY, holiday);
                    // Set the result and the intent
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
