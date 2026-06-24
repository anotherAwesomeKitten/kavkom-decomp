package com.swmansion.worklets;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.soloader.SoLoader;
import com.swmansion.worklets.runloop.AnimationFrameCallback;
import com.swmansion.worklets.runloop.AnimationFrameQueue;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
@ReactModule(name = NativeWorkletsModuleSpec.NAME)
public class WorkletsModule extends NativeWorkletsModuleSpec implements LifecycleEventListener {
    private final AndroidUIScheduler mAndroidUIScheduler;
    private final AnimationFrameQueue mAnimationFrameQueue;
    private HybridData mHybridData;
    private final AtomicBoolean mInvalidated;
    private final WorkletsMessageQueueThread mMessageQueueThread;
    private boolean mSlowAnimationsEnabled;

    private native HybridData initHybrid(long j, MessageQueueThread messageQueueThread, CallInvokerHolderImpl callInvokerHolderImpl, AndroidUIScheduler androidUIScheduler);

    private native void invalidateCpp();

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    static {
        SoLoader.loadLibrary("worklets");
    }

    protected HybridData getHybridData() {
        return this.mHybridData;
    }

    public WorkletsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mMessageQueueThread = new WorkletsMessageQueueThread();
        this.mInvalidated = new AtomicBoolean(false);
        reactApplicationContext.assertOnJSQueueThread();
        this.mAndroidUIScheduler = new AndroidUIScheduler(reactApplicationContext);
        this.mAnimationFrameQueue = new AnimationFrameQueue(reactApplicationContext);
    }

    @Override // com.swmansion.worklets.NativeWorkletsModuleSpec
    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean installTurboModule() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.assertOnJSQueueThread();
        this.mHybridData = initHybrid(((JavaScriptContextHolder) Objects.requireNonNull(reactApplicationContext.getJavaScriptContextHolder())).getContext(), this.mMessageQueueThread, JSCallInvokerResolver.getJSCallInvokerHolder(reactApplicationContext), this.mAndroidUIScheduler);
        return true;
    }

    public void requestAnimationFrame(AnimationFrameCallback animationFrameCallback) {
        this.mAnimationFrameQueue.requestAnimationFrame(animationFrameCallback);
    }

    public boolean isOnJSQueueThread() {
        return getReactApplicationContext().isOnJSQueueThread();
    }

    public void toggleSlowAnimations() {
        boolean z = !this.mSlowAnimationsEnabled;
        this.mSlowAnimationsEnabled = z;
        this.mAnimationFrameQueue.enableSlowAnimations(z, 10);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        if (this.mInvalidated.getAndSet(true)) {
            return;
        }
        HybridData hybridData = this.mHybridData;
        if (hybridData != null && hybridData.isValid()) {
            invalidateCpp();
        }
        this.mAndroidUIScheduler.deactivate();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mAnimationFrameQueue.resume();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.mAnimationFrameQueue.pause();
    }
}
