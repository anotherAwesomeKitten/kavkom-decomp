package com.facebook.react.packagerconnection;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: FileIoHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000fJ\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\fH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/packagerconnection/FileIoHandler;", "Ljava/lang/Runnable;", "<init>", "()V", "nextHandle", "", "handler", "Landroid/os/Handler;", "openFiles", "", "Lcom/facebook/react/packagerconnection/FileIoHandler$TtlFileInputStream;", "requestHandlers", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "handlers", "", "addOpenFile", "filename", "run", "", "TtlFileInputStream", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FileIoHandler implements Runnable {
    private static final Companion Companion = new Companion(null);
    private static final long FILE_TTL = 30000;
    private static final String TAG;
    private final Map<String, RequestHandler> requestHandlers;
    private int nextHandle = 1;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Map<Integer, TtlFileInputStream> openFiles = new LinkedHashMap();

    public FileIoHandler() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.requestHandlers = linkedHashMap;
        linkedHashMap.put("fopen", new RequestOnlyHandler() { // from class: com.facebook.react.packagerconnection.FileIoHandler.1
            @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
            public void onRequest(Object params, Responder responder) {
                JSONObject jSONObject;
                Intrinsics.checkNotNullParameter(responder, "responder");
                Map map = FileIoHandler.this.openFiles;
                FileIoHandler fileIoHandler = FileIoHandler.this;
                synchronized (map) {
                    try {
                        jSONObject = (JSONObject) params;
                    } catch (Exception e) {
                        responder.error(e.toString());
                    }
                    if (jSONObject == null) {
                        throw new Exception("params must be an object { mode: string, filename: string }");
                    }
                    String strOptString = jSONObject.optString("mode");
                    if (strOptString == null) {
                        throw new Exception("missing params.mode");
                    }
                    String strOptString2 = jSONObject.optString("filename");
                    if (strOptString2 == null) {
                        throw new Exception("missing params.filename");
                    }
                    if (Intrinsics.areEqual(strOptString, "r")) {
                        responder.respond(Integer.valueOf(fileIoHandler.addOpenFile(strOptString2)));
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new IllegalArgumentException(("unsupported mode: " + strOptString).toString());
                    }
                }
            }
        });
        linkedHashMap.put("fclose", new RequestOnlyHandler() { // from class: com.facebook.react.packagerconnection.FileIoHandler.2
            @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
            public void onRequest(Object params, Responder responder) {
                Intrinsics.checkNotNullParameter(responder, "responder");
                Map map = FileIoHandler.this.openFiles;
                FileIoHandler fileIoHandler = FileIoHandler.this;
                synchronized (map) {
                    try {
                    } catch (Exception e) {
                        responder.error(e.toString());
                    }
                    if (params instanceof Number) {
                        TtlFileInputStream ttlFileInputStream = (TtlFileInputStream) fileIoHandler.openFiles.get(params);
                        if (ttlFileInputStream != null) {
                            TypeIntrinsics.asMutableMap(fileIoHandler.openFiles).remove(params);
                            ttlFileInputStream.close();
                            responder.respond("");
                            Unit unit = Unit.INSTANCE;
                        } else {
                            throw new Exception("invalid file handle, it might have timed out");
                        }
                    } else {
                        throw new Exception("params must be a file handle");
                    }
                }
            }
        });
        linkedHashMap.put("fread", new RequestOnlyHandler() { // from class: com.facebook.react.packagerconnection.FileIoHandler.3
            @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
            public void onRequest(Object params, Responder responder) {
                JSONObject jSONObject;
                Intrinsics.checkNotNullParameter(responder, "responder");
                Map map = FileIoHandler.this.openFiles;
                FileIoHandler fileIoHandler = FileIoHandler.this;
                synchronized (map) {
                    try {
                        jSONObject = (JSONObject) params;
                    } catch (Exception e) {
                        responder.error(e.toString());
                    }
                    if (jSONObject == null) {
                        throw new Exception("params must be an object { file: handle, size: number }");
                    }
                    int iOptInt = jSONObject.optInt("file");
                    if (iOptInt == 0) {
                        throw new Exception("invalid or missing file handle");
                    }
                    int iOptInt2 = jSONObject.optInt(RRWebVideoEvent.JsonKeys.SIZE);
                    if (iOptInt2 != 0) {
                        TtlFileInputStream ttlFileInputStream = (TtlFileInputStream) fileIoHandler.openFiles.get(Integer.valueOf(iOptInt));
                        if (ttlFileInputStream == null) {
                            throw new Exception("invalid file handle, it might have timed out");
                        }
                        responder.respond(ttlFileInputStream.read(iOptInt2));
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new Exception("invalid or missing read size");
                    }
                }
            }
        });
    }

    /* JADX INFO: compiled from: FileIoHandler.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/react/packagerconnection/FileIoHandler$TtlFileInputStream;", "", "path", "", "<init>", "(Ljava/lang/String;)V", "stream", "Ljava/io/FileInputStream;", "ttl", "", "extendTtl", "", "expiredTtl", "", "read", RRWebVideoEvent.JsonKeys.SIZE, "", "close", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class TtlFileInputStream {
        private final FileInputStream stream;
        private long ttl = System.currentTimeMillis() + 30000;

        public TtlFileInputStream(String str) {
            this.stream = new FileInputStream(str);
        }

        private final void extendTtl() {
            this.ttl = System.currentTimeMillis() + 30000;
        }

        public final boolean expiredTtl() {
            return System.currentTimeMillis() >= this.ttl;
        }

        public final String read(int size) throws IOException {
            extendTtl();
            byte[] bArr = new byte[size];
            String strEncodeToString = Base64.encodeToString(bArr, 0, this.stream.read(bArr), 0);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
            return strEncodeToString;
        }

        public final void close() throws IOException {
            this.stream.close();
        }
    }

    public final Map<String, RequestHandler> handlers() {
        return this.requestHandlers;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int addOpenFile(String filename) throws FileNotFoundException {
        int i = this.nextHandle;
        this.nextHandle = i + 1;
        this.openFiles.put(Integer.valueOf(i), new TtlFileInputStream(filename));
        if (this.openFiles.size() == 1) {
            this.handler.postDelayed(this, 30000L);
        }
        return i;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.openFiles) {
            CollectionsKt.removeAll(this.openFiles.entrySet(), new Function1() { // from class: com.facebook.react.packagerconnection.FileIoHandler$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FileIoHandler.run$lambda$1$lambda$0((Map.Entry) obj));
                }
            });
            if (!this.openFiles.isEmpty()) {
                this.handler.postDelayed(this, 30000L);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean run$lambda$1$lambda$0(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        TtlFileInputStream ttlFileInputStream = (TtlFileInputStream) entry.getValue();
        if (!ttlFileInputStream.expiredTtl()) {
            return false;
        }
        try {
            ttlFileInputStream.close();
            return true;
        } catch (IOException e) {
            FLog.e(TAG, "Failed to close expired file", e);
            return true;
        }
    }

    /* JADX INFO: compiled from: FileIoHandler.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/packagerconnection/FileIoHandler$Companion;", "", "<init>", "()V", "TAG", "", "FILE_TTL", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("JSPackagerClient", "getSimpleName(...)");
        TAG = "JSPackagerClient";
    }
}
