package com.jbapplab.navigationdrawertabs;

/**
 Now create a new File TabFragment.java to inflate & code
 TabLayout and viewPager elements.
 For viewPager to display content it requires a Adapter.
 So, here we have added a FragmentPagerAdapter which is
 used to inflate tab specific fragments.

 The FragmentPagerAdapter overrides three main methods:
 1) getCount(): It returns the total number of tabs to be
 bind with the view pager.
 2) getItem(int position): It returns the tab-specific
 fragment wrt to its position.
 3) getPageTitle(int position): It returns name of the
 title according to the position.
 The FragmentPagerAdapter also includes a default constructor
 which accepts FragmentManager parameter to add/remove fragments.
 At the end , attach your ViewPager with the TabLayout with the
 setupWithViewPager(viewPager) method of the TabLayout.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class StoryCoreFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;
    int savedInstanceStateTest = 0;

    String userIdString, actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl;

    MetaFirstFormFragment metaFirstFormFragment = new MetaFirstFormFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
    StageFragment stageFragment = new StageFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         /*
        UNPACK THE DATA FROM THE BUNDLE


        if (getArguments().getString("CATEGORY_KEY") != null){
            categorySelection = getArguments().getString("CATEGORY_KEY");
        }
        if (getArguments().getString("STAGE_KEY") != null){
            stageSelection = getArguments().getString("STAGE_KEY");
        }
        */

        /**
         * Set the title bar according to the fragment
         */
        ((MetaFirstActivity) getActivity())
                .setActionBarTitle("Create");

        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.story_core_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabsStoryCore);
        viewPager = (ViewPager) x.findViewById(R.id.viewpagerStoryCore);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */

        userIdString = getArguments().getString("USERID_KEY");
        if (getArguments().getString("UPDATE_KEY") != null) {
            actionString = getArguments().getString("UPDATE_KEY");
        }
        if ((actionString != null) && actionString.equals("update")) {
            storyIdString = getArguments().getString("STORY_ID_KEY");
            storyTitle = getArguments().getString("STORY_TITLE_KEY");
            ifOtherSpecify = getArguments().getString("IF_OTHER_SPECIFY_KEY");
            authorIdString = getArguments().getString("AUTHOR_ID_KEY");
            storyDescription = getArguments().getString("STORY_DESCRIPTION_KEY");
            orientation = getArguments().getString("ORIENTATION_KEY");
            complicatedAction = getArguments().getString("COMPLICATED_ACTION_KEY");
            evaluation = getArguments().getString("EVALUATION_KEY");
            resolution = getArguments().getString("RESOLUTION_KEY");
            message = getArguments().getString("MESSAGE_KEY");
            stageRelated = getArguments().getString("STAGE_RELATED_KEY");
            contextRelated = getArguments().getString("CONTEXT_RELATED_KEY");
            imageUrl = getArguments().getString("IMAGE_URL_KEY");
        }

        return x;
    }

    String categorySelection = "default";
    String stageSelection = "default";

    /*
        SEND DATA TO FRAGMENT
        */
    private void sendDataToSideFragments() {
        //PACK DATA IN A BUNDLE
        Bundle forSideFragments = new Bundle();
        forSideFragments.putString("CATEGORY_KEY", categorySelection);
        forSideFragments.putString("STAGE_KEY", stageSelection);
        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        categoryFragment.setArguments(forSideFragments);
        stageFragment.setArguments(forSideFragments);
    }

    private void sendDataMetaFirstFormFragment() {
        //PACK DATA IN A BUNDLE
        Bundle forMetaFirstFormBundle = new Bundle();
        forMetaFirstFormBundle.putString("USERID_KEY", userIdString);
        if (getArguments().getString("UPDATE_KEY") != null) {
            forMetaFirstFormBundle.putString("UPDATE_KEY", actionString);
        }
        if ((actionString != null) && actionString.equals("update")){
            forMetaFirstFormBundle.putString("STORY_ID_KEY", storyIdString);
            forMetaFirstFormBundle.putString("STORY_TITLE_KEY", storyTitle);
            forMetaFirstFormBundle.putString("IF_OTHER_SPECIFY_KEY", ifOtherSpecify);
            forMetaFirstFormBundle.putString("AUTHOR_ID_KEY", authorIdString);
            forMetaFirstFormBundle.putString("STORY_DESCRIPTION_KEY", storyDescription);
            forMetaFirstFormBundle.putString("ORIENTATION_KEY", orientation);
            forMetaFirstFormBundle.putString("COMPLICATED_ACTION_KEY", complicatedAction);
            forMetaFirstFormBundle.putString("EVALUATION_KEY", evaluation);
            forMetaFirstFormBundle.putString("RESOLUTION_KEY", resolution);
            forMetaFirstFormBundle.putString("MESSAGE_KEY", message);
            forMetaFirstFormBundle.putString("STAGE_RELATED_KEY", stageRelated);
            forMetaFirstFormBundle.putString("CONTEXT_RELATED_KEY", contextRelated);
            forMetaFirstFormBundle.putString("IMAGE_URL_KEY", imageUrl);
        } else if (savedInstanceStateTest == 1){
            forMetaFirstFormBundle.putString("STORY_ID_KEY", storyIdString);
            forMetaFirstFormBundle.putString("STORY_TITLE_KEY", storyTitle);
            forMetaFirstFormBundle.putString("IF_OTHER_SPECIFY_KEY", ifOtherSpecify);
            forMetaFirstFormBundle.putString("AUTHOR_ID_KEY", authorIdString);
            forMetaFirstFormBundle.putString("STORY_DESCRIPTION_KEY", storyDescription);
            forMetaFirstFormBundle.putString("ORIENTATION_KEY", orientation);
            forMetaFirstFormBundle.putString("COMPLICATED_ACTION_KEY", complicatedAction);
            forMetaFirstFormBundle.putString("EVALUATION_KEY", evaluation);
            forMetaFirstFormBundle.putString("RESOLUTION_KEY", resolution);
            forMetaFirstFormBundle.putString("MESSAGE_KEY", message);
            forMetaFirstFormBundle.putString("STAGE_RELATED_KEY", stageRelated);
            forMetaFirstFormBundle.putString("CONTEXT_RELATED_KEY", contextRelated);
            forMetaFirstFormBundle.putString("IMAGE_URL_KEY", imageUrl);
        }
        metaFirstFormFragment.setArguments(forMetaFirstFormBundle);
    }

    //Subscribers to the events - The method is called when a EventBus event is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCategorySelected(EventBusCategorySelected eventBusCategorySelected){
        categorySelection = eventBusCategorySelected.message;
        sendDataToSideFragments();
        Toast.makeText(getActivity(), categorySelection, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStageSelected(EventBusStageSelected eventBusStageSelected){
        stageSelection = eventBusStageSelected.message;
        sendDataToSideFragments();
        Toast.makeText(getActivity(), stageSelection, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSavedInstanceStateForm(EventBusOnSavedInstanceStateForm eventBusStageOnSavedInstanceStateForm){
        userIdString = eventBusStageOnSavedInstanceStateForm.userIdString;
        actionString = eventBusStageOnSavedInstanceStateForm.actionString;
        storyIdString = eventBusStageOnSavedInstanceStateForm.storyIdString;
        storyTitle = eventBusStageOnSavedInstanceStateForm.storyTitle;
        ifOtherSpecify = eventBusStageOnSavedInstanceStateForm.ifOtherSpecify;
        authorIdString = eventBusStageOnSavedInstanceStateForm.authorIdString;
        storyDescription = eventBusStageOnSavedInstanceStateForm.storyDescription;
        orientation = eventBusStageOnSavedInstanceStateForm.orientation;
        complicatedAction = eventBusStageOnSavedInstanceStateForm.complicatedAction;
        evaluation = eventBusStageOnSavedInstanceStateForm.evaluation;
        resolution = eventBusStageOnSavedInstanceStateForm.resolution;
        message = eventBusStageOnSavedInstanceStateForm.message;
        stageRelated = eventBusStageOnSavedInstanceStateForm.stageRelated;
        contextRelated = eventBusStageOnSavedInstanceStateForm.contextRelated;
        imageUrl = eventBusStageOnSavedInstanceStateForm.imageUrl;
        savedInstanceStateTest = 1;

        sendDataMetaFirstFormFragment();
        //Toast.makeText(getActivity(), stageSelection, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 :
                    sendDataMetaFirstFormFragment();
                    sendDataToSideFragments();
                    return metaFirstFormFragment;
                case 1 :
                    sendDataToSideFragments();
                    return categoryFragment;
                case 2 :
                    sendDataToSideFragments();
                    return stageFragment;
            }
            return
                    null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Story Form";
                case 1 :
                    return "Topic Tips";
                case 2 :
                    return "Stage Tips";
            }
            return null;
        }
    }

}