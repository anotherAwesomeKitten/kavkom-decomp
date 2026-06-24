package com.oney.WebRTCModule;

import android.util.Base64;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import com.google.firebase.messaging.Constants;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.RTCStatsCollectorCallback;
import org.webrtc.RTCStatsReport;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.SessionDescription;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes2.dex */
class PeerConnectionObserver implements PeerConnection.Observer {
    private static final String TAG = WebRTCModule.TAG;
    private final int id;
    private PeerConnection peerConnection;
    private final VideoTrackAdapter videoTrackAdapters;
    private final WebRTCModule webRTCModule;
    private int transceiverNextId = 0;
    private final Map<String, DataChannelWrapper> dataChannels = new HashMap();
    final Map<String, String> remoteStreamIds = new HashMap();
    final Map<String, MediaStream> remoteStreams = new HashMap();
    final Map<String, MediaStreamTrack> remoteTracks = new HashMap();

    @Override // org.webrtc.PeerConnection.Observer
    public void onAddStream(MediaStream mediaStream) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceConnectionReceivingChange(boolean z) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onRemoveStream(MediaStream mediaStream) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onTrack(RtpTransceiver rtpTransceiver) {
    }

    PeerConnectionObserver(WebRTCModule webRTCModule, int i) {
        this.webRTCModule = webRTCModule;
        this.id = i;
        this.videoTrackAdapters = new VideoTrackAdapter(webRTCModule, i);
    }

    PeerConnection getPeerConnection() {
        return this.peerConnection;
    }

    void setPeerConnection(PeerConnection peerConnection) {
        this.peerConnection = peerConnection;
    }

    void close() {
        Log.d(TAG, "PeerConnection.close() for " + this.id);
        this.peerConnection.close();
    }

    void dispose() {
        Log.d(TAG, "PeerConnection.dispose() for " + this.id);
        for (MediaStreamTrack mediaStreamTrack : this.remoteTracks.values()) {
            if (mediaStreamTrack instanceof VideoTrack) {
                this.videoTrackAdapters.removeAdapter((VideoTrack) mediaStreamTrack);
            }
        }
        Iterator<DataChannelWrapper> it = this.dataChannels.values().iterator();
        while (it.hasNext()) {
            it.next().getDataChannel().unregisterObserver();
        }
        this.peerConnection.dispose();
        this.remoteStreamIds.clear();
        this.remoteStreams.clear();
        this.remoteTracks.clear();
        this.dataChannels.clear();
    }

    public synchronized int getNextTransceiverId() {
        int i;
        i = this.transceiverNextId;
        this.transceiverNextId = i + 1;
        return i;
    }

