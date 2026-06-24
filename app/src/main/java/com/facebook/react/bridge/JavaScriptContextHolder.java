package com.facebook.react.bridge;

import kotlin.Metadata;

/* JADX INFO: compiled from: JavaScriptContextHolder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0007J\u0006\u0010\u0007\u001a\u00020\bR\u0012\u0010\u0002\u001a\u00020\u00038\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/react/bridge/JavaScriptContextHolder;", "", "context", "", "<init>", "(J)V", "get", "clear", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class JavaScriptContextHolder {
    private long context;

    public JavaScriptContextHolder(long j) {
        this.context = j;
    }

    /* JADX INFO: renamed from: get, reason: from getter */
    public final long getContext() {
        return this.context;
    }

    public final synchronized void clear() {
        this.context = 0L;
    }
}
