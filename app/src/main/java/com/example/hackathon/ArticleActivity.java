package com.example.hackathon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArticleActivity extends AppCompatActivity {

    ImageView imgView, backBtn;
    TextView authorTextView, titleTextView, contentTextView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        imgView = findViewById(R.id.articleImageView);
        titleTextView = findViewById(R.id.titleTextView);
        authorTextView = findViewById(R.id.authorTextView);
        contentTextView = findViewById(R.id.contentTextView);
        backBtn = findViewById(R.id.backbtn);

        backBtn.setOnClickListener(v->{
            finish();
        });


        String title = getIntent().getStringExtra("TITLE");
        String author = getIntent().getStringExtra("AUTHOR");
        String content = getIntent().getStringExtra("CONTENT");
        int image = getIntent().getIntExtra("IMAGE", 0);

        imgView.setImageResource(image);
        titleTextView.setText(title);
        authorTextView.setText(author);
        contentTextView.setText(content);

    }
}
