package com.oney.WebRTCModule;

import androidx.core.util.Consumer;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import io.sentry.rrweb.RRWebVideoEvent;
import org.webrtc.VideoCapturer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractVideoCaptureController {
    protected int actualFps;
    protected int actualHeight;
    protected int actualWidth;
    protected CapturerEventsListener capturerEventsListener;
    protected int targetFps;
    protected int targetHeight;
    protected int targetWidth;
    protected VideoCapturer videoCapturer;

    public interface CapturerEventsListener {
        void onCapturerEnded();
    }

    protected abstract VideoCapturer createVideoCapturer();

    public abstract String getDeviceId();

    public AbstractVideoCaptureController(int i, int i2, int i3) {
        this.targetWidth = i;
        this.targetHeight = i2;
        this.targetFps = i3;
        this.actualWidth = i;
        this.actualHeight = i2;
        this.actualFps = i3;
    }

    public void initializeVideoCapturer() {
        this.videoCapturer = createVideoCapturer();
    }

    public void dispose() {
        VideoCapturer videoCapturer = this.videoCapturer;
        if (videoCapturer != null) {
            videoCapturer.dispose();
            this.videoCapturer = null;
        }
    }

    public int getHeight() {
        return this.actualHeight;
    }

    public int getWidth() {
        return this.actualWidth;
    }

    public int getFrameRate() {
        return this.actualFps;
    }

    public WritableMap getSettings() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("deviceId", getDeviceId());
        writableMapCreateMap.putString("groupId", "");
        writableMapCreateMap.putInt("height", getHeight());
        writableMapCreateMap.putInt("width", getWidth());
        writableMapCreateMap.putInt(RRWebVideoEvent.JsonKeys.FRAME_RATE, getFrameRate());
        return writableMapCreateMap;
    }

    public VideoCapturer getVideoCapturer() {
        return this.videoCapturer;
    }

    public void startCapture() {
        try {
            this.videoCapturer.startCapture(this.targetWidth, this.targetHeight, this.targetFps);
        } catch (RuntimeException unused) {
        }
    }

    public boolean stopCapture() {
        try {
            this.videoCapturer.stopCapture();
            return true;
        } catch (InterruptedException unused) {
            return false;
        }
    }

    public void applyConstraints(ReadableMap readableMap, Consumer<Exception> consumer) {
        if (consumer != null) {
            consumer.accept(new UnsupportedOperationException("This video track does not support applyConstraints."));
        }
    }

    public void setCapturerEventsListener(CapturerEventsListener capturerEventsListener) {
        this.capturerEventsListener = capturerEventsListener;
    }
}
