package com.oney.WebRTCModule.webrtcutils;

import java.util.ArrayList;
import java.util.Arrays;
import org.webrtc.EglBase;
import org.webrtc.HardwareVideoEncoderFactory;
import org.webrtc.VideoCodecInfo;
import org.webrtc.VideoEncoder;
import org.webrtc.VideoEncoderFactory;

/* JADX INFO: loaded from: classes2.dex */
public class H264AndSoftwareVideoEncoderFactory implements VideoEncoderFactory {
    private final VideoEncoderFactory hardwareVideoEncoderFactory;
    private final VideoEncoderFactory softwareVideoEncoderFactory = new SoftwareVideoEncoderFactoryProxy();

    public H264AndSoftwareVideoEncoderFactory(EglBase.Context context) {
        this.hardwareVideoEncoderFactory = new HardwareVideoEncoderFactory(context, false, true);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        if (videoCodecInfo.name.equalsIgnoreCase("H264")) {
            return this.hardwareVideoEncoderFactory.createEncoder(videoCodecInfo);
        }
        return this.softwareVideoEncoderFactory.createEncoder(videoCodecInfo);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        String str;
        ArrayList arrayList = new ArrayList();
        VideoCodecInfo videoCodecInfo = null;
        VideoCodecInfo videoCodecInfo2 = null;
        for (VideoCodecInfo videoCodecInfo3 : this.hardwareVideoEncoderFactory.getSupportedCodecs()) {
            if (videoCodecInfo3.name.equalsIgnoreCase("H264") && (str = videoCodecInfo3.params.get("profile-level-id")) != null) {
                if (str.equalsIgnoreCase("640c1f")) {
                    videoCodecInfo = videoCodecInfo3;
                } else if (str.equalsIgnoreCase("42e01f")) {
                    videoCodecInfo2 = videoCodecInfo3;
                }
            }
        }
        if (videoCodecInfo != null) {
            arrayList.add(videoCodecInfo);
        }
        if (videoCodecInfo2 != null) {
            arrayList.add(videoCodecInfo2);
        }
        arrayList.addAll(Arrays.asList(this.softwareVideoEncoderFactory.getSupportedCodecs()));
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }
}
