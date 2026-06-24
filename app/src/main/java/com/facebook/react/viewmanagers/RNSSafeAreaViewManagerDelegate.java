package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSSafeAreaViewManagerInterface;

/* JADX INFO: loaded from: classes.dex */
public class RNSSafeAreaViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSSafeAreaViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSSafeAreaViewManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (str.equals("insetType")) {
            ((RNSSafeAreaViewManagerInterface) this.mViewManager).setInsetType(t, (String) obj);
        } else if (str.equals("edges")) {
            ((RNSSafeAreaViewManagerInterface) this.mViewManager).setEdges(t, (ReadableMap) obj);
        } else {
            super.kotlinCompat$setProperty(t, str, obj);
        }
    }
}
