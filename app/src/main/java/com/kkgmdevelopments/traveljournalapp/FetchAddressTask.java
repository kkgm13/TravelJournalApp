package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FetchAddressTask extends AsyncTask<Location, Void, String> {
    private final String TAG = FetchAddressTask.class.getSimpleName();
    private Context context;

    FetchAddressTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Location... params) {
        // Create Geocoder
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        Location location = params[0];
        // Prepare list of Addresses
        List<Address> addresses = null;
        // Have a result string
        String resultMessage = "";
        // Get the addresses
        try{
            // Get Addresses
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1
            );
            // If Nothing
            if(addresses == null || addresses.size() == 0){
                if(resultMessage.isEmpty()){
                    resultMessage = context.getString(R.string.no_address_found);
                    Log.e(TAG, resultMessage);
                }
            }
            // If something catches
            else {
                Address address = addresses.get(0);
                ArrayList<String> addressParts = new ArrayList<>();
                // Loop through each address line
                for (int i = 0; i < address.getMaxAddressLineIndex() ; i++) {
                    // Add to the list
                    addressParts.add(address.getAddressLine(i));
                }
                // Return results
                resultMessage = TextUtils.join("\n", addressParts);
            }
        } catch (IOException exception){
            // Catch I/O Issues
            resultMessage = context.getString(R.string.service_unavailable);
            Log.e(TAG, resultMessage, exception);
        } catch (IllegalArgumentException exception){
            // Catch invalid navigational data
            resultMessage = context.getString(R.string.invalid_data_geocode);
            Log.e(TAG, resultMessage +
                    ". Latitude: "+location.getLatitude() +
                    ", Longitude: "+location.getLongitude(),
                    exception);
        }
        return resultMessage;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
