package com.jbapplab.navigationdrawertabs;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emmasuzuki.easyform.EasyForm;
import com.emmasuzuki.easyform.EasyFormEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegistrationFormFragment extends Fragment {


    @Bind(R.id.registration_form)
    EasyForm easyForm;

    @Bind(R.id.submit_button)
    Button submitButton;

    @Bind(R.id.password_input)
    EasyFormEditText passwordInput;

    @Bind(R.id.confirm_password_input)
    EasyFormEditText confirmPasswordInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_register_validation, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.submit_button)
    public void submitButtonClicked() {
        //make sure to call easyForm.Validate() when using showErrorOn = UNFOCUS
        final String password1 = passwordInput.getText().toString();
        final String password2 = confirmPasswordInput.getText().toString();


        if (password2.equals(password1)) {
            easyForm.validate();

            if (easyForm.isValid()) {
                Log.e(getClass().getSimpleName(), "All values valid");
            } else {
                Log.e(getClass().getSimpleName(), "The last input was invalid");
            }
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("The passwords do not match. Please make sure they are identical.")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();

        }
    }
}
