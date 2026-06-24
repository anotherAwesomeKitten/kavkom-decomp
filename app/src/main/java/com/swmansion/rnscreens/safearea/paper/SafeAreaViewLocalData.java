package com.swmansion.rnscreens.safearea.paper;

import com.swmansion.rnscreens.safearea.EdgeInsets;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeAreaViewLocalData.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/swmansion/rnscreens/safearea/paper/SafeAreaViewLocalData;", "", "insets", "Lcom/swmansion/rnscreens/safearea/EdgeInsets;", "edges", "Lcom/swmansion/rnscreens/safearea/paper/SafeAreaViewEdges;", "<init>", "(Lcom/swmansion/rnscreens/safearea/EdgeInsets;Lcom/swmansion/rnscreens/safearea/paper/SafeAreaViewEdges;)V", "getInsets", "()Lcom/swmansion/rnscreens/safearea/EdgeInsets;", "getEdges", "()Lcom/swmansion/rnscreens/safearea/paper/SafeAreaViewEdges;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class SafeAreaViewLocalData {
    private final SafeAreaViewEdges edges;
    private final EdgeInsets insets;

    public static /* synthetic */ SafeAreaViewLocalData copy$default(SafeAreaViewLocalData safeAreaViewLocalData, EdgeInsets edgeInsets, SafeAreaViewEdges safeAreaViewEdges, int i, Object obj) {
        if ((i & 1) != 0) {
            edgeInsets = safeAreaViewLocalData.insets;
        }
        if ((i & 2) != 0) {
            safeAreaViewEdges = safeAreaViewLocalData.edges;
        }
        return safeAreaViewLocalData.copy(edgeInsets, safeAreaViewEdges);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EdgeInsets getInsets() {
        return this.insets;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SafeAreaViewEdges getEdges() {
        return this.edges;
    }

    public final SafeAreaViewLocalData copy(EdgeInsets insets, SafeAreaViewEdges edges) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        Intrinsics.checkNotNullParameter(edges, "edges");
        return new SafeAreaViewLocalData(insets, edges);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SafeAreaViewLocalData)) {
            return false;
        }
        SafeAreaViewLocalData safeAreaViewLocalData = (SafeAreaViewLocalData) other;
        return Intrinsics.areEqual(this.insets, safeAreaViewLocalData.insets) && Intrinsics.areEqual(this.edges, safeAreaViewLocalData.edges);
    }

    public int hashCode() {
        return (this.insets.hashCode() * 31) + this.edges.hashCode();
    }

    public String toString() {
        return "SafeAreaViewLocalData(insets=" + this.insets + ", edges=" + this.edges + ")";
    }

    public SafeAreaViewLocalData(EdgeInsets insets, SafeAreaViewEdges edges) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        Intrinsics.checkNotNullParameter(edges, "edges");
        this.insets = insets;
        this.edges = edges;
    }

    public final EdgeInsets getInsets() {
        return this.insets;
    }

    public final SafeAreaViewEdges getEdges() {
        return this.edges;
    }
}
