package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNCViewPagerManagerInterface;

/* JADX INFO: loaded from: classes.dex */
public class RNCViewPagerManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNCViewPagerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNCViewPagerManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "layoutDirection":
                ((RNCViewPagerManagerInterface) this.mViewManager).setLayoutDirection(t, (String) obj);
                break;
            case "offscreenPageLimit":
                ((RNCViewPagerManagerInterface) this.mViewManager).setOffscreenPageLimit(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case "orientation":
                ((RNCViewPagerManagerInterface) this.mViewManager).setOrientation(t, (String) obj);
                break;
            case "overScrollMode":
                ((RNCViewPagerManagerInterface) this.mViewManager).setOverScrollMode(t, (String) obj);
                break;
            case "scrollEnabled":
                ((RNCViewPagerManagerInterface) this.mViewManager).setScrollEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case "keyboardDismissMode":
                ((RNCViewPagerManagerInterface) this.mViewManager).setKeyboardDismissMode(t, (String) obj);
                break;
            case "overdrag":
                ((RNCViewPagerManagerInterface) this.mViewManager).setOverdrag(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case "pageMargin":
                ((RNCViewPagerManagerInterface) this.mViewManager).setPageMargin(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case "initialPage":
                ((RNCViewPagerManagerInterface) this.mViewManager).setInitialPage(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                break;
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: receiveCommand */
    public void kotlinCompat$receiveCommand(T t, String str, ReadableArray readableArray) {
        str.hashCode();
        switch (str) {
            case "setPageWithoutAnimation":
                ((RNCViewPagerManagerInterface) this.mViewManager).setPageWithoutAnimation(t, readableArray.getInt(0));
                break;
            case "setScrollEnabledImperatively":
                ((RNCViewPagerManagerInterface) this.mViewManager).setScrollEnabledImperatively(t, readableArray.getBoolean(0));
                break;
            case "setPage":
                ((RNCViewPagerManagerInterface) this.mViewManager).setPage(t, readableArray.getInt(0));
                break;
        }
    }
}
