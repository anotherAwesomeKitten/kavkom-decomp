package com.dieam.reactnativepushnotification.modules;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.firebase.messaging.FirebaseMessagingService;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class RNReceivedMessageHandler {
    private FirebaseMessagingService mFirebaseMessagingService;

    public RNReceivedMessageHandler(FirebaseMessagingService firebaseMessagingService) {
        this.mFirebaseMessagingService = firebaseMessagingService;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleReceivedMessage(com.google.firebase.messaging.RemoteMessage r8) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dieam.reactnativepushnotification.modules.RNReceivedMessageHandler.handleReceivedMessage(com.google.firebase.messaging.RemoteMessage):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRemotePushNotification(ReactApplicationContext reactApplicationContext, Bundle bundle) {
        if (bundle.getString("id") == null) {
            bundle.putString("id", String.valueOf(new SecureRandom().nextInt()));
        }
        Application application = (Application) reactApplicationContext.getApplicationContext();
        RNPushNotificationConfig rNPushNotificationConfig = new RNPushNotificationConfig(this.mFirebaseMessagingService.getApplication());
        RNPushNotificationHelper rNPushNotificationHelper = new RNPushNotificationHelper(application);
        boolean zIsApplicationInForeground = rNPushNotificationHelper.isApplicationInForeground();
        RNPushNotificationJsDelivery rNPushNotificationJsDelivery = new RNPushNotificationJsDelivery(reactApplicationContext);
        bundle.putBoolean("foreground", zIsApplicationInForeground);
        bundle.putBoolean("userInteraction", false);
        rNPushNotificationJsDelivery.notifyNotification(bundle);
        if (bundle.getString("contentAvailable", "false").equalsIgnoreCase("true")) {
            rNPushNotificationJsDelivery.notifyRemoteFetch(bundle);
        }
        if (rNPushNotificationConfig.getNotificationForeground() || !zIsApplicationInForeground) {
            Log.v(RNPushNotification.LOG_TAG, "sendNotification: " + bundle);
            rNPushNotificationHelper.sendToNotificationCentre(bundle);
        }
    }

    private String getLocalizedString(String str, String str2, String[] strArr) {
        int identifier;
        if (str != null) {
            return str;
        }
        Context applicationContext = this.mFirebaseMessagingService.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        if (str2 == null || (identifier = applicationContext.getResources().getIdentifier(str2, TypedValues.Custom.S_STRING, packageName)) == 0) {
            return null;
        }
        if (strArr != null) {
            return applicationContext.getResources().getString(identifier, strArr);
        }
        return applicationContext.getResources().getString(identifier);
    }
}
