package com.jbapplab.navigationdrawertabs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        //Use shared preferences to save user info
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userId = userInfo.getString("userId", "");
        String firstName = userInfo.getString("firstName", "");
        String lastName = userInfo.getString("lastName", "");
        String username = userInfo.getString("username", "");
        String password = userInfo.getString("password", "");
        String email = userInfo.getString("email", "");

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class,
                        localData.get_hour(), localData.get_min());
                return;
            }
        }
        //Trigger the notification
        NotificationScheduler.showNotification(context, MetaFirstActivity.class,
                "Hey! How is your inspiration?", "w!rite a compelling story now?", userId, firstName, lastName, username, password, email);
    }
}
