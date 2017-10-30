package com.jbapplab.navigationdrawertabs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by JohnB on 30/10/2017.
 */

public class CopyTextView extends android.support.v7.widget.AppCompatEditText {

    private boolean mEnabled; // is this edittext enabled

    public CopyTextView(Context context) {
        super(context);
    }

    public CopyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CopyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            if (!mEnabled) return;
            super.setEnabled(false);
            super.setEnabled(mEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
        super.setEnabled(enabled);
    }}