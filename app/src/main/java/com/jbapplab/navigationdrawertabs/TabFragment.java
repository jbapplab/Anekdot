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
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class TabFragment extends Fragment {

    FragmentManager mFragmentManager;
    Fragment primaryFragment = new PrimaryFragment();
    Fragment tipsFragment = new TipFragment();
    Fragment linksFragment = new LinksFragment();

    TabLayout tabLayout;

    View dashboardTab, tipTab, linkTab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * Set the title bar according to the fragment
         */
        ((UserAreaActivity) getActivity())
                .setActionBarTitle("Home");

        /**
         *Inflate tab_layout and setup Views.
         */
        View view = inflater.inflate(R.layout.tab_layout,null);
        //Set up viewPager
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        dashboardTab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
        tipTab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
        linkTab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(2);

        return view;
    }

    //ADD FRAGMENTS TO Tabs
    private void setupViewPager(ViewPager viewPager){
        mFragmentManager = getChildFragmentManager();
        TabFragment.Adapter adapter = new Adapter(mFragmentManager);
        adapter.addFragment(primaryFragment,"Dashboard");
        adapter.addFragment(tipsFragment,"Tip of the Day");
        adapter.addFragment(linksFragment,"Useful Links");
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

    @Override
    public void onResume() {
        super.onResume();
        //Tutorial
        Boolean firstTime = getActivity().getSharedPreferences("USER_AREA_TUTORIAL",  Context.MODE_PRIVATE).getBoolean("first_time", true);
        if (firstTime) {
            runTutorial();
            SharedPreferences.Editor userAreaPrefsTutorialEditor = getActivity().getSharedPreferences("USER_AREA_TUTORIAL", Context.MODE_PRIVATE).edit();
            userAreaPrefsTutorialEditor.putBoolean("first_time", false).apply();
        }
    }

    void runTutorial() {

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity());

        sequence.setConfig(config);

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(getActivity())
                        .setMaskColour(getResources().getColor(R.color.colorAccent))
                        .setTarget(dashboardTab)
                        .setTitleText("Welcome to Anecdote!")
                        .setContentText("This tutorial will guide you through the basic features.\n\n" +
                                "On the top left you have the main menu that gives you access to all the features.\n\n" +
                                "On the top right you have the options menu and where relevant the option to share information.")
                        .setDismissText("OK, GOT IT!")
                        .withoutShape()
                        .setDelay(1000)
                        .build()
        );

        sequence.addSequenceItem( new MaterialShowcaseView.Builder(getActivity())
                .setMaskColour(getResources().getColor(R.color.colorPrimaryDark))
                .setTarget(dashboardTab)
                .setContentText("This is the Dashboard you can use it to quickly navigate through the app.")
                .setDismissText("OK, GOT IT!")
                .build()
        );

        sequence.addSequenceItem(new MaterialShowcaseView.Builder(getActivity())
                .setMaskColour(getResources().getColor(R.color.colorPrimaryDark))
                .setTarget(tipTab)
                .setContentText("Here you can receive general writing tips.")
                .setDismissText("OK, GOT IT!")
                .build()
        );

        sequence.addSequenceItem(new MaterialShowcaseView.Builder(getActivity())
                .setMaskColour(getResources().getColor(R.color.colorPrimaryDark))
                .setTarget(linkTab)
                .setContentText("And here you can select links of useful tutorials and articles.")
                .setDismissText("OK, GOT IT!")
                .build()
        );

        sequence.start();
    }
}