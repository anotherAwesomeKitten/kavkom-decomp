package org.webrtc;

import java.util.ArrayList;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class Camera1Helper {
    public static int getCameraId(String str) {
        return Camera1Enumerator.getCameraIndex(str);
    }

    public static List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int i) {
        return Camera1Enumerator.getSupportedFormats(i);
    }

    public static Size findClosestCaptureFormat(int i, int i2, int i3) {
        List<CameraEnumerationAndroid.CaptureFormat> supportedFormats = getSupportedFormats(i);
        ArrayList arrayList = new ArrayList();
        if (supportedFormats != null) {
            for (CameraEnumerationAndroid.CaptureFormat captureFormat : supportedFormats) {
                arrayList.add(new Size(captureFormat.width, captureFormat.height));
            }
        }
        return CameraEnumerationAndroid.getClosestSupportedSize(arrayList, i2, i3);
    }
}
