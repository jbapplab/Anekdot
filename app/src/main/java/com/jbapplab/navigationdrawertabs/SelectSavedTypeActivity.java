package com.jbapplab.navigationdrawertabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.jbapplab.navigationdrawertabs.m_DataObject.Category;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCategory;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterSaved;

import java.util.ArrayList;

public class SelectSavedTypeActivity extends AppCompatActivity {

    CustomAdapterSaved customAdapterSaved;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_saved_type);

        gridView = findViewById(R.id.gridViewSaved);

        customAdapterSaved = new CustomAdapterSaved(this, getData());
        gridView.setAdapter(customAdapterSaved);
    }

    private ArrayList getData(){
        ArrayList<Category> categories = new ArrayList<>();

        Category category = new Category();
        category.setCategoryName("My Written Archives");
        category.setCategoryImage(R.drawable.saved_my);
        categories.add(category);

        category = new Category();
        category.setCategoryName("My Favourite Stories");
        category.setCategoryImage(R.drawable.saved_other);
        categories.add(category);

        return categories;
    }
}
