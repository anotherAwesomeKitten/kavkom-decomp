package com.dieam.reactnativepushnotification.modules;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.res.ResourcesCompat;
import com.google.firebase.messaging.CommonNotificationBuilder;

/* JADX INFO: loaded from: classes.dex */
class RNPushNotificationConfig {
    private static final String KEY_NOTIFICATION_COLOR = "com.dieam.reactnativepushnotification.notification_color";
    private static final String KEY_NOTIFICATION_DEFAULT_CHANNEL_ID = "com.dieam.reactnativepushnotification.default_notification_channel_id";
    private static final String KEY_NOTIFICATION_FIREBASE_DEFAULT_CHANNEL_ID = "com.google.firebase.messaging.default_notification_channel_id";
    private static final String KEY_NOTIFICATION_FOREGROUND = "com.dieam.reactnativepushnotification.notification_foreground";
    private static Bundle metadata;
    private Context context;

    public RNPushNotificationConfig(Context context) {
        this.context = context;
        if (metadata == null) {
            try {
                metadata = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Log.e(RNPushNotification.LOG_TAG, "Error reading application meta, falling back to defaults");
                metadata = new Bundle();
            }
        }
    }

    private String getStringValue(String str, String str2) {
        try {
            String string = metadata.getString(str);
            if (string != null) {
                if (string.length() > 0) {
                    return string;
                }
            }
            return str2;
        } catch (Exception unused) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + str + " in manifest. Falling back to default");
            return str2;
        }
    }

    public int getNotificationColor() {
        try {
            return ResourcesCompat.getColor(this.context.getResources(), metadata.getInt(KEY_NOTIFICATION_COLOR), null);
        } catch (Exception unused) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find com.dieam.reactnativepushnotification.notification_color in manifest. Falling back to default");
            return -1;
        }
    }

    public boolean getNotificationForeground() {
        try {
            return metadata.getBoolean(KEY_NOTIFICATION_FOREGROUND, false);
        } catch (Exception unused) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find com.dieam.reactnativepushnotification.notification_foreground in manifest. Falling back to default");
            return false;
        }
    }

    public String getNotificationDefaultChannelId() {
        try {
            return getStringValue(KEY_NOTIFICATION_DEFAULT_CHANNEL_ID, getStringValue("com.google.firebase.messaging.default_notification_channel_id", CommonNotificationBuilder.FCM_FALLBACK_NOTIFICATION_CHANNEL));
        } catch (Exception unused) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find com.dieam.reactnativepushnotification.default_notification_channel_id in manifest. Falling back to default");
            return CommonNotificationBuilder.FCM_FALLBACK_NOTIFICATION_CHANNEL;
        }
    }
}
