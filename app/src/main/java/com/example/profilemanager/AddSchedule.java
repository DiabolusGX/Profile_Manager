package com.example.profilemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddSchedule extends AppCompatActivity {

    Button addTimingBtn, viewTimingBtn, editTimingBtn, deleteTimingBtn;
    DatabaseHelp myDb;
    TimePicker startTimingPicker, endTimingPicker;
    EditText timeTesting, idTiming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        myDb = new DatabaseHelp(this);
        idTiming = findViewById(R.id.idTiming);
        addTimingBtn = findViewById(R.id.addTimingBtn);
        viewTimingBtn = findViewById(R.id.viewTimingBtn);
        editTimingBtn = findViewById(R.id.editTimingBtn);
        deleteTimingBtn = findViewById(R.id.deleteTimingBtn);
        startTimingPicker = findViewById(R.id.startTimingPicker);
        endTimingPicker = findViewById(R.id.endTimingPicker);

//        addData();
//        viewData();
    }

    public void viewData(){
        viewTimingBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("ERROR : ", "NO DATA FOUND");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID : "+res.getString(0)+"\n");
                            buffer.append("STARTING TIME : "+res.getString(1)+"\n");
                            buffer.append("ENDING TIME : "+res.getString(2)+"\n\n");
                        }
                        showMessage("DATA : ", buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void addData() {
        addTimingBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean ifInserted = myDb.insertTimings(startPickerData(),endPickerData());
                        if (ifInserted == true) {
                            Toast.makeText(AddSchedule.this,"Timing added.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddSchedule.this,"Error: Failed to add Timings.",
                                    Toast.LENGTH_LONG).show();
                        }
                        //testing toast
                        /*Toast.makeText(AddSchedule.this,"HOUR ARE = "+startTimingPicker.
                                        getCurrentHour().toString(),
                                Toast.LENGTH_LONG).show();*/
                    }
                }
        );
    }
    public String startPickerData(){
        Integer startingHours = startTimingPicker.getCurrentHour();
        Integer startingMinutes = startTimingPicker.getCurrentMinute();
        String startMinutes = ""; String startTime = "";
        if(startingMinutes<10){
            startMinutes = "0" + startMinutes.concat(startingMinutes.toString());
        }else{
            startMinutes = startingMinutes.toString();
        }
        if(startingHours>12){
            startingHours = startingHours - 12;
            startTime = startingHours.toString().concat(":").concat(startMinutes.toString().concat(" PM"));
        }else{
            startTime = startingHours.toString().concat(":").concat(startMinutes.toString().concat(" AM"));
        }
        return startTime;
    }
    public String endPickerData(){
        Integer endingHours = endTimingPicker.getCurrentHour();
        Integer endingMinutes = endTimingPicker.getCurrentMinute();
        String endMinutes = ""; String endTime = "";
        if(endingMinutes<10){
            endMinutes = "0" + endMinutes.concat(endingMinutes.toString());
        }else{
            endMinutes = endingMinutes.toString();
        }
        if(endingHours>12){
            endingHours = endingHours - 12;
            endTime = endingHours.toString().concat(":").concat(endMinutes.toString().concat(" PM"));
        }else{
            endTime = endingHours.toString().concat(":").concat(endMinutes.toString().concat(" AM"));
        }
        return endTime;
    }
}
