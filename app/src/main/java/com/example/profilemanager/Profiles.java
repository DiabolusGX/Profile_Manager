package com.example.profilemanager;

import android.media.AudioManager;
import android.util.Log;

import java.util.Calendar;

public class Profiles {

    String selectedProfile="";
    Profiles(String state){
        selectedProfile += state;
    }
    private AudioManager myAudioManager;
    Calendar c = Calendar.getInstance();

    AddSchedule addSchedule = new AddSchedule();
    String busyHoursStarting = addSchedule.startTime;
    String busyHoursEnding = addSchedule.endTime;
    String currentTime = "7:30 AM";

    //returns current time
    public String currentTime(){

        int currHr = c.get(Calendar.HOUR_OF_DAY);
        int currMin = c.get(Calendar.MINUTE);
//        c.set(Calendar.HOUR_OF_DAY, currHr);
//        c.set(Calendar.MINUTE, currMin);
//        c.set(Calendar.SECOND, 0);
        String currentMinutes = "";
        if(currMin<10){
            currentMinutes = "0" + currentMinutes.concat(String.valueOf(currMin));
        }else{
            currentMinutes = String.valueOf(currMin);
        }
        if(currHr>12){
            currHr = currHr - 12;
            currentTime = String.valueOf(currHr).concat(":").concat(currentMinutes.concat(" PM"));
        }else{
            currentTime = String.valueOf(currHr).concat(":").concat(currentMinutes.concat(" AM"));
        }

        return currentTime;
    }

    //changes profile
    public void changeProfile(){
        //FOR BUSY HOURS
        if(currentTime().equals(busyHoursStarting)
                && selectedProfile.equals("Silent")){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }else if(currentTime().equals(busyHoursStarting)
                && selectedProfile.equals("Vibrate")){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }else if(currentTime().equals(busyHoursStarting)
                &&selectedProfile.equals("Ringing")){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
        //FOR NON-BUSY HOURS
        if(currentTime().equals(busyHoursEnding)
                && selectedProfile.equals("Silent")){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }else if(currentTime().equals(busyHoursEnding)
                && selectedProfile.equals("Vibrate")){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }else if(currentTime().equals(busyHoursEnding)
                &&selectedProfile.equals("Ringing")){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
    }
    public void putOnSilent(){
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }
    public void putOnVibrate(){
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }
    public void putOnRingig(){
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }
//    //DND ACCESS
//    private void checkDndPermission(Context context) {
//        NotificationManager notificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && !notificationManager.isNotificationPolicyAccessGranted()) {
//
//            Intent intent = new Intent(
//                    android.provider.Settings
//                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
//
//            startActivity(intent);
//        }
//    }
}
