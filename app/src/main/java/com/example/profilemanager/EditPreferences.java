package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class EditPreferences extends AppCompatActivity {
    private static final String TAG = "EditPreferences";

    private AudioManager myAudioManager;
    AddSchedule addSchedule = new AddSchedule();
    String busyHoursStarting;
    String busyHoursEnding;
    String currentTime;

    RadioGroup radioGroupBusy, radioGroupFree;
    RadioButton radioButtonBusy, radioButtonFree;
    TextView textView;
    TextView timeTV;
    String selectedProfile;
    String startData;
    String endData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        setContentView(R.layout.activity_edit_prefrences);

        getScheduleTimings();

        timeTV = findViewById(R.id.textClock);
        radioGroupBusy = findViewById(R.id.radioGroupBusy);
        radioGroupFree = findViewById(R.id.radioGroupFree);
        textView = findViewById(R.id.text_view_selected);

        Button submit = findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Checking if the app has DND permission. If it doesn't, ask for it.
                checkDndPermission(getApplicationContext());

                //Setting the current profile on button press.
                int radioIdbusy = radioGroupBusy.getCheckedRadioButtonId();
                int radioIdfree = radioGroupFree.getCheckedRadioButtonId();
                radioButtonBusy = findViewById(radioIdbusy);
                radioButtonFree = findViewById(radioIdfree);
                textView.setText("Your choice for busy hours: " + radioButtonBusy.getText() +
                        "\n and for non-busy hours :" + radioButtonFree.getText());

                selectedProfile = radioButtonBusy.getText().toString();
                //Profiles profile = new Profiles(selectedProfile);
                //profile.changeProfile();

                currentTime = timeTV.getText().toString();

                changeProfile();

                Toast.makeText(EditPreferences.this, "Current Time : " + timeTV.getText().toString()
                                + " \n Busy from " + busyHoursStarting + " to " + busyHoursEnding,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void checkButton(View v) {
        int radioIdBusy = radioGroupBusy.getCheckedRadioButtonId();
        radioButtonBusy = findViewById(radioIdBusy);
        Toast.makeText(this, "MODE for busy hours: " + radioButtonBusy.getText()
                , Toast.LENGTH_SHORT).show();

    }

    public void checkButton2(View v) {
        int radioIdFree = radioGroupFree.getCheckedRadioButtonId();
        radioButtonFree = findViewById(radioIdFree);
        Toast.makeText(this, "for non busy hours : " + radioButtonFree.getText(),
                Toast.LENGTH_SHORT).show();
    }

    public void changeProfile() {
        addSchedule.returnStartBusy();
        addSchedule.returnEndBusy();
        busyHoursStarting = startData;
        busyHoursEnding = endData;

        //For Busy hours-
        if (timeTV.getText().toString().equals(busyHoursStarting)
                && selectedProfile.equals("Silent")) {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } else if (timeTV.getText().toString().equals(busyHoursStarting)
                && selectedProfile.equals("Vibrate")) {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        } else if (timeTV.getText().toString().equals(busyHoursStarting)
                && selectedProfile.equals("Ringing")) {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        //For Non-Busy hours-
        if (timeTV.getText().toString().equals(busyHoursEnding)
                && selectedProfile.equals("Silent")) {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } else if (timeTV.getText().toString().equals(busyHoursEnding)
                && selectedProfile.equals("Vibrate")) {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        } else if (timeTV.getText().toString().equals(busyHoursEnding)
                && selectedProfile.equals("Ringing")) {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
    }

    //DND ACCESS
    private void checkDndPermission(Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }
    }

    private void getScheduleTimings() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            startData = "NONE";
            endData = "NONE";
        } else {
            startData = extras.getString("StartData");
            endData = extras.getString("EndData");
        }
        Log.d(TAG, "getScheduleTimings: startData = " +startData);
    }
}
