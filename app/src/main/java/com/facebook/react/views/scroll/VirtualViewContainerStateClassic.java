package com.facebook.react.views.scroll;

import android.graphics.Rect;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.views.virtual.VirtualViewMode;
import java.util.Collection;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VirtualViewContainerStateClassic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0014R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/views/scroll/VirtualViewContainerStateClassic;", "Lcom/facebook/react/views/scroll/VirtualViewContainerState;", "scrollView", "Landroid/view/ViewGroup;", "<init>", "(Landroid/view/ViewGroup;)V", "virtualViews", "", "Lcom/facebook/react/views/scroll/VirtualView;", "getVirtualViews", "()Ljava/util/Collection;", "updateModes", "", "virtualView", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VirtualViewContainerStateClassic extends VirtualViewContainerState {
    private final Collection<VirtualView> virtualViews;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VirtualViewContainerStateClassic(ViewGroup scrollView) {
        super(scrollView);
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        this.virtualViews = new LinkedHashSet();
    }

    @Override // com.facebook.react.views.scroll.VirtualViewContainerState
    protected Collection<VirtualView> getVirtualViews() {
        return this.virtualViews;
    }

    @Override // com.facebook.react.views.scroll.VirtualViewContainerState
    protected void updateModes(VirtualView virtualView) {
        getScrollView().getDrawingRect(getVisibleRect());
        if (!getVisibleRect().isEmpty()) {
            getPrerenderRect().set(getVisibleRect());
            getPrerenderRect().inset((int) (((double) (-getPrerenderRect().width())) * getPrerenderRatio()), (int) (((double) (-getPrerenderRect().height())) * getPrerenderRatio()));
            if (getHysteresisRatio() > 0.0d) {
                getHysteresisRect().set(getPrerenderRect());
                getHysteresisRect().inset((int) (((double) (-getVisibleRect().width())) * getHysteresisRatio()), (int) (((double) (-getVisibleRect().height())) * getHysteresisRatio()));
            }
            for (VirtualView virtualView2 : virtualView != null ? CollectionsKt.listOf(virtualView) : CollectionsKt.toMutableSet(getVirtualViews())) {
                Rect containerRelativeRect = virtualView2.getContainerRelativeRect();
                VirtualViewMode virtualViewMode = VirtualViewMode.Hidden;
                Rect emptyRect = getEmptyRect();
                if (VirtualViewContainerKt.rectsOverlap(containerRelativeRect, getVisibleRect())) {
                    emptyRect = getVisibleRect();
                    virtualViewMode = (getOnWindowFocusChangeListener() == null || getScrollView().hasWindowFocus()) ? VirtualViewMode.Visible : VirtualViewMode.Prerender;
                } else if (VirtualViewContainerKt.rectsOverlap(containerRelativeRect, getPrerenderRect())) {
                    virtualViewMode = VirtualViewMode.Prerender;
                    emptyRect = getPrerenderRect();
                } else if (getHysteresisRatio() > 0.0d && VirtualViewContainerKt.rectsOverlap(containerRelativeRect, getHysteresisRect())) {
                    virtualViewMode = null;
                }
                if (virtualViewMode != null) {
                    virtualView2.onModeChange(virtualViewMode, emptyRect);
                    if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
                        FLog.d("VirtualViewContainerStateClassic:updateModes", "virtualView=" + virtualView2.getVirtualViewID() + " mode=" + virtualViewMode + "  rect=" + containerRelativeRect + " thresholdRect=" + emptyRect);
                    }
                }
            }
            return;
        }
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateClassic:updateModes", "scrollView visibleRect is empty");
        }
    }
}
