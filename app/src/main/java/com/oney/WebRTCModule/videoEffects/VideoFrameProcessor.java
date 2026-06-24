package com.oney.WebRTCModule.videoEffects;

import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoFrame;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoFrameProcessor {
    VideoFrame process(VideoFrame videoFrame, SurfaceTextureHelper surfaceTextureHelper);
}
