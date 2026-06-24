package com.kavkom.phone;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import io.sentry.protocol.DebugImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.webrtc.MediaStreamTrack;

/* JADX INFO: loaded from: classes2.dex */
public class CallkeepHelperModule extends ReactContextBaseJavaModule {
    private static final String TAG = "CallkeepHelperModule";
    public static NotificationManager notificationManager;
    public static ReactApplicationContext reactContext;
    private static AtomicInteger notificationIdCounter = new AtomicInteger(0);
    private static Map<String, Boolean> acceptedFromLockedStateMap = new HashMap();

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    CallkeepHelperModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactContext = reactApplicationContext;
        notificationManager = (NotificationManager) reactApplicationContext.getSystemService(NotificationManager.class);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    private void dismissKeyguard() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            Log.w(TAG, "Cannot dismiss keyguard: no current activity");
            return;
        }
        KeyguardManager keyguardManager = (KeyguardManager) reactContext.getSystemService("keyguard");
        if (keyguardManager.isKeyguardLocked()) {
            Log.d(TAG, "lockscreen");
            keyguardManager.requestDismissKeyguard(currentActivity, new KeyguardManager.KeyguardDismissCallback() { // from class: com.kavkom.phone.CallkeepHelperModule.1
                @Override // android.app.KeyguardManager.KeyguardDismissCallback
                public void onDismissError() {
                    Log.d(CallkeepHelperModule.TAG, "onDismissError");
                }

                @Override // android.app.KeyguardManager.KeyguardDismissCallback
                public void onDismissSucceeded() {
                    Log.d(CallkeepHelperModule.TAG, "onDismissSucceeded");
                }

                @Override // android.app.KeyguardManager.KeyguardDismissCallback
                public void onDismissCancelled() {
                    Log.d(CallkeepHelperModule.TAG, "onDismissCancelled");
                }
            });
        } else {
            Log.d(TAG, "unlocked");
        }
    }

    @ReactMethod
    public void startActivity() {
        Log.d(TAG, "start activity");
        Context appContext = getAppContext();
        Intent intentCloneFilter = appContext.getPackageManager().getLaunchIntentForPackage(appContext.getApplicationContext().getPackageName()).cloneFilter();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            Log.d(TAG, "activity is running");
            intentCloneFilter.addFlags(131072);
            currentActivity.startActivity(intentCloneFilter);
            if (Build.VERSION.SDK_INT >= 27) {
                dismissKeyguard();
                return;
            }
            return;
        }
        Log.d(TAG, "activity is not running, starting activity");
        intentCloneFilter.addFlags(268566528);
        appContext.startActivity(intentCloneFilter);
    }

    @ReactMethod
    public void isSpeakerphoneOn(Callback callback) {
        callback.invoke(Boolean.valueOf(((AudioManager) reactContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).isSpeakerphoneOn()));
    }

    @ReactMethod
    public void switchAudioOutput(Boolean bool) {
        AudioManager audioManager = (AudioManager) reactContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (bool.booleanValue() != audioManager.isSpeakerphoneOn()) {
            audioManager.setSpeakerphoneOn(bool.booleanValue());
        }
        if (bool.booleanValue()) {
            audioManager.setMode(3);
        } else {
            audioManager.setMode(0);
        }
    }

    @ReactMethod
    public void incomingCallNotification(String str, String str2, String str3, String str4, String str5, String str6) {
        Log.d(TAG, "Notification for call " + str);
        Log.d(TAG, "Notification channelId " + str2);
        Log.d(TAG, "Notification channelName " + str3);
        Log.d(TAG, "Notification channelDescription " + str4);
        Log.d(TAG, "Notification contentText " + str5);
        Log.d(TAG, "Notification ringtone " + str6);
        Intent intentPutExtra = new Intent(reactContext, (Class<?>) IncomingCallService.class).putExtra(DebugImage.JsonKeys.UUID, str).putExtra("channelId", str2).putExtra("channelName", str3).putExtra("channelDescription", str4).putExtra("contentText", str5).putExtra("ringtone", str6).putExtra("notificationId", notificationIdCounter.incrementAndGet());
        if (Build.VERSION.SDK_INT >= 26) {
            reactContext.startForegroundService(intentPutExtra);
        }
    }

    @ReactMethod
    public void cancelIncomingCallNotification() {
        reactContext.stopService(new Intent(reactContext, (Class<?>) IncomingCallService.class));
    }

    @ReactMethod
    public void finishIncomingCallActivity(String str) {
        IncomingCallActivity.finishIfShowing(str);
    }

    @ReactMethod
    public void askForPermissions(Promise promise) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("android.permission.READ_PHONE_STATE");
        arrayList.add("android.permission.CALL_PHONE");
        arrayList.add("android.permission.READ_CALL_LOG");
        arrayList.add("android.permission.WRITE_CALL_LOG");
        arrayList.add("android.permission.PROCESS_OUTGOING_CALLS");
        arrayList.add("android.permission.ANSWER_PHONE_CALLS");
        arrayList.add("android.permission.RECORD_AUDIO");
        arrayList.add("android.permission.WAKE_LOCK");
        if (Build.VERSION.SDK_INT > 26) {
            arrayList.add("android.permission.READ_PHONE_NUMBERS");
        }
        if (Build.VERSION.SDK_INT > 28) {
            arrayList.add("android.permission.FOREGROUND_SERVICE");
        }
        for (String str : arrayList) {
            if (reactContext.checkSelfPermission(str) != 0) {
                writableArrayCreateArray.pushString(str);
            }
        }
        promise.resolve(writableArrayCreateArray);
    }

    private Context getAppContext() {
        return reactContext.getApplicationContext();
    }

    @ReactMethod
    public void removeNotification(int i) {
        Log.d(TAG, "[IncomingCallService] cancelNotification " + i);
        notificationManager.cancel(i);
        try {
            if (reactContext != null) {
                reactContext.stopService(new Intent(reactContext, (Class<?>) IncomingCallService.class));
            } else {
                Log.w(TAG, "[CallkeepHelperModule] Cannot stop service - React context is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "[CallkeepHelperModule] Error stopping service: " + e.getMessage(), e);
        }
    }

    @ReactMethod
    public void canUseFullScreenIntent(Promise promise) {
        if (Build.VERSION.SDK_INT < 34) {
            promise.resolve(true);
            return;
        }
        try {
            boolean zBooleanValue = ((Boolean) NotificationManager.class.getMethod("canUseFullScreenIntent", new Class[0]).invoke(notificationManager, new Object[0])).booleanValue();
            Log.d(TAG, "[CallkeepHelperModule] canUseFullScreenIntent: " + zBooleanValue);
            promise.resolve(Boolean.valueOf(zBooleanValue));
        } catch (Exception e) {
            Log.e(TAG, "[CallkeepHelperModule] Failed to check full screen intent permission: " + e.getMessage());
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void openFullScreenIntentSettings() {
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                Intent intent = new Intent("android.settings.MANAGE_APP_USE_FULL_SCREEN_INTENT");
                intent.setData(Uri.parse("package:" + reactContext.getPackageName()));
                intent.addFlags(268435456);
                reactContext.startActivity(intent);
                Log.d(TAG, "[CallkeepHelperModule] Opened full screen intent settings");
                return;
            } catch (Exception e) {
                Log.e(TAG, "[CallkeepHelperModule] Failed to open full screen intent settings: " + e.getMessage());
                openAppSettings();
                return;
            }
        }
        Log.d(TAG, "[CallkeepHelperModule] Full screen intent settings not available on this Android version");
    }

    @ReactMethod
    public void isIgnoringBatteryOptimizations(Promise promise) {
        try {
            boolean zIsIgnoringBatteryOptimizations = ((PowerManager) reactContext.getSystemService("power")).isIgnoringBatteryOptimizations(reactContext.getPackageName());
            Log.d(TAG, "[CallkeepHelperModule] isIgnoringBatteryOptimizations: " + zIsIgnoringBatteryOptimizations);
            promise.resolve(Boolean.valueOf(zIsIgnoringBatteryOptimizations));
        } catch (Exception e) {
            Log.e(TAG, "[CallkeepHelperModule] Failed to check battery optimization: " + e.getMessage());
            promise.resolve(false);
        }
    }

    @ReactMethod
    public void openBatteryOptimizationSettings() {
        try {
            Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + reactContext.getPackageName()));
            intent.addFlags(268435456);
            reactContext.startActivity(intent);
            Log.d(TAG, "[CallkeepHelperModule] Opened battery optimization settings");
        } catch (Exception e) {
            Log.e(TAG, "[CallkeepHelperModule] Failed to open battery optimization settings: " + e.getMessage());
            openAppSettings();
        }
    }

    private void openAppSettings() {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + reactContext.getPackageName()));
            intent.addFlags(268435456);
            reactContext.startActivity(intent);
            Log.d(TAG, "[CallkeepHelperModule] Opened app settings");
        } catch (Exception e) {
            Log.e(TAG, "[CallkeepHelperModule] Failed to open app settings: " + e.getMessage());
        }
    }

    @ReactMethod
    public void startActiveCallService(String str, String str2) {
        Log.d(TAG, "Starting active call service for call: " + str);
        Intent intentPutExtra = new Intent(reactContext, (Class<?>) ActiveCallService.class).putExtra("callUUID", str);
        if (str2 == null) {
            str2 = "Active call";
        }
        Intent intentPutExtra2 = intentPutExtra.putExtra("callerName", str2);
        if (Build.VERSION.SDK_INT >= 26) {
            reactContext.startForegroundService(intentPutExtra2);
        } else {
            reactContext.startService(intentPutExtra2);
        }
    }

    @ReactMethod
    public void stopActiveCallService() {
        Log.d(TAG, "Stopping active call service");
        reactContext.stopService(new Intent(reactContext, (Class<?>) ActiveCallService.class));
    }

    @ReactMethod
    public void finishMainActivity() {
        Log.d(TAG, "finishMainActivity called - attempting to finish MainActivity to allow keyguard to show");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            String simpleName = currentActivity.getClass().getSimpleName();
            Log.d(TAG, "Current activity found: " + simpleName);
            if (currentActivity.isFinishing()) {
                Log.d(TAG, "Activity is already finishing, skipping finish() call");
                return;
            } else if ("MainActivity".equals(simpleName)) {
                Log.d(TAG, "Activity is MainActivity, finishing it");
                currentActivity.runOnUiThread(new Runnable() { // from class: com.kavkom.phone.CallkeepHelperModule.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Activity currentActivity2 = CallkeepHelperModule.this.getCurrentActivity();
                            if (currentActivity2 == null || !"MainActivity".equals(currentActivity2.getClass().getSimpleName())) {
                                Log.w(CallkeepHelperModule.TAG, "Current activity is not MainActivity or became null on UI thread");
                            } else {
                                if (!currentActivity2.isFinishing()) {
                                    Log.d(CallkeepHelperModule.TAG, "Finishing MainActivity");
                                    currentActivity2.finish();
                                    Log.d(CallkeepHelperModule.TAG, "MainActivity finished successfully");
                                    return;
                                }
                                Log.d(CallkeepHelperModule.TAG, "MainActivity is already finishing, skipping finish() call");
                            }
                        } catch (Exception e) {
                            Log.e(CallkeepHelperModule.TAG, "Exception when finishing MainActivity: " + e.getMessage(), e);
                        }
                    }
                });
                return;
            } else {
                Log.w(TAG, "Current activity is not MainActivity: " + simpleName + ", finishing it anyway");
                currentActivity.runOnUiThread(new Runnable() { // from class: com.kavkom.phone.CallkeepHelperModule.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Activity currentActivity2 = CallkeepHelperModule.this.getCurrentActivity();
                            if (currentActivity2 != null && !currentActivity2.isFinishing()) {
                                Log.d(CallkeepHelperModule.TAG, "Finishing current activity: " + currentActivity2.getClass().getSimpleName());
                                currentActivity2.finish();
                            } else {
                                if (currentActivity2 == null || !currentActivity2.isFinishing()) {
                                    return;
                                }
                                Log.d(CallkeepHelperModule.TAG, "Current activity is already finishing, skipping finish() call");
                            }
                        } catch (Exception e) {
                            Log.e(CallkeepHelperModule.TAG, "Exception when finishing activity: " + e.getMessage(), e);
                        }
                    }
                });
                return;
            }
        }
        Log.w(TAG, "No current activity to finish - getCurrentActivity() returned null");
    }

    public static void setAcceptedFromLockedState(String str, boolean z) {
        Log.d(TAG, "setAcceptedFromLockedState called - UUID: " + str + ", acceptedFromLockedState: " + z);
        acceptedFromLockedStateMap.put(str, Boolean.valueOf(z));
    }

    @ReactMethod
    public void getAcceptedFromLockedState(String str, Callback callback) {
        Boolean bool = acceptedFromLockedStateMap.get(str);
        boolean z = bool != null && bool.booleanValue();
        Log.d(TAG, "getAcceptedFromLockedState called - UUID: " + str + ", result: " + z);
        callback.invoke(Boolean.valueOf(z));
    }

    @ReactMethod
    public void clearAcceptedFromLockedState(String str) {
        Log.d(TAG, "clearAcceptedFromLockedState called - UUID: " + str);
        acceptedFromLockedStateMap.remove(str);
    }
}
