package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface RNSBottomTabsScreenManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setBadgeValue(T t, String str);

    void setBottomScrollEdgeEffect(T t, String str);

    void setDrawableIconResourceName(T t, String str);

    void setIconImageSource(T t, ReadableMap readableMap);

    void setIconSfSymbolName(T t, String str);

    void setIconType(T t, String str);

    void setImageIconResource(T t, ReadableMap readableMap);

    void setIsFocused(T t, boolean z);

    void setIsTitleUndefined(T t, boolean z);

    void setLeftScrollEdgeEffect(T t, String str);

    void setOrientation(T t, String str);

    void setOverrideScrollViewContentInsetAdjustmentBehavior(T t, boolean z);

    void setRightScrollEdgeEffect(T t, String str);

    void setScrollEdgeAppearance(T t, Dynamic dynamic);

    void setSelectedIconImageSource(T t, ReadableMap readableMap);

    void setSelectedIconSfSymbolName(T t, String str);

    void setSpecialEffects(T t, ReadableMap readableMap);

    void setStandardAppearance(T t, Dynamic dynamic);

    void setSystemItem(T t, String str);

    void setTabBarItemAccessibilityLabel(T t, String str);

    void setTabBarItemBadgeBackgroundColor(T t, Integer num);

    void setTabBarItemBadgeTextColor(T t, Integer num);

    void setTabBarItemTestID(T t, String str);

    void setTabKey(T t, String str);

    void setTitle(T t, String str);

    void setTopScrollEdgeEffect(T t, String str);

    void setUserInterfaceStyle(T t, String str);
}
