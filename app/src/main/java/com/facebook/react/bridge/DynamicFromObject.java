package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DynamicFromObject.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/facebook/react/bridge/DynamicFromObject;", "Lcom/facebook/react/bridge/Dynamic;", "value", "", "<init>", "(Ljava/lang/Object;)V", "recycle", "", "isNull", "", "()Z", "asBoolean", "asDouble", "", "asInt", "", "asString", "", "asArray", "Lcom/facebook/react/bridge/ReadableArray;", "asMap", "Lcom/facebook/react/bridge/ReadableMap;", "type", "Lcom/facebook/react/bridge/ReadableType;", "getType", "()Lcom/facebook/react/bridge/ReadableType;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DynamicFromObject implements Dynamic {
    private final Object value;

    @Override // com.facebook.react.bridge.Dynamic
    public void recycle() {
    }

    public DynamicFromObject(Object obj) {
        this.value = obj;
    }

    @Override // com.facebook.react.bridge.Dynamic
    public boolean isNull() {
        return this.value == null;
    }

    @Override // com.facebook.react.bridge.Dynamic
    public boolean asBoolean() {
        Object obj = this.value;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        throw new ClassCastException("Dynamic value from Object is not a boolean");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public double asDouble() {
        Object obj = this.value;
        if (obj instanceof Number) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
            return ((Double) obj).doubleValue();
        }
        throw new ClassCastException("Dynamic value from Object is not a number");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public int asInt() {
        Object obj = this.value;
        if (obj instanceof Number) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
            return (int) ((Double) obj).doubleValue();
        }
        throw new ClassCastException("Dynamic value from Object is not a number");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public String asString() {
        Object obj = this.value;
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new ClassCastException("Dynamic value from Object is not a string");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public ReadableArray asArray() {
        Object obj = this.value;
        if (obj instanceof ReadableArray) {
            return (ReadableArray) obj;
        }
        throw new ClassCastException("Dynamic value from Object is not a ReadableArray");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public ReadableMap asMap() {
        Object obj = this.value;
        if (obj instanceof ReadableMap) {
            return (ReadableMap) obj;
        }
        throw new ClassCastException("Dynamic value from Object is not a ReadableMap");
    }

    @Override // com.facebook.react.bridge.Dynamic
    public ReadableType getType() {
        Object obj = this.value;
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (obj instanceof Number) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        FLog.e(ReactConstants.TAG, "Unmapped object type " + obj.getClass().getName());
        return ReadableType.Null;
    }
}
