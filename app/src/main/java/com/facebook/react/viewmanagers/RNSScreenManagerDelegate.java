package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSScreenManagerInterface;
import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes.dex */
public class RNSScreenManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSScreenManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public RNSScreenManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        byte b;
        str.hashCode();
        switch (str.hashCode()) {
            case -2035671681:
                b = !str.equals("synchronousShadowStateUpdatesEnabled") ? (byte) -1 : (byte) 0;
                break;
            case -1991728986:
                b = !str.equals("bottomScrollEdgeEffect") ? (byte) -1 : (byte) 1;
                break;
            case -1937389126:
                b = !str.equals("homeIndicatorHidden") ? (byte) -1 : (byte) 2;
                break;
            case -1871891504:
                b = !str.equals("topScrollEdgeEffect") ? (byte) -1 : (byte) 3;
                break;
            case -1853558344:
                b = !str.equals("gestureEnabled") ? (byte) -1 : (byte) 4;
                break;
            case -1791070590:
                b = !str.equals("leftScrollEdgeEffect") ? (byte) -1 : (byte) 5;
                break;
            case -1734097646:
                b = !str.equals("hideKeyboardOnSwipe") ? (byte) -1 : (byte) 6;
                break;
            case -1628518761:
                b = !str.equals("rightScrollEdgeEffect") ? (byte) -1 : (byte) 7;
                break;
            case -1349152186:
                b = !str.equals("sheetCornerRadius") ? (byte) -1 : (byte) 8;
                break;
            case -1322084375:
                b = !str.equals("navigationBarHidden") ? (byte) -1 : (byte) 9;
                break;
            case -1156137512:
                b = !str.equals("statusBarTranslucent") ? (byte) -1 : (byte) 10;
                break;
            case -1150711358:
                b = !str.equals("stackPresentation") ? (byte) -1 : Ascii.VT;
                break;
            case -1047235902:
                b = !str.equals("activityState") ? (byte) -1 : Ascii.FF;
                break;
            case -973702878:
                b = !str.equals("statusBarColor") ? (byte) -1 : Ascii.CR;
                break;
            case -958765200:
                b = !str.equals("statusBarStyle") ? (byte) -1 : Ascii.SO;
                break;
            case -952227806:
                b = !str.equals("fullScreenSwipeShadowEnabled") ? (byte) -1 : Ascii.SI;
                break;
            case -577711652:
                b = !str.equals("stackAnimation") ? (byte) -1 : Ascii.DLE;
                break;
            case -462720700:
                b = !str.equals("navigationBarColor") ? (byte) -1 : (byte) 17;
                break;
            case -411607385:
                b = !str.equals("screenId") ? (byte) -1 : Ascii.DC2;
                break;
            case -381571779:
                b = !str.equals("sheetInitialDetent") ? (byte) -1 : (byte) 19;
                break;
            case -274098190:
                b = !str.equals("sheetAllowedDetents") ? (byte) -1 : Ascii.DC4;
                break;
            case -257141968:
                b = !str.equals("replaceAnimation") ? (byte) -1 : Ascii.NAK;
                break;
            case -166356101:
                b = !str.equals("preventNativeDismiss") ? (byte) -1 : Ascii.SYN;
                break;
            case 17337291:
                b = !str.equals("statusBarHidden") ? (byte) -1 : Ascii.ETB;
                break;
            case 129956386:
                b = !str.equals("fullScreenSwipeEnabled") ? (byte) -1 : (byte) 24;
                break;
            case 187703999:
                b = !str.equals("gestureResponseDistance") ? (byte) -1 : Ascii.EM;
                break;
            case 227582404:
                b = !str.equals("screenOrientation") ? (byte) -1 : Ascii.SUB;
                break;
            case 241896530:
                b = !str.equals("sheetLargestUndimmedDetent") ? (byte) -1 : Ascii.ESC;
                break;
            case 425064969:
                b = !str.equals("transitionDuration") ? (byte) -1 : Ascii.FS;
                break;
            case 658632444:
                b = !str.equals("sheetShouldOverflowTopInset") ? (byte) -1 : Ascii.GS;
                break;
            case 1082157413:
                b = !str.equals("swipeDirection") ? (byte) -1 : Ascii.RS;
                break;
            case 1110843912:
                b = !str.equals("customAnimationOnSwipe") ? (byte) -1 : Ascii.US;
                break;
            case 1116050554:
                b = !str.equals("navigationBarTranslucent") ? (byte) -1 : (byte) 32;
                break;
            case 1269009342:
                b = !str.equals("sheetElevation") ? (byte) -1 : (byte) 33;
                break;
            case 1287164531:
                b = !str.equals("sheetDefaultResizeAnimationEnabled") ? (byte) -1 : (byte) 34;
                break;
            case 1357942638:
                b = !str.equals("sheetGrabberVisible") ? (byte) -1 : (byte) 35;
                break;
            case 1387359683:
                b = !str.equals("statusBarAnimation") ? (byte) -1 : (byte) 36;
                break;
            case 1729091548:
                b = !str.equals("nativeBackButtonDismissalEnabled") ? (byte) -1 : (byte) 37;
                break;
            case 2029667685:
                b = !str.equals("androidResetScreenShadowStateOnOrientationChangeEnabled") ? (byte) -1 : (byte) 38;
                break;
            case 2097450072:
                b = !str.equals("sheetExpandsWhenScrolledToEdge") ? (byte) -1 : (byte) 39;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
                ((RNSScreenManagerInterface) this.mViewManager).setSynchronousShadowStateUpdatesEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 1:
                ((RNSScreenManagerInterface) this.mViewManager).setBottomScrollEdgeEffect(t, (String) obj);
                break;
            case 2:
                ((RNSScreenManagerInterface) this.mViewManager).setHomeIndicatorHidden(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 3:
                ((RNSScreenManagerInterface) this.mViewManager).setTopScrollEdgeEffect(t, (String) obj);
                break;
            case 4:
                ((RNSScreenManagerInterface) this.mViewManager).setGestureEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case 5:
                ((RNSScreenManagerInterface) this.mViewManager).setLeftScrollEdgeEffect(t, (String) obj);
                break;
            case 6:
                ((RNSScreenManagerInterface) this.mViewManager).setHideKeyboardOnSwipe(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 7:
                ((RNSScreenManagerInterface) this.mViewManager).setRightScrollEdgeEffect(t, (String) obj);
                break;
            case 8:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetCornerRadius(t, obj != null ? ((Double) obj).floatValue() : -1.0f);
                break;
            case 9:
                ((RNSScreenManagerInterface) this.mViewManager).setNavigationBarHidden(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 10:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarTranslucent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 11:
                ((RNSScreenManagerInterface) this.mViewManager).setStackPresentation(t, (String) obj);
                break;
            case 12:
                ((RNSScreenManagerInterface) this.mViewManager).setActivityState(t, obj != null ? ((Double) obj).floatValue() : -1.0f);
                break;
            case 13:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 14:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarStyle(t, obj != null ? (String) obj : null);
                break;
            case 15:
                ((RNSScreenManagerInterface) this.mViewManager).setFullScreenSwipeShadowEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case 16:
                ((RNSScreenManagerInterface) this.mViewManager).setStackAnimation(t, (String) obj);
                break;
            case 17:
                ((RNSScreenManagerInterface) this.mViewManager).setNavigationBarColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 18:
                ((RNSScreenManagerInterface) this.mViewManager).setScreenId(t, obj == null ? "" : (String) obj);
                break;
            case 19:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetInitialDetent(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 20:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetAllowedDetents(t, (ReadableArray) obj);
                break;
            case 21:
                ((RNSScreenManagerInterface) this.mViewManager).setReplaceAnimation(t, (String) obj);
                break;
            case 22:
                ((RNSScreenManagerInterface) this.mViewManager).setPreventNativeDismiss(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 23:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarHidden(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 24:
                ((RNSScreenManagerInterface) this.mViewManager).setFullScreenSwipeEnabled(t, (String) obj);
                break;
            case 25:
                ((RNSScreenManagerInterface) this.mViewManager).setGestureResponseDistance(t, (ReadableMap) obj);
                break;
            case 26:
                ((RNSScreenManagerInterface) this.mViewManager).setScreenOrientation(t, obj != null ? (String) obj : null);
                break;
            case 27:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetLargestUndimmedDetent(t, obj != null ? ((Double) obj).intValue() : -1);
                break;
            case 28:
                ((RNSScreenManagerInterface) this.mViewManager).setTransitionDuration(t, obj == null ? 500 : ((Double) obj).intValue());
                break;
            case 29:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetShouldOverflowTopInset(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 30:
                ((RNSScreenManagerInterface) this.mViewManager).setSwipeDirection(t, (String) obj);
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                ((RNSScreenManagerInterface) this.mViewManager).setCustomAnimationOnSwipe(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 32:
                ((RNSScreenManagerInterface) this.mViewManager).setNavigationBarTranslucent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 33:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetElevation(t, obj != null ? ((Double) obj).intValue() : 24);
                break;
            case 34:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetDefaultResizeAnimationEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case 35:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetGrabberVisible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 36:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarAnimation(t, obj != null ? (String) obj : null);
                break;
            case 37:
                ((RNSScreenManagerInterface) this.mViewManager).setNativeBackButtonDismissalEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 38:
                ((RNSScreenManagerInterface) this.mViewManager).setAndroidResetScreenShadowStateOnOrientationChangeEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case 39:
                ((RNSScreenManagerInterface) this.mViewManager).setSheetExpandsWhenScrolledToEdge(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                break;
        }
    }
}
