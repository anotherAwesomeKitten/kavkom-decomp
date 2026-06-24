package com.mkuczera;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.mkuczera.vibrateFactory.Vibrate;
import com.mkuczera.vibrateFactory.VibrateFactory;
import org.webrtc.MediaStreamTrack;

/* JADX INFO: loaded from: classes2.dex */
public class RNReactNativeHapticFeedbackModuleImpl {
    public static final String NAME = "RNHapticFeedback";

    public static boolean isVibrationEnabled(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        AudioManager audioManager = (AudioManager) context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        return (vibrator != null && vibrator.hasVibrator()) && ((audioManager.getRingerMode() != 0) || (audioManager.getRingerMode() == 1));
    }

    public static void trigger(ReactApplicationContext reactApplicationContext, String str, ReadableMap readableMap) {
        boolean z = readableMap.getBoolean("ignoreAndroidSystemSettings");
        boolean zIsVibrationEnabled = isVibrationEnabled(reactApplicationContext);
        if (z || zIsVibrationEnabled) {
            Vibrator vibrator = (Vibrator) reactApplicationContext.getSystemService("vibrator");
            Vibrate vibration = VibrateFactory.getVibration(str);
            if (vibrator == null || vibration == null) {
                return;
            }
            vibration.apply(vibrator);
        }
    }
}
