package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewManagerWithGeneratedInterface;

/* JADX INFO: loaded from: classes.dex */
public interface RNSModalScreenManagerInterface<T extends View> extends ViewManagerWithGeneratedInterface {
    void setActivityState(T t, float f);

    void setCustomAnimationOnSwipe(T t, boolean z);

    void setFullScreenSwipeEnabled(T t, String str);

    void setFullScreenSwipeShadowEnabled(T t, boolean z);

    void setGestureEnabled(T t, boolean z);

    void setGestureResponseDistance(T t, ReadableMap readableMap);

    void setHideKeyboardOnSwipe(T t, boolean z);

    void setHomeIndicatorHidden(T t, boolean z);

    void setNativeBackButtonDismissalEnabled(T t, boolean z);

    void setNavigationBarColor(T t, Integer num);

    void setNavigationBarHidden(T t, boolean z);

    void setNavigationBarTranslucent(T t, boolean z);

    void setPreventNativeDismiss(T t, boolean z);

    void setReplaceAnimation(T t, String str);

    void setScreenId(T t, String str);

    void setScreenOrientation(T t, String str);

    void setSheetAllowedDetents(T t, ReadableArray readableArray);

    void setSheetCornerRadius(T t, float f);

    void setSheetDefaultResizeAnimationEnabled(T t, boolean z);

    void setSheetElevation(T t, int i);

    void setSheetExpandsWhenScrolledToEdge(T t, boolean z);

    void setSheetGrabberVisible(T t, boolean z);

    void setSheetInitialDetent(T t, int i);

    void setSheetLargestUndimmedDetent(T t, int i);

    void setSheetShouldOverflowTopInset(T t, boolean z);

    void setStackAnimation(T t, String str);

    void setStackPresentation(T t, String str);

    void setStatusBarAnimation(T t, String str);

    void setStatusBarColor(T t, Integer num);

    void setStatusBarHidden(T t, boolean z);

    void setStatusBarStyle(T t, String str);

    void setStatusBarTranslucent(T t, boolean z);

    void setSwipeDirection(T t, String str);

    void setSynchronousShadowStateUpdatesEnabled(T t, boolean z);

    void setTransitionDuration(T t, int i);
}
