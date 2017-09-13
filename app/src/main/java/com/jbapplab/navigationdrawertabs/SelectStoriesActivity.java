package com.jbapplab.navigationdrawertabs;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.jbapplab.navigationdrawertabs.m_MySQL.Downloader;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class SelectStoriesActivity extends AppCompatActivity {

    static String urlAddress = "http://applabjb.000webhostapp.com/select_stories.php";

    //Button sortStoriesOldNewButton;
    //Button sortStoriesAlphabeticallyButton;
    //private boolean ascending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewStories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList dummyArrayList = new ArrayList<>();
        CustomAdapter customAdapter = new CustomAdapter(this, dummyArrayList);
        recyclerView.setAdapter(customAdapter);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeLayoutStories);

        new Downloader(SelectStoriesActivity.this, urlAddress, recyclerView, swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Downloader(SelectStoriesActivity.this, urlAddress, recyclerView, swipeRefreshLayout).execute();
            }
        });

        /*sortStoriesOldNewButton = findViewById(R.id.sortStoriesOldNewButton);
        sortStoriesAlphabeticallyButton = findViewById(R.id.sortStoriesAlphabeticallyButton);

        sortStoriesOldNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortOldNew(ascending);
                ascending = !ascending;
            }
        });

        sortStoriesAlphabeticallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortAlphabetically(ascending);
                ascending = !ascending;
            }
        }); */
    }
/*
    private void sortOldNew (boolean ascending){
        if (ascending){
            Collections.sort(stories);
        } else {
            Collections.reverse();
        }
    }
    private void sortAlphabetically (boolean ascending){
        if (ascending){
            Collections.sort();
        } else {
            Collections.reverse();
        }
    }
    */
}
