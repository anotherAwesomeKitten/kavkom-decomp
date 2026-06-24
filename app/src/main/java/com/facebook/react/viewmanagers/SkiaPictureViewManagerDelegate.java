package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.SkiaPictureViewManagerInterface;

/* JADX INFO: loaded from: classes.dex */
public class SkiaPictureViewManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & SkiaPictureViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SkiaPictureViewManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "opaque":
                ((SkiaPictureViewManagerInterface) this.mViewManager).setOpaque(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case "androidWarmup":
                ((SkiaPictureViewManagerInterface) this.mViewManager).setAndroidWarmup(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case "debug":
                ((SkiaPictureViewManagerInterface) this.mViewManager).setDebug(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case "colorSpace":
                ((SkiaPictureViewManagerInterface) this.mViewManager).setColorSpace(t, obj == null ? null : (String) obj);
                break;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                break;
        }
    }
}
