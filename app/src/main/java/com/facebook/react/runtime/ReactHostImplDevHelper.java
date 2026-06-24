package com.facebook.react.runtime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.devsupport.ReactInstanceDevHelper;
import com.facebook.react.devsupport.interfaces.TracingState;
import com.facebook.react.devsupport.interfaces.TracingStateProvider;
import com.facebook.react.devsupport.perfmonitor.PerfMonitorDevHelper;
import com.facebook.react.devsupport.perfmonitor.PerfMonitorInspectorTarget;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import io.sentry.clientreport.DiscardedEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactHostImplDevHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001cH\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001eH\u0016J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006*"}, d2 = {"Lcom/facebook/react/runtime/ReactHostImplDevHelper;", "Lcom/facebook/react/devsupport/ReactInstanceDevHelper;", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorDevHelper;", "Lcom/facebook/react/devsupport/interfaces/TracingStateProvider;", "delegate", "Lcom/facebook/react/runtime/ReactHostImpl;", "<init>", "(Lcom/facebook/react/runtime/ReactHostImpl;)V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "javaScriptExecutorFactory", "Lcom/facebook/react/bridge/JavaScriptExecutorFactory;", "getJavaScriptExecutorFactory", "()Lcom/facebook/react/bridge/JavaScriptExecutorFactory;", "currentReactContext", "Lcom/facebook/react/bridge/ReactContext;", "getCurrentReactContext", "()Lcom/facebook/react/bridge/ReactContext;", "inspectorTarget", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorInspectorTarget;", "getInspectorTarget", "()Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorInspectorTarget;", "onJSBundleLoadedFromServer", "", "toggleElementInspector", "createRootView", "Landroid/view/View;", "appKey", "", "destroyRootView", "rootView", "reload", DiscardedEvent.JsonKeys.REASON, "loadBundle", "Lcom/facebook/react/interfaces/TaskInterface;", "", "bundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "getTracingState", "Lcom/facebook/react/devsupport/interfaces/TracingState;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@UnstableReactNativeAPI
public final class ReactHostImplDevHelper implements ReactInstanceDevHelper, PerfMonitorDevHelper, TracingStateProvider {
    private final ReactHostImpl delegate;

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public void destroyRootView(View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public void onJSBundleLoadedFromServer() {
    }

    public ReactHostImplDevHelper(ReactHostImpl delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public Activity getCurrentActivity() {
        return this.delegate.getLastUsedActivity$ReactAndroid_release();
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        throw new IllegalStateException("Not implemented for bridgeless mode");
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public ReactContext getCurrentReactContext() {
        return this.delegate.getCurrentReactContext();
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorDevHelper
    public PerfMonitorInspectorTarget getInspectorTarget() {
        return this.delegate.getReactHostInspectorTarget();
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public void toggleElementInspector() {
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter;
        ReactContext currentReactContext = this.delegate.getCurrentReactContext();
        if (currentReactContext == null || (rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)) == null) {
            return;
        }
        rCTDeviceEventEmitter.emit("toggleElementInspector", null);
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public View createRootView(String appKey) {
        Intrinsics.checkNotNullParameter(appKey, "appKey");
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || this.delegate.isSurfaceWithModuleNameAttached$ReactAndroid_release(appKey)) {
            return null;
        }
        ReactSurfaceImpl reactSurfaceImplCreateWithView = ReactSurfaceImpl.INSTANCE.createWithView(currentActivity, appKey, new Bundle());
        reactSurfaceImplCreateWithView.attach(this.delegate);
        reactSurfaceImplCreateWithView.start();
        return reactSurfaceImplCreateWithView.getView();
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public void reload(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.delegate.reload(reason);
    }

    @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
    public TaskInterface<Boolean> loadBundle(JSBundleLoader bundleLoader) {
        Intrinsics.checkNotNullParameter(bundleLoader, "bundleLoader");
        return this.delegate.loadBundle$ReactAndroid_release(bundleLoader);
    }

    @Override // com.facebook.react.devsupport.interfaces.TracingStateProvider
    public TracingState getTracingState() {
        TracingState tracingState;
        ReactHostInspectorTarget reactHostInspectorTarget = this.delegate.getReactHostInspectorTarget();
        return (reactHostInspectorTarget == null || (tracingState = reactHostInspectorTarget.getTracingState()) == null) ? TracingState.ENABLEDINCDPMODE : tracingState;
    }
}
