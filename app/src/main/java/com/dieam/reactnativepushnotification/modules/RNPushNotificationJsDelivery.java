package com.dieam.reactnativepushnotification.modules;

import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RNPushNotificationJsDelivery {
    private ReactContext mReactContext;

    public RNPushNotificationJsDelivery(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    void sendEvent(String str, Object obj) {
        if (this.mReactContext.hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, obj);
        }
    }

    void notifyRemoteFetch(Bundle bundle) {
        String strConvertJSON = convertJSON(bundle);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("dataJSON", strConvertJSON);
        sendEvent("remoteFetch", writableMapCreateMap);
    }

    void notifyNotification(Bundle bundle) {
        String strConvertJSON = convertJSON(bundle);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("dataJSON", strConvertJSON);
        sendEvent("remoteNotificationReceived", writableMapCreateMap);
    }

    void notifyNotificationAction(Bundle bundle) {
        String strConvertJSON = convertJSON(bundle);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("dataJSON", strConvertJSON);
        sendEvent("notificationActionReceived", writableMapCreateMap);
    }

    String convertJSON(Bundle bundle) {
        try {
            return convertJSONObject(bundle).toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    private JSONObject convertJSONObject(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                jSONObject.put(str, convertJSONObject((Bundle) obj));
            } else {
                jSONObject.put(str, JSONObject.wrap(obj));
            }
        }
        return jSONObject;
    }
}
