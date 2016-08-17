package com.vathsav.lynk.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Py;
import io.particle.android.sdk.utils.Toaster;

/**
 * Created by vathsav on 15/08/16.
 */
public class Utils {
    public static ParticleCloud sparkCloud = null;
    public static ParticleDevice ruthlessDynamite = null;
    public static String deviceId = null;

    public static void pushDigitalValue(ParticleDevice particleDevice, final String pinNumber,
                                        final String pinStatus, final String user, final String peripheral, final Activity activity) {
        Async.executeAsync(particleDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                if (particleDevice.isConnected()) {
                    try {
                        return Utils.ruthlessDynamite.callFunction(Constants.digitalWrite, Py.list(pinNumber, pinStatus));
                    } catch (ParticleDevice.FunctionDoesNotExistException e) {
                        e.printStackTrace();
                    }
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity.getApplicationContext(), Constants.toastCoreOffline, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return null;
            }

            @Override
            public void onSuccess(Integer value) {
                // Update values on Firebase!
                if (value != null)
                    if (value > 0) {
                        if (pinStatus.equals("0")) {
                            Constants.peripheralsReference
                                    .child(user)
                                    .child(peripheral)
                                    .setValue(false)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (!task.isSuccessful()) {
                                                // TODO: 16/08/16 Try again.
                                            }
                                        }
                                    });
                        } else {
                            Constants.peripheralsReference
                                    .child(user)
                                    .child(peripheral)
                                    .setValue(true)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (!task.isSuccessful()) {
                                                // TODO: 16/08/16 Try again.
                                            }
                                        }
                                    });
                        }
                    } else {
                        // TODO: 16/08/16 Throw error
                    }
                Toaster.s(activity, "Return value: " + value);
            }

            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

    // Turn off all peripherals
    public static void supermassiveEmp(ParticleDevice particleDevice, Activity activity) {
        if (particleDevice.isConnected()) {
            /** TODO: 16/08/16 Write a custom function on the Core to set a 0 to all digital and analog pins.
             * It's inefficient to push lows for each pin.
             */
//            Utils.pushDigitalValue(Utils.ruthlessDynamite, Constants.blowEmAll, "0", Constants.userOne, activity);

        }
    }

    // Fetch state from cloud when device powers on
    public static void pullSavedStateFromCore(ParticleDevice particleDevice, final Activity activity) {
        if (particleDevice.isConnected()) {
            Constants.peripheralsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot peripheralSnapshot : dataSnapshot.getChildren()) {
                        String status = peripheralSnapshot.child("something").getValue().toString();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, Constants.toastCoreOffline, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
