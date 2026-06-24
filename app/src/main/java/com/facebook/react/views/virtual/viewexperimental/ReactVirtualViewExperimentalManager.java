package com.facebook.react.views.virtual.viewexperimental;

import android.view.View;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.VirtualViewExperimentalManagerDelegate;
import com.facebook.react.viewmanagers.VirtualViewExperimentalManagerInterface;
import com.facebook.react.views.view.ReactClippingViewManager;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.virtual.VirtualViewMode;
import com.facebook.react.views.virtual.VirtualViewRenderState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactVirtualViewExperimentalManager.kt */
/* JADX INFO: loaded from: classes.dex */
@ReactModule(name = ReactVirtualViewExperimentalManager.REACT_CLASS)
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001\u001bB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0017J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0016H\u0017J\u001a\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\fH\u0016J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0002H\u0014J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0002H\u0014R*\u0010\u0006\u001a\u001e\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00000\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/views/virtual/viewexperimental/ReactVirtualViewExperimentalManager;", "Lcom/facebook/react/views/view/ReactClippingViewManager;", "Lcom/facebook/react/views/virtual/viewexperimental/ReactVirtualViewExperimental;", "Lcom/facebook/react/viewmanagers/VirtualViewExperimentalManagerInterface;", "<init>", "()V", "_delegate", "Lcom/facebook/react/viewmanagers/VirtualViewExperimentalManagerDelegate;", "kotlin.jvm.PlatformType", "getDelegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "getName", "", "createViewInstance", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "setInitialHidden", "", "view", "value", "", "setRenderState", "", "setNativeId", "nativeId", "addEventEmitters", "prepareToRecycleView", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactVirtualViewExperimentalManager extends ReactClippingViewManager<ReactVirtualViewExperimental> implements VirtualViewExperimentalManagerInterface<ReactVirtualViewExperimental> {
    public static final String REACT_CLASS = "VirtualViewExperimental";
    private final VirtualViewExperimentalManagerDelegate<ReactVirtualViewExperimental, ReactVirtualViewExperimentalManager> _delegate = new VirtualViewExperimentalManagerDelegate<>(this);

    @Override // com.facebook.react.viewmanagers.VirtualViewExperimentalManagerInterface
    public /* bridge */ /* synthetic */ void setRemoveClippedSubviews(View view, boolean z) {
        setRemoveClippedSubviews((ReactViewGroup) view, z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<ReactVirtualViewExperimental> getDelegate() {
        return this._delegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ReactVirtualViewExperimental createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new ReactVirtualViewExperimental(reactContext);
    }

    @Override // com.facebook.react.viewmanagers.VirtualViewExperimentalManagerInterface
    @ReactProp(name = "initialHidden")
    public void setInitialHidden(ReactVirtualViewExperimental view, boolean value) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getMode() == null) {
            view.setMode$ReactAndroid_release(value ? VirtualViewMode.Hidden : VirtualViewMode.Visible);
        }
    }

    @Override // com.facebook.react.viewmanagers.VirtualViewExperimentalManagerInterface
    @ReactProp(name = "renderState")
    public void setRenderState(ReactVirtualViewExperimental view, int value) {
        VirtualViewRenderState virtualViewRenderState;
        Intrinsics.checkNotNullParameter(view, "view");
        if (ReactNativeFeatureFlags.enableVirtualViewRenderState()) {
            if (value == 1) {
                virtualViewRenderState = VirtualViewRenderState.Rendered;
            } else if (value == 2) {
                virtualViewRenderState = VirtualViewRenderState.None;
            } else {
                virtualViewRenderState = VirtualViewRenderState.Unknown;
            }
            view.setRenderState$ReactAndroid_release(virtualViewRenderState);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManager
    public void setNativeId(ReactVirtualViewExperimental view, String nativeId) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.setNativeId(view, nativeId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext reactContext, ReactVirtualViewExperimental view) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, view.getId());
        if (eventDispatcherForReactTag == null) {
            return;
        }
        view.setModeChangeEmitter$ReactAndroid_release(new VirtualViewEventEmitter(view.getId(), UIManagerHelper.getSurfaceId(reactContext), eventDispatcherForReactTag));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public ReactVirtualViewExperimental prepareToRecycleView(ThemedReactContext reactContext, ReactVirtualViewExperimental view) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        view.recycleView$ReactAndroid_release();
        return (ReactVirtualViewExperimental) super.prepareToRecycleView(reactContext, view);
    }
}
