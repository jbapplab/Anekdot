package com.jbapplab.navigationdrawertabs;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jbapplab.navigationdrawertabs.m_MySQL.Downloader;
import com.jbapplab.navigationdrawertabs.m_MySQL.DownloaderCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCRUD;

import java.util.ArrayList;


public class RetrieveStoriesActivity extends AppCompatActivity {

    static String urlAddress = "http://applabjb.000webhostapp.com/create_index.php";

    //Variables
    String userId;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_stories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewRetrieveStories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList dummyArrayList = new ArrayList<>();
        CustomAdapterCRUD customAdapterCRUD = new CustomAdapterCRUD(this, dummyArrayList, userId, firstName, lastName, username, password, email);
        recyclerView.setAdapter(customAdapterCRUD);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeLayoutRetrieveStories);

        new DownloaderCRUD(RetrieveStoriesActivity.this, urlAddress, recyclerView, swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DownloaderCRUD(RetrieveStoriesActivity.this, urlAddress, recyclerView, swipeRefreshLayout).execute();
            }
        });
    }
}
