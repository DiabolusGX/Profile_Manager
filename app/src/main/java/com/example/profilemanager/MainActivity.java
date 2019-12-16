package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editPrefBtn = findViewById(R.id.editPrefrencesBtn);
        editPrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEditPrefActivity();
            }
        });
        Button addSchedule = findViewById(R.id.addScheduleBtn);
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddSchedule();
            }
        });
    }

    private void startEditPrefActivity() {
        Intent intent = new Intent(MainActivity.this, EditPrefrences.class);
        startActivity(intent);
    }

    private void startAddSchedule(){
        Intent intent = new Intent(MainActivity.this, AddSchedule.class);
        startActivity(intent);
    }
}
