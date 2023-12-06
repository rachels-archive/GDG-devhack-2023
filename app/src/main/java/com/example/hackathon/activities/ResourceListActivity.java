package com.example.hackathon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon.R;
import com.example.hackathon.adapters.Resource_RecyclerViewAdapter;
import com.example.hackathon.models.RecyclerViewInterface;
import com.example.hackathon.models.ResourceModel;

import java.util.ArrayList;

public class ResourceListActivity extends AppCompatActivity implements RecyclerViewInterface {
    ImageView backBtn;

    SearchView searchView;

    ArrayList<ResourceModel> resourceModels = new ArrayList<>();

    Resource_RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);

        backBtn = findViewById(R.id.backbtn);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.rRecyclerView);

        setUpResourceModels();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Resource_RecyclerViewAdapter(this, resourceModels, this);

        recyclerView.setAdapter(adapter);

        backBtn.setOnClickListener((v) -> {
            finish();
        });

    }

    private void filterList(String text) {
        ArrayList<ResourceModel> dataSearchList = new ArrayList<>();
        for (ResourceModel data : resourceModels) {
            if (data.getName().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        adapter.setSearchList(dataSearchList);
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

                for (int i = 0; i < resourceName.length; i++) {
                    resourceModels.add(new ResourceModel(resourceName[i], resourceDesc[i]));
                }
                break;
            case "medications":
                String[] medicationName = getResources().getStringArray(R.array.medication_name);
                String[] medicationDesc = getResources().getStringArray(R.array.medication_desc);

                for (int i = 0; i < medicationName.length; i++) {
                    resourceModels.add(new ResourceModel(medicationName[i], medicationDesc[i]));
                }
                break;
            case "ingredients":
                String[] ingredientName = getResources().getStringArray(R.array.ingredients_name);
                String[] ingredientDesc = getResources().getStringArray(R.array.ingredients_desc);

                for (int i = 0; i < ingredientName.length; i++) {
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
