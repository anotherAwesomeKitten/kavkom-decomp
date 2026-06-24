package com.facebook.yoga;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: LayoutPassReason.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/facebook/yoga/LayoutPassReason;", "", "intValue", "", "<init>", "(Ljava/lang/String;II)V", "getIntValue", "()I", "INITIAL", "ABS_LAYOUT", "STRETCH", "MULTILINE_STRETCH", "FLEX_LAYOUT", "MEASURE", "ABS_MEASURE", "FLEX_MEASURE", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LayoutPassReason {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LayoutPassReason[] $VALUES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int intValue;
    public static final LayoutPassReason INITIAL = new LayoutPassReason("INITIAL", 0, 0);
    public static final LayoutPassReason ABS_LAYOUT = new LayoutPassReason("ABS_LAYOUT", 1, 1);
    public static final LayoutPassReason STRETCH = new LayoutPassReason("STRETCH", 2, 2);
    public static final LayoutPassReason MULTILINE_STRETCH = new LayoutPassReason("MULTILINE_STRETCH", 3, 3);
    public static final LayoutPassReason FLEX_LAYOUT = new LayoutPassReason("FLEX_LAYOUT", 4, 4);
    public static final LayoutPassReason MEASURE = new LayoutPassReason("MEASURE", 5, 5);
    public static final LayoutPassReason ABS_MEASURE = new LayoutPassReason("ABS_MEASURE", 6, 6);
    public static final LayoutPassReason FLEX_MEASURE = new LayoutPassReason("FLEX_MEASURE", 7, 7);

    private static final /* synthetic */ LayoutPassReason[] $values() {
        return new LayoutPassReason[]{INITIAL, ABS_LAYOUT, STRETCH, MULTILINE_STRETCH, FLEX_LAYOUT, MEASURE, ABS_MEASURE, FLEX_MEASURE};
    }

    @JvmStatic
    public static final LayoutPassReason fromInt(int i) {
        return INSTANCE.fromInt(i);
    }

    public static EnumEntries<LayoutPassReason> getEntries() {
        return $ENTRIES;
    }

    private LayoutPassReason(String str, int i, int i2) {
        this.intValue = i2;
    }

    public final int getIntValue() {
        return this.intValue;
    }

    static {
        LayoutPassReason[] layoutPassReasonArr$values = $values();
        $VALUES = layoutPassReasonArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(layoutPassReasonArr$values);
        INSTANCE = new Companion(null);
    }

    /* JADX INFO: compiled from: LayoutPassReason.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/yoga/LayoutPassReason$Companion;", "", "<init>", "()V", "fromInt", "Lcom/facebook/yoga/LayoutPassReason;", "value", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final LayoutPassReason fromInt(int value) {
            switch (value) {
                case 0:
                    return LayoutPassReason.INITIAL;
                case 1:
                    return LayoutPassReason.ABS_LAYOUT;
                case 2:
                    return LayoutPassReason.STRETCH;
                case 3:
                    return LayoutPassReason.MULTILINE_STRETCH;
                case 4:
                    return LayoutPassReason.FLEX_LAYOUT;
                case 5:
                    return LayoutPassReason.MEASURE;
                case 6:
                    return LayoutPassReason.ABS_MEASURE;
                case 7:
                    return LayoutPassReason.FLEX_MEASURE;
                default:
                    throw new IllegalArgumentException("Unknown enum value: " + value);
            }
        }
    }

    public static LayoutPassReason valueOf(String str) {
        return (LayoutPassReason) Enum.valueOf(LayoutPassReason.class, str);
    }

    public static LayoutPassReason[] values() {
        return (LayoutPassReason[]) $VALUES.clone();
    }
}
