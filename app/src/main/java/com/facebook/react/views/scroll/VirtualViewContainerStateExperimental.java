package com.facebook.react.views.scroll;

import android.graphics.Rect;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.views.virtual.VirtualViewMode;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VirtualViewContainerStateExperimental.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\u001aH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012¨\u0006 "}, d2 = {"Lcom/facebook/react/views/scroll/VirtualViewContainerStateExperimental;", "Lcom/facebook/react/views/scroll/VirtualViewContainerState;", "scrollView", "Landroid/view/ViewGroup;", "<init>", "(Landroid/view/ViewGroup;)V", "horizontal", "", "virtualViews", "Lcom/facebook/react/views/scroll/IntervalTree;", "getVirtualViews", "()Lcom/facebook/react/views/scroll/IntervalTree;", "HPV", "", "", "getHPV", "()Ljava/util/Set;", "setHPV", "(Ljava/util/Set;)V", "P", "getP", "setP", "V", "getV", "setV", "onChange", "", "virtualView", "Lcom/facebook/react/views/scroll/VirtualView;", "updateModes", "updateMode", "updateModesAll", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VirtualViewContainerStateExperimental extends VirtualViewContainerState {
    private Set<String> HPV;
    private Set<String> P;
    private Set<String> V;
    private final boolean horizontal;
    private final IntervalTree virtualViews;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VirtualViewContainerStateExperimental(ViewGroup scrollView) {
        super(scrollView);
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        boolean z = false;
        if (!(scrollView instanceof ReactScrollView) && (scrollView instanceof ReactHorizontalScrollView)) {
            z = true;
        }
        this.horizontal = z;
        this.virtualViews = new IntervalTree(z);
        this.HPV = new LinkedHashSet();
        this.P = new LinkedHashSet();
        this.V = new LinkedHashSet();
    }

    @Override // com.facebook.react.views.scroll.VirtualViewContainerState
    public IntervalTree getVirtualViews() {
        return this.virtualViews;
    }

    public final Set<String> getHPV() {
        return this.HPV;
    }

    public final void setHPV(Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.HPV = set;
    }

    public final Set<String> getP() {
        return this.P;
    }

    public final void setP(Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.P = set;
    }

    public final Set<String> getV() {
        return this.V;
    }

    public final void setV(Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.V = set;
    }

    @Override // com.facebook.react.views.scroll.VirtualViewContainerState
    public void onChange(VirtualView virtualView) {
        Intrinsics.checkNotNullParameter(virtualView, "virtualView");
        if (getVirtualViews().add(virtualView)) {
            if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
                FLog.d("VirtualViewContainerStateExperimental:add", "virtualViewID=" + virtualView.getVirtualViewID());
            }
        } else if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:update", "virtualViewID=" + virtualView.getVirtualViewID());
        }
        updateModes(virtualView);
    }

    @Override // com.facebook.react.views.scroll.VirtualViewContainerState
    protected void updateModes(VirtualView virtualView) {
        updateRects();
        if (virtualView != null) {
            updateMode(virtualView);
        } else {
            updateModesAll();
        }
    }

    private final void updateMode(VirtualView virtualView) {
        Rect containerRelativeRect = virtualView.getContainerRelativeRect();
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
            virtualView.onModeChange(virtualViewMode, emptyRect);
        }
        if (virtualViewMode == VirtualViewMode.Visible) {
            this.HPV.add(virtualView.getVirtualViewID());
            this.P.remove(virtualView.getVirtualViewID());
            this.V.add(virtualView.getVirtualViewID());
        } else if (virtualViewMode == VirtualViewMode.Prerender) {
            this.HPV.add(virtualView.getVirtualViewID());
            this.P.add(virtualView.getVirtualViewID());
            this.V.remove(virtualView.getVirtualViewID());
        } else if (virtualViewMode == VirtualViewMode.Hidden) {
            this.HPV.remove(virtualView.getVirtualViewID());
            this.P.remove(virtualView.getVirtualViewID());
            this.V.remove(virtualView.getVirtualViewID());
        } else {
            this.HPV.add(virtualView.getVirtualViewID());
            this.P.remove(virtualView.getVirtualViewID());
            this.V.remove(virtualView.getVirtualViewID());
        }
    }

    private final void updateModesAll() {
        Set<String> setQuery = getVirtualViews().query(getVisibleRect());
        Set<String> setQuery2 = getVirtualViews().query(getPrerenderRect());
        Set<String> setQuery3 = getVirtualViews().query(getHysteresisRect());
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:updateModes", "V: " + this.V + ", P: " + this.P + ", HPV: " + this.HPV);
        }
        Set setMinus = SetsKt.minus((Set) setQuery2, (Iterable) setQuery);
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:updateModes", "V': " + setQuery + ", P': " + setMinus + ", HPV': " + setQuery3);
        }
        Set setMinus2 = SetsKt.minus((Set) setQuery, (Iterable) this.V);
        Set setMinus3 = SetsKt.minus(setMinus, (Iterable) this.P);
        Set<String> set = setQuery3;
        Set setMinus4 = SetsKt.minus((Set) this.HPV, (Iterable) set);
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:updateModes", "toV: " + setMinus2 + ", toP: " + setMinus3 + ", toH: " + setMinus4);
        }
        Iterator it = setMinus2.iterator();
        while (it.hasNext()) {
            VirtualView virtualView = getVirtualViews().getVirtualView((String) it.next());
            if (virtualView != null) {
                virtualView.onModeChange(VirtualViewMode.Visible, getVisibleRect());
            }
        }
        Iterator it2 = setMinus3.iterator();
        while (it2.hasNext()) {
            VirtualView virtualView2 = getVirtualViews().getVirtualView((String) it2.next());
            if (virtualView2 != null) {
                virtualView2.onModeChange(VirtualViewMode.Prerender, getPrerenderRect());
            }
        }
        Iterator it3 = setMinus4.iterator();
        while (it3.hasNext()) {
            VirtualView virtualView3 = getVirtualViews().getVirtualView((String) it3.next());
            if (virtualView3 != null) {
                virtualView3.onModeChange(VirtualViewMode.Hidden, getEmptyRect());
            }
        }
        this.V = setQuery;
        this.P = CollectionsKt.toMutableSet(setMinus);
        this.HPV = CollectionsKt.toMutableSet(set);
    }
}
