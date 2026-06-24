package com.facebook.react.bridge;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReadableArrayBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000eJ\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0007J\u001f\u0010\u0011\u001a\u00020\u00072\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00070\u0013¢\u0006\u0002\b\u0015J\u001f\u0010\u0016\u001a\u00020\u00072\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0013¢\u0006\u0002\b\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/bridge/ReadableArrayBuilder;", "", "array", "Lcom/facebook/react/bridge/WritableArray;", "<init>", "(Lcom/facebook/react/bridge/WritableArray;)V", "add", "", "value", "", "", "", "", "", "Lcom/facebook/react/bridge/ReadableMap;", "Lcom/facebook/react/bridge/ReadableArray;", "addNull", "addMap", "builder", "Lkotlin/Function1;", "Lcom/facebook/react/bridge/ReadableMapBuilder;", "Lkotlin/ExtensionFunctionType;", "addArray", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReadableArrayBuilder {
    private final WritableArray array;

    public ReadableArrayBuilder(WritableArray array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
    }

    public final void add(String value) {
        this.array.pushString(value);
    }

    public final void add(int value) {
        this.array.pushInt(value);
    }

    public final void add(boolean value) {
        this.array.pushBoolean(value);
    }

    public final void add(double value) {
        this.array.pushDouble(value);
    }

    public final void add(long value) {
        this.array.pushDouble(value);
    }

    public final void add(ReadableMap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.array.pushMap(value);
    }

    public final void add(ReadableArray value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.array.pushArray(value);
    }

    public final void addNull() {
        this.array.pushNull();
    }

    public final void addMap(Function1<? super ReadableMapBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        WritableArray writableArray = this.array;
        WritableMap writableMapCreateMap = Arguments.createMap();
        builder.invoke(new ReadableMapBuilder(writableMapCreateMap));
        writableArray.pushMap(writableMapCreateMap);
    }

    public final void addArray(Function1<? super ReadableArrayBuilder, Unit> builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        WritableArray writableArray = this.array;
        WritableArray writableArrayCreateArray = Arguments.createArray();
        builder.invoke(new ReadableArrayBuilder(writableArrayCreateArray));
        writableArray.pushArray(writableArrayCreateArray);
    }
}
