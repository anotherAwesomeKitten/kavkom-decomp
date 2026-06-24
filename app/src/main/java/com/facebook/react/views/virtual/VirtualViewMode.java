package com.facebook.react.views.virtual;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: VirtualViewMode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/views/virtual/VirtualViewMode;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "Visible", "Prerender", "Hidden", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VirtualViewMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ VirtualViewMode[] $VALUES;
    private final int value;
    public static final VirtualViewMode Visible = new VirtualViewMode("Visible", 0, 0);
    public static final VirtualViewMode Prerender = new VirtualViewMode("Prerender", 1, 1);
    public static final VirtualViewMode Hidden = new VirtualViewMode("Hidden", 2, 2);

    private static final /* synthetic */ VirtualViewMode[] $values() {
        return new VirtualViewMode[]{Visible, Prerender, Hidden};
    }

    public static EnumEntries<VirtualViewMode> getEntries() {
        return $ENTRIES;
    }

    private VirtualViewMode(String str, int i, int i2) {
        this.value = i2;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        VirtualViewMode[] virtualViewModeArr$values = $values();
        $VALUES = virtualViewModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(virtualViewModeArr$values);
    }

    public static VirtualViewMode valueOf(String str) {
        return (VirtualViewMode) Enum.valueOf(VirtualViewMode.class, str);
    }

    public static VirtualViewMode[] values() {
        return (VirtualViewMode[]) $VALUES.clone();
    }
}
