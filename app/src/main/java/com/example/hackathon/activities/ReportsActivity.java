package com.example.hackathon.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hackathon.R;

public class ReportsActivity extends AppCompatActivity {

    ImageView backBtn;

    CardView reportCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        reportCard = findViewById(R.id.reportCv);
        backBtn = findViewById(R.id.backbtn);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        reportCard.setOnClickListener(v-> {
            startActivity(new Intent(ReportsActivity.this, ReportDetailsActivity.class));
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}