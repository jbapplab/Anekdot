package com.jbapplab.navigationdrawertabs.m_StoryDetailActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.jbapplab.navigationdrawertabs.R;
import com.jbapplab.navigationdrawertabs.m_UI.PicassoClient;

public class StoryDetailActivity extends AppCompatActivity {

    TextView storyIdDetail, firstNameDetail, lastNameDetail, usernameDetail, passwordDetail, emailDetail, imageUrlDetail;
    ImageView storyImageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storyIdDetail = findViewById(R.id.storyIdDetail);
        firstNameDetail = findViewById(R.id.firstNameDetail);
        lastNameDetail = findViewById(R.id.lastNameDetail);
        usernameDetail = findViewById(R.id.usernameDetail);
        passwordDetail = findViewById(R.id.passwordDetail);
        emailDetail = findViewById(R.id.emailDetail);
        storyImageDetail = findViewById(R.id.storyImageDetail);

        //RECEIVE
        Intent intent = this.getIntent();
        String storyId = intent.getExtras().getString("ID_KEY");
        String firstName = intent.getExtras().getString("FIRST_NAME_KEY");
        String lastName = intent.getExtras().getString("LAST_NAME_KEY");
        String username = intent.getExtras().getString("USERNAME_KEY");
        String password = intent.getExtras().getString("PASSWORD_KEY");
        String email = intent.getExtras().getString("EMAIL_KEY");
        String imageUrl = intent.getExtras().getString("IMAGE_URL_KEY");

        //BIND
        storyIdDetail.setText(storyId);
        firstNameDetail.setText(firstName);
        lastNameDetail.setText(lastName);
        usernameDetail.setText(username);
        passwordDetail.setText(password);
        emailDetail.setText(email);
        PicassoClient.downloadImage(this, imageUrl, storyImageDetail);
    }
}
