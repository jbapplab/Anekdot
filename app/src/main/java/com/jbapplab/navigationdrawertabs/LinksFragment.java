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
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LinksFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.updates_layout,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final TextView link1 = getView().findViewById(R.id.tvLink1);
        final TextView link2 = getView().findViewById(R.id.tvLink2);
        final TextView link3 = getView().findViewById(R.id.tvLink3);
        //final TextView link4 = getView().findViewById(R.id.tvLink4);
        //final TextView link5 = getView().findViewById(R.id.tvLink5);

        link1.setMovementMethod(LinkMovementMethod.getInstance());
        link2.setMovementMethod(LinkMovementMethod.getInstance());
        link3.setMovementMethod(LinkMovementMethod.getInstance());
        //link4.setMovementMethod(LinkMovementMethod.getInstance());
        //link5.setMovementMethod(LinkMovementMethod.getInstance());

    }
}