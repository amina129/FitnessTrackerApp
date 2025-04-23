package com.example.finetnesstracker;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class TimerActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running = false;
    private TextView timeView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timeView = findViewById(R.id.time_view);
        runTimer();

        findViewById(R.id.btn_start).setOnClickListener(v -> running = true);
        findViewById(R.id.btn_pause).setOnClickListener(v -> running = false);
        findViewById(R.id.btn_reset).setOnClickListener(v -> {
            running = false;
            seconds = 0;
        });
    }

    private void runTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                int mins = seconds / 60;
                int secs = seconds % 60;
                timeView.setText(String.format(Locale.getDefault(), "%02d:%02d", mins, secs));
                if (running) seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }
}
