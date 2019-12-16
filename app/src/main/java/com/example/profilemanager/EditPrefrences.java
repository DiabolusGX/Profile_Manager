package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditPrefrences extends AppCompatActivity {

    private AudioManager myAudioManager;

    RadioGroup radioGroupBusy,radioGroupFree;
    RadioButton radioButtonBusy,radioButtonFree;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        setContentView(R.layout.activity_edit_prefrences);
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
                textView.setText("Your choice for busy hours: " + radioButtonBusy.getText()+
                        "\n and for non-busy hours :"+radioButtonFree.getText());
                //FOR BUSY HOURS
                if(radioButtonBusy.getText().toString().equals("Silent")){
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }else if(radioButtonBusy.toString().equals("Vibrate")){
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }else if(radioButtonBusy.toString().equals("Ringing")){
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
                //FOR NON-BUSY HOURS
                if(radioButtonFree.getText().toString().equals("Silent")){
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }else if(radioButtonFree.getText().toString().equals("Vibrate")){
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }else if(radioButtonFree.getText().toString().equals("Ringing")){
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
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
        Toast.makeText(this, "for non busy hours : "+radioButtonFree.getText(),
                Toast.LENGTH_SHORT).show();
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
}
