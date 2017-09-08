package com.jbapplab.navigationdrawertabs;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        /**
         * When the user presses the register button we will get information from the fields pass it
         * to com.jbapplab.navigationdrawertabs.RegisterRequest and then execute the request.
         */
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets the text from the name field, converts it to a string and saves it to the name variable
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                //This gets the age. converts it to an int and saves it
                final int age = Integer.parseInt(etAge.getText().toString());

                //Log.d("REGISTER ACTIVITY", "Value: " + (username));

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        /**
                         * This happens when the response has been executed and volley here gives
                         * us the response from register.php. Now we need to convert this to a
                         * JSONObject because we encoded it like that in the PHP file.
                         * It takes the response that volley has given us and converts it into a JSONObject
                         */
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //When we register we get a success response from the PHP file. We need to get that.
                            boolean success = jsonResponse.getBoolean("success");


                            if (success){
                                //If successful it will take them to the login page
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else{
                                //If not successful we want to display an error and user can select to retry
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //Now we have to create the request. It needs the 4 fields and a response listener
                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                /**
                 * Finally we need to add our request to a request queue.
                 * Then we ask to get the queue from volley, because volley hold all of the requests.
                 */
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
                //Log.d("REGISTER ACTIVITY2", "Value: " + (username));
            }
        });

    }
}
