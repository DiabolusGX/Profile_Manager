package com.example.profilemanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    String startData = "";
    String endData = "";

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: startData= " + startData);
        Log.d(TAG, "onStart: endData= " + endData);
    }

    private void startEditPrefActivity() {
        Intent intent = new Intent(MainActivity.this, EditPreferences.class);
        intent.putExtra("StartData", startData);
        intent.putExtra("EndData", endData);
        startActivity(intent);
    }

    private void startAddSchedule(){
        Intent intent = new Intent(MainActivity.this, AddSchedule.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                startData = data.getStringExtra("StartData");
                endData = data.getStringExtra("EndData");
            }
        }
    }
}
