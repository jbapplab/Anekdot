package com.jbapplab.navigationdrawertabs.m_MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jbapplab.navigationdrawertabs.m_DataObject.Story;
import com.jbapplab.navigationdrawertabs.m_UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JohnB on 12/09/2017.
 */

public class DataParser extends AsyncTask<Void, Void, Boolean> {
    Context context;
    String jsonData;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<Story> stories = new ArrayList<>();

    public DataParser(Context context, String jsonData, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        swipeRefreshLayout.setRefreshing(false);

        if(isParsed){
            //BIND
            recyclerView.setAdapter(new CustomAdapter(context, stories));
        } else {
            Toast.makeText(context, "Unable to parse.", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            stories.clear();
            Story story;

            //for (int i=0;i<jsonArray.length();i++){
            for (int i=jsonArray.length()-1;i>-1;i--){
                jsonObject = jsonArray.getJSONObject(i);

                String storyId = jsonObject.getString("user_id");
                String firstName = jsonObject.getString("first_name");
                String lastName = jsonObject.getString("last_name");
                String username = jsonObject.getString("username");
                String password = jsonObject.getString("password");
                String email = jsonObject.getString("email");
                //String imageUrl = jsonObject.getString("imageUrl");

                story = new Story();

                story.setId(storyId);
                story.setFirstName(firstName);
                story.setLastName(lastName);
                story.setUsername(username);
                story.setPassword(password);
                story.setEmail(email);
                //story.setImageUrl(imageUrl);

                stories.add(story);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}
