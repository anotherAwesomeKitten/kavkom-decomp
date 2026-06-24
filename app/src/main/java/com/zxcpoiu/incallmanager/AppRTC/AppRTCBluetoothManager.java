package com.zxcpoiu.incallmanager.AppRTC;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.facebook.react.uimanager.ViewProps;
import com.zxcpoiu.incallmanager.InCallManagerModule;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.webrtc.MediaStreamTrack;

/* JADX INFO: loaded from: classes2.dex */
public class AppRTCBluetoothManager {
    private static final int BLUETOOTH_SCO_TIMEOUT_MS = 6000;
    private static final int MAX_SCO_CONNECTION_ATTEMPTS = 10;
    private static final String TAG = "AppRTCBluetoothManager";
    private final InCallManagerModule apprtcAudioManager;
    private final Context apprtcContext;
    private final AudioManager audioManager;
    private BluetoothAdapter bluetoothAdapter;
    private AudioDeviceInfo bluetoothAudioDevice;
    private AudioDeviceCallback bluetoothAudioDeviceCallback;
    private BluetoothDevice bluetoothDevice;
    private BluetoothHeadset bluetoothHeadset;
    private final BroadcastReceiver bluetoothHeadsetReceiver;
    private final BluetoothProfile.ServiceListener bluetoothServiceListener;
    private State bluetoothState;
    private final Runnable bluetoothTimeoutRunnable = new Runnable() { // from class: com.zxcpoiu.incallmanager.AppRTC.AppRTCBluetoothManager.1
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppRTCBluetoothManager.this.bluetoothTimeout();
        }
    };
    private final Handler handler;
    int scoConnectionAttempts;

    public enum State {
        UNINITIALIZED,
        ERROR,
        HEADSET_UNAVAILABLE,
        HEADSET_AVAILABLE,
        SCO_DISCONNECTING,
        SCO_CONNECTING,
        SCO_CONNECTED
    }

    /* JADX INFO: renamed from: com.zxcpoiu.incallmanager.AppRTC.AppRTCBluetoothManager$1 */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppRTCBluetoothManager.this.bluetoothTimeout();
        }
    }

    private class BluetoothServiceListener implements BluetoothProfile.ServiceListener {
        /* synthetic */ BluetoothServiceListener(AppRTCBluetoothManager appRTCBluetoothManager, AppRTCBluetoothManagerIA appRTCBluetoothManagerIA) {
            this();
        }

        private BluetoothServiceListener() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i != 1 || AppRTCBluetoothManager.this.bluetoothState == State.UNINITIALIZED) {
                return;
            }
            Log.d(AppRTCBluetoothManager.TAG, "BluetoothServiceListener.onServiceConnected: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
            AppRTCBluetoothManager.this.bluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
            AppRTCBluetoothManager.this.updateAudioDeviceState();
            Log.d(AppRTCBluetoothManager.TAG, "onServiceConnected done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i != 1 || AppRTCBluetoothManager.this.bluetoothState == State.UNINITIALIZED) {
                return;
            }
            Log.d(AppRTCBluetoothManager.TAG, "BluetoothServiceListener.onServiceDisconnected: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
            AppRTCBluetoothManager.this.stopScoAudio();
            AppRTCBluetoothManager.this.bluetoothHeadset = null;
            AppRTCBluetoothManager.this.bluetoothDevice = null;
            AppRTCBluetoothManager.this.bluetoothState = State.HEADSET_UNAVAILABLE;
            AppRTCBluetoothManager.this.updateAudioDeviceState();
            Log.d(AppRTCBluetoothManager.TAG, "onServiceDisconnected done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
        }
    }

    private class BluetoothAudioDeviceCallback extends AudioDeviceCallback {
        /* synthetic */ BluetoothAudioDeviceCallback(AppRTCBluetoothManager appRTCBluetoothManager, AppRTCBluetoothManagerIA appRTCBluetoothManagerIA) {
            this();
        }

        private BluetoothAudioDeviceCallback() {
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
            updateDeviceList();
        }

        @Override // android.media.AudioDeviceCallback
        public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
            updateDeviceList();
        }

        private void updateDeviceList() {
            AudioDeviceInfo scoDevice = AppRTCBluetoothManager.this.getScoDevice();
            if (AppRTCBluetoothManager.this.bluetoothAudioDevice != null && scoDevice == null) {
                AppRTCBluetoothManager.this.bluetoothState = State.HEADSET_UNAVAILABLE;
            } else if ((AppRTCBluetoothManager.this.bluetoothAudioDevice != null || scoDevice == null) && (AppRTCBluetoothManager.this.bluetoothAudioDevice == null || AppRTCBluetoothManager.this.bluetoothAudioDevice.getId() == scoDevice.getId())) {
                return;
            }
            AppRTCBluetoothManager.this.updateAudioDeviceState();
        }
    }

    private class BluetoothHeadsetBroadcastReceiver extends BroadcastReceiver {
        /* synthetic */ BluetoothHeadsetBroadcastReceiver(AppRTCBluetoothManager appRTCBluetoothManager, AppRTCBluetoothManagerIA appRTCBluetoothManagerIA) {
            this();
        }

        private BluetoothHeadsetBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (AppRTCBluetoothManager.this.bluetoothState == State.UNINITIALIZED) {
                return;
            }
            String action = intent.getAction();
            if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                Log.d(AppRTCBluetoothManager.TAG, "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_CONNECTION_STATE_CHANGED, s=" + AppRTCBluetoothManager.this.stateToString(intExtra) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + AppRTCBluetoothManager.this.bluetoothState);
                if (intExtra == 2) {
                    AppRTCBluetoothManager.this.scoConnectionAttempts = 0;
                    AppRTCBluetoothManager.this.updateAudioDeviceState();
                } else if (intExtra != 1 && intExtra != 3 && intExtra == 0) {
                    AppRTCBluetoothManager.this.stopScoAudio();
                    AppRTCBluetoothManager.this.updateAudioDeviceState();
                }
            } else if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
                Log.d(AppRTCBluetoothManager.TAG, "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_AUDIO_STATE_CHANGED, s=" + AppRTCBluetoothManager.this.stateToString(intExtra2) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + AppRTCBluetoothManager.this.bluetoothState);
                if (intExtra2 == 12) {
                    AppRTCBluetoothManager.this.cancelTimer();
                    if (AppRTCBluetoothManager.this.bluetoothState == State.SCO_CONNECTING) {
                        Log.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now connected");
                        AppRTCBluetoothManager.this.bluetoothState = State.SCO_CONNECTED;
                        AppRTCBluetoothManager.this.scoConnectionAttempts = 0;
                        AppRTCBluetoothManager.this.updateAudioDeviceState();
                    } else {
                        Log.w(AppRTCBluetoothManager.TAG, "Unexpected state BluetoothHeadset.STATE_AUDIO_CONNECTED");
                    }
                } else if (intExtra2 == 11) {
                    Log.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now connecting...");
                } else if (intExtra2 == 10) {
                    Log.d(AppRTCBluetoothManager.TAG, "+++ Bluetooth audio SCO is now disconnected");
                    if (isInitialStickyBroadcast()) {
                        Log.d(AppRTCBluetoothManager.TAG, "Ignore STATE_AUDIO_DISCONNECTED initial sticky broadcast.");
                        return;
                    }
                    AppRTCBluetoothManager.this.updateAudioDeviceState();
                }
            }
            Log.d(AppRTCBluetoothManager.TAG, "onReceive done: BT state=" + AppRTCBluetoothManager.this.bluetoothState);
        }
    }

    public static AppRTCBluetoothManager create(Context context, InCallManagerModule inCallManagerModule) {
        Log.d(TAG, "create" + AppRTCUtils.getThreadInfo());
        return new AppRTCBluetoothManager(context, inCallManagerModule);
    }

    protected AppRTCBluetoothManager(Context context, InCallManagerModule inCallManagerModule) {
        Log.d(TAG, "ctor");
        ThreadUtils.checkIsOnMainThread();
        this.apprtcContext = context;
        this.apprtcAudioManager = inCallManagerModule;
        this.audioManager = getAudioManager(context);
        this.bluetoothState = State.UNINITIALIZED;
        this.bluetoothServiceListener = new BluetoothServiceListener();
        this.bluetoothHeadsetReceiver = new BluetoothHeadsetBroadcastReceiver();
        if (Build.VERSION.SDK_INT >= 31) {
            this.bluetoothAudioDeviceCallback = new BluetoothAudioDeviceCallback();
        }
        this.handler = new Handler(Looper.getMainLooper());
    }

    public State getState() {
        ThreadUtils.checkIsOnMainThread();
        return this.bluetoothState;
    }

    public void start() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, ViewProps.START);
        if (!hasPermission(this.apprtcContext, Build.VERSION.SDK_INT >= 31 ? "android.permission.BLUETOOTH_CONNECT" : "android.permission.BLUETOOTH")) {
            Log.w(TAG, "Process (pid=" + Process.myPid() + ") lacks BLUETOOTH permission");
            return;
        }
        if (this.bluetoothState != State.UNINITIALIZED) {
            Log.w(TAG, "Invalid BT state");
            return;
        }
        this.bluetoothHeadset = null;
        this.bluetoothDevice = null;
        this.scoConnectionAttempts = 0;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.bluetoothAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            Log.w(TAG, "Device does not support Bluetooth");
            return;
        }
        if (!this.audioManager.isBluetoothScoAvailableOffCall()) {
            Log.e(TAG, "Bluetooth SCO audio is not available off call");
            return;
        }
        logBluetoothAdapterInfo(this.bluetoothAdapter);
        if (!getBluetoothProfileProxy(this.apprtcContext, this.bluetoothServiceListener, 1)) {
            Log.e(TAG, "BluetoothAdapter.getProfileProxy(HEADSET) failed");
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            this.audioManager.registerAudioDeviceCallback(this.bluetoothAudioDeviceCallback, null);
        } else {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
            registerReceiver(this.bluetoothHeadsetReceiver, intentFilter);
            Log.d(TAG, "HEADSET profile state: " + stateToString(this.bluetoothAdapter.getProfileConnectionState(1)));
        }
        Log.d(TAG, "Bluetooth proxy for headset profile has started");
        this.bluetoothState = State.HEADSET_UNAVAILABLE;
        Log.d(TAG, "start done: BT state=" + this.bluetoothState);
    }

    public void stop() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "stop: BT state=" + this.bluetoothState);
        if (this.bluetoothAdapter == null) {
            return;
        }
        stopScoAudio();
        if (this.bluetoothState == State.UNINITIALIZED) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            this.audioManager.unregisterAudioDeviceCallback(this.bluetoothAudioDeviceCallback);
        } else {
            unregisterReceiver(this.bluetoothHeadsetReceiver);
            cancelTimer();
        }
        BluetoothHeadset bluetoothHeadset = this.bluetoothHeadset;
        if (bluetoothHeadset != null) {
            this.bluetoothAdapter.closeProfileProxy(1, bluetoothHeadset);
            this.bluetoothHeadset = null;
        }
        this.bluetoothAdapter = null;
        this.bluetoothDevice = null;
        this.bluetoothState = State.UNINITIALIZED;
        Log.d(TAG, "stop done: BT state=" + this.bluetoothState);
    }

    public boolean startScoAudio() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "startSco: BT state=" + this.bluetoothState + ", attempts: " + this.scoConnectionAttempts + ", SCO is on: " + isScoOn());
        if (this.scoConnectionAttempts >= 10) {
            Log.e(TAG, "BT SCO connection fails - no more attempts");
            return false;
        }
        if (this.bluetoothState != State.HEADSET_AVAILABLE) {
            Log.e(TAG, "BT SCO connection fails - no headset available");
            return false;
        }
        if (Build.VERSION.SDK_INT < 31) {
            Log.d(TAG, "Starting Bluetooth SCO and waits for ACTION_AUDIO_STATE_CHANGED...");
            this.bluetoothState = State.SCO_CONNECTING;
            startTimer();
            this.audioManager.startBluetoothSco();
            this.audioManager.setBluetoothScoOn(true);
            this.scoConnectionAttempts++;
            Log.d(TAG, "startScoAudio done: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
        } else {
            AudioDeviceInfo audioDeviceInfo = this.bluetoothAudioDevice;
            if (audioDeviceInfo != null) {
                this.audioManager.setCommunicationDevice(audioDeviceInfo);
                this.bluetoothState = State.SCO_CONNECTED;
                Log.d(TAG, "Set bluetooth audio device as communication device: id=" + this.bluetoothAudioDevice.getId());
            } else {
                this.bluetoothState = State.SCO_DISCONNECTING;
                Log.d(TAG, "Cannot find any bluetooth SCO device to set as communication device");
            }
            updateAudioDeviceState();
        }
        return true;
    }

    private List<BluetoothDevice> getFinalConnectedDevices() {
        List<BluetoothDevice> connectedDevices = this.bluetoothHeadset.getConnectedDevices();
        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice bluetoothDevice : connectedDevices) {
            if (bluetoothDevice.getBluetoothClass().getMajorDeviceClass() == 1024) {
                arrayList.add(bluetoothDevice);
            }
        }
        return arrayList;
    }

    public void stopScoAudio() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "stopScoAudio: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
        if (this.bluetoothState == State.SCO_CONNECTING || this.bluetoothState == State.SCO_CONNECTED) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.audioManager.clearCommunicationDevice();
            } else {
                cancelTimer();
                this.audioManager.stopBluetoothSco();
                this.audioManager.setBluetoothScoOn(false);
            }
            this.bluetoothState = State.SCO_DISCONNECTING;
            Log.d(TAG, "stopScoAudio done: BT state=" + this.bluetoothState + ", SCO is on: " + isScoOn());
        }
    }

    public void updateDevice() {
        if (this.bluetoothState == State.UNINITIALIZED || this.bluetoothHeadset == null) {
            return;
        }
        Log.d(TAG, "updateDevice");
        if (Build.VERSION.SDK_INT >= 31) {
            AudioDeviceInfo scoDevice = getScoDevice();
            this.bluetoothAudioDevice = scoDevice;
            if (scoDevice != null) {
                this.bluetoothState = State.HEADSET_AVAILABLE;
                Log.d(TAG, "Connected bluetooth headset: name=" + ((Object) this.bluetoothAudioDevice.getProductName()));
            } else {
                this.bluetoothState = State.HEADSET_UNAVAILABLE;
            }
        } else {
            List<BluetoothDevice> finalConnectedDevices = getFinalConnectedDevices();
            if (finalConnectedDevices.isEmpty()) {
                this.bluetoothDevice = null;
                this.bluetoothState = State.HEADSET_UNAVAILABLE;
                Log.d(TAG, "No connected bluetooth headset");
            } else {
                this.bluetoothDevice = finalConnectedDevices.get(0);
                this.bluetoothState = State.HEADSET_AVAILABLE;
                Log.d(TAG, "Connected bluetooth headset: name=" + this.bluetoothDevice.getName() + ", state=" + stateToString(this.bluetoothHeadset.getConnectionState(this.bluetoothDevice)) + ", SCO audio=" + this.bluetoothHeadset.isAudioConnected(this.bluetoothDevice));
            }
        }
        Log.d(TAG, "updateDevice done: BT state=" + this.bluetoothState);
    }

    protected AudioManager getAudioManager(Context context) {
        return (AudioManager) context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
    }

    protected void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (Build.VERSION.SDK_INT >= 33) {
            this.apprtcContext.registerReceiver(broadcastReceiver, intentFilter, 4);
        } else {
            this.apprtcContext.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    protected void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                this.apprtcContext.unregisterReceiver(broadcastReceiver);
            } catch (Exception unused) {
            }
        }
    }

    protected boolean getBluetoothProfileProxy(Context context, BluetoothProfile.ServiceListener serviceListener, int i) {
        return this.bluetoothAdapter.getProfileProxy(context, serviceListener, i);
    }

    protected boolean hasPermission(Context context, String str) {
        return this.apprtcContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    protected void logBluetoothAdapterInfo(BluetoothAdapter bluetoothAdapter) {
        Log.d(TAG, "BluetoothAdapter: enabled=" + bluetoothAdapter.isEnabled() + ", state=" + stateToString(bluetoothAdapter.getState()) + ", name=" + bluetoothAdapter.getName() + ", address=" + bluetoothAdapter.getAddress());
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices.isEmpty()) {
            return;
        }
        Log.d(TAG, "paired devices:");
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            Log.d(TAG, " name=" + bluetoothDevice.getName() + ", address=" + bluetoothDevice.getAddress() + ", deviceClass=" + String.valueOf(bluetoothDevice.getBluetoothClass().getDeviceClass()) + ", deviceMajorClass=" + String.valueOf(bluetoothDevice.getBluetoothClass().getMajorDeviceClass()));
        }
    }

    public void updateAudioDeviceState() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "updateAudioDeviceState");
        this.apprtcAudioManager.updateAudioDeviceState();
    }

    private void startTimer() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "startTimer");
        this.handler.postDelayed(this.bluetoothTimeoutRunnable, 6000L);
    }

    public void cancelTimer() {
        ThreadUtils.checkIsOnMainThread();
        Log.d(TAG, "cancelTimer");
        this.handler.removeCallbacks(this.bluetoothTimeoutRunnable);
    }

    public void bluetoothTimeout() {
        ThreadUtils.checkIsOnMainThread();
        if (this.bluetoothState == State.UNINITIALIZED || this.bluetoothHeadset == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            Log.w(TAG, "Invalid state, the timeout should not be running on the version: " + Build.VERSION.SDK_INT);
        } else {
            Log.d(TAG, "bluetoothTimeout: BT state=" + this.bluetoothState + ", attempts: " + this.scoConnectionAttempts + ", SCO is on: " + isScoOn());
            if (this.bluetoothState != State.SCO_CONNECTING) {
                return;
            }
            List<BluetoothDevice> finalConnectedDevices = getFinalConnectedDevices();
            if (finalConnectedDevices.size() > 0) {
                BluetoothDevice bluetoothDevice = finalConnectedDevices.get(0);
                this.bluetoothDevice = bluetoothDevice;
                if (this.bluetoothHeadset.isAudioConnected(bluetoothDevice)) {
                    Log.d(TAG, "SCO connected with " + this.bluetoothDevice.getName());
                    this.bluetoothState = State.SCO_CONNECTED;
                    this.scoConnectionAttempts = 0;
                } else {
                    Log.d(TAG, "SCO is not connected with " + this.bluetoothDevice.getName());
                    Log.w(TAG, "BT failed to connect after timeout");
                    stopScoAudio();
                }
            } else {
                Log.w(TAG, "BT failed to connect after timeout");
                stopScoAudio();
            }
        }
        updateAudioDeviceState();
        Log.d(TAG, "bluetoothTimeout done: BT state=" + this.bluetoothState);
    }

    private boolean isScoOn() {
        if (Build.VERSION.SDK_INT >= 31) {
            AudioDeviceInfo communicationDevice = this.audioManager.getCommunicationDevice();
            return (communicationDevice == null || this.bluetoothAudioDevice == null || communicationDevice.getId() != this.bluetoothAudioDevice.getId()) ? false : true;
        }
        return this.audioManager.isBluetoothScoOn();
    }

    public String stateToString(int i) {
        if (i == 0) {
            return "DISCONNECTED";
        }
        if (i == 1) {
            return "CONNECTING";
        }
        if (i == 2) {
            return "CONNECTED";
        }
        if (i == 3) {
            return "DISCONNECTING";
        }
        switch (i) {
            case 10:
                return "OFF";
            case 11:
                return "TURNING_ON";
            case 12:
                return "ON";
            case 13:
                return "TURNING_OFF";
            default:
                return "INVALID";
        }
    }

    public AudioDeviceInfo getScoDevice() {
        AudioManager audioManager = this.audioManager;
        if (audioManager == null) {
            return null;
        }
        for (AudioDeviceInfo audioDeviceInfo : audioManager.getAvailableCommunicationDevices()) {
            if (audioDeviceInfo.getType() == 26 || audioDeviceInfo.getType() == 7) {
                return audioDeviceInfo;
            }
        }
        return null;
    }
}
