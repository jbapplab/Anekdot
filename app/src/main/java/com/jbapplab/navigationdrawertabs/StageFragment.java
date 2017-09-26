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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;


public class StageFragment extends Fragment {

    String stageSelection;
    String stage= "default";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */
        stageSelection = getArguments().getString("STAGE_KEY");
        //Log.i("stageSelection", stageSelection);

        switch (stageSelection){
            case "Stage 1: The audience is unaware of the problem of issue you are describing; they don't have intention to change in the forseeable future (e.g. first time smokers)":
                stage = "0";
                break;
            case "Stage 2: The audience is aware of the problem or issue you are describing; they are seriously considering to change their behaviour in relation to it (e.g. people that have been diagnosed with respitory problems due to smoking)":
                stage = "1";
                break;
            case "Stage 3: The audience is at a stage that they are intending to take action (e.g. people that know about the benefits of exercise but postpone signing up to the gym)":
                stage = "2";
                break;
            case "Stage 4: The audience modify their behaviours, experiences and/or environment to overcome the issue or problem. (e.g. people that buy healthy food and throw away snacks while loosing weight)":
                stage = "3";
                break;
            default:
                stage = "default";
        }

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.stage_fragment_layout, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        switch (stage){
            case "0":
                RelativeLayout relativeLayout0 = view.findViewById(R.id.stage0);
                relativeLayout0.setVisibility(View.VISIBLE);
                break;
            case "1":
                RelativeLayout relativeLayout1 = view.findViewById(R.id.stage1);
                relativeLayout1.setVisibility(View.VISIBLE);
                break;
            case "2":
                RelativeLayout relativeLayout2 = view.findViewById(R.id.stage2);
                relativeLayout2.setVisibility(View.VISIBLE);
                break;
            case "3":
                RelativeLayout relativeLayout3 = view.findViewById(R.id.stage3);
                relativeLayout3.setVisibility(View.VISIBLE);
                break;
            default:
                RelativeLayout relativeLayoutDefault = view.findViewById(R.id.stageDefault);
                relativeLayoutDefault.setVisibility(View.VISIBLE);

        }
    }
}