package com.oney.WebRTCModule;

import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import io.sentry.protocol.SentryThread;
import java.nio.charset.StandardCharsets;
import org.webrtc.DataChannel;

/* JADX INFO: loaded from: classes2.dex */
class DataChannelWrapper implements DataChannel.Observer {
    private final DataChannel mDataChannel;
    private final int peerConnectionId;
    private final String reactTag;
    private final WebRTCModule webRTCModule;

    DataChannelWrapper(WebRTCModule webRTCModule, int i, String str, DataChannel dataChannel) {
        this.webRTCModule = webRTCModule;
        this.peerConnectionId = i;
        this.reactTag = str;
        this.mDataChannel = dataChannel;
    }

    public DataChannel getDataChannel() {
        return this.mDataChannel;
    }

    public String getReactTag() {
        return this.reactTag;
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.DataChannelWrapper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$DataChannel$State;

        static {
            int[] iArr = new int[DataChannel.State.values().length];
            $SwitchMap$org$webrtc$DataChannel$State = iArr;
            try {
                iArr[DataChannel.State.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$DataChannel$State[DataChannel.State.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$DataChannel$State[DataChannel.State.CLOSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$webrtc$DataChannel$State[DataChannel.State.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public String dataChannelStateString(DataChannel.State state) {
        int i = AnonymousClass1.$SwitchMap$org$webrtc$DataChannel$State[state.ordinal()];
        if (i == 1) {
            return "connecting";
        }
        if (i == 2) {
            return "open";
        }
        if (i == 3) {
            return "closing";
        }
        if (i != 4) {
            return null;
        }
        return "closed";
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onBufferedAmountChange(long j) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("reactTag", this.reactTag);
        writableMapCreateMap.putInt("peerConnectionId", this.peerConnectionId);
        writableMapCreateMap.putDouble("bufferedAmount", Long.valueOf(j).doubleValue());
        this.webRTCModule.sendEvent("dataChannelDidChangeBufferedAmount", writableMapCreateMap);
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onMessage(DataChannel.Buffer buffer) {
        byte[] bArrArray;
        String str;
        String str2;
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("reactTag", this.reactTag);
        writableMapCreateMap.putInt("peerConnectionId", this.peerConnectionId);
        if (buffer.data.hasArray()) {
            bArrArray = buffer.data.array();
        } else {
            bArrArray = new byte[buffer.data.remaining()];
            buffer.data.get(bArrArray);
        }
        if (buffer.binary) {
            str = Base64.encodeToString(bArrArray, 2);
            str2 = "binary";
        } else {
            str = new String(bArrArray, StandardCharsets.UTF_8);
            str2 = ReactTextInputShadowNode.PROP_TEXT;
        }
        writableMapCreateMap.putString("type", str2);
        writableMapCreateMap.putString("data", str);
        this.webRTCModule.sendEvent("dataChannelReceiveMessage", writableMapCreateMap);
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onStateChange() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("reactTag", this.reactTag);
        writableMapCreateMap.putInt("peerConnectionId", this.peerConnectionId);
        writableMapCreateMap.putInt("id", this.mDataChannel.id());
        writableMapCreateMap.putString(SentryThread.JsonKeys.STATE, dataChannelStateString(this.mDataChannel.state()));
        this.webRTCModule.sendEvent("dataChannelStateChanged", writableMapCreateMap);
    }
}
