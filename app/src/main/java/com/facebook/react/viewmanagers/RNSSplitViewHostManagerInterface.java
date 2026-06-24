package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface RNSSplitViewHostManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setColumnMetrics(T t, ReadableMap readableMap);

    void setDisplayModeButtonVisibility(T t, String str);

    void setOrientation(T t, String str);

    void setPreferredDisplayMode(T t, String str);

    void setPreferredSplitBehavior(T t, String str);

    void setPresentsWithGesture(T t, boolean z);

    void setPrimaryBackgroundStyle(T t, String str);

    void setPrimaryEdge(T t, String str);

    void setShowInspector(T t, boolean z);

    void setShowSecondaryToggleButton(T t, boolean z);
}
