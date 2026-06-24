package com.oney.WebRTCModule.webrtcutils;

import org.webrtc.SoftwareVideoEncoderFactory;
import org.webrtc.VideoCodecInfo;
import org.webrtc.VideoEncoder;
import org.webrtc.VideoEncoderFactory;

/* JADX INFO: loaded from: classes2.dex */
public class SoftwareVideoEncoderFactoryProxy implements VideoEncoderFactory {
    private VideoEncoderFactory factory;

    private synchronized VideoEncoderFactory getFactory() {
        if (this.factory == null) {
            this.factory = new SoftwareVideoEncoderFactory();
        }
        return this.factory;
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        return getFactory().createEncoder(videoCodecInfo);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return getFactory().getSupportedCodecs();
    }
}
