package org.webrtc;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoEncoderFactory {

    public interface VideoEncoderSelector {
        VideoCodecInfo onAvailableBitrate(int i);

        void onCurrentEncoder(VideoCodecInfo videoCodecInfo);

        VideoCodecInfo onEncoderBroken();

        default VideoCodecInfo onResolutionChange(int i, int i2) {
            return null;
        }
    }

    VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo);

    default VideoEncoderSelector getEncoderSelector() {
        return null;
    }

    VideoCodecInfo[] getSupportedCodecs();

    default VideoCodecInfo[] getImplementations() {
        return getSupportedCodecs();
    }
}
