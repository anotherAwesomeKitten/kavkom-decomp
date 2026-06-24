package com.dieam.reactnativepushnotification.modules;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.dieam.reactnativepushnotification.modules.RNPushNotificationPicturesAggregator;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class RNPushNotificationHelper {
    private static final long DEFAULT_VIBRATION = 300;
    public static final String PREFERENCES_KEY = "rn_push_notification";
    private RNPushNotificationConfig config;
    private Context context;
    private final SharedPreferences scheduledNotificationsPersistence;

    public RNPushNotificationHelper(Application application) {
        this.context = application;
        this.config = new RNPushNotificationConfig(application);
        this.scheduledNotificationsPersistence = application.getSharedPreferences(PREFERENCES_KEY, 0);
    }

    public Class getMainActivityClass() {
        try {
            return Class.forName(this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName()).getComponent().getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private AlarmManager getAlarmManager() {
        return (AlarmManager) this.context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    public void invokeApp(Bundle bundle) {
        try {
            Intent intent = new Intent(this.context, Class.forName(this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName()).getComponent().getClassName()));
            if (bundle != null) {
                intent.putExtra("notification", bundle);
            }
            intent.addFlags(268435456);
            this.context.startActivity(intent);
        } catch (Exception e) {
            Log.e(RNPushNotification.LOG_TAG, "Class not found", e);
        }
    }

    private PendingIntent toScheduleNotificationIntent(Bundle bundle) {
        try {
            int i = Integer.parseInt(bundle.getString("id"));
            Intent intent = new Intent(this.context, (Class<?>) RNPushNotificationPublisher.class);
            intent.putExtra("notificationId", i);
            intent.putExtras(bundle);
            return PendingIntent.getBroadcast(this.context, i, intent, 201326592);
        } catch (Exception e) {
            Log.e(RNPushNotification.LOG_TAG, "Unable to parse Notification ID", e);
            return null;
        }
    }

    public void sendNotificationScheduled(Bundle bundle) {
        if (getMainActivityClass() == null) {
            Log.e(RNPushNotification.LOG_TAG, "No activity class found for the scheduled notification");
            return;
        }
        if (bundle.getString("message") == null) {
            Log.e(RNPushNotification.LOG_TAG, "No message specified for the scheduled notification");
            return;
        }
        if (bundle.getString("id") == null) {
            Log.e(RNPushNotification.LOG_TAG, "No notification ID specified for the scheduled notification");
            return;
        }
        if (bundle.getDouble("fireDate") == 0.0d) {
            Log.e(RNPushNotification.LOG_TAG, "No date specified for the scheduled notification");
            return;
        }
        RNPushNotificationAttributes rNPushNotificationAttributes = new RNPushNotificationAttributes(bundle);
        String id = rNPushNotificationAttributes.getId();
        Log.d(RNPushNotification.LOG_TAG, "Storing push notification with id " + id);
        SharedPreferences.Editor editorEdit = this.scheduledNotificationsPersistence.edit();
        editorEdit.putString(id, rNPushNotificationAttributes.toJson().toString());
        editorEdit.apply();
        if (!this.scheduledNotificationsPersistence.contains(id)) {
            Log.e(RNPushNotification.LOG_TAG, "Failed to save " + id);
        }
        sendNotificationScheduledCore(bundle);
    }

    public void sendNotificationScheduledCore(Bundle bundle) {
        long j = (long) bundle.getDouble("fireDate");
        boolean z = bundle.getBoolean("allowWhileIdle");
        PendingIntent scheduleNotificationIntent = toScheduleNotificationIntent(bundle);
        if (scheduleNotificationIntent == null) {
            return;
        }
        Log.d(RNPushNotification.LOG_TAG, String.format("Setting a notification with id %s at time %s", bundle.getString("id"), Long.toString(j)));
        if (z) {
            getAlarmManager().setExactAndAllowWhileIdle(0, j, scheduleNotificationIntent);
        } else {
            getAlarmManager().setExact(0, j, scheduleNotificationIntent);
        }
    }

    public void sendToNotificationCentre(final Bundle bundle) {
        RNPushNotificationPicturesAggregator rNPushNotificationPicturesAggregator = new RNPushNotificationPicturesAggregator(new RNPushNotificationPicturesAggregator.Callback() { // from class: com.dieam.reactnativepushnotification.modules.RNPushNotificationHelper.1
            @Override // com.dieam.reactnativepushnotification.modules.RNPushNotificationPicturesAggregator.Callback
            public void call(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
                RNPushNotificationHelper.this.sendToNotificationCentreWithPicture(bundle, bitmap, bitmap2, bitmap3);
            }
        });
        rNPushNotificationPicturesAggregator.setLargeIconUrl(this.context, bundle.getString("largeIconUrl"));
        rNPushNotificationPicturesAggregator.setBigLargeIconUrl(this.context, bundle.getString("bigLargeIconUrl"));
        rNPushNotificationPicturesAggregator.setBigPictureUrl(this.context, bundle.getString("bigPictureUrl"));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0206 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0219 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0251 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x029d A[Catch: Exception -> 0x04bf, TRY_ENTER, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02b0 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02c5 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02d6 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02f0 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x031e A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0326 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0348 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03a6 A[Catch: JSONException -> 0x03b7, Exception -> 0x04bf, TRY_LEAVE, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x047e A[Catch: Exception -> 0x04bd, TryCatch #1 {Exception -> 0x04bd, blocks: (B:198:0x03c3, B:200:0x03c9, B:201:0x03cd, B:203:0x0404, B:204:0x0407, B:206:0x0417, B:210:0x0469, B:207:0x044c, B:209:0x045e, B:211:0x0473, B:213:0x047e, B:214:0x048a, B:216:0x0490, B:222:0x04b9, B:218:0x0498, B:220:0x04ab, B:221:0x04b6, B:195:0x03bc, B:186:0x039e, B:188:0x03a6), top: B:234:0x039e, inners: #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0498 A[Catch: Exception -> 0x04bd, TryCatch #1 {Exception -> 0x04bd, blocks: (B:198:0x03c3, B:200:0x03c9, B:201:0x03cd, B:203:0x0404, B:204:0x0407, B:206:0x0417, B:210:0x0469, B:207:0x044c, B:209:0x045e, B:211:0x0473, B:213:0x047e, B:214:0x048a, B:216:0x0490, B:222:0x04b9, B:218:0x0498, B:220:0x04ab, B:221:0x04b6, B:195:0x03bc, B:186:0x039e, B:188:0x03a6), top: B:234:0x039e, inners: #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x04ab A[Catch: Exception -> 0x04bd, TryCatch #1 {Exception -> 0x04bd, blocks: (B:198:0x03c3, B:200:0x03c9, B:201:0x03cd, B:203:0x0404, B:204:0x0407, B:206:0x0417, B:210:0x0469, B:207:0x044c, B:209:0x045e, B:211:0x0473, B:213:0x047e, B:214:0x048a, B:216:0x0490, B:222:0x04b9, B:218:0x0498, B:220:0x04ab, B:221:0x04b6, B:195:0x03bc, B:186:0x039e, B:188:0x03a6), top: B:234:0x039e, inners: #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x04b6 A[Catch: Exception -> 0x04bd, TryCatch #1 {Exception -> 0x04bd, blocks: (B:198:0x03c3, B:200:0x03c9, B:201:0x03cd, B:203:0x0404, B:204:0x0407, B:206:0x0417, B:210:0x0469, B:207:0x044c, B:209:0x045e, B:211:0x0473, B:213:0x047e, B:214:0x048a, B:216:0x0490, B:222:0x04b9, B:218:0x0498, B:220:0x04ab, B:221:0x04b6, B:195:0x03bc, B:186:0x039e, B:188:0x03a6), top: B:234:0x039e, inners: #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cf A[PHI: r17
      0x00cf: PHI (r17v14 java.lang.String) = 
      (r17v5 java.lang.String)
      (r17v6 java.lang.String)
      (r17v7 java.lang.String)
      (r17v8 java.lang.String)
      (r17v9 java.lang.String)
      (r17v15 java.lang.String)
     binds: [B:37:0x00c4, B:34:0x00b8, B:31:0x00ac, B:29:0x009b, B:27:0x0097, B:41:0x00cd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x011d A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016a A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0175 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x017e A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0184 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0193 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01be A[Catch: Exception -> 0x04bf, TRY_LEAVE, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01d8 A[Catch: Exception -> 0x04bf, TryCatch #0 {Exception -> 0x04bf, blocks: (B:26:0x0091, B:43:0x00d0, B:45:0x00d9, B:61:0x0115, B:63:0x011d, B:64:0x0123, B:66:0x016a, B:67:0x016d, B:69:0x0175, B:70:0x0178, B:72:0x017e, B:75:0x018b, B:77:0x0193, B:78:0x019a, B:81:0x01a8, B:83:0x01ae, B:85:0x01b4, B:93:0x01ca, B:96:0x01d3, B:98:0x01d8, B:100:0x01e0, B:102:0x01e6, B:104:0x01ec, B:110:0x01fd, B:113:0x0206, B:114:0x0209, B:116:0x0219, B:119:0x0220, B:121:0x0228, B:123:0x022e, B:125:0x0234, B:127:0x023b, B:132:0x0271, B:135:0x029d, B:136:0x02a0, B:138:0x02a7, B:144:0x02bf, B:147:0x02c8, B:149:0x02d0, B:152:0x02dd, B:154:0x02f0, B:158:0x02fe, B:160:0x0318, B:170:0x0342, B:172:0x0348, B:174:0x0350, B:175:0x0353, B:177:0x0361, B:179:0x0369, B:180:0x0370, B:182:0x037e, B:184:0x0386, B:185:0x038d, B:186:0x039e, B:188:0x03a6, B:162:0x031e, B:164:0x0326, B:169:0x0334, B:157:0x02fb, B:151:0x02d6, B:146:0x02c5, B:142:0x02b0, B:128:0x0251, B:130:0x0259, B:131:0x0263, B:107:0x01f5, B:88:0x01be, B:74:0x0184, B:52:0x00f5, B:53:0x00fc, B:56:0x0106, B:29:0x009b, B:30:0x00a4, B:33:0x00b0, B:36:0x00bc), top: B:232:0x0081 }] */
    /* JADX WARN: Type inference failed for: r11v29, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v32, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v33 */
    /* JADX WARN: Type inference failed for: r11v46 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendToNotificationCentreWithPicture(android.os.Bundle r29, android.graphics.Bitmap r30, android.graphics.Bitmap r31, android.graphics.Bitmap r32) {
        /*
            Method dump skipped, instruction units count: 1250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dieam.reactnativepushnotification.modules.RNPushNotificationHelper.sendToNotificationCentreWithPicture(android.os.Bundle, android.graphics.Bitmap, android.graphics.Bitmap, android.graphics.Bitmap):void");
    }

    private void scheduleNextNotificationIfRepeating(Bundle bundle) {
        long timeInMillis;
        String string = bundle.getString("repeatType");
        long j = (long) bundle.getDouble("repeatTime");
        if (string != null) {
            long j2 = (long) bundle.getDouble("fireDate");
            if (!Arrays.asList("time", "month", "week", "day", "hour", "minute").contains(string)) {
                Log.w(RNPushNotification.LOG_TAG, String.format("Invalid repeatType specified as %s", string));
                return;
            }
            if ("time".equals(string) && j <= 0) {
                Log.w(RNPushNotification.LOG_TAG, "repeatType specified as time but no repeatTime has been mentioned");
                return;
            }
            if ("time".equals(string)) {
                timeInMillis = j2 + j;
            } else {
                int repeatField = getRepeatField(string);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(j2);
                calendar.add(repeatField, j > 0 ? (int) j : 1);
                timeInMillis = calendar.getTimeInMillis();
            }
            if (timeInMillis != 0) {
                Log.d(RNPushNotification.LOG_TAG, String.format("Repeating notification with id %s at time %s", bundle.getString("id"), Long.toString(timeInMillis)));
                bundle.putDouble("fireDate", timeInMillis);
                sendNotificationScheduled(bundle);
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private int getRepeatField(String str) {
        switch (str.hashCode()) {
            case -1074026988:
                return str.equals("minute") ? 12 : 5;
            case 99228:
                str.equals("day");
                return 5;
            case 3208676:
                return str.equals("hour") ? 10 : 5;
            case 3645428:
                return str.equals("week") ? 3 : 5;
            case 104080000:
                return str.equals("month") ? 2 : 5;
            default:
                return 5;
        }
    }

    private Uri getSoundUri(String str) {
        int identifier;
        if (str == null || Constants.COLLATION_DEFAULT.equalsIgnoreCase(str)) {
            return RingtoneManager.getDefaultUri(2);
        }
        if (this.context.getResources().getIdentifier(str, "raw", this.context.getPackageName()) != 0) {
            identifier = this.context.getResources().getIdentifier(str, "raw", this.context.getPackageName());
        } else {
            identifier = this.context.getResources().getIdentifier(str.substring(0, str.lastIndexOf(46)), "raw", this.context.getPackageName());
        }
        return Uri.parse("android.resource://" + this.context.getPackageName() + "/" + identifier);
    }

    public void clearNotifications() {
        Log.i(RNPushNotification.LOG_TAG, "Clearing alerts from the notification centre");
        notificationManager().cancelAll();
    }

    public void clearNotification(String str, int i) {
        Log.i(RNPushNotification.LOG_TAG, "Clearing notification: " + i);
        NotificationManager notificationManager = notificationManager();
        if (str != null) {
            notificationManager.cancel(str, i);
        } else {
            notificationManager.cancel(i);
        }
    }

    public void clearDeliveredNotifications(ReadableArray readableArray) {
        NotificationManager notificationManager = notificationManager();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            Log.i(RNPushNotification.LOG_TAG, "Removing notification with id " + string);
            notificationManager.cancel(Integer.parseInt(string));
        }
    }

    public WritableArray getDeliveredNotifications() {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        StatusBarNotification[] activeNotifications = notificationManager().getActiveNotifications();
        Log.i(RNPushNotification.LOG_TAG, "Found " + activeNotifications.length + " delivered notifications");
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            Notification notification = statusBarNotification.getNotification();
            Bundle bundle = notification.extras;
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString(ViewHierarchyNode.JsonKeys.IDENTIFIER, "" + statusBarNotification.getId());
            writableMapCreateMap.putString("title", bundle.getString(NotificationCompat.EXTRA_TITLE));
            writableMapCreateMap.putString("body", bundle.getString(NotificationCompat.EXTRA_TEXT));
            writableMapCreateMap.putString("tag", statusBarNotification.getTag());
            writableMapCreateMap.putString("group", notification.getGroup());
            writableArrayCreateArray.pushMap(writableMapCreateMap);
        }
        return writableArrayCreateArray;
    }

    public WritableArray getScheduledLocalNotifications() {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<Map.Entry<String, ?>> it = this.scheduledNotificationsPersistence.getAll().entrySet().iterator();
        while (it.hasNext()) {
            try {
                RNPushNotificationAttributes rNPushNotificationAttributesFromJson = RNPushNotificationAttributes.fromJson(it.next().getValue().toString());
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("title", rNPushNotificationAttributesFromJson.getTitle());
                writableMapCreateMap.putString("message", rNPushNotificationAttributesFromJson.getMessage());
                writableMapCreateMap.putString("number", rNPushNotificationAttributesFromJson.getNumber());
                writableMapCreateMap.putDouble("date", rNPushNotificationAttributesFromJson.getFireDate());
                writableMapCreateMap.putString("id", rNPushNotificationAttributesFromJson.getId());
                writableMapCreateMap.putString("repeatInterval", rNPushNotificationAttributesFromJson.getRepeatType());
                writableMapCreateMap.putString("soundName", rNPushNotificationAttributesFromJson.getSound());
                writableMapCreateMap.putString("data", rNPushNotificationAttributesFromJson.getUserInfo());
                writableArrayCreateArray.pushMap(writableMapCreateMap);
            } catch (JSONException e) {
                Log.e(RNPushNotification.LOG_TAG, e.getMessage());
            }
        }
        return writableArrayCreateArray;
    }

    public void cancelAllScheduledNotifications() {
        Log.i(RNPushNotification.LOG_TAG, "Cancelling all notifications");
        Iterator<String> it = this.scheduledNotificationsPersistence.getAll().keySet().iterator();
        while (it.hasNext()) {
            cancelScheduledNotification(it.next());
        }
    }

    public void cancelScheduledNotification(String str) {
        Log.i(RNPushNotification.LOG_TAG, "Cancelling notification: " + str);
        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        PendingIntent scheduleNotificationIntent = toScheduleNotificationIntent(bundle);
        if (scheduleNotificationIntent != null) {
            getAlarmManager().cancel(scheduleNotificationIntent);
        }
        if (this.scheduledNotificationsPersistence.contains(str)) {
            SharedPreferences.Editor editorEdit = this.scheduledNotificationsPersistence.edit();
            editorEdit.remove(str);
            editorEdit.apply();
        } else {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find notification " + str);
        }
        try {
            notificationManager().cancel(Integer.parseInt(str));
        } catch (Exception e) {
            Log.e(RNPushNotification.LOG_TAG, "Unable to parse Notification ID " + str, e);
        }
    }

    private NotificationManager notificationManager() {
        return (NotificationManager) this.context.getSystemService("notification");
    }

    public List<String> listChannels() {
        NotificationManager notificationManager;
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 26 && (notificationManager = notificationManager()) != null) {
            Iterator<NotificationChannel> it = notificationManager.getNotificationChannels().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getId());
            }
        }
        return arrayList;
    }

    public boolean channelBlocked(String str) {
        NotificationManager notificationManager;
        NotificationChannel notificationChannel;
        return Build.VERSION.SDK_INT >= 26 && (notificationManager = notificationManager()) != null && (notificationChannel = notificationManager.getNotificationChannel(str)) != null && notificationChannel.getImportance() == 0;
    }

    public boolean channelExists(String str) {
        NotificationManager notificationManager;
        return (Build.VERSION.SDK_INT < 26 || (notificationManager = notificationManager()) == null || notificationManager.getNotificationChannel(str) == null) ? false : true;
    }

    public void deleteChannel(String str) {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT >= 26 && (notificationManager = notificationManager()) != null) {
            notificationManager.deleteNotificationChannel(str);
        }
    }

    private boolean checkOrCreateChannel(NotificationManager notificationManager, String str, String str2, String str3, Uri uri, int i, long[] jArr) {
        if (Build.VERSION.SDK_INT < 26 || notificationManager == null) {
            return false;
        }
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(str);
        if ((notificationChannel != null || str2 == null || str3 == null) && (notificationChannel == null || ((str2 == null || str2.equals(notificationChannel.getName())) && (str3 == null || str3.equals(notificationChannel.getDescription()))))) {
            return false;
        }
        NotificationChannel notificationChannel2 = new NotificationChannel(str, str2, i);
        notificationChannel2.setDescription(str3);
        notificationChannel2.enableLights(true);
        notificationChannel2.enableVibration(jArr != null);
        notificationChannel2.setVibrationPattern(jArr);
        if (uri != null) {
            notificationChannel2.setSound(uri, new AudioAttributes.Builder().setContentType(4).setUsage(5).build());
        } else {
            notificationChannel2.setSound(null, null);
        }
        notificationManager.createNotificationChannel(notificationChannel2);
        return true;
    }

    public boolean createChannel(ReadableMap readableMap) {
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        String string = readableMap.getString("channelId");
        String string2 = readableMap.getString("channelName");
        String string3 = readableMap.hasKey("channelDescription") ? readableMap.getString("channelDescription") : "";
        boolean z = !readableMap.hasKey("playSound") || readableMap.getBoolean("playSound");
        String string4 = readableMap.hasKey("soundName") ? readableMap.getString("soundName") : Constants.COLLATION_DEFAULT;
        return checkOrCreateChannel(notificationManager(), string, string2, string3, z ? getSoundUri(string4) : null, readableMap.hasKey("importance") ? readableMap.getInt("importance") : 4, (readableMap.hasKey("vibrate") && readableMap.getBoolean("vibrate")) ? new long[]{0, DEFAULT_VIBRATION} : null);
    }

    public boolean isApplicationInForeground() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(this.context.getPackageName()) && runningAppProcessInfo.importance == 100 && runningAppProcessInfo.pkgList.length > 0) {
                return true;
            }
        }
        return false;
    }
}
