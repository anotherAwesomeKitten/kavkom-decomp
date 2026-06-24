package com.facebook.react.uimanager.style;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BackgroundPosition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/uimanager/style/BackgroundPosition;", "", "top", "Lcom/facebook/react/uimanager/LengthPercentage;", "left", ViewProps.RIGHT, ViewProps.BOTTOM, "<init>", "(Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;)V", "getTop", "()Lcom/facebook/react/uimanager/LengthPercentage;", "getLeft", "getRight", "getBottom", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BackgroundPosition {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final LengthPercentage bottom;
    private final LengthPercentage left;
    private final LengthPercentage right;
    private final LengthPercentage top;

    public BackgroundPosition(LengthPercentage lengthPercentage, LengthPercentage lengthPercentage2, LengthPercentage lengthPercentage3, LengthPercentage lengthPercentage4) {
        this.top = lengthPercentage;
        this.left = lengthPercentage2;
        this.right = lengthPercentage3;
        this.bottom = lengthPercentage4;
    }

    public final LengthPercentage getTop() {
        return this.top;
    }

    public final LengthPercentage getLeft() {
        return this.left;
    }

    public final LengthPercentage getRight() {
        return this.right;
    }

    public final LengthPercentage getBottom() {
        return this.bottom;
    }

    /* JADX INFO: compiled from: BackgroundPosition.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/uimanager/style/BackgroundPosition$Companion;", "", "<init>", "()V", "parse", "Lcom/facebook/react/uimanager/style/BackgroundPosition;", "backgroundPositionMap", "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BackgroundPosition parse(ReadableMap backgroundPositionMap) {
            LengthPercentage fromDynamic = null;
            if (backgroundPositionMap == null) {
                return null;
            }
            LengthPercentage fromDynamic2 = (!backgroundPositionMap.hasKey("top") || backgroundPositionMap.getType("top") == ReadableType.Null) ? null : LengthPercentage.INSTANCE.setFromDynamic(backgroundPositionMap.getDynamic("top"), true);
            LengthPercentage fromDynamic3 = (!backgroundPositionMap.hasKey("left") || backgroundPositionMap.getType("left") == ReadableType.Null) ? null : LengthPercentage.INSTANCE.setFromDynamic(backgroundPositionMap.getDynamic("left"), true);
            LengthPercentage fromDynamic4 = (!backgroundPositionMap.hasKey(ViewProps.RIGHT) || backgroundPositionMap.getType(ViewProps.RIGHT) == ReadableType.Null) ? null : LengthPercentage.INSTANCE.setFromDynamic(backgroundPositionMap.getDynamic(ViewProps.RIGHT), true);
            if (backgroundPositionMap.hasKey(ViewProps.BOTTOM) && backgroundPositionMap.getType(ViewProps.BOTTOM) != ReadableType.Null) {
                fromDynamic = LengthPercentage.INSTANCE.setFromDynamic(backgroundPositionMap.getDynamic(ViewProps.BOTTOM), true);
            }
            return new BackgroundPosition(fromDynamic2, fromDynamic3, fromDynamic4, fromDynamic);
        }
    }
}
