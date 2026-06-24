package com.dieam.reactnativepushnotification.modules;

import android.content.ComponentCallbacks2;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/* JADX INFO: loaded from: classes.dex */
public class RNPushNotificationListenerService extends FirebaseMessagingService {
    private FirebaseMessagingService mFirebaseServiceDelegate;
    private RNReceivedMessageHandler mMessageReceivedHandler;

    public RNPushNotificationListenerService() {
        this.mMessageReceivedHandler = new RNReceivedMessageHandler(this);
    }

    public RNPushNotificationListenerService(FirebaseMessagingService firebaseMessagingService) {
        this.mFirebaseServiceDelegate = firebaseMessagingService;
        this.mMessageReceivedHandler = new RNReceivedMessageHandler(firebaseMessagingService);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(final String str) {
        final FirebaseMessagingService firebaseMessagingService = this.mFirebaseServiceDelegate;
        if (firebaseMessagingService == null) {
            firebaseMessagingService = this;
        }
        Log.d(RNPushNotification.LOG_TAG, "Refreshed token: " + str);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.dieam.reactnativepushnotification.modules.RNPushNotificationListenerService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ComponentCallbacks2 application = firebaseMessagingService.getApplication();
                    if (!(application instanceof ReactApplication)) {
                        Log.e(RNPushNotification.LOG_TAG, "Application is not a ReactApplication");
                        return;
                    }
                    try {
                        final ReactInstanceManager reactInstanceManager = ((ReactApplication) application).getReactNativeHost().getReactInstanceManager();
                        if (reactInstanceManager == null) {
                            Log.e(RNPushNotification.LOG_TAG, "Could not get ReactInstanceManager");
                            return;
                        }
                        ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                        if (currentReactContext != null) {
                            RNPushNotificationListenerService.this.handleNewToken((ReactApplicationContext) currentReactContext, str);
                            return;
                        }
                        reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() { // from class: com.dieam.reactnativepushnotification.modules.RNPushNotificationListenerService.1.1
                            @Override // com.facebook.react.ReactInstanceEventListener
                            public void onReactContextInitialized(ReactContext reactContext) {
                                RNPushNotificationListenerService.this.handleNewToken((ReactApplicationContext) reactContext, str);
                                reactInstanceManager.removeReactInstanceEventListener(this);
                            }
                        });
                        if (reactInstanceManager.hasStartedCreatingInitialContext()) {
                            return;
                        }
                        reactInstanceManager.createReactContextInBackground();
                    } catch (RuntimeException unused) {
                        Log.w(RNPushNotification.LOG_TAG, "New Architecture detected. Token refresh handling is limited. Token: " + str);
                    }
                } catch (Exception e) {
                    Log.e(RNPushNotification.LOG_TAG, "Error handling new token", e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewToken(ReactApplicationContext reactApplicationContext, String str) {
        RNPushNotificationJsDelivery rNPushNotificationJsDelivery = new RNPushNotificationJsDelivery(reactApplicationContext);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("deviceToken", str);
        rNPushNotificationJsDelivery.sendEvent("remoteNotificationsRegistered", writableMapCreateMap);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        this.mMessageReceivedHandler.handleReceivedMessage(remoteMessage);
    }
}
