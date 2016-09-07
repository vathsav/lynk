package com.vathsav.lynk.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.vathsav.lynk.R;
import com.vathsav.lynk.utils.Constants;
import com.vathsav.lynk.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        /**
         * Peripheral references' valueEventListener
         */
//        Constants.peripheralsReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot peripheralSnapshot : dataSnapshot.getChildren()) {
//                    Toast.makeText(getApplicationContext(), peripheralSnapshot., Toast.LENGTH_LONG).show();
//                    if (peripheralSnapshot.child(Constants.userOne).getValue() != null) {
//                        toggleButtonUserOneLight.setChecked(peripheralSnapshot.child(Constants.userOne).child(Constants.peripheralLight).getValue().equals(true));
//                        toggleButtonUserOneFan.setChecked(peripheralSnapshot.child(Constants.userOne).child(Constants.peripheralFan).getValue().equals(true));
//                    } else if (peripheralSnapshot.child(Constants.userTwo).getValue() != null) {
//                        toggleButtonUserTwoLight.setChecked(peripheralSnapshot.child(Constants.userTwo).child(Constants.peripheralLight).getValue().equals(true));
//                        toggleButtonUserTwoFan.setChecked(peripheralSnapshot.child(Constants.userTwo).child(Constants.peripheralFan).getValue().equals(true));
//                    } else if (peripheralSnapshot.child(Constants.userThree).getValue() != null) {
//                        toggleButtonUserThreeLight.setChecked(peripheralSnapshot.child(Constants.userThree).child(Constants.peripheralLight).getValue().equals(true));
//                        toggleButtonUserThreeFan.setChecked(peripheralSnapshot.child(Constants.userThree).child(Constants.peripheralFan).getValue().equals(true));
//                    } else if (peripheralSnapshot.child(Constants.userFour).child(Constants.peripheralFan).getValue() != null) {
//                        toggleButtonUserFourLight.setChecked(peripheralSnapshot.child(Constants.userFour).child(Constants.peripheralLight).getValue().equals(true));
//                        toggleButtonUserFourFan.setChecked(peripheralSnapshot.child(Constants.userFour).child(Constants.peripheralFan).getValue().equals(true));
//                        Toast.makeText(getApplicationContext(),
//                                peripheralSnapshot.child(Constants.userFour).child(Constants.peripheralFan).getValue().toString(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), Constants.toastCommandCancelled, Toast.LENGTH_SHORT).show();
//            }
//        });

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
}
