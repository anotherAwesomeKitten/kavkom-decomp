package com.oney.WebRTCModule.webrtcutils;

import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.VideoCodecInfo;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoDecoderFactory;

/* JADX INFO: loaded from: classes2.dex */
public class SoftwareVideoDecoderFactoryProxy implements VideoDecoderFactory {
    private VideoDecoderFactory factory;

    private synchronized VideoDecoderFactory getFactory() {
        if (this.factory == null) {
            this.factory = new SoftwareVideoDecoderFactory();
        }
        return this.factory;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        return getFactory().createDecoder(videoCodecInfo);
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return getFactory().getSupportedCodecs();
    }
}
