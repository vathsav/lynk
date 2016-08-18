package com.vathsav.lynk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vathsav.lynk.R;
import com.vathsav.lynk.utils.Constants;
import com.vathsav.lynk.utils.Credentials;
import com.vathsav.lynk.utils.Utils;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudException;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.utils.Async;
import io.particle.android.sdk.utils.Toaster;

public class SplashActivity extends AppCompatActivity {

    private Async.AsyncApiWorker<ParticleCloud, Void> loginTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ParticleCloudSDK.init(getApplicationContext());
        Utils.sparkCloud = ParticleCloudSDK.getCloud();

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar_splash);
        final TextView textView = (TextView) findViewById(R.id.text_view_splash_initializing);

        loginTask = Async.executeAsync(Utils.sparkCloud, new Async.ApiWork<ParticleCloud, Void>() {
            @Override
            public Void callApi(ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                try {
                    particleCloud.logIn(Credentials.particleEmail, Credentials.particlePassword);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);
                        }
                    });

                    if (particleCloud.getDevices().contains(particleCloud.getDevice(Constants.particleRuthlessDynamite))) {
                        Utils.ruthlessDynamite = particleCloud.getDevice(Constants.particleRuthlessDynamite);
                        Utils.deviceId = Utils.ruthlessDynamite.getID();
                    } else {
                        Toaster.s(SplashActivity.this, Constants.toastUnableToFindCore);
                    }
                } catch (ParticleCloudException ex) {
                    ex.printStackTrace();
                    Toaster.s(SplashActivity.this, Constants.toastInvalidLoginCredentials);
                    finish();
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

                Intent openMainActivity = new Intent(Constants.intentHome);
                startActivity(openMainActivity);
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
