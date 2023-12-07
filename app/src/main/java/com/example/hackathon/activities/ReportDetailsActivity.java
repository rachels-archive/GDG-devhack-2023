package com.example.hackathon.activities;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathon.R;

public class ReportDetailsActivity extends AppCompatActivity {

    ImageView backBtn;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);

        backBtn = findViewById(R.id.backbtn);

        backBtn.setOnClickListener(v-> {
            finish();
        });

    }

}
