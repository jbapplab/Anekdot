package com.jbapplab.navigationdrawertabs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by JohnB on 30/10/2017.
 */

public class PopupAlertDialogFragment extends DialogFragment {

    private EditText editText;
    private Button buttonBack, buttonConfirm;

    public PopupAlertDialogFragment(){}

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_window, container);
        editText = (EditText) view.findViewById(R.id.popup_edit_text);
        buttonBack = (Button) view.findViewById(R.id.popup_button_back);
        buttonConfirm = (Button) view.findViewById(R.id.popup_button_confirm);

        return view;
    }

}
