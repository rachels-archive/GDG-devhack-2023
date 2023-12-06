package com.example.hackathon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.hackathon.R;

public class ResourcesActivity extends AppCompatActivity {

    ImageView backBtn;

    CardView conditionsCard, medicationsCard, ingredientsCard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        conditionsCard = findViewById(R.id.conditions_card);
        medicationsCard = findViewById(R.id.medications_card);
        ingredientsCard = findViewById(R.id.ingredients_card);
        backBtn = findViewById(R.id.backbtn);

        conditionsCard.setOnClickListener(v -> {
            Intent intent = new Intent(ResourcesActivity.this, ResourceListActivity.class);
            intent.putExtra("prev", "conditions");
            startActivity(intent);
        });

        medicationsCard.setOnClickListener(v -> {
            Intent intent = new Intent(ResourcesActivity.this, ResourceListActivity.class);
            intent.putExtra("prev", "medications");
            startActivity(intent);
        });

        ingredientsCard.setOnClickListener(v -> {
            Intent intent = new Intent(ResourcesActivity.this, ResourceListActivity.class);
            intent.putExtra("prev", "ingredients");
            startActivity(intent);
        });

        backBtn.setOnClickListener(v -> {
            finish();
        });
    }
}