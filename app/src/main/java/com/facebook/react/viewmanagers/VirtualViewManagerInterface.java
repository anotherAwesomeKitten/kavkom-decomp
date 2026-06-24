package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface VirtualViewManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setInitialHidden(T t, boolean z);

    void setRemoveClippedSubviews(T t, boolean z);

    void setRenderState(T t, int i);
}
