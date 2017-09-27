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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class CategoryFragment extends Fragment {

    String categorySelection;
    String category = "default";
    RelativeLayout relativeLayoutOther;
    RelativeLayout relativeLayoutArt;
    RelativeLayout relativeLayoutCauses;
    RelativeLayout relativeLayoutEducation;
    RelativeLayout relativeLayoutFood;
    RelativeLayout relativeLayoutLifestyle;
    RelativeLayout relativeLayoutBusiness;
    RelativeLayout relativeLayoutSports;
    RelativeLayout relativeLayoutTravel;
    RelativeLayout relativeLayoutSecurity;
    RelativeLayout relativeLayoutDefault;


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

    //Subscribers to the events - The method is called when a EventBus event is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCategorySelected(EventBusCategorySelected eventBusCategorySelected){
        categorySelection = eventBusCategorySelected.message;
        Toast.makeText(getActivity(), categorySelection, Toast.LENGTH_SHORT).show();

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
        showCategory(category);
    }

    public void showCategory(String category){
        switch (category){
            case "Other":
                relativeLayoutOther.setVisibility(View.VISIBLE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Art":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.VISIBLE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Causes":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.VISIBLE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Education":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.VISIBLE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Food":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.VISIBLE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Lifestyle":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.VISIBLE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Business":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.VISIBLE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Sports":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.VISIBLE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Travel":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.VISIBLE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            case "Security":
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.VISIBLE);
                relativeLayoutDefault.setVisibility(View.GONE);
                break;
            default:
                relativeLayoutOther.setVisibility(View.GONE);
                relativeLayoutArt.setVisibility(View.GONE);
                relativeLayoutCauses.setVisibility(View.GONE);
                relativeLayoutEducation.setVisibility(View.GONE);
                relativeLayoutFood.setVisibility(View.GONE);
                relativeLayoutLifestyle.setVisibility(View.GONE);
                relativeLayoutBusiness.setVisibility(View.GONE);
                relativeLayoutSports.setVisibility(View.GONE);
                relativeLayoutTravel.setVisibility(View.GONE);
                relativeLayoutSecurity.setVisibility(View.GONE);
                relativeLayoutDefault.setVisibility(View.VISIBLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView", ": CATEGORY");

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */
        //categorySelection = getArguments().getString("CATEGORY_KEY");

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.category_fragment_layout, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("onViewCreated", ": CATEGORY");
        relativeLayoutOther = view.findViewById(R.id.Other);
                relativeLayoutArt = view.findViewById(R.id.Art);
                relativeLayoutCauses = view.findViewById(R.id.Causes);
                relativeLayoutEducation = view.findViewById(R.id.Education);
                relativeLayoutFood = view.findViewById(R.id.Food);
                relativeLayoutFood.setVisibility(View.VISIBLE);
                relativeLayoutLifestyle = view.findViewById(R.id.Lifestyle);
                relativeLayoutBusiness = view.findViewById(R.id.Business);
                relativeLayoutSports = view.findViewById(R.id.Sports);
                relativeLayoutTravel = view.findViewById(R.id.Travel);
                relativeLayoutSecurity = view.findViewById(R.id.Security);
                relativeLayoutDefault = view.findViewById(R.id.topicDefault);
        showCategory(category);

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
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
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