package com.facebook.yoga;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: YogaLayoutType.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/facebook/yoga/YogaLayoutType;", "", "intValue", "", "<init>", "(Ljava/lang/String;II)V", "getIntValue", "()I", "LAYOUT", "MEASURE", "CACHED_LAYOUT", "CACHED_MEASURE", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class YogaLayoutType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ YogaLayoutType[] $VALUES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int intValue;
    public static final YogaLayoutType LAYOUT = new YogaLayoutType("LAYOUT", 0, 0);
    public static final YogaLayoutType MEASURE = new YogaLayoutType("MEASURE", 1, 1);
    public static final YogaLayoutType CACHED_LAYOUT = new YogaLayoutType("CACHED_LAYOUT", 2, 2);
    public static final YogaLayoutType CACHED_MEASURE = new YogaLayoutType("CACHED_MEASURE", 3, 3);

    private static final /* synthetic */ YogaLayoutType[] $values() {
        return new YogaLayoutType[]{LAYOUT, MEASURE, CACHED_LAYOUT, CACHED_MEASURE};
    }

    @JvmStatic
    public static final YogaLayoutType fromInt(int i) {
        return INSTANCE.fromInt(i);
    }

    public static EnumEntries<YogaLayoutType> getEntries() {
        return $ENTRIES;
    }

    private YogaLayoutType(String str, int i, int i2) {
        this.intValue = i2;
    }

    public final int getIntValue() {
        return this.intValue;
    }

    static {
        YogaLayoutType[] yogaLayoutTypeArr$values = $values();
        $VALUES = yogaLayoutTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(yogaLayoutTypeArr$values);
        INSTANCE = new Companion(null);
    }

    /* JADX INFO: compiled from: YogaLayoutType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/yoga/YogaLayoutType$Companion;", "", "<init>", "()V", "fromInt", "Lcom/facebook/yoga/YogaLayoutType;", "value", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final YogaLayoutType fromInt(int value) {
            if (value == 0) {
                return YogaLayoutType.LAYOUT;
            }
            if (value == 1) {
                return YogaLayoutType.MEASURE;
            }
            if (value == 2) {
                return YogaLayoutType.CACHED_LAYOUT;
            }
            if (value == 3) {
                return YogaLayoutType.CACHED_MEASURE;
            }
            throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }

    public static YogaLayoutType valueOf(String str) {
        return (YogaLayoutType) Enum.valueOf(YogaLayoutType.class, str);
    }

    public static YogaLayoutType[] values() {
        return (YogaLayoutType[]) $VALUES.clone();
    }
}
