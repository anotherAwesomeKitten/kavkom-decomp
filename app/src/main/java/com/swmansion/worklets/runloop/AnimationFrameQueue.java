package com.swmansion.worklets.runloop;

import android.os.SystemClock;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.GuardedFrameCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class AnimationFrameQueue {
    private double lastFrameTimeMs;
    private final GuardedFrameCallback mChoreographerCallback;
    private Long mFirstUptime = Long.valueOf(SystemClock.uptimeMillis());
    private boolean mSlowAnimationsEnabled = false;
    private int mAnimationsDragFactor = 1;
    private final ReactChoreographer mReactChoreographer = ReactChoreographer.getInstance();
    private final AtomicBoolean mCallbackPosted = new AtomicBoolean();
    private final AtomicBoolean mPaused = new AtomicBoolean();
    private final List<AnimationFrameCallback> mFrameCallbacks = new ArrayList();

    public AnimationFrameQueue(ReactApplicationContext reactApplicationContext) {
        this.mChoreographerCallback = new GuardedFrameCallback(reactApplicationContext) { // from class: com.swmansion.worklets.runloop.AnimationFrameQueue.1
            @Override // com.facebook.react.uimanager.GuardedFrameCallback
            protected void doFrameGuarded(long j) {
                AnimationFrameQueue.this.executeQueue(j);
            }
        };
    }

    public void resume() {
        if (this.mPaused.getAndSet(false)) {
            scheduleQueueExecution();
        }
    }

    public void pause() {
        synchronized (this.mPaused) {
            if (!this.mPaused.getAndSet(true) && this.mCallbackPosted.getAndSet(false)) {
                this.mReactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mChoreographerCallback);
            }
        }
    }

    public void requestAnimationFrame(AnimationFrameCallback animationFrameCallback) {
        synchronized (this.mFrameCallbacks) {
            this.mFrameCallbacks.add(animationFrameCallback);
        }
        scheduleQueueExecution();
    }

    public void enableSlowAnimations(boolean z, int i) {
        this.mSlowAnimationsEnabled = z;
        this.mAnimationsDragFactor = i;
        if (z) {
            this.mFirstUptime = Long.valueOf(SystemClock.uptimeMillis());
        }
    }

    private void scheduleQueueExecution() {
        synchronized (this.mPaused) {
            if (!this.mPaused.get() && !this.mCallbackPosted.getAndSet(true)) {
                this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mChoreographerCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeQueue(long j) {
        double dCalculateTimestamp = calculateTimestamp(j);
        if (dCalculateTimestamp <= this.lastFrameTimeMs) {
            this.mCallbackPosted.set(false);
            scheduleQueueExecution();
            return;
        }
        List<AnimationFrameCallback> listPullCallbacks = pullCallbacks();
        this.mCallbackPosted.set(false);
        this.lastFrameTimeMs = dCalculateTimestamp;
        Iterator<AnimationFrameCallback> it = listPullCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onAnimationFrame(dCalculateTimestamp);
        }
    }

    private List<AnimationFrameCallback> pullCallbacks() {
        ArrayList arrayList;
        synchronized (this.mFrameCallbacks) {
            arrayList = new ArrayList(this.mFrameCallbacks);
            this.mFrameCallbacks.clear();
        }
        return arrayList;
    }

    private double calculateTimestamp(long j) {
        double d = j / 1000000.0d;
        return this.mSlowAnimationsEnabled ? this.mFirstUptime.longValue() + ((d - this.mFirstUptime.longValue()) / ((double) this.mAnimationsDragFactor)) : d;
    }
}
