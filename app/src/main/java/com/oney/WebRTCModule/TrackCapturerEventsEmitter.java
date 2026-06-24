package com.oney.WebRTCModule;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.oney.WebRTCModule.AbstractVideoCaptureController;

/* JADX INFO: loaded from: classes2.dex */
public class TrackCapturerEventsEmitter implements AbstractVideoCaptureController.CapturerEventsListener {
    private static final String TAG = "com.oney.WebRTCModule.TrackCapturerEventsEmitter";
    private final String trackId;
    private final WebRTCModule webRTCModule;

    public TrackCapturerEventsEmitter(WebRTCModule webRTCModule, String str) {
        this.webRTCModule = webRTCModule;
        this.trackId = str;
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController.CapturerEventsListener
    public void onCapturerEnded() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("trackId", this.trackId);
        Log.d(TAG, "ended event trackId: " + this.trackId);
        this.webRTCModule.sendEvent("mediaStreamTrackEnded", writableMapCreateMap);
    }
}
