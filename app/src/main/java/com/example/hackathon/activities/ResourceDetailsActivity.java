package com.example.hackathon.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathon.R;

public class ResourceDetailsActivity extends AppCompatActivity {

    ImageView backBtn;
    TextView nameTv, descTv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        nameTv = findViewById(R.id.nameTextView);
        descTv = findViewById(R.id.descTextView);
        backBtn = findViewById(R.id.backbtn);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        String name = getIntent().getStringExtra("NAME");
        String desc = getIntent().getStringExtra("DESC");

        nameTv.setText(name);
        descTv.setText(desc);


    }
}
