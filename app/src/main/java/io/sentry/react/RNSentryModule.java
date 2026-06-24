package io.sentry.react;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

/* JADX INFO: loaded from: classes3.dex */
public class RNSentryModule extends NativeRNSentrySpec {
    private final RNSentryModuleImpl impl;

    @Override // io.sentry.react.NativeRNSentrySpec
    public WritableMap fetchNativeStackFramesBy(ReadableArray readableArray) {
        return null;
    }

    RNSentryModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.impl = new RNSentryModuleImpl(reactApplicationContext);
    }

    @Override // io.sentry.react.NativeRNSentrySpec, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNSentry";
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void addListener(String str) {
        this.impl.addListener(str);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void removeListeners(double d) {
        this.impl.removeListeners(d);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void initNativeReactNavigationNewFrameTracking(Promise promise) {
        this.impl.initNativeReactNavigationNewFrameTracking(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void initNativeSdk(ReadableMap readableMap, Promise promise) {
        this.impl.initNativeSdk(readableMap, promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void crash() {
        this.impl.crash();
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void fetchModules(Promise promise) {
        this.impl.fetchModules(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void fetchNativeRelease(Promise promise) {
        this.impl.fetchNativeRelease(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void fetchNativeAppStart(Promise promise) {
        this.impl.fetchNativeAppStart(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void fetchNativeFrames(Promise promise) {
        this.impl.fetchNativeFrames(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void captureEnvelope(String str, ReadableMap readableMap, Promise promise) {
        this.impl.captureEnvelope(str, readableMap, promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void captureScreenshot(Promise promise) {
        this.impl.captureScreenshot(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void fetchViewHierarchy(Promise promise) {
        this.impl.fetchViewHierarchy(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void setUser(ReadableMap readableMap, ReadableMap readableMap2) {
        this.impl.setUser(readableMap, readableMap2);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void addBreadcrumb(ReadableMap readableMap) {
        this.impl.addBreadcrumb(readableMap);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void clearBreadcrumbs() {
        this.impl.clearBreadcrumbs();
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void setExtra(String str, String str2) {
        this.impl.setExtra(str, str2);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void setContext(String str, ReadableMap readableMap) {
        this.impl.setContext(str, readableMap);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void setTag(String str, String str2) {
        this.impl.setTag(str, str2);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void closeNativeSdk(Promise promise) {
        this.impl.closeNativeSdk(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void enableNativeFramesTracking() {
        this.impl.enableNativeFramesTracking();
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void disableNativeFramesTracking() {
        this.impl.disableNativeFramesTracking();
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void fetchNativeDeviceContexts(Promise promise) {
        this.impl.fetchNativeDeviceContexts(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void fetchNativeSdkInfo(Promise promise) {
        this.impl.fetchNativeSdkInfo(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public WritableMap startProfiling(boolean z) {
        return this.impl.startProfiling(z);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public WritableMap stopProfiling() {
        return this.impl.stopProfiling();
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public String fetchNativePackageName() {
        return this.impl.fetchNativePackageName();
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void captureReplay(boolean z, Promise promise) {
        this.impl.captureReplay(z, promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public String getCurrentReplayId() {
        return this.impl.getCurrentReplayId();
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void crashedLastRun(Promise promise) {
        this.impl.crashedLastRun(promise);
    }

    @Override // io.sentry.react.NativeRNSentrySpec
    public void getNewScreenTimeToDisplay(Promise promise) {
        this.impl.getNewScreenTimeToDisplay(promise);
    }
}
