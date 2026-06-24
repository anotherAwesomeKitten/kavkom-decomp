package com.facebook.react.devsupport;

import com.facebook.react.devsupport.CxxInspectorPackagerConnection;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import io.sentry.clientreport.DiscardedEvent;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/* JADX INFO: compiled from: CxxInspectorPackagerConnection.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0016¨\u0006\u0012"}, d2 = {"com/facebook/react/devsupport/CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1", "Lokhttp3/WebSocketListener;", "onFailure", "", "webSocket", "Lokhttp3/WebSocket;", "t", "", Response.TYPE, "Lokhttp3/Response;", "onMessage", ReactTextInputShadowNode.PROP_TEXT, "", "onOpen", "onClosed", "code", "", DiscardedEvent.JsonKeys.REASON, "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1 extends WebSocketListener {
    final /* synthetic */ CxxInspectorPackagerConnection.WebSocketDelegate $delegate;
    final /* synthetic */ CxxInspectorPackagerConnection.DelegateImpl this$0;

    CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1(CxxInspectorPackagerConnection.DelegateImpl delegateImpl, CxxInspectorPackagerConnection.WebSocketDelegate webSocketDelegate) {
        this.this$0 = delegateImpl;
        this.$delegate = webSocketDelegate;
    }

    @Override // okhttp3.WebSocketListener
    public void onFailure(WebSocket webSocket, final Throwable t, okhttp3.Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(t, "t");
        CxxInspectorPackagerConnection.DelegateImpl delegateImpl = this.this$0;
        final CxxInspectorPackagerConnection.WebSocketDelegate webSocketDelegate = this.$delegate;
        delegateImpl.scheduleCallback(new Runnable() { // from class: com.facebook.react.devsupport.CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1.onFailure$lambda$0(t, webSocketDelegate);
            }
        }, 0L);
    }

    public static final void onFailure$lambda$0(Throwable th, CxxInspectorPackagerConnection.WebSocketDelegate webSocketDelegate) {
        String message = th.getMessage();
        if (message == null) {
            message = "<Unknown error>";
        }
        webSocketDelegate.didFailWithError(null, message);
        webSocketDelegate.close();
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, final String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "text");
        CxxInspectorPackagerConnection.DelegateImpl delegateImpl = this.this$0;
        final CxxInspectorPackagerConnection.WebSocketDelegate webSocketDelegate = this.$delegate;
        delegateImpl.scheduleCallback(new Runnable() { // from class: com.facebook.react.devsupport.CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                webSocketDelegate.didReceiveMessage(str);
            }
        }, 0L);
    }

    @Override // okhttp3.WebSocketListener
    public void onOpen(WebSocket webSocket, okhttp3.Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
        CxxInspectorPackagerConnection.DelegateImpl delegateImpl = this.this$0;
        final CxxInspectorPackagerConnection.WebSocketDelegate webSocketDelegate = this.$delegate;
        delegateImpl.scheduleCallback(new Runnable() { // from class: com.facebook.react.devsupport.CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                webSocketDelegate.didOpen();
            }
        }, 0L);
    }

    @Override // okhttp3.WebSocketListener
    public void onClosed(WebSocket webSocket, int code, String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        CxxInspectorPackagerConnection.DelegateImpl delegateImpl = this.this$0;
        final CxxInspectorPackagerConnection.WebSocketDelegate webSocketDelegate = this.$delegate;
        delegateImpl.scheduleCallback(new Runnable() { // from class: com.facebook.react.devsupport.CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CxxInspectorPackagerConnection$DelegateImpl$connectWebSocket$webSocket$1.onClosed$lambda$3(webSocketDelegate);
            }
        }, 0L);
    }

    public static final void onClosed$lambda$3(CxxInspectorPackagerConnection.WebSocketDelegate webSocketDelegate) {
        webSocketDelegate.didClose();
        webSocketDelegate.close();
    }
}
