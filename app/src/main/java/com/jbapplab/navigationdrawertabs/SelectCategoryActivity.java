package com.jbapplab.navigationdrawertabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.jbapplab.navigationdrawertabs.m_DataObject.Category;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapterCategory;

import java.util.ArrayList;

public class SelectCategoryActivity extends AppCompatActivity {

    CustomAdapterCategory customAdapterCategory;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        gridView = findViewById(R.id.gridViewCategories);

        customAdapterCategory = new CustomAdapterCategory(this, getData());
        gridView.setAdapter(customAdapterCategory);
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
