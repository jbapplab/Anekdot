package com.jbapplab.navigationdrawertabs.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.jbapplab.navigationdrawertabs.R;
import com.squareup.picasso.Picasso;

/**
 * Created by JohnB on 12/09/2017.
 */

public class PicassoClient {

    public static void downloadImage(Context context, String imageCategory, ImageView imageView){
        if (imageCategory != null && imageCategory.length()>0){
            switch (imageCategory){
                case "Other":
                    Picasso.with(context).load(R.drawable.category_other).into(imageView);
                    break;
                case "Art":
                    Picasso.with(context).load(R.drawable.category_art).into(imageView);
                    break;
                case "Causes":
                    Picasso.with(context).load(R.drawable.category_causes).into(imageView);
                    break;
                case "Education":
                    Picasso.with(context).load(R.drawable.category_education).into(imageView);
                    break;
                case "Food":
                    Picasso.with(context).load(R.drawable.category_food).into(imageView);
                    break;
                case "Lifestyle":
                    Picasso.with(context).load(R.drawable.category_lifestyle).into(imageView);
                    break;
                case "Business":
                    Picasso.with(context).load(R.drawable.category_business).into(imageView);
                    break;
                case "Sports":
                    Picasso.with(context).load(R.drawable.category_sports).into(imageView);
                    break;
                case "Travel":
                    Picasso.with(context).load(R.drawable.category_travel).into(imageView);
                    break;
                case "Security":
                    Picasso.with(context).load(R.drawable.category_security).into(imageView);
                    break;
                default:
                    Picasso.with(context).load(R.drawable.category_placeholder).into(imageView);
            }

        } else {
            Picasso.with(context).load(R.drawable.placeholder).into(imageView);
        }
    }
}
