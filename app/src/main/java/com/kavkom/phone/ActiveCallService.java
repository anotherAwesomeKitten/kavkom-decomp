package com.kavkom.phone;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveCallService extends Service {
    private static final String CHANNEL_ID = "active_call_channel";
    private static final int NOTIFICATION_ID = 9999;
    private static final String TAG = "[ActiveCallService]";
    private PowerManager.WakeLock wakeLock = null;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        Log.d(TAG, "Service created");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String stringExtra = intent != null ? intent.getStringExtra("callUUID") : null;
        String stringExtra2 = intent != null ? intent.getStringExtra("callerName") : "Active call";
        Log.d(TAG, "Starting foreground service for call: " + stringExtra);
        try {
            startForeground(NOTIFICATION_ID, createNotification(stringExtra2));
            acquireWakeLock();
            return 1;
        } catch (Exception e) {
            Log.e(TAG, "Failed to start foreground service: " + e.getMessage(), e);
            try {
                acquireWakeLock();
                return 1;
            } catch (Exception e2) {
                Log.e(TAG, "Failed to acquire wake lock: " + e2.getMessage(), e2);
                return 1;
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        releaseWakeLock();
        Log.d(TAG, "Service destroyed");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Active Call", 2);
            notificationChannel.setDescription("Keeps microphone active during calls");
            notificationChannel.setShowBadge(false);
            notificationChannel.setSound(null, null);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    private Notification createNotification(String str) {
        PendingIntent activity;
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.setFlags(268468224);
        if (Build.VERSION.SDK_INT >= 31) {
            activity = PendingIntent.getActivity(this, 0, intent, 167772160);
        } else {
            activity = PendingIntent.getActivity(this, 0, intent, 134217728);
        }
        return new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Call in progress").setContentText(str).setSmallIcon(R.drawable.logo).setContentIntent(activity).setOngoing(true).setPriority(-1).setCategory(NotificationCompat.CATEGORY_CALL).setVisibility(1).setSilent(true).build();
    }

    private void acquireWakeLock() {
        try {
            PowerManager powerManager = (PowerManager) getSystemService("power");
            if (powerManager != null) {
                PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, MainActivity.class.getCanonicalName() + ":ActiveCall");
                this.wakeLock = wakeLockNewWakeLock;
                wakeLockNewWakeLock.acquire();
                Log.d(TAG, "Wake lock acquired");
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to acquire wake lock: " + e.getMessage(), e);
        }
    }

    private void releaseWakeLock() {
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        try {
            this.wakeLock.release();
            Log.d(TAG, "Wake lock released");
        } catch (Exception e) {
            Log.e(TAG, "Error releasing wake lock: " + e.getMessage(), e);
        }
        this.wakeLock = null;
    }
}
