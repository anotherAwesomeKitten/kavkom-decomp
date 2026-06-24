package com.facebook.react.devsupport;

import androidx.core.app.NotificationCompat;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.MultipartStreamReader;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.google.common.net.HttpHeaders;
import io.sentry.protocol.Response;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.DeprecatedUpgrade;
import okio.Sink;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: BundleDownloader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0002 !B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J6\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007J:\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002JB\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader;", "", "client", "Lokhttp3/OkHttpClient;", "<init>", "(Lokhttp3/OkHttpClient;)V", "downloadBundleFromURLCall", "Lokhttp3/Call;", "downloadBundleFromURL", "", "callback", "Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "outputFile", "Ljava/io/File;", "bundleURL", "", "bundleInfo", "Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "requestBuilder", "Lokhttp3/Request$Builder;", "processMultipartResponse", "url", Response.TYPE, "Lokhttp3/Response;", "boundary", "processBundleResult", "statusCode", "", "headers", "Lokhttp3/Headers;", "body", "Lokio/BufferedSource;", "BundleInfo", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BundleDownloader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int FILES_CHANGED_COUNT_NOT_BUILT_BY_BUNDLER = -2;
    private static final String TAG = "BundleDownloader";
    private final OkHttpClient client;
    private Call downloadBundleFromURLCall;

    public final void downloadBundleFromURL(DevBundleDownloadListener callback, File outputFile, String str, BundleInfo bundleInfo) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        downloadBundleFromURL$default(this, callback, outputFile, str, bundleInfo, null, 16, null);
    }

    public BundleDownloader(OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
    }

    /* JADX INFO: compiled from: BundleDownloader.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "", "<init>", "()V", "_url", "", "get_url$ReactAndroid_release", "()Ljava/lang/String;", "set_url$ReactAndroid_release", "(Ljava/lang/String;)V", "url", "getUrl", "value", "", "filesChangedCount", "getFilesChangedCount", "()I", "setFilesChangedCount$ReactAndroid_release", "(I)V", "toJSONString", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class BundleInfo {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String _url;
        private int filesChangedCount;

        @JvmStatic
        public static final BundleInfo fromJSONString(String str) {
            return INSTANCE.fromJSONString(str);
        }

        /* JADX INFO: renamed from: get_url$ReactAndroid_release, reason: from getter */
        public final String get_url() {
            return this._url;
        }

        public final void set_url$ReactAndroid_release(String str) {
            this._url = str;
        }

        public final String getUrl() {
            String str = this._url;
            return str == null ? "unknown" : str;
        }

        public final int getFilesChangedCount() {
            return this.filesChangedCount;
        }

        public final void setFilesChangedCount$ReactAndroid_release(int i) {
            this.filesChangedCount = i;
        }

        public final String toJSONString() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("url", this._url);
                jSONObject.put("filesChangedCount", this.filesChangedCount);
                return jSONObject.toString();
            } catch (JSONException e) {
                FLog.e(BundleDownloader.TAG, "Can't serialize bundle info: ", e);
                return null;
            }
        }

        /* JADX INFO: compiled from: BundleDownloader.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo$Companion;", "", "<init>", "()V", "fromJSONString", "Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "jsonStr", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final BundleInfo fromJSONString(String jsonStr) {
                if (jsonStr == null) {
                    return null;
                }
                try {
                    JSONObject jSONObject = new JSONObject(jsonStr);
                    BundleInfo bundleInfo = new BundleInfo();
                    bundleInfo.set_url$ReactAndroid_release(jSONObject.getString("url"));
                    bundleInfo.setFilesChangedCount$ReactAndroid_release(jSONObject.getInt("filesChangedCount"));
                    return bundleInfo;
                } catch (JSONException e) {
                    FLog.e(BundleDownloader.TAG, "Invalid bundle info: ", e);
                    return null;
                }
            }
        }
    }

    public static /* synthetic */ void downloadBundleFromURL$default(BundleDownloader bundleDownloader, DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleInfo bundleInfo, Request.Builder builder, int i, Object obj) {
        if ((i & 16) != 0) {
            builder = new Request.Builder();
        }
        bundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, builder);
    }

    public final void downloadBundleFromURL(DevBundleDownloadListener callback, File outputFile, String bundleURL, BundleInfo bundleInfo, Request.Builder requestBuilder) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        if (bundleURL == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        Call callNewCall = this.client.newCall(requestBuilder.url(bundleURL).addHeader(HttpHeaders.ACCEPT, "multipart/mixed").build());
        this.downloadBundleFromURLCall = callNewCall;
        if (callNewCall == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        callNewCall.enqueue(new Callback() { // from class: com.facebook.react.devsupport.BundleDownloader.downloadBundleFromURL.1
            final /* synthetic */ BundleInfo $bundleInfo;
            final /* synthetic */ DevBundleDownloadListener $callback;
            final /* synthetic */ File $outputFile;

            AnonymousClass1(DevBundleDownloadListener callback2, File outputFile2, BundleInfo bundleInfo2) {
                devBundleDownloadListener = callback2;
                file = outputFile2;
                bundleInfo = bundleInfo2;
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Call call2;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                if (BundleDownloader.this.downloadBundleFromURLCall == null || ((call2 = BundleDownloader.this.downloadBundleFromURLCall) != null && call2.getCanceled())) {
                    BundleDownloader.this.downloadBundleFromURLCall = null;
                    return;
                }
                BundleDownloader.this.downloadBundleFromURLCall = null;
                String url = call.request().getUrl().getUrl();
                devBundleDownloadListener.onFailure(DebugServerException.INSTANCE.makeGeneric(url, "Could not connect to development server.", "URL: " + url, e));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Call call2;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                ResponseBody body = response;
                BundleDownloader bundleDownloader = BundleDownloader.this;
                File file = file;
                BundleInfo bundleInfo2 = bundleInfo;
                DevBundleDownloadListener devBundleDownloadListener = devBundleDownloadListener;
                try {
                    okhttp3.Response response2 = body;
                    if (bundleDownloader.downloadBundleFromURLCall != null && ((call2 = bundleDownloader.downloadBundleFromURLCall) == null || !call2.getCanceled())) {
                        bundleDownloader.downloadBundleFromURLCall = null;
                        String url = response2.getRequest().getUrl().getUrl();
                        String strHeader$default = okhttp3.Response.header$default(response2, "content-type", null, 2, null);
                        if (strHeader$default == null) {
                            strHeader$default = "";
                        }
                        Matcher matcher = Pattern.compile("multipart/mixed;.*boundary=\"([^\"]+)\"").matcher(strHeader$default);
                        if (strHeader$default.length() > 0 && matcher.find()) {
                            String str = (String) Assertions.assertNotNull(matcher.group(1));
                            Intrinsics.checkNotNull(str);
                            bundleDownloader.processMultipartResponse(url, response2, str, file, bundleInfo2, devBundleDownloadListener);
                        } else {
                            body = response2.getBody();
                            try {
                                ResponseBody responseBody = body;
                                if (responseBody != null) {
                                    bundleDownloader.processBundleResult(url, response2.getCode(), response2.getHeaders(), responseBody.getSource(), file, bundleInfo2, devBundleDownloadListener);
                                }
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(body, null);
                            } finally {
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(body, null);
                        return;
                    }
                    bundleDownloader.downloadBundleFromURLCall = null;
                    CloseableKt.closeFinally(body, null);
                } finally {
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.devsupport.BundleDownloader$downloadBundleFromURL$1 */
    /* JADX INFO: compiled from: BundleDownloader.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/facebook/react/devsupport/BundleDownloader$downloadBundleFromURL$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", Response.TYPE, "Lokhttp3/Response;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements Callback {
        final /* synthetic */ BundleInfo $bundleInfo;
        final /* synthetic */ DevBundleDownloadListener $callback;
        final /* synthetic */ File $outputFile;

        AnonymousClass1(DevBundleDownloadListener callback2, File outputFile2, BundleInfo bundleInfo2) {
            devBundleDownloadListener = callback2;
            file = outputFile2;
            bundleInfo = bundleInfo2;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException e) {
            Call call2;
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(e, "e");
            if (BundleDownloader.this.downloadBundleFromURLCall == null || ((call2 = BundleDownloader.this.downloadBundleFromURLCall) != null && call2.getCanceled())) {
                BundleDownloader.this.downloadBundleFromURLCall = null;
                return;
            }
            BundleDownloader.this.downloadBundleFromURLCall = null;
            String url = call.request().getUrl().getUrl();
            devBundleDownloadListener.onFailure(DebugServerException.INSTANCE.makeGeneric(url, "Could not connect to development server.", "URL: " + url, e));
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, okhttp3.Response response) throws IOException {
            Call call2;
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(response, "response");
            ResponseBody body = response;
            BundleDownloader bundleDownloader = BundleDownloader.this;
            File file = file;
            BundleInfo bundleInfo2 = bundleInfo;
            DevBundleDownloadListener devBundleDownloadListener = devBundleDownloadListener;
            try {
                okhttp3.Response response2 = body;
                if (bundleDownloader.downloadBundleFromURLCall != null && ((call2 = bundleDownloader.downloadBundleFromURLCall) == null || !call2.getCanceled())) {
                    bundleDownloader.downloadBundleFromURLCall = null;
                    String url = response2.getRequest().getUrl().getUrl();
                    String strHeader$default = okhttp3.Response.header$default(response2, "content-type", null, 2, null);
                    if (strHeader$default == null) {
                        strHeader$default = "";
                    }
                    Matcher matcher = Pattern.compile("multipart/mixed;.*boundary=\"([^\"]+)\"").matcher(strHeader$default);
                    if (strHeader$default.length() > 0 && matcher.find()) {
                        String str = (String) Assertions.assertNotNull(matcher.group(1));
                        Intrinsics.checkNotNull(str);
                        bundleDownloader.processMultipartResponse(url, response2, str, file, bundleInfo2, devBundleDownloadListener);
                    } else {
                        body = response2.getBody();
                        try {
                            ResponseBody responseBody = body;
                            if (responseBody != null) {
                                bundleDownloader.processBundleResult(url, response2.getCode(), response2.getHeaders(), responseBody.getSource(), file, bundleInfo2, devBundleDownloadListener);
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(body, null);
                        } finally {
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(body, null);
                    return;
                }
                bundleDownloader.downloadBundleFromURLCall = null;
                CloseableKt.closeFinally(body, null);
            } finally {
            }
        }
    }

    public final void processMultipartResponse(final String url, final okhttp3.Response response, String boundary, final File outputFile, final BundleInfo bundleInfo, final DevBundleDownloadListener callback) throws IOException {
        if (response.getBody() == null) {
            callback.onFailure(new DebugServerException(StringsKt.trimIndent("\n                    Error while reading multipart response.\n                    \n                    Response body was empty: " + response.getCode() + "\n                    \n                    URL: " + url + "\n                    \n                    \n                    ")));
            return;
        }
        ResponseBody body = response.getBody();
        BufferedSource source = body != null ? body.getSource() : null;
        if (source == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (new MultipartStreamReader(source, boundary).readAllParts(new MultipartStreamReader.ChunkListener() { // from class: com.facebook.react.devsupport.BundleDownloader$processMultipartResponse$completed$1
            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkComplete(Map<String, String> headers, Buffer body2, boolean isLastChunk) throws IOException {
                Intrinsics.checkNotNullParameter(headers, "headers");
                Intrinsics.checkNotNullParameter(body2, "body");
                if (isLastChunk) {
                    int code = response.getCode();
                    if (headers.containsKey("X-Http-Status")) {
                        code = Integer.parseInt(headers.getOrDefault("X-Http-Status", "0"));
                    }
                    this.processBundleResult(url, code, Headers.INSTANCE.m2565deprecated_of(headers), body2, outputFile, bundleInfo, callback);
                    return;
                }
                if (headers.containsKey(HttpHeaders.CONTENT_TYPE) && Intrinsics.areEqual(headers.get(HttpHeaders.CONTENT_TYPE), "application/json")) {
                    try {
                        JSONObject jSONObject = new JSONObject(body2.readUtf8());
                        callback.onProgress(jSONObject.has("status") ? jSONObject.getString("status") : "Bundling", jSONObject.has("done") ? Integer.valueOf(jSONObject.getInt("done")) : null, jSONObject.has("total") ? Integer.valueOf(jSONObject.getInt("total")) : null);
                    } catch (JSONException e) {
                        FLog.e(ReactConstants.TAG, "Error parsing progress JSON. " + e);
                    }
                }
            }

            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkProgress(Map<String, String> headers, long loaded, long total) {
                Intrinsics.checkNotNullParameter(headers, "headers");
                if (Intrinsics.areEqual("application/javascript", headers.get(HttpHeaders.CONTENT_TYPE))) {
                    long j = 1024;
                    callback.onProgress("Downloading", Integer.valueOf((int) (loaded / j)), Integer.valueOf((int) (total / j)));
                }
            }
        })) {
            return;
        }
        callback.onFailure(new DebugServerException(StringsKt.trimIndent("\n                    Error while reading multipart response.\n                    \n                    Response code: " + response.getCode() + "\n                    \n                    URL: " + url + "\n                    \n                    \n                    ")));
    }

    public final void processBundleResult(String url, int statusCode, Headers headers, BufferedSource body, File outputFile, BundleInfo bundleInfo, DevBundleDownloadListener callback) throws IOException {
        if (statusCode != 200) {
            String utf8 = body.readUtf8();
            DebugServerException debugServerException = DebugServerException.INSTANCE.parse(url, utf8);
            if (debugServerException != null) {
                callback.onFailure(debugServerException);
                return;
            }
            StringBuilder sb = new StringBuilder("The development server returned response error code: ");
            sb.append(statusCode).append("\n\nURL: ").append(url).append("\n\nBody:\n").append(utf8);
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            callback.onFailure(new DebugServerException(string));
            return;
        }
        if (bundleInfo != null) {
            INSTANCE.populateBundleInfo(url, headers, bundleInfo);
        }
        File file = new File(outputFile.getPath() + DefaultDiskStorage.FileType.TEMP);
        if (INSTANCE.storePlainJSInFile(body, file) && !file.renameTo(outputFile)) {
            throw new IOException("Couldn't rename " + file + " to " + outputFile);
        }
        callback.onSuccess();
    }

    /* JADX INFO: compiled from: BundleDownloader.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/devsupport/BundleDownloader$Companion;", "", "<init>", "()V", "TAG", "", "FILES_CHANGED_COUNT_NOT_BUILT_BY_BUNDLER", "", "storePlainJSInFile", "", "body", "Lokio/BufferedSource;", "outputFile", "Ljava/io/File;", "populateBundleInfo", "", "url", "headers", "Lokhttp3/Headers;", "bundleInfo", "Lcom/facebook/react/devsupport/BundleDownloader$BundleInfo;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean storePlainJSInFile(BufferedSource body, File outputFile) throws IOException {
            Sink sink = DeprecatedUpgrade.getOkio().sink(outputFile);
            try {
                body.readAll(sink);
                CloseableKt.closeFinally(sink, null);
                return true;
            } finally {
            }
        }

        public final void populateBundleInfo(String url, Headers headers, BundleInfo bundleInfo) {
            bundleInfo.set_url$ReactAndroid_release(url);
            String str = headers.get("X-Metro-Files-Changed-Count");
            if (str != null) {
                try {
                    bundleInfo.setFilesChangedCount$ReactAndroid_release(Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    bundleInfo.setFilesChangedCount$ReactAndroid_release(-2);
                    FLog.e(BundleDownloader.TAG, "Can't populate bundle info: ", e);
                }
            }
        }
    }
}
