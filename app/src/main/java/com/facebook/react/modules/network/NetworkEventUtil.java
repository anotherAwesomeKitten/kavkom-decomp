package com.facebook.react.modules.network;

import android.os.Bundle;
import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArrayBuilder;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.google.firebase.messaging.Constants;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Response;
import java.net.SocketTimeoutException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: NetworkEventUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J*\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007J<\u0010\u0012\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007J*\u0010\u0014\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007J4\u0010\u0015\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0007J2\u0010\u0015\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J6\u0010\u001a\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J*\u0010\u001e\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0010H\u0007J4\u0010 \u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020#H\u0007J>\u0010 \u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010\u00172\b\u0010&\u001a\u0004\u0018\u00010\u0007H\u0007J\u001c\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070(2\u0006\u0010%\u001a\u00020)H\u0002¨\u0006*"}, d2 = {"Lcom/facebook/react/modules/network/NetworkEventUtil;", "", "<init>", "()V", "onCreateRequest", "", "devToolsRequestId", "", SentryBaseEvent.JsonKeys.REQUEST, "Lokhttp3/Request;", "onDataSend", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "requestId", "", "progress", "", "total", "onIncrementalDataReceived", "data", "onDataReceivedProgress", "onDataReceived", "responseType", "Lcom/facebook/react/bridge/WritableMap;", Constants.MessagePayloadKeys.RAW_DATA, "", "onRequestError", "error", "e", "", "onRequestSuccess", "encodedDataLength", "onResponseReceived", "requestUrl", Response.TYPE, "Lokhttp3/Response;", "statusCode", "headers", "url", "okHttpHeadersToMap", "", "Lokhttp3/Headers;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NetworkEventUtil {
    public static final NetworkEventUtil INSTANCE = new NetworkEventUtil();

    private NetworkEventUtil() {
    }

    @JvmStatic
    public static final void onCreateRequest(String devToolsRequestId, Request request) {
        String bodyPreview;
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        Intrinsics.checkNotNullParameter(request, "request");
        if (ReactNativeFeatureFlags.enableNetworkEventReporting()) {
            Map<String, String> mapOkHttpHeadersToMap = INSTANCE.okHttpHeadersToMap(request.getHeaders());
            String str = "";
            if (ReactBuildConfig.DEBUG) {
                RequestBody body = request.getBody();
                ProgressRequestBody progressRequestBody = body instanceof ProgressRequestBody ? (ProgressRequestBody) body : null;
                if (progressRequestBody == null || (bodyPreview = progressRequestBody.getBodyPreview()) == null) {
                    RequestBody body2 = request.getBody();
                    String string = body2 != null ? body2.toString() : null;
                    if (string != null) {
                        str = string;
                    }
                } else {
                    str = bodyPreview;
                }
            }
            String str2 = str;
            String url = request.getUrl().getUrl();
            String method = request.getMethod();
            RequestBody body3 = request.getBody();
            InspectorNetworkReporter.reportRequestStart(devToolsRequestId, url, method, mapOkHttpHeadersToMap, str2, body3 != null ? body3.contentLength() : 0L);
            InspectorNetworkReporter.reportConnectionTiming(devToolsRequestId, mapOkHttpHeadersToMap);
        }
    }

    @JvmStatic
    public static final void onIncrementalDataReceived(ReactApplicationContext reactContext, int requestId, String devToolsRequestId, String data, long progress, long total) {
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        if (ReactNativeFeatureFlags.enableNetworkEventReporting() && data != null) {
            InspectorNetworkReporter.reportDataReceived(devToolsRequestId, data);
            InspectorNetworkReporter.maybeStoreResponseBodyIncremental(devToolsRequestId, data);
        }
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            ReadableArrayBuilder readableArrayBuilder = new ReadableArrayBuilder(writableArrayCreateArray);
            readableArrayBuilder.add(requestId);
            readableArrayBuilder.add(data);
            readableArrayBuilder.add((int) progress);
            readableArrayBuilder.add((int) total);
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didReceiveNetworkIncrementalData", writableArrayCreateArray);
        }
    }

    @JvmStatic
    public static final void onDataReceived(ReactApplicationContext reactContext, int requestId, String devToolsRequestId, String data, String responseType) {
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        Intrinsics.checkNotNullParameter(responseType, "responseType");
        if (ReactNativeFeatureFlags.enableNetworkEventReporting()) {
            InspectorNetworkReporter.maybeStoreResponseBody(devToolsRequestId, data == null ? "" : data, Intrinsics.areEqual(responseType, "base64"));
        }
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            ReadableArrayBuilder readableArrayBuilder = new ReadableArrayBuilder(writableArrayCreateArray);
            readableArrayBuilder.add(requestId);
            readableArrayBuilder.add(data);
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didReceiveNetworkData", writableArrayCreateArray);
        }
    }

    @JvmStatic
    public static final void onDataReceived(ReactApplicationContext reactContext, int requestId, String devToolsRequestId, WritableMap data, byte[] rawData) {
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        if (ReactNativeFeatureFlags.enableNetworkEventReporting()) {
            String strEncodeToString = Base64.encodeToString(rawData, 2);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
            InspectorNetworkReporter.maybeStoreResponseBody(devToolsRequestId, strEncodeToString, true);
        }
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            writableArrayCreateArray.pushInt(requestId);
            writableArrayCreateArray.pushMap(data);
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didReceiveNetworkData", writableArrayCreateArray);
        }
    }

    @JvmStatic
    public static final void onRequestError(ReactApplicationContext reactContext, int requestId, String devToolsRequestId, String error, Throwable e) {
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        if (ReactNativeFeatureFlags.enableNetworkEventReporting()) {
            InspectorNetworkReporter.reportRequestFailed(devToolsRequestId, false);
        }
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            ReadableArrayBuilder readableArrayBuilder = new ReadableArrayBuilder(writableArrayCreateArray);
            readableArrayBuilder.add(requestId);
            readableArrayBuilder.add(error);
            if (Intrinsics.areEqual(e != null ? e.getClass() : null, SocketTimeoutException.class)) {
                readableArrayBuilder.add(true);
            }
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didCompleteNetworkResponse", writableArrayCreateArray);
        }
    }

    @JvmStatic
    public static final void onRequestSuccess(ReactApplicationContext reactContext, int requestId, String devToolsRequestId, long encodedDataLength) {
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        if (ReactNativeFeatureFlags.enableNetworkEventReporting()) {
            InspectorNetworkReporter.reportResponseEnd(devToolsRequestId, encodedDataLength);
        }
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            ReadableArrayBuilder readableArrayBuilder = new ReadableArrayBuilder(writableArrayCreateArray);
            readableArrayBuilder.add(requestId);
            readableArrayBuilder.addNull();
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didCompleteNetworkResponse", writableArrayCreateArray);
        }
    }

    @JvmStatic
    public static final void onResponseReceived(ReactApplicationContext reactContext, int requestId, String devToolsRequestId, String requestUrl, okhttp3.Response response) {
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        Intrinsics.checkNotNullParameter(response, "response");
        Map<String, String> mapOkHttpHeadersToMap = INSTANCE.okHttpHeadersToMap(response.getHeaders());
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : mapOkHttpHeadersToMap.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        if (ReactNativeFeatureFlags.enableNetworkEventReporting()) {
            String str = requestUrl == null ? "" : requestUrl;
            int code = response.getCode();
            ResponseBody body = response.getBody();
            InspectorNetworkReporter.reportResponseStart(devToolsRequestId, str, code, mapOkHttpHeadersToMap, body != null ? body.getContentLength() : 0L);
        }
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            writableArrayCreateArray.pushInt(requestId);
            writableArrayCreateArray.pushInt(response.getCode());
            writableArrayCreateArray.pushMap(Arguments.fromBundle(bundle));
            writableArrayCreateArray.pushString(requestUrl);
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didReceiveNetworkResponse", writableArrayCreateArray);
        }
    }

    @Deprecated(message = "Compatibility overload")
    @JvmStatic
    public static final void onResponseReceived(ReactApplicationContext reactContext, int requestId, String devToolsRequestId, int statusCode, WritableMap headers, String url) {
        Intrinsics.checkNotNullParameter(devToolsRequestId, "devToolsRequestId");
        Headers.Builder builder = new Headers.Builder();
        if (headers != null) {
            ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = headers.keySetIterator();
            while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
                String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
                String string = headers.getString(strNextKey);
                if (string != null) {
                    builder.add(strNextKey, string);
                }
            }
        }
        onResponseReceived(reactContext, requestId, devToolsRequestId, url, new Response.Builder().protocol(Protocol.HTTP_1_1).request(new Request.Builder().url(url == null ? "" : url).build()).headers(builder.build()).code(statusCode).message("").build());
    }

    private final Map<String, String> okHttpHeadersToMap(Headers headers) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int iM2564deprecated_size = headers.m2564deprecated_size();
        for (int i = 0; i < iM2564deprecated_size; i++) {
            String strName = headers.name(i);
            if (linkedHashMap.containsKey(strName)) {
                linkedHashMap.put(strName, linkedHashMap.get(strName) + ", " + headers.value(i));
            } else {
                linkedHashMap.put(strName, headers.value(i));
            }
        }
        return linkedHashMap;
    }

    @JvmStatic
    public static final void onDataSend(ReactApplicationContext reactContext, int requestId, long progress, long total) {
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            ReadableArrayBuilder readableArrayBuilder = new ReadableArrayBuilder(writableArrayCreateArray);
            readableArrayBuilder.add(requestId);
            readableArrayBuilder.add((int) progress);
            readableArrayBuilder.add((int) total);
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didSendNetworkData", writableArrayCreateArray);
        }
    }

    @JvmStatic
    public static final void onDataReceivedProgress(ReactApplicationContext reactContext, int requestId, long progress, long total) {
        if (reactContext != null) {
            WritableArray writableArrayCreateArray = Arguments.createArray();
            ReadableArrayBuilder readableArrayBuilder = new ReadableArrayBuilder(writableArrayCreateArray);
            readableArrayBuilder.add(requestId);
            readableArrayBuilder.add((int) progress);
            readableArrayBuilder.add((int) total);
            Unit unit = Unit.INSTANCE;
            reactContext.emitDeviceEvent("didReceiveNetworkDataProgress", writableArrayCreateArray);
        }
    }
}
