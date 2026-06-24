package com.facebook.react.modules.network;

import com.facebook.soloader.SoLoader;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: InspectorNetworkReporter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0004\u001a\u00020\u0005H\u0087 JE\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087 J%\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\rH\u0087 J=\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0017\u001a\u00020\u0010H\u0087 J\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0007J\u0019\u0010\u001a\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0015H\u0087 J\u0019\u0010\u001c\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087 J\u0019\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0005H\u0087 J \u0010\u001f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\u0005H\u0007J!\u0010\"\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\u0005H\u0087 J\u0018\u0010#\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0007J\u0019\u0010$\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0087 ¨\u0006%"}, d2 = {"Lcom/facebook/react/modules/network/InspectorNetworkReporter;", "", "<init>", "()V", "isDebuggingEnabled", "", "reportRequestStart", "", "requestId", "", "requestUrl", "requestMethod", "requestHeaders", "", "requestBody", "encodedDataLength", "", "reportConnectionTiming", "headers", "reportResponseStart", "responseStatus", "", "responseHeaders", "expectedDataLength", "reportDataReceived", "data", "reportDataReceivedImpl", "dataLength", "reportResponseEnd", "reportRequestFailed", "cancelled", "maybeStoreResponseBody", "body", "base64Encoded", "maybeStoreResponseBodyImpl", "maybeStoreResponseBodyIncremental", "maybeStoreResponseBodyIncrementalImpl", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InspectorNetworkReporter {
    public static final InspectorNetworkReporter INSTANCE = new InspectorNetworkReporter();

    @JvmStatic
    public static final native boolean isDebuggingEnabled();

    @JvmStatic
    public static final native void maybeStoreResponseBodyImpl(String requestId, String body, boolean base64Encoded);

    @JvmStatic
    public static final native void maybeStoreResponseBodyIncrementalImpl(String requestId, String data);

    @JvmStatic
    public static final native void reportConnectionTiming(String requestId, Map<String, String> headers);

    @JvmStatic
    public static final native void reportDataReceivedImpl(String requestId, int dataLength);

    @JvmStatic
    public static final native void reportRequestFailed(String requestId, boolean cancelled);

    @JvmStatic
    public static final native void reportRequestStart(String requestId, String requestUrl, String requestMethod, Map<String, String> requestHeaders, String requestBody, long encodedDataLength);

    @JvmStatic
    public static final native void reportResponseEnd(String requestId, long encodedDataLength);

    @JvmStatic
    public static final native void reportResponseStart(String requestId, String requestUrl, int responseStatus, Map<String, String> responseHeaders, long expectedDataLength);

    private InspectorNetworkReporter() {
    }

    static {
        SoLoader.loadLibrary("react_devsupportjni");
    }

    @JvmStatic
    public static final void reportDataReceived(String requestId, String data) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(data, "data");
        if (isDebuggingEnabled()) {
            reportDataReceivedImpl(requestId, StringsKt.encodeToByteArray(data).length);
        }
    }

    @JvmStatic
    public static final void maybeStoreResponseBody(String requestId, String body, boolean base64Encoded) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(body, "body");
        if (isDebuggingEnabled()) {
            maybeStoreResponseBodyImpl(requestId, body, base64Encoded);
        }
    }

    @JvmStatic
    public static final void maybeStoreResponseBodyIncremental(String requestId, String data) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(data, "data");
        if (isDebuggingEnabled()) {
            maybeStoreResponseBodyIncrementalImpl(requestId, data);
        }
    }
}
