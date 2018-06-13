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

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class StoryCoreFragment extends Fragment {

    //public static TabLayout tabLayout;
    //public static ViewPager viewPager;
    //public static int int_items = 3;

    String userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString;
    String actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl;

    FragmentManager mFragmentManager;
    Fragment metaFirstFormFragment = new MetaFirstFormFragment();
    Fragment categoryFragment = new CategoryFragment();
    Fragment stageFragment = new StageFragment();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Log.i("onSaveInstanceState", ": CONTAINER");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Log.i("onAttach", ": CONTAINER");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("onCreate", ": CONTAINER");

        if (savedInstanceState != null){
            //Log.i("On Create CONTAINER: ", "SAVEDINSTANCE");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.i("onCreateView", ": CONTAINER");
        /**
         * Set the title bar according to the fragment
         */
        ((MetaFirstActivity) getActivity())
                .setActionBarTitle("Create");

        /**
         *Inflate tab_layout and setup Views.
         */
        View view = inflater.inflate(R.layout.story_core_layout,null);
        //Set up viewPager
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpagerStoryCore);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabsStoryCore);
        tabs.setupWithViewPager(viewPager);


        /*
         *Set an Apater for the View Pager
         *
        mFragmentManager = getChildFragmentManager();
        viewPager.setAdapter(new MyAdapter(mFragmentManager));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         *

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
         */

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */

        userIdString = getArguments().getString("USERID_KEY");
        firstNameString = getArguments().getString("FIRSTNAME_KEY");
        lastNameString = getArguments().getString("LASTNAME_KEY");
        usernameString = getArguments().getString("USERNAME_KEY");
        passwordString = getArguments().getString("PASSWORD_KEY");
        emailString = getArguments().getString("EMAIL_KEY");

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

        sendDataMetaFirstFormFragment();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Log.i("onViewCreated", ": CONTAINER");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.i("onActivityCreated", ": CONTAINER");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //Log.i("onViewStateRestored", ": CONTAINER");
    }


    //ADD FRAGMENTS TO Tabs
    private void setupViewPager(ViewPager viewPager){
        mFragmentManager = getChildFragmentManager();
        //mFragmentTransaction = mFragmentManager.beginTransaction();
        Adapter adapter = new Adapter(mFragmentManager);
        adapter.addFragment(metaFirstFormFragment,"Story Form");
        adapter.addFragment(categoryFragment,"Topic Tips");
        adapter.addFragment(stageFragment,"Stage Tips");
        viewPager.setAdapter(adapter);
    }

    //Custom Adapter
    class Adapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager){
            super (manager);
        }

        @Override
        public Fragment getItem(int position){
            return mFragmentList.get(position);
        }

        @Override
        public int getCount(){
            return  mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return mFragmentTitleList.get(position);
        }
    }

    String categorySelection = "default";
    String stageSelection = "default";

    private void sendDataMetaFirstFormFragment() {
        //PACK DATA IN A BUNDLE
        Bundle forMetaFirstFormBundle = new Bundle();
        forMetaFirstFormBundle.putString("USERID_KEY", userIdString);
        forMetaFirstFormBundle.putString("FIRSTNAME_KEY", firstNameString);
        forMetaFirstFormBundle.putString("LASTNAME_KEY", lastNameString);
        forMetaFirstFormBundle.putString("USERNAME_KEY", usernameString);
        forMetaFirstFormBundle.putString("PASSWORD_KEY", passwordString);
        forMetaFirstFormBundle.putString("EMAIL_KEY", emailString);

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
        }
        metaFirstFormFragment.setArguments(forMetaFirstFormBundle);
    }


    @Override
    public void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
        //Log.i("onStart", ": CONTAINER");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i("onResume", ": CONTAINER");
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.i("onPause", ": CONTAINER");
    }

    @Override
    public void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
        //Log.i("onStop", ": CONTAINER");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.i("onDestroyView", ": CONTAINER");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.i("onDestroy", ": CONTAINER");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //Log.i("onDetach", ": CONTAINER");
    }


    /*
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         *

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
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         *

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
    }*/

}