package com.kkgmdevelopments.traveljournalapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Settings Activity
 *  This is to present the activity related to the App Settings
 * @TODO: Fill with appropriate information based on the
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * Create Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    /**
     * Setting Preference Fragment Subclass
     *  Provides reference to the XML View
     */
    public static class SettingsFragment extends PreferenceFragmentCompat {
        /**
         * Set the Settings View
         *
         * @param savedInstanceState
         * @param rootKey
         */
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
    }
}