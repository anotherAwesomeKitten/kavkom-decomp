package com.zxcpoiu.incallmanager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.zxcpoiu.incallmanager.AppRTC.AppRTCBluetoothManager;
import io.sentry.protocol.SentryThread;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import okhttp3.internal.ws.WebSocketProtocol;
import org.webrtc.MediaStreamTrack;

/* JADX INFO: loaded from: classes2.dex */
public class InCallManagerModule extends ReactContextBaseJavaModule implements LifecycleEventListener, AudioManager.OnAudioFocusChangeListener {
    private static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";
    private static final String REACT_NATIVE_MODULE_NAME = "InCallManager";
    private static final String SPEAKERPHONE_AUTO = "auto";
    private static final String SPEAKERPHONE_FALSE = "false";
    private static final String SPEAKERPHONE_TRUE = "true";
    private static final String TAG = "InCallManager";
    private Set<AudioDevice> audioDevices;
    private AudioManager audioManager;
    private boolean audioManagerActivated;
    private Map<String, Uri> audioUriMap;
    private boolean automatic;
    private AppRTCBluetoothManager bluetoothManager;
    private Uri bundleBusytoneUri;
    private Uri bundleRingbackUri;
    private Uri bundleRingtoneUri;
    private AudioDevice defaultAudioDevice;
    private int defaultAudioMode;
    private Uri defaultBusytoneUri;
    private Uri defaultRingbackUri;
    private Uri defaultRingtoneUri;
    private boolean defaultSpeakerOn;
    private int forceSpeakerOn;
    private boolean hasWiredHeadset;
    private boolean isAudioFocused;
    private boolean isOrigAudioSetupStored;
    private boolean isProximityRegistered;
    private WindowManager.LayoutParams lastLayoutParams;
    private AudioAttributes mAudioAttributes;
    private AudioFocusRequest mAudioFocusRequest;
    private MyPlayerInterface mBusytone;
    private String mPackageName;
    private PowerManager mPowerManager;
    private MyPlayerInterface mRingback;
    private MyPlayerInterface mRingtone;
    private Handler mRingtoneCountDownHandler;
    private WindowManager mWindowManager;
    private String media;
    private BroadcastReceiver mediaButtonReceiver;
    private BroadcastReceiver noisyAudioReceiver;
    private int origAudioMode;
    private boolean origIsMicrophoneMute;
    private boolean origIsSpeakerPhoneOn;
    private boolean proximityIsNear;
    private final InCallProximityManager proximityManager;
    private String ringtoneName;
    private int savedAudioMode;
    private boolean savedIsMicrophoneMute;
    private boolean savedIsSpeakerPhoneOn;
    private AudioDevice selectedAudioDevice;
    private final String useSpeakerphone;
    private AudioDevice userSelectedAudioDevice;
    private final InCallWakeLockUtils wakeLockUtils;
    private BroadcastReceiver wiredHeadsetReceiver;

    public enum AudioDevice {
        SPEAKER_PHONE,
        WIRED_HEADSET,
        EARPIECE,
        BLUETOOTH,
        NONE
    }

    public enum AudioManagerState {
        UNINITIALIZED,
        PREINITIALIZED,
        RUNNING
    }

    interface MyPlayerInterface {
        boolean isPlaying();

        void startPlay(Map<String, Object> map);

        void stopPlay();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "InCallManager";
    }

