package com.example.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class ReportsActivity extends AppCompatActivity {
    
    ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        backBtn = findViewById(R.id.backbtn);

        backBtn.setOnClickListener(v-> {
            finish();
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}