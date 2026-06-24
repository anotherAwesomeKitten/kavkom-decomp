package com.facebook.react.devsupport.interfaces;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: DebuggerFrontendPanelName.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/DebuggerFrontendPanelName;", "", "internalName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getInternalName", "()Ljava/lang/String;", "CONSOLE", "MEMORY", "NETWORK", "PERFORMANCE", "REACT_COMPONENTS", "REACT_PROFILER", "SOURCES", "WELCOME", "toString", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DebuggerFrontendPanelName {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DebuggerFrontendPanelName[] $VALUES;
    public static final DebuggerFrontendPanelName CONSOLE = new DebuggerFrontendPanelName("CONSOLE", 0, "console");
    public static final DebuggerFrontendPanelName MEMORY = new DebuggerFrontendPanelName("MEMORY", 1, "heap-profiler");
    public static final DebuggerFrontendPanelName NETWORK = new DebuggerFrontendPanelName("NETWORK", 2, "network");
    public static final DebuggerFrontendPanelName PERFORMANCE = new DebuggerFrontendPanelName("PERFORMANCE", 3, "timeline");
    public static final DebuggerFrontendPanelName REACT_COMPONENTS = new DebuggerFrontendPanelName("REACT_COMPONENTS", 4, "react-devtools-components");
    public static final DebuggerFrontendPanelName REACT_PROFILER = new DebuggerFrontendPanelName("REACT_PROFILER", 5, "react-devtools-profiler");
    public static final DebuggerFrontendPanelName SOURCES = new DebuggerFrontendPanelName("SOURCES", 6, "sources");
    public static final DebuggerFrontendPanelName WELCOME = new DebuggerFrontendPanelName("WELCOME", 7, "rn-welcome");
    private final String internalName;

    private static final /* synthetic */ DebuggerFrontendPanelName[] $values() {
        return new DebuggerFrontendPanelName[]{CONSOLE, MEMORY, NETWORK, PERFORMANCE, REACT_COMPONENTS, REACT_PROFILER, SOURCES, WELCOME};
    }

    public static EnumEntries<DebuggerFrontendPanelName> getEntries() {
        return $ENTRIES;
    }

    private DebuggerFrontendPanelName(String str, int i, String str2) {
        this.internalName = str2;
    }

    public final String getInternalName() {
        return this.internalName;
    }

    static {
        DebuggerFrontendPanelName[] debuggerFrontendPanelNameArr$values = $values();
        $VALUES = debuggerFrontendPanelNameArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(debuggerFrontendPanelNameArr$values);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.internalName;
    }

    public static DebuggerFrontendPanelName valueOf(String str) {
        return (DebuggerFrontendPanelName) Enum.valueOf(DebuggerFrontendPanelName.class, str);
    }

    public static DebuggerFrontendPanelName[] values() {
        return (DebuggerFrontendPanelName[]) $VALUES.clone();
    }
}
