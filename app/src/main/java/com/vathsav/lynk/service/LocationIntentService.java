package com.vathsav.lynk.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.vathsav.lynk.R;
import com.vathsav.lynk.utils.Constants;

import java.util.concurrent.TimeUnit;

/**
 * Created by vathsav on 03/10/16.
 * Service for getting device location periodically
 */

public class LocationIntentService extends IntentService
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;

    public LocationIntentService() {
        super(LocationIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geoFenceEvent = GeofencingEvent.fromIntent(intent);
        if (geoFenceEvent.hasError()) {
            int errorCode = geoFenceEvent.getErrorCode();
            Log.e(Constants.tagLocationIntentServiceError, "" + errorCode);
        } else {
//            int transitionType = geoFenceEvent.getGeofenceTransition();
//            if (Geofence.GEOFENCE_TRANSITION_ENTER == transitionType) {
//                // Connect to the Google Api service in preparation for sending a DataItem.
//                mGoogleApiClient.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
//                // Get the geofence id triggered. Note that only one geofence can be triggered at a
//                // time in this example, but in some cases you might want to consider the full list
//                // of geofences triggered.
//                String triggeredGeoFenceId = geoFenceEvent.getTriggeringGeofences().get(0)
//                        .getRequestId();
//                // Create a DataItem with this geofence's id. The wearable can use this to create
//                // a notification.
//                final PutDataMapRequest putDataMapRequest =
//                        PutDataMapRequest.create(GEOFENCE_DATA_ITEM_PATH);
//                putDataMapRequest.getDataMap().putString(KEY_GEOFENCE_ID, triggeredGeoFenceId);
//                putDataMapRequest.setUrgent();
//                if (mGoogleApiClient.isConnected()) {
//                    Wearable.DataApi.putDataItem(
//                            mGoogleApiClient, putDataMapRequest.asPutDataRequest()).await();
//                } else {
//                    Log.e(Constants.tagLocationIntentServiceError, "Failed to send data item: " + putDataMapRequest
//                            + " - Client disconnected from Google Play Services");
//                }
//                Toast.makeText(this, getString(R.string.entering_geofence),
//                        Toast.LENGTH_SHORT).show();
//                mGoogleApiClient.disconnect();
//            } else if (Geofence.GEOFENCE_TRANSITION_EXIT == transitionType) {
//                // Delete the data item when leaving a geofence region.
//                mGoogleApiClient.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
//                Wearable.DataApi.deleteDataItems(mGoogleApiClient, GEOFENCE_DATA_ITEM_URI).await();
//                showToast(this, R.string.exiting_geofence);
//                mGoogleApiClient.disconnect();
//            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}