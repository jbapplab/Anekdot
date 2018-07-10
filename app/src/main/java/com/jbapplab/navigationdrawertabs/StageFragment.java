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

import com.jbapplab.navigationdrawertabs.m_EventHandling.EventBusStageSelected;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class StageFragment extends Fragment {

    String stageSelection;
    String stage = "default";
    RelativeLayout relativeLayout0;
    RelativeLayout relativeLayout1;
    RelativeLayout relativeLayout2;
    RelativeLayout relativeLayout3;
    RelativeLayout relativeLayoutDefault;

    //Subscribers to the events - The method is called when a EventBus event is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStageSelected(EventBusStageSelected eventBusStageSelected){
        stageSelection = eventBusStageSelected.message;
        //Toast.makeText(getActivity(), stageSelection, Toast.LENGTH_SHORT).show();

        switch (stageSelection){
            case "Stage 1: The audience is unaware of the problem or issue you are describing.":
                stage = "0";
                break;
            case "Stage 2: The audience is aware of the problem or issue you are describing.":
                stage = "1";
                break;
            case "Stage 3: The audience wants to act soon regarding the problem or issue.":
                stage = "2";
                break;
            case "Stage 4: The audience is already taking action to overcome the problem or issue.":
                stage = "3";
                break;
            default:
                stage = "default";
        }
        showStage(stage);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        UNPACK THE DATA FROM THE BUNDLE
        */
        //stageSelection = getArguments().getString("STAGE_KEY");
        //Log.i("stageSelection", stageSelection);

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.stage_fragment_layout, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
                relativeLayout0 = view.findViewById(R.id.stage0);
                relativeLayout1 = view.findViewById(R.id.stage1);
                relativeLayout2 = view.findViewById(R.id.stage2);
                relativeLayout3 = view.findViewById(R.id.stage3);
                relativeLayoutDefault = view.findViewById(R.id.stageDefault);
        showStage(stage);
    }

   public void showStage(String stage){
       switch (stage){
           case "0":
               relativeLayout0.setVisibility(View.VISIBLE);
               relativeLayout1.setVisibility(View.GONE);
               relativeLayout2.setVisibility(View.GONE);
               relativeLayout3.setVisibility(View.GONE);
               relativeLayoutDefault.setVisibility(View.GONE);
               break;
           case "1":
               relativeLayout0.setVisibility(View.GONE);
               relativeLayout1.setVisibility(View.VISIBLE);
               relativeLayout2.setVisibility(View.GONE);
               relativeLayout3.setVisibility(View.GONE);
               relativeLayoutDefault.setVisibility(View.GONE);
               break;
           case "2":
               relativeLayout0.setVisibility(View.GONE);
               relativeLayout1.setVisibility(View.GONE);
               relativeLayout2.setVisibility(View.VISIBLE);
               relativeLayout3.setVisibility(View.GONE);
               relativeLayoutDefault.setVisibility(View.GONE);
               break;
           case "3":
               relativeLayout0.setVisibility(View.GONE);
               relativeLayout1.setVisibility(View.GONE);
               relativeLayout2.setVisibility(View.GONE);
               relativeLayout3.setVisibility(View.VISIBLE);
               relativeLayoutDefault.setVisibility(View.GONE);
               break;
           default:
               relativeLayout0.setVisibility(View.GONE);
               relativeLayout1.setVisibility(View.GONE);
               relativeLayout2.setVisibility(View.GONE);
               relativeLayout3.setVisibility(View.GONE);
               relativeLayoutDefault.setVisibility(View.VISIBLE);
       }
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
}