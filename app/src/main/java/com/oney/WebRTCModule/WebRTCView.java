package com.oney.WebRTCModule;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;
import java.util.Objects;
import org.webrtc.EglBase;
import org.webrtc.Logging;
import org.webrtc.MediaStream;
import org.webrtc.RendererCommon;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes2.dex */
public class WebRTCView extends ViewGroup {
    private static final RendererCommon.ScalingType DEFAULT_SCALING_TYPE = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
    private static final String TAG = WebRTCModule.TAG;
    private static int surfaceViewRendererInstances;
    private int frameHeight;
    private int frameRotation;
    private int frameWidth;
    private final Object layoutSyncRoot;
    private boolean mirror;
    private boolean onDimensionsChangeEnabled;
    private boolean rendererAttached;
    private final RendererCommon.RendererEvents rendererEvents;
    private final Runnable requestSurfaceViewRendererLayoutRunnable;
    private RendererCommon.ScalingType scalingType;
    private String streamURL;
    private final SurfaceViewRenderer surfaceViewRenderer;
    private VideoTrack videoTrack;

    public WebRTCView(Context context) {
        super(context);
        this.layoutSyncRoot = new Object();
        this.rendererEvents = new RendererCommon.RendererEvents() { // from class: com.oney.WebRTCModule.WebRTCView.1
            @Override // org.webrtc.RendererCommon.RendererEvents
            public void onFirstFrameRendered() {
                WebRTCView.this.onFirstFrameRendered();
            }

            @Override // org.webrtc.RendererCommon.RendererEvents
            public void onFrameResolutionChanged(int i, int i2, int i3) {
                WebRTCView.this.onFrameResolutionChanged(i, i2, i3);
            }
        };
        this.requestSurfaceViewRendererLayoutRunnable = new Runnable() { // from class: com.oney.WebRTCModule.WebRTCView.2
            @Override // java.lang.Runnable
            public void run() {
                WebRTCView.this.requestSurfaceViewRendererLayout();
            }
        };
        this.onDimensionsChangeEnabled = false;
        SurfaceViewRenderer surfaceViewRenderer = new SurfaceViewRenderer(context);
        this.surfaceViewRenderer = surfaceViewRenderer;
        addView(surfaceViewRenderer);
        setMirror(false);
        setScalingType(DEFAULT_SCALING_TYPE);
    }

    private void cleanSurfaceViewRenderer() {
        this.surfaceViewRenderer.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.surfaceViewRenderer.clearImage();
    }

