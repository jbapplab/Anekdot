package com.jbapplab.navigationdrawertabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.jbapplab.navigationdrawertabs.m_DataObject.Category;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCategory;

import java.util.ArrayList;

public class SelectCategoryActivity extends AppCompatActivity {

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

    //This is to create the list of topics
    GridView gridView;
    CustomAdapterCategory customAdapterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        /**
         * Here we need to retrieve the data that we passed through the intent from the LoginActivity
         */
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId_KEY");
        firstName = intent.getStringExtra("firstName_KEY");
        lastName = intent.getStringExtra("lastName_KEY");
        username = intent.getStringExtra("username_KEY");
        password = intent.getStringExtra("password_KEY");
        email = intent.getStringExtra("email_KEY");

        //Set the toolbar as an action bar to later change the label
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);

        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_stuff);

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

                    Intent intentGoToUserAreaActivity = new Intent(SelectCategoryActivity.this, UserAreaActivity.class);
                    intentGoToUserAreaActivity.putExtra("userId_KEY", userId);
                    intentGoToUserAreaActivity.putExtra("firstName_KEY", firstName);
                    intentGoToUserAreaActivity.putExtra("lastName_KEY", lastName);
                    intentGoToUserAreaActivity.putExtra("username_KEY", username);
                    intentGoToUserAreaActivity.putExtra("password_KEY", password);
                    intentGoToUserAreaActivity.putExtra("email_KEY", email);
                    SelectCategoryActivity.this.startActivity(intentGoToUserAreaActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    new AlertDialog.Builder(SelectCategoryActivity.this)
                            .setTitle("Log Out?")
                            .setMessage("Are you sure you want to log out?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {

                                    Intent intentGoToLoginActivity = new Intent(SelectCategoryActivity.this, LoginActivity.class);
                                    SelectCategoryActivity.this.startActivity(intentGoToLoginActivity);

                                }
                            }).create().show();
                }

                if (menuItem.getItemId() == R.id.nav_item_categories) {

                    Intent intentGoToSelectCategoryActivity = new Intent(SelectCategoryActivity.this, SelectCategoryActivity.class);
                    intentGoToSelectCategoryActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intentGoToSelectCategoryActivity.putExtra("userId_KEY", userId);
                    intentGoToSelectCategoryActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSelectCategoryActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSelectCategoryActivity.putExtra("username_KEY", username);
                    intentGoToSelectCategoryActivity.putExtra("password_KEY", password);
                    intentGoToSelectCategoryActivity.putExtra("email_KEY", email);
                    SelectCategoryActivity.this.startActivity(intentGoToSelectCategoryActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_mystories) {

                    Intent intentGoToMyStoriesActivity = new Intent(SelectCategoryActivity.this, MyStoriesActivity.class);
                    intentGoToMyStoriesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyStoriesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyStoriesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyStoriesActivity.putExtra("username_KEY", username);
                    intentGoToMyStoriesActivity.putExtra("password_KEY", password);
                    intentGoToMyStoriesActivity.putExtra("email_KEY", email);
                    SelectCategoryActivity.this.startActivity(intentGoToMyStoriesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_myfavourites) {

                    Intent intentGoToMyFavouritesActivity = new Intent(SelectCategoryActivity.this, MyFavouritesActivity.class);
                    intentGoToMyFavouritesActivity.putExtra("userId_KEY", userId);
                    intentGoToMyFavouritesActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMyFavouritesActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMyFavouritesActivity.putExtra("username_KEY", username);
                    intentGoToMyFavouritesActivity.putExtra("password_KEY", password);
                    intentGoToMyFavouritesActivity.putExtra("email_KEY", email);
                    SelectCategoryActivity.this.startActivity(intentGoToMyFavouritesActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_eventsfirst) {

                    Intent intentGoToEvensFirstActivity = new Intent(SelectCategoryActivity.this, MetaFirstActivity.class);
                    intentGoToEvensFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToEvensFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToEvensFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToEvensFirstActivity.putExtra("username_KEY", username);
                    intentGoToEvensFirstActivity.putExtra("password_KEY", password);
                    intentGoToEvensFirstActivity.putExtra("email_KEY", email);
                    intentGoToEvensFirstActivity.putExtra("version_KEY", "detailed_guidance");
                    SelectCategoryActivity.this.startActivity(intentGoToEvensFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_audiencefirst) {

                    Intent intentGoToMetaFirstActivity = new Intent(SelectCategoryActivity.this, MetaFirstActivity.class);
                    intentGoToMetaFirstActivity.putExtra("userId_KEY", userId);
                    intentGoToMetaFirstActivity.putExtra("firstName_KEY", firstName);
                    intentGoToMetaFirstActivity.putExtra("lastName_KEY", lastName);
                    intentGoToMetaFirstActivity.putExtra("username_KEY", username);
                    intentGoToMetaFirstActivity.putExtra("password_KEY", password);
                    intentGoToMetaFirstActivity.putExtra("email_KEY", email);
                    intentGoToMetaFirstActivity.putExtra("version_KEY", "basic_instructions");
                    SelectCategoryActivity.this.startActivity(intentGoToMetaFirstActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_settings) {

                    Intent intentGoToSettingsActivity = new Intent(SelectCategoryActivity.this, SettingsActivity.class);
                    intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                    intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                    intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                    intentGoToSettingsActivity.putExtra("username_KEY", username);
                    intentGoToSettingsActivity.putExtra("password_KEY", password);
                    intentGoToSettingsActivity.putExtra("email_KEY", email);
                    SelectCategoryActivity.this.startActivity(intentGoToSettingsActivity);

                }

                if (menuItem.getItemId() == R.id.nav_item_help) {

                    Intent intentGoToHelpActivity = new Intent(SelectCategoryActivity.this, HelpActivity.class);
                    intentGoToHelpActivity.putExtra("userId_KEY", userId);
                    intentGoToHelpActivity.putExtra("firstName_KEY", firstName);
                    intentGoToHelpActivity.putExtra("lastName_KEY", lastName);
                    intentGoToHelpActivity.putExtra("username_KEY", username);
                    intentGoToHelpActivity.putExtra("password_KEY", password);
                    intentGoToHelpActivity.putExtra("email_KEY", email);
                    SelectCategoryActivity.this.startActivity(intentGoToHelpActivity);

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

        //This is to create the list of topics
        gridView = findViewById(R.id.gridViewCategories);
        customAdapterCategory = new CustomAdapterCategory(this, getData(), userId, firstName, lastName, username, password, email);
        gridView.setAdapter(customAdapterCategory);
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
                Intent intentGoToSettingsActivity = new Intent(SelectCategoryActivity.this, SettingsActivity.class);
                intentGoToSettingsActivity.putExtra("userId_KEY", userId);
                intentGoToSettingsActivity.putExtra("firstName_KEY", firstName);
                intentGoToSettingsActivity.putExtra("lastName_KEY", lastName);
                intentGoToSettingsActivity.putExtra("username_KEY", username);
                intentGoToSettingsActivity.putExtra("password_KEY", password);
                intentGoToSettingsActivity.putExtra("email_KEY", email);
                SelectCategoryActivity.this.startActivity(intentGoToSettingsActivity);
                return true;

            case R.id.action_logout:
                new AlertDialog.Builder(SelectCategoryActivity.this)
                        .setTitle("Log Out?")
                        .setMessage("Are you sure you want to log out?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent intentGoToLoginActivity = new Intent(SelectCategoryActivity.this, LoginActivity.class);
                                SelectCategoryActivity.this.startActivity(intentGoToLoginActivity);

                            }
                        }).create().show();
                return true;

                /*
                case R.id.menu_item_share:
                //TODO User chose the "Share" action WE PUT CONTEXT SPECIFIC SHARE
                Toast.makeText(SelectCategoryActivity.this, "There is nothing but category names here, move along!", Toast.LENGTH_SHORT).show();
                return true;
                */

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private ArrayList getData(){
        ArrayList<Category> categories = new ArrayList<>();

        Category category = new Category();
        category.setCategoryName("Art");
        category.setCategoryImage(R.drawable.category_art);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Causes");
        category.setCategoryImage(R.drawable.category_causes);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Education");
        category.setCategoryImage(R.drawable.category_education);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Food");
        category.setCategoryImage(R.drawable.category_food);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Lifestyle");
        category.setCategoryImage(R.drawable.category_lifestyle);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Business");
        category.setCategoryImage(R.drawable.category_business);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Sports");
        category.setCategoryImage(R.drawable.category_sports);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Travel");
        category.setCategoryImage(R.drawable.category_travel);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Security");
        category.setCategoryImage(R.drawable.category_security);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Other");
        category.setCategoryImage(R.drawable.category_other);
        categories.add(category);

        return categories;
    }
}
