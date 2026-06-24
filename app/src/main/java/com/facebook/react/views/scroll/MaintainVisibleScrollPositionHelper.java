package com.facebook.react.views.scroll;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.scroll.ReactScrollViewHelper.HasSmoothScroll;
import com.facebook.react.views.view.ReactViewGroup;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MaintainVisibleScrollPositionHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\b\u0000\u0018\u0000*\u0010\b\u0000\u0010\u0001*\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00032\u00020\u0004:\u0001+B\u0017\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020 J\u0006\u0010\"\u001a\u00020 J\b\u0010#\u001a\u00020 H\u0002J\b\u0010$\u001a\u00020 H\u0002J\u0010\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u001cH\u0016J\u0010\u0010'\u001a\u00020 2\u0006\u0010&\u001a\u00020\u001cH\u0016J\u0010\u0010(\u001a\u00020 2\u0006\u0010&\u001a\u00020\u001cH\u0016J\u0010\u0010)\u001a\u00020 2\u0006\u0010&\u001a\u00020\u001cH\u0016J\u0010\u0010*\u001a\u00020 2\u0006\u0010&\u001a\u00020\u001cH\u0016R\u0010\u0010\u0005\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006,"}, d2 = {"Lcom/facebook/react/views/scroll/MaintainVisibleScrollPositionHelper;", "ScrollViewT", "Lcom/facebook/react/views/scroll/ReactScrollViewHelper$HasSmoothScroll;", "Landroid/view/ViewGroup;", "Lcom/facebook/react/bridge/UIManagerListener;", "scrollView", "horizontal", "", "<init>", "(Landroid/view/ViewGroup;Z)V", "Landroid/view/ViewGroup;", "config", "Lcom/facebook/react/views/scroll/MaintainVisibleScrollPositionHelper$Config;", "getConfig", "()Lcom/facebook/react/views/scroll/MaintainVisibleScrollPositionHelper$Config;", "setConfig", "(Lcom/facebook/react/views/scroll/MaintainVisibleScrollPositionHelper$Config;)V", "firstVisibleViewRef", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "prevFirstVisibleFrame", "Landroid/graphics/Rect;", "isListening", "contentView", "Lcom/facebook/react/views/view/ReactViewGroup;", "getContentView", "()Lcom/facebook/react/views/view/ReactViewGroup;", "uIManager", "Lcom/facebook/react/bridge/UIManager;", "getUIManager", "()Lcom/facebook/react/bridge/UIManager;", ViewProps.START, "", "stop", "updateScrollPosition", "updateScrollPositionInternal", "computeTargetView", "willDispatchViewUpdates", "uiManager", "willMountItems", "didMountItems", "didDispatchMountItems", "didScheduleMountItems", "Config", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MaintainVisibleScrollPositionHelper<ScrollViewT extends ViewGroup & ReactScrollViewHelper.HasSmoothScroll> implements UIManagerListener {
    private Config config;
    private WeakReference<View> firstVisibleViewRef;
    private final boolean horizontal;
    private boolean isListening;
    private Rect prevFirstVisibleFrame;
    private final ScrollViewT scrollView;

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didDispatchMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didScheduleMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
    }

    public MaintainVisibleScrollPositionHelper(ScrollViewT scrollviewt, boolean z) {
        this.scrollView = scrollviewt;
        this.horizontal = z;
    }

    public final Config getConfig() {
        return this.config;
    }

    public final void setConfig(Config config) {
        this.config = config;
    }

    private final ReactViewGroup getContentView() {
        ScrollViewT scrollviewt = this.scrollView;
        return (ReactViewGroup) (scrollviewt != null ? scrollviewt.getChildAt(0) : null);
    }

    private final UIManager getUIManager() {
        ScrollViewT scrollviewt = this.scrollView;
        ReactContext reactContext = (ReactContext) (scrollviewt != null ? ((View) scrollviewt).getContext() : null);
        if (reactContext == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        ScrollViewT scrollviewt2 = this.scrollView;
        UIManager uIManager = UIManagerHelper.getUIManager(reactContext, ViewUtil.getUIManagerType(scrollviewt2 != null ? ((View) scrollviewt2).getId() : 0));
        if (uIManager != null) {
            return uIManager;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    /* JADX INFO: compiled from: MaintainVisibleScrollPositionHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001b\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/facebook/react/views/scroll/MaintainVisibleScrollPositionHelper$Config;", "", "minIndexForVisible", "", "autoScrollToTopThreshold", "<init>", "(ILjava/lang/Integer;)V", "getMinIndexForVisible", "()I", "getAutoScrollToTopThreshold", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Config {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final Integer autoScrollToTopThreshold;
        private final int minIndexForVisible;

        @JvmStatic
        public static final Config fromReadableMap(ReadableMap readableMap) {
            return INSTANCE.fromReadableMap(readableMap);
        }

        /* JADX INFO: compiled from: MaintainVisibleScrollPositionHelper.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/views/scroll/MaintainVisibleScrollPositionHelper$Config$Companion;", "", "<init>", "()V", "fromReadableMap", "Lcom/facebook/react/views/scroll/MaintainVisibleScrollPositionHelper$Config;", "value", "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Config fromReadableMap(ReadableMap value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return new Config(value.getInt("minIndexForVisible"), value.hasKey("autoscrollToTopThreshold") ? Integer.valueOf(value.getInt("autoscrollToTopThreshold")) : null);
            }
        }

        public Config(int i, Integer num) {
            this.minIndexForVisible = i;
            this.autoScrollToTopThreshold = num;
        }

        public final Integer getAutoScrollToTopThreshold() {
            return this.autoScrollToTopThreshold;
        }

        public final int getMinIndexForVisible() {
            return this.minIndexForVisible;
        }
    }

    public final void start() {
        if (this.isListening) {
            return;
        }
        this.isListening = true;
        getUIManager().addUIManagerEventListener(this);
    }

    public final void stop() {
        if (this.isListening) {
            this.isListening = false;
            getUIManager().removeUIManagerEventListener(this);
        }
    }

    public final void updateScrollPosition() {
        ScrollViewT scrollviewt = this.scrollView;
        if (scrollviewt == null || ViewUtil.getUIManagerType(((View) scrollviewt).getId()) == 2) {
            return;
        }
        updateScrollPositionInternal();
    }

    private final void updateScrollPositionInternal() {
        WeakReference<View> weakReference;
        Rect rect;
        View view;
        ScrollViewT scrollviewt;
        Config config = this.config;
        if (config == null || (weakReference = this.firstVisibleViewRef) == null || (rect = this.prevFirstVisibleFrame) == null || (view = weakReference.get()) == null || (scrollviewt = this.scrollView) == null) {
            return;
        }
        Rect rect2 = new Rect();
        view.getHitRect(rect2);
        if (this.horizontal) {
            int i = rect2.left - rect.left;
            if (i != 0) {
                View view2 = (View) scrollviewt;
                int scrollX = view2.getScrollX();
                ScrollViewT scrollviewt2 = scrollviewt;
                scrollviewt2.scrollToPreservingMomentum(i + scrollX, view2.getScrollY());
                this.prevFirstVisibleFrame = rect2;
                if (config.getAutoScrollToTopThreshold() == null || scrollX > config.getAutoScrollToTopThreshold().intValue()) {
                    return;
                }
                scrollviewt2.reactSmoothScrollTo(0, view2.getScrollY());
                return;
            }
            return;
        }
        int i2 = rect2.top - rect.top;
        if (i2 != 0) {
            View view3 = (View) scrollviewt;
            int scrollY = view3.getScrollY();
            ScrollViewT scrollviewt3 = scrollviewt;
            scrollviewt3.scrollToPreservingMomentum(view3.getScrollX(), i2 + scrollY);
            this.prevFirstVisibleFrame = rect2;
            if (config.getAutoScrollToTopThreshold() == null || scrollY > config.getAutoScrollToTopThreshold().intValue()) {
                return;
            }
            scrollviewt3.reactSmoothScrollTo(view3.getScrollX(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void computeTargetView() {
        ScrollViewT scrollviewt;
        ReactViewGroup contentView;
        float y;
        int height;
        Config config = this.config;
        if (config == null || (scrollviewt = this.scrollView) == null || (contentView = getContentView()) == null) {
            return;
        }
        View view = (View) scrollviewt;
        int scrollX = this.horizontal ? view.getScrollX() : view.getScrollY();
        int childCount = contentView.getChildCount();
        for (int minIndexForVisible = config.getMinIndexForVisible(); minIndexForVisible < childCount; minIndexForVisible++) {
            View childAt = contentView.getChildAt(minIndexForVisible);
            if (this.horizontal) {
                y = childAt.getX();
                height = childAt.getWidth();
            } else {
                y = childAt.getY();
                height = childAt.getHeight();
            }
            if (y + height > scrollX || minIndexForVisible == contentView.getChildCount() - 1) {
                this.firstVisibleViewRef = new WeakReference<>(childAt);
                Rect rect = new Rect();
                childAt.getHitRect(rect);
                this.prevFirstVisibleFrame = rect;
                return;
            }
        }
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void willDispatchViewUpdates(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.views.scroll.MaintainVisibleScrollPositionHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.computeTargetView();
            }
        });
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void willMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        computeTargetView();
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        updateScrollPositionInternal();
    }
}
