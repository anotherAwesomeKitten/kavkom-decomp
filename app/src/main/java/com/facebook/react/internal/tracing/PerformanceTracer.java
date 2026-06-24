package com.facebook.react.internal.tracing;

import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: PerformanceTracer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0087 J+\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0087 J?\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0087 J\t\u0010\u0015\u001a\u00020\u0016H\u0087 J\u0011\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0087 J\u0011\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0018H\u0087 ¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/internal/tracing/PerformanceTracer;", "", "<init>", "()V", "reportMark", "", "name", "", "timestampNanos", "", "detail", "Lcom/facebook/react/bridge/ReadableNativeMap;", "reportMeasure", "startTimestampNanos", "durationNanos", "reportTimeStamp", "startTimeNanos", "endTimeNanos", "trackName", "trackGroup", "color", "isTracing", "", "subscribeToTracingStateChanges", "", "callback", "Lcom/facebook/react/internal/tracing/PerformanceTracer$TracingStateCallback;", "unsubscribeFromTracingStateChanges", "subscriptionId", "TracingStateCallback", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PerformanceTracer {
    public static final PerformanceTracer INSTANCE = new PerformanceTracer();

    /* JADX INFO: compiled from: PerformanceTracer.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/internal/tracing/PerformanceTracer$TracingStateCallback;", "", "onTracingStateChanged", "", "isTracing", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface TracingStateCallback {
        void onTracingStateChanged(boolean isTracing);
    }

    @JvmStatic
    public static final native boolean isTracing();

    @JvmStatic
    public static final native void reportMark(String name, long timestampNanos, ReadableNativeMap detail);

    @JvmStatic
    public static final native void reportMeasure(String name, long startTimestampNanos, long durationNanos, ReadableNativeMap detail);

    @JvmStatic
    public static final native void reportTimeStamp(String name, long startTimeNanos, long endTimeNanos, String trackName, String trackGroup, String color);

    @JvmStatic
    public static final native int subscribeToTracingStateChanges(TracingStateCallback callback);

    @JvmStatic
    public static final native void unsubscribeFromTracingStateChanges(int subscriptionId);

    private PerformanceTracer() {
    }

    static {
        SoLoader.loadLibrary("react_performancetracerjni");
    }
}
