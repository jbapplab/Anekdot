package com.jbapplab.navigationdrawertabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jbapplab.navigationdrawertabs.m_MySQL.MySQLClientCRUD;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCRUD;

import java.util.ArrayList;

public class RetrieveStoriesCRUDActivity extends AppCompatActivity {

    //Navigation drawer declaration
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    //Variables
    String userId;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_stories_crud);

        /**
         * Here we need to retrieve the data that we passed through the intent from the LoginActivity
         */
        Intent intentGet = getIntent();
        userId = intentGet.getStringExtra("userId_KEY");
        firstName = intentGet.getStringExtra("firstName_KEY");
        lastName = intentGet.getStringExtra("lastName_KEY");
        username = intentGet.getStringExtra("username_KEY");
        password = intentGet.getStringExtra("password_KEY");
        email = intentGet.getStringExtra("email_KEY");

        //Set the toolbar as an action bar to later change the label
        final Toolbar mainToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNavigationView = findViewById(R.id.navigation_stuff);

        /**
         * Setup click events on the Navigation View Items.
         */
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                //Can add more according to the buttons I will need
                //Added the addToBackStack("str") bit to use back button
                if (menuItem.getItemId() == R.id.nav_item_home) {

                    Intent intentGoToUserAreaActivity = new Intent(RetrieveStoriesCRUDActivity.this, UserAreaActivity.class);
                    intentGoToUserAreaActivity.putExtra("userId_KEY", userId);
                    intentGoToUserAreaActivity.putExtra("firstName_KEY", firstName);
                    intentGoToUserAreaActivity.putExtra("lastName_KEY", lastName);
                    intentGoToUserAreaActivity.putExtra("username_KEY", username);
                    intentGoToUserAreaActivity.putExtra("password_KEY", password);
                    intentGoToUserAreaActivity.putExtra("email_KEY", email);
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToUserAreaActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    new AlertDialog.Builder(RetrieveStoriesCRUDActivity.this)
                            .setTitle("Log Out?")
                            .setMessage("Are you sure you want to log out?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intentGoToLoginActivity = new Intent(RetrieveStoriesCRUDActivity.this, LoginActivity.class);
                                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToLoginActivity);

                                }
                            }).create().show();
                }

                if (menuItem.getItemId() == R.id.nav_item_categories) {

                    Intent intentGoToSelectCategoryActivity = new Intent(RetrieveStoriesCRUDActivity.this, SelectCategoryActivity.class);
                    intentGoToSelectCategoryActivity.putExtra("userId_KEY", userId);
                    intentGoToSelectCategoryActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSelectCategoryActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSelectCategoryActivity.putExtra("username_KEY", username);
                    intentGoToSelectCategoryActivity.putExtra("password_KEY", password);
                    intentGoToSelectCategoryActivity.putExtra("email_KEY", email);
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToSelectCategoryActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_mystories) {

                    Intent intentGoToMyStoriesActivity = new Intent(RetrieveStoriesCRUDActivity.this, MyStoriesActivity.class);
                    intentGoToMyStoriesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyStoriesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyStoriesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyStoriesActivity.putExtra("username_KEY", username);
                    intentGoToMyStoriesActivity.putExtra("password_KEY", password);
                    intentGoToMyStoriesActivity.putExtra("email_KEY", email);
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToMyStoriesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_myfavourites) {

                    Intent intentGoToMyFavouritesActivity = new Intent(RetrieveStoriesCRUDActivity.this, MyFavouritesActivity.class);
                    intentGoToMyFavouritesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyFavouritesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyFavouritesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyFavouritesActivity.putExtra("username_KEY", username);
                    intentGoToMyFavouritesActivity.putExtra("password_KEY", password);
                    intentGoToMyFavouritesActivity.putExtra("email_KEY", email);
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToMyFavouritesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_eventsfirst) {

                    Intent intentGoToEvensFirstActivity = new Intent(RetrieveStoriesCRUDActivity.this, MetaFirstActivity.class);
                    intentGoToEvensFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToEvensFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToEvensFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToEvensFirstActivity.putExtra("username_KEY", username);
                    intentGoToEvensFirstActivity.putExtra("password_KEY", password);
                    intentGoToEvensFirstActivity.putExtra("email_KEY", email);
                    intentGoToEvensFirstActivity.putExtra("version_KEY", "detailed_guidance");
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToEvensFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_audiencefirst) {

                    Intent intentGoToMetaFirstActivity = new Intent(RetrieveStoriesCRUDActivity.this, MetaFirstActivity.class);
                    intentGoToMetaFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToMetaFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMetaFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMetaFirstActivity.putExtra("username_KEY", username);
                    intentGoToMetaFirstActivity.putExtra("password_KEY", password);
                    intentGoToMetaFirstActivity.putExtra("email_KEY", email);
                    intentGoToMetaFirstActivity.putExtra("version_KEY", "basic_instructions");
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToMetaFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_settings) {

                    Intent intentGoToSettingsActivity = new Intent(RetrieveStoriesCRUDActivity.this, SettingsActivity.class);
                    intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                    intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSettingsActivity.putExtra("username_KEY", username);
                    intentGoToSettingsActivity.putExtra("password_KEY", password);
                    intentGoToSettingsActivity.putExtra("email_KEY", email);
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToSettingsActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_help) {

                    Intent intentGoToHelpActivity = new Intent(RetrieveStoriesCRUDActivity.this, HelpActivity.class);
                    intentGoToHelpActivity.putExtra("userId_KEY", userId);
                    intentGoToHelpActivity.putExtra("firstName_KEY", firstName);
                    intentGoToHelpActivity.putExtra("lastName_KEY", lastName);
                    intentGoToHelpActivity.putExtra("username_KEY", username);
                    intentGoToHelpActivity.putExtra("password_KEY", password);
                    intentGoToHelpActivity.putExtra("email_KEY", email);
                    RetrieveStoriesCRUDActivity.this.startActivity(intentGoToHelpActivity);

                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        //This sets the loading progress bar
        progressBar = findViewById(R.id.progressBarRetrieve);

        //REFERENCE VIEWS
        recyclerView = findViewById(R.id.recyclerViewRetrieveStoriesCRUD);
        recyclerView.setLayoutManager(new LinearLayoutManager(RetrieveStoriesCRUDActivity.this));

        ArrayList dummyArrayList = new ArrayList<>();
        CustomAdapterCRUD customAdapterCRUD = new CustomAdapterCRUD(this, dummyArrayList, userId, firstName, lastName, username, password, email);
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
                mainToolbar.setTitle("Art");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Art", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Art", progressBar);
                    }
                });

                break;
            case "Causes":
                //Retrieve
                mainToolbar.setTitle("Causes");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Causes", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Causes", progressBar);
                    }
                });
                break;
            case "Education":
                //Retrieve
                mainToolbar.setTitle("Education");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Education", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Education", progressBar);
                    }
                });
                break;
            case "Food":
                //Retrieve
                mainToolbar.setTitle("Food");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Food", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Food", progressBar);
                    }
                });
                break;
            case "Lifestyle":
                //Retrieve
                mainToolbar.setTitle("Lifestyle");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Lifestyle", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Lifestyle", progressBar);
                    }
                });
                break;
            case "Business":
                //Retrieve
                mainToolbar.setTitle("Business");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Business", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Business", progressBar);
                    }
                });
                break;
            case "Sports":
                //Retrieve
                mainToolbar.setTitle("Sports");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Sports", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Sports", progressBar);
                    }
                });
                break;
            case "Travel":
                //Retrieve
                mainToolbar.setTitle("Travel");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Travel", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Travel", progressBar);
                    }
                });
                break;
            case "Security":
                //Retrieve
                mainToolbar.setTitle("Security");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Security", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Security", progressBar);
                    }
                });
                break;
            case "Other":
                //Retrieve
                mainToolbar.setTitle("Other");
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Other", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "Other", progressBar);
                    }
                });
                break;
            default:
                //Retrieve
                new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "All", progressBar);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        if(!swipeRefreshLayout.isRefreshing()){
                            swipeRefreshLayout.setRefreshing(true);
                        }
                        new MySQLClientCRUD(RetrieveStoriesCRUDActivity.this, userId, firstName, lastName, username, password, email).retrieve(recyclerView, swipeRefreshLayout, "All", progressBar);
                    }
                });
        }
    }

    /*
    CREATE THE OPTIONS MENU
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);

        MenuItem menuShareStory = menu.findItem(R.id.menu_item_share_story);
        menuShareStory.setVisible(false);

        MenuItem menuShareUserDetails = menu.findItem(R.id.menu_item_share_details);
        menuShareUserDetails.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    /*
    HANDLE CLICK ON MENU ACTIONS
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notifications:
                Intent intentGoToSettingsActivity = new Intent(RetrieveStoriesCRUDActivity.this, SettingsActivity.class);
                intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                intentGoToSettingsActivity.putExtra("username_KEY", username);
                intentGoToSettingsActivity.putExtra("password_KEY", password);
                intentGoToSettingsActivity.putExtra("email_KEY", email);
                RetrieveStoriesCRUDActivity.this.startActivity(intentGoToSettingsActivity);
                return true;

            case R.id.action_logout:
                new AlertDialog.Builder(RetrieveStoriesCRUDActivity.this)
                        .setTitle("Log Out?")
                        .setMessage("Are you sure you want to log out?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent intentGoToLoginActivity = new Intent(RetrieveStoriesCRUDActivity.this, LoginActivity.class);
                                RetrieveStoriesCRUDActivity.this.startActivity(intentGoToLoginActivity);

                            }
                        }).create().show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
