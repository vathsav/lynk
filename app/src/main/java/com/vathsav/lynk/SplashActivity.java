package com.vathsav.lynk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vathsav.lynk.utils.Utils;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.utils.Async;

public class SplashActivity extends AppCompatActivity {

    private Async.AsyncApiWorker<ParticleCloud, Void> loginTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ParticleCloudSDK.init(getApplicationContext());
        Utils.sparkCloud = ParticleCloudSDK.getCloud();

        Button buttonSplashLogin = (Button) findViewById(R.id.button_splash_login);
        buttonSplashLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginTask = Async.executeAsync(Utils.sparkCloud, new Async.ApiWork<ParticleCloud, Void>() {
                    @Override
                    public Void callApi(ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                        particleCloud.logIn("email@domain.com", "password");

                        if (particleCloud.getDevices().contains(particleCloud.getDevice("ruthless_dynamite"))) {
                            Utils.ruthlessDynamite = particleCloud.getDevice("ruthless_dynamite");
                            Utils.deviceId = Utils.ruthlessDynamite.getID();
                            Log.v("Device id:", Utils.deviceId);
                        } else {
                            Toast.makeText(getApplicationContext(), "Unable to find Core.", Toast.LENGTH_SHORT).show();
                        }

                        return null;
                    }

                    @Override
                    public void onTaskFinished() {
                        loginTask = null;
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        // TODO: 15/08/16 Handle token expiration D/ParticleAccessToken: Scheduling token expiration for Sun Nov 13 19:23:38 GMT+05:30 2016 (7776000000ms.
                        if (isFinishing()) {
                            return;
                        }

                        Intent openMainActivity = new Intent("com.vathsav.lynk.MAIN");
                        startActivity(openMainActivity);
                    }

                    @Override
                    public void onFailure(ParticleCloudException exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
