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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StoryCoreFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    String userIdString, actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl;

    MetaFirstFormFragment metaFirstFormFragment = new MetaFirstFormFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        userIdString = getArguments().getString("USERID_KEY").toString();
        if (getArguments().getString("UPDATE_KEY") != null) {
            actionString = getArguments().getString("UPDATE_KEY").toString();
        }
        if ((actionString != null) && actionString.equals("update")) {
            storyIdString = getArguments().getString("STORY_ID_KEY").toString();
            storyTitle = getArguments().getString("STORY_TITLE_KEY").toString();
            ifOtherSpecify = getArguments().getString("IF_OTHER_SPECIFY_KEY").toString();
            authorIdString = getArguments().getString("AUTHOR_ID_KEY").toString();
            storyDescription = getArguments().getString("STORY_DESCRIPTION_KEY").toString();
            orientation = getArguments().getString("ORIENTATION_KEY").toString();
            complicatedAction = getArguments().getString("COMPLICATED_ACTION_KEY").toString();
            evaluation = getArguments().getString("EVALUATION_KEY").toString();
            resolution = getArguments().getString("RESOLUTION_KEY").toString();
            message = getArguments().getString("MESSAGE_KEY").toString();
            stageRelated = getArguments().getString("STAGE_RELATED_KEY").toString();
            contextRelated = getArguments().getString("CONTEXT_RELATED_KEY").toString();
            imageUrl = getArguments().getString("IMAGE_URL_KEY").toString();
        }

        return x;
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
                    return metaFirstFormFragment;
                case 1 :

                    return new SocialFragment();
                case 2 :

                    return new UpdatesFragment();
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

        /*
    SEND DATA TO FRAGMENT
     */
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
            }
            //PASS OVER THE BUNDLE TO OUR FRAGMENT
            metaFirstFormFragment.setArguments(forMetaFirstFormBundle);
        }
    }

}