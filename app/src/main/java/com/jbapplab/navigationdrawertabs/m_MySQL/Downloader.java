package com.jbapplab.navigationdrawertabs.m_MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by JohnB on 12/09/2017.
 */

public class Downloader extends AsyncTask<Void, Void, String>{

    Context context;
    String urlAddress;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public Downloader(Context context, String urlAddress, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.urlAddress = urlAddress;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(!swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        if (jsonData==null){
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(context, "Unable to retrieve stories.", Toast.LENGTH_SHORT).show();
        } else {
            //PARSE
            new DataParser(context, jsonData, recyclerView, swipeRefreshLayout).execute();
        }
    }

    private String downloadData()
    {
        HttpURLConnection con=Connector.connection(urlAddress);
        if(con==null){
            return null;
        }

        try{
            InputStream inputStream = new BufferedInputStream(con.getInputStream());

            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line=bufferedReader.readLine()) != null){
                jsonData.append(line+"\n");
            }

            bufferedReader.close();
            inputStream.close();

            return jsonData.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
