package com.asterinet.react.bgactions;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

/* JADX INFO: loaded from: classes.dex */
public final class RNBackgroundActionsTask extends HeadlessJsTaskService {
    private static final String CHANNEL_ID = "RN_BACKGROUND_ACTIONS_CHANNEL";
    public static final int SERVICE_NOTIFICATION_ID = 92901;

    /* JADX WARN: Removed duplicated region for block: B:33:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.app.Notification buildNotification(android.content.Context r10, com.asterinet.react.bgactions.BackgroundTaskOptions r11) {
        /*
            java.lang.String r0 = r11.getTaskTitle()
            java.lang.String r1 = r11.getTaskDesc()
            int r2 = r11.getIconInt()
            int r3 = r11.getColor()
            java.lang.String r4 = r11.getLinkingURI()
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L2c
            android.content.Intent r7 = new android.content.Intent
            java.lang.String r8 = "android.intent.action.VIEW"
            android.net.Uri r4 = android.net.Uri.parse(r4)
            r7.<init>(r8, r4)
            android.content.ComponentName r4 = r7.getComponent()
            if (r4 != 0) goto L2a
            goto L39
        L2a:
            r4 = r6
            goto L3a
        L2c:
            android.content.Intent r4 = new android.content.Intent
            java.lang.String r7 = "android.intent.action.MAIN"
            r4.<init>(r7)
            java.lang.String r7 = "android.intent.category.LAUNCHER"
            android.content.Intent r7 = r4.addCategory(r7)
        L39:
            r4 = r5
        L3a:
            int r8 = android.os.Build.VERSION.SDK_INT
            r9 = 31
            if (r8 < r9) goto L50
            if (r4 == 0) goto L49
            r4 = 67108864(0x4000000, float:1.5046328E-36)
            android.app.PendingIntent r4 = android.app.PendingIntent.getActivity(r10, r6, r7, r4)
            goto L56
        L49:
            r4 = 33554432(0x2000000, float:9.403955E-38)
            android.app.PendingIntent r4 = android.app.PendingIntent.getActivity(r10, r6, r7, r4)
            goto L56
        L50:
            r4 = 201326592(0xc000000, float:9.8607613E-32)
            android.app.PendingIntent r4 = android.app.PendingIntent.getActivity(r10, r6, r7, r4)
        L56:
            androidx.core.app.NotificationCompat$Builder r6 = new androidx.core.app.NotificationCompat$Builder
            java.lang.String r7 = "RN_BACKGROUND_ACTIONS_CHANNEL"
            r6.<init>(r10, r7)
            androidx.core.app.NotificationCompat$Builder r10 = r6.setContentTitle(r0)
            androidx.core.app.NotificationCompat$Builder r10 = r10.setContentText(r1)
            androidx.core.app.NotificationCompat$Builder r10 = r10.setSmallIcon(r2)
            androidx.core.app.NotificationCompat$Builder r10 = r10.setContentIntent(r4)
            androidx.core.app.NotificationCompat$Builder r10 = r10.setOngoing(r5)
            r0 = -2
            androidx.core.app.NotificationCompat$Builder r10 = r10.setPriority(r0)
            androidx.core.app.NotificationCompat$Builder r10 = r10.setColor(r3)
            android.os.Bundle r11 = r11.getProgressBar()
            if (r11 == 0) goto La0
            java.lang.String r0 = "max"
            double r0 = r11.getDouble(r0)
            double r0 = java.lang.Math.floor(r0)
            int r0 = (int) r0
            java.lang.String r1 = "value"
            double r1 = r11.getDouble(r1)
            double r1 = java.lang.Math.floor(r1)
            int r1 = (int) r1
            java.lang.String r2 = "indeterminate"
            boolean r11 = r11.getBoolean(r2)
            r10.setProgress(r0, r1, r11)
        La0:
            android.app.Notification r10 = r10.build()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.asterinet.react.bgactions.RNBackgroundActionsTask.buildNotification(android.content.Context, com.asterinet.react.bgactions.BackgroundTaskOptions):android.app.Notification");
    }

    @Override // com.facebook.react.HeadlessJsTaskService
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            return new HeadlessJsTaskConfig(extras.getString("taskName"), Arguments.fromBundle(extras), 0L, true);
        }
        return null;
    }

    @Override // com.facebook.react.HeadlessJsTaskService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            throw new IllegalArgumentException("Extras cannot be null");
        }
        BackgroundTaskOptions backgroundTaskOptions = new BackgroundTaskOptions(extras);
        createNotificationChannel(backgroundTaskOptions.getTaskTitle(), backgroundTaskOptions.getTaskDesc());
        startForeground(SERVICE_NOTIFICATION_ID, buildNotification(this, backgroundTaskOptions));
        return super.onStartCommand(intent, i, i2);
    }

    private void createNotificationChannel(String str, String str2) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, str, 2);
            notificationChannel.setDescription(str2);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }
}
