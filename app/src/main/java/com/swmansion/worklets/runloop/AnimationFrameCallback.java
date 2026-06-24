package com.swmansion.worklets.runloop;

import com.facebook.jni.HybridData;

/* JADX INFO: loaded from: classes2.dex */
public class AnimationFrameCallback {
    private final HybridData mHybridData;

    public native void onAnimationFrame(double d);

    private AnimationFrameCallback(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
