package com.jbapplab.navigationdrawertabs;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ProgressBar;

import com.jbapplab.navigationdrawertabs.m_MySQL.DownloaderCRUD;
import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCRUD;

import java.util.ArrayList;

public class RetrieveStoriesCRUDActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_stories_crud);

        //This sets the loading progress bar
        progressBar = findViewById(R.id.progressBarRetrieve);

        //REFERENCE VIEWS
        recyclerView = findViewById(R.id.recyclerViewRetrieveStoriesCRUD);
        recyclerView.setLayoutManager(new LinearLayoutManager(RetrieveStoriesCRUDActivity.this));

        ArrayList dummyArrayList = new ArrayList<>();
        CustomAdapterCRUD customAdapterCRUD = new CustomAdapterCRUD(this, dummyArrayList);
        recyclerView.setAdapter(customAdapterCRUD);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeLayoutRetrieveStoriesCRUD);

        //Intent to update
        String categoryName;
        Intent intent = this.getIntent();
        if (intent == null){

            categoryName = "All";

        } else {

            categoryName = intent.getExtras().getString("CATEGORY_KEY");

        }


        switch (categoryName){

            case "Art":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Art", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Art", progressBar);
                    }
                });

                break;
            case "Causes":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Causes", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Causes", progressBar);
                    }
                });
                break;
            case "Education":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Education", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Education", progressBar);
                    }
                });
                break;
            case "Food":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Food", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Food", progressBar);
                    }
                });
                break;
            case "Lifestyle":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Lifestyle", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Lifestyle", progressBar);
                    }
                });
                break;
            case "Business":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Business", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Business", progressBar);
                    }
                });
                break;
            case "Sports":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Sports", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Sports", progressBar);
                    }
                });
                break;
            case "Travel":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Travel", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Travel", progressBar);
                    }
                });
                break;
            case "Security":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Security", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Security", progressBar);
                    }
                });
                break;
            case "Other":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Other", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Other", progressBar);
                    }
                });
                break;
            default:
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "All", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "All", progressBar);
                    }
                });
        }
    }
}
