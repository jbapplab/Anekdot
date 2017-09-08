package com.jbapplab.navigationdrawertabs;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JohnB on 07/09/2017.
 */

public class RegisterRequest extends StringRequest {
    /**
     * Allows us to make a request to the register.php file on the server
     * and get a response as a string (extends StringRequest)
     */

    //Need to specify URL of the register.php
    //private static final String REGISTER_REQUEST_URL = "http://anekdot.epizy.com/register.php";
    private static final String REGISTER_REQUEST_URL = "http://applabjb.000webhostapp.com/register.php";
    //Need to create a map
    private Map<String, String> params;

    //Create a constructor for when the first instance of this class is created
    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener, Response.ErrorListener errorListener){
        /**
         * We need to pass some data to volley which will allow it to execute our request
         * -First thing we need to pass is the method - POST means we are gonna send some data to register.php and it
         * will respond with some data.
         * -Then we need to give volley the URL
         * -Then we need to give it the listener: when volley has finished with the request it will inform the
         * Response.Listener
         * Finally we need to provide an error listener that volley will inform if something goes wrong with the request
         */
        super(Method.POST, REGISTER_REQUEST_URL, listener, errorListener);

        //We use the params for volley to pass the information to register.php
        params = new HashMap<>();
        //Now we need to put the data into the HashMap params.put("nameinPHP", nameinJAVA)
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);
        //age+ '''' basically converts it from an integer to a string
        params.put("age",age + "");
        //Log.d("REGISTER REQUEST", "Value: " + (username));
    }
    //Now that we have put the data in the params volley needs to access them
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

