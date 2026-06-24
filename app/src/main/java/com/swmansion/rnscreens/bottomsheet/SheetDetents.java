package com.swmansion.rnscreens.bottomsheet;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenContentWrapper;
import io.sentry.protocol.SentryThread;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SheetDetents.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\u0018\u0000 *2\u00020\u0001:\u0001*B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0011J\u001d\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fJ\u001f\u0010 \u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\bH\u0000¢\u0006\u0002\b\"J\u0015\u0010#\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\b$J\u0015\u0010%\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\bH\u0000¢\u0006\u0002\b)R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006+"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/SheetDetents;", "", "rawDetents", "", "", "<init>", "(Ljava/util/List;)V", "count", "", "getCount$react_native_screens_release", "()I", "at", FirebaseAnalytics.Param.INDEX, "at$react_native_screens_release", "shortest", "shortest$react_native_screens_release", "highest", "highest$react_native_screens_release", "heightAt", "containerHeight", "heightAt$react_native_screens_release", "firstHeight", "firstHeight$react_native_screens_release", "maxAllowedHeight", "maxAllowedHeight$react_native_screens_release", "maxAllowedHeightForFitToContents", "screen", "Lcom/swmansion/rnscreens/Screen;", "maxAllowedHeightForFitToContents$react_native_screens_release", "halfExpandedRatio", "", "halfExpandedRatio$react_native_screens_release", "expandedOffsetFromTop", "topInset", "expandedOffsetFromTop$react_native_screens_release", "peekHeight", "peekHeight$react_native_screens_release", "sheetStateFromIndex", "sheetStateFromIndex$react_native_screens_release", "indexFromSheetState", SentryThread.JsonKeys.STATE, "indexFromSheetState$react_native_screens_release", "Companion", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SheetDetents {
    public static final double SHEET_FIT_TO_CONTENTS = -1.0d;
    private final List<Double> rawDetents;

    public SheetDetents(List<Double> rawDetents) {
        Intrinsics.checkNotNullParameter(rawDetents, "rawDetents");
        List<Double> list = rawDetents;
        this.rawDetents = CollectionsKt.toList(list);
        if (rawDetents.isEmpty()) {
            throw new IllegalArgumentException("[RNScreens] At least one detent must be provided.".toString());
        }
        if (rawDetents.size() > 3) {
            throw new IllegalArgumentException("[RNScreens] Maximum of 3 detents supported.".toString());
        }
        if (rawDetents.size() == 1) {
            double dDoubleValue = rawDetents.get(0).doubleValue();
            if ((0.0d > dDoubleValue || dDoubleValue > 1.0d) && dDoubleValue != -1.0d) {
                throw new IllegalArgumentException(("[RNScreens] Detent value must be within 0.0 and 1.0, or SHEET_FIT_TO_CONTENTS should be defined, got " + dDoubleValue + ".").toString());
            }
            return;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            double dDoubleValue2 = ((Number) it.next()).doubleValue();
            if (0.0d > dDoubleValue2 || dDoubleValue2 > 1.0d) {
                throw new IllegalArgumentException(("[RNScreens] Detent values must be within 0.0 and 1.0, got " + dDoubleValue2 + ".").toString());
            }
        }
        if (!Intrinsics.areEqual(rawDetents, CollectionsKt.sorted(list))) {
            throw new IllegalArgumentException("[RNScreens] Detents must be sorted in ascending order.".toString());
        }
    }

    public final int getCount$react_native_screens_release() {
        return this.rawDetents.size();
    }

    public final double at$react_native_screens_release(int i) {
        return this.rawDetents.get(i).doubleValue();
    }

    public final double shortest$react_native_screens_release() {
        return ((Number) CollectionsKt.first((List) this.rawDetents)).doubleValue();
    }

    public final double highest$react_native_screens_release() {
        return ((Number) CollectionsKt.last((List) this.rawDetents)).doubleValue();
    }

    public final int heightAt$react_native_screens_release(int i, int containerHeight) {
        double dAt$react_native_screens_release = at$react_native_screens_release(i);
        if (dAt$react_native_screens_release == -1.0d) {
            throw new IllegalArgumentException("[RNScreens] FIT_TO_CONTENTS is not supported by heightAt.".toString());
        }
        return (int) (dAt$react_native_screens_release * ((double) containerHeight));
    }

    public final int firstHeight$react_native_screens_release(int containerHeight) {
        return heightAt$react_native_screens_release(0, containerHeight);
    }

    public final int maxAllowedHeight$react_native_screens_release(int containerHeight) {
        return heightAt$react_native_screens_release(getCount$react_native_screens_release() - 1, containerHeight);
    }

    public final int maxAllowedHeightForFitToContents$react_native_screens_release(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        ScreenContentWrapper contentWrapper = screen.getContentWrapper();
        if (contentWrapper == null) {
            return 0;
        }
        Integer numValueOf = Integer.valueOf(contentWrapper.getHeight());
        numValueOf.intValue();
        if (!SheetUtilsKt.isLaidOutOrHasCachedLayout(contentWrapper)) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    public final float halfExpandedRatio$react_native_screens_release() {
        if (getCount$react_native_screens_release() < 3) {
            throw new IllegalStateException("[RNScreens] At least 3 detents required for halfExpandedRatio.");
        }
        return (float) (at$react_native_screens_release(1) / at$react_native_screens_release(2));
    }

    public static /* synthetic */ int expandedOffsetFromTop$react_native_screens_release$default(SheetDetents sheetDetents, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return sheetDetents.expandedOffsetFromTop$react_native_screens_release(i, i2);
    }

    public final int expandedOffsetFromTop$react_native_screens_release(int containerHeight, int topInset) {
        if (getCount$react_native_screens_release() < 3) {
            throw new IllegalStateException("[RNScreens] At least 3 detents required for expandedOffsetFromTop.");
        }
        return ((int) ((((double) 1) - at$react_native_screens_release(2)) * ((double) containerHeight))) + topInset;
    }

    public final int peekHeight$react_native_screens_release(int containerHeight) {
        return heightAt$react_native_screens_release(0, containerHeight);
    }

    public final int sheetStateFromIndex$react_native_screens_release(int i) {
        return SheetUtils.INSTANCE.sheetStateFromDetentIndex(i, getCount$react_native_screens_release());
    }

    public final int indexFromSheetState$react_native_screens_release(int i) {
        return SheetUtils.INSTANCE.detentIndexFromSheetState(i, getCount$react_native_screens_release());
    }
}
