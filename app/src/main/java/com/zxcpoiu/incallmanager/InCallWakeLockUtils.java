package com.zxcpoiu.incallmanager;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class InCallWakeLockUtils {
    private static final String TAG = "InCallWakeLockUtils";
    private PowerManager.WakeLock mFullLock;
    private PowerManager.WakeLock mPartialLock;
    private PowerManager.WakeLock mPokeFullLock;
    private PowerManager mPowerManager;

    public InCallWakeLockUtils(Context context) {
        this.mFullLock = null;
        this.mPokeFullLock = null;
        this.mPartialLock = null;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mPowerManager = powerManager;
        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(805306394, TAG);
        this.mFullLock = wakeLockNewWakeLock;
        wakeLockNewWakeLock.setReferenceCounted(false);
        PowerManager.WakeLock wakeLockNewWakeLock2 = this.mPowerManager.newWakeLock(1, TAG);
        this.mPartialLock = wakeLockNewWakeLock2;
        wakeLockNewWakeLock2.setReferenceCounted(false);
        PowerManager.WakeLock wakeLockNewWakeLock3 = this.mPowerManager.newWakeLock(805306394, TAG);
        this.mPokeFullLock = wakeLockNewWakeLock3;
        wakeLockNewWakeLock3.setReferenceCounted(false);
    }

    private boolean _acquireWakeLock(PowerManager.WakeLock wakeLock) {
        return _acquireWakeLock(wakeLock, 0L);
    }

    private boolean _acquireWakeLock(PowerManager.WakeLock wakeLock, long j) {
        synchronized (wakeLock) {
            if (wakeLock.isHeld()) {
                return false;
            }
            if (j > 0) {
                wakeLock.acquire(j);
            } else {
                wakeLock.acquire();
            }
            return true;
        }
    }

    private boolean _releaseWakeLock(PowerManager.WakeLock wakeLock) {
        synchronized (wakeLock) {
            if (!wakeLock.isHeld()) {
                return false;
            }
            wakeLock.release();
            return true;
        }
    }

    public boolean acquireFullWakeLock() {
        boolean z_acquireWakeLock = _acquireWakeLock(this.mFullLock);
        Log.d(TAG, "acquireFullWakeLock(). sta=" + z_acquireWakeLock);
        return z_acquireWakeLock;
    }

    public boolean releaseFullWakeLock() {
        boolean z_releaseWakeLock = _releaseWakeLock(this.mFullLock);
        Log.d(TAG, "releaseFullWakeLock(). sta=" + z_releaseWakeLock);
        return z_releaseWakeLock;
    }

    public boolean acquirePokeFullWakeLock() {
        boolean z_acquireWakeLock = _acquireWakeLock(this.mPokeFullLock);
        Log.d(TAG, "acquirePokeFullWakeLock(). sta=" + z_acquireWakeLock);
        return z_acquireWakeLock;
    }

    public boolean releasePokeFullWakeLock() {
        boolean z_releaseWakeLock = _releaseWakeLock(this.mPokeFullLock);
        Log.d(TAG, "releasePokeFullWakeLock(). sta=" + z_releaseWakeLock);
        return z_releaseWakeLock;
    }

    public boolean acquirePartialWakeLock() {
        boolean z_acquireWakeLock = _acquireWakeLock(this.mPartialLock);
        Log.d(TAG, "acquirePartialWakeLock(). sta=" + z_acquireWakeLock);
        return z_acquireWakeLock;
    }

    public boolean releasePartialWakeLock() {
        boolean z_releaseWakeLock = _releaseWakeLock(this.mPartialLock);
        Log.d(TAG, "releasePartialWakeLock(). sta=" + z_releaseWakeLock);
        return z_releaseWakeLock;
    }

    public boolean acquirePokeFullWakeLockReleaseAfter(long j) {
        boolean z_acquireWakeLock = _acquireWakeLock(this.mPokeFullLock, j);
        Log.d(TAG, String.format("acquirePokeFullWakeLockReleaseAfter() timeout=%s, sta=%s", Long.valueOf(j), Boolean.valueOf(z_acquireWakeLock)));
        return z_acquireWakeLock;
    }
}