    RtpTransceiver addTransceiver(MediaStreamTrack.MediaType mediaType, RtpTransceiver.RtpTransceiverInit rtpTransceiverInit) {
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null) {
            return null;
        }
        return peerConnection.addTransceiver(mediaType, rtpTransceiverInit);
    }

    RtpTransceiver addTransceiver(MediaStreamTrack mediaStreamTrack, RtpTransceiver.RtpTransceiverInit rtpTransceiverInit) {
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null) {
            return null;
        }
        return peerConnection.addTransceiver(mediaStreamTrack, rtpTransceiverInit);
    }

    RtpSender getSender(String str) {
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null) {
            return null;
        }
        for (RtpSender rtpSender : peerConnection.getSenders()) {
            if (rtpSender.id().equals(str)) {
                return rtpSender;
            }
        }
        return null;
    }

    RtpTransceiver getTransceiver(String str) {
        PeerConnection peerConnection = this.peerConnection;
        if (peerConnection == null) {
            return null;
        }
        for (RtpTransceiver rtpTransceiver : peerConnection.getTransceivers()) {
            if (rtpTransceiver.getSender().id().equals(str)) {
                return rtpTransceiver;
            }
        }
        return null;
    }

    WritableMap createDataChannel(String str, ReadableMap readableMap) {
        DataChannel.Init init = new DataChannel.Init();
        if (readableMap != null) {
            if (readableMap.hasKey("id")) {
                init.id = readableMap.getInt("id");
            }
            if (readableMap.hasKey("ordered")) {
                init.ordered = readableMap.getBoolean("ordered");
            }
            if (readableMap.hasKey("maxRetransmitTime")) {
                init.maxRetransmitTimeMs = readableMap.getInt("maxRetransmitTime");
            }
            if (readableMap.hasKey("maxRetransmits")) {
                init.maxRetransmits = readableMap.getInt("maxRetransmits");
            }
            if (readableMap.hasKey("protocol")) {
                init.protocol = readableMap.getString("protocol");
            }
            if (readableMap.hasKey("negotiated")) {
                init.negotiated = readableMap.getBoolean("negotiated");
            }
        }
        DataChannel dataChannelCreateDataChannel = this.peerConnection.createDataChannel(str, init);
        if (dataChannelCreateDataChannel == null) {
            return null;
        }
        String string = UUID.randomUUID().toString();
        DataChannelWrapper dataChannelWrapper = new DataChannelWrapper(this.webRTCModule, this.id, string, dataChannelCreateDataChannel);
        this.dataChannels.put(string, dataChannelWrapper);
        dataChannelCreateDataChannel.registerObserver(dataChannelWrapper);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("peerConnectionId", this.id);
        writableMapCreateMap.putString("reactTag", string);
        writableMapCreateMap.putString(Constants.ScionAnalytics.PARAM_LABEL, dataChannelCreateDataChannel.label());
        writableMapCreateMap.putInt("id", dataChannelCreateDataChannel.id());
        writableMapCreateMap.putBoolean("ordered", init.ordered);
        writableMapCreateMap.putInt("maxPacketLifeTime", init.maxRetransmitTimeMs);
        writableMapCreateMap.putInt("maxRetransmits", init.maxRetransmits);
        writableMapCreateMap.putString("protocol", init.protocol);
        writableMapCreateMap.putBoolean("negotiated", init.negotiated);
        writableMapCreateMap.putString("readyState", dataChannelWrapper.dataChannelStateString(dataChannelCreateDataChannel.state()));
        return writableMapCreateMap;
    }

    void dataChannelClose(String str) {
        DataChannelWrapper dataChannelWrapper = this.dataChannels.get(str);
        if (dataChannelWrapper == null) {
            Log.d(TAG, "dataChannelClose() dataChannel is null");
        } else {
            dataChannelWrapper.getDataChannel().close();
        }
    }

    void dataChannelDispose(String str) {
        DataChannelWrapper dataChannelWrapper = this.dataChannels.get(str);
        if (dataChannelWrapper == null) {
            Log.d(TAG, "dataChannelDispose() dataChannel is null");
        } else {
            dataChannelWrapper.getDataChannel().unregisterObserver();
            this.dataChannels.remove(str);
        }
    }

    void dataChannelSend(String str, String str2, String str3) {
        byte[] bArrDecode;
        DataChannelWrapper dataChannelWrapper = this.dataChannels.get(str);
        if (dataChannelWrapper == null) {
            Log.d(TAG, "dataChannelSend() dataChannel is null");
            return;
        }
        if (str3.equals(ReactTextInputShadowNode.PROP_TEXT)) {
            bArrDecode = str2.getBytes(StandardCharsets.UTF_8);
        } else if (str3.equals("binary")) {
            bArrDecode = Base64.decode(str2, 2);
        } else {
            Log.e(TAG, "Unsupported data type: " + str3);
            return;
        }
        dataChannelWrapper.getDataChannel().send(new DataChannel.Buffer(ByteBuffer.wrap(bArrDecode), str3.equals("binary")));
    }

    void getStats(final Promise promise) {
        this.peerConnection.getStats(new RTCStatsCollectorCallback() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda10
            @Override // org.webrtc.RTCStatsCollectorCallback
            public final void onStatsDelivered(RTCStatsReport rTCStatsReport) {
                promise.resolve(StringUtils.statsToJSON(rTCStatsReport));
            }
        });
    }

    public void receiverGetStats(String str, final Promise promise) {
        RtpReceiver next;
        Iterator<RtpReceiver> it = this.peerConnection.getReceivers().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.id().equals(str)) {
                    break;
                }
            }
        }
        if (next == null) {
            Log.w(TAG, "receiverGetStats(): Receiver ID " + str + " not found");
            promise.resolve(StringUtils.statsToJSON(new RTCStatsReport(0L, new HashMap())));
        } else {
            this.peerConnection.getStats(next, new RTCStatsCollectorCallback() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda5
                @Override // org.webrtc.RTCStatsCollectorCallback
                public final void onStatsDelivered(RTCStatsReport rTCStatsReport) {
                    promise.resolve(StringUtils.statsToJSON(rTCStatsReport));
                }
            });
        }
    }

    public void senderGetStats(String str, final Promise promise) {
        RtpSender next;
        Iterator<RtpSender> it = this.peerConnection.getSenders().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.id().equals(str)) {
                    break;
                }
            }
        }
        if (next == null) {
            Log.w(TAG, "senderGetStats(): Sender ID " + str + " not found");
            promise.resolve(StringUtils.statsToJSON(new RTCStatsReport(0L, new HashMap())));
        } else {
            this.peerConnection.getStats(next, new RTCStatsCollectorCallback() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda4
                @Override // org.webrtc.RTCStatsCollectorCallback
                public final void onStatsDelivered(RTCStatsReport rTCStatsReport) {
                    promise.resolve(StringUtils.statsToJSON(rTCStatsReport));
                }
            });
        }
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceCandidate(final IceCandidate iceCandidate) {
        Log.d(TAG, "onIceCandidate");
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onIceCandidate$3(iceCandidate);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public /* synthetic */ void lambda$onIceCandidate$3(IceCandidate iceCandidate) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("pcId", this.id);
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putInt("sdpMLineIndex", iceCandidate.sdpMLineIndex);
        writableMapCreateMap2.putString("sdpMid", iceCandidate.sdpMid);
        writableMapCreateMap2.putString("candidate", iceCandidate.sdp);
        writableMapCreateMap.putMap("candidate", writableMapCreateMap2);
        SessionDescription localDescription = this.peerConnection.getLocalDescription();
        WritableMap writableMapCreateMap3 = Arguments.createMap();
        if (localDescription != null) {
            writableMapCreateMap3.putString("type", localDescription.type.canonicalForm());
            writableMapCreateMap3.putString("sdp", localDescription.description);
        }
        writableMapCreateMap.putMap("sdp", writableMapCreateMap3);
        this.webRTCModule.sendEvent("peerConnectionGotICECandidate", writableMapCreateMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceConnectionChange(final PeerConnection.IceConnectionState iceConnectionState) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onIceConnectionChange$4(iceConnectionState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onIceConnectionChange$4(PeerConnection.IceConnectionState iceConnectionState) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("pcId", this.id);
        writableMapCreateMap.putString("iceConnectionState", iceConnectionStateString(iceConnectionState));
        this.webRTCModule.sendEvent("peerConnectionIceConnectionChanged", writableMapCreateMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onConnectionChange(final PeerConnection.PeerConnectionState peerConnectionState) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onConnectionChange$5(peerConnectionState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onConnectionChange$5(PeerConnection.PeerConnectionState peerConnectionState) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("pcId", this.id);
        writableMapCreateMap.putString("connectionState", peerConnectionStateString(peerConnectionState));
        this.webRTCModule.sendEvent("peerConnectionStateChanged", writableMapCreateMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceGatheringChange(final PeerConnection.IceGatheringState iceGatheringState) {
        Log.d(TAG, "onIceGatheringChange" + iceGatheringState.name());
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onIceGatheringChange$6(iceGatheringState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public /* synthetic */ void lambda$onIceGatheringChange$6(PeerConnection.IceGatheringState iceGatheringState) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("pcId", this.id);
        writableMapCreateMap.putString("iceGatheringState", iceGatheringStateString(iceGatheringState));
        if (iceGatheringState == PeerConnection.IceGatheringState.COMPLETE) {
            SessionDescription localDescription = this.peerConnection.getLocalDescription();
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            if (localDescription != null) {
                writableMapCreateMap2.putString("type", localDescription.type.canonicalForm());
                writableMapCreateMap2.putString("sdp", localDescription.description);
            }
            writableMapCreateMap.putMap("sdp", writableMapCreateMap2);
        }
        this.webRTCModule.sendEvent("peerConnectionIceGatheringChanged", writableMapCreateMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onDataChannel(final DataChannel dataChannel) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDataChannel$7(dataChannel);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChannel$7(DataChannel dataChannel) {
        String string = UUID.randomUUID().toString();
        DataChannelWrapper dataChannelWrapper = new DataChannelWrapper(this.webRTCModule, this.id, string, dataChannel);
        this.dataChannels.put(string, dataChannelWrapper);
        dataChannel.registerObserver(dataChannelWrapper);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("peerConnectionId", this.id);
        writableMapCreateMap.putString("reactTag", string);
        writableMapCreateMap.putString(Constants.ScionAnalytics.PARAM_LABEL, dataChannel.label());
        writableMapCreateMap.putInt("id", dataChannel.id());
        writableMapCreateMap.putBoolean("ordered", true);
        writableMapCreateMap.putInt("maxPacketLifeTime", -1);
        writableMapCreateMap.putInt("maxRetransmits", -1);
        writableMapCreateMap.putString("protocol", "");
        writableMapCreateMap.putBoolean("negotiated", false);
        writableMapCreateMap.putString("readyState", dataChannelWrapper.dataChannelStateString(dataChannel.state()));
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putInt("pcId", this.id);
        writableMapCreateMap2.putMap("dataChannel", writableMapCreateMap);
        this.webRTCModule.sendEvent("peerConnectionDidOpenDataChannel", writableMapCreateMap2);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onRenegotiationNeeded() {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRenegotiationNeeded$8();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRenegotiationNeeded$8() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("pcId", this.id);
        this.webRTCModule.sendEvent("peerConnectionOnRenegotiationNeeded", writableMapCreateMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onSignalingChange(final PeerConnection.SignalingState signalingState) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSignalingChange$9(signalingState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSignalingChange$9(PeerConnection.SignalingState signalingState) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("pcId", this.id);
        writableMapCreateMap.putString("signalingState", signalingStateString(signalingState));
        this.webRTCModule.sendEvent("peerConnectionSignalingStateChanged", writableMapCreateMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onAddTrack(final RtpReceiver rtpReceiver, final MediaStream[] mediaStreamArr) {
        Log.d(TAG, "onAddTrack");
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAddTrack$10(rtpReceiver, mediaStreamArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public /* synthetic */ void lambda$onAddTrack$10(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
        RtpTransceiver next;
        Iterator<RtpTransceiver> it = this.peerConnection.getTransceivers().iterator();
        while (true) {
            if (it.hasNext()) {
                next = it.next();
                if (Objects.equals(next.getReceiver().id(), rtpReceiver.id())) {
                    break;
                }
            } else {
                next = null;
                break;
            }
        }
        if (next == null) {
            return;
        }
        MediaStreamTrack mediaStreamTrackTrack = rtpReceiver.track();
        if (!this.remoteTracks.containsKey(mediaStreamTrackTrack.id())) {
            if (mediaStreamTrackTrack.kind().equals("video")) {
                this.videoTrackAdapters.addAdapter((VideoTrack) mediaStreamTrackTrack);
            }
            this.remoteTracks.put(mediaStreamTrackTrack.id(), mediaStreamTrackTrack);
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (MediaStream mediaStream : mediaStreamArr) {
            String string = this.remoteStreamIds.get(mediaStream.getId());
            if (string == null) {
                string = UUID.randomUUID().toString();
                this.remoteStreamIds.put(mediaStream.getId(), string);
            }
            this.remoteStreams.put(string, mediaStream);
            writableArrayCreateArray.pushMap(SerializeUtils.serializeStream(this.id, string, mediaStream));
        }
        writableMapCreateMap.putArray("streams", writableArrayCreateArray);
        writableMapCreateMap.putMap("receiver", SerializeUtils.serializeReceiver(this.id, rtpReceiver));
        writableMapCreateMap.putInt("transceiverOrder", getNextTransceiverId());
        writableMapCreateMap.putMap("transceiver", SerializeUtils.serializeTransceiver(this.id, next));
        writableMapCreateMap.putInt("pcId", this.id);
        this.webRTCModule.sendEvent("peerConnectionOnTrack", writableMapCreateMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onRemoveTrack(final RtpReceiver rtpReceiver) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.PeerConnectionObserver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRemoveTrack$11(rtpReceiver);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRemoveTrack$11(RtpReceiver rtpReceiver) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("pcId", this.id);
        writableMapCreateMap.putString("receiverId", rtpReceiver.id());
        this.webRTCModule.sendEvent("peerConnectionOnRemoveTrack", writableMapCreateMap);
    }

    private String peerConnectionStateString(PeerConnection.PeerConnectionState peerConnectionState) {
        switch (AnonymousClass1.$SwitchMap$org$webrtc$PeerConnection$PeerConnectionState[peerConnectionState.ordinal()]) {
            case 1:
                return "new";
            case 2:
                return "connecting";
            case 3:
                return "connected";
            case 4:
                return "disconnected";
            case 5:
                return "failed";
            case 6:
                return "closed";
            default:
                return null;
        }
    }

    private String iceConnectionStateString(PeerConnection.IceConnectionState iceConnectionState) {
        switch (AnonymousClass1.$SwitchMap$org$webrtc$PeerConnection$IceConnectionState[iceConnectionState.ordinal()]) {
            case 1:
                return "new";
            case 2:
                return "checking";
            case 3:
                return "connected";
            case 4:
                return "completed";
            case 5:
                return "failed";
            case 6:
                return "disconnected";
            case 7:
                return "closed";
            default:
                return null;
        }
    }

    private String iceGatheringStateString(PeerConnection.IceGatheringState iceGatheringState) {
        int i = AnonymousClass1.$SwitchMap$org$webrtc$PeerConnection$IceGatheringState[iceGatheringState.ordinal()];
        if (i == 1) {
            return "new";
        }
        if (i == 2) {
            return "gathering";
        }
        if (i != 3) {
            return null;
        }
        return "complete";
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.PeerConnectionObserver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$PeerConnection$IceConnectionState;
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$PeerConnection$IceGatheringState;
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$PeerConnection$PeerConnectionState;
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$PeerConnection$SignalingState;

        static {
            int[] iArr = new int[PeerConnection.SignalingState.values().length];
            $SwitchMap$org$webrtc$PeerConnection$SignalingState = iArr;
            try {
                iArr[PeerConnection.SignalingState.STABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$SignalingState[PeerConnection.SignalingState.HAVE_LOCAL_OFFER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$SignalingState[PeerConnection.SignalingState.HAVE_LOCAL_PRANSWER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$SignalingState[PeerConnection.SignalingState.HAVE_REMOTE_OFFER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$SignalingState[PeerConnection.SignalingState.HAVE_REMOTE_PRANSWER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$SignalingState[PeerConnection.SignalingState.CLOSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[PeerConnection.IceGatheringState.values().length];
            $SwitchMap$org$webrtc$PeerConnection$IceGatheringState = iArr2;
            try {
                iArr2[PeerConnection.IceGatheringState.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceGatheringState[PeerConnection.IceGatheringState.GATHERING.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceGatheringState[PeerConnection.IceGatheringState.COMPLETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[PeerConnection.IceConnectionState.values().length];
            $SwitchMap$org$webrtc$PeerConnection$IceConnectionState = iArr3;
            try {
                iArr3[PeerConnection.IceConnectionState.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.CHECKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.COMPLETED.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.FAILED.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.DISCONNECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$IceConnectionState[PeerConnection.IceConnectionState.CLOSED.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            int[] iArr4 = new int[PeerConnection.PeerConnectionState.values().length];
            $SwitchMap$org$webrtc$PeerConnection$PeerConnectionState = iArr4;
            try {
                iArr4[PeerConnection.PeerConnectionState.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$PeerConnectionState[PeerConnection.PeerConnectionState.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$PeerConnectionState[PeerConnection.PeerConnectionState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$PeerConnectionState[PeerConnection.PeerConnectionState.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$PeerConnectionState[PeerConnection.PeerConnectionState.FAILED.ordinal()] = 5;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$org$webrtc$PeerConnection$PeerConnectionState[PeerConnection.PeerConnectionState.CLOSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    private String signalingStateString(PeerConnection.SignalingState signalingState) {
        switch (AnonymousClass1.$SwitchMap$org$webrtc$PeerConnection$SignalingState[signalingState.ordinal()]) {
            case 1:
                return "stable";
            case 2:
                return "have-local-offer";
            case 3:
                return "have-local-pranswer";
            case 4:
                return "have-remote-offer";
            case 5:
                return "have-remote-pranswer";
            case 6:
                return "closed";
            default:
                return null;
        }
    }
}
