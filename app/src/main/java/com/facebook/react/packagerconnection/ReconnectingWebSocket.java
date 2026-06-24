package com.facebook.react.packagerconnection;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.logging.FLog;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import io.sentry.clientreport.DiscardedEvent;
import io.sentry.protocol.Response;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/* JADX INFO: compiled from: ReconnectingWebSocket.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 -2\u00020\u0001:\u0003+,-B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0006\u0010\u0017\u001a\u00020\u0014J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\"\u0010 \u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0018\u0010\"\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0003H\u0016J\u0018\u0010\"\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%H\u0016J \u0010&\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0003H\u0016J\u000e\u0010*\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0003J\u000e\u0010*\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/facebook/react/packagerconnection/ReconnectingWebSocket;", "Lokhttp3/WebSocketListener;", "url", "", "messageCallback", "Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$MessageCallback;", "connectionCallback", "Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$ConnectionCallback;", "<init>", "(Ljava/lang/String;Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$MessageCallback;Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$ConnectionCallback;)V", "handler", "Landroid/os/Handler;", "okHttpClient", "Lokhttp3/OkHttpClient;", "closed", "", "suppressConnectionErrors", "webSocket", "Lokhttp3/WebSocket;", "connect", "", "delayedReconnect", "reconnect", "closeQuietly", "closeWebSocketQuietly", "abort", "message", "cause", "", "onOpen", Response.TYPE, "Lokhttp3/Response;", "onFailure", "t", "onMessage", ReactTextInputShadowNode.PROP_TEXT, "bytes", "Lokio/ByteString;", "onClosed", "code", "", DiscardedEvent.JsonKeys.REASON, "sendMessage", "MessageCallback", "ConnectionCallback", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReconnectingWebSocket extends WebSocketListener {
    private static final Companion Companion = new Companion(null);
    private static final long RECONNECT_DELAY_MS = 2000;
    private static final String TAG;
    private boolean closed;
    private final ConnectionCallback connectionCallback;
    private final Handler handler;
    private MessageCallback messageCallback;
    private final OkHttpClient okHttpClient;
    private boolean suppressConnectionErrors;
    private final String url;
    private WebSocket webSocket;

    /* JADX INFO: compiled from: ReconnectingWebSocket.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$ConnectionCallback;", "", "onConnected", "", "onDisconnected", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface ConnectionCallback {
        void onConnected();

        void onDisconnected();
    }

    /* JADX INFO: compiled from: ReconnectingWebSocket.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$MessageCallback;", "", "onMessage", "", ReactTextInputShadowNode.PROP_TEXT, "", "bytes", "Lokio/ByteString;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface MessageCallback {
        void onMessage(String str);

        void onMessage(ByteString bytes);
    }

    public ReconnectingWebSocket(String url, MessageCallback messageCallback, ConnectionCallback connectionCallback) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.messageCallback = messageCallback;
        this.connectionCallback = connectionCallback;
        this.handler = new Handler(Looper.getMainLooper());
        this.okHttpClient = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).readTimeout(0L, TimeUnit.MINUTES).build();
    }

    public final void connect() {
        if (this.closed) {
            throw new IllegalStateException("Can't connect closed client".toString());
        }
        this.okHttpClient.newWebSocket(new Request.Builder().url(this.url).build(), this);
    }

    public final synchronized void delayedReconnect() {
        if (!this.closed) {
            connect();
        }
    }

    private final void reconnect() {
        if (this.closed) {
            throw new IllegalStateException("Can't reconnect closed client".toString());
        }
        if (!this.suppressConnectionErrors) {
            FLog.w(TAG, "Couldn't connect to \"" + this.url + "\", will silently retry");
            this.suppressConnectionErrors = true;
        }
        this.handler.postDelayed(new Runnable() { // from class: com.facebook.react.packagerconnection.ReconnectingWebSocket$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.delayedReconnect();
            }
        }, RECONNECT_DELAY_MS);
    }

    public final void closeQuietly() {
        this.closed = true;
        closeWebSocketQuietly();
        this.messageCallback = null;
        ConnectionCallback connectionCallback = this.connectionCallback;
        if (connectionCallback != null) {
            connectionCallback.onDisconnected();
        }
    }

    private final void closeWebSocketQuietly() {
        try {
            WebSocket webSocket = this.webSocket;
            if (webSocket != null) {
                webSocket.close(1000, "End of session");
            }
        } catch (Exception unused) {
        }
        this.webSocket = null;
    }

    private final void abort(String message, Throwable cause) {
        FLog.e(TAG, "Error occurred, shutting down websocket connection: " + message, cause);
        closeWebSocketQuietly();
    }

    @Override // okhttp3.WebSocketListener
    public synchronized void onOpen(WebSocket webSocket, okhttp3.Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
        this.webSocket = webSocket;
        this.suppressConnectionErrors = false;
        ConnectionCallback connectionCallback = this.connectionCallback;
        if (connectionCallback != null) {
            connectionCallback.onConnected();
        }
    }

    @Override // okhttp3.WebSocketListener
    public synchronized void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(t, "t");
        if (this.webSocket != null) {
            abort("Websocket exception", t);
        }
        if (!this.closed) {
            ConnectionCallback connectionCallback = this.connectionCallback;
            if (connectionCallback != null) {
                connectionCallback.onDisconnected();
            }
            reconnect();
        }
    }

    @Override // okhttp3.WebSocketListener
    public synchronized void onMessage(WebSocket webSocket, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "text");
        MessageCallback messageCallback = this.messageCallback;
        if (messageCallback != null) {
            messageCallback.onMessage(str);
        }
    }

    @Override // okhttp3.WebSocketListener
    public synchronized void onMessage(WebSocket webSocket, ByteString bytes) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        MessageCallback messageCallback = this.messageCallback;
        if (messageCallback != null) {
            messageCallback.onMessage(bytes);
        }
    }

    @Override // okhttp3.WebSocketListener
    public synchronized void onClosed(WebSocket webSocket, int code, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        this.webSocket = null;
        if (!this.closed) {
            ConnectionCallback connectionCallback = this.connectionCallback;
            if (connectionCallback != null) {
                connectionCallback.onDisconnected();
            }
            reconnect();
        }
    }

    public final synchronized void sendMessage(String message) throws IOException {
        Intrinsics.checkNotNullParameter(message, "message");
        WebSocket webSocket = this.webSocket;
        if (webSocket == null) {
            throw new ClosedChannelException();
        }
        webSocket.send(message);
    }

    public final synchronized void sendMessage(ByteString message) throws IOException {
        Intrinsics.checkNotNullParameter(message, "message");
        WebSocket webSocket = this.webSocket;
        if (webSocket == null) {
            throw new ClosedChannelException();
        }
        webSocket.send(message);
    }

    /* JADX INFO: compiled from: ReconnectingWebSocket.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$Companion;", "", "<init>", "()V", "TAG", "", "RECONNECT_DELAY_MS", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("ReconnectingWebSocket", "getSimpleName(...)");
        TAG = "ReconnectingWebSocket";
    }
}
