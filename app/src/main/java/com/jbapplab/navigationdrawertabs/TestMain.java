package com.jbapplab.navigationdrawertabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by JohnB on 11/09/2017.
 */

public class TestMain extends AppCompatActivity {
    private static final String REGISTRATION_FORM_TAG = "registration_form";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.form, new RegistrationFormFragment(), REGISTRATION_FORM_TAG)
                    .commitAllowingStateLoss();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }


}

