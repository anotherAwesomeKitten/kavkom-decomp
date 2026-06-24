package com.swmansion.rnscreens.safearea.paper;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.swmansion.rnscreens.safearea.EdgeInsets;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeAreaViewShadowNode.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0017R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/swmansion/rnscreens/safearea/paper/SafeAreaViewShadowNode;", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "<init>", "()V", "localData", "Lcom/swmansion/rnscreens/safearea/paper/SafeAreaViewLocalData;", "margins", "", "needsUpdate", "", "updateInsets", "", "getEdgeValue", "", "edgeMode", "insetValue", "edgeValue", "onBeforeLayout", "nativeViewHierarchyOptimizer", "Lcom/facebook/react/uimanager/NativeViewHierarchyOptimizer;", "setLocalData", "data", "", "setMargins", FirebaseAnalytics.Param.INDEX, "", ViewProps.MARGIN, "Lcom/facebook/react/bridge/Dynamic;", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SafeAreaViewShadowNode extends LayoutShadowNode {
    private SafeAreaViewLocalData localData;
    private final float[] margins = new float[ViewProps.PADDING_MARGIN_SPACING_TYPES.length];
    private boolean needsUpdate;

    private final float getEdgeValue(boolean edgeMode, float insetValue, float edgeValue) {
        return edgeMode ? insetValue + edgeValue : edgeValue;
    }

    public SafeAreaViewShadowNode() {
        int length = ViewProps.PADDING_MARGIN_SPACING_TYPES.length;
        for (int i = 0; i < length; i++) {
            this.margins[i] = Float.NaN;
        }
    }

    private final void updateInsets() {
        SafeAreaViewLocalData safeAreaViewLocalData = this.localData;
        if (safeAreaViewLocalData == null) {
            return;
        }
        float f = this.margins[8];
        if (Float.isNaN(f)) {
            f = 0.0f;
        }
        float f2 = f;
        float f3 = f2;
        float f4 = f3;
        float f5 = this.margins[7];
        if (!Float.isNaN(f5)) {
            f2 = f5;
            f4 = f2;
        }
        float f6 = this.margins[6];
        if (!Float.isNaN(f6)) {
            f = f6;
            f3 = f;
        }
        float f7 = this.margins[0];
        if (!Float.isNaN(f7)) {
            f = f7;
        }
        float f8 = this.margins[1];
        if (!Float.isNaN(f8)) {
            f2 = f8;
        }
        float f9 = this.margins[2];
        if (!Float.isNaN(f9)) {
            f3 = f9;
        }
        float f10 = this.margins[3];
        if (!Float.isNaN(f10)) {
            f4 = f10;
        }
        float pixelFromDIP = PixelUtil.toPixelFromDIP(f);
        float pixelFromDIP2 = PixelUtil.toPixelFromDIP(f2);
        float pixelFromDIP3 = PixelUtil.toPixelFromDIP(f3);
        float pixelFromDIP4 = PixelUtil.toPixelFromDIP(f4);
        SafeAreaViewEdges edges = safeAreaViewLocalData.getEdges();
        EdgeInsets insets = safeAreaViewLocalData.getInsets();
        super.setMargin(0, getEdgeValue(edges.getLeft(), insets.getLeft(), pixelFromDIP));
        super.setMargin(1, getEdgeValue(edges.getTop(), insets.getTop(), pixelFromDIP2));
        super.setMargin(2, getEdgeValue(edges.getRight(), insets.getRight(), pixelFromDIP3));
        super.setMargin(3, getEdgeValue(edges.getBottom(), insets.getBottom(), pixelFromDIP4));
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        Intrinsics.checkNotNullParameter(nativeViewHierarchyOptimizer, "nativeViewHierarchyOptimizer");
        if (this.needsUpdate) {
            this.needsUpdate = false;
            updateInsets();
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof SafeAreaViewLocalData) {
            this.localData = (SafeAreaViewLocalData) data;
            this.needsUpdate = false;
            updateInsets();
        }
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    @ReactPropGroup(names = {ViewProps.MARGIN, ViewProps.MARGIN_VERTICAL, ViewProps.MARGIN_HORIZONTAL, ViewProps.MARGIN_START, ViewProps.MARGIN_END, ViewProps.MARGIN_TOP, ViewProps.MARGIN_BOTTOM, ViewProps.MARGIN_LEFT, ViewProps.MARGIN_RIGHT})
    public void setMargins(int index, Dynamic margin) {
        Intrinsics.checkNotNullParameter(margin, "margin");
        this.margins[ViewProps.PADDING_MARGIN_SPACING_TYPES[index]] = margin.getType() == ReadableType.Number ? (float) margin.asDouble() : Float.NaN;
        super.setMargins(index, margin);
        this.needsUpdate = true;
    }
}
