package com.jbapplab.navigationdrawertabs.m_MySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JohnB on 12/09/2017.
 */

public class Connector {

    public static HttpURLConnection connection(String urlAddress){
        try {
            URL url=new URL(urlAddress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(45000);
            con.setReadTimeout(45000);
            con.setDoInput(true);

            return con;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
