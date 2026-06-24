package com.oney.WebRTCModule;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes2.dex */
public class VideoTrackAdapter {
    static final long INITIAL_MUTE_DELAY = 3000;
    static final long MUTE_DELAY = 1500;
    static final String TAG = "com.oney.WebRTCModule.VideoTrackAdapter";
    private final int peerConnectionId;
    private final WebRTCModule webRTCModule;
    private Map<String, TrackMuteUnmuteImpl> muteImplMap = new HashMap();
    private Timer timer = new Timer("VideoTrackMutedTimer");

    public VideoTrackAdapter(WebRTCModule webRTCModule, int i) {
        this.peerConnectionId = i;
        this.webRTCModule = webRTCModule;
    }

    public void addAdapter(VideoTrack videoTrack) {
        String strId = videoTrack.id();
        if (this.muteImplMap.containsKey(strId)) {
            Log.w(TAG, "Attempted to add adapter twice for track ID: " + strId);
            return;
        }
        TrackMuteUnmuteImpl trackMuteUnmuteImpl = new TrackMuteUnmuteImpl(strId);
        Log.d(TAG, "Created adapter for " + strId);
        this.muteImplMap.put(strId, trackMuteUnmuteImpl);
        videoTrack.addSink(trackMuteUnmuteImpl);
        trackMuteUnmuteImpl.start();
    }

    public void removeAdapter(VideoTrack videoTrack) {
        String strId = videoTrack.id();
        TrackMuteUnmuteImpl trackMuteUnmuteImplRemove = this.muteImplMap.remove(strId);
        if (trackMuteUnmuteImplRemove == null) {
            Log.w(TAG, "removeAdapter - no adapter for " + strId);
            return;
        }
        videoTrack.removeSink(trackMuteUnmuteImplRemove);
        trackMuteUnmuteImplRemove.dispose();
        Log.d(TAG, "Deleted adapter for " + strId);
    }

    private class TrackMuteUnmuteImpl implements VideoSink {
        private volatile boolean disposed;
        private TimerTask emitMuteTask;
        private AtomicInteger frameCounter = new AtomicInteger();
        private boolean mutedState;
        private final String trackId;

        TrackMuteUnmuteImpl(String str) {
            this.trackId = str;
        }

        @Override // org.webrtc.VideoSink
        public void onFrame(VideoFrame videoFrame) {
            this.frameCounter.addAndGet(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void start() {
            if (this.disposed) {
                return;
            }
            synchronized (this) {
                TimerTask timerTask = this.emitMuteTask;
                if (timerTask != null) {
                    timerTask.cancel();
                }
                this.emitMuteTask = new TimerTask() { // from class: com.oney.WebRTCModule.VideoTrackAdapter.TrackMuteUnmuteImpl.1
                    private int lastFrameNumber;

                    {
                        this.lastFrameNumber = TrackMuteUnmuteImpl.this.frameCounter.get();
                    }

                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (TrackMuteUnmuteImpl.this.disposed) {
                            return;
                        }
                        boolean z = this.lastFrameNumber == TrackMuteUnmuteImpl.this.frameCounter.get();
                        if (z != TrackMuteUnmuteImpl.this.mutedState) {
                            TrackMuteUnmuteImpl.this.mutedState = z;
                            TrackMuteUnmuteImpl.this.emitMuteEvent(z);
                        }
                        this.lastFrameNumber = TrackMuteUnmuteImpl.this.frameCounter.get();
                    }
                };
                VideoTrackAdapter.this.timer.schedule(this.emitMuteTask, VideoTrackAdapter.INITIAL_MUTE_DELAY, VideoTrackAdapter.MUTE_DELAY);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void emitMuteEvent(boolean z) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("pcId", VideoTrackAdapter.this.peerConnectionId);
            writableMapCreateMap.putString("trackId", this.trackId);
            writableMapCreateMap.putBoolean("muted", z);
            Log.d(VideoTrackAdapter.TAG, (z ? "Mute" : "Unmute") + " event pcId: " + VideoTrackAdapter.this.peerConnectionId + " trackId: " + this.trackId);
            VideoTrackAdapter.this.webRTCModule.sendEvent("mediaStreamTrackMuteChanged", writableMapCreateMap);
        }

        void dispose() {
            this.disposed = true;
            synchronized (this) {
                TimerTask timerTask = this.emitMuteTask;
                if (timerTask != null) {
                    timerTask.cancel();
                    this.emitMuteTask = null;
                }
            }
        }
    }
}
