package com.oney.WebRTCModule;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import androidx.core.util.Consumer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Objects;
import org.webrtc.Camera1Capturer;
import org.webrtc.Camera1Helper;
import org.webrtc.Camera2Capturer;
import org.webrtc.Camera2Helper;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.Size;
import org.webrtc.VideoCapturer;

/* JADX INFO: loaded from: classes2.dex */
public class CameraCaptureController extends AbstractVideoCaptureController {
    private static final String TAG = "CameraCaptureController";
    private final CameraEnumerator cameraEnumerator;
    private final CameraEventsHandler cameraEventsHandler;
    private ReadableMap constraints;
    private final Context context;
    private String currentDeviceId;
    private boolean isFrontFacing;

    public CameraCaptureController(Context context, CameraEnumerator cameraEnumerator, ReadableMap readableMap) {
        super(readableMap.getInt("width"), readableMap.getInt("height"), readableMap.getInt(RRWebVideoEvent.JsonKeys.FRAME_RATE));
        this.cameraEventsHandler = new CameraEventsHandler() { // from class: com.oney.WebRTCModule.CameraCaptureController.1
            @Override // com.oney.WebRTCModule.CameraEventsHandler, org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpening(String str) {
                super.onCameraOpening(str);
                int iFindCameraIndex = CameraCaptureController.this.findCameraIndex(str);
                CameraCaptureController cameraCaptureController = CameraCaptureController.this;
                cameraCaptureController.updateActualSize(iFindCameraIndex, str, cameraCaptureController.videoCapturer);
                CameraCaptureController.this.currentDeviceId = iFindCameraIndex == -1 ? null : String.valueOf(iFindCameraIndex);
            }
        };
        this.context = context;
        this.cameraEnumerator = cameraEnumerator;
        this.constraints = readableMap;
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController
    public String getDeviceId() {
        return this.currentDeviceId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int findCameraIndex(String str) {
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        for (int i = 0; i < deviceNames.length; i++) {
            if (Objects.equals(deviceNames[i], str)) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController
    public WritableMap getSettings() {
        WritableMap settings = super.getSettings();
        settings.putString("facingMode", this.isFrontFacing ? "user" : "environment");
        return settings;
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController
    public void applyConstraints(final ReadableMap readableMap, final Consumer<Exception> consumer) {
        int i;
        final int i2;
        boolean z;
        final int i3 = this.targetWidth;
        final int i4 = this.targetHeight;
        final int i5 = this.targetFps;
        final Runnable runnable = new Runnable() { // from class: com.oney.WebRTCModule.CameraCaptureController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$applyConstraints$0(readableMap);
            }
        };
        final String str = null;
        if (this.videoCapturer == null) {
            runnable.run();
            if (consumer != null) {
                consumer.accept(null);
                return;
            }
            return;
        }
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        String mapStrValue = ReactBridgeUtil.getMapStrValue(readableMap, "deviceId");
        String mapStrValue2 = ReactBridgeUtil.getMapStrValue(readableMap, "facingMode");
        int i6 = -1;
        if (mapStrValue != null) {
            try {
                i = Integer.parseInt(mapStrValue);
            } catch (Exception unused) {
                i = -1;
            }
            try {
                str = deviceNames[i];
            } catch (Exception unused2) {
                Log.d(TAG, "failed to find device with id: " + mapStrValue);
            }
        } else {
            i = -1;
        }
        if (str == null) {
            boolean z2 = mapStrValue2 == null || mapStrValue2.equals("user");
            int length = deviceNames.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    i2 = i6;
                    break;
                }
                String str2 = deviceNames[i7];
                i6++;
                if (this.cameraEnumerator.isFrontFacing(str2) == z2) {
                    i2 = i6;
                    str = str2;
                    break;
                }
                i7++;
            }
        } else {
            i2 = i;
        }
        if (str == null) {
            if (consumer != null) {
                consumer.accept(new Exception("OverconstrainedError: could not find camera with deviceId: " + mapStrValue + " or facingMode: " + mapStrValue2));
                return;
            }
            return;
        }
        try {
            z = i2 != Integer.parseInt(this.currentDeviceId);
        } catch (Exception unused3) {
            Log.d(TAG, "Forcing camera switch, couldn't parse current device id: " + this.currentDeviceId);
            z = true;
        }
        final CameraVideoCapturer cameraVideoCapturer = (CameraVideoCapturer) this.videoCapturer;
        final Runnable runnable2 = new Runnable() { // from class: com.oney.WebRTCModule.CameraCaptureController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$applyConstraints$1(runnable, i3, i4, i5, i2, str, cameraVideoCapturer, consumer);
            }
        };
        if (z) {
            cameraVideoCapturer.switchCamera(new CameraVideoCapturer.CameraSwitchHandler() { // from class: com.oney.WebRTCModule.CameraCaptureController.2
                @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
                public void onCameraSwitchDone(boolean z3) {
                    CameraCaptureController.this.isFrontFacing = z3;
                    runnable2.run();
                }

                @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
                public void onCameraSwitchError(String str3) {
                    Exception exc = new Exception("Error switching camera: " + str3);
                    Log.e(CameraCaptureController.TAG, "OnCameraSwitchError", exc);
                    Consumer consumer2 = consumer;
                    if (consumer2 != null) {
                        consumer2.accept(exc);
                    }
                }
            }, str);
        } else {
            runnable2.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyConstraints$0(ReadableMap readableMap) {
        this.constraints = readableMap;
        this.targetWidth = readableMap.getInt("width");
        this.targetHeight = readableMap.getInt("height");
        this.targetFps = readableMap.getInt(RRWebVideoEvent.JsonKeys.FRAME_RATE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyConstraints$1(Runnable runnable, int i, int i2, int i3, int i4, String str, CameraVideoCapturer cameraVideoCapturer, Consumer consumer) {
        runnable.run();
        if (this.targetWidth != i || this.targetHeight != i2 || this.targetFps != i3) {
            updateActualSize(i4, str, this.videoCapturer);
            cameraVideoCapturer.changeCaptureFormat(this.targetWidth, this.targetHeight, this.targetFps);
        }
        if (consumer != null) {
            consumer.accept(null);
        }
    }

    @Override // com.oney.WebRTCModule.AbstractVideoCaptureController
    protected VideoCapturer createVideoCapturer() {
        CreateCapturerResult createCapturerResultCreateVideoCapturer = createVideoCapturer(ReactBridgeUtil.getMapStrValue(this.constraints, "deviceId"), ReactBridgeUtil.getMapStrValue(this.constraints, "facingMode"));
        if (createCapturerResultCreateVideoCapturer == null) {
            return null;
        }
        updateActualSize(createCapturerResultCreateVideoCapturer.cameraIndex, createCapturerResultCreateVideoCapturer.cameraName, createCapturerResultCreateVideoCapturer.videoCapturer);
        return createCapturerResultCreateVideoCapturer.videoCapturer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateActualSize(int i, String str, VideoCapturer videoCapturer) {
        Size sizeFindClosestCaptureFormat;
        if (videoCapturer instanceof Camera1Capturer) {
            sizeFindClosestCaptureFormat = Camera1Helper.findClosestCaptureFormat(i, this.targetWidth, this.targetHeight);
        } else {
            sizeFindClosestCaptureFormat = videoCapturer instanceof Camera2Capturer ? Camera2Helper.findClosestCaptureFormat((CameraManager) this.context.getSystemService("camera"), str, this.targetWidth, this.targetHeight) : null;
        }
        if (sizeFindClosestCaptureFormat != null) {
            this.actualWidth = sizeFindClosestCaptureFormat.width;
            this.actualHeight = sizeFindClosestCaptureFormat.height;
        }
    }

    private CreateCapturerResult createVideoCapturer(String str, String str2) {
        int i;
        String str3;
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        ArrayList arrayList = new ArrayList();
        try {
            i = Integer.parseInt(str);
        } catch (Exception unused) {
            i = -1;
        }
        try {
            str3 = deviceNames[i];
        } catch (Exception unused2) {
            Log.d(TAG, "failed to find device with id: " + str);
            str3 = null;
        }
        if (str3 != null) {
            CameraVideoCapturer cameraVideoCapturerCreateCapturer = this.cameraEnumerator.createCapturer(str3, this.cameraEventsHandler);
            String str4 = "Create user-specified camera " + str3;
            if (cameraVideoCapturerCreateCapturer != null) {
                Log.d(TAG, str4 + " succeeded");
                this.isFrontFacing = this.cameraEnumerator.isFrontFacing(str3);
                this.currentDeviceId = String.valueOf(i);
                return new CreateCapturerResult(i, str3, cameraVideoCapturerCreateCapturer);
            }
            Log.d(TAG, str4 + " failed");
            arrayList.add(str3);
        }
        boolean z = str2 == null || str2.equals("user");
        int i2 = -1;
        for (String str5 : deviceNames) {
            i2++;
            if (!arrayList.contains(str5) && this.cameraEnumerator.isFrontFacing(str5) == z) {
                CameraVideoCapturer cameraVideoCapturerCreateCapturer2 = this.cameraEnumerator.createCapturer(str5, this.cameraEventsHandler);
                String str6 = "Create camera " + str5;
                if (cameraVideoCapturerCreateCapturer2 != null) {
                    Log.d(TAG, str6 + " succeeded");
                    this.isFrontFacing = this.cameraEnumerator.isFrontFacing(str5);
                    this.currentDeviceId = String.valueOf(i2);
                    return new CreateCapturerResult(i2, str5, cameraVideoCapturerCreateCapturer2);
                }
                Log.d(TAG, str6 + " failed");
                arrayList.add(str5);
            }
        }
        int i3 = -1;
        for (String str7 : deviceNames) {
            i3++;
            if (!arrayList.contains(str7)) {
                CameraVideoCapturer cameraVideoCapturerCreateCapturer3 = this.cameraEnumerator.createCapturer(str7, this.cameraEventsHandler);
                String str8 = "Create fallback camera " + str7;
                if (cameraVideoCapturerCreateCapturer3 != null) {
                    Log.d(TAG, str8 + " succeeded");
                    this.isFrontFacing = this.cameraEnumerator.isFrontFacing(str7);
                    this.currentDeviceId = String.valueOf(i3);
                    return new CreateCapturerResult(i3, str7, cameraVideoCapturerCreateCapturer3);
                }
                Log.d(TAG, str8 + " failed");
                arrayList.add(str7);
            }
        }
        this.currentDeviceId = null;
        Log.w(TAG, "Unable to identify a suitable camera.");
        return null;
    }

    private static class CreateCapturerResult {
        public final int cameraIndex;
        public final String cameraName;
        public final VideoCapturer videoCapturer;

        public CreateCapturerResult(int i, String str, VideoCapturer videoCapturer) {
            this.cameraIndex = i;
            this.cameraName = str;
            this.videoCapturer = videoCapturer;
        }
    }
}
