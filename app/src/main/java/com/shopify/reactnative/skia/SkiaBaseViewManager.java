package com.shopify.reactnative.skia;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import com.shopify.reactnative.skia.SkiaBaseView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SkiaBaseViewManager<T extends SkiaBaseView> extends ReactViewManager {
    @Override // com.facebook.react.uimanager.BaseViewManager
    public void setNativeId(ReactViewGroup reactViewGroup, String str) {
        super.setNativeId(reactViewGroup, str);
        ((SkiaBaseView) reactViewGroup).registerView(Integer.parseInt(str));
    }

    @ReactProp(name = "debug")
    public void setDebug(T t, boolean z) {
        t.setDebugMode(z);
    }

    @ReactProp(name = "opaque")
    public void setOpaque(T t, boolean z) {
        t.setOpaque(z);
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(ReactViewGroup reactViewGroup) {
        super.onDropViewInstance(reactViewGroup);
        ((SkiaBaseView) reactViewGroup).dropInstance();
    }
}
