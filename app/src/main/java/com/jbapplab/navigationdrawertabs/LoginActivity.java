package com.jbapplab.navigationdrawertabs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //To exit when back pressed in user area
        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT", false)) {
            LoginActivity.this.finishAndRemoveTask();
        }

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView tvRegisterHere = (TextView) findViewById(R.id.tvRegisterHere);

        //We create a listener that will do something when the register link is clicked
        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Created an intent that opens the RegisterActivity
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                //Then we told the current activity (LoginActivity) to perform that intent
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /**
                         * We need to convert the response to a JSON object so we can work with it.
                         *Like before we have a success that shows us if the response has been successful or not.
                         *
                         */
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                /**
                                 * If successful we want to get the details from the JSON response and send that over to
                                 * the UserAreaActivity. We can retrieve the fields we said that we would get in the PHP
                                 * file. Then we need to create an intend to open the UserAreaActivity. Using the intent
                                 * we can send extra stuff to the new activity. After we pass all the data we need to pass
                                 * in the new activity we need to open it.
                                  */

                                int userId = jsonResponse.getInt("userId");
                                String firstName = jsonResponse.getString("firstName");
                                String lastName = jsonResponse.getString("lastName");
                                String username = jsonResponse.getString("username");
                                String password = jsonResponse.getString("password");
                                String email = jsonResponse.getString("email");
                                //int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("userId_KEY", Integer.toString(userId));
                                intent.putExtra("firstName_KEY", firstName);
                                intent.putExtra("lastName_KEY", lastName);
                                intent.putExtra("username_KEY", username);
                                intent.putExtra("password_KEY", password);
                                intent.putExtra("email_KEY", email);
                                //intent.putExtra("age", age);

                                LoginActivity.this.startActivity(intent);

                            }else {
                                //If not successful we want to display an error and user can select to retry
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Login Failed. There is a problem with the server connection.")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener, errorListener);

                /**
                 * Finally we need to add our request to a request queue.
                 * Then we ask to get the queue from volley, because volley hold all of the requests.
                 */
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //This is to not accidentally exit the app
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit Anekdot?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            LoginActivity.this.finishAndRemoveTask();
                        }
                    }).create().show();
        }
        else {
            super.onBackPressed();
        }
    }
}
