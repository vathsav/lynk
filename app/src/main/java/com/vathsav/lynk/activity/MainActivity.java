package com.vathsav.lynk.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.vathsav.lynk.R;
import com.vathsav.lynk.service.LocationIntentService;
import com.vathsav.lynk.utils.Constants;
import com.vathsav.lynk.utils.Utils;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status> {

    private GoogleApiClient googleApiClient;
    private Geofence lynkGeofence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: 03/10/16 Use the device's location


        final ToggleButton toggleButtonUserOneLight = (ToggleButton) findViewById(R.id.toggle_button_user_one_light);
        final ToggleButton toggleButtonUserOneFan = (ToggleButton) findViewById(R.id.toggle_button_user_one_fan);
        final ToggleButton toggleButtonUserTwoLight = (ToggleButton) findViewById(R.id.toggle_button_user_two_light);
        final ToggleButton toggleButtonUserTwoFan = (ToggleButton) findViewById(R.id.toggle_button_user_two_fan);
        final ToggleButton toggleButtonUserThreeLight = (ToggleButton) findViewById(R.id.toggle_button_user_three_light);
        final ToggleButton toggleButtonUserThreeFan = (ToggleButton) findViewById(R.id.toggle_button_user_three_fan);
        final ToggleButton toggleButtonUserFourLight = (ToggleButton) findViewById(R.id.toggle_button_user_four_light);
        final ToggleButton toggleButtonUserFourFan = (ToggleButton) findViewById(R.id.toggle_button_user_four_fan);

        TextView textViewUserOne = (TextView) findViewById(R.id.text_view_user_one);
        TextView textViewUserTwo = (TextView) findViewById(R.id.text_view_user_two);
        TextView textViewUserThree = (TextView) findViewById(R.id.text_view_user_three);
        TextView textViewUserFour = (TextView) findViewById(R.id.text_view_user_four);

        textViewUserOne.setText(Constants.userOne);
        textViewUserTwo.setText(Constants.userTwo);
        textViewUserThree.setText(Constants.userThree);
        textViewUserFour.setText(Constants.userFour);

        if (!isGooglePlayServicesAvailable()) {
            finish();
            return;
        }

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        googleApiClient.connect();


        lynkGeofence = Utils.setupGeofence();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            LocationServices.GeofencingApi.addGeofences(
                    googleApiClient,
                    getGeofencingRequest(),
                    getGeofencePendingIntent()
            ).setResultCallback(this);

            return;
        }

        /**
         * Peripheral references' valueEventListeners
         */
        Constants.peripheralsReference
                .orderByChild(Constants.userFour).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot peripheralSnapshot : dataSnapshot.getChildren()) {
                    toggleButtonUserFourLight.setChecked(peripheralSnapshot.child(Constants.peripheralLight).getValue().equals(true));
                    toggleButtonUserFourFan.setChecked(peripheralSnapshot.child(Constants.peripheralFan).getValue().equals(true));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), Constants.toastCommandCancelled, Toast.LENGTH_SHORT).show();
            }
        });

        Constants.peripheralsReference
                .orderByChild(Constants.userOne).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot peripheralSnapshot : dataSnapshot.getChildren()) {
                    toggleButtonUserFourLight.setChecked(peripheralSnapshot.child(Constants.peripheralLight).getValue().equals(true));
                    toggleButtonUserFourFan.setChecked(peripheralSnapshot.child(Constants.peripheralFan).getValue().equals(true));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), Constants.toastCommandCancelled, Toast.LENGTH_SHORT).show();
            }
        });

        Constants.peripheralsReference
                .orderByChild(Constants.userTwo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot peripheralSnapshot : dataSnapshot.getChildren()) {
                    toggleButtonUserFourLight.setChecked(peripheralSnapshot.child(Constants.peripheralLight).getValue().equals(true));
                    toggleButtonUserFourFan.setChecked(peripheralSnapshot.child(Constants.peripheralFan).getValue().equals(true));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), Constants.toastCommandCancelled, Toast.LENGTH_SHORT).show();
            }
        });

        Constants.peripheralsReference
                .orderByChild(Constants.userThree).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot peripheralSnapshot : dataSnapshot.getChildren()) {
                    toggleButtonUserFourLight.setChecked(peripheralSnapshot.child(Constants.peripheralLight).getValue().equals(true));
                    toggleButtonUserFourFan.setChecked(peripheralSnapshot.child(Constants.peripheralFan).getValue().equals(true));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), Constants.toastCommandCancelled, Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * User One
         */
        toggleButtonUserOneLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userOneLight, "0", Constants.userOne,
                            Constants.peripheralLight, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userOneLight, "1", Constants.userOne,
                            Constants.peripheralLight, MainActivity.this);
            }
        });

        toggleButtonUserOneFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userOneFan, "0", Constants.userOne,
                            Constants.peripheralFan, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userOneFan, "1", Constants.userOne,
                            Constants.peripheralFan, MainActivity.this);
            }
        });

        /**
         * User Two
         */
        toggleButtonUserTwoLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userTwoLight, "0", Constants.userTwo,
                            Constants.peripheralLight, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userTwoLight, "1", Constants.userTwo,
                            Constants.peripheralLight, MainActivity.this);
            }
        });

        toggleButtonUserTwoFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userTwoFan, "0", Constants.userTwo,
                            Constants.peripheralFan, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userTwoFan, "1", Constants.userTwo,
                            Constants.peripheralFan, MainActivity.this);
            }
        });

        /**
         * User Three
         */
        toggleButtonUserThreeLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userThreeLight, "0", Constants.userThree,
                            Constants.peripheralLight, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userThreeLight, "1", Constants.userThree,
                            Constants.peripheralLight, MainActivity.this);
            }
        });

        toggleButtonUserThreeFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userThreeFan, "0", Constants.userThree,
                            Constants.peripheralFan, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userThreeFan, "1", Constants.userThree,
                            Constants.peripheralFan, MainActivity.this);
            }
        });

        /**
         * User Four
         */
        toggleButtonUserFourLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userFourLight, "0", Constants.userFour,
                            Constants.peripheralLight, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userFourLight, "1", Constants.userFour,
                            Constants.peripheralLight, MainActivity.this);
            }
        });

        toggleButtonUserFourFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userFourFan, "0", Constants.userFour,
                            Constants.peripheralFan, MainActivity.this);
                else
                    Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.userFourFan, "1", Constants.userFour,
                            Constants.peripheralFan, MainActivity.this);
            }
        });

        // Check if Particle Core is online.
        if (Utils.ruthlessDynamite.isConnected()) {
            // Update server timestamp - Will this come in handy?
            Constants.globalsReference.child(Constants.lynkGlobalsServerTimestamp).setValue(ServerValue.TIMESTAMP);

            // Show current state on UI
            Constants.peripheralsReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            // Last state of the device is store in Firebase. Just show that the device is offline on the UI.
        }

        // Get current device state from Firebase and push to Particle core in case of loss of power supply.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.supermassiveEmp(Utils.ruthlessDynamite, MainActivity.this);
            }
        });
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofence(lynkGeofence);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
//        if (mGeofencePendingIntent != null) {
//            return mGeofencePendingIntent;
//        }
        Intent intent = new Intent(this, LocationIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        return PendingIntent.getService(this, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(Constants.intentSettings);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Checks if Google Play services is available.
     *
     * @return true if it is.
     */
    private boolean isGooglePlayServicesAvailable() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == resultCode) {
//            if (Log.isLoggable(TAG, Log.DEBUG)) {
//                Log.d(TAG, "Google Play services is available.");
//            }
            return true;
        } else {
//            Log.e(TAG, "Google Play services is unavailable.");
            return false;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onResult(@NonNull Status status) {

    }
}
