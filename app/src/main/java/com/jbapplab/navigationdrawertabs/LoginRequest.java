package com.jbapplab.navigationdrawertabs;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JohnB on 08/09/2017.
 */

public class LoginRequest extends StringRequest {
    /**
     * Allows us to make a request to the register.php file on the server
     * and get a response as a string (extends StringRequest)
     */

    //Need to specify URL of the register.php
    //private static final String LOGIN_REQUEST_URL = "http://anekdot.epizy.com/login.php";
    private static final String LOGIN_REQUEST_URL = "http://applabjb.000webhostapp.com/login.php";
    //Need to create a map
    private Map<String, String> params;

    //Create a constructor for when the first instance of this class is created
    public LoginRequest(String username, String password, Response.Listener<String> listener, Response.ErrorListener errorListener){
        /**
         * We need to pass some data to volley which will allow it to execute our request
         * -First thing we need to pass is the method - POST means we are gonna send some data to register.php and it
         * will respond with some data.
         * -Then we need to give volley the URL
         * -Then we need to give it the listener: when volley has finished with the request it will inform the
         * Response.Listener
         * -TODO Finally we need to provide an error listener that volley will inform if something goes wrong with the request
         */
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, errorListener);

        //We use the params for volley to pass the information to register.php
        params = new HashMap<>();
        //Now we need to put the data into the HashMap params.put("nameinPHP", nameinJAVA)
        params.put("username",username);
        params.put("password",password);
        //Log.d("REGISTER REQUEST", "Value: " + (username));
    }
    //Now that we have put the data in the params volley needs to access them
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
