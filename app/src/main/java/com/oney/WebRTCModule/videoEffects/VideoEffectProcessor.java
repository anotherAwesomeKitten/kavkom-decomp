package com.oney.WebRTCModule.videoEffects;

import java.util.Iterator;
import java.util.List;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoFrame;
import org.webrtc.VideoProcessor;
import org.webrtc.VideoSink;

/* JADX INFO: loaded from: classes2.dex */
public class VideoEffectProcessor implements VideoProcessor {
    private VideoSink mSink;
    private final SurfaceTextureHelper textureHelper;
    private final List<VideoFrameProcessor> videoFrameProcessors;

    @Override // org.webrtc.CapturerObserver
    public void onCapturerStarted(boolean z) {
    }

    @Override // org.webrtc.CapturerObserver
    public void onCapturerStopped() {
    }

    public VideoEffectProcessor(List<VideoFrameProcessor> list, SurfaceTextureHelper surfaceTextureHelper) {
        this.textureHelper = surfaceTextureHelper;
        this.videoFrameProcessors = list;
    }

    @Override // org.webrtc.VideoProcessor
    public void setSink(VideoSink videoSink) {
        this.mSink = videoSink;
    }

    @Override // org.webrtc.CapturerObserver
    public void onFrameCaptured(VideoFrame videoFrame) {
        videoFrame.retain();
        Iterator<VideoFrameProcessor> it = this.videoFrameProcessors.iterator();
        VideoFrame videoFrameProcess = videoFrame;
        while (it.hasNext()) {
            videoFrameProcess = it.next().process(videoFrameProcess, this.textureHelper);
            if (videoFrameProcess == null) {
                this.mSink.onFrame(videoFrame);
                videoFrame.release();
                return;
            }
        }
        this.mSink.onFrame(videoFrameProcess);
        videoFrameProcess.release();
        videoFrame.release();
    }
}
