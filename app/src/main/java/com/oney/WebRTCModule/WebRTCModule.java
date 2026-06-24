package com.oney.WebRTCModule;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import com.oney.WebRTCModule.GetUserMediaImpl;
import com.oney.WebRTCModule.WebRTCModule;
import com.oney.WebRTCModule.webrtcutils.H264AndSoftwareVideoDecoderFactory;
import com.oney.WebRTCModule.webrtcutils.H264AndSoftwareVideoEncoderFactory;
import io.sentry.SentryReplayEvent;
import io.sentry.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import org.webrtc.AddIceObserver;
import org.webrtc.AudioTrack;
import org.webrtc.CryptoOptions;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.Loggable;
import org.webrtc.Logging;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RTCStatsReport;
import org.webrtc.RtpCapabilities;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.SoftwareVideoEncoderFactory;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoEncoderFactory;
import org.webrtc.VideoTrack;
import org.webrtc.audio.AudioDeviceModule;
import org.webrtc.audio.JavaAudioDeviceModule;

/* JADX INFO: loaded from: classes2.dex */
@ReactModule(name = "WebRTCModule")
public class WebRTCModule extends ReactContextBaseJavaModule {
    static final String TAG = "com.oney.WebRTCModule.WebRTCModule";
    private final GetUserMediaImpl getUserMediaImpl;
    final Map<String, MediaStream> localStreams;
    AudioDeviceModule mAudioDeviceModule;
    PeerConnectionFactory mFactory;
    private final SparseArray<PeerConnectionObserver> mPeerConnectionObservers;
    VideoDecoderFactory mVideoDecoderFactory;
    VideoEncoderFactory mVideoEncoderFactory;

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public WebRTCModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mPeerConnectionObservers = new SparseArray<>();
        this.localStreams = new HashMap();
        WebRTCModuleOptions webRTCModuleOptions = WebRTCModuleOptions.getInstance();
        AudioDeviceModule audioDeviceModuleCreateAudioDeviceModule = webRTCModuleOptions.audioDeviceModule;
        VideoEncoderFactory softwareVideoEncoderFactory = webRTCModuleOptions.videoEncoderFactory;
        VideoDecoderFactory softwareVideoDecoderFactory = webRTCModuleOptions.videoDecoderFactory;
        Loggable loggable = webRTCModuleOptions.injectableLogger;
        Logging.Severity severity = webRTCModuleOptions.loggingSeverity;
        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(reactApplicationContext).setFieldTrials(webRTCModuleOptions.fieldTrials).setNativeLibraryLoader(new LibraryLoader()).setInjectableLogger(loggable, severity).createInitializationOptions());
        if (loggable == null && severity != null) {
            Logging.enableLogToDebugOutput(severity);
        }
        if (softwareVideoEncoderFactory == null || softwareVideoDecoderFactory == null) {
            EglBase.Context rootEglBaseContext = EglUtils.getRootEglBaseContext();
            if (rootEglBaseContext != null) {
                softwareVideoEncoderFactory = new H264AndSoftwareVideoEncoderFactory(rootEglBaseContext);
                softwareVideoDecoderFactory = new H264AndSoftwareVideoDecoderFactory(rootEglBaseContext);
            } else {
                softwareVideoEncoderFactory = new SoftwareVideoEncoderFactory();
                softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
            }
        }
        audioDeviceModuleCreateAudioDeviceModule = audioDeviceModuleCreateAudioDeviceModule == null ? JavaAudioDeviceModule.builder(reactApplicationContext).setEnableVolumeLogger(false).createAudioDeviceModule() : audioDeviceModuleCreateAudioDeviceModule;
        String str = TAG;
        Log.d(str, "Using video encoder factory: " + softwareVideoEncoderFactory.getClass().getCanonicalName());
        Log.d(str, "Using video decoder factory: " + softwareVideoDecoderFactory.getClass().getCanonicalName());
        this.mFactory = PeerConnectionFactory.builder().setAudioDeviceModule(audioDeviceModuleCreateAudioDeviceModule).setVideoEncoderFactory(softwareVideoEncoderFactory).setVideoDecoderFactory(softwareVideoDecoderFactory).createPeerConnectionFactory();
        audioDeviceModuleCreateAudioDeviceModule.release();
        this.mVideoEncoderFactory = softwareVideoEncoderFactory;
        this.mVideoDecoderFactory = softwareVideoDecoderFactory;
        this.mAudioDeviceModule = audioDeviceModuleCreateAudioDeviceModule;
        this.getUserMediaImpl = new GetUserMediaImpl(this, reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "WebRTCModule";
    }

    private PeerConnection getPeerConnection(int i) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null) {
            return null;
        }
        return peerConnectionObserver.getPeerConnection();
    }

    void sendEvent(String str, ReadableMap readableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, readableMap);
    }

    private PeerConnection.IceServer createIceServer(String str) {
        return PeerConnection.IceServer.builder(str).createIceServer();
    }

    private PeerConnection.IceServer createIceServer(String str, String str2, String str3) {
        return PeerConnection.IceServer.builder(str).setUsername(str2).setPassword(str3).createIceServer();
    }

    private List<PeerConnection.IceServer> createIceServers(ReadableArray readableArray) {
        int size = readableArray == null ? 0 : readableArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            boolean z = map.hasKey("username") && map.hasKey("credential");
            if (map.hasKey(SentryReplayEvent.JsonKeys.URLS)) {
                int i2 = AnonymousClass6.$SwitchMap$com$facebook$react$bridge$ReadableType[map.getType(SentryReplayEvent.JsonKeys.URLS).ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        ReadableArray array = map.getArray(SentryReplayEvent.JsonKeys.URLS);
                        for (int i3 = 0; i3 < array.size(); i3++) {
                            String string = array.getString(i3);
                            if (z) {
                                arrayList.add(createIceServer(string, map.getString("username"), map.getString("credential")));
                            } else {
                                arrayList.add(createIceServer(string));
                            }
                        }
                    }
                } else if (z) {
                    arrayList.add(createIceServer(map.getString(SentryReplayEvent.JsonKeys.URLS), map.getString("username"), map.getString("credential")));
                } else {
                    arrayList.add(createIceServer(map.getString(SentryReplayEvent.JsonKeys.URLS)));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.WebRTCModule$6 */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private PeerConnection.RTCConfiguration parseRTCConfiguration(ReadableMap readableMap) {
        int i;
        String string;
        String string2;
        String string3;
        String string4;
        int i2;
        String string5;
        String string6;
        String string7;
        PeerConnection.RTCConfiguration rTCConfiguration = new PeerConnection.RTCConfiguration(createIceServers((readableMap == null || !readableMap.hasKey("iceServers")) ? null : readableMap.getArray("iceServers")));
        rTCConfiguration.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        rTCConfiguration.enableImplicitRollback = true;
        rTCConfiguration.cryptoOptions = CryptoOptions.builder().setEnableGcmCryptoSuites(true).setEnableAes128Sha1_32CryptoCipher(false).setEnableEncryptedRtpHeaderExtensions(false).setRequireFrameEncryption(false).createCryptoOptions();
        if (readableMap != null) {
            if (readableMap.hasKey("iceTransportPolicy") && readableMap.getType("iceTransportPolicy") == ReadableType.String && (string7 = readableMap.getString("iceTransportPolicy")) != null) {
                string7.hashCode();
                switch (string7) {
                    case "nohost":
                        rTCConfiguration.iceTransportsType = PeerConnection.IceTransportsType.NOHOST;
                        break;
                    case "all":
                        rTCConfiguration.iceTransportsType = PeerConnection.IceTransportsType.ALL;
                        break;
                    case "none":
                        rTCConfiguration.iceTransportsType = PeerConnection.IceTransportsType.NONE;
                        break;
                    case "relay":
                        rTCConfiguration.iceTransportsType = PeerConnection.IceTransportsType.RELAY;
                        break;
                }
            }
            if (readableMap.hasKey("bundlePolicy") && readableMap.getType("bundlePolicy") == ReadableType.String && (string6 = readableMap.getString("bundlePolicy")) != null) {
                string6.hashCode();
                switch (string6) {
                    case "balanced":
                        rTCConfiguration.bundlePolicy = PeerConnection.BundlePolicy.BALANCED;
                        break;
                    case "max-bundle":
                        rTCConfiguration.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE;
                        break;
                    case "max-compat":
                        rTCConfiguration.bundlePolicy = PeerConnection.BundlePolicy.MAXCOMPAT;
                        break;
                }
            }
            if (readableMap.hasKey("rtcpMuxPolicy") && readableMap.getType("rtcpMuxPolicy") == ReadableType.String && (string5 = readableMap.getString("rtcpMuxPolicy")) != null) {
                string5.hashCode();
                if (string5.equals("negotiate")) {
                    rTCConfiguration.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.NEGOTIATE;
                } else if (string5.equals("require")) {
                    rTCConfiguration.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
                }
            }
            if (readableMap.hasKey("iceCandidatePoolSize") && readableMap.getType("iceCandidatePoolSize") == ReadableType.Number && (i2 = readableMap.getInt("iceCandidatePoolSize")) > 0) {
                rTCConfiguration.iceCandidatePoolSize = i2;
            }
            if (readableMap.hasKey("tcpCandidatePolicy") && readableMap.getType("tcpCandidatePolicy") == ReadableType.String && (string4 = readableMap.getString("tcpCandidatePolicy")) != null) {
                string4.hashCode();
                if (string4.equals(ViewProps.ENABLED)) {
                    rTCConfiguration.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.ENABLED;
                } else if (string4.equals("disabled")) {
                    rTCConfiguration.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.DISABLED;
                }
            }
            if (readableMap.hasKey("candidateNetworkPolicy") && readableMap.getType("candidateNetworkPolicy") == ReadableType.String && (string3 = readableMap.getString("candidateNetworkPolicy")) != null) {
                string3.hashCode();
                if (string3.equals("low_cost")) {
                    rTCConfiguration.candidateNetworkPolicy = PeerConnection.CandidateNetworkPolicy.LOW_COST;
                } else if (string3.equals("all")) {
                    rTCConfiguration.candidateNetworkPolicy = PeerConnection.CandidateNetworkPolicy.ALL;
                }
            }
            if (readableMap.hasKey("keyType") && readableMap.getType("keyType") == ReadableType.String && (string2 = readableMap.getString("keyType")) != null) {
                string2.hashCode();
                if (string2.equals(CipherStorageKeystoreRsaEcb.ALGORITHM_RSA)) {
                    rTCConfiguration.keyType = PeerConnection.KeyType.RSA;
                } else if (string2.equals("ECDSA")) {
                    rTCConfiguration.keyType = PeerConnection.KeyType.ECDSA;
                }
            }
            if (readableMap.hasKey("continualGatheringPolicy") && readableMap.getType("continualGatheringPolicy") == ReadableType.String && (string = readableMap.getString("continualGatheringPolicy")) != null) {
                string.hashCode();
                if (string.equals("gather_once")) {
                    rTCConfiguration.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_ONCE;
                } else if (string.equals("gather_continually")) {
                    rTCConfiguration.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
                }
            }
            if (readableMap.hasKey("audioJitterBufferMaxPackets") && readableMap.getType("audioJitterBufferMaxPackets") == ReadableType.Number && (i = readableMap.getInt("audioJitterBufferMaxPackets")) > 0) {
                rTCConfiguration.audioJitterBufferMaxPackets = i;
            }
            if (readableMap.hasKey("iceConnectionReceivingTimeout") && readableMap.getType("iceConnectionReceivingTimeout") == ReadableType.Number) {
                rTCConfiguration.iceConnectionReceivingTimeout = readableMap.getInt("iceConnectionReceivingTimeout");
            }
            if (readableMap.hasKey("iceBackupCandidatePairPingInterval") && readableMap.getType("iceBackupCandidatePairPingInterval") == ReadableType.Number) {
                rTCConfiguration.iceBackupCandidatePairPingInterval = readableMap.getInt("iceBackupCandidatePairPingInterval");
            }
            if (readableMap.hasKey("audioJitterBufferFastAccelerate") && readableMap.getType("audioJitterBufferFastAccelerate") == ReadableType.Boolean) {
                rTCConfiguration.audioJitterBufferFastAccelerate = readableMap.getBoolean("audioJitterBufferFastAccelerate");
            }
            if (readableMap.hasKey("pruneTurnPorts") && readableMap.getType("pruneTurnPorts") == ReadableType.Boolean) {
                rTCConfiguration.pruneTurnPorts = readableMap.getBoolean("pruneTurnPorts");
            }
            if (readableMap.hasKey("presumeWritableWhenFullyRelayed") && readableMap.getType("presumeWritableWhenFullyRelayed") == ReadableType.Boolean) {
                rTCConfiguration.presumeWritableWhenFullyRelayed = readableMap.getBoolean("presumeWritableWhenFullyRelayed");
            }
        }
        return rTCConfiguration;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean peerConnectionInit(ReadableMap readableMap, final int i) {
        final PeerConnection.RTCConfiguration rTCConfiguration = parseRTCConfiguration(readableMap);
        try {
            return ((Boolean) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda36
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$peerConnectionInit$0(i, rTCConfiguration);
                }
            }).get()).booleanValue();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public /* synthetic */ Boolean lambda$peerConnectionInit$0(int i, PeerConnection.RTCConfiguration rTCConfiguration) throws Exception {
        PeerConnectionObserver peerConnectionObserver = new PeerConnectionObserver(this, i);
        PeerConnection peerConnectionCreatePeerConnection = this.mFactory.createPeerConnection(rTCConfiguration, peerConnectionObserver);
        if (peerConnectionCreatePeerConnection == null) {
            return false;
        }
        peerConnectionObserver.setPeerConnection(peerConnectionCreatePeerConnection);
        this.mPeerConnectionObservers.put(i, peerConnectionObserver);
        return true;
    }

    MediaStream getStreamForReactTag(final String str) {
        try {
            return (MediaStream) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda28
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$getStreamForReactTag$1(str);
                }
            }).get();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    public /* synthetic */ Object lambda$getStreamForReactTag$1(String str) throws Exception {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream != null) {
            return mediaStream;
        }
        int size = this.mPeerConnectionObservers.size();
        for (int i = 0; i < size; i++) {
            MediaStream mediaStream2 = this.mPeerConnectionObservers.valueAt(i).remoteStreams.get(str);
            if (mediaStream2 != null) {
                return mediaStream2;
            }
        }
        return null;
    }

    public MediaStreamTrack getTrack(int i, String str) {
        if (i == -1) {
            return getLocalTrack(str);
        }
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null) {
            Log.d(TAG, "getTrack(): could not find PeerConnection");
            return null;
        }
        return peerConnectionObserver.remoteTracks.get(str);
    }

    MediaStreamTrack getLocalTrack(String str) {
        return this.getUserMediaImpl.getTrack(str);
    }

    public VideoTrack createVideoTrack(AbstractVideoCaptureController abstractVideoCaptureController) {
        return this.getUserMediaImpl.createVideoTrack(abstractVideoCaptureController);
    }

    public void createStream(MediaStreamTrack[] mediaStreamTrackArr, GetUserMediaImpl.BiConsumer<String, ArrayList<WritableMap>> biConsumer) {
        this.getUserMediaImpl.createStream(mediaStreamTrackArr, biConsumer);
    }

    MediaConstraints constraintsForOptions(ReadableMap readableMap) {
        MediaConstraints mediaConstraints = new MediaConstraints();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            mediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair(strNextKey, ReactBridgeUtil.getMapStrValue(readableMap, strNextKey)));
        }
        return mediaConstraints;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap peerConnectionAddTransceiver(final int i, final ReadableMap readableMap) {
        try {
            return (WritableMap) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda15
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$peerConnectionAddTransceiver$2(i, readableMap);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(TAG, "peerConnectionAddTransceiver() " + e.getMessage());
            return null;
        }
    }

    public /* synthetic */ Object lambda$peerConnectionAddTransceiver$2(int i, ReadableMap readableMap) throws Exception {
        RtpTransceiver rtpTransceiverAddTransceiver;
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null) {
            Log.d(TAG, "peerConnectionAddTransceiver() peerConnection is null");
            return null;
        }
        if (readableMap.hasKey("type")) {
            rtpTransceiverAddTransceiver = peerConnectionObserver.addTransceiver(SerializeUtils.parseMediaType(readableMap.getString("type")), SerializeUtils.parseTransceiverOptions(readableMap.getMap(Session.JsonKeys.INIT)));
        } else if (readableMap.hasKey("trackId")) {
            rtpTransceiverAddTransceiver = peerConnectionObserver.addTransceiver(getLocalTrack(readableMap.getString("trackId")), SerializeUtils.parseTransceiverOptions(readableMap.getMap(Session.JsonKeys.INIT)));
        } else {
            Log.d(TAG, "peerConnectionAddTransceiver() no type nor trackId provided in options");
            return null;
        }
        if (rtpTransceiverAddTransceiver == null) {
            Log.d(TAG, "peerConnectionAddTransceiver() Error adding transceiver");
            return null;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("transceiverOrder", peerConnectionObserver.getNextTransceiverId());
        writableMapCreateMap.putMap("transceiver", SerializeUtils.serializeTransceiver(i, rtpTransceiverAddTransceiver));
        return writableMapCreateMap;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap peerConnectionAddTrack(final int i, final String str, final ReadableMap readableMap) {
        try {
            return (WritableMap) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda14
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$peerConnectionAddTrack$3(i, str, readableMap);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(TAG, "peerConnectionAddTrack() " + e.getMessage());
            return null;
        }
    }

    public /* synthetic */ Object lambda$peerConnectionAddTrack$3(int i, String str, ReadableMap readableMap) throws Exception {
        ReadableArray array;
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null) {
            Log.d(TAG, "peerConnectionAddTrack() peerConnection is null");
            return null;
        }
        MediaStreamTrack localTrack = getLocalTrack(str);
        if (localTrack == null) {
            Log.w(TAG, "peerConnectionAddTrack() couldn't find track " + str);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (readableMap.hasKey("streamIds") && (array = readableMap.getArray("streamIds")) != null) {
            for (int i2 = 0; i2 < array.size(); i2++) {
                arrayList.add(array.getString(i2));
            }
        }
        RtpSender rtpSenderAddTrack = peerConnectionObserver.getPeerConnection().addTrack(localTrack, arrayList);
        RtpTransceiver transceiver = peerConnectionObserver.getTransceiver(rtpSenderAddTrack.id());
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("transceiverOrder", peerConnectionObserver.getNextTransceiverId());
        writableMapCreateMap.putMap("transceiver", SerializeUtils.serializeTransceiver(i, transceiver));
        writableMapCreateMap.putMap("sender", SerializeUtils.serializeSender(i, rtpSenderAddTrack));
        return writableMapCreateMap;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean peerConnectionRemoveTrack(final int i, final String str) {
        try {
            return ((Boolean) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda32
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$peerConnectionRemoveTrack$4(i, str);
                }
            }).get()).booleanValue();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(TAG, "peerConnectionRemoveTrack() " + e.getMessage());
            return false;
        }
    }

    public /* synthetic */ Object lambda$peerConnectionRemoveTrack$4(int i, String str) throws Exception {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null) {
            Log.d(TAG, "peerConnectionRemoveTrack() peerConnection is null");
            return false;
        }
        RtpSender sender = peerConnectionObserver.getSender(str);
        if (sender == null) {
            Log.w(TAG, "peerConnectionRemoveTrack() sender is null");
            return false;
        }
        return Boolean.valueOf(peerConnectionObserver.getPeerConnection().removeTrack(sender));
    }

    @ReactMethod
    public void peerConnectionSendDTMF(final int i, final String str, final String str2, final int i2, final int i3) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionSendDTMF$5(i, str, str2, i2, i3);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionSendDTMF$5(int i, String str, String str2, int i2, int i3) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "peerConnectionSendDTMF() peerConnection is null");
            return;
        }
        RtpSender sender = peerConnectionObserver.getSender(str);
        if (sender == null) {
            Log.w(TAG, "peerConnectionSendDTMF() sender is null");
        } else {
            sender.dtmf().insertDtmf(str2, i2, i3);
        }
    }

    @ReactMethod
    public void senderSetParameters(final int i, final String str, final ReadableMap readableMap, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$senderSetParameters$6(i, promise, str, readableMap);
            }
        });
    }

    public /* synthetic */ void lambda$senderSetParameters$6(int i, Promise promise, String str, ReadableMap readableMap) {
        try {
            PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
            if (peerConnectionObserver == null) {
                Log.d(TAG, "senderSetParameters() peerConnectionObserver is null");
                promise.reject(new Exception("Peer Connection is not initialized"));
                return;
            }
            RtpSender sender = peerConnectionObserver.getSender(str);
            if (sender == null) {
                Log.w(TAG, "senderSetParameters() sender is null");
                promise.reject(new Exception("Could not get sender"));
            } else {
                sender.setParameters(SerializeUtils.updateRtpParameters(readableMap, sender.getParameters()));
                promise.resolve(SerializeUtils.serializeRtpParameters(sender.getParameters()));
            }
        } catch (Exception e) {
            Log.d(TAG, "senderSetParameters: " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void transceiverStop(final int i, final String str, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$transceiverStop$7(i, promise, str);
            }
        });
    }

    public /* synthetic */ void lambda$transceiverStop$7(int i, Promise promise, String str) {
        try {
            PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
            if (peerConnectionObserver == null) {
                Log.d(TAG, "transceiverStop() peerConnectionObserver is null");
                promise.reject(new Exception("Peer Connection is not initialized"));
                return;
            }
            RtpTransceiver transceiver = peerConnectionObserver.getTransceiver(str);
            if (transceiver == null) {
                Log.w(TAG, "transceiverStop() transceiver is null");
                promise.reject(new Exception("Could not get transceiver"));
            } else {
                transceiver.stopStandard();
                promise.resolve(true);
            }
        } catch (Exception e) {
            Log.d(TAG, "transceiverStop(): " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void senderReplaceTrack(final int i, final String str, final String str2, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda21
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$senderReplaceTrack$8(i, promise, str, str2);
            }
        });
    }

    public /* synthetic */ void lambda$senderReplaceTrack$8(int i, Promise promise, String str, String str2) {
        try {
            PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
            if (peerConnectionObserver == null) {
                Log.d(TAG, "senderReplaceTrack() peerConnectionObserver is null");
                promise.reject(new Exception("Peer Connection is not initialized"));
                return;
            }
            RtpSender sender = peerConnectionObserver.getSender(str);
            if (sender == null) {
                Log.w(TAG, "senderReplaceTrack() sender is null");
                promise.reject(new Exception("Could not get sender"));
            } else {
                sender.setTrack(getLocalTrack(str2), false);
                promise.resolve(true);
            }
        } catch (Exception e) {
            Log.d(TAG, "senderReplaceTrack(): " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod
    public void transceiverSetDirection(final int i, final String str, final String str2, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda35
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$transceiverSetDirection$9(i, str, promise, str2);
            }
        });
    }

    public /* synthetic */ void lambda$transceiverSetDirection$9(int i, String str, Promise promise, String str2) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Arguments.createMap();
        writableMapCreateMap.putInt("peerConnectionId", i);
        writableMapCreateMap.putString("transceiverId", str);
        try {
            PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
            if (peerConnectionObserver == null) {
                Log.d(TAG, "transceiverSetDirection() peerConnectionObserver is null");
                promise.reject(new Exception("Peer Connection is not initialized"));
                return;
            }
            RtpTransceiver transceiver = peerConnectionObserver.getTransceiver(str);
            if (transceiver == null) {
                Log.d(TAG, "transceiverSetDirection() transceiver is null");
                promise.reject(new Exception("Could not get sender"));
            } else {
                transceiver.setDirection(SerializeUtils.parseDirection(str2));
                promise.resolve(true);
            }
        } catch (Exception e) {
            Log.d(TAG, "transceiverSetDirection(): " + e.getMessage());
            promise.reject(e);
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean transceiverSetCodecPreferences(final int i, final String str, final ReadableArray readableArray) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$transceiverSetCodecPreferences$10(i, str, readableArray);
            }
        });
        return true;
    }

    public /* synthetic */ void lambda$transceiverSetCodecPreferences$10(int i, String str, ReadableArray readableArray) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Arguments.createMap();
        writableMapCreateMap.putInt("peerConnectionId", i);
        writableMapCreateMap.putString("transceiverId", str);
        try {
            PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
            if (peerConnectionObserver == null) {
                Log.d(TAG, "transceiverSetDirection() peerConnectionObserver is null");
                return;
            }
            RtpTransceiver transceiver = peerConnectionObserver.getTransceiver(str);
            if (transceiver == null) {
                Log.d(TAG, "transceiverSetDirection() transceiver is null");
                return;
            }
            RtpTransceiver.RtpTransceiverDirection direction = transceiver.getDirection();
            ArrayList arrayList = new ArrayList();
            if (direction.equals(RtpTransceiver.RtpTransceiverDirection.SEND_RECV) || direction.equals(RtpTransceiver.RtpTransceiverDirection.SEND_ONLY)) {
                for (RtpCapabilities.CodecCapability codecCapability : this.mFactory.getRtpSenderCapabilities(transceiver.getMediaType()).codecs) {
                    arrayList.add(new Pair(SerializeUtils.serializeRtpCapabilitiesCodec(codecCapability).toHashMap(), codecCapability));
                }
            }
            if (direction.equals(RtpTransceiver.RtpTransceiverDirection.SEND_RECV) || direction.equals(RtpTransceiver.RtpTransceiverDirection.RECV_ONLY)) {
                for (RtpCapabilities.CodecCapability codecCapability2 : this.mFactory.getRtpReceiverCapabilities(transceiver.getMediaType()).codecs) {
                    arrayList.add(new Pair(SerializeUtils.serializeRtpCapabilitiesCodec(codecCapability2).toHashMap(), codecCapability2));
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                HashMap<String, Object> hashMap = readableArray.getMap(i2).toHashMap();
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        if (hashMap.equals((Map) pair.first)) {
                            arrayList2.add((RtpCapabilities.CodecCapability) pair.second);
                            break;
                        }
                    }
                }
            }
            transceiver.setCodecPreferences(arrayList2);
        } catch (Exception e) {
            Log.d(TAG, "transceiverSetCodecPreferences(): " + e.getMessage());
        }
    }

    public /* synthetic */ void lambda$getDisplayMedia$11(Promise promise) {
        this.getUserMediaImpl.getDisplayMedia(promise);
    }

    @ReactMethod
    public void getDisplayMedia(final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$getDisplayMedia$11(promise);
            }
        });
    }

    public /* synthetic */ void lambda$getUserMedia$12(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.getUserMediaImpl.getUserMedia(readableMap, callback, callback2);
    }

    @ReactMethod
    public void getUserMedia(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda37
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$getUserMedia$12(readableMap, callback, callback2);
            }
        });
    }

    public /* synthetic */ void lambda$enumerateDevices$13(Callback callback) {
        callback.invoke(this.getUserMediaImpl.enumerateDevices());
    }

    @ReactMethod
    public void enumerateDevices(final Callback callback) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$enumerateDevices$13(callback);
            }
        });
    }

    @ReactMethod
    public void mediaStreamCreate(final String str) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamCreate$14(str);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamCreate$14(String str) {
        this.localStreams.put(str, this.mFactory.createLocalMediaStream(str));
    }

    @ReactMethod
    public void mediaStreamAddTrack(final String str, final int i, final String str2) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda31
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamAddTrack$15(str, i, str2);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamAddTrack$15(String str, int i, String str2) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            Log.d(TAG, "mediaStreamAddTrack() could not find stream " + str);
            return;
        }
        MediaStreamTrack track = getTrack(i, str2);
        if (track == null) {
            Log.d(TAG, "mediaStreamAddTrack() could not find track " + str2);
            return;
        }
        String strKind = track.kind();
        if (MediaStreamTrack.AUDIO_TRACK_KIND.equals(strKind)) {
            mediaStream.addTrack((AudioTrack) track);
        } else if ("video".equals(strKind)) {
            mediaStream.addTrack((VideoTrack) track);
        }
    }

    @ReactMethod
    public void mediaStreamRemoveTrack(final String str, final int i, final String str2) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamRemoveTrack$16(str, i, str2);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamRemoveTrack$16(String str, int i, String str2) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            Log.d(TAG, "mediaStreamRemoveTrack() could not find stream " + str);
            return;
        }
        MediaStreamTrack track = getTrack(i, str2);
        if (track == null) {
            Log.d(TAG, "mediaStreamRemoveTrack() could not find track " + str2);
            return;
        }
        String strKind = track.kind();
        if (MediaStreamTrack.AUDIO_TRACK_KIND.equals(strKind)) {
            mediaStream.removeTrack((AudioTrack) track);
        } else if ("video".equals(strKind)) {
            mediaStream.removeTrack((VideoTrack) track);
        }
    }

    @ReactMethod
    public void mediaStreamRelease(final String str) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda33
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamRelease$17(str);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamRelease$17(String str) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            Log.d(TAG, "mediaStreamRelease() stream is null");
        } else {
            this.localStreams.remove(str);
            mediaStream.dispose();
        }
    }

    @ReactMethod
    public void mediaStreamTrackRelease(final String str) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamTrackRelease$18(str);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamTrackRelease$18(String str) {
        MediaStreamTrack localTrack = getLocalTrack(str);
        if (localTrack == null) {
            Log.d(TAG, "mediaStreamTrackRelease() track is null");
        } else {
            localTrack.setEnabled(false);
            this.getUserMediaImpl.disposeTrack(str);
        }
    }

    @ReactMethod
    public void mediaStreamTrackSetEnabled(final int i, final String str, final boolean z) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamTrackSetEnabled$19(i, str, z);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamTrackSetEnabled$19(int i, String str, boolean z) {
        MediaStreamTrack track = getTrack(i, str);
        if (track == null) {
            Log.d(TAG, "mediaStreamTrackSetEnabled() could not find track " + str);
        } else {
            if (track.enabled() == z) {
                return;
            }
            track.setEnabled(z);
            this.getUserMediaImpl.mediaStreamTrackSetEnabled(str, z);
        }
    }

    @ReactMethod
    public void mediaStreamTrackApplyConstraints(final String str, final ReadableMap readableMap, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamTrackApplyConstraints$20(str, readableMap, promise);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamTrackApplyConstraints$20(String str, ReadableMap readableMap, Promise promise) {
        if (getLocalTrack(str) != null) {
            this.getUserMediaImpl.applyConstraints(str, readableMap, promise);
        } else {
            promise.reject(new Exception("mediaStreamTrackApplyConstraints() could not find track " + str));
        }
    }

    @ReactMethod
    public void mediaStreamTrackSetVolume(final int i, final String str, final double d) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamTrackSetVolume$21(i, str, d);
            }
        });
    }

    public /* synthetic */ void lambda$mediaStreamTrackSetVolume$21(int i, String str, double d) {
        MediaStreamTrack track = getTrack(i, str);
        if (track == null) {
            Log.d(TAG, "mediaStreamTrackSetVolume() could not find track " + str);
        } else if (!(track instanceof AudioTrack)) {
            Log.d(TAG, "mediaStreamTrackSetVolume() track is not an AudioTrack!");
        } else {
            ((AudioTrack) track).setVolume(d);
        }
    }

    public ReadableArray getTransceiversInfo(PeerConnection peerConnection) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (RtpTransceiver rtpTransceiver : peerConnection.getTransceivers()) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            RtpTransceiver.RtpTransceiverDirection currentDirection = rtpTransceiver.getCurrentDirection();
            if (currentDirection != null) {
                writableMapCreateMap.putString("currentDirection", SerializeUtils.serializeDirection(currentDirection));
            }
            writableMapCreateMap.putString("transceiverId", rtpTransceiver.getSender().id());
            writableMapCreateMap.putString("mid", rtpTransceiver.getMid());
            writableMapCreateMap.putBoolean("isStopped", rtpTransceiver.isStopped());
            writableMapCreateMap.putMap("senderRtpParameters", SerializeUtils.serializeRtpParameters(rtpTransceiver.getSender().getParameters()));
            writableMapCreateMap.putMap("receiverRtpParameters", SerializeUtils.serializeRtpParameters(rtpTransceiver.getReceiver().getParameters()));
            writableArrayCreateArray.pushMap(writableMapCreateMap);
        }
        return writableArrayCreateArray;
    }

    public /* synthetic */ void lambda$mediaStreamTrackSetVideoEffects$22(String str, ReadableArray readableArray) {
        this.getUserMediaImpl.setVideoEffects(str, readableArray);
    }

    @ReactMethod
    public void mediaStreamTrackSetVideoEffects(final String str, final ReadableArray readableArray) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$mediaStreamTrackSetVideoEffects$22(str, readableArray);
            }
        });
    }

    @ReactMethod
    public void peerConnectionSetConfiguration(final ReadableMap readableMap, final int i) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda39
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionSetConfiguration$23(i, readableMap);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionSetConfiguration$23(int i, ReadableMap readableMap) {
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionSetConfiguration() peerConnection is null");
        } else {
            peerConnection.setConfiguration(parseRTCConfiguration(readableMap));
        }
    }

    @ReactMethod
    public void peerConnectionCreateOffer(final int i, final ReadableMap readableMap, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionCreateOffer$24(i, promise, readableMap);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionCreateOffer$24(int i, Promise promise, ReadableMap readableMap) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        PeerConnection peerConnection = peerConnectionObserver.getPeerConnection();
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionCreateOffer() peerConnection is null");
            promise.reject(new Exception("PeerConnection not found"));
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<RtpTransceiver> it = peerConnection.getTransceivers().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getReceiver().id());
        }
        peerConnection.createOffer(new AnonymousClass1(promise, peerConnection, arrayList, peerConnectionObserver, i), constraintsForOptions(readableMap));
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.WebRTCModule$1 */
    class AnonymousClass1 implements SdpObserver {
        final /* synthetic */ int val$id;
        final /* synthetic */ PeerConnectionObserver val$pco;
        final /* synthetic */ PeerConnection val$peerConnection;
        final /* synthetic */ Promise val$promise;
        final /* synthetic */ List val$receiversIds;

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(String str) {
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
        }

        AnonymousClass1(Promise promise, PeerConnection peerConnection, List list, PeerConnectionObserver peerConnectionObserver, int i) {
            this.val$promise = promise;
            this.val$peerConnection = peerConnection;
            this.val$receiversIds = list;
            this.val$pco = peerConnectionObserver;
            this.val$id = i;
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(final String str) {
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    promise.reject("E_OPERATION_ERROR", str);
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(final SessionDescription sessionDescription) {
            final PeerConnection peerConnection = this.val$peerConnection;
            final List list = this.val$receiversIds;
            final PeerConnectionObserver peerConnectionObserver = this.val$pco;
            final int i = this.val$id;
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCreateSuccess$1(sessionDescription, peerConnection, list, peerConnectionObserver, i, promise);
                }
            });
        }

        public /* synthetic */ void lambda$onCreateSuccess$1(SessionDescription sessionDescription, PeerConnection peerConnection, List list, PeerConnectionObserver peerConnectionObserver, int i, Promise promise) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putString("sdp", sessionDescription.description);
            writableMapCreateMap2.putString("type", sessionDescription.type.canonicalForm());
            writableMapCreateMap.putArray("transceiversInfo", WebRTCModule.this.getTransceiversInfo(peerConnection));
            writableMapCreateMap.putMap("sdpInfo", writableMapCreateMap2);
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (RtpTransceiver rtpTransceiver : peerConnection.getTransceivers()) {
                if (!list.contains(rtpTransceiver.getReceiver().id())) {
                    WritableMap writableMapCreateMap3 = Arguments.createMap();
                    writableMapCreateMap3.putInt("transceiverOrder", peerConnectionObserver.getNextTransceiverId());
                    writableMapCreateMap3.putMap("transceiver", SerializeUtils.serializeTransceiver(i, rtpTransceiver));
                    writableArrayCreateArray.pushMap(writableMapCreateMap3);
                }
            }
            writableMapCreateMap.putArray("newTransceivers", writableArrayCreateArray);
            promise.resolve(writableMapCreateMap);
        }
    }

    @ReactMethod
    public void peerConnectionCreateAnswer(final int i, final ReadableMap readableMap, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionCreateAnswer$25(i, promise, readableMap);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionCreateAnswer$25(int i, Promise promise, ReadableMap readableMap) {
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionCreateAnswer() peerConnection is null");
            promise.reject(new Exception("PeerConnection not found"));
        } else {
            peerConnection.createAnswer(new AnonymousClass2(promise, peerConnection), constraintsForOptions(readableMap));
        }
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.WebRTCModule$2 */
    class AnonymousClass2 implements SdpObserver {
        final /* synthetic */ PeerConnection val$peerConnection;
        final /* synthetic */ Promise val$promise;

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(String str) {
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
        }

        AnonymousClass2(Promise promise, PeerConnection peerConnection) {
            this.val$promise = promise;
            this.val$peerConnection = peerConnection;
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(final String str) {
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    promise.reject("E_OPERATION_ERROR", str);
                }
            });
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(final SessionDescription sessionDescription) {
            final PeerConnection peerConnection = this.val$peerConnection;
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCreateSuccess$1(sessionDescription, peerConnection, promise);
                }
            });
        }

        public /* synthetic */ void lambda$onCreateSuccess$1(SessionDescription sessionDescription, PeerConnection peerConnection, Promise promise) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putString("sdp", sessionDescription.description);
            writableMapCreateMap2.putString("type", sessionDescription.type.canonicalForm());
            writableMapCreateMap.putArray("transceiversInfo", WebRTCModule.this.getTransceiversInfo(peerConnection));
            writableMapCreateMap.putMap("sdpInfo", writableMapCreateMap2);
            promise.resolve(writableMapCreateMap);
        }
    }

    @ReactMethod
    public void peerConnectionSetLocalDescription(final int i, final ReadableMap readableMap, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionSetLocalDescription$26(i, promise, readableMap);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionSetLocalDescription$26(int i, Promise promise, ReadableMap readableMap) {
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionSetLocalDescription() peerConnection is null");
            promise.reject(new Exception("PeerConnection not found"));
            return;
        }
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(peerConnection, promise);
        if (readableMap != null) {
            peerConnection.setLocalDescription(anonymousClass3, new SessionDescription(SessionDescription.Type.fromCanonicalForm((String) Objects.requireNonNull(readableMap.getString("type"))), readableMap.getString("sdp")));
        } else {
            peerConnection.setLocalDescription(anonymousClass3);
        }
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.WebRTCModule$3 */
    class AnonymousClass3 implements SdpObserver {
        final /* synthetic */ PeerConnection val$peerConnection;
        final /* synthetic */ Promise val$promise;

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(String str) {
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(SessionDescription sessionDescription) {
        }

        AnonymousClass3(PeerConnection peerConnection, Promise promise) {
            this.val$peerConnection = peerConnection;
            this.val$promise = promise;
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
            final PeerConnection peerConnection = this.val$peerConnection;
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSetSuccess$0(peerConnection, promise);
                }
            });
        }

        public /* synthetic */ void lambda$onSetSuccess$0(PeerConnection peerConnection, Promise promise) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            SessionDescription localDescription = peerConnection.getLocalDescription();
            if (localDescription != null) {
                writableMapCreateMap.putString("type", localDescription.type.canonicalForm());
                writableMapCreateMap.putString("sdp", localDescription.description);
            }
            writableMapCreateMap2.putMap("sdpInfo", writableMapCreateMap);
            writableMapCreateMap2.putArray("transceiversInfo", WebRTCModule.this.getTransceiversInfo(peerConnection));
            promise.resolve(writableMapCreateMap2);
        }

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(final String str) {
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$3$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    promise.reject("E_OPERATION_ERROR", str);
                }
            });
        }
    }

    @ReactMethod
    public void peerConnectionSetRemoteDescription(final int i, final ReadableMap readableMap, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionSetRemoteDescription$27(i, promise, readableMap);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionSetRemoteDescription$27(int i, Promise promise, ReadableMap readableMap) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        PeerConnection peerConnection = peerConnectionObserver.getPeerConnection();
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionSetRemoteDescription() peerConnection is null");
            promise.reject(new Exception("PeerConnection not found"));
            return;
        }
        SessionDescription sessionDescription = new SessionDescription(SessionDescription.Type.fromCanonicalForm(readableMap.getString("type")), readableMap.getString("sdp"));
        ArrayList arrayList = new ArrayList();
        Iterator<RtpTransceiver> it = peerConnection.getTransceivers().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getReceiver().id());
        }
        peerConnection.setRemoteDescription(new AnonymousClass4(peerConnection, arrayList, peerConnectionObserver, i, promise), sessionDescription);
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.WebRTCModule$4 */
    class AnonymousClass4 implements SdpObserver {
        final /* synthetic */ int val$id;
        final /* synthetic */ PeerConnectionObserver val$pco;
        final /* synthetic */ PeerConnection val$peerConnection;
        final /* synthetic */ Promise val$promise;
        final /* synthetic */ List val$receiversIds;

        @Override // org.webrtc.SdpObserver
        public void onCreateFailure(String str) {
        }

        @Override // org.webrtc.SdpObserver
        public void onCreateSuccess(SessionDescription sessionDescription) {
        }

        AnonymousClass4(PeerConnection peerConnection, List list, PeerConnectionObserver peerConnectionObserver, int i, Promise promise) {
            this.val$peerConnection = peerConnection;
            this.val$receiversIds = list;
            this.val$pco = peerConnectionObserver;
            this.val$id = i;
            this.val$promise = promise;
        }

        @Override // org.webrtc.SdpObserver
        public void onSetSuccess() {
            final PeerConnection peerConnection = this.val$peerConnection;
            final List list = this.val$receiversIds;
            final PeerConnectionObserver peerConnectionObserver = this.val$pco;
            final int i = this.val$id;
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$4$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSetSuccess$0(peerConnection, list, peerConnectionObserver, i, promise);
                }
            });
        }

        public /* synthetic */ void lambda$onSetSuccess$0(PeerConnection peerConnection, List list, PeerConnectionObserver peerConnectionObserver, int i, Promise promise) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            SessionDescription remoteDescription = peerConnection.getRemoteDescription();
            if (remoteDescription != null) {
                writableMapCreateMap.putString("type", remoteDescription.type.canonicalForm());
                writableMapCreateMap.putString("sdp", remoteDescription.description);
            }
            writableMapCreateMap2.putArray("transceiversInfo", WebRTCModule.this.getTransceiversInfo(peerConnection));
            writableMapCreateMap2.putMap("sdpInfo", writableMapCreateMap);
            WritableArray writableArrayCreateArray = Arguments.createArray();
            for (RtpTransceiver rtpTransceiver : peerConnection.getTransceivers()) {
                if (!list.contains(rtpTransceiver.getReceiver().id())) {
                    WritableMap writableMapCreateMap3 = Arguments.createMap();
                    writableMapCreateMap3.putInt("transceiverOrder", peerConnectionObserver.getNextTransceiverId());
                    writableMapCreateMap3.putMap("transceiver", SerializeUtils.serializeTransceiver(i, rtpTransceiver));
                    writableArrayCreateArray.pushMap(writableMapCreateMap3);
                }
            }
            writableMapCreateMap2.putArray("newTransceivers", writableArrayCreateArray);
            promise.resolve(writableMapCreateMap2);
        }

        @Override // org.webrtc.SdpObserver
        public void onSetFailure(final String str) {
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    promise.reject("E_OPERATION_ERROR", str);
                }
            });
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap receiverGetCapabilities(final String str) {
        try {
            return (WritableMap) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda34
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$receiverGetCapabilities$28(str);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(TAG, "receiverGetCapabilities() " + e.getMessage());
            return null;
        }
    }

    public /* synthetic */ Object lambda$receiverGetCapabilities$28(String str) throws Exception {
        MediaStreamTrack.MediaType mediaType;
        if (str.equals(MediaStreamTrack.AUDIO_TRACK_KIND)) {
            mediaType = MediaStreamTrack.MediaType.MEDIA_TYPE_AUDIO;
        } else if (str.equals("video")) {
            mediaType = MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO;
        } else {
            return Arguments.createMap();
        }
        return SerializeUtils.serializeRtpCapabilities(this.mFactory.getRtpReceiverCapabilities(mediaType));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap senderGetCapabilities(final String str) {
        try {
            return (WritableMap) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$senderGetCapabilities$29(str);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(TAG, "senderGetCapabilities() " + e.getMessage());
            return null;
        }
    }

    public /* synthetic */ Object lambda$senderGetCapabilities$29(String str) throws Exception {
        MediaStreamTrack.MediaType mediaType;
        if (str.equals(MediaStreamTrack.AUDIO_TRACK_KIND)) {
            mediaType = MediaStreamTrack.MediaType.MEDIA_TYPE_AUDIO;
        } else if (str.equals("video")) {
            mediaType = MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO;
        } else {
            return Arguments.createMap();
        }
        return SerializeUtils.serializeRtpCapabilities(this.mFactory.getRtpSenderCapabilities(mediaType));
    }

    @ReactMethod
    public void receiverGetStats(final int i, final String str, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$receiverGetStats$30(i, promise, str);
            }
        });
    }

    public /* synthetic */ void lambda$receiverGetStats$30(int i, Promise promise, String str) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "receiverGetStats() peerConnection is null");
            promise.resolve(StringUtils.statsToJSON(new RTCStatsReport(0L, new HashMap())));
        } else {
            peerConnectionObserver.receiverGetStats(str, promise);
        }
    }

    @ReactMethod
    public void senderGetStats(final int i, final String str, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$senderGetStats$31(i, promise, str);
            }
        });
    }

    public /* synthetic */ void lambda$senderGetStats$31(int i, Promise promise, String str) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "senderGetStats() peerConnection is null");
            promise.resolve(StringUtils.statsToJSON(new RTCStatsReport(0L, new HashMap())));
        } else {
            peerConnectionObserver.senderGetStats(str, promise);
        }
    }

    @ReactMethod
    public void peerConnectionAddICECandidate(final int i, final ReadableMap readableMap, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda40
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionAddICECandidate$32(i, promise, readableMap);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionAddICECandidate$32(int i, Promise promise, ReadableMap readableMap) {
        String string;
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionAddICECandidate() peerConnection is null");
            promise.reject(new Exception("PeerConnection not found"));
        } else {
            if (readableMap.hasKey("sdpMid") || readableMap.hasKey("sdpMLineIndex")) {
                if (readableMap.hasKey("sdpMid") && !readableMap.isNull("sdpMid")) {
                    string = readableMap.getString("sdpMid");
                } else {
                    string = "";
                }
                peerConnection.addIceCandidate(new IceCandidate(string, (!readableMap.hasKey("sdpMLineIndex") || readableMap.isNull("sdpMLineIndex")) ? 0 : readableMap.getInt("sdpMLineIndex"), readableMap.getString("candidate")), new AnonymousClass5(peerConnection, promise));
                return;
            }
            promise.reject("E_TYPE_ERROR", "Invalid argument");
        }
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.WebRTCModule$5 */
    class AnonymousClass5 implements AddIceObserver {
        final /* synthetic */ PeerConnection val$peerConnection;
        final /* synthetic */ Promise val$promise;

        AnonymousClass5(PeerConnection peerConnection, Promise promise) {
            this.val$peerConnection = peerConnection;
            this.val$promise = promise;
        }

        @Override // org.webrtc.AddIceObserver
        public void onAddSuccess() {
            final PeerConnection peerConnection = this.val$peerConnection;
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WebRTCModule.AnonymousClass5.lambda$onAddSuccess$0(peerConnection, promise);
                }
            });
        }

        static /* synthetic */ void lambda$onAddSuccess$0(PeerConnection peerConnection, Promise promise) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            SessionDescription remoteDescription = peerConnection.getRemoteDescription();
            writableMapCreateMap.putString("type", remoteDescription.type.canonicalForm());
            writableMapCreateMap.putString("sdp", remoteDescription.description);
            promise.resolve(writableMapCreateMap);
        }

        @Override // org.webrtc.AddIceObserver
        public void onAddFailure(final String str) {
            final Promise promise = this.val$promise;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$5$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    promise.reject("E_OPERATION_ERROR", str);
                }
            });
        }
    }

    @ReactMethod
    public void peerConnectionGetStats(final int i, final Promise promise) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionGetStats$33(i, promise);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionGetStats$33(int i, Promise promise) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "peerConnectionGetStats() peerConnection is null");
            promise.resolve(StringUtils.statsToJSON(new RTCStatsReport(0L, new HashMap())));
        } else {
            peerConnectionObserver.getStats(promise);
        }
    }

    @ReactMethod
    public void peerConnectionClose(final int i) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionClose$34(i);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionClose$34(int i) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "peerConnectionClose() peerConnection is null");
        } else {
            peerConnectionObserver.close();
        }
    }

    @ReactMethod
    public void peerConnectionDispose(final int i) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionDispose$35(i);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionDispose$35(int i) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "peerConnectionDispose() peerConnection is null");
        }
        peerConnectionObserver.dispose();
        this.mPeerConnectionObservers.remove(i);
    }

    @ReactMethod
    public void peerConnectionRestartIce(final int i) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$peerConnectionRestartIce$36(i);
            }
        });
    }

    public /* synthetic */ void lambda$peerConnectionRestartIce$36(int i) {
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection == null) {
            Log.w(TAG, "peerConnectionRestartIce() peerConnection is null");
        } else {
            peerConnection.restartIce();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap createDataChannel(final int i, final String str, final ReadableMap readableMap) {
        try {
            return (WritableMap) ThreadUtils.submitToExecutor(new Callable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda22
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.lambda$createDataChannel$37(i, str, readableMap);
                }
            }).get();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    public /* synthetic */ Object lambda$createDataChannel$37(int i, String str, ReadableMap readableMap) throws Exception {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "createDataChannel() peerConnection is null");
            return null;
        }
        return peerConnectionObserver.createDataChannel(str, readableMap);
    }

    @ReactMethod
    public void dataChannelClose(final int i, final String str) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dataChannelClose$38(i, str);
            }
        });
    }

    public /* synthetic */ void lambda$dataChannelClose$38(int i, String str) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "dataChannelClose() peerConnection is null");
        } else {
            peerConnectionObserver.dataChannelClose(str);
        }
    }

    @ReactMethod
    public void dataChannelDispose(final int i, final String str) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda38
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dataChannelDispose$39(i, str);
            }
        });
    }

    public /* synthetic */ void lambda$dataChannelDispose$39(int i, String str) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "dataChannelDispose() peerConnection is null");
        } else {
            peerConnectionObserver.dataChannelDispose(str);
        }
    }

    @ReactMethod
    public void dataChannelSend(final int i, final String str, final String str2, final String str3) {
        ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCModule$$ExternalSyntheticLambda29
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dataChannelSend$40(i, str, str2, str3);
            }
        });
    }

    public /* synthetic */ void lambda$dataChannelSend$40(int i, String str, String str2, String str3) {
        PeerConnectionObserver peerConnectionObserver = this.mPeerConnectionObservers.get(i);
        if (peerConnectionObserver == null || peerConnectionObserver.getPeerConnection() == null) {
            Log.d(TAG, "dataChannelSend() peerConnection is null");
        } else {
            peerConnectionObserver.dataChannelSend(str, str2, str3);
        }
    }
}
