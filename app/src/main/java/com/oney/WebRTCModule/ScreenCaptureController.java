package com.oney.WebRTCModule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.OrientationEventListener;
import org.webrtc.ScreenCapturerAndroid;
import org.webrtc.VideoCapturer;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenCaptureController extends AbstractVideoCaptureController {
    private static final int DEFAULT_FPS = 30;
    private static final String TAG = "ScreenCaptureController";
    private final Context context;
    private final Intent mediaProjectionPermissionResultData;
    private final OrientationEventListener orientationListener;

    public ScreenCaptureController(Context context, int i, int i2, Intent intent) {
        super(i, i2, 30);
        this.mediaProjectionPermissionResultData = intent;
        this.context = context;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(context, context);
        this.orientationListener = anonymousClass1;
        if (anonymousClass1.canDetectOrientation()) {
            anonymousClass1.enable();
        }
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.ScreenCaptureController$1, reason: invalid class name */
    class AnonymousClass1 extends OrientationEventListener {
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Context context2) {
            super(context);
            this.val$context = context2;
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i) {
            DisplayMetrics displayMetrics = DisplayUtils.getDisplayMetrics((Activity) this.val$context);
            final int i2 = displayMetrics.widthPixels;
            final int i3 = displayMetrics.heightPixels;
            ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.ScreenCaptureController$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onOrientationChanged$0(i2, i3);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onOrientationChanged$0(int i, int i2) {
            try {
                ScreenCaptureController.this.videoCapturer.changeCaptureFormat(i, i2, 30);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController
    public String getDeviceId() {
        return "screen-capture";
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController
    public void dispose() {
        MediaProjectionService.abort(this.context);
        super.dispose();
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController
    protected VideoCapturer createVideoCapturer() {
        return new ScreenCapturerAndroid(this.mediaProjectionPermissionResultData, new MediaProjection.Callback() { // from class: com.oney.WebRTCModule.ScreenCaptureController.2
            @Override // android.media.projection.MediaProjection.Callback
            public void onStop() {
                Log.w(ScreenCaptureController.TAG, "Media projection stopped.");
                ScreenCaptureController.this.orientationListener.disable();
                ScreenCaptureController.this.stopCapture();
                if (ScreenCaptureController.this.capturerEventsListener != null) {
                    ScreenCaptureController.this.capturerEventsListener.onCapturerEnded();
                }
            }
        });
    }
}
