package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSStackScreenManagerInterface;

/* JADX INFO: loaded from: classes.dex */
public class RNSStackScreenManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSStackScreenManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSStackScreenManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (str.equals("screenKey")) {
            ((RNSStackScreenManagerInterface) this.mViewManager).setScreenKey(t, obj == null ? null : (String) obj);
        } else if (str.equals("activityMode")) {
            ((RNSStackScreenManagerInterface) this.mViewManager).setActivityMode(t, (String) obj);
        } else {
            super.kotlinCompat$setProperty(t, str, obj);
        }
    }
}
