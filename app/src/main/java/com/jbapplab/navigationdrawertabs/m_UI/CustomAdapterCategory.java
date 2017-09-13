package com.jbapplab.navigationdrawertabs.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.m_DataObject.Category;

import java.util.ArrayList;

/**
 * Created by JohnB on 13/09/2017.
 */

public class CustomAdapterCategory extends BaseAdapter {
    Context context;
    ArrayList<Category> categories;

    public CustomAdapterCategory(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount(){
        return categories.size();
    }

    @Override
    public Object getItem(int i){
        return categories.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){

        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.category_item, viewGroup, false);
        }

        final Category category = (Category) this.getItem(i);

        ImageView categoryImageView = view.findViewById(R.id.categoryImage);
        TextView categoryNameTextView = view.findViewById(R.id.categoryName);

        //BIND
        categoryNameTextView.setText(category.getCategoryName());
        categoryImageView.setImageResource(category.getCategoryImage());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, category.getCategoryName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
