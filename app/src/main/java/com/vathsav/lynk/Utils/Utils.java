package com.vathsav.lynk.utils;

import android.app.Activity;

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

    public static void pushValue(ParticleDevice particleDevice, final String functionName,
                                 final String pinNumber, final String pinStatus, final Activity context) {
        Async.executeAsync(particleDevice, new Async.ApiWork<ParticleDevice, Integer>() {
            @Override
            public Integer callApi(ParticleDevice particleDevice) throws ParticleCloudException, IOException {
                try {
                    return Utils.ruthlessDynamite.callFunction(functionName, Py.list(pinNumber, pinStatus));
                } catch (ParticleDevice.FunctionDoesNotExistException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void onSuccess(Integer value) {
                Toaster.s(context, "Return value: " + value);
            }

            @Override
            public void onFailure(ParticleCloudException exception) {

            }
        });
    }

}