    public InCallManagerModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mPackageName = BuildConfig.LIBRARY_PACKAGE_NAME;
        this.audioManagerActivated = false;
        this.isAudioFocused = false;
        this.isOrigAudioSetupStored = false;
        this.origIsSpeakerPhoneOn = false;
        this.origIsMicrophoneMute = false;
        this.origAudioMode = -2;
        this.defaultSpeakerOn = false;
        this.defaultAudioMode = 3;
        this.forceSpeakerOn = 0;
        this.automatic = true;
        this.isProximityRegistered = false;
        this.proximityIsNear = false;
        this.defaultRingtoneUri = Settings.System.DEFAULT_RINGTONE_URI;
        this.defaultRingbackUri = Settings.System.DEFAULT_RINGTONE_URI;
        this.defaultBusytoneUri = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.media = MediaStreamTrack.AUDIO_TRACK_KIND;
        this.ringtoneName = "incallmanager_ringtone";
        this.savedAudioMode = -2;
        this.savedIsSpeakerPhoneOn = false;
        this.savedIsMicrophoneMute = false;
        this.hasWiredHeadset = false;
        this.defaultAudioDevice = AudioDevice.NONE;
        this.useSpeakerphone = "auto";
        this.bluetoothManager = null;
        this.audioDevices = new HashSet();
        this.mPackageName = reactApplicationContext.getPackageName();
        reactApplicationContext.addLifecycleEventListener(this);
        this.mWindowManager = (WindowManager) reactApplicationContext.getSystemService("window");
        this.mPowerManager = (PowerManager) reactApplicationContext.getSystemService("power");
        this.audioManager = (AudioManager) reactApplicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        HashMap map = new HashMap();
        this.audioUriMap = map;
        map.put("defaultRingtoneUri", this.defaultRingtoneUri);
        this.audioUriMap.put("defaultRingbackUri", this.defaultRingbackUri);
        this.audioUriMap.put("defaultBusytoneUri", this.defaultBusytoneUri);
        this.audioUriMap.put("bundleRingtoneUri", this.bundleRingtoneUri);
        this.audioUriMap.put("bundleRingbackUri", this.bundleRingbackUri);
        this.audioUriMap.put("bundleBusytoneUri", this.bundleBusytoneUri);
        this.wakeLockUtils = new InCallWakeLockUtils(reactApplicationContext);
        this.proximityManager = InCallProximityManager.create(reactApplicationContext, this);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0(reactApplicationContext);
            }
        });
        Log.d("InCallManager", "InCallManager initialized");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ReactApplicationContext reactApplicationContext) {
        this.bluetoothManager = AppRTCBluetoothManager.create(reactApplicationContext, this);
    }

    private void manualTurnScreenOff() {
        Log.d("InCallManager", "manualTurnScreenOff()");
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            Log.d("InCallManager", "ReactContext doesn't have any Activity attached.");
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.1
                @Override // java.lang.Runnable
                public void run() {
                    Window window = currentActivity.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    InCallManagerModule.this.lastLayoutParams = attributes;
                    attributes.screenBrightness = 0.0f;
                    window.setAttributes(attributes);
                    window.clearFlags(128);
                }
            });
        }
    }

    private void manualTurnScreenOn() {
        Log.d("InCallManager", "manualTurnScreenOn()");
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            Log.d("InCallManager", "ReactContext doesn't have any Activity attached.");
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.2
                @Override // java.lang.Runnable
                public void run() {
                    Window window = currentActivity.getWindow();
                    if (InCallManagerModule.this.lastLayoutParams != null) {
                        window.setAttributes(InCallManagerModule.this.lastLayoutParams);
                    } else {
                        WindowManager.LayoutParams attributes = window.getAttributes();
                        attributes.screenBrightness = -1.0f;
                        window.setAttributes(attributes);
                    }
                    window.addFlags(128);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void storeOriginalAudioSetup() {
        Log.d("InCallManager", "storeOriginalAudioSetup()");
        if (this.isOrigAudioSetupStored) {
            return;
        }
        this.origAudioMode = this.audioManager.getMode();
        this.origIsSpeakerPhoneOn = this.audioManager.isSpeakerphoneOn();
        this.origIsMicrophoneMute = this.audioManager.isMicrophoneMute();
        this.isOrigAudioSetupStored = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreOriginalAudioSetup() {
        Log.d("InCallManager", "restoreOriginalAudioSetup()");
        if (this.isOrigAudioSetupStored) {
            setSpeakerphoneOn(this.origIsSpeakerPhoneOn);
            setMicrophoneMute(this.origIsMicrophoneMute);
            this.audioManager.setMode(this.origAudioMode);
            if (getCurrentActivity() != null) {
                getCurrentActivity().setVolumeControlStream(Integer.MIN_VALUE);
            }
            this.isOrigAudioSetupStored = false;
        }
    }

    private void startWiredHeadsetEvent() {
        if (this.wiredHeadsetReceiver == null) {
            Log.d("InCallManager", "startWiredHeadsetEvent()");
            IntentFilter intentFilter = new IntentFilter(ACTION_HEADSET_PLUG);
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (InCallManagerModule.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
                        InCallManagerModule.this.hasWiredHeadset = intent.getIntExtra(SentryThread.JsonKeys.STATE, 0) == 1;
                        InCallManagerModule.this.updateAudioRoute();
                        String stringExtra = intent.getStringExtra("name");
                        if (stringExtra == null) {
                            stringExtra = "";
                        }
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putBoolean("isPlugged", intent.getIntExtra(SentryThread.JsonKeys.STATE, 0) == 1);
                        writableMapCreateMap.putBoolean("hasMic", intent.getIntExtra("microphone", 0) == 1);
                        writableMapCreateMap.putString("deviceName", stringExtra);
                        InCallManagerModule.this.sendEvent("WiredHeadset", writableMapCreateMap);
                        return;
                    }
                    InCallManagerModule.this.hasWiredHeadset = false;
                }
            };
            this.wiredHeadsetReceiver = broadcastReceiver;
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void stopWiredHeadsetEvent() {
        if (this.wiredHeadsetReceiver != null) {
            Log.d("InCallManager", "stopWiredHeadsetEvent()");
            unregisterReceiver(this.wiredHeadsetReceiver);
            this.wiredHeadsetReceiver = null;
        }
    }

    private void startNoisyAudioEvent() {
        if (this.noisyAudioReceiver == null) {
            Log.d("InCallManager", "startNoisyAudioEvent()");
            IntentFilter intentFilter = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                        InCallManagerModule.this.updateAudioRoute();
                        InCallManagerModule.this.sendEvent("NoisyAudio", null);
                    }
                }
            };
            this.noisyAudioReceiver = broadcastReceiver;
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void stopNoisyAudioEvent() {
        if (this.noisyAudioReceiver != null) {
            Log.d("InCallManager", "stopNoisyAudioEvent()");
            unregisterReceiver(this.noisyAudioReceiver);
            this.noisyAudioReceiver = null;
        }
    }

    private void startMediaButtonEvent() {
        if (this.mediaButtonReceiver == null) {
            Log.d("InCallManager", "startMediaButtonEvent()");
            IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_BUTTON");
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.5
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    String str;
                    if ("android.intent.action.MEDIA_BUTTON".equals(intent.getAction())) {
                        int keyCode = ((KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")).getKeyCode();
                        switch (keyCode) {
                            case JpegTranscoderUtils.DEFAULT_JPEG_QUALITY /* 85 */:
                                str = "KEYCODE_MEDIA_PLAY_PAUSE";
                                break;
                            case 86:
                                str = "KEYCODE_MEDIA_STOP";
                                break;
                            case 87:
                                str = "KEYCODE_MEDIA_NEXT";
                                break;
                            case 88:
                                str = "KEYCODE_MEDIA_PREVIOUS";
                                break;
                            default:
                                switch (keyCode) {
                                    case WebSocketProtocol.PAYLOAD_SHORT /* 126 */:
                                        str = "KEYCODE_MEDIA_PLAY";
                                        break;
                                    case 127:
                                        str = "KEYCODE_MEDIA_PAUSE";
                                        break;
                                    case 128:
                                        str = "KEYCODE_MEDIA_CLOSE";
                                        break;
                                    case 129:
                                        str = "KEYCODE_MEDIA_EJECT";
                                        break;
                                    case 130:
                                        str = "KEYCODE_MEDIA_RECORD";
                                        break;
                                    default:
                                        str = "KEYCODE_UNKNOW";
                                        break;
                                }
                                break;
                        }
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putString("eventText", str);
                        writableMapCreateMap.putInt("eventCode", keyCode);
                        InCallManagerModule.this.sendEvent("MediaButton", writableMapCreateMap);
                    }
                }
            };
            this.mediaButtonReceiver = broadcastReceiver;
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void stopMediaButtonEvent() {
        if (this.mediaButtonReceiver != null) {
            Log.d("InCallManager", "stopMediaButtonEvent()");
            unregisterReceiver(this.mediaButtonReceiver);
            this.mediaButtonReceiver = null;
        }
    }

    public void onProximitySensorChangedState(boolean z) {
        if (this.automatic && getSelectedAudioDevice() == AudioDevice.EARPIECE) {
            if (z) {
                turnScreenOff();
            } else {
                turnScreenOn();
            }
            updateAudioRoute();
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("isNear", z);
        sendEvent("Proximity", writableMapCreateMap);
    }

    @ReactMethod
    public void startProximitySensor() {
        if (!this.proximityManager.isProximitySupported()) {
            Log.d("InCallManager", "Proximity Sensor is not supported.");
            return;
        }
        if (this.isProximityRegistered) {
            Log.d("InCallManager", "Proximity Sensor is already registered.");
        } else if (!this.proximityManager.start()) {
            Log.d("InCallManager", "proximityManager.start() failed. return false");
        } else {
            Log.d("InCallManager", "startProximitySensor()");
            this.isProximityRegistered = true;
        }
    }

    @ReactMethod
    public void stopProximitySensor() {
        if (!this.proximityManager.isProximitySupported()) {
            Log.d("InCallManager", "Proximity Sensor is not supported.");
        } else {
            if (!this.isProximityRegistered) {
                Log.d("InCallManager", "Proximity Sensor is not registered.");
                return;
            }
            Log.d("InCallManager", "stopProximitySensor()");
            this.proximityManager.stop();
            this.isProximityRegistered = false;
        }
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        String str;
        switch (i) {
            case -3:
                str = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
                break;
            case -2:
                str = "AUDIOFOCUS_LOSS_TRANSIENT";
                break;
            case -1:
                str = "AUDIOFOCUS_LOSS";
                break;
            case 0:
                str = "AUDIOFOCUS_NONE";
                break;
            case 1:
                str = "AUDIOFOCUS_GAIN";
                break;
            case 2:
                str = "AUDIOFOCUS_GAIN_TRANSIENT";
                break;
            case 3:
                str = "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK";
                break;
            case 4:
                str = "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE";
                break;
            default:
                str = "AUDIOFOCUS_UNKNOW";
                break;
        }
        Log.d("InCallManager", "onAudioFocusChange(): " + i + " - " + str);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("eventText", str);
        writableMapCreateMap.putInt("eventCode", i);
        sendEvent("onAudioFocusChange", writableMapCreateMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, WritableMap writableMap) {
        try {
            ReactApplicationContext reactApplicationContext = getReactApplicationContext();
            if (reactApplicationContext == null || !reactApplicationContext.hasActiveCatalystInstance()) {
                Log.e("InCallManager", "sendEvent(): reactContext is null or not having CatalystInstance yet.");
            } else {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            }
        } catch (RuntimeException unused) {
            Log.e("InCallManager", "sendEvent(): java.lang.RuntimeException: Trying to invoke JS before CatalystInstance has been set!");
        }
    }

    @ReactMethod
    public void start(String str, boolean z, String str2) {
        this.media = str;
        if (str.equals("video")) {
            this.defaultSpeakerOn = true;
        } else {
            this.defaultSpeakerOn = false;
        }
        this.automatic = z;
        if (this.audioManagerActivated) {
            return;
        }
        this.audioManagerActivated = true;
        Log.d("InCallManager", "start audioRouteManager");
        this.wakeLockUtils.acquirePartialWakeLock();
        MyPlayerInterface myPlayerInterface = this.mRingtone;
        if (myPlayerInterface != null && myPlayerInterface.isPlaying()) {
            Log.d("InCallManager", "stop ringtone");
            stopRingtone();
        }
        storeOriginalAudioSetup();
        requestAudioFocus();
        startEvents();
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$start$1();
            }
        });
        this.audioManager.setMode(this.defaultAudioMode);
        setSpeakerphoneOn(this.defaultSpeakerOn);
        setMicrophoneMute(false);
        this.forceSpeakerOn = 0;
        this.hasWiredHeadset = hasWiredHeadset();
        this.defaultAudioDevice = (!this.defaultSpeakerOn && hasEarpiece()) ? AudioDevice.EARPIECE : AudioDevice.SPEAKER_PHONE;
        this.userSelectedAudioDevice = AudioDevice.NONE;
        this.selectedAudioDevice = AudioDevice.NONE;
        this.audioDevices.clear();
        updateAudioRoute();
        if (str2.isEmpty()) {
            return;
        }
        startRingback(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$start$1() {
        this.bluetoothManager.start();
    }

    public void stop() {
        stop("");
    }

    @ReactMethod
    public void stop(String str) {
        if (this.audioManagerActivated) {
            stopRingback();
            if (!str.isEmpty() && startBusytone(str)) {
                Log.d("InCallManager", "play busytone before stop InCallManager");
                return;
            }
            Log.d("InCallManager", "stop() InCallManager");
            stopBusytone();
            stopEvents();
            setSpeakerphoneOn(false);
            setMicrophoneMute(false);
            this.forceSpeakerOn = 0;
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$stop$2();
                }
            });
            restoreOriginalAudioSetup();
            abandonAudioFocus();
            this.audioManagerActivated = false;
            this.wakeLockUtils.releasePartialWakeLock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stop$2() {
        this.bluetoothManager.stop();
    }

    private void startEvents() {
        startWiredHeadsetEvent();
        startNoisyAudioEvent();
        startMediaButtonEvent();
        startProximitySensor();
        setKeepScreenOn(true);
    }

    private void stopEvents() {
        stopWiredHeadsetEvent();
        stopNoisyAudioEvent();
        stopMediaButtonEvent();
        stopProximitySensor();
        setKeepScreenOn(false);
        turnScreenOn();
    }

    @ReactMethod
    public void requestAudioFocusJS(Promise promise) {
        promise.resolve(requestAudioFocus());
    }

    private String requestAudioFocus() {
        String strRequestAudioFocusOld;
        if (Build.VERSION.SDK_INT >= 26) {
            strRequestAudioFocusOld = requestAudioFocusV26();
        } else {
            strRequestAudioFocusOld = requestAudioFocusOld();
        }
        Log.d("InCallManager", "requestAudioFocus(): res = " + strRequestAudioFocusOld);
        return strRequestAudioFocusOld;
    }

    private String requestAudioFocusV26() {
        if (this.isAudioFocused) {
            return "";
        }
        if (this.mAudioAttributes == null) {
            this.mAudioAttributes = new AudioAttributes.Builder().setUsage(2).setContentType(1).build();
        }
        if (this.mAudioFocusRequest == null) {
            this.mAudioFocusRequest = new AudioFocusRequest.Builder(2).setAudioAttributes(this.mAudioAttributes).setAcceptsDelayedFocusGain(false).setWillPauseWhenDucked(false).setOnAudioFocusChangeListener(this).build();
        }
        int iRequestAudioFocus = this.audioManager.requestAudioFocus(this.mAudioFocusRequest);
        if (iRequestAudioFocus == 0) {
            return "AUDIOFOCUS_REQUEST_FAILED";
        }
        if (iRequestAudioFocus == 1) {
            this.isAudioFocused = true;
            return "AUDIOFOCUS_REQUEST_GRANTED";
        }
        if (iRequestAudioFocus == 2) {
            return "AUDIOFOCUS_REQUEST_DELAYED";
        }
        return "AUDIOFOCUS_REQUEST_UNKNOWN";
    }

    private String requestAudioFocusOld() {
        if (this.isAudioFocused) {
            return "";
        }
        int iRequestAudioFocus = this.audioManager.requestAudioFocus(this, 0, 2);
        if (iRequestAudioFocus == 0) {
            return "AUDIOFOCUS_REQUEST_FAILED";
        }
        if (iRequestAudioFocus == 1) {
            this.isAudioFocused = true;
            return "AUDIOFOCUS_REQUEST_GRANTED";
        }
        return "AUDIOFOCUS_REQUEST_UNKNOWN";
    }

    @ReactMethod
    public void abandonAudioFocusJS(Promise promise) {
        promise.resolve(abandonAudioFocus());
    }

    private String abandonAudioFocus() {
        String strAbandonAudioFocusOld;
        if (Build.VERSION.SDK_INT >= 26) {
            strAbandonAudioFocusOld = abandonAudioFocusV26();
        } else {
            strAbandonAudioFocusOld = abandonAudioFocusOld();
        }
        Log.d("InCallManager", "abandonAudioFocus(): res = " + strAbandonAudioFocusOld);
        return strAbandonAudioFocusOld;
    }

    private String abandonAudioFocusV26() {
        AudioFocusRequest audioFocusRequest;
        if (!this.isAudioFocused || (audioFocusRequest = this.mAudioFocusRequest) == null) {
            return "";
        }
        int iAbandonAudioFocusRequest = this.audioManager.abandonAudioFocusRequest(audioFocusRequest);
        if (iAbandonAudioFocusRequest == 0) {
            return "AUDIOFOCUS_REQUEST_FAILED";
        }
        if (iAbandonAudioFocusRequest == 1) {
            this.isAudioFocused = false;
            return "AUDIOFOCUS_REQUEST_GRANTED";
        }
        return "AUDIOFOCUS_REQUEST_UNKNOWN";
    }

    private String abandonAudioFocusOld() {
        if (!this.isAudioFocused) {
            return "";
        }
        int iAbandonAudioFocus = this.audioManager.abandonAudioFocus(this);
        if (iAbandonAudioFocus == 0) {
            return "AUDIOFOCUS_REQUEST_FAILED";
        }
        if (iAbandonAudioFocus == 1) {
            this.isAudioFocused = false;
            return "AUDIOFOCUS_REQUEST_GRANTED";
        }
        return "AUDIOFOCUS_REQUEST_UNKNOWN";
    }

    @ReactMethod
    public void pokeScreen(int i) {
        Log.d("InCallManager", "pokeScreen()");
        this.wakeLockUtils.acquirePokeFullWakeLockReleaseAfter(i);
    }

    private void debugScreenPowerState() {
        String str;
        String str2 = String.format("%s", Boolean.valueOf(this.mPowerManager.isDeviceIdleMode()));
        String str3 = String.format("%s", Boolean.valueOf(this.mPowerManager.isIgnoringBatteryOptimizations(this.mPackageName)));
        String str4 = String.format("%s", Boolean.valueOf(this.mPowerManager.isPowerSaveMode()));
        String str5 = String.format("%s", Boolean.valueOf(this.mPowerManager.isInteractive()));
        int state = this.mWindowManager.getDefaultDisplay().getState();
        if (state == 1) {
            str = "STATE_OFF";
        } else if (state == 2) {
            str = "STATE_ON";
        } else if (state == 3) {
            str = "STATE_DOZE";
        } else {
            str = state != 4 ? "unknow" : "STATE_DOZE_SUSPEND";
        }
        Log.d("InCallManager", String.format("debugScreenPowerState(): screenState='%s', isInteractive='%s', isPowerSaveMode='%s', isDeviceIdleMode='%s', isIgnoringBatteryOptimizations='%s'", str, str5, str4, str2, str3));
    }

    @ReactMethod
    public void turnScreenOn() {
        if (this.proximityManager.isProximityWakeLockSupported()) {
            Log.d("InCallManager", "turnScreenOn(): use proximity lock.");
            this.proximityManager.releaseProximityWakeLock(true);
        } else {
            Log.d("InCallManager", "turnScreenOn(): proximity lock is not supported. try manually.");
            manualTurnScreenOn();
        }
    }

    @ReactMethod
    public void turnScreenOff() {
        if (this.proximityManager.isProximityWakeLockSupported()) {
            Log.d("InCallManager", "turnScreenOff(): use proximity lock.");
            this.proximityManager.acquireProximityWakeLock();
        } else {
            Log.d("InCallManager", "turnScreenOff(): proximity lock is not supported. try manually.");
            manualTurnScreenOff();
        }
    }

    @ReactMethod
    public void setKeepScreenOn(final boolean z) {
        Log.d("InCallManager", "setKeepScreenOn() " + z);
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            Log.d("InCallManager", "ReactContext doesn't have any Activity attached.");
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.6
                @Override // java.lang.Runnable
                public void run() {
                    Window window = currentActivity.getWindow();
                    if (z) {
                        window.addFlags(128);
                    } else {
                        window.clearFlags(128);
                    }
                }
            });
        }
    }

    @ReactMethod
    public void setSpeakerphoneOn(boolean z) {
        if (z != this.audioManager.isSpeakerphoneOn()) {
            Log.d("InCallManager", "setSpeakerphoneOn(): " + z);
            this.audioManager.setMode(this.defaultAudioMode);
            this.audioManager.setSpeakerphoneOn(z);
        }
    }

    @ReactMethod
    public void setForceSpeakerphoneOn(int i) {
        if (i < -1 || i > 1) {
            return;
        }
        Log.d("InCallManager", "setForceSpeakerphoneOn() flag: " + i);
        this.forceSpeakerOn = i;
        if (i == 1) {
            selectAudioDevice(AudioDevice.SPEAKER_PHONE);
        } else if (i == -1) {
            selectAudioDevice(AudioDevice.EARPIECE);
        } else {
            selectAudioDevice(AudioDevice.NONE);
        }
    }

    @ReactMethod
    public void setMicrophoneMute(boolean z) {
        if (z != this.audioManager.isMicrophoneMute()) {
            Log.d("InCallManager", "setMicrophoneMute(): " + z);
            this.audioManager.setMicrophoneMute(z);
        }
    }

    @ReactMethod
    public void startRingback(String str) {
        if (str.isEmpty()) {
            return;
        }
        try {
            Log.d("InCallManager", "startRingback(): UriType=" + str);
            MyPlayerInterface myPlayerInterface = this.mRingback;
            if (myPlayerInterface != null) {
                if (myPlayerInterface.isPlaying()) {
                    Log.d("InCallManager", "startRingback(): is already playing");
                    return;
                }
                stopRingback();
            }
            HashMap map = new HashMap();
            map.put("name", "mRingback");
            if (str.equals("_DTMF_")) {
                myToneGenerator mytonegenerator = new myToneGenerator(myToneGenerator.RINGBACK);
                this.mRingback = mytonegenerator;
                mytonegenerator.startPlay(map);
                return;
            }
            Uri ringbackUri = getRingbackUri(str);
            if (ringbackUri == null) {
                Log.d("InCallManager", "startRingback(): no available media");
                return;
            }
            this.mRingback = new myMediaPlayer();
            map.put("sourceUri", ringbackUri);
            map.put("setLooping", true);
            map.put("audioUsage", 2);
            map.put("audioContentType", 2);
            setMediaPlayerEvents((MediaPlayer) this.mRingback, "mRingback");
            this.mRingback.startPlay(map);
        } catch (Exception e) {
            Log.d("InCallManager", "startRingback() failed", e);
        }
    }

    @ReactMethod
    public void stopRingback() {
        try {
            MyPlayerInterface myPlayerInterface = this.mRingback;
            if (myPlayerInterface != null) {
                myPlayerInterface.stopPlay();
                this.mRingback = null;
            }
        } catch (Exception unused) {
            Log.d("InCallManager", "stopRingback() failed");
        }
    }

    public boolean startBusytone(String str) {
        if (str.isEmpty()) {
            return false;
        }
        try {
            Log.d("InCallManager", "startBusytone(): UriType=" + str);
            MyPlayerInterface myPlayerInterface = this.mBusytone;
            if (myPlayerInterface != null) {
                if (myPlayerInterface.isPlaying()) {
                    Log.d("InCallManager", "startBusytone(): is already playing");
                    return false;
                }
                stopBusytone();
            }
            HashMap map = new HashMap();
            map.put("name", "mBusytone");
            if (str.equals("_DTMF_")) {
                myToneGenerator mytonegenerator = new myToneGenerator(102);
                this.mBusytone = mytonegenerator;
                mytonegenerator.startPlay(map);
                return true;
            }
            Uri busytoneUri = getBusytoneUri(str);
            if (busytoneUri == null) {
                Log.d("InCallManager", "startBusytone(): no available media");
                return false;
            }
            this.mBusytone = new myMediaPlayer();
            map.put("sourceUri", busytoneUri);
            map.put("setLooping", false);
            map.put("audioUsage", 2);
            map.put("audioContentType", 4);
            setMediaPlayerEvents((MediaPlayer) this.mBusytone, "mBusytone");
            this.mBusytone.startPlay(map);
            return true;
        } catch (Exception e) {
            Log.d("InCallManager", "startBusytone() failed", e);
            return false;
        }
    }

    public void stopBusytone() {
        try {
            MyPlayerInterface myPlayerInterface = this.mBusytone;
            if (myPlayerInterface != null) {
                myPlayerInterface.stopPlay();
                this.mBusytone = null;
            }
        } catch (Exception unused) {
            Log.d("InCallManager", "stopBusytone() failed");
        }
    }

    @ReactMethod
    public void startRingtone(final String str, final int i) {
        new Thread() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.7
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Looper.prepare();
                    Log.d("InCallManager", "startRingtone(): UriType=" + str);
                    if (InCallManagerModule.this.mRingtone != null) {
                        if (InCallManagerModule.this.mRingtone.isPlaying()) {
                            Log.d("InCallManager", "startRingtone(): is already playing");
                            return;
                        }
                        InCallManagerModule.this.stopRingtone();
                    }
                    if (InCallManagerModule.this.audioManager.getStreamVolume(2) == 0) {
                        Log.d("InCallManager", "startRingtone(): ringer is silent. leave without play.");
                        return;
                    }
                    Uri ringtoneUri = InCallManagerModule.this.getRingtoneUri(str);
                    if (ringtoneUri == null) {
                        Log.d("InCallManager", "startRingtone(): no available media");
                        return;
                    }
                    if (InCallManagerModule.this.audioManagerActivated) {
                        InCallManagerModule.this.stop();
                    }
                    InCallManagerModule.this.wakeLockUtils.acquirePartialWakeLock();
                    InCallManagerModule.this.storeOriginalAudioSetup();
                    HashMap map = new HashMap();
                    InCallManagerModule.this.mRingtone = new myMediaPlayer();
                    map.put("name", "mRingtone");
                    map.put("sourceUri", ringtoneUri);
                    map.put("setLooping", true);
                    map.put("audioUsage", 6);
                    map.put("audioContentType", 2);
                    InCallManagerModule inCallManagerModule = InCallManagerModule.this;
                    inCallManagerModule.setMediaPlayerEvents((MediaPlayer) inCallManagerModule.mRingtone, "mRingtone");
                    InCallManagerModule.this.mRingtone.startPlay(map);
                    if (i > 0) {
                        InCallManagerModule.this.mRingtoneCountDownHandler = new Handler();
                        InCallManagerModule.this.mRingtoneCountDownHandler.postDelayed(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.7.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    Log.d("InCallManager", String.format("mRingtoneCountDownHandler.stopRingtone() timeout after %d seconds", Integer.valueOf(i)));
                                    InCallManagerModule.this.stopRingtone();
                                } catch (Exception unused) {
                                    Log.d("InCallManager", "mRingtoneCountDownHandler.stopRingtone() failed.");
                                }
                            }
                        }, i * 1000);
                    }
                    Looper.loop();
                } catch (Exception e) {
                    InCallManagerModule.this.wakeLockUtils.releasePartialWakeLock();
                    Log.e("InCallManager", "startRingtone() failed", e);
                }
            }
        }.start();
    }

    @ReactMethod
    public void stopRingtone() {
        new Thread() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.8
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    if (InCallManagerModule.this.mRingtone != null) {
                        InCallManagerModule.this.mRingtone.stopPlay();
                        InCallManagerModule.this.mRingtone = null;
                        InCallManagerModule.this.restoreOriginalAudioSetup();
                    }
                    if (InCallManagerModule.this.mRingtoneCountDownHandler != null) {
                        InCallManagerModule.this.mRingtoneCountDownHandler.removeCallbacksAndMessages(null);
                        InCallManagerModule.this.mRingtoneCountDownHandler = null;
                    }
                } catch (Exception unused) {
                    Log.d("InCallManager", "stopRingtone() failed");
                }
                InCallManagerModule.this.wakeLockUtils.releasePartialWakeLock();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMediaPlayerEvents(MediaPlayer mediaPlayer, final String str) {
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.9
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                Log.d("InCallManager", String.format("MediaPlayer %s onError(). what: %d, extra: %d", str, Integer.valueOf(i), Integer.valueOf(i2)));
                return true;
            }
        });
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.10
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer2, int i, int i2) {
                Log.d("InCallManager", String.format("MediaPlayer %s onInfo(). what: %d, extra: %d", str, Integer.valueOf(i), Integer.valueOf(i2)));
                return true;
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.11
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer2) {
                Log.d("InCallManager", String.format("MediaPlayer %s onPrepared(), start play, isSpeakerPhoneOn %b", str, Boolean.valueOf(InCallManagerModule.this.audioManager.isSpeakerphoneOn())));
                if (str.equals("mBusytone") || str.equals("mRingback")) {
                    InCallManagerModule.this.audioManager.setMode(3);
                } else if (str.equals("mRingtone")) {
                    InCallManagerModule.this.audioManager.setMode(1);
                }
                InCallManagerModule.this.updateAudioRoute();
                mediaPlayer2.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule.12
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer2) {
                Log.d("InCallManager", String.format("MediaPlayer %s onCompletion()", str));
                if (str.equals("mBusytone")) {
                    Log.d("InCallManager", "MyMediaPlayer(): invoke stop()");
                    InCallManagerModule.this.stop();
                }
            }
        });
    }

    @ReactMethod
    public void setRingtoneName(String str) {
        this.ringtoneName = str;
        this.audioUriMap.remove("bundleRingtoneUri");
    }

    @ReactMethod
    public void getAudioUriJS(String str, String str2, Promise promise) {
        Uri ringtoneUri;
        if (str.equals("ringback")) {
            ringtoneUri = getRingbackUri(str2);
        } else if (str.equals("busytone")) {
            ringtoneUri = getBusytoneUri(str2);
        } else {
            ringtoneUri = str.equals("ringtone") ? getRingtoneUri(str2) : null;
        }
        try {
            if (ringtoneUri != null) {
                promise.resolve(ringtoneUri.toString());
            } else {
                promise.reject("failed");
            }
        } catch (Exception unused) {
            promise.reject("failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getRingtoneUri(String str) {
        String str2 = this.ringtoneName;
        if (str.equals("_DEFAULT_") || str.isEmpty()) {
            return getDefaultUserUri("defaultRingtoneUri");
        }
        return getAudioUri(str, str2, "mp3", "media_volume.ogg", "/system/media/audio/ui", "bundleRingtoneUri", "defaultRingtoneUri");
    }

    private Uri getRingbackUri(String str) {
        if (str.equals("_DEFAULT_") || str.isEmpty()) {
            return getDefaultUserUri("defaultRingbackUri");
        }
        return getAudioUri(str, "incallmanager_ringback", "mp3", "media_volume.ogg", "/system/media/audio/ui", "bundleRingbackUri", "defaultRingbackUri");
    }

    private Uri getBusytoneUri(String str) {
        if (str.equals("_DEFAULT_") || str.isEmpty()) {
            return getDefaultUserUri("defaultBusytoneUri");
        }
        return getAudioUri(str, "incallmanager_busytone", "mp3", "LowBattery.ogg", "/system/media/audio/ui", "bundleBusytoneUri", "defaultBusytoneUri");
    }

    private Uri getAudioUri(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        int identifier;
        if (str.equals("_BUNDLE_")) {
            if (this.audioUriMap.get(str6) == null) {
                ReactApplicationContext reactApplicationContext = getReactApplicationContext();
                if (reactApplicationContext != null) {
                    identifier = reactApplicationContext.getResources().getIdentifier(str2, "raw", this.mPackageName);
                } else {
                    Log.d("InCallManager", "getAudioUri() reactContext is null");
                    identifier = 0;
                }
                if (identifier <= 0) {
                    Log.d("InCallManager", String.format("getAudioUri() %s.%s not found in bundle.", str2, str3));
                    this.audioUriMap.put(str6, null);
                    return getDefaultUserUri(str7);
                }
                this.audioUriMap.put(str6, Uri.parse("android.resource://" + this.mPackageName + "/" + Integer.toString(identifier)));
                Log.d("InCallManager", "getAudioUri() using: " + str);
                return this.audioUriMap.get(str6);
            }
            Log.d("InCallManager", "getAudioUri() using: " + str);
            return this.audioUriMap.get(str6);
        }
        String str8 = str5 + "/" + str;
        Uri sysFileUri = getSysFileUri(str8);
        if (sysFileUri == null) {
            Log.d("InCallManager", "getAudioUri() using user default");
            return getDefaultUserUri(str7);
        }
        Log.d("InCallManager", "getAudioUri() using internal: " + str8);
        this.audioUriMap.put(str7, sysFileUri);
        return sysFileUri;
    }

    private Uri getSysFileUri(String str) {
        File file = new File(str);
        if (file.isFile()) {
            return Uri.fromFile(file);
        }
        return null;
    }

    private Uri getDefaultUserUri(String str) {
        if (str.equals("defaultRingtoneUri")) {
            return Settings.System.DEFAULT_RINGTONE_URI;
        }
        if (str.equals("defaultRingbackUri")) {
            return Settings.System.DEFAULT_RINGTONE_URI;
        }
        if (str.equals("defaultBusytoneUri")) {
            return Settings.System.DEFAULT_NOTIFICATION_URI;
        }
        return Settings.System.DEFAULT_NOTIFICATION_URI;
    }

    private class myToneGenerator extends Thread implements MyPlayerInterface {
        public static final int BEEP = 101;
        public static final int BUSY = 102;
        public static final int CALLEND = 103;
        public static final int CALLWAITING = 104;
        public static final int RINGBACK = 105;
        public static final int SILENT = 106;
        private static final int loadBufferWaitTimeMs = 20;
        private static final int maxWaitTimeMs = 3600000;
        private static final int toneVolume = 100;
        public String caller;
        private int toneCategory;
        private int toneType;
        private boolean playing = false;
        public int customWaitTimeMs = maxWaitTimeMs;

        myToneGenerator(int i) {
            this.toneCategory = i;
        }

        public void setCustomWaitTime(int i) {
            this.customWaitTimeMs = i;
        }

        @Override // com.zxcpoiu.incallmanager.InCallManagerModule.MyPlayerInterface
        public void startPlay(Map map) {
            this.caller = (String) map.get("name");
            start();
        }

        @Override // com.zxcpoiu.incallmanager.InCallManagerModule.MyPlayerInterface
        public boolean isPlaying() {
            return this.playing;
        }

        @Override // com.zxcpoiu.incallmanager.InCallManagerModule.MyPlayerInterface
        public void stopPlay() {
            synchronized (this) {
                if (this.playing) {
                    notify();
                }
                this.playing = false;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ToneGenerator toneGenerator;
            int i = this.toneCategory;
            int i2 = maxWaitTimeMs;
            switch (i) {
                case 101:
                    this.toneType = 28;
                    i2 = 1000;
                    break;
                case 102:
                    this.toneType = 20;
                    i2 = 4000;
                    break;
                case CALLEND /* 103 */:
                    this.toneType = 27;
                    i2 = ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;
                    break;
                case 104:
                    this.toneType = 22;
                    break;
                case RINGBACK /* 105 */:
                    this.toneType = 35;
                    break;
                case SILENT /* 106 */:
                    this.toneType = 42;
                    i2 = 1000;
                    break;
                default:
                    Log.d("InCallManager", "myToneGenerator: use internal tone type: " + this.toneCategory);
                    this.toneType = this.toneCategory;
                    i2 = this.customWaitTimeMs;
                    break;
            }
            Log.d("InCallManager", String.format("myToneGenerator: toneCategory: %d ,toneType: %d, toneWaitTimeMs: %d", Integer.valueOf(this.toneCategory), Integer.valueOf(this.toneType), Integer.valueOf(i2)));
            try {
                toneGenerator = new ToneGenerator(0, 100);
            } catch (RuntimeException e) {
                Log.d("InCallManager", "myToneGenerator: Exception caught while creating ToneGenerator: " + e);
                toneGenerator = null;
            }
            if (toneGenerator != null) {
                synchronized (this) {
                    if (!this.playing) {
                        this.playing = true;
                        if (this.caller.equals("mBusytone") || this.caller.equals("mRingback")) {
                            InCallManagerModule.this.audioManager.setMode(3);
                        } else if (this.caller.equals("mRingtone")) {
                            InCallManagerModule.this.audioManager.setMode(1);
                        }
                        InCallManagerModule.this.updateAudioRoute();
                        toneGenerator.startTone(this.toneType);
                        try {
                            wait(i2 + 20);
                        } catch (InterruptedException unused) {
                            Log.d("InCallManager", "myToneGenerator stopped. toneType: " + this.toneType);
                        }
                        toneGenerator.stopTone();
                    }
                    this.playing = false;
                    toneGenerator.release();
                }
            }
            Log.d("InCallManager", "MyToneGenerator(): play finished. caller=" + this.caller);
            if (this.caller.equals("mBusytone")) {
                Log.d("InCallManager", "MyToneGenerator(): invoke stop()");
                InCallManagerModule.this.stop();
            }
        }
    }

    private class myMediaPlayer extends MediaPlayer implements MyPlayerInterface {
        private myMediaPlayer() {
        }

        @Override // com.zxcpoiu.incallmanager.InCallManagerModule.MyPlayerInterface
        public void stopPlay() {
            stop();
            reset();
            release();
        }

        @Override // com.zxcpoiu.incallmanager.InCallManagerModule.MyPlayerInterface
        public void startPlay(Map map) {
            try {
                setDataSource(InCallManagerModule.this.getReactApplicationContext(), (Uri) map.get("sourceUri"));
                setLooping(((Boolean) map.get("setLooping")).booleanValue());
                setAudioAttributes(new AudioAttributes.Builder().setUsage(((Integer) map.get("audioUsage")).intValue()).setContentType(((Integer) map.get("audioContentType")).intValue()).build());
                prepareAsync();
            } catch (Exception e) {
                Log.d("InCallManager", "startPlay() failed", e);
            }
        }

        @Override // android.media.MediaPlayer, com.zxcpoiu.incallmanager.InCallManagerModule.MyPlayerInterface
        public boolean isPlaying() {
            return super.isPlaying();
        }
    }

    @ReactMethod
    public void chooseAudioRoute(String str, Promise promise) {
        Log.d("InCallManager", "RNInCallManager.chooseAudioRoute(): user choose audioDevice = " + str);
        if (str.equals(AudioDevice.EARPIECE.name())) {
            selectAudioDevice(AudioDevice.EARPIECE);
        } else if (str.equals(AudioDevice.SPEAKER_PHONE.name())) {
            selectAudioDevice(AudioDevice.SPEAKER_PHONE);
        } else if (str.equals(AudioDevice.WIRED_HEADSET.name())) {
            selectAudioDevice(AudioDevice.WIRED_HEADSET);
        } else if (str.equals(AudioDevice.BLUETOOTH.name())) {
            selectAudioDevice(AudioDevice.BLUETOOTH);
        }
        promise.resolve(getAudioDeviceStatusMap());
    }

    private static int getRandomInteger(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    private void pause() {
        if (this.audioManagerActivated) {
            Log.d("InCallManager", "pause audioRouteManager");
            stopEvents();
        }
    }

    private void resume() {
        if (this.audioManagerActivated) {
            Log.d("InCallManager", "resume audioRouteManager");
            startEvents();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        Log.d("InCallManager", "onResume()");
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        Log.d("InCallManager", "onPause()");
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        Log.d("InCallManager", "onDestroy()");
        stopRingtone();
        stopRingback();
        stopBusytone();
        stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAudioRoute() {
        if (this.automatic) {
            updateAudioDeviceState();
        }
    }

    private void setAudioDeviceInternal(AudioDevice audioDevice) {
        Log.d("InCallManager", "setAudioDeviceInternal(device=" + audioDevice + ")");
        if (!this.audioDevices.contains(audioDevice)) {
            Log.e("InCallManager", "specified audio device does not exist");
            return;
        }
        int i = AnonymousClass13.$SwitchMap$com$zxcpoiu$incallmanager$InCallManagerModule$AudioDevice[audioDevice.ordinal()];
        if (i == 1) {
            setSpeakerphoneOn(true);
        } else if (i == 2 || i == 3 || i == 4) {
            setSpeakerphoneOn(false);
        } else {
            Log.e("InCallManager", "Invalid audio device selection");
        }
        this.selectedAudioDevice = audioDevice;
    }

    /* JADX INFO: renamed from: com.zxcpoiu.incallmanager.InCallManagerModule$13, reason: invalid class name */
    static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$zxcpoiu$incallmanager$InCallManagerModule$AudioDevice;

        static {
            int[] iArr = new int[AudioDevice.values().length];
            $SwitchMap$com$zxcpoiu$incallmanager$InCallManagerModule$AudioDevice = iArr;
            try {
                iArr[AudioDevice.SPEAKER_PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$zxcpoiu$incallmanager$InCallManagerModule$AudioDevice[AudioDevice.EARPIECE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$zxcpoiu$incallmanager$InCallManagerModule$AudioDevice[AudioDevice.WIRED_HEADSET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$zxcpoiu$incallmanager$InCallManagerModule$AudioDevice[AudioDevice.BLUETOOTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void setDefaultAudioDevice(AudioDevice audioDevice) {
        int i = AnonymousClass13.$SwitchMap$com$zxcpoiu$incallmanager$InCallManagerModule$AudioDevice[audioDevice.ordinal()];
        if (i == 1) {
            this.defaultAudioDevice = audioDevice;
        } else if (i == 2) {
            if (hasEarpiece()) {
                this.defaultAudioDevice = audioDevice;
            } else {
                this.defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
            }
        } else {
            Log.e("InCallManager", "Invalid default audio device selection");
        }
        Log.d("InCallManager", "setDefaultAudioDevice(device=" + this.defaultAudioDevice + ")");
        updateAudioDeviceState();
    }

    public void selectAudioDevice(AudioDevice audioDevice) {
        if (audioDevice != AudioDevice.NONE && !this.audioDevices.contains(audioDevice)) {
            Log.e("InCallManager", "selectAudioDevice() Can not select " + audioDevice + " from available " + this.audioDevices);
        } else {
            this.userSelectedAudioDevice = audioDevice;
            updateAudioDeviceState();
        }
    }

    public Set<AudioDevice> getAudioDevices() {
        return Collections.unmodifiableSet(new HashSet(this.audioDevices));
    }

    public AudioDevice getSelectedAudioDevice() {
        return this.selectedAudioDevice;
    }

    private void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                reactApplicationContext.registerReceiver(broadcastReceiver, intentFilter, 4);
                return;
            } else {
                reactApplicationContext.registerReceiver(broadcastReceiver, intentFilter);
                return;
            }
        }
        Log.d("InCallManager", "registerReceiver() reactContext is null");
    }

    private void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext == null) {
            Log.d("InCallManager", "unregisterReceiver() reactContext is null");
            return;
        }
        try {
            reactApplicationContext.unregisterReceiver(broadcastReceiver);
        } catch (Exception unused) {
            Log.d("InCallManager", "unregisterReceiver() failed");
        }
    }

    private boolean hasEarpiece() {
        return getReactApplicationContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    @Deprecated
    private boolean hasWiredHeadset() {
        for (AudioDeviceInfo audioDeviceInfo : this.audioManager.getDevices(3)) {
            int type = audioDeviceInfo.getType();
            if (type == 3) {
                Log.d("InCallManager", "hasWiredHeadset: found wired headset");
                return true;
            }
            if (type == 11) {
                Log.d("InCallManager", "hasWiredHeadset: found USB audio device");
                return true;
            }
            if (type == 4) {
                Log.d("InCallManager", "hasWiredHeadset: found wired headphones");
                return true;
            }
        }
        return false;
    }

    @ReactMethod
    public void getIsWiredHeadsetPluggedIn(Promise promise) {
        promise.resolve(Boolean.valueOf(hasWiredHeadset()));
    }

    public void updateAudioDeviceState() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallManagerModule$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateAudioDeviceState$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAudioDeviceState$3() {
        Log.d("InCallManager", "--- updateAudioDeviceState: wired headset=" + this.hasWiredHeadset + ", BT state=" + this.bluetoothManager.getState());
        Log.d("InCallManager", "Device status: available=" + this.audioDevices + ", selected=" + this.selectedAudioDevice + ", user selected=" + this.userSelectedAudioDevice);
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_AVAILABLE || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_UNAVAILABLE || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_DISCONNECTING) {
            this.bluetoothManager.updateDevice();
        }
        HashSet hashSet = new HashSet();
        hashSet.add(AudioDevice.SPEAKER_PHONE);
        if (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTED || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTING || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_AVAILABLE) {
            hashSet.add(AudioDevice.BLUETOOTH);
        }
        if (this.hasWiredHeadset) {
            hashSet.add(AudioDevice.WIRED_HEADSET);
        }
        if (hasEarpiece()) {
            hashSet.add(AudioDevice.EARPIECE);
        }
        AudioDevice audioDevice = this.userSelectedAudioDevice;
        if (audioDevice != null && audioDevice != AudioDevice.NONE && !hashSet.contains(this.userSelectedAudioDevice)) {
            this.userSelectedAudioDevice = AudioDevice.NONE;
        }
        boolean z = !this.audioDevices.equals(hashSet);
        this.audioDevices = hashSet;
        AudioDevice preferredAudioDevice = getPreferredAudioDevice();
        if (this.selectedAudioDevice == AudioDevice.BLUETOOTH && preferredAudioDevice != AudioDevice.BLUETOOTH && (this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTED || this.bluetoothManager.getState() == AppRTCBluetoothManager.State.SCO_CONNECTING)) {
            this.bluetoothManager.stopScoAudio();
            this.bluetoothManager.updateDevice();
        }
        if (this.selectedAudioDevice != AudioDevice.BLUETOOTH && preferredAudioDevice == AudioDevice.BLUETOOTH && this.bluetoothManager.getState() == AppRTCBluetoothManager.State.HEADSET_AVAILABLE && !this.bluetoothManager.startScoAudio()) {
            this.audioDevices.remove(AudioDevice.BLUETOOTH);
            if (this.userSelectedAudioDevice == AudioDevice.BLUETOOTH) {
                this.userSelectedAudioDevice = AudioDevice.NONE;
            }
            preferredAudioDevice = getPreferredAudioDevice();
            z = true;
        }
        if (preferredAudioDevice == AudioDevice.BLUETOOTH && this.bluetoothManager.getState() != AppRTCBluetoothManager.State.SCO_CONNECTED) {
            preferredAudioDevice = getPreferredAudioDevice(true);
        }
        if (preferredAudioDevice != this.selectedAudioDevice || z) {
            setAudioDeviceInternal(preferredAudioDevice);
            Log.d("InCallManager", "New device status: available=" + this.audioDevices + ", selected=" + preferredAudioDevice);
            sendEvent("onAudioDeviceChanged", getAudioDeviceStatusMap());
        }
        Log.d("InCallManager", "--- updateAudioDeviceState done");
    }

    private WritableMap getAudioDeviceStatusMap() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Iterator<AudioDevice> it = this.audioDevices.iterator();
        String strSubstring = "[";
        while (it.hasNext()) {
            strSubstring = strSubstring + "\"" + it.next().name() + "\",";
        }
        if (strSubstring.length() > 1) {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        writableMapCreateMap.putString("availableAudioDeviceList", strSubstring + "]");
        AudioDevice audioDevice = this.selectedAudioDevice;
        writableMapCreateMap.putString("selectedAudioDevice", audioDevice == null ? "" : audioDevice.name());
        return writableMapCreateMap;
    }

    private AudioDevice getPreferredAudioDevice() {
        return getPreferredAudioDevice(false);
    }

    private AudioDevice getPreferredAudioDevice(boolean z) {
        AudioDevice audioDevice = this.userSelectedAudioDevice;
        if (audioDevice != null && audioDevice != AudioDevice.NONE) {
            return this.userSelectedAudioDevice;
        }
        if (!z && this.audioDevices.contains(AudioDevice.BLUETOOTH)) {
            return AudioDevice.BLUETOOTH;
        }
        if (this.audioDevices.contains(AudioDevice.WIRED_HEADSET)) {
            return AudioDevice.WIRED_HEADSET;
        }
        if (this.audioDevices.contains(this.defaultAudioDevice)) {
            return this.defaultAudioDevice;
        }
        return AudioDevice.SPEAKER_PHONE;
    }
}
