package com.jbapplab.navigationdrawertabs.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.jbapplab.navigationdrawertabs.R;
import com.squareup.picasso.Picasso;

/**
 * Created by JohnB on 12/09/2017.
 */

public class PicassoClient {

    public static void downloadImage(Context context, String imageUrl, ImageView imageView){
        if (imageUrl != null && imageUrl.length()>0){
            Picasso.with(context).load(imageUrl).placeholder(R.drawable.placeholder).into(imageView);

        } else {
            Picasso.with(context).load(R.drawable.placeholder).into(imageView);
        }
    }

}
