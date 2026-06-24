package com.facebook.react.views.virtual.view;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.VirtualViewManagerDelegate;
import com.facebook.react.viewmanagers.VirtualViewManagerInterface;
import com.facebook.react.views.view.ReactClippingViewManager;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.virtual.VirtualViewMode;
import com.facebook.react.views.virtual.VirtualViewRenderState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactVirtualViewManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001\u001bB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0017J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0016H\u0017J\u001a\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\fH\u0016J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0002H\u0014J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0002H\u0014R*\u0010\u0006\u001a\u001e\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00000\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/views/virtual/view/ReactVirtualViewManager;", "Lcom/facebook/react/views/view/ReactClippingViewManager;", "Lcom/facebook/react/views/virtual/view/ReactVirtualView;", "Lcom/facebook/react/viewmanagers/VirtualViewManagerInterface;", "<init>", "()V", "_delegate", "Lcom/facebook/react/viewmanagers/VirtualViewManagerDelegate;", "kotlin.jvm.PlatformType", "getDelegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "getName", "", "createViewInstance", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "setInitialHidden", "", "view", "value", "", "setRenderState", "", "setNativeId", "nativeId", "addEventEmitters", "prepareToRecycleView", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@ReactModule(name = ReactVirtualViewManager.REACT_CLASS)
public final class ReactVirtualViewManager extends ReactClippingViewManager<ReactVirtualView> implements VirtualViewManagerInterface<ReactVirtualView> {
    public static final String REACT_CLASS = "VirtualView";
    private final VirtualViewManagerDelegate<ReactVirtualView, ReactVirtualViewManager> _delegate = new VirtualViewManagerDelegate<>(this);

    @Override // com.facebook.react.viewmanagers.VirtualViewManagerInterface
    public /* bridge */ /* synthetic */ void setRemoveClippedSubviews(View view, boolean z) {
        setRemoveClippedSubviews((ReactViewGroup) view, z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<ReactVirtualView> getDelegate() {
        return this._delegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ReactVirtualView createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new ReactVirtualView(reactContext);
    }

    @Override // com.facebook.react.viewmanagers.VirtualViewManagerInterface
    @ReactProp(name = "initialHidden")
    public void setInitialHidden(ReactVirtualView view, boolean value) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getMode() == null) {
            view.setMode$ReactAndroid_release(value ? VirtualViewMode.Hidden : VirtualViewMode.Visible);
        }
    }

    @Override // com.facebook.react.viewmanagers.VirtualViewManagerInterface
    @ReactProp(name = "renderState")
    public void setRenderState(ReactVirtualView view, int value) {
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
    public void setNativeId(ReactVirtualView view, String nativeId) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.setNativeId(view, nativeId);
        if (view.getDebugLogEnabled()) {
            if (ReactVirtualViewKt.IS_DEBUG_BUILD) {
                String strValueOf = String.valueOf(view.getId());
                FLog.d("ReactVirtualView:setNativeId", ((Object) strValueOf) + " [" + view.getId() + "][" + view.getNativeId$ReactAndroid_release() + "]");
                return;
            }
            String strValueOf2 = String.valueOf(view.getId());
            FLog.w("ReactVirtualView:setNativeId", ((Object) strValueOf2) + " [" + view.getId() + "][" + view.getNativeId$ReactAndroid_release() + "]");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext reactContext, ReactVirtualView view) {
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
    public ReactVirtualView prepareToRecycleView(ThemedReactContext reactContext, ReactVirtualView view) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        view.recycleView$ReactAndroid_release();
        return (ReactVirtualView) super.prepareToRecycleView(reactContext, view);
    }
}
