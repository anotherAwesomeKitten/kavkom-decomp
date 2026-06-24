package com.facebook.yoga;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: YogaValue.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/facebook/yoga/YogaValue;", "", "value", "", "unit", "Lcom/facebook/yoga/YogaUnit;", "<init>", "(FLcom/facebook/yoga/YogaUnit;)V", "", "(FI)V", "equals", "", "other", "hashCode", "toString", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YogaValue {
    public final YogaUnit unit;
    public final float value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final YogaValue UNDEFINED = new YogaValue(YogaConstants.UNDEFINED, YogaUnit.UNDEFINED);
    public static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    public static final YogaValue AUTO = new YogaValue(YogaConstants.UNDEFINED, YogaUnit.AUTO);

    /* JADX INFO: compiled from: YogaValue.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[YogaUnit.values().length];
            try {
                iArr[YogaUnit.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[YogaUnit.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[YogaUnit.PERCENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[YogaUnit.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final YogaValue parse(String str) {
        return INSTANCE.parse(str);
    }

    public YogaValue(float f, YogaUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.value = f;
        this.unit = unit;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public YogaValue(float f, int i) {
        YogaUnit yogaUnitFromInt = YogaUnit.fromInt(i);
        Intrinsics.checkNotNullExpressionValue(yogaUnitFromInt, "fromInt(...)");
        this(f, yogaUnitFromInt);
    }

    public boolean equals(Object other) {
        if (other instanceof YogaValue) {
            YogaUnit yogaUnit = this.unit;
            YogaValue yogaValue = (YogaValue) other;
            if (yogaUnit == yogaValue.unit) {
                return yogaUnit == YogaUnit.UNDEFINED || this.unit == YogaUnit.AUTO || Float.compare(this.value, yogaValue.value) == 0;
            }
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }

    public String toString() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.unit.ordinal()];
        if (i == 1) {
            return "undefined";
        }
        if (i == 2) {
            return String.valueOf(this.value);
        }
        if (i == 3) {
            return this.value + "%";
        }
        if (i == 4) {
            return "auto";
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: compiled from: YogaValue.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/facebook/yoga/YogaValue$Companion;", "", "<init>", "()V", "UNDEFINED", "Lcom/facebook/yoga/YogaValue;", "ZERO", "AUTO", "parse", "s", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final YogaValue parse(String s) {
            if (s == null) {
                return null;
            }
            if (Intrinsics.areEqual("undefined", s)) {
                return YogaValue.UNDEFINED;
            }
            if (Intrinsics.areEqual("auto", s)) {
                return YogaValue.AUTO;
            }
            if (StringsKt.endsWith$default(s, "%", false, 2, (Object) null)) {
                String strSubstring = s.substring(0, s.length() - 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return new YogaValue(Float.parseFloat(strSubstring), YogaUnit.PERCENT);
            }
            return new YogaValue(Float.parseFloat(s), YogaUnit.POINT);
        }
    }
}
