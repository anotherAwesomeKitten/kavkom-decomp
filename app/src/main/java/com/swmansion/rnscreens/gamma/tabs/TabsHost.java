package com.swmansion.rnscreens.gamma.tabs;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Choreographer;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.swmansion.rnscreens.ScreenStack;
import com.swmansion.rnscreens.gamma.helpers.FragmentManagerHelper;
import com.swmansion.rnscreens.gamma.helpers.ViewFinder;
import com.swmansion.rnscreens.gamma.helpers.ViewIdGenerator;
import com.swmansion.rnscreens.safearea.EdgeInsets;
import com.swmansion.rnscreens.safearea.SafeAreaProvider;
import com.swmansion.rnscreens.safearea.SafeAreaView;
import com.swmansion.rnscreens.utils.RNSLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: TabsHost.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u0007\n\u0002\b#\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 ¾\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006¼\u0001½\u0001¾\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ$\u0010{\u001a\u00020|\"\u0004\b\u0000\u0010}2\u0006\u0010~\u001a\u0002H}2\u0006\u0010\u007f\u001a\u0002H}H\u0002¢\u0006\u0003\u0010\u0080\u0001J\t\u0010\u0081\u0001\u001a\u00020|H\u0014J\"\u0010\u0082\u0001\u001a\u00020|2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0085\u0001\u001a\u00020&H\u0000¢\u0006\u0003\b\u0086\u0001J\u0018\u0010\u0087\u0001\u001a\u00020|2\u0007\u0010\u0085\u0001\u001a\u00020&H\u0000¢\u0006\u0003\b\u0088\u0001J\u0019\u0010\u0089\u0001\u001a\u00020|2\b\u0010\u008a\u0001\u001a\u00030\u0084\u0001H\u0000¢\u0006\u0003\b\u008b\u0001J\u000f\u0010\u008c\u0001\u001a\u00020|H\u0000¢\u0006\u0003\b\u008d\u0001J\u001c\u0010\u008e\u0001\u001a\u00020|2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u008f\u0001\u001a\u00020)H\u0016J\u0013\u0010\u0090\u0001\u001a\u00020|2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0016J\u0015\u0010\u0091\u0001\u001a\u0004\u0018\u00010!2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0016J\u001d\u0010\u0092\u0001\u001a\u00020|2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0016J\t\u0010\u0095\u0001\u001a\u00020|H\u0002J\t\u0010\u0096\u0001\u001a\u00020|H\u0002J\t\u0010\u0099\u0001\u001a\u00020|H\u0002J\t\u0010\u009a\u0001\u001a\u00020|H\u0016J\u0015\u0010\u009b\u0001\u001a\u00020|2\n\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u0094\u0001H\u0014J\u0012\u0010\u009d\u0001\u001a\u00020|2\u0007\u0010\u009e\u0001\u001a\u00020&H\u0002J\t\u0010\u009f\u0001\u001a\u00020|H\u0002J\u0014\u0010 \u0001\u001a\u0004\u0018\u00010!2\u0007\u0010¡\u0001\u001a\u00020&H\u0002J\u0010\u0010¢\u0001\u001a\u0004\u0018\u00010&H\u0002¢\u0006\u0002\u00103J\u0016\u0010£\u0001\u001a\u0005\u0018\u00010¤\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002J\u0012\u0010¥\u0001\u001a\u00020|2\u0007\u0010¦\u0001\u001a\u00020+H\u0016J\u0012\u0010§\u0001\u001a\u00020|2\u0007\u0010¦\u0001\u001a\u00020+H\u0016J\n\u0010¨\u0001\u001a\u00030©\u0001H\u0016J\u0018\u0010ª\u0001\u001a\u0005\u0018\u00010«\u00012\n\u0010¬\u0001\u001a\u0005\u0018\u00010«\u0001H\u0016J\u000f\u0010\u00ad\u0001\u001a\u00020|H\u0000¢\u0006\u0003\b®\u0001J]\u0010¯\u0001\u001a\u00020|2\n\u0010°\u0001\u001a\u0005\u0018\u00010±\u00012\u0007\u0010²\u0001\u001a\u00020&2\u0007\u0010³\u0001\u001a\u00020&2\u0007\u0010´\u0001\u001a\u00020&2\u0007\u0010µ\u0001\u001a\u00020&2\u0007\u0010¶\u0001\u001a\u00020&2\u0007\u0010·\u0001\u001a\u00020&2\u0007\u0010¸\u0001\u001a\u00020&2\u0007\u0010¹\u0001\u001a\u00020&H\u0016J\u001b\u0010º\u0001\u001a\u00020|2\u000b\b\u0002\u0010»\u0001\u001a\u0004\u0018\u00010&H\u0002¢\u0006\u0002\u00105R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00060\u000eR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0004\n\u0002\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R/\u00101\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b2\u00103\"\u0004\b4\u00105R/\u00108\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b;\u00107\u001a\u0004\b9\u00103\"\u0004\b:\u00105R+\u0010<\u001a\u00020)2\u0006\u00100\u001a\u00020)8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u00107\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R/\u0010A\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bD\u00107\u001a\u0004\bB\u00103\"\u0004\bC\u00105R/\u0010F\u001a\u0004\u0018\u00010E2\b\u00100\u001a\u0004\u0018\u00010E8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bK\u00107\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR/\u0010L\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bO\u00107\u001a\u0004\bM\u00103\"\u0004\bN\u00105R/\u0010P\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bS\u00107\u001a\u0004\bQ\u00103\"\u0004\bR\u00105R/\u0010T\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bW\u00107\u001a\u0004\bU\u00103\"\u0004\bV\u00105R/\u0010Y\u001a\u0004\u0018\u00010X2\b\u00100\u001a\u0004\u0018\u00010X8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b^\u00107\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R/\u0010_\u001a\u0004\u0018\u00010X2\b\u00100\u001a\u0004\u0018\u00010X8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bb\u00107\u001a\u0004\b`\u0010[\"\u0004\ba\u0010]R/\u0010c\u001a\u0004\u0018\u00010E2\b\u00100\u001a\u0004\u0018\u00010E8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bf\u00107\u001a\u0004\bd\u0010H\"\u0004\be\u0010JR/\u0010g\u001a\u0004\u0018\u00010E2\b\u00100\u001a\u0004\u0018\u00010E8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bj\u00107\u001a\u0004\bh\u0010H\"\u0004\bi\u0010JR/\u0010k\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bn\u00107\u001a\u0004\bl\u00103\"\u0004\bm\u00105R/\u0010o\u001a\u0004\u0018\u00010E2\b\u00100\u001a\u0004\u0018\u00010E8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\br\u00107\u001a\u0004\bp\u0010H\"\u0004\bq\u0010JR+\u0010s\u001a\u00020)2\u0006\u00100\u001a\u00020)8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bv\u00107\u001a\u0004\bt\u0010=\"\u0004\bu\u0010?R/\u0010w\u001a\u0004\u0018\u00010&2\b\u00100\u001a\u0004\u0018\u00010&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bz\u00107\u001a\u0004\bx\u00103\"\u0004\by\u00105R\u0010\u0010\u0097\u0001\u001a\u00030\u0098\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¿\u0001"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;", "Landroid/widget/FrameLayout;", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenDelegate;", "Lcom/swmansion/rnscreens/safearea/SafeAreaProvider;", "Landroid/view/View$OnLayoutChangeListener;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "<init>", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "getReactContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "containerUpdateCoordinator", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHost$ContainerUpdateCoordinator;", "specialEffectsHandler", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHost$SpecialEffectsHandler;", "wrappedContext", "Landroidx/appcompat/view/ContextThemeWrapper;", "bottomNavigationView", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "contentView", "eventEmitter", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHostEventEmitter;", "getEventEmitter$react_native_screens_release", "()Lcom/swmansion/rnscreens/gamma/tabs/TabsHostEventEmitter;", "setEventEmitter$react_native_screens_release", "(Lcom/swmansion/rnscreens/gamma/tabs/TabsHostEventEmitter;)V", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "requireFragmentManager", "getRequireFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "tabScreenFragments", "", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenFragment;", "currentFocusedTab", "getCurrentFocusedTab", "()Lcom/swmansion/rnscreens/gamma/tabs/TabScreenFragment;", "lastAppliedUiMode", "", "Ljava/lang/Integer;", "isLayoutEnqueued", "", "interfaceInsetsChangeListener", "Lcom/swmansion/rnscreens/safearea/SafeAreaView;", "appearanceCoordinator", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHostAppearanceCoordinator;", "a11yCoordinator", "Lcom/swmansion/rnscreens/gamma/tabs/TabsHostA11yCoordinator;", "<set-?>", "tabBarBackgroundColor", "getTabBarBackgroundColor", "()Ljava/lang/Integer;", "setTabBarBackgroundColor", "(Ljava/lang/Integer;)V", "tabBarBackgroundColor$delegate", "Lkotlin/properties/ReadWriteProperty;", "tabBarItemActiveIndicatorColor", "getTabBarItemActiveIndicatorColor", "setTabBarItemActiveIndicatorColor", "tabBarItemActiveIndicatorColor$delegate", "isTabBarItemActiveIndicatorEnabled", "()Z", "setTabBarItemActiveIndicatorEnabled", "(Z)V", "isTabBarItemActiveIndicatorEnabled$delegate", "tabBarItemIconColor", "getTabBarItemIconColor", "setTabBarItemIconColor", "tabBarItemIconColor$delegate", "", "tabBarItemTitleFontFamily", "getTabBarItemTitleFontFamily", "()Ljava/lang/String;", "setTabBarItemTitleFontFamily", "(Ljava/lang/String;)V", "tabBarItemTitleFontFamily$delegate", "tabBarItemIconColorActive", "getTabBarItemIconColorActive", "setTabBarItemIconColorActive", "tabBarItemIconColorActive$delegate", "tabBarItemTitleFontColor", "getTabBarItemTitleFontColor", "setTabBarItemTitleFontColor", "tabBarItemTitleFontColor$delegate", "tabBarItemTitleFontColorActive", "getTabBarItemTitleFontColorActive", "setTabBarItemTitleFontColorActive", "tabBarItemTitleFontColorActive$delegate", "", "tabBarItemTitleFontSize", "getTabBarItemTitleFontSize", "()Ljava/lang/Float;", "setTabBarItemTitleFontSize", "(Ljava/lang/Float;)V", "tabBarItemTitleFontSize$delegate", "tabBarItemTitleFontSizeActive", "getTabBarItemTitleFontSizeActive", "setTabBarItemTitleFontSizeActive", "tabBarItemTitleFontSizeActive$delegate", "tabBarItemTitleFontWeight", "getTabBarItemTitleFontWeight", "setTabBarItemTitleFontWeight", "tabBarItemTitleFontWeight$delegate", "tabBarItemTitleFontStyle", "getTabBarItemTitleFontStyle", "setTabBarItemTitleFontStyle", "tabBarItemTitleFontStyle$delegate", "tabBarItemRippleColor", "getTabBarItemRippleColor", "setTabBarItemRippleColor", "tabBarItemRippleColor$delegate", "tabBarItemLabelVisibilityMode", "getTabBarItemLabelVisibilityMode", "setTabBarItemLabelVisibilityMode", "tabBarItemLabelVisibilityMode$delegate", "tabBarHidden", "getTabBarHidden", "setTabBarHidden", "tabBarHidden$delegate", "nativeContainerBackgroundColor", "getNativeContainerBackgroundColor", "setNativeContainerBackgroundColor", "nativeContainerBackgroundColor$delegate", "updateNavigationMenuIfNeeded", "", "T", "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;)V", "onAttachedToWindow", "mountReactSubviewAt", "tabScreen", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreen;", FirebaseAnalytics.Param.INDEX, "mountReactSubviewAt$react_native_screens_release", "unmountReactSubviewAt", "unmountReactSubviewAt$react_native_screens_release", "unmountReactSubview", "reactSubview", "unmountReactSubview$react_native_screens_release", "unmountAllReactSubviews", "unmountAllReactSubviews$react_native_screens_release", "onTabFocusChangedFromJS", "isFocused", "onMenuItemAttributesChange", "getFragmentForTabScreen", "onFragmentConfigurationChange", "config", "Landroid/content/res/Configuration;", "updateBottomNavigationViewAppearance", "updateSelectedTab", "layoutCallback", "Landroid/view/Choreographer$FrameCallback;", "refreshLayout", "requestLayout", "onConfigurationChanged", "newConfig", "applyDayNightUiModeIfNeeded", "uiMode", "forceSubtreeMeasureAndLayoutPass", "getFragmentForMenuItemId", "itemId", "getSelectedTabScreenFragmentId", "getMenuItemForTabScreen", "Landroid/view/MenuItem;", "setOnInterfaceInsetsChangeListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeOnInterfaceInsetsChangeListener", "getInterfaceInsets", "Lcom/swmansion/rnscreens/safearea/EdgeInsets;", "dispatchApplyWindowInsets", "Landroid/view/WindowInsets;", "insets", "onViewManagerAddEventEmitters", "onViewManagerAddEventEmitters$react_native_screens_release", "onLayoutChange", "view", "Landroid/view/View;", "left", "top", ViewProps.RIGHT, ViewProps.BOTTOM, "oldLeft", "oldTop", "oldRight", "oldBottom", "updateInterfaceInsets", "newHeight", "ContainerUpdateCoordinator", "SpecialEffectsHandler", "Companion", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TabsHost extends FrameLayout implements TabScreenDelegate, SafeAreaProvider, View.OnLayoutChangeListener {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarBackgroundColor", "getTabBarBackgroundColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemActiveIndicatorColor", "getTabBarItemActiveIndicatorColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "isTabBarItemActiveIndicatorEnabled", "isTabBarItemActiveIndicatorEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemIconColor", "getTabBarItemIconColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontFamily", "getTabBarItemTitleFontFamily()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemIconColorActive", "getTabBarItemIconColorActive()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontColor", "getTabBarItemTitleFontColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontColorActive", "getTabBarItemTitleFontColorActive()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontSize", "getTabBarItemTitleFontSize()Ljava/lang/Float;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontSizeActive", "getTabBarItemTitleFontSizeActive()Ljava/lang/Float;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontWeight", "getTabBarItemTitleFontWeight()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemTitleFontStyle", "getTabBarItemTitleFontStyle()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemRippleColor", "getTabBarItemRippleColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarItemLabelVisibilityMode", "getTabBarItemLabelVisibilityMode()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "tabBarHidden", "getTabBarHidden()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabsHost.class, "nativeContainerBackgroundColor", "getNativeContainerBackgroundColor()Ljava/lang/Integer;", 0))};
    public static final String TAG = "TabsHost";
    private final TabsHostA11yCoordinator a11yCoordinator;
    private final TabsHostAppearanceCoordinator appearanceCoordinator;
    private final BottomNavigationView bottomNavigationView;
    private final ContainerUpdateCoordinator containerUpdateCoordinator;
    private final FrameLayout contentView;
    public TabsHostEventEmitter eventEmitter;
    private FragmentManager fragmentManager;
    private SafeAreaView interfaceInsetsChangeListener;
    private boolean isLayoutEnqueued;

    /* JADX INFO: renamed from: isTabBarItemActiveIndicatorEnabled$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isTabBarItemActiveIndicatorEnabled;
    private Integer lastAppliedUiMode;
    private final Choreographer.FrameCallback layoutCallback;

    /* JADX INFO: renamed from: nativeContainerBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty nativeContainerBackgroundColor;
    private final ThemedReactContext reactContext;
    private final SpecialEffectsHandler specialEffectsHandler;

    /* JADX INFO: renamed from: tabBarBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarBackgroundColor;

    /* JADX INFO: renamed from: tabBarHidden$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarHidden;

    /* JADX INFO: renamed from: tabBarItemActiveIndicatorColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemActiveIndicatorColor;

    /* JADX INFO: renamed from: tabBarItemIconColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemIconColor;

    /* JADX INFO: renamed from: tabBarItemIconColorActive$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemIconColorActive;

    /* JADX INFO: renamed from: tabBarItemLabelVisibilityMode$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemLabelVisibilityMode;

    /* JADX INFO: renamed from: tabBarItemRippleColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemRippleColor;

    /* JADX INFO: renamed from: tabBarItemTitleFontColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontColor;

    /* JADX INFO: renamed from: tabBarItemTitleFontColorActive$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontColorActive;

    /* JADX INFO: renamed from: tabBarItemTitleFontFamily$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontFamily;

    /* JADX INFO: renamed from: tabBarItemTitleFontSize$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontSize;

    /* JADX INFO: renamed from: tabBarItemTitleFontSizeActive$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontSizeActive;

    /* JADX INFO: renamed from: tabBarItemTitleFontStyle$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontStyle;

    /* JADX INFO: renamed from: tabBarItemTitleFontWeight$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTitleFontWeight;
    private final List<TabScreenFragment> tabScreenFragments;
    private final ContextThemeWrapper wrappedContext;

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabsHost(ThemedReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.containerUpdateCoordinator = new ContainerUpdateCoordinator();
        this.specialEffectsHandler = new SpecialEffectsHandler();
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(reactContext, R.style.Theme_Material3_DayNight_NoActionBar);
        this.wrappedContext = contextThemeWrapper;
        BottomNavigationView bottomNavigationView = new BottomNavigationView(contextThemeWrapper);
        bottomNavigationView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 80));
        this.bottomNavigationView = bottomNavigationView;
        FrameLayout frameLayout = new FrameLayout(reactContext);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setId(ViewIdGenerator.INSTANCE.generateViewId());
        this.contentView = frameLayout;
        ArrayList arrayList = new ArrayList();
        this.tabScreenFragments = arrayList;
        this.appearanceCoordinator = new TabsHostAppearanceCoordinator(contextThemeWrapper, bottomNavigationView, arrayList);
        this.a11yCoordinator = new TabsHostA11yCoordinator(bottomNavigationView, arrayList);
        Delegates delegates = Delegates.INSTANCE;
        final Object obj = null;
        this.tabBarBackgroundColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates2 = Delegates.INSTANCE;
        this.tabBarItemActiveIndicatorColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates3 = Delegates.INSTANCE;
        final boolean z = true;
        this.isTabBarItemActiveIndicatorEnabled = new ObservableProperty<Boolean>(z) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                this.updateNavigationMenuIfNeeded(Boolean.valueOf(oldValue.booleanValue()), Boolean.valueOf(zBooleanValue));
            }
        };
        Delegates delegates4 = Delegates.INSTANCE;
        this.tabBarItemIconColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates5 = Delegates.INSTANCE;
        this.tabBarItemTitleFontFamily = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates6 = Delegates.INSTANCE;
        this.tabBarItemIconColorActive = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates7 = Delegates.INSTANCE;
        this.tabBarItemTitleFontColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$7
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates8 = Delegates.INSTANCE;
        this.tabBarItemTitleFontColorActive = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$8
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates9 = Delegates.INSTANCE;
        this.tabBarItemTitleFontSize = new ObservableProperty<Float>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$9
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Float oldValue, Float newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates10 = Delegates.INSTANCE;
        this.tabBarItemTitleFontSizeActive = new ObservableProperty<Float>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$10
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Float oldValue, Float newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates11 = Delegates.INSTANCE;
        this.tabBarItemTitleFontWeight = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$11
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates12 = Delegates.INSTANCE;
        this.tabBarItemTitleFontStyle = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$12
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates13 = Delegates.INSTANCE;
        this.tabBarItemRippleColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$13
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates14 = Delegates.INSTANCE;
        this.tabBarItemLabelVisibilityMode = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$14
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabsHost tabsHost = this;
                tabsHost.updateNavigationMenuIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates15 = Delegates.INSTANCE;
        final boolean z2 = false;
        this.tabBarHidden = new ObservableProperty<Boolean>(z2) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$15
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                boolean zBooleanValue2 = oldValue.booleanValue();
                if (zBooleanValue != zBooleanValue2) {
                    TabsHost.updateInterfaceInsets$default(this, null, 1, null);
                    this.updateNavigationMenuIfNeeded(Boolean.valueOf(zBooleanValue2), Boolean.valueOf(zBooleanValue));
                }
            }
        };
        Delegates delegates16 = Delegates.INSTANCE;
        this.nativeContainerBackgroundColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$special$$inlined$observable$16
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                Integer num = newValue;
                if (Intrinsics.areEqual(num, oldValue)) {
                    return;
                }
                this.setBackground(num != null ? new ColorDrawable(num.intValue()) : null);
            }
        };
        addView(frameLayout);
        addView(bottomNavigationView);
        bottomNavigationView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                TabsHost._init_$lambda$22(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda1
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                return TabsHost._init_$lambda$23(this.f$0, menuItem);
            }
        });
        this.layoutCallback = new Choreographer.FrameCallback() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda2
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                TabsHost.layoutCallback$lambda$44(this.f$0, j);
            }
        };
    }

    /* JADX INFO: compiled from: TabsHost.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\u000b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\tJ\u0006\u0010\r\u001a\u00020\tJ\b\u0010\u000e\u001a\u00020\tH\u0002J\u0006\u0010\u000f\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHost$ContainerUpdateCoordinator;", "", "<init>", "(Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;)V", "isUpdatePending", "", "isSelectedTabInvalidated", "isBottomNavigationMenuInvalidated", "invalidateSelectedTab", "", "invalidateNavigationMenu", "invalidateAll", "postContainerUpdateIfNeeded", "postContainerUpdate", "runContainerUpdateIfNeeded", "runContainerUpdate", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    final class ContainerUpdateCoordinator {
        private boolean isBottomNavigationMenuInvalidated;
        private boolean isSelectedTabInvalidated;
        private boolean isUpdatePending;

        public ContainerUpdateCoordinator() {
        }

        public final void invalidateSelectedTab() {
            this.isSelectedTabInvalidated = true;
        }

        public final void invalidateNavigationMenu() {
            this.isBottomNavigationMenuInvalidated = true;
        }

        public final void invalidateAll() {
            invalidateSelectedTab();
            invalidateNavigationMenu();
        }

        public final void postContainerUpdateIfNeeded() {
            if (this.isUpdatePending) {
                return;
            }
            postContainerUpdate();
        }

        public final void postContainerUpdate() {
            this.isUpdatePending = true;
            TabsHost.this.post(new Runnable() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$ContainerUpdateCoordinator$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.runContainerUpdateIfNeeded();
                }
            });
        }

        public final void runContainerUpdateIfNeeded() {
            if (this.isUpdatePending) {
                runContainerUpdate();
            }
        }

        public final void runContainerUpdate() {
            this.isUpdatePending = false;
            if (this.isSelectedTabInvalidated) {
                this.isSelectedTabInvalidated = false;
                TabsHost.this.updateSelectedTab();
            }
            if (this.isBottomNavigationMenuInvalidated) {
                this.isBottomNavigationMenuInvalidated = false;
                TabsHost.this.updateBottomNavigationViewAppearance();
                TabsHost.this.a11yCoordinator.setA11yPropertiesToAllTabItems();
            }
        }
    }

    /* JADX INFO: compiled from: TabsHost.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabsHost$SpecialEffectsHandler;", "", "<init>", "(Lcom/swmansion/rnscreens/gamma/tabs/TabsHost;)V", "handleRepeatedTabSelection", "", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class SpecialEffectsHandler {
        public SpecialEffectsHandler() {
        }

        public final boolean handleRepeatedTabSelection() {
            ScrollView scrollViewFindScrollViewInFirstDescendantChain;
            ScreenStack screenStackFindScreenStackInFirstDescendantChain;
            FrameLayout frameLayout = TabsHost.this.contentView;
            TabScreenFragment currentFocusedTab = TabsHost.this.getCurrentFocusedTab();
            if (currentFocusedTab.getTabScreen().getShouldUseRepeatedTabSelectionPopToRootSpecialEffect() && (screenStackFindScreenStackInFirstDescendantChain = ViewFinder.INSTANCE.findScreenStackInFirstDescendantChain(frameLayout)) != null && screenStackFindScreenStackInFirstDescendantChain.popToRoot()) {
                return true;
            }
            if (!currentFocusedTab.getTabScreen().getShouldUseRepeatedTabSelectionScrollToTopSpecialEffect() || (scrollViewFindScrollViewInFirstDescendantChain = ViewFinder.INSTANCE.findScrollViewInFirstDescendantChain(frameLayout)) == null || scrollViewFindScrollViewInFirstDescendantChain.getScrollY() <= 0) {
                return false;
            }
            scrollViewFindScrollViewInFirstDescendantChain.smoothScrollTo(scrollViewFindScrollViewInFirstDescendantChain.getScrollX(), 0);
            return true;
        }
    }

    public final TabsHostEventEmitter getEventEmitter$react_native_screens_release() {
        TabsHostEventEmitter tabsHostEventEmitter = this.eventEmitter;
        if (tabsHostEventEmitter != null) {
            return tabsHostEventEmitter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("eventEmitter");
        return null;
    }

    public final void setEventEmitter$react_native_screens_release(TabsHostEventEmitter tabsHostEventEmitter) {
        Intrinsics.checkNotNullParameter(tabsHostEventEmitter, "<set-?>");
        this.eventEmitter = tabsHostEventEmitter;
    }

    private final FragmentManager getRequireFragmentManager() {
        FragmentManager fragmentManager = this.fragmentManager;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("[RNScreens] Nullish fragment manager".toString());
    }

    public final TabScreenFragment getCurrentFocusedTab() {
        Object next;
        Iterator<T> it = this.tabScreenFragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((TabScreenFragment) next).getTabScreen().getIsFocusedTab()) {
                break;
            }
        }
        if (next != null) {
            return (TabScreenFragment) next;
        }
        throw new IllegalStateException("[RNScreens] No focused tab present".toString());
    }

    public final Integer getTabBarBackgroundColor() {
        return (Integer) this.tabBarBackgroundColor.getValue(this, $$delegatedProperties[0]);
    }

    public final void setTabBarBackgroundColor(Integer num) {
        this.tabBarBackgroundColor.setValue(this, $$delegatedProperties[0], num);
    }

    public final Integer getTabBarItemActiveIndicatorColor() {
        return (Integer) this.tabBarItemActiveIndicatorColor.getValue(this, $$delegatedProperties[1]);
    }

    public final void setTabBarItemActiveIndicatorColor(Integer num) {
        this.tabBarItemActiveIndicatorColor.setValue(this, $$delegatedProperties[1], num);
    }

    public final boolean isTabBarItemActiveIndicatorEnabled() {
        return ((Boolean) this.isTabBarItemActiveIndicatorEnabled.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    public final void setTabBarItemActiveIndicatorEnabled(boolean z) {
        this.isTabBarItemActiveIndicatorEnabled.setValue(this, $$delegatedProperties[2], Boolean.valueOf(z));
    }

    public final Integer getTabBarItemIconColor() {
        return (Integer) this.tabBarItemIconColor.getValue(this, $$delegatedProperties[3]);
    }

    public final void setTabBarItemIconColor(Integer num) {
        this.tabBarItemIconColor.setValue(this, $$delegatedProperties[3], num);
    }

    public final String getTabBarItemTitleFontFamily() {
        return (String) this.tabBarItemTitleFontFamily.getValue(this, $$delegatedProperties[4]);
    }

    public final void setTabBarItemTitleFontFamily(String str) {
        this.tabBarItemTitleFontFamily.setValue(this, $$delegatedProperties[4], str);
    }

    public final Integer getTabBarItemIconColorActive() {
        return (Integer) this.tabBarItemIconColorActive.getValue(this, $$delegatedProperties[5]);
    }

    public final void setTabBarItemIconColorActive(Integer num) {
        this.tabBarItemIconColorActive.setValue(this, $$delegatedProperties[5], num);
    }

    public final Integer getTabBarItemTitleFontColor() {
        return (Integer) this.tabBarItemTitleFontColor.getValue(this, $$delegatedProperties[6]);
    }

    public final void setTabBarItemTitleFontColor(Integer num) {
        this.tabBarItemTitleFontColor.setValue(this, $$delegatedProperties[6], num);
    }

    public final Integer getTabBarItemTitleFontColorActive() {
        return (Integer) this.tabBarItemTitleFontColorActive.getValue(this, $$delegatedProperties[7]);
    }

    public final void setTabBarItemTitleFontColorActive(Integer num) {
        this.tabBarItemTitleFontColorActive.setValue(this, $$delegatedProperties[7], num);
    }

    public final Float getTabBarItemTitleFontSize() {
        return (Float) this.tabBarItemTitleFontSize.getValue(this, $$delegatedProperties[8]);
    }

    public final void setTabBarItemTitleFontSize(Float f) {
        this.tabBarItemTitleFontSize.setValue(this, $$delegatedProperties[8], f);
    }

    public final Float getTabBarItemTitleFontSizeActive() {
        return (Float) this.tabBarItemTitleFontSizeActive.getValue(this, $$delegatedProperties[9]);
    }

    public final void setTabBarItemTitleFontSizeActive(Float f) {
        this.tabBarItemTitleFontSizeActive.setValue(this, $$delegatedProperties[9], f);
    }

    public final String getTabBarItemTitleFontWeight() {
        return (String) this.tabBarItemTitleFontWeight.getValue(this, $$delegatedProperties[10]);
    }

    public final void setTabBarItemTitleFontWeight(String str) {
        this.tabBarItemTitleFontWeight.setValue(this, $$delegatedProperties[10], str);
    }

    public final String getTabBarItemTitleFontStyle() {
        return (String) this.tabBarItemTitleFontStyle.getValue(this, $$delegatedProperties[11]);
    }

    public final void setTabBarItemTitleFontStyle(String str) {
        this.tabBarItemTitleFontStyle.setValue(this, $$delegatedProperties[11], str);
    }

    public final Integer getTabBarItemRippleColor() {
        return (Integer) this.tabBarItemRippleColor.getValue(this, $$delegatedProperties[12]);
    }

    public final void setTabBarItemRippleColor(Integer num) {
        this.tabBarItemRippleColor.setValue(this, $$delegatedProperties[12], num);
    }

    public final String getTabBarItemLabelVisibilityMode() {
        return (String) this.tabBarItemLabelVisibilityMode.getValue(this, $$delegatedProperties[13]);
    }

    public final void setTabBarItemLabelVisibilityMode(String str) {
        this.tabBarItemLabelVisibilityMode.setValue(this, $$delegatedProperties[13], str);
    }

    public final boolean getTabBarHidden() {
        return ((Boolean) this.tabBarHidden.getValue(this, $$delegatedProperties[14])).booleanValue();
    }

    public final void setTabBarHidden(boolean z) {
        this.tabBarHidden.setValue(this, $$delegatedProperties[14], Boolean.valueOf(z));
    }

    public final Integer getNativeContainerBackgroundColor() {
        return (Integer) this.nativeContainerBackgroundColor.getValue(this, $$delegatedProperties[15]);
    }

    public final void setNativeContainerBackgroundColor(Integer num) {
        this.nativeContainerBackgroundColor.setValue(this, $$delegatedProperties[15], num);
    }

    public final <T> void updateNavigationMenuIfNeeded(T oldValue, T newValue) {
        if (Intrinsics.areEqual(newValue, oldValue)) {
            return;
        }
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateNavigationMenu();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    public static final void _init_$lambda$22(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        RNSLog.INSTANCE.d(TAG, "BottomNavigationView layout changed {" + i + ", " + i2 + "} {" + (i3 - i) + ", " + (i4 - i2) + "}");
    }

    public static final boolean _init_$lambda$23(TabsHost tabsHost, MenuItem item) {
        String tabKey;
        TabScreen tabScreen;
        Intrinsics.checkNotNullParameter(item, "item");
        RNSLog.INSTANCE.d(TAG, "Item selected " + item);
        TabScreenFragment fragmentForMenuItemId = tabsHost.getFragmentForMenuItemId(item.getItemId());
        boolean zHandleRepeatedTabSelection = Intrinsics.areEqual(fragmentForMenuItemId, tabsHost.getCurrentFocusedTab()) ? tabsHost.specialEffectsHandler.handleRepeatedTabSelection() : false;
        if (fragmentForMenuItemId == null || (tabScreen = fragmentForMenuItemId.getTabScreen()) == null || (tabKey = tabScreen.getTabKey()) == null) {
            tabKey = "undefined";
        }
        tabsHost.getEventEmitter$react_native_screens_release().emitOnNativeFocusChange(tabKey, item.getItemId(), zHandleRepeatedTabSelection);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        RNSLog.INSTANCE.d(TAG, "TabsHost [" + getId() + "] attached to window");
        super.onAttachedToWindow();
        FragmentManager fragmentManagerFindFragmentManagerForView = FragmentManagerHelper.INSTANCE.findFragmentManagerForView(this);
        if (fragmentManagerFindFragmentManagerForView != null) {
            this.fragmentManager = fragmentManagerFindFragmentManagerForView;
            ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
            containerUpdateCoordinator.invalidateAll();
            containerUpdateCoordinator.runContainerUpdate();
            return;
        }
        throw new IllegalStateException("[RNScreens] Nullish fragment manager - can't run container operations".toString());
    }

    public final void mountReactSubviewAt$react_native_screens_release(TabScreen tabScreen, int i) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        if (i >= this.bottomNavigationView.getMaxItemCount()) {
            throw new IllegalArgumentException(("[RNScreens] Attempt to insert TabScreen at index " + i + "; BottomNavigationView supports at most " + this.bottomNavigationView.getMaxItemCount() + " items").toString());
        }
        this.tabScreenFragments.add(i, new TabScreenFragment(tabScreen));
        tabScreen.setTabScreenDelegate$react_native_screens_release(this);
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    public final void unmountReactSubviewAt$react_native_screens_release(int i) {
        this.tabScreenFragments.remove(i).getTabScreen().setTabScreenDelegate$react_native_screens_release(null);
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    public static final boolean unmountReactSubview$lambda$30(TabScreen tabScreen, TabScreenFragment it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getTabScreen() == tabScreen;
    }

    public static final boolean unmountReactSubview$lambda$31(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public final void unmountReactSubview$react_native_screens_release(final TabScreen reactSubview) {
        Intrinsics.checkNotNullParameter(reactSubview, "reactSubview");
        List<TabScreenFragment> list = this.tabScreenFragments;
        final Function1 function1 = new Function1() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TabsHost.unmountReactSubview$lambda$30(reactSubview, (TabScreenFragment) obj));
            }
        };
        Boolean boolValueOf = Boolean.valueOf(list.removeIf(new Predicate() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TabsHost.unmountReactSubview$lambda$31(function1, obj);
            }
        }));
        if (!boolValueOf.booleanValue()) {
            boolValueOf = null;
        }
        if (boolValueOf != null) {
            boolValueOf.booleanValue();
            reactSubview.setTabScreenDelegate$react_native_screens_release(null);
            ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
            containerUpdateCoordinator.invalidateAll();
            containerUpdateCoordinator.postContainerUpdateIfNeeded();
        }
    }

    public final void unmountAllReactSubviews$react_native_screens_release() {
        Iterator<T> it = this.tabScreenFragments.iterator();
        while (it.hasNext()) {
            ((TabScreenFragment) it.next()).getTabScreen().setTabScreenDelegate$react_native_screens_release(null);
        }
        this.tabScreenFragments.clear();
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public void onTabFocusChangedFromJS(TabScreen tabScreen, boolean isFocused) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        ContainerUpdateCoordinator containerUpdateCoordinator = this.containerUpdateCoordinator;
        containerUpdateCoordinator.invalidateAll();
        containerUpdateCoordinator.postContainerUpdateIfNeeded();
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public void onMenuItemAttributesChange(TabScreen tabScreen) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        MenuItem menuItemForTabScreen = getMenuItemForTabScreen(tabScreen);
        if (menuItemForTabScreen != null) {
            this.appearanceCoordinator.updateMenuItemAppearance(menuItemForTabScreen, tabScreen);
            this.a11yCoordinator.setA11yPropertiesToTabItem(menuItemForTabScreen, tabScreen);
        }
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public TabScreenFragment getFragmentForTabScreen(TabScreen tabScreen) {
        Object next;
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        Iterator<T> it = this.tabScreenFragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((TabScreenFragment) next).getTabScreen() == tabScreen) {
                break;
            }
        }
        return (TabScreenFragment) next;
    }

    @Override // com.swmansion.rnscreens.gamma.tabs.TabScreenDelegate
    public void onFragmentConfigurationChange(TabScreen tabScreen, Configuration config) {
        Intrinsics.checkNotNullParameter(tabScreen, "tabScreen");
        Intrinsics.checkNotNullParameter(config, "config");
        onConfigurationChanged(config);
    }

    public final void updateBottomNavigationViewAppearance() {
        RNSLog.INSTANCE.d(TAG, "updateBottomNavigationViewAppearance");
        this.appearanceCoordinator.updateTabAppearance(this);
        Integer selectedTabScreenFragmentId = getSelectedTabScreenFragmentId();
        if (selectedTabScreenFragmentId == null) {
            throw new IllegalStateException("[RNScreens] A single selected tab must be present".toString());
        }
        int iIntValue = selectedTabScreenFragmentId.intValue();
        if (this.bottomNavigationView.getSelectedItemId() != iIntValue) {
            this.bottomNavigationView.setSelectedItemId(iIntValue);
        }
        post(new Runnable() { // from class: com.swmansion.rnscreens.gamma.tabs.TabsHost$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                TabsHost.updateBottomNavigationViewAppearance$lambda$41(this.f$0);
            }
        });
    }

    public static final void updateBottomNavigationViewAppearance$lambda$41(TabsHost tabsHost) {
        tabsHost.refreshLayout();
        RNSLog.INSTANCE.d(TAG, "BottomNavigationView request layout");
    }

    public final void updateSelectedTab() {
        TabScreenFragment currentFocusedTab = getCurrentFocusedTab();
        if (getRequireFragmentManager().getFragments().size() > 1) {
            throw new IllegalStateException("[RNScreens] There can be only a single focused tab".toString());
        }
        List<Fragment> fragments = getRequireFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "getFragments(...)");
        Fragment fragment = (Fragment) CollectionsKt.firstOrNull((List) fragments);
        if (currentFocusedTab == fragment) {
            return;
        }
        FragmentTransaction reorderingAllowed = getRequireFragmentManager().beginTransaction().setReorderingAllowed(true);
        if (fragment != null) {
            reorderingAllowed.remove(fragment);
        }
        reorderingAllowed.add(this.contentView.getId(), currentFocusedTab);
        reorderingAllowed.commitNowAllowingStateLoss();
    }

    public static final void layoutCallback$lambda$44(TabsHost tabsHost, long j) {
        tabsHost.isLayoutEnqueued = false;
        tabsHost.forceSubtreeMeasureAndLayoutPass();
    }

    private final void refreshLayout() {
        if (this.isLayoutEnqueued || this.layoutCallback == null) {
            return;
        }
        this.isLayoutEnqueued = true;
        ReactChoreographer.INSTANCE.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.layoutCallback);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        refreshLayout();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig != null) {
            applyDayNightUiModeIfNeeded(newConfig.uiMode & 48);
        }
    }

    private final void applyDayNightUiModeIfNeeded(int uiMode) {
        Integer num = this.lastAppliedUiMode;
        if (num != null && uiMode == num.intValue()) {
            return;
        }
        if (uiMode == 16) {
            this.wrappedContext.setTheme(R.style.Theme_Material3_Light_NoActionBar);
        } else if (uiMode == 32) {
            this.wrappedContext.setTheme(R.style.Theme_Material3_Dark_NoActionBar);
        } else {
            this.wrappedContext.setTheme(R.style.Theme_Material3_DayNight_NoActionBar);
        }
        this.appearanceCoordinator.updateTabAppearance(this);
        this.lastAppliedUiMode = Integer.valueOf(uiMode);
    }

    private final void forceSubtreeMeasureAndLayoutPass() {
        measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight(), 1073741824));
        layout(getLeft(), getTop(), getRight(), getBottom());
    }

    private final TabScreenFragment getFragmentForMenuItemId(int itemId) {
        return (TabScreenFragment) CollectionsKt.getOrNull(this.tabScreenFragments, itemId);
    }

    private final Integer getSelectedTabScreenFragmentId() {
        if (this.tabScreenFragments.isEmpty()) {
            return null;
        }
        Iterator<TabScreenFragment> it = this.tabScreenFragments.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getTabScreen().getIsFocusedTab()) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i);
    }

    private final MenuItem getMenuItemForTabScreen(TabScreen tabScreen) {
        Iterator<TabScreenFragment> it = this.tabScreenFragments.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getTabScreen() == tabScreen) {
                break;
            }
            i++;
        }
        Integer numValueOf = Integer.valueOf(i);
        if (numValueOf.intValue() == -1) {
            numValueOf = null;
        }
        if (numValueOf == null) {
            return null;
        }
        return this.bottomNavigationView.getMenu().findItem(numValueOf.intValue());
    }

    @Override // com.swmansion.rnscreens.safearea.SafeAreaProvider
    public void setOnInterfaceInsetsChangeListener(SafeAreaView safeAreaView) {
        Intrinsics.checkNotNullParameter(safeAreaView, "listener");
        if (this.interfaceInsetsChangeListener == null) {
            this.bottomNavigationView.addOnLayoutChangeListener(this);
        }
        this.interfaceInsetsChangeListener = safeAreaView;
    }

    @Override // com.swmansion.rnscreens.safearea.SafeAreaProvider
    public void removeOnInterfaceInsetsChangeListener(SafeAreaView safeAreaView) {
        Intrinsics.checkNotNullParameter(safeAreaView, "listener");
        if (Intrinsics.areEqual(this.interfaceInsetsChangeListener, safeAreaView)) {
            this.interfaceInsetsChangeListener = null;
            this.bottomNavigationView.removeOnLayoutChangeListener(this);
        }
    }

    @Override // com.swmansion.rnscreens.safearea.SafeAreaProvider
    public EdgeInsets getInterfaceInsets() {
        return new EdgeInsets(0.0f, 0.0f, 0.0f, this.bottomNavigationView.getHeight());
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= 30) {
            return super.dispatchApplyWindowInsets(insets);
        }
        if (!(insets != null ? insets.isConsumed() : true)) {
            Iterator<View> it = ViewGroupKt.getChildren(this).iterator();
            while (it.hasNext()) {
                it.next().dispatchApplyWindowInsets(insets);
            }
        }
        return insets;
    }

    public final void onViewManagerAddEventEmitters$react_native_screens_release() {
        if (getId() == -1) {
            throw new IllegalStateException("[RNScreens] TabsHost must have its tag set when registering event emitters".toString());
        }
        setEventEmitter$react_native_screens_release(new TabsHostEventEmitter(this.reactContext, getId()));
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int left, int top, int i, int i2, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (!(view instanceof BottomNavigationView)) {
            throw new IllegalArgumentException(("[RNScreens] TabsHost's onLayoutChange expects BottomNavigationView, received " + view + " instead").toString());
        }
        int i3 = i2 - top;
        if (i3 != oldBottom - oldTop) {
            updateInterfaceInsets(Integer.valueOf(i3));
        }
    }

    static /* synthetic */ void updateInterfaceInsets$default(TabsHost tabsHost, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        tabsHost.updateInterfaceInsets(num);
    }

    private final void updateInterfaceInsets(Integer newHeight) {
        int iIntValue = getTabBarHidden() ? 0 : newHeight != null ? newHeight.intValue() : this.bottomNavigationView.getHeight();
        SafeAreaView safeAreaView = this.interfaceInsetsChangeListener;
        if (safeAreaView != null) {
            safeAreaView.onInterfaceInsetsChange(new EdgeInsets(0.0f, 0.0f, 0.0f, iIntValue));
        }
    }
}
