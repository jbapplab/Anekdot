package com.jbapplab.navigationdrawertabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.jbapplab.navigationdrawertabs.m_DataObject.Category;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCreate;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterSaved;

import java.util.ArrayList;

public class SelectCreateTypeActivity extends AppCompatActivity {

    CustomAdapterCreate customAdapterCreate;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_create_type);

        gridView = findViewById(R.id.gridViewCreate);

        customAdapterCreate = new CustomAdapterCreate(this, getData());
        gridView.setAdapter(customAdapterCreate);
    }

    private ArrayList getData(){
        ArrayList<Category> categories = new ArrayList<>();

        Category category = new Category();
        category.setCategoryName("Start by thinking about your audience");
        category.setCategoryImage(R.drawable.create_audience);
        categories.add(category);

        category = new Category();
        category.setCategoryName("Start by thinking about what happens");
        category.setCategoryImage(R.drawable.create_events);
        categories.add(category);

        return categories;
    }
}
