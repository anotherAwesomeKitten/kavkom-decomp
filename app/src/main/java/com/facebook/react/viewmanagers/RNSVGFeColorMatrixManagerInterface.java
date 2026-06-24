package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface RNSVGFeColorMatrixManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setHeight(T t, Dynamic dynamic);

    void setIn1(T t, String str);

    void setResult(T t, String str);

    void setType(T t, String str);

    void setValues(T t, ReadableArray readableArray);

    void setWidth(T t, Dynamic dynamic);

    void setX(T t, Dynamic dynamic);

    void setY(T t, Dynamic dynamic);
}