    private VideoTrack getVideoTrackForStreamURL(String str) {
        VideoTrack videoTrack = null;
        if (str != null) {
            MediaStream streamForReactTag = ((WebRTCModule) ((ReactContext) getContext()).getNativeModule(WebRTCModule.class)).getStreamForReactTag(str);
            if (streamForReactTag != null) {
                List<VideoTrack> list = streamForReactTag.videoTracks;
                if (!list.isEmpty()) {
                    videoTrack = list.get(0);
                }
            }
            if (videoTrack == null) {
                Log.w(TAG, "No video stream for react tag: " + str);
            }
        }
        return videoTrack;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        try {
            tryAddRendererToVideoTrack();
        } finally {
            super.onAttachedToWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        try {
            removeRendererFromVideoTrack();
        } finally {
            super.onDetachedFromWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFirstFrameRendered() {
        post(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFirstFrameRendered$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onFirstFrameRendered$0() {
        Log.d(TAG, "First frame rendered.");
        this.surfaceViewRenderer.setBackgroundColor(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFrameResolutionChanged(final int i, final int i2, int i3) {
        boolean z;
        boolean z2;
        synchronized (this.layoutSyncRoot) {
            z = true;
            if (this.frameHeight != i2) {
                this.frameHeight = i2;
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.frameRotation != i3) {
                this.frameRotation = i3;
                z2 = true;
            }
            if (this.frameWidth != i) {
                this.frameWidth = i;
            } else {
                z = z2;
            }
        }
        if (z) {
            post(this.requestSurfaceViewRendererLayoutRunnable);
            if (this.onDimensionsChangeEnabled) {
                post(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCView$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onFrameResolutionChanged$1(i, i2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onFrameResolutionChanged$1(int i, int i2) {
        try {
            ReactContext reactContext = (ReactContext) getContext();
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("width", i);
            writableMapCreateMap.putInt("height", i2);
            ((RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onDimensionsChange", writableMapCreateMap);
        } catch (Exception e) {
            Log.e(TAG, "Error calling onDimensionsChange callback", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004a  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            int r9 = r9 - r7
            int r8 = r8 - r6
            r5 = 0
            if (r9 == 0) goto L4a
            if (r8 != 0) goto L8
            goto L4a
        L8:
            java.lang.Object r6 = r4.layoutSyncRoot
            monitor-enter(r6)
            int r7 = r4.frameHeight     // Catch: java.lang.Throwable -> L47
            int r0 = r4.frameRotation     // Catch: java.lang.Throwable -> L47
            int r1 = r4.frameWidth     // Catch: java.lang.Throwable -> L47
            org.webrtc.RendererCommon$ScalingType r2 = r4.scalingType     // Catch: java.lang.Throwable -> L47
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L47
            int[] r6 = com.oney.WebRTCModule.WebRTCView.AnonymousClass3.$SwitchMap$org$webrtc$RendererCommon$ScalingType
            int r3 = r2.ordinal()
            r6 = r6[r3]
            r3 = 1
            if (r6 == r3) goto L45
            if (r7 == 0) goto L4a
            if (r1 != 0) goto L24
            goto L4a
        L24:
            int r0 = r0 % 180
            if (r0 != 0) goto L2b
            float r5 = (float) r1
            float r6 = (float) r7
            goto L2d
        L2b:
            float r5 = (float) r7
            float r6 = (float) r1
        L2d:
            float r5 = r5 / r6
            android.graphics.Point r5 = org.webrtc.RendererCommon.getDisplaySize(r2, r5, r8, r9)
            int r6 = r5.x
            int r8 = r8 - r6
            int r6 = r8 / 2
            int r7 = r5.y
            int r9 = r9 - r7
            int r7 = r9 / 2
            int r8 = r5.x
            int r8 = r8 + r6
            int r5 = r5.y
            int r9 = r7 + r5
            r5 = r6
            goto L4d
        L45:
            r7 = r5
            goto L4d
        L47:
            r5 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L47
            throw r5
        L4a:
            r7 = r5
            r8 = r7
            r9 = r8
        L4d:
            org.webrtc.SurfaceViewRenderer r6 = r4.surfaceViewRenderer
            r6.layout(r5, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.WebRTCView.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.WebRTCView$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$RendererCommon$ScalingType;

        static {
            int[] iArr = new int[RendererCommon.ScalingType.values().length];
            $SwitchMap$org$webrtc$RendererCommon$ScalingType = iArr;
            try {
                iArr[RendererCommon.ScalingType.SCALE_ASPECT_FILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$RendererCommon$ScalingType[RendererCommon.ScalingType.SCALE_ASPECT_FIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void removeRendererFromVideoTrack() {
        if (this.rendererAttached) {
            if (this.videoTrack != null) {
                ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCView$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$removeRendererFromVideoTrack$2();
                    }
                });
            }
            this.surfaceViewRenderer.release();
            surfaceViewRendererInstances--;
            this.rendererAttached = false;
            synchronized (this.layoutSyncRoot) {
                this.frameHeight = 0;
                this.frameRotation = 0;
                this.frameWidth = 0;
            }
            requestSurfaceViewRendererLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeRendererFromVideoTrack$2() {
        try {
            this.videoTrack.removeSink(this.surfaceViewRenderer);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSurfaceViewRendererLayout() {
        this.surfaceViewRenderer.requestLayout();
        if (ViewCompat.isInLayout(this)) {
            return;
        }
        onLayout(false, getLeft(), getTop(), getRight(), getBottom());
    }

    public void setMirror(boolean z) {
        if (this.mirror != z) {
            this.mirror = z;
            this.surfaceViewRenderer.setMirror(z);
            requestSurfaceViewRendererLayout();
        }
    }

    public void setObjectFit(String str) {
        setScalingType("cover".equals(str) ? RendererCommon.ScalingType.SCALE_ASPECT_FILL : RendererCommon.ScalingType.SCALE_ASPECT_FIT);
    }

    private void setScalingType(RendererCommon.ScalingType scalingType) {
        synchronized (this.layoutSyncRoot) {
            if (this.scalingType == scalingType) {
                return;
            }
            this.scalingType = scalingType;
            this.surfaceViewRenderer.setScalingType(scalingType);
            requestSurfaceViewRendererLayout();
        }
    }

    void setStreamURL(String str) {
        if (Objects.equals(str, this.streamURL)) {
            return;
        }
        VideoTrack videoTrackForStreamURL = getVideoTrackForStreamURL(str);
        if (this.videoTrack != videoTrackForStreamURL) {
            setVideoTrack(null);
        }
        this.streamURL = str;
        setVideoTrack(videoTrackForStreamURL);
    }

    private void setVideoTrack(VideoTrack videoTrack) {
        VideoTrack videoTrack2 = this.videoTrack;
        if (videoTrack2 != videoTrack) {
            if (videoTrack2 != null) {
                if (videoTrack == null) {
                    cleanSurfaceViewRenderer();
                }
                removeRendererFromVideoTrack();
            }
            this.videoTrack = videoTrack;
            if (videoTrack != null) {
                tryAddRendererToVideoTrack();
                if (videoTrack2 == null) {
                    cleanSurfaceViewRenderer();
                }
            }
        }
    }

    public void setZOrder(int i) {
        if (i == 0) {
            this.surfaceViewRenderer.setZOrderMediaOverlay(false);
        } else if (i == 1) {
            this.surfaceViewRenderer.setZOrderMediaOverlay(true);
        } else {
            if (i != 2) {
                return;
            }
            this.surfaceViewRenderer.setZOrderOnTop(true);
        }
    }

    private void tryAddRendererToVideoTrack() {
        if (this.rendererAttached || this.videoTrack == null || !ViewCompat.isAttachedToWindow(this)) {
            return;
        }
        EglBase.Context rootEglBaseContext = EglUtils.getRootEglBaseContext();
        if (rootEglBaseContext == null) {
            Log.e(TAG, "Failed to render a VideoTrack!");
            return;
        }
        try {
            this.surfaceViewRenderer.init(rootEglBaseContext, this.rendererEvents);
            surfaceViewRendererInstances++;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.WebRTCView$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$tryAddRendererToVideoTrack$3();
                }
            });
            this.rendererAttached = true;
        } catch (Exception e) {
            Logging.e(TAG, "Failed to initialize surfaceViewRenderer on instance " + surfaceViewRendererInstances, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$tryAddRendererToVideoTrack$3() {
        try {
            this.videoTrack.addSink(this.surfaceViewRenderer);
        } catch (Throwable th) {
            Log.e(TAG, "Failed to add renderer", th);
        }
    }

    public void setOnDimensionsChange(boolean z) {
        this.onDimensionsChangeEnabled = z;
    }
}
