package com.facebook.react.views.virtual;

import com.oblador.keychain.KeychainModule;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: VirtualViewRenderState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/views/virtual/VirtualViewRenderState;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "Unknown", "Rendered", KeychainModule.AccessControl.NONE, "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VirtualViewRenderState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ VirtualViewRenderState[] $VALUES;
    private final int value;
    public static final VirtualViewRenderState Unknown = new VirtualViewRenderState("Unknown", 0, 0);
    public static final VirtualViewRenderState Rendered = new VirtualViewRenderState("Rendered", 1, 1);
    public static final VirtualViewRenderState None = new VirtualViewRenderState(KeychainModule.AccessControl.NONE, 2, 2);

    private static final /* synthetic */ VirtualViewRenderState[] $values() {
        return new VirtualViewRenderState[]{Unknown, Rendered, None};
    }

    public static EnumEntries<VirtualViewRenderState> getEntries() {
        return $ENTRIES;
    }

    private VirtualViewRenderState(String str, int i, int i2) {
        this.value = i2;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        VirtualViewRenderState[] virtualViewRenderStateArr$values = $values();
        $VALUES = virtualViewRenderStateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(virtualViewRenderStateArr$values);
    }

    public static VirtualViewRenderState valueOf(String str) {
        return (VirtualViewRenderState) Enum.valueOf(VirtualViewRenderState.class, str);
    }

    public static VirtualViewRenderState[] values() {
        return (VirtualViewRenderState[]) $VALUES.clone();
    }
}
