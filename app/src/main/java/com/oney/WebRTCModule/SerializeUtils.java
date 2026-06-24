package com.oney.WebRTCModule;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.webrtc.AudioTrack;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.RtpCapabilities;
import org.webrtc.RtpParameters;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.VideoCodecInfo;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes2.dex */
public class SerializeUtils {
    public static ReadableMap serializeVideoCodecInfo(VideoCodecInfo videoCodecInfo) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("mimeType", "video/" + videoCodecInfo.name);
        return writableMapCreateMap;
    }

    public static ReadableMap serializeStream(int i, String str, MediaStream mediaStream) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("streamId", mediaStream.getId());
        writableMapCreateMap.putString("streamReactTag", str);
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<VideoTrack> it = mediaStream.videoTracks.iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushMap(serializeTrack(i, it.next()));
        }
        Iterator<AudioTrack> it2 = mediaStream.audioTracks.iterator();
        while (it2.hasNext()) {
            writableArrayCreateArray.pushMap(serializeTrack(i, it2.next()));
        }
        writableMapCreateMap.putArray("tracks", writableArrayCreateArray);
        return writableMapCreateMap;
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.SerializeUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$RtpTransceiver$RtpTransceiverDirection;

        static {
            int[] iArr = new int[RtpTransceiver.RtpTransceiverDirection.values().length];
            $SwitchMap$org$webrtc$RtpTransceiver$RtpTransceiverDirection = iArr;
            try {
                iArr[RtpTransceiver.RtpTransceiverDirection.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$RtpTransceiver$RtpTransceiverDirection[RtpTransceiver.RtpTransceiverDirection.RECV_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$RtpTransceiver$RtpTransceiverDirection[RtpTransceiver.RtpTransceiverDirection.SEND_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$webrtc$RtpTransceiver$RtpTransceiverDirection[RtpTransceiver.RtpTransceiverDirection.SEND_RECV.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$webrtc$RtpTransceiver$RtpTransceiverDirection[RtpTransceiver.RtpTransceiverDirection.STOPPED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static String serializeDirection(RtpTransceiver.RtpTransceiverDirection rtpTransceiverDirection) {
        int i = AnonymousClass1.$SwitchMap$org$webrtc$RtpTransceiver$RtpTransceiverDirection[rtpTransceiverDirection.ordinal()];
        if (i == 1) {
            return "inactive";
        }
        if (i == 2) {
            return "recvonly";
        }
        if (i == 3) {
            return "sendonly";
        }
        if (i == 4) {
            return "sendrecv";
        }
        if (i == 5) {
            return "stopped";
        }
        throw new Error("Invalid direction");
    }

    public static ReadableMap serializeTrack(int i, MediaStreamTrack mediaStreamTrack) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("id", mediaStreamTrack.id());
        writableMapCreateMap.putInt("peerConnectionId", i);
        writableMapCreateMap.putString("kind", mediaStreamTrack.kind());
        writableMapCreateMap.putBoolean(ViewProps.ENABLED, mediaStreamTrack.enabled());
        writableMapCreateMap.putString("readyState", mediaStreamTrack.state().toString().toLowerCase());
        writableMapCreateMap.putBoolean("remote", true);
        return writableMapCreateMap;
    }

    public static ReadableMap serializeSender(int i, RtpSender rtpSender) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("id", rtpSender.id());
        writableMapCreateMap.putInt("peerConnectionId", i);
        if (rtpSender.track() != null) {
            writableMapCreateMap.putMap("track", serializeTrack(i, rtpSender.track()));
        }
        writableMapCreateMap.putMap("rtpParameters", serializeRtpParameters(rtpSender.getParameters()));
        return writableMapCreateMap;
    }

    public static ReadableMap serializeReceiver(int i, RtpReceiver rtpReceiver) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("id", rtpReceiver.id());
        writableMapCreateMap.putInt("peerConnectionId", i);
        if (rtpReceiver.track() != null) {
            writableMapCreateMap.putMap("track", serializeTrack(i, rtpReceiver.track()));
        }
        writableMapCreateMap.putMap("rtpParameters", serializeRtpParameters(rtpReceiver.getParameters()));
        return writableMapCreateMap;
    }

    public static ReadableMap serializeTransceiver(int i, RtpTransceiver rtpTransceiver) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("id", rtpTransceiver.getSender().id());
        writableMapCreateMap.putInt("peerConnectionId", i);
        writableMapCreateMap.putString("mid", rtpTransceiver.getMid());
        writableMapCreateMap.putString("direction", serializeDirection(rtpTransceiver.getDirection()));
        if (rtpTransceiver.getCurrentDirection() != null) {
            writableMapCreateMap.putString("currentDirection", serializeDirection(rtpTransceiver.getCurrentDirection()));
        }
        writableMapCreateMap.putBoolean("isStopped", rtpTransceiver.isStopped());
        writableMapCreateMap.putMap("receiver", serializeReceiver(i, rtpTransceiver.getReceiver()));
        writableMapCreateMap.putMap("sender", serializeSender(i, rtpTransceiver.getSender()));
        return writableMapCreateMap;
    }

    public static ReadableMap serializeRtpParameters(RtpParameters rtpParameters) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        final WritableArray writableArrayCreateArray = Arguments.createArray();
        final WritableArray writableArrayCreateArray2 = Arguments.createArray();
        final WritableArray writableArrayCreateArray3 = Arguments.createArray();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putString("cname", rtpParameters.getRtcp().getCname());
        writableMapCreateMap2.putBoolean("reducedSize", rtpParameters.getRtcp().getReducedSize());
        rtpParameters.getHeaderExtensions().forEach(new Consumer() { // from class: com.oney.WebRTCModule.SerializeUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SerializeUtils.lambda$serializeRtpParameters$0(writableArrayCreateArray3, (RtpParameters.HeaderExtension) obj);
            }
        });
        rtpParameters.encodings.forEach(new Consumer() { // from class: com.oney.WebRTCModule.SerializeUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SerializeUtils.lambda$serializeRtpParameters$1(writableArrayCreateArray, (RtpParameters.Encoding) obj);
            }
        });
        rtpParameters.codecs.forEach(new Consumer() { // from class: com.oney.WebRTCModule.SerializeUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SerializeUtils.lambda$serializeRtpParameters$2(writableArrayCreateArray2, (RtpParameters.Codec) obj);
            }
        });
        writableMapCreateMap.putString("transactionId", rtpParameters.transactionId);
        writableMapCreateMap.putMap("rtcp", writableMapCreateMap2);
        writableMapCreateMap.putArray("encodings", writableArrayCreateArray);
        writableMapCreateMap.putArray("codecs", writableArrayCreateArray2);
        writableMapCreateMap.putArray("headerExtensions", writableArrayCreateArray3);
        if (rtpParameters.degradationPreference != null) {
            writableMapCreateMap.putString("degradationPreference", rtpParameters.degradationPreference.toString());
        }
        return writableMapCreateMap;
    }

    static /* synthetic */ void lambda$serializeRtpParameters$0(WritableArray writableArray, RtpParameters.HeaderExtension headerExtension) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("id", headerExtension.getId());
        writableMapCreateMap.putString("uri", headerExtension.getUri());
        writableMapCreateMap.putBoolean("encrypted", headerExtension.getEncrypted());
        writableArray.pushMap(writableMapCreateMap);
    }

    static /* synthetic */ void lambda$serializeRtpParameters$1(WritableArray writableArray, RtpParameters.Encoding encoding) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("active", encoding.active);
        if (encoding.rid != null) {
            writableMapCreateMap.putString("rid", encoding.rid);
        }
        if (encoding.maxBitrateBps != null) {
            writableMapCreateMap.putInt("maxBitrate", encoding.maxBitrateBps.intValue());
        }
        if (encoding.maxFramerate != null) {
            writableMapCreateMap.putInt("maxFramerate", encoding.maxFramerate.intValue());
        }
        if (encoding.scaleResolutionDownBy != null) {
            writableMapCreateMap.putDouble("scaleResolutionDownBy", encoding.scaleResolutionDownBy.doubleValue());
        }
        writableArray.pushMap(writableMapCreateMap);
    }

    static /* synthetic */ void lambda$serializeRtpParameters$2(WritableArray writableArray, RtpParameters.Codec codec) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("payloadType", codec.payloadType);
        writableMapCreateMap.putString("mimeType", codec.name);
        writableMapCreateMap.putInt("clockRate", codec.clockRate.intValue());
        if (codec.numChannels != null) {
            writableMapCreateMap.putInt("channels", codec.numChannels.intValue());
        }
        if (!codec.parameters.isEmpty()) {
            writableMapCreateMap.putString("sdpFmtpLine", serializeSdpParameters(codec.parameters));
        }
        writableArray.pushMap(writableMapCreateMap);
    }

    public static ReadableMap serializeRtpCapabilities(RtpCapabilities rtpCapabilities) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        final WritableArray writableArrayCreateArray = Arguments.createArray();
        rtpCapabilities.codecs.forEach(new Consumer() { // from class: com.oney.WebRTCModule.SerializeUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                writableArrayCreateArray.pushMap(SerializeUtils.serializeRtpCapabilitiesCodec((RtpCapabilities.CodecCapability) obj));
            }
        });
        writableMapCreateMap.putArray("codecs", writableArrayCreateArray);
        return writableMapCreateMap;
    }

    public static ReadableMap serializeRtpCapabilitiesCodec(RtpCapabilities.CodecCapability codecCapability) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("payloadType", codecCapability.preferredPayloadType);
        writableMapCreateMap.putString("mimeType", codecCapability.mimeType);
        writableMapCreateMap.putInt("clockRate", codecCapability.clockRate.intValue());
        if (codecCapability.numChannels != null) {
            writableMapCreateMap.putInt("channels", codecCapability.numChannels.intValue());
        }
        if (!codecCapability.parameters.isEmpty()) {
            writableMapCreateMap.putString("sdpFmtpLine", serializeSdpParameters(codecCapability.parameters));
        }
        return writableMapCreateMap;
    }

    public static String serializeSdpParameters(final Map<String, String> map) {
        return (String) map.keySet().stream().map(new Function() { // from class: com.oney.WebRTCModule.SerializeUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SerializeUtils.lambda$serializeSdpParameters$4(map, (String) obj);
            }
        }).collect(Collectors.joining(";"));
    }

    static /* synthetic */ String lambda$serializeSdpParameters$4(Map map, String str) {
        return str + "=" + ((String) map.get(str));
    }

    public static RtpParameters updateRtpParameters(ReadableMap readableMap, RtpParameters rtpParameters) {
        ReadableArray array = readableMap.getArray("encodings");
        List<RtpParameters.Encoding> list = rtpParameters.encodings;
        if (array.size() != list.size()) {
            return null;
        }
        for (int i = 0; i < array.size(); i++) {
            ReadableMap map = array.getMap(i);
            RtpParameters.Encoding encoding = list.get(i);
            Integer numValueOf = map.hasKey("maxBitrate") ? Integer.valueOf(map.getInt("maxBitrate")) : null;
            Integer numValueOf2 = map.hasKey("maxFramerate") ? Integer.valueOf(map.getInt("maxFramerate")) : null;
            Double dValueOf = map.hasKey("scaleResolutionDownBy") ? Double.valueOf(map.getDouble("scaleResolutionDownBy")) : null;
            encoding.active = map.getBoolean("active");
            encoding.rid = map.getString("rid");
            encoding.maxBitrateBps = numValueOf;
            encoding.maxFramerate = numValueOf2;
            encoding.scaleResolutionDownBy = dValueOf;
        }
        if (readableMap.hasKey("degradationPreference")) {
            rtpParameters.degradationPreference = RtpParameters.DegradationPreference.valueOf(readableMap.getString("degradationPreference"));
        }
        return rtpParameters;
    }

    public static MediaStreamTrack.MediaType parseMediaType(String str) {
        str.hashCode();
        if (str.equals(MediaStreamTrack.AUDIO_TRACK_KIND)) {
            return MediaStreamTrack.MediaType.MEDIA_TYPE_AUDIO;
        }
        if (str.equals("video")) {
            return MediaStreamTrack.MediaType.MEDIA_TYPE_VIDEO;
        }
        throw new Error("Unknown media type");
    }

    public static RtpTransceiver.RtpTransceiverDirection parseDirection(String str) {
        str.hashCode();
        switch (str) {
            case "recvonly":
                return RtpTransceiver.RtpTransceiverDirection.RECV_ONLY;
            case "inactive":
                return RtpTransceiver.RtpTransceiverDirection.INACTIVE;
            case "sendonly":
                return RtpTransceiver.RtpTransceiverDirection.SEND_ONLY;
            case "sendrecv":
                return RtpTransceiver.RtpTransceiverDirection.SEND_RECV;
            default:
                throw new Error("Invalid direction");
        }
    }

    private static RtpParameters.Encoding parseEncoding(ReadableMap readableMap) {
        RtpParameters.Encoding encoding = new RtpParameters.Encoding(readableMap.getString("rid"), true, Double.valueOf(1.0d));
        if (readableMap.hasKey("active")) {
            encoding.active = readableMap.getBoolean("active");
        }
        if (readableMap.hasKey("maxBitrate")) {
            encoding.maxBitrateBps = Integer.valueOf(readableMap.getInt("maxBitrate"));
        }
        if (readableMap.hasKey("maxFramerate")) {
            encoding.maxFramerate = Integer.valueOf(readableMap.getInt("maxFramerate"));
        }
        if (readableMap.hasKey("scaleResolutionDownBy")) {
            encoding.scaleResolutionDownBy = Double.valueOf(readableMap.getDouble("scaleResolutionDownBy"));
        }
        return encoding;
    }

    public static RtpTransceiver.RtpTransceiverInit parseTransceiverOptions(ReadableMap readableMap) {
        ReadableArray array;
        ReadableArray array2;
        String string;
        if (readableMap == null) {
            return null;
        }
        RtpTransceiver.RtpTransceiverDirection direction = RtpTransceiver.RtpTransceiverDirection.SEND_RECV;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (readableMap.hasKey("direction") && (string = readableMap.getString("direction")) != null) {
            direction = parseDirection(string);
        }
        if (readableMap.hasKey("streamIds") && (array2 = readableMap.getArray("streamIds")) != null) {
            for (int i = 0; i < array2.size(); i++) {
                arrayList.add(array2.getString(i));
            }
        }
        if (readableMap.hasKey("sendEncodings") && (array = readableMap.getArray("sendEncodings")) != null) {
            for (int i2 = 0; i2 < array.size(); i2++) {
                arrayList2.add(parseEncoding(array.getMap(i2)));
            }
        }
        return new RtpTransceiver.RtpTransceiverInit(direction, arrayList, arrayList2);
    }
}
