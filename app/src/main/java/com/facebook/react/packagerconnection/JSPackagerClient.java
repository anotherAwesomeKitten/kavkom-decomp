package com.facebook.react.packagerconnection;

import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.ReconnectingWebSocket;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import io.sentry.Session;
import io.sentry.clientreport.DiscardedEvent;
import io.sentry.protocol.App;
import io.sentry.protocol.Device;
import io.sentry.protocol.Message;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.json.JSONObject;

/* JADX INFO: compiled from: JSPackagerClient.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0003H\u0002R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/packagerconnection/JSPackagerClient;", "Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$MessageCallback;", "clientId", "", "settings", "Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;", "requestHandlers", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "connectionCallback", "Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$ConnectionCallback;", "<init>", "(Ljava/lang/String;Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;Ljava/util/Map;Lcom/facebook/react/packagerconnection/ReconnectingWebSocket$ConnectionCallback;)V", "webSocket", "Lcom/facebook/react/packagerconnection/ReconnectingWebSocket;", Session.JsonKeys.INIT, "", "close", "onMessage", ReactTextInputShadowNode.PROP_TEXT, "bytes", "Lokio/ByteString;", "abortOnMessage", "id", "", DiscardedEvent.JsonKeys.REASON, "ResponderImpl", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class JSPackagerClient implements ReconnectingWebSocket.MessageCallback {
    private static final Companion Companion = new Companion(null);
    private static final int PROTOCOL_VERSION = 2;
    private static final String TAG;
    private final Map<String, RequestHandler> requestHandlers;
    private final ReconnectingWebSocket webSocket;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JSPackagerClient(String clientId, PackagerConnectionSettings settings, Map<String, ? extends RequestHandler> requestHandlers) {
        this(clientId, settings, requestHandlers, null, 8, null);
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(requestHandlers, "requestHandlers");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JSPackagerClient(String clientId, PackagerConnectionSettings settings, Map<String, ? extends RequestHandler> requestHandlers, ReconnectingWebSocket.ConnectionCallback connectionCallback) {
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(requestHandlers, "requestHandlers");
        this.requestHandlers = requestHandlers;
        String string = new Uri.Builder().scheme("ws").encodedAuthority(settings.getDebugServerHost()).appendPath("message").appendQueryParameter(Device.TYPE, AndroidInfoHelpers.getFriendlyDeviceName()).appendQueryParameter(App.TYPE, settings.getPackageName()).appendQueryParameter("clientid", clientId).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        this.webSocket = new ReconnectingWebSocket(string, this, connectionCallback);
    }

    public /* synthetic */ JSPackagerClient(String str, PackagerConnectionSettings packagerConnectionSettings, Map map, ReconnectingWebSocket.ConnectionCallback connectionCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, packagerConnectionSettings, map, (i & 8) != 0 ? null : connectionCallback);
    }

    public final void init() {
        this.webSocket.connect();
    }

    public final void close() {
        this.webSocket.closeQuietly();
    }

    @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.MessageCallback
    public void onMessage(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        try {
            JSONObject jSONObject = new JSONObject(text);
            int iOptInt = jSONObject.optInt("version");
            String strOptString = jSONObject.optString("method");
            Object objOpt = jSONObject.opt("id");
            Object objOpt2 = jSONObject.opt(Message.JsonKeys.PARAMS);
            if (iOptInt != 2) {
                FLog.e(TAG, "Message with incompatible or missing version of protocol received: " + iOptInt);
                return;
            }
            if (strOptString == null) {
                abortOnMessage(objOpt, "No method provided");
                return;
            }
            RequestHandler requestHandler = this.requestHandlers.get(strOptString);
            if (requestHandler == null) {
                abortOnMessage(objOpt, "No request handler for method: " + strOptString);
            } else if (objOpt == null) {
                requestHandler.onNotification(objOpt2);
            } else {
                requestHandler.onRequest(objOpt2, new ResponderImpl(this, objOpt));
            }
        } catch (Exception e) {
            FLog.e(TAG, "Handling the message failed", e);
        }
    }

    @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.MessageCallback
    public void onMessage(ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        FLog.w(TAG, "Websocket received message with payload of unexpected type binary");
    }

    private final void abortOnMessage(Object id, String reason) {
        if (id != null) {
            new ResponderImpl(this, id).error(reason);
        }
        FLog.e(TAG, "Handling the message failed with reason: " + reason);
    }

    /* JADX INFO: compiled from: JSPackagerClient.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/react/packagerconnection/JSPackagerClient$ResponderImpl;", "Lcom/facebook/react/packagerconnection/Responder;", "id", "", "<init>", "(Lcom/facebook/react/packagerconnection/JSPackagerClient;Ljava/lang/Object;)V", "respond", "", "result", "error", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class ResponderImpl implements Responder {
        private final Object id;
        final /* synthetic */ JSPackagerClient this$0;

        public ResponderImpl(JSPackagerClient jSPackagerClient, Object id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.this$0 = jSPackagerClient;
            this.id = id;
        }

        @Override // com.facebook.react.packagerconnection.Responder
        public void respond(Object result) {
            Intrinsics.checkNotNullParameter(result, "result");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("version", 2);
                jSONObject.put("id", this.id);
                jSONObject.put("result", result);
                ReconnectingWebSocket reconnectingWebSocket = this.this$0.webSocket;
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                reconnectingWebSocket.sendMessage(string);
            } catch (Exception e) {
                FLog.e(JSPackagerClient.TAG, "Responding failed", e);
            }
        }

        @Override // com.facebook.react.packagerconnection.Responder
        public void error(Object error) {
            Intrinsics.checkNotNullParameter(error, "error");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("version", 2);
                jSONObject.put("id", this.id);
                jSONObject.put("error", error);
                ReconnectingWebSocket reconnectingWebSocket = this.this$0.webSocket;
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                reconnectingWebSocket.sendMessage(string);
            } catch (Exception e) {
                FLog.e(JSPackagerClient.TAG, "Responding with error failed", e);
            }
        }
    }

    /* JADX INFO: compiled from: JSPackagerClient.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/packagerconnection/JSPackagerClient$Companion;", "", "<init>", "()V", "TAG", "", "PROTOCOL_VERSION", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
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
