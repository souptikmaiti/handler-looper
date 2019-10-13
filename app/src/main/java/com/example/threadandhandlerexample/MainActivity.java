package com.example.threadandhandlerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button btnStart, btnStop;
    ThreadExample threadExample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        threadExample = new ThreadExample(false,this);
    }

    public void onStartThread(View view){
        threadExample.startBackgroundThread();
    }
    public void onStopThread(View view){
        threadExample.stopBackgroundThread();
    }
}
