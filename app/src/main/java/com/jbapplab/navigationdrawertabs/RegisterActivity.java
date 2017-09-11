package com.jbapplab.navigationdrawertabs;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyFormEditText;
import com.emmasuzuki.easyform.EasyTextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.jbapplab.navigationdrawertabs.R.id.etName;

public class RegisterActivity extends AppCompatActivity {

    //@Bind(R.id.registration_form)
    //EasyForm easyForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EasyForm easyForm = (EasyForm) findViewById(R.id.registration_form);
        final EasyFormEditText etFirstName = (EasyFormEditText) findViewById(R.id.etFirstName);
        final EasyFormEditText etLastName = (EasyFormEditText) findViewById(R.id.etLastName);
        final EasyFormEditText etUsername = (EasyFormEditText) findViewById(R.id.etUsername);
        final EasyFormEditText etEmail = (EasyFormEditText) findViewById(R.id.etEmail);
        final EasyFormEditText etPassword = (EasyFormEditText) findViewById(R.id.etPassword);
        final EasyFormEditText etPasswordConfirm = (EasyFormEditText) findViewById(R.id.etPasswordConfirm);
        final Button bRegister = (Button) findViewById(R.id.bRegister);


        /**
         * When the user presses the register button we will get information from the fields pass it
         * to com.jbapplab.navigationdrawertabs.RegisterRequest and then execute the request.
         */
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets the text from the name field, converts it to a string and saves it to the name variable
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();
                final String username = etUsername.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                final String passwordConfirm = etPasswordConfirm.getText().toString();
                //This gets the age. converts it to an int and saves it
                //final int age = Integer.parseInt(etAge.getText().toString());

                if (passwordConfirm.equals(password)) {
                    easyForm.validate();

                    if (true/*easyForm.isValid()*/) {
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

                        Response.ErrorListener errorListener = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Login Failed. There is a problem with the server connection.")
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
                        //Log.d("REGISTER ACTIVITY2", "Value: " + (username));
                    } else {
                    Log.e(getClass().getSimpleName(), "The last input was invalid");
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("The passwords do not match. Please make sure they are identical.")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
        });

    }
}
