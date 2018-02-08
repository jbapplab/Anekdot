package com.jbapplab.navigationdrawertabs.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jbapplab.navigationdrawertabs.MetaFirstActivity;
import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.RetrieveStoriesCRUDActivity;
import com.jbapplab.navigationdrawertabs.SelectCategoryActivity;
import com.jbapplab.navigationdrawertabs.m_DataObject.Category;
import com.jbapplab.navigationdrawertabs.m_StoryDetailActivity.StoryDetailActivityCRUD;

import java.util.ArrayList;

/**
 * Created by JohnB on 13/09/2017.
 */

public class CustomAdapterCategory extends BaseAdapter {
    Context context;
    ArrayList<Category> categories;
    Intent intentRetrieveCategory;


    public CustomAdapterCategory(Context context, ArrayList<Category> categories,  String userId, String firstName, String lastName, String username, String password, String email) {
        this.context = context;
        this.categories = categories;
        intentRetrieveCategory = new Intent(this.context, RetrieveStoriesCRUDActivity.class);
        intentRetrieveCategory.putExtra("userId_KEY", userId);
        intentRetrieveCategory.putExtra("firstName_KEY", firstName);
        intentRetrieveCategory.putExtra("lastName_KEY", lastName);
        intentRetrieveCategory.putExtra("username_KEY", username);
        intentRetrieveCategory.putExtra("password_KEY", password);
        intentRetrieveCategory.putExtra("email_KEY", email);
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
                switch (category.getCategoryName()){

                    case "Art":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Art");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Causes":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Causes");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Education":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Education");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Food":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Food");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Lifestyle":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Lifestyle");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Business":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Business");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Sports":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Sports");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Travel":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Travel");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Security":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Security");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    case "Other":
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "Other");
                        context.startActivity(intentRetrieveCategory);
                        break;
                    default:
                        intentRetrieveCategory.putExtra("CATEGORY_KEY", "All");
                        context.startActivity(intentRetrieveCategory);
                }

                //Toast.makeText(context, category.getCategoryName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
