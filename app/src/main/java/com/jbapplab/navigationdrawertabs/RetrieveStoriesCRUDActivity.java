package com.jbapplab.navigationdrawertabs;

import android.content.Intent;
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
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Art");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Art");
                    }
                });
                break;
            case "Causes":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Causes");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Causes");
                    }
                });
                break;
            case "Education":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Education");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Education");
                    }
                });
                break;
            case "Food":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Food");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Food");
                    }
                });
                break;
            case "Lifestyle":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Lifestyle");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Lifestyle");
                    }
                });
                break;
            case "Business":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Business");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Business");
                    }
                });
                break;
            case "Sports":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Sports");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Sports");
                    }
                });
                break;
            case "Travel":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Travel");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Travel");
                    }
                });
                break;
            case "Security":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Security");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Security");
                    }
                });
                break;
            case "Other":
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Other");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "Other");
                    }
                });
                break;
            default:
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "All");

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this).retrieve(recyclerView, swipeRefreshLayout, "All");
                    }
                });
        }
    }
}
