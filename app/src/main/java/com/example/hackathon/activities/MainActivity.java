package com.example.hackathon.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hackathon.R;
import com.example.hackathon.adapters.Article_RecyclerViewAdapter;
import com.example.hackathon.models.ArticleModel;
import com.example.hackathon.models.RecyclerViewInterface;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<ArticleModel> articleModels = new ArrayList<>();
    int[] articleImages = {R.drawable.article1, R.drawable.article2, R.drawable.article3};
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, reports, resources;

    CardView scanCard, chatCard, trackCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpArticleModels();

        Article_RecyclerViewAdapter adapter = new Article_RecyclerViewAdapter(this, articleModels, this);

        recyclerView.setAdapter(adapter);

        //LinearLayout linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        //recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.nav_home);
        reports = findViewById(R.id.nav_report);
        resources = findViewById(R.id.nav_resources);

        scanCard = findViewById(R.id.scanCard);
        chatCard = findViewById(R.id.chatbotCard);
        trackCard = findViewById(R.id.trackCard);

        scanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScanActivity.class));
            }
        });


        chatCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
            }
        });

        trackCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TrackActivity.class));
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "dnje", Toast.LENGTH_SHORT).show();
                openDrawer(drawerLayout);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReportsActivity.class));
            }
        });

        resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResourcesActivity.class));
            }
        });

    }

    private void setUpArticleModels() {
        String[] articleTitles = getResources().getStringArray(R.array.article_title);
        String[] articleAuthors = getResources().getStringArray(R.array.article_author);
        String[] articleContent = getResources().getStringArray(R.array.article_content);

        for (int i = 0; i < articleTitles.length; i++) {
            articleModels.add(new ArticleModel(articleTitles[i],
                    articleAuthors[i],
                    articleImages[i],
                    articleContent[i]));
        }
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(MainActivity.this, ArticleActivity.class);

        intent.putExtra("TITLE", articleModels.get(pos).getArticleTitle());
        intent.putExtra("AUTHOR", articleModels.get(pos).getArticleAuthor());
        intent.putExtra("CONTENT", articleModels.get(pos).getArticleContent());
        intent.putExtra("IMAGE", articleModels.get(pos).getArticleImage());

        startActivity(intent);
    }
}