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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusCategorySelected;
import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusStageSelected;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class StoryCoreFragment extends Fragment {

    //public static TabLayout tabLayout;
    //public static ViewPager viewPager;
    //public static int int_items = 3;

    String userIdString, firstNameString, lastNameString, usernameString, passwordString, emailString;
    String actionString, storyIdString, storyTitle, ifOtherSpecify, authorIdString, storyDescription, orientation, complicatedAction, evaluation, resolution, message, stageRelated, contextRelated, imageUrl, storyCategory, audienceStage, version;

    FragmentManager mFragmentManager;
    Fragment metaFirstFormFragment = new MetaFirstFormFragment();
    Fragment categoryFragment = new CategoryFragment();
    Fragment stageFragment = new StageFragment();

    TabLayout tabs;

    View storyForm, topicTips, stageTips;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("onCreate", ": CONTAINER");

        if (savedInstanceState != null){
            //Log.i("On Create CONTAINER: ", "SAVEDINSTANCE");
        }

        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**
         *Inflate tab_layout and setup Views.
         */
        View view = inflater.inflate(R.layout.story_core_layout,null);
        //Set up viewPager
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpagerStoryCore);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);
        tabs = view.findViewById(R.id.tabsStoryCore);
        tabs.setupWithViewPager(viewPager);

        storyForm = ((ViewGroup) tabs.getChildAt(0)).getChildAt(0);
        topicTips = ((ViewGroup) tabs.getChildAt(0)).getChildAt(1);
        stageTips = ((ViewGroup) tabs.getChildAt(0)).getChildAt(2);

        /*
        UNPACK THE DATA FROM THE BUNDLE
        */

        userIdString = getArguments().getString("USERID_KEY");
        firstNameString = getArguments().getString("FIRSTNAME_KEY");
        lastNameString = getArguments().getString("LASTNAME_KEY");
        usernameString = getArguments().getString("USERNAME_KEY");
        passwordString = getArguments().getString("PASSWORD_KEY");
        emailString = getArguments().getString("EMAIL_KEY");
        version = getArguments().getString("VERSION_KEY");

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
            storyCategory = getArguments().getString("STORY_CATEGORY_KEY");
            audienceStage = getArguments().getString("AUDIENCE_STAGE_KEY");
        }

        if (version.equals("detailed_guidance")) {

            /**
             * Set the title bar according to the fragment
             */
            ((MetaFirstActivity) getActivity())
                    .setActionBarTitle("Detailed");


        } else {

            /**
             * Set the title bar according to the fragment
             */
            ((MetaFirstActivity) getActivity())
                    .setActionBarTitle("Basic");

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
        forMetaFirstFormBundle.putString("VERSION_KEY", version);

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
            forMetaFirstFormBundle.putString("STORY_CATEGORY_KEY", storyCategory);
            forMetaFirstFormBundle.putString("AUDIENCE_STAGE_KEY", audienceStage);
        }
        metaFirstFormFragment.setArguments(forMetaFirstFormBundle);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStageSelected(EventBusStageSelected eventBusStageSelected){
        stageSelection = eventBusStageSelected.message;
        //Toast.makeText(getActivity(), stageSelection, Toast.LENGTH_SHORT).show();
        String stage;
        switch (stageSelection){
            case "Stage 1: The audience is unaware of the problem or issue you are describing.":
                stage = "Stage 1 Tips";
                break;
            case "Stage 2: The audience is aware of the problem or issue you are describing.":
                stage = "Stage 2 Tips";
                break;
            case "Stage 3: The audience wants to act soon regarding the problem or issue.":
                stage = "Stage 3 Tips";
                break;
            case "Stage 4: The audience is already taking action to overcome the problem or issue.":
                stage = "Stage 4 Tips";
                break;
            default:
                stage = "Stage Tips";
        }
        updateStageTitle(stage, tabs);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCategorySelected(EventBusCategorySelected eventBusCategorySelected){
        categorySelection = eventBusCategorySelected.message;
        //Toast.makeText(getActivity(), categorySelection, Toast.LENGTH_SHORT).show();

        String category;
        switch (categorySelection){
            case "Other":
                category = "Other Tips";
                break;
            case "Art":
                category = "Art Tips";
                break;
            case "Causes":
                category = "Causes Tips";
                break;
            case "Education":
                category = "Education Tips";
                break;
            case "Food":
                category = "Food Tips";
                break;
            case "Lifestyle":
                category = "Lifestyle Tips";
                break;
            case "Business":
                category = "Business Tips";
                break;
            case "Sports":
                category = "Sports Tips";
                break;
            case "Travel":
                category = "Travel Tips";
                break;
            case "Security":
                category = "Security Tips";
                break;
            default:
                category = "Topic Tips";
        }
        updateCategoryTitle(category, tabs);
    }

    public void updateStageTitle(String stage, TabLayout tabs){
        tabs.getTabAt(2).setText(stage);
    }

    public void updateCategoryTitle(String category, TabLayout tabs){
        tabs.getTabAt(1).setText(category);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Tutorial
        Boolean firstTime = getActivity().getSharedPreferences("META_FIRST_TUTORIAL",  Context.MODE_PRIVATE).getBoolean("first_time", true);
        if (firstTime) {
            runTutorial();
            SharedPreferences.Editor metaFirstPrefsTutorialEditor = getActivity().getSharedPreferences("META_FIRST_TUTORIAL", Context.MODE_PRIVATE).edit();
            metaFirstPrefsTutorialEditor.putBoolean("first_time", false).apply();
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
                        .setTarget(storyForm)
                        .setTitleText("Create a persuasive story")
                        .setContentText("Here you will find all the information you need to create a persuasive story.\n\n" +
                                "Follow the instructions step by step and make sure you complete all the fields. You can receive more/less guidance by selecting the red \"expand/collapse arrows\".\n\n" +
                                "It is all about making the story more relatable to your audience!")
                        .setDismissText("OK, GOT IT!")
                        .withoutShape()
                        .setDelay(1000)
                        .build()
        );

        sequence.addSequenceItem( new MaterialShowcaseView.Builder(getActivity())
                .setMaskColour(getResources().getColor(R.color.colorPrimaryDark))
                .setTarget(storyForm)
                .setTitleText("Story form")
                .setContentText("Here is where you will capture your ideas, generate and edit your story before you share or post it online.")
                .setDismissText("OK, GOT IT!")
                .build()
        );

        sequence.addSequenceItem(new MaterialShowcaseView.Builder(getActivity())
                .setMaskColour(getResources().getColor(R.color.colorPrimaryDark))
                .setTarget(topicTips)
                .setTitleText("Topic tips")
                .setContentText("After you select a topic for your story, here you will get tailored tips that will help you make your story more relatable.")
                .setDismissText("OK, GOT IT!")
                .build()
        );

        sequence.addSequenceItem(new MaterialShowcaseView.Builder(getActivity())
                .setMaskColour(getResources().getColor(R.color.colorPrimaryDark))
                .setTarget(stageTips)
                .setTitleText("Stage tips")
                .setContentText("After you select the stage that your audience is in, here you will get tailored tips that will help you make your story more relatable.\"")
                .setDismissText("OK, GOT IT!")
                .build()
        );

        sequence.start();
    }
}