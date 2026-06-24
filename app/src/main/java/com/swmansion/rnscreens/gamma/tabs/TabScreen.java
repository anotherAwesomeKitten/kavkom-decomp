package com.swmansion.rnscreens.gamma.tabs;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.facebook.react.uimanager.ThemedReactContext;
import com.swmansion.rnscreens.gamma.common.FragmentProviding;
import com.swmansion.rnscreens.gamma.helpers.SystemDrawableKt;
import com.swmansion.rnscreens.utils.RNSLog;
import io.sentry.protocol.Request;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TabScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 h2\u00020\u00012\u00020\u0002:\u0001hB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0014J#\u0010Q\u001a\u00020\n\"\u0004\b\u0000\u0010R2\u0006\u0010S\u001a\u0002HR2\u0006\u0010T\u001a\u0002HRH\u0002¢\u0006\u0002\u0010UJ\b\u0010V\u001a\u00020\nH\u0014J\u0017\u0010Y\u001a\u00020\n2\b\u0010Z\u001a\u0004\u0018\u00010\u0014H\u0000¢\u0006\u0002\b[J\n\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\b\u0010^\u001a\u00020\nH\u0002J\b\u0010_\u001a\u00020\nH\u0002J\r\u0010`\u001a\u00020\nH\u0000¢\u0006\u0002\baJ\u001d\u0010b\u001a\u00020\n2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020fH\u0000¢\u0006\u0002\bgR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR(\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R/\u0010#\u001a\u0004\u0018\u00010\u001c2\b\u0010\"\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R/\u0010(\u001a\u0004\u0018\u00010\u001c2\b\u0010\"\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010'\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!R/\u0010,\u001a\u0004\u0018\u00010\u000e2\b\u0010\"\u001a\u0004\u0018\u00010\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b1\u0010'\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R/\u00102\u001a\u0004\u0018\u00010\u000e2\b\u0010\"\u001a\u0004\u0018\u00010\u000e8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010'\u001a\u0004\b3\u0010.\"\u0004\b4\u00100R/\u00106\u001a\u0004\u0018\u00010\u001c2\b\u0010\"\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b9\u0010'\u001a\u0004\b7\u0010\u001f\"\u0004\b8\u0010!R/\u0010:\u001a\u0004\u0018\u00010\u001c2\b\u0010\"\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010'\u001a\u0004\b;\u0010\u001f\"\u0004\b<\u0010!R/\u0010>\u001a\u0004\u0018\u00010\u001c2\b\u0010\"\u001a\u0004\u0018\u00010\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010'\u001a\u0004\b?\u0010\u001f\"\u0004\b@\u0010!R/\u0010C\u001a\u0004\u0018\u00010B2\b\u0010\"\u001a\u0004\u0018\u00010B8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010'\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u001a\u0010I\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001a\u0010N\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010K\"\u0004\bP\u0010MR$\u0010W\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010K\"\u0004\bX\u0010M¨\u0006i"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/TabScreen;", "Landroid/view/ViewGroup;", "Lcom/swmansion/rnscreens/gamma/common/FragmentProviding;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "<init>", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "getReactContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "onLayout", "", "changed", "", "l", "", "t", "r", "b", "tabScreenDelegate", "Ljava/lang/ref/WeakReference;", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenDelegate;", "eventEmitter", "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenEventEmitter;", "getEventEmitter$react_native_screens_release", "()Lcom/swmansion/rnscreens/gamma/tabs/TabScreenEventEmitter;", "setEventEmitter$react_native_screens_release", "(Lcom/swmansion/rnscreens/gamma/tabs/TabScreenEventEmitter;)V", "value", "", "tabKey", "getTabKey", "()Ljava/lang/String;", "setTabKey", "(Ljava/lang/String;)V", "<set-?>", "tabTitle", "getTabTitle", "setTabTitle", "tabTitle$delegate", "Lkotlin/properties/ReadWriteProperty;", "badgeValue", "getBadgeValue", "setBadgeValue", "badgeValue$delegate", "tabBarItemBadgeTextColor", "getTabBarItemBadgeTextColor", "()Ljava/lang/Integer;", "setTabBarItemBadgeTextColor", "(Ljava/lang/Integer;)V", "tabBarItemBadgeTextColor$delegate", "tabBarItemBadgeBackgroundColor", "getTabBarItemBadgeBackgroundColor", "setTabBarItemBadgeBackgroundColor", "tabBarItemBadgeBackgroundColor$delegate", "tabBarItemTestID", "getTabBarItemTestID", "setTabBarItemTestID", "tabBarItemTestID$delegate", "tabBarItemAccessibilityLabel", "getTabBarItemAccessibilityLabel", "setTabBarItemAccessibilityLabel", "tabBarItemAccessibilityLabel$delegate", "drawableIconResourceName", "getDrawableIconResourceName", "setDrawableIconResourceName", "drawableIconResourceName$delegate", "Landroid/graphics/drawable/Drawable;", "icon", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "icon$delegate", "shouldUseRepeatedTabSelectionScrollToTopSpecialEffect", "getShouldUseRepeatedTabSelectionScrollToTopSpecialEffect", "()Z", "setShouldUseRepeatedTabSelectionScrollToTopSpecialEffect", "(Z)V", "shouldUseRepeatedTabSelectionPopToRootSpecialEffect", "getShouldUseRepeatedTabSelectionPopToRootSpecialEffect", "setShouldUseRepeatedTabSelectionPopToRootSpecialEffect", "updateMenuItemAttributesIfNeeded", "T", "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;)V", "onAttachedToWindow", "isFocusedTab", "setFocusedTab", "setTabScreenDelegate", "delegate", "setTabScreenDelegate$react_native_screens_release", "getAssociatedFragment", "Landroidx/fragment/app/Fragment;", "onTabFocusChangedFromJS", "onMenuItemAttributesChange", "onViewManagerAddEventEmitters", "onViewManagerAddEventEmitters$react_native_screens_release", "onFragmentConfigurationChange", Request.JsonKeys.FRAGMENT, "Lcom/swmansion/rnscreens/gamma/tabs/TabScreenFragment;", "config", "Landroid/content/res/Configuration;", "onFragmentConfigurationChange$react_native_screens_release", "Companion", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TabScreen extends ViewGroup implements FragmentProviding {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabTitle", "getTabTitle()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "badgeValue", "getBadgeValue()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabBarItemBadgeTextColor", "getTabBarItemBadgeTextColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabBarItemBadgeBackgroundColor", "getTabBarItemBadgeBackgroundColor()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabBarItemTestID", "getTabBarItemTestID()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "tabBarItemAccessibilityLabel", "getTabBarItemAccessibilityLabel()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "drawableIconResourceName", "getDrawableIconResourceName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TabScreen.class, "icon", "getIcon()Landroid/graphics/drawable/Drawable;", 0))};
    public static final String TAG = "TabScreen";

    /* JADX INFO: renamed from: badgeValue$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty badgeValue;

    /* JADX INFO: renamed from: drawableIconResourceName$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty drawableIconResourceName;
    public TabScreenEventEmitter eventEmitter;

    /* JADX INFO: renamed from: icon$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty icon;
    private boolean isFocusedTab;
    private final ThemedReactContext reactContext;
    private boolean shouldUseRepeatedTabSelectionPopToRootSpecialEffect;
    private boolean shouldUseRepeatedTabSelectionScrollToTopSpecialEffect;

    /* JADX INFO: renamed from: tabBarItemAccessibilityLabel$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemAccessibilityLabel;

    /* JADX INFO: renamed from: tabBarItemBadgeBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemBadgeBackgroundColor;

    /* JADX INFO: renamed from: tabBarItemBadgeTextColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemBadgeTextColor;

    /* JADX INFO: renamed from: tabBarItemTestID$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabBarItemTestID;
    private String tabKey;
    private WeakReference<TabScreenDelegate> tabScreenDelegate;

    /* JADX INFO: renamed from: tabTitle$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty tabTitle;

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabScreen(ThemedReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        final Object obj = null;
        this.tabScreenDelegate = new WeakReference<>(null);
        Delegates delegates = Delegates.INSTANCE;
        this.tabTitle = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates2 = Delegates.INSTANCE;
        this.badgeValue = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates3 = Delegates.INSTANCE;
        this.tabBarItemBadgeTextColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates4 = Delegates.INSTANCE;
        this.tabBarItemBadgeBackgroundColor = new ObservableProperty<Integer>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates5 = Delegates.INSTANCE;
        this.tabBarItemTestID = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates6 = Delegates.INSTANCE;
        this.tabBarItemAccessibilityLabel = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        Delegates delegates7 = Delegates.INSTANCE;
        this.drawableIconResourceName = new ObservableProperty<String>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$7
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, String oldValue, String newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                String str = newValue;
                if (Intrinsics.areEqual(str, oldValue)) {
                    return;
                }
                TabScreen tabScreen = this;
                tabScreen.setIcon(SystemDrawableKt.getSystemDrawableResource(tabScreen.getReactContext(), str));
            }
        };
        Delegates delegates8 = Delegates.INSTANCE;
        this.icon = new ObservableProperty<Drawable>(obj) { // from class: com.swmansion.rnscreens.gamma.tabs.TabScreen$special$$inlined$observable$8
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Drawable oldValue, Drawable newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                TabScreen tabScreen = this;
                tabScreen.updateMenuItemAttributesIfNeeded(oldValue, newValue);
            }
        };
        this.shouldUseRepeatedTabSelectionScrollToTopSpecialEffect = true;
        this.shouldUseRepeatedTabSelectionPopToRootSpecialEffect = true;
    }

    public final TabScreenEventEmitter getEventEmitter$react_native_screens_release() {
        TabScreenEventEmitter tabScreenEventEmitter = this.eventEmitter;
        if (tabScreenEventEmitter != null) {
            return tabScreenEventEmitter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("eventEmitter");
        return null;
    }

    public final void setEventEmitter$react_native_screens_release(TabScreenEventEmitter tabScreenEventEmitter) {
        Intrinsics.checkNotNullParameter(tabScreenEventEmitter, "<set-?>");
        this.eventEmitter = tabScreenEventEmitter;
    }

    public final String getTabKey() {
        return this.tabKey;
    }

    public final void setTabKey(String str) {
        if (str != null && StringsKt.isBlank(str)) {
            str = null;
        }
        this.tabKey = str;
    }

    public final String getTabTitle() {
        return (String) this.tabTitle.getValue(this, $$delegatedProperties[0]);
    }

    public final void setTabTitle(String str) {
        this.tabTitle.setValue(this, $$delegatedProperties[0], str);
    }

    public final String getBadgeValue() {
        return (String) this.badgeValue.getValue(this, $$delegatedProperties[1]);
    }

    public final void setBadgeValue(String str) {
        this.badgeValue.setValue(this, $$delegatedProperties[1], str);
    }

    public final Integer getTabBarItemBadgeTextColor() {
        return (Integer) this.tabBarItemBadgeTextColor.getValue(this, $$delegatedProperties[2]);
    }

    public final void setTabBarItemBadgeTextColor(Integer num) {
        this.tabBarItemBadgeTextColor.setValue(this, $$delegatedProperties[2], num);
    }

    public final Integer getTabBarItemBadgeBackgroundColor() {
        return (Integer) this.tabBarItemBadgeBackgroundColor.getValue(this, $$delegatedProperties[3]);
    }

    public final void setTabBarItemBadgeBackgroundColor(Integer num) {
        this.tabBarItemBadgeBackgroundColor.setValue(this, $$delegatedProperties[3], num);
    }

    public final String getTabBarItemTestID() {
        return (String) this.tabBarItemTestID.getValue(this, $$delegatedProperties[4]);
    }

    public final void setTabBarItemTestID(String str) {
        this.tabBarItemTestID.setValue(this, $$delegatedProperties[4], str);
    }

    public final String getTabBarItemAccessibilityLabel() {
        return (String) this.tabBarItemAccessibilityLabel.getValue(this, $$delegatedProperties[5]);
    }

    public final void setTabBarItemAccessibilityLabel(String str) {
        this.tabBarItemAccessibilityLabel.setValue(this, $$delegatedProperties[5], str);
    }

    public final String getDrawableIconResourceName() {
        return (String) this.drawableIconResourceName.getValue(this, $$delegatedProperties[6]);
    }

    public final void setDrawableIconResourceName(String str) {
        this.drawableIconResourceName.setValue(this, $$delegatedProperties[6], str);
    }

    public final Drawable getIcon() {
        return (Drawable) this.icon.getValue(this, $$delegatedProperties[7]);
    }

    public final void setIcon(Drawable drawable) {
        this.icon.setValue(this, $$delegatedProperties[7], drawable);
    }

    public final boolean getShouldUseRepeatedTabSelectionScrollToTopSpecialEffect() {
        return this.shouldUseRepeatedTabSelectionScrollToTopSpecialEffect;
    }

    public final void setShouldUseRepeatedTabSelectionScrollToTopSpecialEffect(boolean z) {
        this.shouldUseRepeatedTabSelectionScrollToTopSpecialEffect = z;
    }

    public final boolean getShouldUseRepeatedTabSelectionPopToRootSpecialEffect() {
        return this.shouldUseRepeatedTabSelectionPopToRootSpecialEffect;
    }

    public final void setShouldUseRepeatedTabSelectionPopToRootSpecialEffect(boolean z) {
        this.shouldUseRepeatedTabSelectionPopToRootSpecialEffect = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> void updateMenuItemAttributesIfNeeded(T oldValue, T newValue) {
        if (Intrinsics.areEqual(newValue, oldValue)) {
            return;
        }
        onMenuItemAttributesChange();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        RNSLog.INSTANCE.d(TAG, "TabScreen [" + getId() + "] attached to window");
        super.onAttachedToWindow();
    }

    /* JADX INFO: renamed from: isFocusedTab, reason: from getter */
    public final boolean getIsFocusedTab() {
        return this.isFocusedTab;
    }

    public final void setFocusedTab(boolean z) {
        if (this.isFocusedTab != z) {
            this.isFocusedTab = z;
            onTabFocusChangedFromJS();
        }
    }

    public final void setTabScreenDelegate$react_native_screens_release(TabScreenDelegate delegate) {
        this.tabScreenDelegate = new WeakReference<>(delegate);
    }

    @Override // com.swmansion.rnscreens.gamma.common.FragmentProviding
    public Fragment getAssociatedFragment() {
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            return tabScreenDelegate.getFragmentForTabScreen(this);
        }
        return null;
    }

    private final void onTabFocusChangedFromJS() {
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            tabScreenDelegate.onTabFocusChangedFromJS(this, this.isFocusedTab);
        }
    }

    private final void onMenuItemAttributesChange() {
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            tabScreenDelegate.onMenuItemAttributesChange(this);
        }
    }

    public final void onViewManagerAddEventEmitters$react_native_screens_release() {
        if (getId() == -1) {
            throw new IllegalStateException("[RNScreens] TabScreen must have its tag set when registering event emitters".toString());
        }
        setEventEmitter$react_native_screens_release(new TabScreenEventEmitter(this.reactContext, getId()));
    }

    public final void onFragmentConfigurationChange$react_native_screens_release(TabScreenFragment fragment, Configuration config) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(config, "config");
        TabScreenDelegate tabScreenDelegate = this.tabScreenDelegate.get();
        if (tabScreenDelegate != null) {
            tabScreenDelegate.onFragmentConfigurationChange(this, config);
        }
    }
}
