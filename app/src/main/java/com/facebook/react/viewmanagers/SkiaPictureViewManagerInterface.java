package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface SkiaPictureViewManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setAndroidWarmup(T t, boolean z);

    void setColorSpace(T t, String str);

    void setDebug(T t, boolean z);

    void setOpaque(T t, boolean z);
}
