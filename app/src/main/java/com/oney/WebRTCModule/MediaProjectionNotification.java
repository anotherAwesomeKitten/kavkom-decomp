package com.oney.WebRTCModule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes2.dex */
class MediaProjectionNotification {
    static final String ONGOING_CONFERENCE_CHANNEL_ID = "OngoingConferenceChannel";
    private static final String TAG = "MediaProjectionNotification";

    MediaProjectionNotification() {
    }

    static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        if (context == null) {
            Log.d(TAG, " Cannot create notification channel: no current context");
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager.getNotificationChannel(ONGOING_CONFERENCE_CHANNEL_ID) != null) {
            return;
        }
        NotificationChannel notificationChannel = new NotificationChannel(ONGOING_CONFERENCE_CHANNEL_ID, context.getString(R.string.ongoing_notification_channel_name), 3);
        notificationChannel.enableLights(false);
        notificationChannel.enableVibration(false);
        notificationChannel.setShowBadge(false);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    static Notification buildMediaProjectionNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ONGOING_CONFERENCE_CHANNEL_ID);
        builder.setCategory(NotificationCompat.CATEGORY_CALL).setContentTitle(context.getString(R.string.media_projection_notification_title)).setContentText(context.getString(R.string.media_projection_notification_text)).setPriority(-1).setOngoing(false).setUsesChronometer(false).setAutoCancel(true).setVisibility(1).setOnlyAlertOnce(true).setSmallIcon(context.getResources().getIdentifier("ic_notification", "drawable", context.getPackageName())).setForegroundServiceBehavior(1);
        return builder.build();
    }
}
