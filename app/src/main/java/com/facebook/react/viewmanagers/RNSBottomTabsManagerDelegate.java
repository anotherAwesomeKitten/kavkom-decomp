package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.viewmanagers.RNSBottomTabsManagerInterface;
import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes.dex */
public class RNSBottomTabsManagerDelegate<T extends View, U extends BaseViewManager<T, ? extends LayoutShadowNode> & RNSBottomTabsManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSBottomTabsManagerDelegate(BaseViewManager baseViewManager) {
        super(baseViewManager);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    /* JADX INFO: renamed from: setProperty */
    public void kotlinCompat$setProperty(T t, String str, Object obj) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1873119606:
                if (str.equals("tabBarTintColor")) {
                    b = 0;
                }
                break;
            case -1716883528:
                if (str.equals("tabBarItemLabelVisibilityMode")) {
                    b = 1;
                }
                break;
            case -1583805635:
                if (str.equals("tabBarControllerMode")) {
                    b = 2;
                }
                break;
            case -1167805191:
                if (str.equals("tabBarItemIconColor")) {
                    b = 3;
                }
                break;
            case -1140765365:
                if (str.equals("tabBarItemActiveIndicatorColor")) {
                    b = 4;
                }
                break;
            case -727132909:
                if (str.equals("tabBarItemTitleFontColorActive")) {
                    b = 5;
                }
                break;
            case -149697865:
                if (str.equals("tabBarBackgroundColor")) {
                    b = 6;
                }
                break;
            case -141083017:
                if (str.equals("tabBarItemTitleFontSize")) {
                    b = 7;
                }
                break;
            case -93216851:
                if (str.equals("tabBarItemTitleFontColor")) {
                    b = 8;
                }
                break;
            case -78279173:
                if (str.equals("tabBarItemTitleFontStyle")) {
                    b = 9;
                }
                break;
            case 144476014:
                if (str.equals("tabBarMinimizeBehavior")) {
                    b = 10;
                }
                break;
            case 278168456:
                if (str.equals("tabBarHidden")) {
                    b = Ascii.VT;
                }
                break;
            case 595595083:
                if (str.equals("nativeContainerBackgroundColor")) {
                    b = Ascii.FF;
                }
                break;
            case 676974377:
                if (str.equals("tabBarItemActiveIndicatorEnabled")) {
                    b = Ascii.CR;
                }
                break;
            case 697418079:
                if (str.equals("tabBarItemIconColorActive")) {
                    b = Ascii.SO;
                }
                break;
            case 1458977038:
                if (str.equals("controlNavigationStateInJS")) {
                    b = Ascii.SI;
                }
                break;
            case 1478227034:
                if (str.equals("tabBarItemTitleFontFamily")) {
                    b = Ascii.DLE;
                }
                break;
            case 1935822306:
                if (str.equals("tabBarItemRippleColor")) {
                    b = 17;
                }
                break;
            case 1968495470:
                if (str.equals("tabBarItemTitleFontWeight")) {
                    b = Ascii.DC2;
                }
                break;
            case 2018161757:
                if (str.equals("tabBarItemTitleFontSizeActive")) {
                    b = 19;
                }
                break;
        }
        switch (b) {
            case 0:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 1:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemLabelVisibilityMode(t, (String) obj);
                break;
            case 2:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarControllerMode(t, (String) obj);
                break;
            case 3:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemIconColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 4:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemActiveIndicatorColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 5:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemTitleFontColorActive(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 6:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 7:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemTitleFontSize(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 8:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemTitleFontColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 9:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemTitleFontStyle(t, obj != null ? (String) obj : null);
                break;
            case 10:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarMinimizeBehavior(t, (String) obj);
                break;
            case 11:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarHidden(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 12:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setNativeContainerBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 13:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemActiveIndicatorEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                break;
            case 14:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemIconColorActive(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 15:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setControlNavigationStateInJS(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 16:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemTitleFontFamily(t, obj != null ? (String) obj : null);
                break;
            case 17:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemRippleColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                break;
            case 18:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemTitleFontWeight(t, obj != null ? (String) obj : null);
                break;
            case 19:
                ((RNSBottomTabsManagerInterface) this.mViewManager).setTabBarItemTitleFontSizeActive(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            default:
                super.kotlinCompat$setProperty(t, str, obj);
                break;
        }
    }
}
