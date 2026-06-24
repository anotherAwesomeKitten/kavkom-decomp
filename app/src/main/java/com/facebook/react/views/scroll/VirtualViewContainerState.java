package com.facebook.react.views.scroll;

import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.facebook.common.logging.FLog;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VirtualViewContainer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b \u0018\u0000 )2\u00020\u0001:\u0001)B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0010\u0010%\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u000eH\u0016J\u0006\u0010&\u001a\u00020\"J\b\u0010'\u001a\u00020\"H\u0004J\u0014\u0010(\u001a\u00020\"2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u000eH$R\u0014\u0010\u0006\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0012X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u0012X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0014\u0010\u0019\u001a\u00020\u0012X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006*"}, d2 = {"Lcom/facebook/react/views/scroll/VirtualViewContainerState;", "", "scrollView", "Landroid/view/ViewGroup;", "<init>", "(Landroid/view/ViewGroup;)V", "prerenderRatio", "", "getPrerenderRatio", "()D", "hysteresisRatio", "getHysteresisRatio", "virtualViews", "", "Lcom/facebook/react/views/scroll/VirtualView;", "getVirtualViews", "()Ljava/util/Collection;", "emptyRect", "Landroid/graphics/Rect;", "getEmptyRect", "()Landroid/graphics/Rect;", "visibleRect", "getVisibleRect", "prerenderRect", "getPrerenderRect", "hysteresisRect", "getHysteresisRect", "onWindowFocusChangeListener", "Landroid/view/ViewTreeObserver$OnWindowFocusChangeListener;", "getOnWindowFocusChangeListener", "()Landroid/view/ViewTreeObserver$OnWindowFocusChangeListener;", "getScrollView", "()Landroid/view/ViewGroup;", "cleanup", "", "onChange", "virtualView", "remove", "updateState", "updateRects", "updateModes", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class VirtualViewContainerState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Rect emptyRect;
    private final double hysteresisRatio;
    private final Rect hysteresisRect;
    private final ViewTreeObserver.OnWindowFocusChangeListener onWindowFocusChangeListener;
    private final double prerenderRatio;
    private final Rect prerenderRect;
    private final ViewGroup scrollView;
    private final Rect visibleRect;

    @JvmStatic
    public static final VirtualViewContainerState create(ViewGroup viewGroup) {
        return INSTANCE.create(viewGroup);
    }

    protected abstract Collection<VirtualView> getVirtualViews();

    protected abstract void updateModes(VirtualView virtualView);

    protected final double getPrerenderRatio() {
        return this.prerenderRatio;
    }

    protected final double getHysteresisRatio() {
        return this.hysteresisRatio;
    }

    protected final Rect getEmptyRect() {
        return this.emptyRect;
    }

    protected final Rect getVisibleRect() {
        return this.visibleRect;
    }

    protected final Rect getPrerenderRect() {
        return this.prerenderRect;
    }

    protected final Rect getHysteresisRect() {
        return this.hysteresisRect;
    }

    protected final ViewTreeObserver.OnWindowFocusChangeListener getOnWindowFocusChangeListener() {
        return this.onWindowFocusChangeListener;
    }

    protected final ViewGroup getScrollView() {
        return this.scrollView;
    }

    /* JADX INFO: compiled from: VirtualViewContainer.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/views/scroll/VirtualViewContainerState$Companion;", "", "<init>", "()V", "create", "Lcom/facebook/react/views/scroll/VirtualViewContainerState;", "scrollView", "Landroid/view/ViewGroup;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final VirtualViewContainerState create(ViewGroup scrollView) {
            Intrinsics.checkNotNullParameter(scrollView, "scrollView");
            if (ReactNativeFeatureFlags.enableVirtualViewContainerStateExperimental()) {
                return new VirtualViewContainerStateExperimental(scrollView);
            }
            return new VirtualViewContainerStateClassic(scrollView);
        }
    }

    public VirtualViewContainerState(ViewGroup scrollView) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        this.prerenderRatio = ReactNativeFeatureFlags.virtualViewPrerenderRatio();
        this.hysteresisRatio = ReactNativeFeatureFlags.virtualViewHysteresisRatio();
        this.emptyRect = new Rect();
        this.visibleRect = new Rect();
        this.prerenderRect = new Rect();
        this.hysteresisRect = new Rect();
        ViewTreeObserver.OnWindowFocusChangeListener onWindowFocusChangeListener = ReactNativeFeatureFlags.enableVirtualViewWindowFocusDetection() ? new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.facebook.react.views.scroll.VirtualViewContainerState$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
            public final void onWindowFocusChanged(boolean z) {
                VirtualViewContainerState.onWindowFocusChangeListener$lambda$0(this.f$0, z);
            }
        } : null;
        this.onWindowFocusChangeListener = onWindowFocusChangeListener;
        this.scrollView = scrollView;
        if (onWindowFocusChangeListener != null) {
            scrollView.getViewTreeObserver().addOnWindowFocusChangeListener(onWindowFocusChangeListener);
        }
    }

    public final void cleanup() {
        if (this.onWindowFocusChangeListener != null) {
            this.scrollView.getViewTreeObserver().removeOnWindowFocusChangeListener(this.onWindowFocusChangeListener);
        }
    }

    public void onChange(VirtualView virtualView) {
        Intrinsics.checkNotNullParameter(virtualView, "virtualView");
        if (getVirtualViews().add(virtualView)) {
            if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
                FLog.d("VirtualViewContainerState:add", "virtualViewID=" + virtualView.getVirtualViewID());
            }
        } else if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerState:update", "virtualViewID=" + virtualView.getVirtualViewID());
        }
        updateModes(virtualView);
    }

    public void remove(VirtualView virtualView) {
        Intrinsics.checkNotNullParameter(virtualView, "virtualView");
        getVirtualViews().remove(virtualView);
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerState:remove", "virtualViewID=" + virtualView.getVirtualViewID());
        }
    }

    protected final void updateRects() {
        this.scrollView.getDrawingRect(this.visibleRect);
        if (!this.visibleRect.isEmpty()) {
            this.prerenderRect.set(this.visibleRect);
            Rect rect = this.prerenderRect;
            rect.inset((int) (((double) (-rect.width())) * this.prerenderRatio), (int) (((double) (-this.prerenderRect.height())) * this.prerenderRatio));
            this.hysteresisRect.set(this.prerenderRect);
            this.hysteresisRect.inset((int) (((double) (-this.visibleRect.width())) * this.hysteresisRatio), (int) (((double) (-this.visibleRect.height())) * this.hysteresisRatio));
            if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
                FLog.d("VirtualViewContainerState:updateRects", "visibleRect " + this.visibleRect + " prerenderRect " + this.prerenderRect + " hysteresisRect " + this.hysteresisRect);
                return;
            }
            return;
        }
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerState:updateRects", "scrollView visibleRect is empty");
        }
        this.prerenderRect.set(this.visibleRect);
        this.hysteresisRect.set(this.prerenderRect);
    }

    public static /* synthetic */ void updateModes$default(VirtualViewContainerState virtualViewContainerState, VirtualView virtualView, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateModes");
        }
        if ((i & 1) != 0) {
            virtualView = null;
        }
        virtualViewContainerState.updateModes(virtualView);
    }

    public final void updateState() {
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerState:updateState", "");
        }
        updateModes$default(this, null, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onWindowFocusChangeListener$lambda$0(VirtualViewContainerState virtualViewContainerState, boolean z) {
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerState:onWindowFocusChanged", "");
        }
        updateModes$default(virtualViewContainerState, null, 1, null);
    }
}
