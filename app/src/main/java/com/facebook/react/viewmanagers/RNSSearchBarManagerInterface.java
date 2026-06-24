package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface RNSSearchBarManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void blur(T t);

    void cancelSearch(T t);

    void clearText(T t);

    void focus(T t);

    void setAllowToolbarIntegration(T t, boolean z);

    void setAutoCapitalize(T t, String str);

    void setAutoFocus(T t, boolean z);

    void setBarTintColor(T t, Integer num);

    void setCancelButtonText(T t, String str);

    void setDisableBackButtonOverride(T t, boolean z);

    void setHeaderIconColor(T t, Integer num);

    void setHideNavigationBar(T t, String str);

    void setHideWhenScrolling(T t, boolean z);

    void setHintTextColor(T t, Integer num);

    void setInputType(T t, String str);

    void setObscureBackground(T t, String str);

    void setPlaceholder(T t, String str);

    void setPlacement(T t, String str);

    void setShouldShowHintSearchIcon(T t, boolean z);

    void setText(T t, String str);

    void setTextColor(T t, Integer num);

    void setTintColor(T t, Integer num);

    void toggleCancelButton(T t, boolean z);
}
