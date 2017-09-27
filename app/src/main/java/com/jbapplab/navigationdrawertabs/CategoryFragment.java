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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class CategoryFragment extends Fragment {

    String categorySelection;
    String category = "default";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("onSaveInstanceState", ": CATEGORY");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("onAttach", ": CATEGORY");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate", ": CATEGORY");

        if (savedInstanceState != null){
            Log.i("On Create CATEGORY: ", "SAVEDINSTANCE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", ": CATEGORY");

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */
        categorySelection = getArguments().getString("CATEGORY_KEY");

        switch (categorySelection){
            case "Other":
                category = "Other";
                break;
            case "Art":
                category = "Art";
                break;
            case "Causes":
                category = "Causes";
                break;
            case "Education":
                category = "Education";
                break;
            case "Food":
                category = "Food";
                break;
            case "Lifestyle":
                category = "Lifestyle";
                break;
            case "Business":
                category = "Business";
                break;
            case "Sports":
                category = "Sports";
                break;
            case "Travel":
                category = "Travel";
                break;
            case "Security":
                category = "Security";
                break;
            default:
                category = "default";
        }

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.category_fragment_layout, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("onViewCreated", ": CATEGORY");
        switch (category){
            case "Other":
                RelativeLayout relativeLayoutOther = view.findViewById(R.id.Other);
                relativeLayoutOther.setVisibility(View.VISIBLE);
                break;
            case "Art":
                RelativeLayout relativeLayoutArt = view.findViewById(R.id.Art);
                relativeLayoutArt.setVisibility(View.VISIBLE);
                break;
            case "Causes":
                RelativeLayout relativeLayoutCauses = view.findViewById(R.id.Causes);
                relativeLayoutCauses.setVisibility(View.VISIBLE);
                break;
            case "Education":
                RelativeLayout relativeLayoutEducation = view.findViewById(R.id.Education);
                relativeLayoutEducation.setVisibility(View.VISIBLE);
                break;
            case "Food":
                RelativeLayout relativeLayoutFood = view.findViewById(R.id.Food);
                relativeLayoutFood.setVisibility(View.VISIBLE);
                break;
            case "Lifestyle":
                RelativeLayout relativeLayoutLifestyle = view.findViewById(R.id.Lifestyle);
                relativeLayoutLifestyle.setVisibility(View.VISIBLE);
                break;
            case "Business":
                RelativeLayout relativeLayoutBusiness = view.findViewById(R.id.Business);
                relativeLayoutBusiness.setVisibility(View.VISIBLE);
                break;
            case "Sports":
                RelativeLayout relativeLayoutSports = view.findViewById(R.id.Sports);
                relativeLayoutSports.setVisibility(View.VISIBLE);
                break;
            case "Travel":
                RelativeLayout relativeLayoutTravel = view.findViewById(R.id.Travel);
                relativeLayoutTravel.setVisibility(View.VISIBLE);
                break;
            case "Security":
                RelativeLayout relativeLayoutSecurity = view.findViewById(R.id.Security);
                relativeLayoutSecurity.setVisibility(View.VISIBLE);
                break;
            default:
                RelativeLayout relativeLayoutDefault = view.findViewById(R.id.topicDefault);
                relativeLayoutDefault.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated", ": CATEGORY");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("onViewStateRestored", ": CATEGORY");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", ": CATEGORY");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", ": CATEGORY");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", ": CATEGORY");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i("onStop", ": CATEGORY");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView", ": CATEGORY");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", ": CATEGORY");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("onDetach", ": CATEGORY");
    }
}