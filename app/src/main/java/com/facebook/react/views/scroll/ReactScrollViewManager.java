package com.facebook.react.views.scroll;

import android.view.View;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.views.scroll.MaintainVisibleScrollPositionHelper;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactScrollViewManager.kt */
/* JADX INFO: loaded from: classes.dex */
@ReactModule(name = ReactScrollViewManager.REACT_CLASS)
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0017\u0018\u0000 ^2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001^B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0014J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0013H\u0007J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0017H\u0007J\u001a\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007J\u001a\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010 \u001a\u0004\u0018\u00010\rH\u0007J\u0018\u0010!\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0013H\u0007J\u0018\u0010#\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0013H\u0007J\u0018\u0010%\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u0013H\u0007J\u0018\u0010'\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0013H\u0007J\u001a\u0010)\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010*\u001a\u0004\u0018\u00010\rH\u0007J\u0018\u0010+\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u0013H\u0007J\u0018\u0010-\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010.\u001a\u00020/H\u0007J\u001a\u00100\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\rH\u0017J\u001a\u00101\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0016\u00102\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020/\u0018\u000103H\u0016J\"\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00022\u0006\u00106\u001a\u00020/2\b\u00107\u001a\u0004\u0018\u00010\u001eH\u0017J\"\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00022\u0006\u00106\u001a\u00020\r2\b\u00107\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u00108\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u0002H\u0016J\u0018\u00109\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00022\u0006\u0010:\u001a\u00020;H\u0016J\"\u0010<\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010=\u001a\u00020/2\u0006\u0010>\u001a\u00020\u0017H\u0007J\u001c\u0010?\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010@\u001a\u0004\u0018\u00010\rH\u0007J\"\u0010A\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010=\u001a\u00020/2\u0006\u0010B\u001a\u00020\u0017H\u0007J)\u0010C\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010=\u001a\u00020/2\b\u0010.\u001a\u0004\u0018\u00010/H\u0007¢\u0006\u0002\u0010DJ\u001a\u0010E\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010F\u001a\u0004\u0018\u00010\rH\u0007J\u0018\u0010G\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u00022\u0006\u0010:\u001a\u00020HH\u0016J\u0018\u0010I\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010J\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020KH\u0007J\u001a\u0010L\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010MH\u0007J\u001a\u0010N\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010MH\u0007J\"\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0016J\u0016\u0010U\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020P\u0018\u000103H\u0016J\u001a\u0010V\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010W\u001a\u0004\u0018\u00010\rH\u0007J\u0018\u0010X\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010Y\u001a\u00020/H\u0007J\u001a\u0010Z\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010[\u001a\u00020\u0013H\u0007J\u0018\u0010\\\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010]\u001a\u00020\u0013H\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/facebook/react/views/scroll/ReactScrollView;", "Lcom/facebook/react/views/scroll/ReactScrollViewCommandHelper$ScrollCommandHandler;", "fpsListener", "Lcom/facebook/react/views/scroll/FpsListener;", "<init>", "(Lcom/facebook/react/views/scroll/FpsListener;)V", "prepareToRecycleView", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "view", "getName", "", "createViewInstance", "context", "setScrollEnabled", "", "value", "", "setShowsVerticalScrollIndicator", "setDecelerationRate", "decelerationRate", "", "setDisableIntervalMomentum", "disableIntervalMomentum", "setSnapToInterval", "snapToInterval", "setSnapToOffsets", "snapToOffsets", "Lcom/facebook/react/bridge/ReadableArray;", "setSnapToAlignment", "alignment", "setSnapToStart", "snapToStart", "setSnapToEnd", "snapToEnd", "setRemoveClippedSubviews", ReactClippingViewGroupHelper.PROP_REMOVE_CLIPPED_SUBVIEWS, "setSendMomentumEvents", "sendMomentumEvents", "setScrollPerfTag", "scrollPerfTag", "setPagingEnabled", "pagingEnabled", "setBottomFillColor", "color", "", "setOverScrollMode", "setNestedScrollEnabled", "getCommandsMap", "", "receiveCommand", "scrollView", "commandId", "args", "flashScrollIndicators", "scrollTo", "data", "Lcom/facebook/react/views/scroll/ReactScrollViewCommandHelper$ScrollToCommandData;", "setBorderRadius", FirebaseAnalytics.Param.INDEX, ViewProps.BORDER_RADIUS, "setBorderStyle", "borderStyle", "setBorderWidth", "width", "setBorderColor", "(Lcom/facebook/react/views/scroll/ReactScrollView;ILjava/lang/Integer;)V", "setOverflow", ViewProps.OVERFLOW, "scrollToEnd", "Lcom/facebook/react/views/scroll/ReactScrollViewCommandHelper$ScrollToEndCommandData;", "setPersistentScrollbar", "setFadingEdgeLength", "Lcom/facebook/react/bridge/Dynamic;", "setContentOffset", "Lcom/facebook/react/bridge/ReadableMap;", "setMaintainVisibleContentPosition", "updateState", "", "props", "Lcom/facebook/react/uimanager/ReactStylesDiffMap;", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "getExportedCustomDirectEventTypeConstants", "setPointerEvents", "pointerEventsStr", "setScrollEventThrottle", "scrollEventThrottle", "setHorizontal", "horizontal", "setIsInvertedVirtualizedList", "applyFix", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class ReactScrollViewManager extends ViewGroupManager<ReactScrollView> implements ReactScrollViewCommandHelper.ScrollCommandHandler<ReactScrollView> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String REACT_CLASS = "RCTScrollView";
    private final FpsListener fpsListener;

    /* JADX INFO: compiled from: ReactScrollViewManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.Map.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ReactScrollViewManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @ReactProp(name = "horizontal")
    public final void setHorizontal(ReactScrollView view, boolean horizontal) {
    }

    public /* synthetic */ ReactScrollViewManager(FpsListener fpsListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : fpsListener);
    }

    public ReactScrollViewManager(FpsListener fpsListener) {
        super(null, 1, null);
        this.fpsListener = fpsListener;
        if (ReactNativeFeatureFlags.enableViewRecyclingForScrollView()) {
            setupViewRecycling();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public ReactScrollView prepareToRecycleView(ThemedReactContext reactContext, ReactScrollView view) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        ReactScrollView reactScrollView = (ReactScrollView) super.prepareToRecycleView(reactContext, view);
        if (reactScrollView != null) {
            reactScrollView.recycleView();
        }
        return reactScrollView;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactScrollView createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ReactScrollView(context, this.fpsListener);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public final void setScrollEnabled(ReactScrollView view, boolean value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setScrollEnabled(value);
        view.setFocusable(value);
    }

    @ReactProp(defaultBoolean = true, name = "showsVerticalScrollIndicator")
    public final void setShowsVerticalScrollIndicator(ReactScrollView view, boolean value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setVerticalScrollBarEnabled(value);
    }

    @ReactProp(name = "decelerationRate")
    public final void setDecelerationRate(ReactScrollView view, float decelerationRate) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setDecelerationRate(decelerationRate);
    }

    @ReactProp(name = "disableIntervalMomentum")
    public final void setDisableIntervalMomentum(ReactScrollView view, boolean disableIntervalMomentum) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setDisableIntervalMomentum(disableIntervalMomentum);
    }

    @ReactProp(name = "snapToInterval")
    public final void setSnapToInterval(ReactScrollView view, float snapToInterval) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setSnapInterval((int) (snapToInterval * PixelUtil.getDisplayMetricDensity()));
    }

    @ReactProp(name = "snapToOffsets")
    public final void setSnapToOffsets(ReactScrollView view, ReadableArray snapToOffsets) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (snapToOffsets == null || snapToOffsets.size() == 0) {
            view.setSnapOffsets(null);
            return;
        }
        float displayMetricDensity = PixelUtil.getDisplayMetricDensity();
        ArrayList arrayList = new ArrayList();
        int size = snapToOffsets.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(Integer.valueOf((int) (snapToOffsets.getDouble(i) * ((double) displayMetricDensity))));
        }
        view.setSnapOffsets(arrayList);
    }

    @ReactProp(name = "snapToAlignment")
    public final void setSnapToAlignment(ReactScrollView view, String alignment) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setSnapToAlignment(ReactScrollViewHelper.parseSnapToAlignment(alignment));
    }

    @ReactProp(name = "snapToStart")
    public final void setSnapToStart(ReactScrollView view, boolean snapToStart) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setSnapToStart(snapToStart);
    }

    @ReactProp(name = "snapToEnd")
    public final void setSnapToEnd(ReactScrollView view, boolean snapToEnd) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setSnapToEnd(snapToEnd);
    }

    @ReactProp(name = ReactClippingViewGroupHelper.PROP_REMOVE_CLIPPED_SUBVIEWS)
    public final void setRemoveClippedSubviews(ReactScrollView view, boolean removeClippedSubviews) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    @ReactProp(name = "sendMomentumEvents")
    public final void setSendMomentumEvents(ReactScrollView view, boolean sendMomentumEvents) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setSendMomentumEvents(sendMomentumEvents);
    }

    @ReactProp(name = "scrollPerfTag")
    public final void setScrollPerfTag(ReactScrollView view, String scrollPerfTag) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setScrollPerfTag(scrollPerfTag);
    }

    @ReactProp(name = "pagingEnabled")
    public final void setPagingEnabled(ReactScrollView view, boolean pagingEnabled) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setPagingEnabled(pagingEnabled);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public final void setBottomFillColor(ReactScrollView view, int color) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setEndFillColor(color);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(ReactScrollView view, String value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setOverScrollMode(ReactScrollViewHelper.parseOverScrollMode(value));
    }

    @ReactProp(name = "nestedScrollEnabled")
    public final void setNestedScrollEnabled(ReactScrollView view, boolean value) {
        if (view != null) {
            ViewCompat.setNestedScrollingEnabled(view, value);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return ReactScrollViewCommandHelper.INSTANCE.getCommandsMap();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Deprecated(message = "ReceiveCommand with an int commandId param is deprecated. Use the overload where commandId is a string.", replaceWith = @ReplaceWith(expression = "receiveCommand(scrollView, commandId, args)", imports = {}))
    public void receiveCommand(ReactScrollView scrollView, int commandId, ReadableArray args) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        ReactScrollViewCommandHelper.INSTANCE.receiveCommand(this, scrollView, commandId, args);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactScrollView scrollView, String commandId, ReadableArray args) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        Intrinsics.checkNotNullParameter(commandId, "commandId");
        ReactScrollViewCommandHelper.INSTANCE.receiveCommand(this, scrollView, commandId, args);
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollCommandHandler
    public void flashScrollIndicators(ReactScrollView scrollView) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        scrollView.flashScrollIndicators();
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollCommandHandler
    public void scrollTo(ReactScrollView scrollView, ReactScrollViewCommandHelper.ScrollToCommandData data) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        Intrinsics.checkNotNullParameter(data, "data");
        scrollView.abortAnimation();
        if (data.mAnimated) {
            scrollView.reactSmoothScrollTo(data.mDestX, data.mDestY);
        } else {
            scrollView.scrollTo(data.mDestX, data.mDestY);
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_RADIUS, ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS})
    public final void setBorderRadius(ReactScrollView view, int index, float borderRadius) {
        if (view != null) {
            BackgroundStyleApplicator.setBorderRadius(view, BorderRadiusProp.getEntries().get(index), Float.isNaN(borderRadius) ? null : new LengthPercentage(borderRadius, LengthPercentageType.POINT));
        }
    }

    @ReactProp(name = "borderStyle")
    public final void setBorderStyle(ReactScrollView view, String borderStyle) {
        if (view != null) {
            BackgroundStyleApplicator.setBorderStyle(view, borderStyle == null ? null : BorderStyle.INSTANCE.fromString(borderStyle));
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH})
    public final void setBorderWidth(ReactScrollView view, int index, float width) {
        if (view != null) {
            BackgroundStyleApplicator.setBorderWidth(view, LogicalEdge.getEntries().get(index), Float.valueOf(width));
        }
    }

    @ReactPropGroup(customType = "Color", names = {ViewProps.BORDER_COLOR, ViewProps.BORDER_LEFT_COLOR, ViewProps.BORDER_RIGHT_COLOR, ViewProps.BORDER_TOP_COLOR, ViewProps.BORDER_BOTTOM_COLOR})
    public final void setBorderColor(ReactScrollView view, int index, Integer color) {
        if (view != null) {
            BackgroundStyleApplicator.setBorderColor(view, LogicalEdge.ALL, color);
        }
    }

    @ReactProp(name = ViewProps.OVERFLOW)
    public final void setOverflow(ReactScrollView view, String overflow) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setOverflow(overflow);
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewCommandHelper.ScrollCommandHandler
    public void scrollToEnd(ReactScrollView scrollView, ReactScrollViewCommandHelper.ScrollToEndCommandData data) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        Intrinsics.checkNotNullParameter(data, "data");
        View childAt = scrollView.getChildAt(0);
        if (childAt == null) {
            throw new RetryableMountingLayerException("scrollToEnd called on ScrollView without child");
        }
        int height = childAt.getHeight() + scrollView.getPaddingBottom();
        scrollView.abortAnimation();
        if (data.mAnimated) {
            scrollView.reactSmoothScrollTo(scrollView.getScrollX(), height);
        } else {
            scrollView.scrollTo(scrollView.getScrollX(), height);
        }
    }

    @ReactProp(name = "persistentScrollbar")
    public final void setPersistentScrollbar(ReactScrollView view, boolean value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setScrollbarFadingEnabled(!value);
    }

    @ReactProp(name = "fadingEdgeLength")
    public final void setFadingEdgeLength(ReactScrollView view, Dynamic value) {
        ReadableMap readableMapAsMap;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(value, "value");
        int i = WhenMappings.$EnumSwitchMapping$0[value.getType().ordinal()];
        if (i == 1) {
            view.setFadingEdgeLengthStart(value.asInt());
            view.setFadingEdgeLengthEnd(value.asInt());
        } else if (i == 2 && (readableMapAsMap = value.asMap()) != null) {
            int i2 = (!readableMapAsMap.hasKey(ViewProps.START) || readableMapAsMap.getInt(ViewProps.START) <= 0) ? 0 : readableMapAsMap.getInt(ViewProps.START);
            int i3 = (!readableMapAsMap.hasKey(ViewProps.END) || readableMapAsMap.getInt(ViewProps.END) <= 0) ? 0 : readableMapAsMap.getInt(ViewProps.END);
            view.setFadingEdgeLengthStart(i2);
            view.setFadingEdgeLengthEnd(i3);
        }
        if (view.getFadingEdgeLengthStart() > 0 || view.getFadingEdgeLengthEnd() > 0) {
            view.setVerticalFadingEdgeEnabled(true);
            view.setFadingEdgeLength(Math.round(PixelUtil.INSTANCE.dpToPx(Math.max(view.getFadingEdgeLengthStart(), view.getFadingEdgeLengthEnd()))));
        } else {
            view.setVerticalFadingEdgeEnabled(false);
            view.setFadingEdgeLength(0);
        }
    }

    @ReactProp(customType = "Point", name = "contentOffset")
    public final void setContentOffset(ReactScrollView view, ReadableMap value) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setContentOffset(value);
    }

    @ReactProp(name = "maintainVisibleContentPosition")
    public final void setMaintainVisibleContentPosition(ReactScrollView view, ReadableMap value) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (value != null) {
            view.setMaintainVisibleContentPosition(MaintainVisibleScrollPositionHelper.Config.INSTANCE.fromReadableMap(value));
        } else {
            view.setMaintainVisibleContentPosition(null);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Object updateState(ReactScrollView view, ReactStylesDiffMap props, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(props, "props");
        Intrinsics.checkNotNullParameter(stateWrapper, "stateWrapper");
        view.setStateWrapper(stateWrapper);
        if (!ReactNativeFeatureFlags.enableViewCulling() && !ReactNativeFeatureFlags.useTraitHiddenOnAndroid()) {
            return null;
        }
        ReactScrollViewHelper.loadFabricScrollState$ReactAndroid_release(view, stateWrapper);
        return null;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        HashMap exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap();
        }
        exportedCustomDirectEventTypeConstants.putAll(INSTANCE.createExportedCustomDirectEventTypeConstants());
        return exportedCustomDirectEventTypeConstants;
    }

    @ReactProp(name = ViewProps.POINTER_EVENTS)
    public final void setPointerEvents(ReactScrollView view, String pointerEventsStr) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setPointerEvents(PointerEvents.INSTANCE.parsePointerEvents(pointerEventsStr));
    }

    @ReactProp(name = "scrollEventThrottle")
    public final void setScrollEventThrottle(ReactScrollView view, int scrollEventThrottle) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setScrollEventThrottle(scrollEventThrottle);
    }

    @ReactProp(name = "isInvertedVirtualizedList")
    public final void setIsInvertedVirtualizedList(ReactScrollView view, boolean applyFix) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (applyFix) {
            view.setVerticalScrollbarPosition(1);
        } else {
            view.setVerticalScrollbarPosition(0);
        }
    }

    /* JADX INFO: compiled from: ReactScrollViewManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/views/scroll/ReactScrollViewManager$Companion;", "", "<init>", "()V", "REACT_CLASS", "", "createExportedCustomDirectEventTypeConstants", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Object> createExportedCustomDirectEventTypeConstants() {
            return MapsKt.mapOf(TuplesKt.to(ScrollEventType.INSTANCE.getJSEventName(ScrollEventType.SCROLL), MapsKt.mapOf(TuplesKt.to("registrationName", "onScroll"))), TuplesKt.to(ScrollEventType.INSTANCE.getJSEventName(ScrollEventType.BEGIN_DRAG), MapsKt.mapOf(TuplesKt.to("registrationName", "onScrollBeginDrag"))), TuplesKt.to(ScrollEventType.INSTANCE.getJSEventName(ScrollEventType.END_DRAG), MapsKt.mapOf(TuplesKt.to("registrationName", "onScrollEndDrag"))), TuplesKt.to(ScrollEventType.INSTANCE.getJSEventName(ScrollEventType.MOMENTUM_BEGIN), MapsKt.mapOf(TuplesKt.to("registrationName", "onMomentumScrollBegin"))), TuplesKt.to(ScrollEventType.INSTANCE.getJSEventName(ScrollEventType.MOMENTUM_END), MapsKt.mapOf(TuplesKt.to("registrationName", "onMomentumScrollEnd"))));
        }
    }
}
