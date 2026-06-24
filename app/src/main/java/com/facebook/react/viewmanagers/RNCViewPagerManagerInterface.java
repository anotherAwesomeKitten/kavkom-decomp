package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface RNCViewPagerManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setInitialPage(T t, int i);

    void setKeyboardDismissMode(T t, String str);

    void setLayoutDirection(T t, String str);

    void setOffscreenPageLimit(T t, int i);

    void setOrientation(T t, String str);

    void setOverScrollMode(T t, String str);

    void setOverdrag(T t, boolean z);

    void setPage(T t, int i);

    void setPageMargin(T t, int i);

    void setPageWithoutAnimation(T t, int i);

    void setScrollEnabled(T t, boolean z);

    void setScrollEnabledImperatively(T t, boolean z);
}
