package com.facebook.react.views.scroll;

import com.facebook.common.logging.FLog;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VirtualViewContainerStateExperimental.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0000J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/facebook/react/views/scroll/Interval;", "", ViewProps.START, "", ViewProps.END, "id", "", "<init>", "(IILjava/lang/String;)V", "getStart", "()I", "getEnd", "getId", "()Ljava/lang/String;", "intersects", "", "other", "component1", "component2", "component3", "copy", "equals", "hashCode", "toString", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
final /* data */ class Interval {
    private final int end;
    private final String id;
    private final int start;

    public static /* synthetic */ Interval copy$default(Interval interval, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = interval.start;
        }
        if ((i3 & 2) != 0) {
            i2 = interval.end;
        }
        if ((i3 & 4) != 0) {
            str = interval.id;
        }
        return interval.copy(i, i2, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEnd() {
        return this.end;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final Interval copy(int start, int end, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new Interval(start, end, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Interval)) {
            return false;
        }
        Interval interval = (Interval) other;
        return this.start == interval.start && this.end == interval.end && Intrinsics.areEqual(this.id, interval.id);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.start) * 31) + Integer.hashCode(this.end)) * 31) + this.id.hashCode();
    }

    public String toString() {
        return "Interval(start=" + this.start + ", end=" + this.end + ", id=" + this.id + ")";
    }

    public Interval(int i, int i2, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.start = i;
        this.end = i2;
        this.id = id;
    }

    public final int getEnd() {
        return this.end;
    }

    public final String getId() {
        return this.id;
    }

    public final int getStart() {
        return this.start;
    }

    public final boolean intersects(Interval other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:Interval: intersect", this.id + ":(" + this.start + ", " + this.end + ") vs " + other.id + ":(" + other.start + ", " + other.end + ")");
        }
        return this.start < other.end && other.start < this.end;
    }
}
