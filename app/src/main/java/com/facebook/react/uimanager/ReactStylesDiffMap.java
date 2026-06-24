package com.facebook.react.uimanager;

import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactStylesDiffMap.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u0016\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\rJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\u000bJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u000e\u001a\u00020\u000bJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000e\u001a\u00020\u000bJ\b\u0010\u001e\u001a\u00020\u000bH\u0016R\u0016\u0010\u0006\u001a\u00020\u00038AX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001f"}, d2 = {"Lcom/facebook/react/uimanager/ReactStylesDiffMap;", "", "props", "Lcom/facebook/react/bridge/ReadableMap;", "<init>", "(Lcom/facebook/react/bridge/ReadableMap;)V", "backingMap", "internal_backingMap", "()Lcom/facebook/react/bridge/ReadableMap;", "toMap", "", "", "hasKey", "", "name", "isNull", "getBoolean", Constants.COLLATION_DEFAULT, "getDouble", "", "getFloat", "", "getInt", "", "getString", "getArray", "Lcom/facebook/react/bridge/ReadableArray;", "getMap", "getDynamic", "Lcom/facebook/react/bridge/Dynamic;", "toString", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactStylesDiffMap {
    private final ReadableMap backingMap;

    public ReactStylesDiffMap(ReadableMap props) {
        Intrinsics.checkNotNullParameter(props, "props");
        this.backingMap = props;
    }

    /* JADX INFO: renamed from: internal_backingMap, reason: from getter */
    public final ReadableMap getBackingMap() {
        return this.backingMap;
    }

    public final Map<String, Object> toMap() {
        return this.backingMap.toHashMap();
    }

    public final boolean hasKey(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.hasKey(name);
    }

    public final boolean isNull(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.isNull(name);
    }

    public final boolean getBoolean(String name, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.isNull(name) ? z : this.backingMap.getBoolean(name);
    }

    public final double getDouble(String name, double d) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.isNull(name) ? d : this.backingMap.getDouble(name);
    }

    public final float getFloat(String name, float f) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.isNull(name) ? f : (float) this.backingMap.getDouble(name);
    }

    public final int getInt(String name, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.isNull(name) ? i : this.backingMap.getInt(name);
    }

    public final String getString(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.getString(name);
    }

    public final ReadableArray getArray(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.getArray(name);
    }

    public final ReadableMap getMap(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.getMap(name);
    }

    public final Dynamic getDynamic(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.backingMap.getDynamic(name);
    }

    public String toString() {
        return "{ " + getClass().getSimpleName() + ": " + this.backingMap + " }";
    }
}
