package com.oney.WebRTCModule;

import android.util.Log;
import org.webrtc.CameraVideoCapturer;

/* JADX INFO: loaded from: classes2.dex */
class CameraEventsHandler implements CameraVideoCapturer.CameraEventsHandler {
    private static final String TAG = WebRTCModule.TAG;

    CameraEventsHandler() {
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraClosed() {
        Log.d(TAG, "CameraEventsHandler.onCameraClosed");
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraDisconnected() {
        Log.d(TAG, "CameraEventsHandler.onCameraDisconnected");
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraError(String str) {
        Log.d(TAG, "CameraEventsHandler.onCameraError: errorDescription=" + str);
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraFreezed(String str) {
        Log.d(TAG, "CameraEventsHandler.onCameraFreezed: errorDescription=" + str);
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onCameraOpening(String str) {
        Log.d(TAG, "CameraEventsHandler.onCameraOpening: cameraName=" + str);
    }

    @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
    public void onFirstFrameAvailable() {
        Log.d(TAG, "CameraEventsHandler.onFirstFrameAvailable");
    }
}
