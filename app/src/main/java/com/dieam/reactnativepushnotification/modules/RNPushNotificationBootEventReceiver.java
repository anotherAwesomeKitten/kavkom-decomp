package com.dieam.reactnativepushnotification.modules;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class RNPushNotificationBootEventReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i(RNPushNotification.LOG_TAG, "RNPushNotificationBootEventReceiver loading scheduled notifications");
        SharedPreferences sharedPreferences = context.getSharedPreferences(RNPushNotificationHelper.PREFERENCES_KEY, 0);
        Set<String> setKeySet = sharedPreferences.getAll().keySet();
        RNPushNotificationHelper rNPushNotificationHelper = new RNPushNotificationHelper((Application) context.getApplicationContext());
        for (String str : setKeySet) {
            try {
                String string = sharedPreferences.getString(str, null);
                if (string != null) {
                    RNPushNotificationAttributes rNPushNotificationAttributesFromJson = RNPushNotificationAttributes.fromJson(string);
                    if (rNPushNotificationAttributesFromJson.getFireDate() < System.currentTimeMillis()) {
                        Log.i(RNPushNotification.LOG_TAG, "RNPushNotificationBootEventReceiver: Showing notification for " + rNPushNotificationAttributesFromJson.getId());
                        rNPushNotificationHelper.sendToNotificationCentre(rNPushNotificationAttributesFromJson.toBundle());
                    } else {
                        Log.i(RNPushNotification.LOG_TAG, "RNPushNotificationBootEventReceiver: Scheduling notification for " + rNPushNotificationAttributesFromJson.getId());
                        rNPushNotificationHelper.sendNotificationScheduledCore(rNPushNotificationAttributesFromJson.toBundle());
                    }
                }
            } catch (Exception e) {
                Log.e(RNPushNotification.LOG_TAG, "Problem with boot receiver loading notification " + str, e);
            }
        }
    }
}
