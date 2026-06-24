package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface RNSBottomTabsManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setControlNavigationStateInJS(T t, boolean z);

    void setNativeContainerBackgroundColor(T t, Integer num);

    void setTabBarBackgroundColor(T t, Integer num);

    void setTabBarControllerMode(T t, String str);

    void setTabBarHidden(T t, boolean z);

    void setTabBarItemActiveIndicatorColor(T t, Integer num);

    void setTabBarItemActiveIndicatorEnabled(T t, boolean z);

    void setTabBarItemIconColor(T t, Integer num);

    void setTabBarItemIconColorActive(T t, Integer num);

    void setTabBarItemLabelVisibilityMode(T t, String str);

    void setTabBarItemRippleColor(T t, Integer num);

    void setTabBarItemTitleFontColor(T t, Integer num);

    void setTabBarItemTitleFontColorActive(T t, Integer num);

    void setTabBarItemTitleFontFamily(T t, String str);

    void setTabBarItemTitleFontSize(T t, float f);

    void setTabBarItemTitleFontSizeActive(T t, float f);

    void setTabBarItemTitleFontStyle(T t, String str);

    void setTabBarItemTitleFontWeight(T t, String str);

    void setTabBarMinimizeBehavior(T t, String str);

    void setTabBarTintColor(T t, Integer num);
}
