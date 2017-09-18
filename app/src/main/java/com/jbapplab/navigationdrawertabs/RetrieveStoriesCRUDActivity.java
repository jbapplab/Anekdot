package com.jbapplab.navigationdrawertabs;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.jbapplab.navigationdrawertabs.m_MySQL.DownloaderCRUD;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCRUD;

import java.util.ArrayList;

public class RetrieveStoriesCRUDActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_stories_crud);

        //REFERENCE VIEWS
        recyclerView = findViewById(R.id.recyclerViewRetrieveStoriesCRUD);
        recyclerView.setLayoutManager(new LinearLayoutManager(RetrieveStoriesCRUDActivity.this));

        ArrayList dummyArrayList = new ArrayList<>();
        CustomAdapterCRUD customAdapterCRUD = new CustomAdapterCRUD(this, dummyArrayList);
        recyclerView.setAdapter(customAdapterCRUD);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeLayoutRetrieveStoriesCRUD);

        //Retrieve
        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(!swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(true);
                }
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout);
            }
        });
    }
}
