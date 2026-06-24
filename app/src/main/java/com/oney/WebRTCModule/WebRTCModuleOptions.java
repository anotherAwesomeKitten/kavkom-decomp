package com.oney.WebRTCModule;

import org.webrtc.Loggable;
import org.webrtc.Logging;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoEncoderFactory;
import org.webrtc.audio.AudioDeviceModule;

/* JADX INFO: loaded from: classes2.dex */
public class WebRTCModuleOptions {
    private static WebRTCModuleOptions instance;
    public AudioDeviceModule audioDeviceModule;
    public boolean enableMediaProjectionService;
    public String fieldTrials;
    public Loggable injectableLogger;
    public Logging.Severity loggingSeverity;
    public VideoDecoderFactory videoDecoderFactory;
    public VideoEncoderFactory videoEncoderFactory;

    public static WebRTCModuleOptions getInstance() {
        if (instance == null) {
            instance = new WebRTCModuleOptions();
        }
        return instance;
    }
}
