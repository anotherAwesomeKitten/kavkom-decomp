package com.oney.WebRTCModule;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class MediaProjectionService extends Service {
    static final int NOTIFICATION_ID = new Random().nextInt(99999) + 10000;
    private static final String TAG = "MediaProjectionService";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void launch(Context context) {
        ComponentName componentNameStartService;
        if (WebRTCModuleOptions.getInstance().enableMediaProjectionService) {
            MediaProjectionNotification.createNotificationChannel(context);
            Intent intent = new Intent(context, (Class<?>) MediaProjectionService.class);
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    componentNameStartService = context.startForegroundService(intent);
                } else {
                    componentNameStartService = context.startService(intent);
                }
                if (componentNameStartService == null) {
                    Log.w(TAG, "Media projection service not started");
                } else {
                    Log.i(TAG, "Media projection service started");
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "Media projection service not started", e);
            }
        }
    }

    public static void abort(Context context) {
        if (WebRTCModuleOptions.getInstance().enableMediaProjectionService) {
            context.stopService(new Intent(context, (Class<?>) MediaProjectionService.class));
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Notification notificationBuildMediaProjectionNotification = MediaProjectionNotification.buildMediaProjectionNotification(this);
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(NOTIFICATION_ID, notificationBuildMediaProjectionNotification, 32);
            return 2;
        }
        startForeground(NOTIFICATION_ID, notificationBuildMediaProjectionNotification);
        return 2;
    }
}
