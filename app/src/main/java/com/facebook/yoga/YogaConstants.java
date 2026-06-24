package com.facebook.yoga;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YogaConstants.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0007J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\u0005H\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/facebook/yoga/YogaConstants;", "", "<init>", "()V", "UNDEFINED", "", "isUndefined", "", "value", "Lcom/facebook/yoga/YogaValue;", "getUndefined", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YogaConstants {
    public static final YogaConstants INSTANCE = new YogaConstants();
    public static final float UNDEFINED = Float.NaN;

    private YogaConstants() {
    }

    @JvmStatic
    public static final boolean isUndefined(float value) {
        return Float.compare(value, UNDEFINED) == 0;
    }

    @JvmStatic
    public static final boolean isUndefined(YogaValue value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value.unit == YogaUnit.UNDEFINED;
    }

    @JvmStatic
    public static final float getUndefined() {
        return UNDEFINED;
    }
}
