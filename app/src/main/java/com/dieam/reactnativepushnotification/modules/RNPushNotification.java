package com.dieam.reactnativepushnotification.modules;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationManagerCompat;
import com.dieam.reactnativepushnotification.helpers.ApplicationBadgeHelper;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessaging;
import io.sentry.SentryEvent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class RNPushNotification extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static ArrayList<RNIntentHandler> IntentHandlers = new ArrayList<>();
    public static final String KEY_TEXT_REPLY = "key_text_reply";
    public static final String LOG_TAG = "RNPushNotification";
    private RNPushNotificationJsDelivery mJsDelivery;
    private RNPushNotificationHelper mRNPushNotificationHelper;
    private final SecureRandom mRandomNumberGenerator;

    public interface RNIntentHandler {
        Bundle getBundleFromIntent(Intent intent);

        void onNewIntent(Intent intent);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public RNPushNotification(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mRandomNumberGenerator = new SecureRandom();
        reactApplicationContext.addActivityEventListener(this);
        this.mRNPushNotificationHelper = new RNPushNotificationHelper((Application) reactApplicationContext.getApplicationContext());
        this.mJsDelivery = new RNPushNotificationJsDelivery(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ReactNativePushNotification";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return new HashMap();
    }

    private Bundle getBundleFromIntent(Intent intent) {
        Bundle bundle;
        if (intent.hasExtra("notification")) {
            bundle = intent.getBundleExtra("notification");
        } else if (intent.hasExtra(Constants.MessagePayloadKeys.MSGID)) {
            bundle = new Bundle();
            bundle.putBundle("data", intent.getExtras());
        } else {
            bundle = null;
        }
        if (bundle == null) {
            Iterator<RNIntentHandler> it = IntentHandlers.iterator();
            while (it.hasNext()) {
                bundle = it.next().getBundleFromIntent(intent);
            }
        }
        if (bundle != null && !bundle.getBoolean("foreground", false) && !bundle.containsKey("userInteraction")) {
            bundle.putBoolean("userInteraction", true);
        }
        return bundle;
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
        Iterator<RNIntentHandler> it = IntentHandlers.iterator();
        while (it.hasNext()) {
            it.next().onNewIntent(intent);
        }
        Bundle bundleFromIntent = getBundleFromIntent(intent);
        if (bundleFromIntent != null) {
            this.mJsDelivery.notifyNotification(bundleFromIntent);
        }
    }

    @ReactMethod
    public void invokeApp(ReadableMap readableMap) {
        this.mRNPushNotificationHelper.invokeApp(readableMap != null ? Arguments.toBundle(readableMap) : null);
    }

    @ReactMethod
    public void checkPermissions(Promise promise) {
        promise.resolve(Boolean.valueOf(NotificationManagerCompat.from(getReactApplicationContext()).areNotificationsEnabled()));
    }

    @ReactMethod
    public void requestPermissions() {
        final RNPushNotificationJsDelivery rNPushNotificationJsDelivery = this.mJsDelivery;
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.dieam.reactnativepushnotification.modules.RNPushNotification.1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.e(RNPushNotification.LOG_TAG, SentryEvent.JsonKeys.EXCEPTION, task.getException());
                    return;
                }
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("deviceToken", task.getResult());
                rNPushNotificationJsDelivery.sendEvent("remoteNotificationsRegistered", writableMapCreateMap);
            }
        });
    }

    @ReactMethod
    public void subscribeToTopic(String str) {
        FirebaseMessaging.getInstance().subscribeToTopic(str);
    }

    @ReactMethod
    public void unsubscribeFromTopic(String str) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(str);
    }

    @ReactMethod
    public void presentLocalNotification(ReadableMap readableMap) {
        Bundle bundle = Arguments.toBundle(readableMap);
        if (bundle.getString("id") == null) {
            bundle.putString("id", String.valueOf(this.mRandomNumberGenerator.nextInt()));
        }
        this.mRNPushNotificationHelper.sendToNotificationCentre(bundle);
    }

    @ReactMethod
    public void scheduleLocalNotification(ReadableMap readableMap) {
        Bundle bundle = Arguments.toBundle(readableMap);
        if (bundle.getString("id") == null) {
            bundle.putString("id", String.valueOf(this.mRandomNumberGenerator.nextInt()));
        }
        this.mRNPushNotificationHelper.sendNotificationScheduled(bundle);
    }

    @ReactMethod
    public void getInitialNotification(Promise promise) {
        Bundle bundleFromIntent;
        WritableMap writableMapCreateMap = Arguments.createMap();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null && (bundleFromIntent = getBundleFromIntent(currentActivity.getIntent())) != null) {
            bundleFromIntent.putBoolean("foreground", false);
            writableMapCreateMap.putString("dataJSON", this.mJsDelivery.convertJSON(bundleFromIntent));
        }
        promise.resolve(writableMapCreateMap);
    }

    @ReactMethod
    public void setApplicationIconBadgeNumber(int i) {
        ApplicationBadgeHelper.INSTANCE.setApplicationIconBadgeNumber(getReactApplicationContext(), i);
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        onActivityResult(i, i2, intent);
    }

    @ReactMethod
    public void cancelAllLocalNotifications() {
        this.mRNPushNotificationHelper.cancelAllScheduledNotifications();
        this.mRNPushNotificationHelper.clearNotifications();
    }

    @ReactMethod
    public void cancelLocalNotification(String str) {
        this.mRNPushNotificationHelper.cancelScheduledNotification(str);
    }

    @ReactMethod
    public void clearLocalNotification(String str, int i) {
        this.mRNPushNotificationHelper.clearNotification(str, i);
    }

    @ReactMethod
    public void removeAllDeliveredNotifications() {
        this.mRNPushNotificationHelper.clearNotifications();
    }

    @ReactMethod
    public void getDeliveredNotifications(Callback callback) {
        callback.invoke(this.mRNPushNotificationHelper.getDeliveredNotifications());
    }

    @ReactMethod
    public void getScheduledLocalNotifications(Callback callback) {
        callback.invoke(this.mRNPushNotificationHelper.getScheduledLocalNotifications());
    }

    @ReactMethod
    public void removeDeliveredNotifications(ReadableArray readableArray) {
        this.mRNPushNotificationHelper.clearDeliveredNotifications(readableArray);
    }

    @ReactMethod
    public void abandonPermissions() {
        FirebaseMessaging.getInstance().deleteToken();
        Log.i(LOG_TAG, "InstanceID deleted");
    }

    @ReactMethod
    public void getChannels(Callback callback) {
        WritableArray writableArrayFromList = Arguments.fromList(this.mRNPushNotificationHelper.listChannels());
        if (callback != null) {
            callback.invoke(writableArrayFromList);
        }
    }

    @ReactMethod
    public void channelExists(String str, Callback callback) {
        boolean zChannelExists = this.mRNPushNotificationHelper.channelExists(str);
        if (callback != null) {
            callback.invoke(Boolean.valueOf(zChannelExists));
        }
    }

    @ReactMethod
    public void createChannel(ReadableMap readableMap, Callback callback) {
        boolean zCreateChannel = this.mRNPushNotificationHelper.createChannel(readableMap);
        if (callback != null) {
            callback.invoke(Boolean.valueOf(zCreateChannel));
        }
    }

    @ReactMethod
    public void channelBlocked(String str, Callback callback) {
        boolean zChannelBlocked = this.mRNPushNotificationHelper.channelBlocked(str);
        if (callback != null) {
            callback.invoke(Boolean.valueOf(zChannelBlocked));
        }
    }

    @ReactMethod
    public void deleteChannel(String str) {
        this.mRNPushNotificationHelper.deleteChannel(str);
    }
}
