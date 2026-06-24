package io.sentry.react;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class NativeRNSentrySpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "RNSentry";

    @ReactMethod
    public abstract void addBreadcrumb(ReadableMap readableMap);

    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod
    public abstract void captureEnvelope(String str, ReadableMap readableMap, Promise promise);

    @ReactMethod
    public abstract void captureReplay(boolean z, Promise promise);

    @ReactMethod
    public abstract void captureScreenshot(Promise promise);

    @ReactMethod
    public abstract void clearBreadcrumbs();

    @ReactMethod
    public abstract void closeNativeSdk(Promise promise);

    @ReactMethod
    public abstract void crash();

    @ReactMethod
    public abstract void crashedLastRun(Promise promise);

    @ReactMethod
    public abstract void disableNativeFramesTracking();

    @ReactMethod
    public abstract void enableNativeFramesTracking();

    @ReactMethod
    public abstract void fetchModules(Promise promise);

    @ReactMethod
    public abstract void fetchNativeAppStart(Promise promise);

    @ReactMethod
    public abstract void fetchNativeDeviceContexts(Promise promise);

    @ReactMethod
    public abstract void fetchNativeFrames(Promise promise);

    @ReactMethod(isBlockingSynchronousMethod = true)
    @Nullable
    public abstract String fetchNativePackageName();

    @ReactMethod
    public abstract void fetchNativeRelease(Promise promise);

    @ReactMethod
    public abstract void fetchNativeSdkInfo(Promise promise);

    @ReactMethod(isBlockingSynchronousMethod = true)
    @Nullable
    public abstract WritableMap fetchNativeStackFramesBy(ReadableArray readableArray);

    @ReactMethod
    public abstract void fetchViewHierarchy(Promise promise);

    @ReactMethod(isBlockingSynchronousMethod = true)
    @Nullable
    public abstract String getCurrentReplayId();

    @ReactMethod
    public abstract void getNewScreenTimeToDisplay(Promise promise);

    @ReactMethod
    public abstract void initNativeReactNavigationNewFrameTracking(Promise promise);

    @ReactMethod
    public abstract void initNativeSdk(ReadableMap readableMap, Promise promise);

    @ReactMethod
    public abstract void removeListeners(double d);

    @ReactMethod
    public abstract void setContext(String str, @Nullable ReadableMap readableMap);

    @ReactMethod
    public abstract void setExtra(String str, String str2);

    @ReactMethod
    public abstract void setTag(String str, String str2);

    @ReactMethod
    public abstract void setUser(@Nullable ReadableMap readableMap, @Nullable ReadableMap readableMap2);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap startProfiling(boolean z);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap stopProfiling();

    public NativeRNSentrySpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNSentry";
    }
}
