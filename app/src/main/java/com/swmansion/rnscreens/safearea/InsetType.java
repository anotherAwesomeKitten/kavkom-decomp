package com.swmansion.rnscreens.safearea;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: InsetType.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\n"}, d2 = {"Lcom/swmansion/rnscreens/safearea/InsetType;", "", "<init>", "(Ljava/lang/String;I)V", "ALL", "SYSTEM", "INTERFACE", "containsSystem", "", "containsInterface", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InsetType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ InsetType[] $VALUES;
    public static final InsetType ALL = new InsetType("ALL", 0);
    public static final InsetType SYSTEM = new InsetType("SYSTEM", 1);
    public static final InsetType INTERFACE = new InsetType("INTERFACE", 2);

    private static final /* synthetic */ InsetType[] $values() {
        return new InsetType[]{ALL, SYSTEM, INTERFACE};
    }

    public static EnumEntries<InsetType> getEntries() {
        return $ENTRIES;
    }

    private InsetType(String str, int i) {
    }

    static {
        InsetType[] insetTypeArr$values = $values();
        $VALUES = insetTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(insetTypeArr$values);
    }

    public final boolean containsSystem() {
        return this == ALL || this == SYSTEM;
    }

    public final boolean containsInterface() {
        return this == ALL || this == INTERFACE;
    }

    public static InsetType valueOf(String str) {
        return (InsetType) Enum.valueOf(InsetType.class, str);
    }

    public static InsetType[] values() {
        return (InsetType[]) $VALUES.clone();
    }
}
