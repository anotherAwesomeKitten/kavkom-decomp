package io.wazo.callkeep;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Icon;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.MediaStreamTrack;

/* JADX INFO: loaded from: classes3.dex */
public class RNCallKeepModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
    private static final String REACT_NATIVE_MODULE_NAME = "RNCallKeep";
    public static final int REQUEST_READ_PHONE_STATE = 1337;
    public static final int REQUEST_REGISTER_CALL_PROVIDER = 394859;
    private static final String TAG = "RNCallKeep";
    private static WritableMap _settings;
    public static PhoneAccountHandle handle;
    private static Promise hasPhoneAccountPromise;
    public static RNCallKeepModule instance;
    private static String[] permissions;
    private static TelecomManager telecomManager;
    private static TelephonyManager telephonyManager;
    private CallStateListener callStateListener;
    private WritableNativeArray delayedEvents;
    private boolean hasActiveCall;
    private boolean hasListeners;
    private boolean isReceiverRegistered;
    private LegacyCallStateListener legacyCallStateListener;
    private ReactApplicationContext reactContext;
    private VoiceBroadcastReceiver voiceBroadcastReceiver;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    static {
        String[] strArr = new String[3];
        strArr[0] = Build.VERSION.SDK_INT < 30 ? "android.permission.READ_PHONE_STATE" : "android.permission.READ_PHONE_NUMBERS";
        strArr[1] = "android.permission.CALL_PHONE";
        strArr[2] = "android.permission.RECORD_AUDIO";
        permissions = strArr;
    }

    public static RNCallKeepModule getInstance(ReactApplicationContext reactApplicationContext, boolean z) {
        if (instance == null) {
            Log.d("RNCallKeep", "[RNCallKeepModule] getInstance : ".concat(reactApplicationContext == null ? "null" : "ok"));
            RNCallKeepModule rNCallKeepModule = new RNCallKeepModule(reactApplicationContext);
            instance = rNCallKeepModule;
            rNCallKeepModule.registerReceiver();
            fetchStoredSettings(reactApplicationContext);
        }
        if (z) {
            instance.setContext(reactApplicationContext);
        }
        return instance;
    }

    public static WritableMap getSettings(Context context) {
        if (_settings == null) {
            fetchStoredSettings(context);
        }
        return _settings;
    }

    private RNCallKeepModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.isReceiverRegistered = false;
        this.hasListeners = false;
        this.hasActiveCall = false;
        reactApplicationContext.addLifecycleEventListener(this);
        Log.d("RNCallKeep", "[RNCallKeepModule] constructor");
        this.reactContext = reactApplicationContext;
        this.delayedEvents = new WritableNativeArray();
    }

    private boolean isSelfManaged() {
        try {
            if (Build.VERSION.SDK_INT >= 26 && _settings.hasKey("selfManaged")) {
                if (_settings.getBoolean("selfManaged")) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCallKeep";
    }

    public void setContext(ReactApplicationContext reactApplicationContext) {
        Log.d("RNCallKeep", "[RNCallKeepModule] updating react context");
        this.reactContext = reactApplicationContext;
    }

    public ReactApplicationContext getContext() {
        return this.reactContext;
    }

    public void reportNewIncomingCall(String str, String str2, String str3, boolean z, String str4) {
        Log.d("RNCallKeep", "[RNCallKeepModule] reportNewIncomingCall, uuid: " + str + ", number: " + str2 + ", callerName: " + str3);
        displayIncomingCall(str, str2, str3, z);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("handle", str2);
        writableMapCreateMap.putString("callUUID", str);
        writableMapCreateMap.putString("name", str3);
        writableMapCreateMap.putString("hasVideo", String.valueOf(z));
        if (str4 != null) {
            writableMapCreateMap.putString("payload", str4);
        }
        sendEventToJS("RNCallKeepDidDisplayIncomingCall", writableMapCreateMap);
    }

    public void startObserving() {
        int size = this.delayedEvents.size();
        Log.d("RNCallKeep", "[RNCallKeepModule] startObserving, event count: " + size);
        if (size > 0) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("RNCallKeepDidLoadWithEvents", this.delayedEvents);
            this.delayedEvents = new WritableNativeArray();
        }
    }

    public void initializeTelecomManager() {
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][initializeTelecomManager] no react context found.");
        } else {
            handle = new PhoneAccountHandle(new ComponentName(appContext, (Class<?>) VoiceConnectionService.class), getApplicationName(appContext));
            telecomManager = (TelecomManager) appContext.getSystemService("telecom");
        }
    }

    private class LegacyCallStateListener extends PhoneStateListener {
        /* synthetic */ LegacyCallStateListener(RNCallKeepModule rNCallKeepModule, RNCallKeepModuleIA rNCallKeepModuleIA) {
            this();
        }

        private LegacyCallStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            if (i != 2) {
                return;
            }
            boolean zCheckIsInManagedCall = RNCallKeepModule.this.checkIsInManagedCall();
            if (RNCallKeepModule.this.hasActiveCall && zCheckIsInManagedCall) {
                RNCallKeepModule.this.sendEventToJS("RNCallKeepHasActiveCall", Arguments.createMap());
            } else if (VoiceConnectionService.currentConnections.size() > 0) {
                RNCallKeepModule.this.hasActiveCall = true;
            }
        }
    }

    private class CallStateListener extends TelephonyCallback implements TelephonyCallback.CallStateListener {
        /* synthetic */ CallStateListener(RNCallKeepModule rNCallKeepModule, RNCallKeepModuleIA rNCallKeepModuleIA) {
            this();
        }

        private CallStateListener() {
        }

        @Override // android.telephony.TelephonyCallback.CallStateListener
        public void onCallStateChanged(int i) {
            if (i != 2) {
                return;
            }
            boolean zCheckIsInManagedCall = RNCallKeepModule.this.checkIsInManagedCall();
            if (RNCallKeepModule.this.hasActiveCall && zCheckIsInManagedCall) {
                RNCallKeepModule.this.sendEventToJS("RNCallKeepHasActiveCall", Arguments.createMap());
            } else if (VoiceConnectionService.currentConnections.size() > 0) {
                RNCallKeepModule.this.hasActiveCall = true;
            }
        }
    }

    public void stopListenToNativeCallsState() {
        LegacyCallStateListener legacyCallStateListener;
        CallStateListener callStateListener;
        Log.d("RNCallKeep", "[RNCallKeepModule] stopListenToNativeCallsState");
        if (Build.VERSION.SDK_INT >= 31 && (callStateListener = this.callStateListener) != null) {
            telephonyManager.unregisterTelephonyCallback(callStateListener);
        } else {
            if (Build.VERSION.SDK_INT >= 31 || (legacyCallStateListener = this.legacyCallStateListener) == null) {
                return;
            }
            telephonyManager.listen(legacyCallStateListener, 0);
            Looper.myLooper().quit();
        }
    }

    public void listenToNativeCallsState() {
        Log.d("RNCallKeep", "[RNCallKeepModule] listenToNativeCallsState");
        Context appContext = getAppContext();
        if (ContextCompat.checkSelfPermission(appContext, "android.permission.READ_PHONE_STATE") == 0) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.callStateListener = new CallStateListener();
                telephonyManager.registerTelephonyCallback(appContext.getMainExecutor(), this.callStateListener);
                return;
            }
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            LegacyCallStateListener legacyCallStateListener = new LegacyCallStateListener();
            this.legacyCallStateListener = legacyCallStateListener;
            telephonyManager.listen(legacyCallStateListener, 32);
            Looper.loop();
        }
    }

    public boolean checkIsInManagedCall() {
        if (ContextCompat.checkSelfPermission(getAppContext(), "android.permission.READ_PHONE_STATE") == 0) {
            return telecomManager.isInManagedCall();
        }
        return false;
    }

    @ReactMethod
    public void checkIsInManagedCall(Promise promise) {
        promise.resolve(Boolean.valueOf(checkIsInManagedCall()));
    }

    @ReactMethod
    public void setSettings(ReadableMap readableMap) {
        Log.d("RNCallKeep", "[RNCallKeepModule] setSettings : " + readableMap);
        if (readableMap == null) {
            return;
        }
        _settings = storeSettings(readableMap);
    }

    @ReactMethod
    public void setup(ReadableMap readableMap) {
        Log.d("RNCallKeep", "[RNCallKeepModule] setup : " + readableMap);
        VoiceConnectionService.setAvailable(false);
        VoiceConnectionService.setInitialized(true);
        setSettings(readableMap);
        if (Build.VERSION.SDK_INT >= 26) {
            if (isSelfManaged()) {
                Log.d("RNCallKeep", "[RNCallKeepModule] API Version supports self managed, and is enabled in setup");
            } else {
                Log.d("RNCallKeep", "[RNCallKeepModule] API Version supports self managed, but it is not enabled in setup");
            }
        }
        if (isSelfManaged()) {
            Log.d("RNCallKeep", "[RNCallKeepModule] setup, adding RECORD_AUDIO in permissions in self managed");
            permissions = new String[]{"android.permission.RECORD_AUDIO"};
        }
        if (isConnectionServiceAvailable().booleanValue()) {
            registerPhoneAccount(readableMap);
            registerEvents();
            startObserving();
            VoiceConnectionService.setAvailable(true);
        }
    }

    @ReactMethod
    public void registerPhoneAccount(ReadableMap readableMap) {
        setSettings(readableMap);
        if (!isConnectionServiceAvailable().booleanValue()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] registerPhoneAccount ignored due to no ConnectionService");
            return;
        }
        Log.d("RNCallKeep", "[RNCallKeepModule] registerPhoneAccount");
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][registerPhoneAccount] no react context found.");
        } else {
            registerPhoneAccount(appContext);
        }
    }

    @ReactMethod
    public void registerEvents() {
        if (!isConnectionServiceAvailable().booleanValue()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] registerEvents ignored due to no ConnectionService");
            return;
        }
        Log.d("RNCallKeep", "[RNCallKeepModule] registerEvents");
        this.hasListeners = true;
        startObserving();
        VoiceConnectionService.setPhoneAccountHandle(handle);
    }

    @ReactMethod
    public void unregisterEvents() {
        Log.d("RNCallKeep", "[RNCallKeepModule] unregisterEvents");
        this.hasListeners = false;
    }

    public void displayIncomingCall(String str, String str2, String str3) {
        displayIncomingCall(str, str2, str3, false, null);
    }

    @ReactMethod
    public void displayIncomingCall(String str, String str2, String str3, boolean z) {
        displayIncomingCall(str, str2, str3, z, null);
    }

    public void displayIncomingCall(String str, String str2, String str3, boolean z, Bundle bundle) {
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] displayIncomingCall ignored due to no ConnectionService or no phone account");
            return;
        }
        Log.d("RNCallKeep", "[RNCallKeepModule] displayIncomingCall, uuid: " + str + ", number: " + str2 + ", callerName: " + str3 + ", hasVideo: " + z + ", payload: " + bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("android.telecom.extra.INCOMING_CALL_ADDRESS", Uri.fromParts("tel", str2, null));
        bundle2.putString(Constants.EXTRA_CALLER_NAME, str3);
        bundle2.putString(Constants.EXTRA_CALL_UUID, str);
        bundle2.putString(Constants.EXTRA_HAS_VIDEO, String.valueOf(z));
        if (bundle != null) {
            bundle2.putBundle(Constants.EXTRA_PAYLOAD, bundle);
        }
        listenToNativeCallsState();
        telecomManager.addNewIncomingCall(handle, bundle2);
    }

    @ReactMethod
    public void answerIncomingCall(String str) {
        Log.d("RNCallKeep", "[RNCallKeepModule] answerIncomingCall, uuid: " + str);
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] answerIncomingCall ignored due to no ConnectionService or no phone account");
            return;
        }
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] answerIncomingCall ignored because no connection found, uuid: " + str);
        } else {
            connection.onAnswer();
        }
    }

    public void startCall(String str, String str2, String str3) {
        startCall(str, str2, str3, false, null);
    }

    @ReactMethod
    public void startCall(String str, String str2, String str3, boolean z) {
        startCall(str, str2, str3, z, null);
    }

    public void startCall(String str, String str2, String str3, boolean z, Bundle bundle) {
        Log.d("RNCallKeep", "[RNCallKeepModule] startCall called, uuid: " + str + ", number: " + str2 + ", callerName: " + str3 + ", payload: " + bundle);
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount() || !hasPermissions().booleanValue() || str2 == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] startCall ignored: " + isConnectionServiceAvailable() + ", " + hasPhoneAccount() + ", " + hasPermissions() + ", " + str2);
            return;
        }
        Bundle bundle2 = new Bundle();
        Uri uriFromParts = Uri.fromParts("tel", str2, null);
        Bundle bundle3 = new Bundle();
        bundle3.putString(Constants.EXTRA_CALLER_NAME, str3);
        bundle3.putString(Constants.EXTRA_CALL_UUID, str);
        bundle3.putString(Constants.EXTRA_CALL_NUMBER, str2);
        bundle3.putString(Constants.EXTRA_HAS_VIDEO, String.valueOf(z));
        if (bundle != null) {
            bundle3.putBundle(Constants.EXTRA_PAYLOAD, bundle);
        }
        bundle2.putParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE", handle);
        bundle2.putParcelable("android.telecom.extra.OUTGOING_CALL_EXTRAS", bundle3);
        Log.d("RNCallKeep", "[RNCallKeepModule] startCall, uuid: " + str);
        listenToNativeCallsState();
        telecomManager.placeCall(uriFromParts, bundle2);
    }

    @ReactMethod
    public void endCall(String str) {
        Log.d("RNCallKeep", "[RNCallKeepModule] endCall called, uuid: " + str);
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] endCall ignored due to no ConnectionService or no phone account");
            return;
        }
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] endCall ignored because no connection found, uuid: " + str);
            return;
        }
        ((AudioManager) getAppContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).setMode(0);
        connection.onDisconnect();
        stopListenToNativeCallsState();
        this.hasActiveCall = false;
        Log.d("RNCallKeep", "[RNCallKeepModule] endCall executed, uuid: " + str);
    }

    @ReactMethod
    public void endAllCalls() {
        Log.d("RNCallKeep", "[RNCallKeepModule] endAllCalls called");
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] endAllCalls ignored due to no ConnectionService or no phone account");
            return;
        }
        Iterator it = new ArrayList(VoiceConnectionService.currentConnections.entrySet()).iterator();
        while (it.hasNext()) {
            ((Connection) ((Map.Entry) it.next()).getValue()).onDisconnect();
        }
        stopListenToNativeCallsState();
        this.hasActiveCall = false;
        Log.d("RNCallKeep", "[RNCallKeepModule] endAllCalls executed");
    }

    @ReactMethod
    public void checkPhoneAccountPermission(ReadableArray readableArray, Promise promise) {
        Activity currentReactActivity = getCurrentReactActivity();
        if (!isConnectionServiceAvailable().booleanValue()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] checkPhoneAccountPermission error ConnectionService not available for this version of Android.");
            promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "ConnectionService not available for this version of Android.");
            return;
        }
        if (currentReactActivity == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] checkPhoneAccountPermission error Activity doesn't exist");
            promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
            return;
        }
        int size = readableArray.size();
        String[] strArr = new String[size];
        for (int i = 0; i < readableArray.size(); i++) {
            strArr[i] = readableArray.getString(i);
        }
        String[] strArr2 = permissions;
        String[] strArr3 = (String[]) Arrays.copyOf(strArr2, strArr2.length + size);
        System.arraycopy(strArr, 0, strArr3, permissions.length, size);
        hasPhoneAccountPromise = promise;
        if (!hasPermissions().booleanValue()) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (String str : strArr3) {
                writableArrayCreateArray.pushString(str);
            }
            ((PermissionsModule) this.reactContext.getNativeModule(PermissionsModule.class)).requestMultiplePermissions(writableArrayCreateArray, new Promise() { // from class: io.wazo.callkeep.RNCallKeepModule.1
                final /* synthetic */ String[] val$allPermissions;

                AnonymousClass1(String[] strArr32) {
                    strArr = strArr32;
                }

                @Override // com.facebook.react.bridge.Promise
                public void resolve(Object obj) {
                    WritableMap writableMap = (WritableMap) obj;
                    int[] iArr = new int[strArr.length];
                    int i2 = 0;
                    while (true) {
                        String[] strArr4 = strArr;
                        if (i2 < strArr4.length) {
                            iArr[i2] = writableMap.getString(strArr4[i2]).equals("granted") ? 0 : -1;
                            i2++;
                        } else {
                            RNCallKeepModule.onRequestPermissionsResult(RNCallKeepModule.REQUEST_READ_PHONE_STATE, strArr4, iArr);
                            return;
                        }
                    }
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2, String str3) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2, Throwable th) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2, String str3, Throwable th) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(Throwable th) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(Throwable th, WritableMap writableMap) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2, WritableMap writableMap) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2, Throwable th, WritableMap writableMap) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2, String str3, WritableMap writableMap) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2, String str3, Throwable th, WritableMap writableMap) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }

                @Override // com.facebook.react.bridge.Promise
                public void reject(String str2) {
                    RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
                }
            });
            return;
        }
        promise.resolve(Boolean.valueOf(!hasPhoneAccount()));
    }

    /* JADX INFO: renamed from: io.wazo.callkeep.RNCallKeepModule$1 */
    class AnonymousClass1 implements Promise {
        final /* synthetic */ String[] val$allPermissions;

        AnonymousClass1(String[] strArr32) {
            strArr = strArr32;
        }

        @Override // com.facebook.react.bridge.Promise
        public void resolve(Object obj) {
            WritableMap writableMap = (WritableMap) obj;
            int[] iArr = new int[strArr.length];
            int i2 = 0;
            while (true) {
                String[] strArr4 = strArr;
                if (i2 < strArr4.length) {
                    iArr[i2] = writableMap.getString(strArr4[i2]).equals("granted") ? 0 : -1;
                    i2++;
                } else {
                    RNCallKeepModule.onRequestPermissionsResult(RNCallKeepModule.REQUEST_READ_PHONE_STATE, strArr4, iArr);
                    return;
                }
            }
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2, String str3) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2, Throwable th) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2, String str3, Throwable th) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(Throwable th) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(Throwable th, WritableMap writableMap) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2, WritableMap writableMap) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2, Throwable th, WritableMap writableMap) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2, String str3, WritableMap writableMap) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2, String str3, Throwable th, WritableMap writableMap) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }

        @Override // com.facebook.react.bridge.Promise
        public void reject(String str2) {
            RNCallKeepModule.hasPhoneAccountPromise.resolve(false);
        }
    }

    @ReactMethod
    public void checkDefaultPhoneAccount(Promise promise) {
        boolean z = true;
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            promise.resolve(true);
            return;
        }
        if (!Build.MANUFACTURER.equalsIgnoreCase("Samsung")) {
            promise.resolve(true);
            return;
        }
        boolean z2 = telephonyManager.getSimState() != 1;
        boolean z3 = telecomManager.getDefaultOutgoingPhoneAccount("tel") != null;
        if (z2 && !z3) {
            z = false;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @ReactMethod
    public void getInitialEvents(Promise promise) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (int i = 0; i < this.delayedEvents.size(); i++) {
            writableNativeArray.pushMap(this.delayedEvents.getMap(i));
        }
        promise.resolve(writableNativeArray);
    }

    @ReactMethod
    public void clearInitialEvents() {
        this.delayedEvents = new WritableNativeArray();
    }

    @ReactMethod
    public void setOnHold(String str, boolean z) {
        Log.d("RNCallKeep", "[RNCallKeepModule] setOnHold, uuid: " + str + ", shouldHold: " + (z ? "true" : "false"));
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] setOnHold ignored because no connection found, uuid: " + str);
        } else if (z) {
            connection.onHold();
        } else {
            connection.onUnhold();
        }
    }

    @ReactMethod
    public void reportEndCallWithUUID(String str, int i) {
        Log.d("RNCallKeep", "[RNCallKeepModule] reportEndCallWithUUID, uuid: " + str + ", reason: " + i);
        if (isConnectionServiceAvailable().booleanValue() && hasPhoneAccount()) {
            VoiceConnection voiceConnection = (VoiceConnection) VoiceConnectionService.getConnection(str);
            if (voiceConnection == null) {
                Log.w("RNCallKeep", "[RNCallKeepModule] reportEndCallWithUUID ignored because no connection found, uuid: " + str);
            } else {
                voiceConnection.reportDisconnect(i);
                stopListenToNativeCallsState();
            }
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        Log.d("RNCallKeep", "[RNCallKeepModule] onHostDestroy called");
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] onHostDestroy ignored due to no ConnectionService or no phone account");
            return;
        }
        Iterator it = new ArrayList(VoiceConnectionService.currentConnections.entrySet()).iterator();
        while (it.hasNext()) {
            ((Connection) ((Map.Entry) it.next()).getValue()).onDisconnect();
        }
        stopListenToNativeCallsState();
        Log.d("RNCallKeep", "[RNCallKeepModule] onHostDestroy executed");
        Process.killProcess(Process.myPid());
    }

    @ReactMethod
    public void rejectCall(String str) {
        Log.d("RNCallKeep", "[RNCallKeepModule] rejectCall, uuid: " + str);
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] rejectCall ignored due to no ConnectionService or no phone account");
            return;
        }
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] rejectCall ignored because no connection found, uuid: " + str);
        } else {
            stopListenToNativeCallsState();
            connection.onReject();
        }
    }

    @ReactMethod
    public void setConnectionState(String str, int i) {
        Log.d("RNCallKeep", "[RNCallKeepModule] setConnectionState, uuid: " + str + ", state :" + i);
        if (!isConnectionServiceAvailable().booleanValue() || !hasPhoneAccount()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] String ignored due to no ConnectionService or no phone account");
        } else {
            VoiceConnectionService.setState(str, i);
        }
    }

    @ReactMethod
    public void setMutedCall(String str, boolean z) {
        CallAudioState callAudioState;
        Log.d("RNCallKeep", "[RNCallKeepModule] setMutedCall, uuid: " + str + ", shouldMute: " + (z ? "true" : "false"));
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] setMutedCall ignored because no connection found, uuid: " + str);
            return;
        }
        if (z) {
            callAudioState = new CallAudioState(true, connection.getCallAudioState().getRoute(), connection.getCallAudioState().getSupportedRouteMask());
        } else {
            callAudioState = new CallAudioState(false, connection.getCallAudioState().getRoute(), connection.getCallAudioState().getSupportedRouteMask());
        }
        connection.onCallAudioStateChanged(callAudioState);
    }

    @ReactMethod
    public void toggleAudioRouteSpeaker(String str, boolean z) {
        Log.d("RNCallKeep", "[RNCallKeepModule] toggleAudioRouteSpeaker, uuid: " + str + ", routeSpeaker: " + (z ? "true" : "false"));
        VoiceConnection voiceConnection = (VoiceConnection) VoiceConnectionService.getConnection(str);
        if (voiceConnection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] toggleAudioRouteSpeaker ignored because no connection found, uuid: " + str);
        } else if (z) {
            voiceConnection.setAudioRoute(8);
        } else {
            voiceConnection.setAudioRoute(1);
        }
    }

    @ReactMethod
    public void setAudioRoute(String str, String str2, Promise promise) {
        try {
            VoiceConnection voiceConnection = (VoiceConnection) VoiceConnectionService.getConnection(str);
            if (voiceConnection == null) {
                return;
            }
            if (str2.equals("Bluetooth")) {
                Log.d("RNCallKeep", "[RNCallKeepModule] setting audio route: Bluetooth");
                voiceConnection.setAudioRoute(2);
                promise.resolve(true);
            } else if (str2.equals("Headset")) {
                Log.d("RNCallKeep", "[RNCallKeepModule] setting audio route: Headset");
                voiceConnection.setAudioRoute(4);
                promise.resolve(true);
            } else if (str2.equals("Speaker")) {
                Log.d("RNCallKeep", "[RNCallKeepModule] setting audio route: Speaker");
                voiceConnection.setAudioRoute(8);
                promise.resolve(true);
            } else {
                Log.d("RNCallKeep", "[RNCallKeepModule] setting audio route: Wired/Earpiece");
                voiceConnection.setAudioRoute(5);
                promise.resolve(true);
            }
        } catch (Exception e) {
            promise.reject("SetAudioRoute", e.getMessage());
        }
    }

    @ReactMethod
    public void getAudioRoutes(Promise promise) {
        try {
            Context appContext = getAppContext();
            if (appContext == null) {
                Log.w("RNCallKeep", "[RNCallKeepModule][getAudioRoutes] no react context found.");
                promise.reject("No react context found to list audio routes");
                return;
            }
            AudioManager audioManager = (AudioManager) appContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            WritableArray writableArrayCreateArray = Arguments.createArray();
            ArrayList arrayList = new ArrayList();
            AudioDeviceInfo[] devices = audioManager.getDevices(3);
            String selectedAudioRoute = getSelectedAudioRoute(audioManager);
            for (AudioDeviceInfo audioDeviceInfo : devices) {
                String audioRouteType = getAudioRouteType(audioDeviceInfo.getType());
                if (audioRouteType != null && !arrayList.contains(audioRouteType)) {
                    WritableMap writableMapCreateMap = Arguments.createMap();
                    writableMapCreateMap.putString("name", audioRouteType);
                    writableMapCreateMap.putString("type", audioRouteType);
                    if (audioRouteType.equals(selectedAudioRoute)) {
                        writableMapCreateMap.putBoolean("selected", true);
                    }
                    arrayList.add(audioRouteType);
                    writableArrayCreateArray.pushMap(writableMapCreateMap);
                }
            }
            promise.resolve(writableArrayCreateArray);
        } catch (Exception e) {
            promise.reject("GetAudioRoutes Error", e.getMessage());
        }
    }

    private String getAudioRouteType(int i) {
        if (i == 2) {
            return "Speaker";
        }
        if (i == 3 || i == 4) {
            return "Headset";
        }
        if (i == 7 || i == 8) {
            return "Bluetooth";
        }
        if (i != 15) {
            return null;
        }
        return "Phone";
    }

    private String getSelectedAudioRoute(AudioManager audioManager) {
        if (audioManager.isBluetoothScoOn()) {
            return "Bluetooth";
        }
        if (audioManager.isSpeakerphoneOn()) {
            return "Speaker";
        }
        if (audioManager.isWiredHeadsetOn()) {
            return "Headset";
        }
        return "Phone";
    }

    @ReactMethod
    public void sendDTMF(String str, String str2) {
        Log.d("RNCallKeep", "[RNCallKeepModule] sendDTMF, uuid: " + str + ", key: " + str2);
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] sendDTMF ignored because no connection found, uuid: " + str);
        } else {
            connection.onPlayDtmfTone(str2.charAt(0));
        }
    }

    @ReactMethod
    public void updateDisplay(String str, String str2, String str3) {
        Log.d("RNCallKeep", "[RNCallKeepModule] updateDisplay, uuid: " + str + ", displayName: " + str2 + ", uri: " + str3);
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] updateDisplay ignored because no connection found, uuid: " + str);
        } else {
            connection.setAddress(Uri.parse(str3), 1);
            connection.setCallerDisplayName(str2, 1);
        }
    }

    @ReactMethod
    public void hasPhoneAccount(Promise promise) {
        if (telecomManager == null) {
            initializeTelecomManager();
        }
        promise.resolve(Boolean.valueOf(hasPhoneAccount()));
    }

    @ReactMethod
    public void hasOutgoingCall(Promise promise) {
        promise.resolve(VoiceConnectionService.hasOutgoingCall);
    }

    @ReactMethod
    public void hasPermissions(Promise promise) {
        promise.resolve(hasPermissions());
    }

    @ReactMethod
    public void setAvailable(Boolean bool) {
        VoiceConnectionService.setAvailable(bool);
    }

    @ReactMethod
    public void setForegroundServiceSettings(ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        WritableMap settings = getSettings(null);
        if (settings != null) {
            settings.putMap("foregroundService", MapUtils.readableToWritableMap(readableMap));
        }
        setSettings(settings);
    }

    @ReactMethod
    public void canMakeMultipleCalls(Boolean bool) {
        VoiceConnectionService.setCanMakeMultipleCalls(bool);
    }

    @ReactMethod
    public void setReachable() {
        VoiceConnectionService.setReachable();
    }

    @ReactMethod
    public void setCurrentCallActive(String str) {
        Log.d("RNCallKeep", "[RNCallKeepModule] setCurrentCallActive, uuid: " + str);
        Connection connection = VoiceConnectionService.getConnection(str);
        if (connection == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule] setCurrentCallActive ignored because no connection found, uuid: " + str);
        } else {
            connection.setConnectionCapabilities(connection.getConnectionCapabilities() | 1);
            connection.setActive();
        }
    }

    @ReactMethod
    public void openPhoneAccounts() {
        Log.d("RNCallKeep", "[RNCallKeepModule] openPhoneAccounts");
        if (!isConnectionServiceAvailable().booleanValue()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] openPhoneAccounts ignored due to no ConnectionService");
            return;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("Samsung") || Build.MANUFACTURER.equalsIgnoreCase("OnePlus")) {
            Intent intent = new Intent();
            intent.setFlags(402653184);
            intent.setComponent(new ComponentName("com.android.server.telecom", "com.android.server.telecom.settings.EnableAccountPreferenceActivity"));
            Context appContext = getAppContext();
            if (appContext == null) {
                Log.w("RNCallKeep", "[RNCallKeepModule][openPhoneAccounts] no react context found.");
                return;
            } else {
                appContext.startActivity(intent);
                return;
            }
        }
        openPhoneAccountSettings();
    }

    @ReactMethod
    public void openPhoneAccountSettings() {
        Log.d("RNCallKeep", "[RNCallKeepModule] openPhoneAccountSettings");
        if (!isConnectionServiceAvailable().booleanValue()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] openPhoneAccountSettings ignored due to no ConnectionService");
            return;
        }
        Intent intent = new Intent("android.telecom.action.CHANGE_PHONE_ACCOUNTS");
        intent.setFlags(402653184);
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][openPhoneAccountSettings] no react context found.");
        } else {
            appContext.startActivity(intent);
        }
    }

    public static Boolean isConnectionServiceAvailable() {
        return true;
    }

    @ReactMethod
    public void isConnectionServiceAvailable(Promise promise) {
        promise.resolve(isConnectionServiceAvailable());
    }

    @ReactMethod
    public void checkPhoneAccountEnabled(Promise promise) {
        promise.resolve(Boolean.valueOf(hasPhoneAccount()));
    }

    @ReactMethod
    public void backToForeground() {
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][backToForeground] no react context found.");
            return;
        }
        Intent intentCloneFilter = appContext.getPackageManager().getLaunchIntentForPackage(appContext.getApplicationContext().getPackageName()).cloneFilter();
        Activity currentReactActivity = getCurrentReactActivity();
        boolean z = currentReactActivity != null;
        Log.d("RNCallKeep", "[RNCallKeepModule] backToForeground, app isOpened ?".concat(z ? "true" : "false"));
        if (z) {
            intentCloneFilter.addFlags(131072);
            currentReactActivity.startActivity(intentCloneFilter);
        } else {
            intentCloneFilter.addFlags(275251200);
            getReactApplicationContext().startActivity(intentCloneFilter);
        }
    }

    public static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        List listAsList = Arrays.asList(permissions);
        int i2 = 0;
        for (int i3 : iArr) {
            if (listAsList.contains(strArr[i2]) && i3 != 0) {
                hasPhoneAccountPromise.resolve(false);
                return;
            }
            i2++;
        }
        hasPhoneAccountPromise.resolve(true);
    }

    public Activity getCurrentReactActivity() {
        return this.reactContext.getCurrentActivity();
    }

    private void registerPhoneAccount(Context context) {
        if (!isConnectionServiceAvailable().booleanValue()) {
            Log.w("RNCallKeep", "[RNCallKeepModule] registerPhoneAccount ignored due to no ConnectionService");
            return;
        }
        initializeTelecomManager();
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][registerPhoneAccount] no react context found.");
            return;
        }
        PhoneAccount.Builder builder = new PhoneAccount.Builder(handle, getApplicationName(appContext));
        if (isSelfManaged()) {
            builder.setCapabilities(2048);
        } else {
            builder.setCapabilities(2);
        }
        WritableMap writableMap = _settings;
        if (writableMap != null && writableMap.hasKey("imageName")) {
            builder.setIcon(Icon.createWithResource(context, context.getResources().getIdentifier(_settings.getString("imageName"), "drawable", context.getPackageName())));
        }
        PhoneAccount phoneAccountBuild = builder.build();
        telephonyManager = (TelephonyManager) appContext.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        telecomManager.registerPhoneAccount(phoneAccountBuild);
    }

    public void sendEventToJS(String str, WritableMap writableMap) {
        boolean zHasActiveCatalystInstance = this.reactContext.hasActiveCatalystInstance();
        Log.v("RNCallKeep", "[RNCallKeepModule] sendEventToJS, eventName: " + str + ", bound: " + zHasActiveCatalystInstance + ", hasListeners: " + this.hasListeners + " args : " + (writableMap != null ? writableMap.toString() : "null"));
        if (zHasActiveCatalystInstance && this.hasListeners) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            return;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (writableMap == null) {
            writableMap = Arguments.createMap();
        }
        writableMapCreateMap.putString("name", str);
        writableMapCreateMap.putMap("data", writableMap);
        this.delayedEvents.pushMap(writableMapCreateMap);
    }

    private String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i = applicationInfo.labelRes;
        return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
    }

    private Boolean hasPermissions() {
        ReactApplicationContext context = getContext();
        boolean z = true;
        for (String str : permissions) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                z = false;
            }
        }
        return Boolean.valueOf(z);
    }

    private boolean hasPhoneAccount() {
        if (telecomManager == null) {
            initializeTelecomManager();
        }
        if (isSelfManaged()) {
            return true;
        }
        return isConnectionServiceAvailable().booleanValue() && telecomManager != null && hasPermissions().booleanValue() && telecomManager.getPhoneAccount(handle) != null && telecomManager.getPhoneAccount(handle).isEnabled();
    }

    protected void registerReceiver() {
        if (this.isReceiverRegistered) {
            return;
        }
        this.isReceiverRegistered = true;
        this.voiceBroadcastReceiver = new VoiceBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_END_CALL);
        intentFilter.addAction(Constants.ACTION_ANSWER_CALL);
        intentFilter.addAction(Constants.ACTION_MUTE_CALL);
        intentFilter.addAction(Constants.ACTION_UNMUTE_CALL);
        intentFilter.addAction(Constants.ACTION_DTMF_TONE);
        intentFilter.addAction(Constants.ACTION_UNHOLD_CALL);
        intentFilter.addAction(Constants.ACTION_HOLD_CALL);
        intentFilter.addAction(Constants.ACTION_ONGOING_CALL);
        intentFilter.addAction(Constants.ACTION_AUDIO_SESSION);
        intentFilter.addAction(Constants.ACTION_CHECK_REACHABILITY);
        intentFilter.addAction(Constants.ACTION_SHOW_INCOMING_CALL_UI);
        intentFilter.addAction(Constants.ACTION_ON_SILENCE_INCOMING_CALL);
        intentFilter.addAction(Constants.ACTION_ON_CREATE_CONNECTION_FAILED);
        intentFilter.addAction(Constants.ACTION_DID_CHANGE_AUDIO_ROUTE);
        ReactApplicationContext reactApplicationContext = this.reactContext;
        if (reactApplicationContext != null) {
            LocalBroadcastManager.getInstance(reactApplicationContext).registerReceiver(this.voiceBroadcastReceiver, intentFilter);
            VoiceConnectionService.startObserving();
        } else {
            this.isReceiverRegistered = false;
        }
    }

    private Context getAppContext() {
        ReactApplicationContext reactApplicationContext = this.reactContext;
        if (reactApplicationContext != null) {
            return reactApplicationContext.getApplicationContext();
        }
        return null;
    }

    private WritableMap storeSettings(ReadableMap readableMap) {
        Context appContext = getAppContext();
        if (appContext == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][storeSettings] no react context found.");
            return MapUtils.readableToWritableMap(readableMap);
        }
        try {
            appContext.getSharedPreferences("rn-callkeep", 0).edit().putString("settings", MapUtils.convertMapToJson(readableMap).toString()).apply();
        } catch (JSONException e) {
            Log.w("RNCallKeep", "[RNCallKeepModule][storeSettings] exception: " + e);
        }
        return MapUtils.readableToWritableMap(readableMap);
    }

    protected static void fetchStoredSettings(Context context) {
        RNCallKeepModule rNCallKeepModule = instance;
        if (rNCallKeepModule == null && context == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][fetchStoredSettings] no instance nor fromContext.");
            return;
        }
        if (context == null) {
            context = rNCallKeepModule.getAppContext();
        }
        _settings = new WritableNativeMap();
        if (context == null) {
            Log.w("RNCallKeep", "[RNCallKeepModule][fetchStoredSettings] no react context found.");
            return;
        }
        try {
            String string = context.getSharedPreferences("rn-callkeep", 0).getString("settings", new JSONObject().toString());
            if (string != null) {
                _settings = MapUtils.convertJsonToMap(new JSONObject(string));
            }
        } catch (JSONException unused) {
        }
    }

    private class VoiceBroadcastReceiver extends BroadcastReceiver {
        /* synthetic */ VoiceBroadcastReceiver(RNCallKeepModule rNCallKeepModule, RNCallKeepModuleIA rNCallKeepModuleIA) {
            this();
        }

        private VoiceBroadcastReceiver() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            HashMap map = (HashMap) intent.getSerializableExtra("attributeMap");
            Log.d("RNCallKeep", "[RNCallKeepModule][onReceive] " + intent.getAction());
            String action = intent.getAction();
            action.hashCode();
            byte b = -1;
            switch (action.hashCode()) {
                case -1728767398:
                    if (action.equals(Constants.ACTION_DID_CHANGE_AUDIO_ROUTE)) {
                        b = 0;
                    }
                    break;
                case -1429647625:
                    if (action.equals(Constants.ACTION_CHECK_REACHABILITY)) {
                        b = 1;
                    }
                    break;
                case -1393018078:
                    if (action.equals(Constants.ACTION_ON_SILENCE_INCOMING_CALL)) {
                        b = 2;
                    }
                    break;
                case -1367062078:
                    if (action.equals(Constants.ACTION_UNMUTE_CALL)) {
                        b = 3;
                    }
                    break;
                case -1325375780:
                    if (action.equals(Constants.ACTION_UNHOLD_CALL)) {
                        b = 4;
                    }
                    break;
                case -1025355595:
                    if (action.equals(Constants.ACTION_SHOW_INCOMING_CALL_UI)) {
                        b = 5;
                    }
                    break;
                case -24512469:
                    if (action.equals(Constants.ACTION_ONGOING_CALL)) {
                        b = 6;
                    }
                    break;
                case 145760463:
                    if (action.equals(Constants.ACTION_WAKE_APP)) {
                        b = 7;
                    }
                    break;
                case 763363071:
                    if (action.equals(Constants.ACTION_DTMF_TONE)) {
                        b = 8;
                    }
                    break;
                case 870609060:
                    if (action.equals(Constants.ACTION_AUDIO_SESSION)) {
                        b = 9;
                    }
                    break;
                case 1012394491:
                    if (action.equals(Constants.ACTION_MUTE_CALL)) {
                        b = 10;
                    }
                    break;
                case 1054080789:
                    if (action.equals(Constants.ACTION_HOLD_CALL)) {
                        b = Ascii.VT;
                    }
                    break;
                case 1387580854:
                    if (action.equals(Constants.ACTION_ANSWER_CALL)) {
                        b = Ascii.FF;
                    }
                    break;
                case 1610331979:
                    if (action.equals(Constants.ACTION_END_CALL)) {
                        b = Ascii.CR;
                    }
                    break;
                case 1634922514:
                    if (action.equals(Constants.ACTION_ON_CREATE_CONNECTION_FAILED)) {
                        b = Ascii.SO;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    writableMapCreateMap.putString("handle", (String) map.get(Constants.EXTRA_CALL_NUMBER));
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    writableMapCreateMap.putString("output", (String) map.get("output"));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidChangeAudioRoute", writableMapCreateMap);
                    break;
                case 1:
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepCheckReachability", null);
                    break;
                case 2:
                    writableMapCreateMap.putString("handle", (String) map.get(Constants.EXTRA_CALL_NUMBER));
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    writableMapCreateMap.putString("name", (String) map.get(Constants.EXTRA_CALLER_NAME));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepOnSilenceIncomingCall", writableMapCreateMap);
                    break;
                case 3:
                    writableMapCreateMap.putBoolean("muted", false);
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidPerformSetMutedCallAction", writableMapCreateMap);
                    break;
                case 4:
                    writableMapCreateMap.putBoolean("hold", false);
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidToggleHoldAction", writableMapCreateMap);
                    break;
                case 5:
                    writableMapCreateMap.putString("handle", (String) map.get(Constants.EXTRA_CALL_NUMBER));
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    writableMapCreateMap.putString("name", (String) map.get(Constants.EXTRA_CALLER_NAME));
                    writableMapCreateMap.putString("hasVideo", (String) map.get(Constants.EXTRA_HAS_VIDEO));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepShowIncomingCallUi", writableMapCreateMap);
                    break;
                case 6:
                    writableMapCreateMap.putString("handle", (String) map.get(Constants.EXTRA_CALL_NUMBER));
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    writableMapCreateMap.putString("name", (String) map.get(Constants.EXTRA_CALLER_NAME));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidReceiveStartCallAction", writableMapCreateMap);
                    break;
                case 7:
                    Intent intent2 = new Intent(RNCallKeepModule.this.reactContext, (Class<?>) RNCallKeepBackgroundMessagingService.class);
                    intent2.putExtra("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    intent2.putExtra("name", (String) map.get(Constants.EXTRA_CALLER_NAME));
                    intent2.putExtra("handle", (String) map.get(Constants.EXTRA_CALL_NUMBER));
                    Log.d("RNCallKeep", "[RNCallKeepModule] wakeUpApplication: " + ((String) map.get(Constants.EXTRA_CALL_UUID)) + ", number : " + ((String) map.get(Constants.EXTRA_CALL_NUMBER)) + ", displayName:" + ((String) map.get(Constants.EXTRA_CALLER_NAME)));
                    if (RNCallKeepModule.this.reactContext.startService(intent2) != null) {
                        HeadlessJsTaskService.acquireWakeLockNow(RNCallKeepModule.this.reactContext);
                    }
                    break;
                case 8:
                    writableMapCreateMap.putString("digits", (String) map.get("DTMF"));
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidPerformDTMFAction", writableMapCreateMap);
                    break;
                case 9:
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidActivateAudioSession", null);
                    break;
                case 10:
                    writableMapCreateMap.putBoolean("muted", true);
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidPerformSetMutedCallAction", writableMapCreateMap);
                    break;
                case 11:
                    writableMapCreateMap.putBoolean("hold", true);
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepDidToggleHoldAction", writableMapCreateMap);
                    break;
                case 12:
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    writableMapCreateMap.putBoolean("withVideo", Boolean.valueOf((String) map.get(Constants.EXTRA_HAS_VIDEO)).booleanValue());
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepPerformAnswerCallAction", writableMapCreateMap);
                    break;
                case 13:
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepPerformEndCallAction", writableMapCreateMap);
                    break;
                case 14:
                    writableMapCreateMap.putString("handle", (String) map.get(Constants.EXTRA_CALL_NUMBER));
                    writableMapCreateMap.putString("callUUID", (String) map.get(Constants.EXTRA_CALL_UUID));
                    writableMapCreateMap.putString("name", (String) map.get(Constants.EXTRA_CALLER_NAME));
                    RNCallKeepModule.this.sendEventToJS("RNCallKeepOnIncomingConnectionFailed", writableMapCreateMap);
                    break;
            }
        }
    }
}
