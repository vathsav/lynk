package com.vathsav.lynk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vathsav.lynk.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView textViewDeviceId = (TextView) findViewById(R.id.text_view_device_id);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.ruthlessDynamite != null) {
                    textViewDeviceId.setText(Utils.deviceId);


                }
            }
        });

        Button buttonOn = (Button) findViewById(R.id.button_on);
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.pushValue(Utils.ruthlessDynamite, "digitalwrite", "D7", "1", MainActivity.this);
            }
        });

        Button buttonOff = (Button) findViewById(R.id.button_off);
        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.pushValue(Utils.ruthlessDynamite, "digitalwrite", "D7", "0", MainActivity.this);
            }
        });
    }

}
