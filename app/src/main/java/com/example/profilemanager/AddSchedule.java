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

    //EditPrefrences editPrefrences = new EditPrefrences();

    Button addTimingBtn, viewTimingBtn, editTimingBtn, deleteTimingBtn;
    TimePicker startTimingPicker, endTimingPicker;
    EditText timeTesting, idTiming;
    String startTime = "12:29 AM"; String endTime = "12:30 AM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        idTiming = findViewById(R.id.idTiming);
        addTimingBtn = findViewById(R.id.addTimingBtn);
        viewTimingBtn = findViewById(R.id.viewTimingBtn);
        editTimingBtn = findViewById(R.id.editTimingBtn);
        deleteTimingBtn = findViewById(R.id.deleteTimingBtn);
        startTimingPicker = findViewById(R.id.startTimingPicker);
        endTimingPicker = findViewById(R.id.endTimingPicker);

        addData();
        viewData();
        editData();
        deleteData();
    }

    private void viewData(){
        viewTimingBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AddSchedule.this,"WORK IN PROGRESS",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void addData() {
        addTimingBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String x = startPickerData();
                        String y = endPickerData();
                        //testing toast
                        Toast.makeText(AddSchedule.this,"FROM " +x+" to "+y,
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    private void editData(){
        editTimingBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AddSchedule.this,"WORK IN PROGRESS",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    private void deleteData(){
        deleteTimingBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AddSchedule.this,"WORK IN PROGRESS",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //returns stating time string of busy hours
    private String startPickerData(){
        Integer startingHours = startTimingPicker.getCurrentHour();
        Integer startingMinutes = startTimingPicker.getCurrentMinute();
        String startMinutes = "";
        if(startingMinutes<10){
            startMinutes = "0" + startMinutes.concat(startingMinutes.toString());
        }else{
            startMinutes = startingMinutes.toString();
        }
        if(startingHours>12){
            startingHours = startingHours - 12;
            startTime = startingHours.toString().concat(":").concat(startMinutes.concat(" PM"));
        }else{
            startTime = startingHours.toString().concat(":").concat(startMinutes.concat(" AM"));
        }
        return startTime;
    }
    //returns ending time string of busy hours
    private String endPickerData(){
        Integer endingHours = endTimingPicker.getCurrentHour();
        Integer endingMinutes = endTimingPicker.getCurrentMinute();
        String endMinutes = "";
        if(endingMinutes<10){
            endMinutes = "0" + endMinutes.concat(endingMinutes.toString());
        }else{
            endMinutes = endingMinutes.toString();
        }
        if(endingHours>12){
            endingHours = endingHours - 12;
            endTime = endingHours.toString().concat(":").concat(endMinutes.concat(" PM"));
        }else{
            endTime = endingHours.toString().concat(":").concat(endMinutes.concat(" AM"));
        }
        return endTime;
    }
    public String returnStartBusy(){
        return startTime;
    }
    public String returnEndBusy(){
        return endTime;
    }

}
