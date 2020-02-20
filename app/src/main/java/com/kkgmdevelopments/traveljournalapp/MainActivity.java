package com.kkgmdevelopments.traveljournalapp;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.holiday.NewHolidayActivity;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayListAdapter;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayViewModel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

/**
 * Main Activity Class
 */
public class MainActivity extends AppCompatActivity {

    // Holiday View Model Instance
    private HolidayViewModel mHolidayViewModel;
    // Recycler View
    private RecyclerView holidayRecycler;
    // Request Codes
    public static final int NEW_HOLIDAY_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_HOLIDAY_ACTIVITY_REQUEST_CODE = 2;
    // Extra Data strings
    public static final String EXTRA_DATA_UPDATE_HOLIDAY = "extra_holiday_to_update";
    public static final String EXTRA_DATA_ID = "extra_data_id";


    /**
     * Activity Creation
     *
     * @param savedInstanceState Instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar mechanism
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Present Data to RecyclerView
        holidayRecycler = findViewById(R.id.holiday_list);
        final HolidayListAdapter adapter = new HolidayListAdapter(this);
        holidayRecycler.setAdapter(adapter);
        holidayRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Create View Modal
        mHolidayViewModel = ViewModelProviders.of(this).get(HolidayViewModel.class);
        //Get all the holidays and observe changes
        mHolidayViewModel.getAllHolidays().observe(this, new Observer<List<Holiday>>() {
            /**
             * On Changed Method
             *  If a change is known, update it
             * @param holidays
             */
            @Override
            public void onChanged(@Nullable List<Holiday> holidays) {
                // Update cached copy in the adapter
                adapter.setHolidays(holidays);
            }
        });
        // Create Touch helper
        final ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Holiday selectHol = adapter.getHolidayPosition(position);
                if(direction == ItemTouchHelper.LEFT){
                    Toast.makeText(MainActivity.this, "Deleting "+selectHol.getMHolidayName()+" Holiday and associated data", Toast.LENGTH_SHORT).show();
                    mHolidayViewModel.deleteHoliday(selectHol);
                }

                if(direction == ItemTouchHelper.RIGHT){
                    Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_LONG).show();
//                    updateHolidayActivity(selectHol);
                }
            }
        });
        helper.attachToRecyclerView(holidayRecycler);

        // Floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        // On Click Action Listener
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Floating Action Bitton Clickable Action
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                // Proceed to Holiday Create Page
                Intent intent = new Intent(getApplicationContext(), NewHolidayActivity.class);
                startActivityForResult(intent, NEW_HOLIDAY_ACTIVITY_REQUEST_CODE);
            }
        });
//        adapter.setOnItemClickListener(HolidayListAdapter.ClickListener(){
//            @Override
//            public void onItemClick(View v, int position){
//                Holiday selectHol = adapter.getHolidayPosition(position);
//                // Launch Update Holiday Activity
//            }
//        });
    }

    /**
     * Create Options Menu on the side
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Create Option Items that are selectable
     * @param item Option Menu items
     * @return ALL Option Items available
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Settings Option Item
        if (id == R.id.action_settings) {
            return true;
        }

        // Clear DB Data Option Item
        if(id == R.id.clear_data){
            // Change so that it is a Dialog Confirmation
            Toast.makeText(this, "Clearing all holiday data...",
                    Toast.LENGTH_LONG).show();
            mHolidayViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Provide information from Create Page's Status
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If the request code is Good
        if (requestCode == NEW_HOLIDAY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            Add a new Holiday via EXTRA REPLY CODE
            Holiday holiday = (Holiday) data.getSerializableExtra("com.kkgmdevelopments.traveljournalapp.roomholiday.REPLY");
            mHolidayViewModel.insert(holiday);
//            Confirm new holiday Made
            Toast.makeText(getApplicationContext(), holiday.getMHolidayName() + " Holiday has been created.", Toast.LENGTH_LONG).show();
        } else {
//            State holiday NOT Made
            Toast.makeText(getApplicationContext(), R.string.empty_holiday_not_saved, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Activity method to use same activity layout for Editing
     * @param holiday
     */
    private void updateHolidayActivity(Holiday holiday){
        Intent intent = new Intent(this, NewHolidayActivity.class);
        intent.putExtra(EXTRA_DATA_UPDATE_HOLIDAY, holiday);
        intent.putExtra(EXTRA_DATA_ID, holiday.getMHolidayID());
        startActivityForResult(intent, UPDATE_HOLIDAY_ACTIVITY_REQUEST_CODE);
    }
}
