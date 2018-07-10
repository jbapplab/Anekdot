package com.jbapplab.navigationdrawertabs;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyFormEditText;
import com.emmasuzuki.easyform.EasyTextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EasyForm easyForm = findViewById(R.id.registration_form);
        final EasyTextInputLayout etFirstName = findViewById(R.id.etFirstName);
        final EasyTextInputLayout etLastName = findViewById(R.id.etLastName);
        final EasyTextInputLayout etUsername = findViewById(R.id.etUsername);
        final EasyTextInputLayout etEmail = findViewById(R.id.etEmail);
        final EasyTextInputLayout etPassword = findViewById(R.id.etPassword);
        final EasyTextInputLayout etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        final Button bRegister = findViewById(R.id.bRegister);

        //This sets the loading progress bar
        progressBar = findViewById(R.id.progressBar);


        /**
         * When the user presses the register button we will get information from the fields pass it
         * to com.jbapplab.navigationdrawertabs.RegisterRequest and then execute the request.
         */
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setAlpha(1);
                //gets the text from the name field, converts it to a string and saves it to the name variable
                final String firstName = etFirstName.getEditText().getText().toString();
                final String lastName = etLastName.getEditText().getText().toString();
                final String username = etUsername.getEditText().getText().toString();
                final String email = etEmail.getEditText().getText().toString();
                final String password = etPassword.getEditText().getText().toString();
                final String passwordConfirm = etPasswordConfirm.getEditText().getText().toString();

                if (passwordConfirm.equals(password)) {
                    easyForm.validate();

                    if (easyForm.isValid()) {
                        Log.e(getClass().getSimpleName(), "All values valid");

                        Response.Listener<String> responseListener = new Response.Listener<String>() {

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


                                    if (success) {
                                        //If successful it will take them to the login page
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        RegisterActivity.this.startActivity(intent);
                                    } else {
                                        progressBar.setAlpha(0);
                                        //If not successful we want to display an error and user can select to retry
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("Registration Failed: The username already exists.")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        Response.ErrorListener errorListener = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressBar.setAlpha(0);
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Failed: There is a problem with the server connection.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        };

                        //Now we have to create the request. It needs the 4 fields and a response listener
                        RegisterRequest registerRequest = new RegisterRequest(firstName, lastName, username, email, password, responseListener, errorListener);
                        /**
                         * Finally we need to add our request to a request queue.
                         * Then we ask to get the queue from volley, because volley hold all of the requests.
                         */
                        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                        queue.add(registerRequest);
                    } else {
                        progressBar.setAlpha(0);
                        Log.e(getClass().getSimpleName(), "The last input was invalid");
                    }
                } else {
                    progressBar.setAlpha(0);
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("The passwords do not match, please make sure they are identical.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
        });
    }
}
