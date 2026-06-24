package com.dieam.reactnativepushnotification.modules;

import android.app.Application;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.RemoteInput;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;

/* JADX INFO: loaded from: classes.dex */
public class RNPushNotificationActions extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = context.getPackageName() + ".ACTION_";
        Log.i(RNPushNotification.LOG_TAG, "RNPushNotificationBootEventReceiver loading scheduled notifications");
        if (intent.getAction() == null || !intent.getAction().startsWith(str)) {
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra("notification");
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        if (resultsFromIntent != null) {
            bundleExtra.putCharSequence("reply_text", resultsFromIntent.getCharSequence(RNPushNotification.KEY_TEXT_REPLY));
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int i = Integer.parseInt(bundleExtra.getString("id"));
        if (bundleExtra.getBoolean("autoCancel", true)) {
            if (bundleExtra.containsKey("tag")) {
                notificationManager.cancel(bundleExtra.getString("tag"), i);
            } else {
                notificationManager.cancel(i);
            }
        }
        if (bundleExtra.getBoolean("invokeApp", true)) {
            new RNPushNotificationHelper((Application) context.getApplicationContext()).invokeApp(bundleExtra);
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.dieam.reactnativepushnotification.modules.RNPushNotificationActions.1
                final /* synthetic */ Bundle val$bundle;
                final /* synthetic */ Context val$context;

                AnonymousClass1(Context context2, Bundle bundleExtra2) {
                    context = context2;
                    bundle = bundleExtra2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ReactInstanceManager reactInstanceManager = ((ReactApplication) context.getApplicationContext()).getReactNativeHost().getReactInstanceManager();
                    ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                    if (currentReactContext != null) {
                        new RNPushNotificationJsDelivery(currentReactContext).notifyNotificationAction(bundle);
                        return;
                    }
                    reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() { // from class: com.dieam.reactnativepushnotification.modules.RNPushNotificationActions.1.1
                        final /* synthetic */ ReactInstanceManager val$mReactInstanceManager;

                        C00071(ReactInstanceManager reactInstanceManager2) {
                            reactInstanceManager = reactInstanceManager2;
                        }

                        @Override // com.facebook.react.ReactInstanceEventListener
                        public void onReactContextInitialized(ReactContext reactContext) {
                            new RNPushNotificationJsDelivery(reactContext).notifyNotificationAction(bundle);
                            reactInstanceManager.removeReactInstanceEventListener(this);
                        }
                    });
                    if (reactInstanceManager2.hasStartedCreatingInitialContext()) {
                        return;
                    }
                    reactInstanceManager2.createReactContextInBackground();
                }

                /* JADX INFO: renamed from: com.dieam.reactnativepushnotification.modules.RNPushNotificationActions$1$1 */
                class C00071 implements ReactInstanceManager.ReactInstanceEventListener {
                    final /* synthetic */ ReactInstanceManager val$mReactInstanceManager;

                    C00071(ReactInstanceManager reactInstanceManager2) {
                        reactInstanceManager = reactInstanceManager2;
                    }

                    @Override // com.facebook.react.ReactInstanceEventListener
                    public void onReactContextInitialized(ReactContext reactContext) {
                        new RNPushNotificationJsDelivery(reactContext).notifyNotificationAction(bundle);
                        reactInstanceManager.removeReactInstanceEventListener(this);
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.dieam.reactnativepushnotification.modules.RNPushNotificationActions$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Bundle val$bundle;
        final /* synthetic */ Context val$context;

        AnonymousClass1(Context context2, Bundle bundleExtra2) {
            context = context2;
            bundle = bundleExtra2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ReactInstanceManager reactInstanceManager2 = ((ReactApplication) context.getApplicationContext()).getReactNativeHost().getReactInstanceManager();
            ReactContext currentReactContext = reactInstanceManager2.getCurrentReactContext();
            if (currentReactContext != null) {
                new RNPushNotificationJsDelivery(currentReactContext).notifyNotificationAction(bundle);
                return;
            }
            reactInstanceManager2.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() { // from class: com.dieam.reactnativepushnotification.modules.RNPushNotificationActions.1.1
                final /* synthetic */ ReactInstanceManager val$mReactInstanceManager;

                C00071(ReactInstanceManager reactInstanceManager22) {
                    reactInstanceManager = reactInstanceManager22;
                }

                @Override // com.facebook.react.ReactInstanceEventListener
                public void onReactContextInitialized(ReactContext reactContext) {
                    new RNPushNotificationJsDelivery(reactContext).notifyNotificationAction(bundle);
                    reactInstanceManager.removeReactInstanceEventListener(this);
                }
            });
            if (reactInstanceManager22.hasStartedCreatingInitialContext()) {
                return;
            }
            reactInstanceManager22.createReactContextInBackground();
        }

        /* JADX INFO: renamed from: com.dieam.reactnativepushnotification.modules.RNPushNotificationActions$1$1 */
        class C00071 implements ReactInstanceManager.ReactInstanceEventListener {
            final /* synthetic */ ReactInstanceManager val$mReactInstanceManager;

            C00071(ReactInstanceManager reactInstanceManager22) {
                reactInstanceManager = reactInstanceManager22;
            }

            @Override // com.facebook.react.ReactInstanceEventListener
            public void onReactContextInitialized(ReactContext reactContext) {
                new RNPushNotificationJsDelivery(reactContext).notifyNotificationAction(bundle);
                reactInstanceManager.removeReactInstanceEventListener(this);
            }
        }
    }
}
