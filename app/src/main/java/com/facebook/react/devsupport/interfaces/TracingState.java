package com.facebook.react.devsupport.interfaces;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: TracingState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/TracingState;", "", "<init>", "(Ljava/lang/String;I)V", "DISABLED", "ENABLEDINBACKGROUNDMODE", "ENABLEDINCDPMODE", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TracingState extends Enum<TracingState> {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TracingState[] $VALUES;
    public static final TracingState DISABLED = new TracingState("DISABLED", 0);
    public static final TracingState ENABLEDINBACKGROUNDMODE = new TracingState("ENABLEDINBACKGROUNDMODE", 1);
    public static final TracingState ENABLEDINCDPMODE = new TracingState("ENABLEDINCDPMODE", 2);

    private static final /* synthetic */ TracingState[] $values() {
        return new TracingState[]{DISABLED, ENABLEDINBACKGROUNDMODE, ENABLEDINCDPMODE};
    }

    public static EnumEntries<TracingState> getEntries() {
        return $ENTRIES;
    }

    private TracingState(String str, int i) {
        super(str, i);
    }

    static {
        TracingState[] tracingStateArr$values = $values();
        $VALUES = tracingStateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(tracingStateArr$values);
    }

    public static TracingState valueOf(String str) {
        return (TracingState) Enum.valueOf(TracingState.class, str);
    }

    public static TracingState[] values() {
        return (TracingState[]) $VALUES.clone();
    }
}
