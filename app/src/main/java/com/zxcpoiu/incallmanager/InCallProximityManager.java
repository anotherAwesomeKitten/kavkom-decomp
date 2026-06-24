package com.zxcpoiu.incallmanager;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.util.Log;
import com.facebook.react.bridge.UiThreadUtil;
import com.zxcpoiu.incallmanager.AppRTC.AppRTCProximitySensor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class InCallProximityManager {
    private static final String TAG = "InCallProximityManager";
    private Method mPowerManagerRelease;
    private PowerManager.WakeLock mProximityLock = null;
    private boolean proximitySupported = false;
    private AppRTCProximitySensor proximitySensor = null;

    static InCallProximityManager create(Context context, InCallManagerModule inCallManagerModule) {
        return new InCallProximityManager(context, inCallManagerModule);
    }

    private InCallProximityManager(final Context context, final InCallManagerModule inCallManagerModule) {
        Log.d(TAG, TAG);
        checkProximitySupport(context);
        if (this.proximitySupported) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallProximityManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$1(context, inCallManagerModule);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$1(Context context, final InCallManagerModule inCallManagerModule) {
        this.proximitySensor = AppRTCProximitySensor.create(context, new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallProximityManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0(inCallManagerModule);
            }
        });
    }

    public /* synthetic */ void lambda$new$0(InCallManagerModule inCallManagerModule) {
        inCallManagerModule.onProximitySensorChangedState(this.proximitySensor.sensorReportsNearState());
    }

    private void checkProximitySupport(Context context) {
        if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) == null) {
            this.proximitySupported = false;
            return;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.proximitySupported = true;
        try {
            int iIntValue = ((Integer) PowerManager.class.getDeclaredField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").get(null)).intValue();
            if (((Boolean) powerManager.getClass().getDeclaredMethod("isWakeLockLevelSupported", Integer.TYPE).invoke(powerManager, Integer.valueOf(iIntValue))).booleanValue()) {
                PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(iIntValue, TAG);
                this.mProximityLock = wakeLockNewWakeLock;
                wakeLockNewWakeLock.setReferenceCounted(false);
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to get proximity screen locker. exception: ", e);
        }
        if (this.mProximityLock != null) {
            Log.d(TAG, "use native screen locker...");
            try {
                this.mPowerManagerRelease = this.mProximityLock.getClass().getDeclaredMethod("release", Integer.TYPE);
                return;
            } catch (Exception e2) {
                Log.d(TAG, "failed to get proximity screen locker: `release()`. exception: ", e2);
                return;
            }
        }
        Log.d(TAG, "fallback to old school screen locker...");
    }

    public boolean start() {
        if (!this.proximitySupported) {
            return false;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallProximityManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$start$2();
            }
        });
        return true;
    }

    public /* synthetic */ void lambda$start$2() {
        this.proximitySensor.start();
    }

    public void stop() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.zxcpoiu.incallmanager.InCallProximityManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$stop$3();
            }
        });
    }

    public /* synthetic */ void lambda$stop$3() {
        this.proximitySensor.stop();
    }

    public boolean isProximitySupported() {
        return this.proximitySupported;
    }

    public boolean isProximityWakeLockSupported() {
        return this.mProximityLock != null;
    }

    public boolean getProximityIsNear() {
        if (this.proximitySupported) {
            return this.proximitySensor.sensorReportsNearState();
        }
        return false;
    }

    public void acquireProximityWakeLock() {
        if (isProximityWakeLockSupported()) {
            synchronized (this.mProximityLock) {
                if (!this.mProximityLock.isHeld()) {
                    Log.d(TAG, "acquireProximityWakeLock()");
                    this.mProximityLock.acquire();
                }
            }
        }
    }

    public void releaseProximityWakeLock(boolean z) {
        if (isProximityWakeLockSupported()) {
            synchronized (this.mProximityLock) {
                if (this.mProximityLock.isHeld()) {
                    try {
                        this.mPowerManagerRelease.invoke(this.mProximityLock, Integer.valueOf(z ? 1 : 0));
                        Log.d(TAG, "releaseProximityWakeLock()");
                    } catch (Exception e) {
                        Log.e(TAG, "failed to release proximity lock. e: ", e);
                    }
                }
            }
        }
    }
}
