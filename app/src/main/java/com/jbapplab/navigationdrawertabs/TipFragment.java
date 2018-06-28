package com.jbapplab.navigationdrawertabs;

/**
 Now this is an inner fragment for the TabLayout Fragment .
 So basically, TabLayout Is an Fragment for the MainActivity
 while this is an fragment for the TabLayout .

 Here , lets create a new class file and call it PrimaryFragment
 and add the following content . It is a very straightforward
 implementation of a fragment which inflates a layout from the
 onCreateView method which replaces the content of the ViewPager
 holding it.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class TipFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.social_layout,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //TextView introTOTD = getView().findViewById(R.id.tvIntroTipOTD);
        final TextView tipOTD = getView().findViewById(R.id.tvTipOTD);

        final String[] tipArray = getContext().getResources().getStringArray(R.array.string_array_TipOTD);
        final String[] randomTODT = {tipArray[new Random().nextInt(tipArray.length)]};

        Button newTip = getView().findViewById(R.id.btnNewTip);

        tipOTD.setText(randomTODT[0]);

        newTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomTODT[0] = tipArray[new Random().nextInt(tipArray.length)];
                tipOTD.setText(randomTODT[0]);
            }
        });
    }
}