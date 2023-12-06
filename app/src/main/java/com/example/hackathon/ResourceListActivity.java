package com.example.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class ResourceListActivity extends AppCompatActivity implements RecyclerViewInterface{
    ImageView backBtn;
    ArrayList<ResourceModel>  resourceModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);

        backBtn = findViewById(R.id.backbtn);

        RecyclerView recyclerView = findViewById(R.id.rRecyclerView);

    //    String a = getIntent().getStringExtra("prev");
    //    Log.d("TEST", a);

        /*
        String prevCard = getIntent().getStringExtra("prev");
        switch (prevCard) {
            case "conditions":
                setUpResourceModels();

                Resource_RecyclerViewAdapter adapter = new Resource_RecyclerViewAdapter(this, resourceModels, this);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                break;
            case "medications":

                setUpMedicationModels();

                Resource_RecyclerViewAdapter adapter2 = new Resource_RecyclerViewAdapter(this, medicationModels, this);

                recyclerView.setAdapter(adapter2);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                break;
        }
        */
        setUpResourceModels();

        Resource_RecyclerViewAdapter adapter = new Resource_RecyclerViewAdapter(this, resourceModels, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backBtn.setOnClickListener((v) -> {
            finish();
        });
    }

    private void setUpResourceModels() {
       // String[] resourceName = getResources().getStringArray(R.array.skin_condition_name);
     //   String[] resourceDesc = getResources().getStringArray(R.array.skin_condition_desc);

        //Log.i("CLASS", getIntent().getStringExtra("prev"));

        String resourceClass = getIntent().getStringExtra("prev");

        switch (resourceClass) {
            case "conditions":
                String[] resourceName = getResources().getStringArray(R.array.skin_condition_name);
                String[] resourceDesc = getResources().getStringArray(R.array.skin_condition_desc);

                for (int i = 0; i < resourceName.length; i ++) {
                    resourceModels.add(new ResourceModel(resourceName[i], resourceDesc[i]));
                }
                break;
            case "medications":
                String[] medicationName = getResources().getStringArray(R.array.medication_name);
                String[] medicationDesc = getResources().getStringArray(R.array.medication_desc);

                for (int i = 0; i < medicationName.length; i ++) {
                    resourceModels.add(new ResourceModel(medicationName[i], medicationDesc[i]));
                }
                break;
            case "ingredients":
                String[] ingredientName = getResources().getStringArray(R.array.article_title);
                String[] ingredientDesc = getResources().getStringArray(R.array.article_content);

                for (int i = 0; i < ingredientName.length; i ++) {
                    resourceModels.add(new ResourceModel(ingredientName[i], ingredientDesc[i]));
                }
                break;
        }

    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(ResourceListActivity.this, ResourceDetailsActivity.class);
        intent.putExtra("NAME", resourceModels.get(pos).getName());
        intent.putExtra("DESC", resourceModels.get(pos).getDescription());
        startActivity(intent);
    }

}
