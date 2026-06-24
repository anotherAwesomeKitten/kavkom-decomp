package com.kavkom.phone;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.telecom.ConnectionService;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import org.webrtc.MediaStreamTrack;

/* JADX INFO: loaded from: classes2.dex */
public class IncomingCallService extends ConnectionService {
    private static final String TAG = "[ReactNativeCallkeepHelper]";
    private static MediaPlayer currentMediaPlayer;
    private AudioFocusRequest audioFocusRequest;
    private AudioManager audioManager;
    public static final ReactApplicationContext reactContext = CallkeepHelperModule.reactContext;
    private static final Object mediaPlayerLock = new Object();
    private int audioFocusResult = 0;
    private PowerManager.WakeLock wakeLock = null;

    public static void stopRingtoneStatic() {
        MediaPlayer mediaPlayer;
        synchronized (mediaPlayerLock) {
            try {
                mediaPlayer = currentMediaPlayer;
            } catch (Exception e) {
                Log.e(TAG, "[IncomingCallService] Failed to stop MediaPlayer (static): " + e.getMessage(), e);
                currentMediaPlayer = null;
            }
            if (mediaPlayer != null) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        currentMediaPlayer.stop();
                    }
                } catch (Exception e2) {
                    Log.w(TAG, "[IncomingCallService] Error stopping MediaPlayer: " + e2.getMessage());
                }
                try {
                    currentMediaPlayer.release();
                } catch (Exception e3) {
                    Log.w(TAG, "[IncomingCallService] Error releasing MediaPlayer: " + e3.getMessage());
                }
                currentMediaPlayer = null;
                Log.d(TAG, "[IncomingCallService] Stopped MediaPlayer ringtone (static)");
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(36:112|114|(1:119)|(1:124)(1:123)|125|(1:131)(1:130)|(1:136)(1:135)|137|(1:142)(1:141)|143|(1:147)(1:146)|148|(29:150|151|216|152|153|218|154|(1:156)|157|168|(1:170)(1:171)|172|(1:174)(1:175)|176|(1:178)(1:179)|180|(1:184)(1:183)|221|(2:186|(1:188)(1:189))(1:190)|220|194|(1:196)|(1:198)(1:199)|200|(1:202)(1:203)|204|214|205|(2:211|212)(1:223))(1:166)|167|168|(0)(0)|172|(0)(0)|176|(0)(0)|180|(1:184)(0)|221|(0)(0)|220|194|(0)|(0)(0)|200|(0)(0)|204|214|205|(0)|211|212) */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0255, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0256, code lost:
    
        android.util.Log.e(r4, "[IncomingCallService] Error getting ringtone URI: " + r0.getMessage(), r0);
        r0 = android.media.RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x03b0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x03b1, code lost:
    
        android.util.Log.e(r4, "[IncomingCallService] Failed to acquire wake lock: " + r0.getMessage(), r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0217 A[Catch: Exception -> 0x0255, TRY_ENTER, TryCatch #3 {Exception -> 0x0255, blocks: (B:186:0x0217, B:188:0x0227, B:189:0x0245, B:190:0x024b), top: B:221:0x0215 }] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x024b A[Catch: Exception -> 0x0255, TRY_LEAVE, TryCatch #3 {Exception -> 0x0255, blocks: (B:186:0x0217, B:188:0x0227, B:189:0x0245, B:190:0x024b), top: B:221:0x0215 }] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0343  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int onStartCommand(android.content.Intent r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 1038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kavkom.phone.IncomingCallService.onStartCommand(android.content.Intent, int, int):int");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopRingtone();
        releaseAudioFocus();
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        try {
            this.wakeLock.release();
            Log.d(TAG, "[IncomingCallService] Wake lock released");
        } catch (Exception e) {
            Log.e(TAG, "[IncomingCallService] Error releasing wake lock: " + e.getMessage(), e);
        }
        this.wakeLock = null;
    }

    private void playRingtone(Uri uri) {
        synchronized (mediaPlayerLock) {
            try {
                stopRingtone();
                if (uri != null) {
                    MediaPlayer mediaPlayer = currentMediaPlayer;
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        return;
                    }
                    currentMediaPlayer = new MediaPlayer();
                    Context applicationContext = reactContext;
                    if (applicationContext == null) {
                        applicationContext = getApplicationContext();
                    }
                    currentMediaPlayer.setDataSource(applicationContext, uri);
                    currentMediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(6).setContentType(4).build());
                    currentMediaPlayer.setLooping(true);
                    currentMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.kavkom.phone.IncomingCallService.1
                        AnonymousClass1() {
                        }

                        @Override // android.media.MediaPlayer.OnErrorListener
                        public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                            Log.e(IncomingCallService.TAG, "[IncomingCallService] MediaPlayer error: what=" + i + ", extra=" + i2);
                            synchronized (IncomingCallService.mediaPlayerLock) {
                                if (IncomingCallService.currentMediaPlayer == mediaPlayer2) {
                                    try {
                                        mediaPlayer2.release();
                                    } catch (Exception e) {
                                        Log.e(IncomingCallService.TAG, "[IncomingCallService] Error releasing MediaPlayer: " + e.getMessage());
                                    }
                                    IncomingCallService.currentMediaPlayer = null;
                                }
                            }
                            return true;
                        }
                    });
                    currentMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.kavkom.phone.IncomingCallService.2
                        AnonymousClass2() {
                        }

                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer2) {
                            Log.w(IncomingCallService.TAG, "[IncomingCallService] MediaPlayer completed unexpectedly (should loop)");
                            synchronized (IncomingCallService.mediaPlayerLock) {
                                if (IncomingCallService.currentMediaPlayer == mediaPlayer2 && mediaPlayer2.isLooping()) {
                                    try {
                                        mediaPlayer2.seekTo(0);
                                        mediaPlayer2.start();
                                        Log.d(IncomingCallService.TAG, "[IncomingCallService] Restarted MediaPlayer after unexpected completion");
                                    } catch (Exception e) {
                                        Log.e(IncomingCallService.TAG, "[IncomingCallService] Failed to restart MediaPlayer: " + e.getMessage());
                                        try {
                                            mediaPlayer2.release();
                                        } catch (Exception e2) {
                                            Log.e(IncomingCallService.TAG, "[IncomingCallService] Error releasing MediaPlayer: " + e2.getMessage());
                                        }
                                        IncomingCallService.currentMediaPlayer = null;
                                    }
                                }
                            }
                        }
                    });
                    currentMediaPlayer.prepare();
                    currentMediaPlayer.start();
                    Log.d(TAG, "[IncomingCallService] MediaPlayer started successfully (looping), isPlaying: " + currentMediaPlayer.isPlaying());
                } else {
                    Log.e(TAG, "[IncomingCallService] ringtoneUri is null, cannot play ringtone");
                }
            } catch (Exception e) {
                Log.e(TAG, "[IncomingCallService] Failed to play ringtone: " + e.getMessage(), e);
                synchronized (mediaPlayerLock) {
                    MediaPlayer mediaPlayer2 = currentMediaPlayer;
                    if (mediaPlayer2 != null) {
                        try {
                            mediaPlayer2.release();
                        } catch (Exception e2) {
                            Log.e(TAG, "[IncomingCallService] Error releasing MediaPlayer on error: " + e2.getMessage());
                        }
                        currentMediaPlayer = null;
                    }
                    try {
                        synchronized (mediaPlayerLock) {
                            if (currentMediaPlayer == null && uri != null) {
                                Context applicationContext2 = reactContext;
                                if (applicationContext2 == null) {
                                    applicationContext2 = getApplicationContext();
                                }
                                Ringtone ringtone = RingtoneManager.getRingtone(applicationContext2, uri);
                                if (ringtone != null) {
                                    if (Build.VERSION.SDK_INT >= 28) {
                                        ringtone.setAudioAttributes(new AudioAttributes.Builder().setUsage(6).setContentType(4).build());
                                    }
                                    ringtone.play();
                                    Log.d(TAG, "[IncomingCallService] Using Ringtone fallback (no looping)");
                                }
                            }
                        }
                    } catch (Exception e3) {
                        Log.e(TAG, "[IncomingCallService] Fallback ringtone also failed: " + e3.getMessage());
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallService$1 */
    class AnonymousClass1 implements MediaPlayer.OnErrorListener {
        AnonymousClass1() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
            Log.e(IncomingCallService.TAG, "[IncomingCallService] MediaPlayer error: what=" + i + ", extra=" + i2);
            synchronized (IncomingCallService.mediaPlayerLock) {
                if (IncomingCallService.currentMediaPlayer == mediaPlayer2) {
                    try {
                        mediaPlayer2.release();
                    } catch (Exception e) {
                        Log.e(IncomingCallService.TAG, "[IncomingCallService] Error releasing MediaPlayer: " + e.getMessage());
                    }
                    IncomingCallService.currentMediaPlayer = null;
                }
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallService$2 */
    class AnonymousClass2 implements MediaPlayer.OnCompletionListener {
        AnonymousClass2() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer2) {
            Log.w(IncomingCallService.TAG, "[IncomingCallService] MediaPlayer completed unexpectedly (should loop)");
            synchronized (IncomingCallService.mediaPlayerLock) {
                if (IncomingCallService.currentMediaPlayer == mediaPlayer2 && mediaPlayer2.isLooping()) {
                    try {
                        mediaPlayer2.seekTo(0);
                        mediaPlayer2.start();
                        Log.d(IncomingCallService.TAG, "[IncomingCallService] Restarted MediaPlayer after unexpected completion");
                    } catch (Exception e) {
                        Log.e(IncomingCallService.TAG, "[IncomingCallService] Failed to restart MediaPlayer: " + e.getMessage());
                        try {
                            mediaPlayer2.release();
                        } catch (Exception e2) {
                            Log.e(IncomingCallService.TAG, "[IncomingCallService] Error releasing MediaPlayer: " + e2.getMessage());
                        }
                        IncomingCallService.currentMediaPlayer = null;
                    }
                }
            }
        }
    }

    private void stopRingtone() {
        MediaPlayer mediaPlayer;
        synchronized (mediaPlayerLock) {
            try {
                mediaPlayer = currentMediaPlayer;
            } catch (Exception e) {
                Log.e(TAG, "[IncomingCallService] Failed to stop MediaPlayer: " + e.getMessage(), e);
                currentMediaPlayer = null;
            }
            if (mediaPlayer != null) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        currentMediaPlayer.stop();
                    }
                } catch (Exception e2) {
                    Log.w(TAG, "[IncomingCallService] Error stopping MediaPlayer: " + e2.getMessage());
                }
                try {
                    currentMediaPlayer.release();
                } catch (Exception e3) {
                    Log.w(TAG, "[IncomingCallService] Error releasing MediaPlayer: " + e3.getMessage());
                }
                currentMediaPlayer = null;
                Log.d(TAG, "[IncomingCallService] Stopped MediaPlayer ringtone");
            }
        }
    }

    private boolean isAppInBackground() {
        try {
            ReactApplicationContext reactApplicationContext = reactContext;
            if (reactApplicationContext == null) {
                Log.d(TAG, "[IncomingCallService] ReactContext is null - app is in background");
                return true;
            }
            try {
                boolean zHasActiveCatalystInstance = reactApplicationContext.hasActiveCatalystInstance();
                if (!zHasActiveCatalystInstance) {
                    Log.d(TAG, "[IncomingCallService] No active Catalyst instance - app is in background");
                    return true;
                }
                ActivityManager activityManager = (ActivityManager) getSystemService("activity");
                if (activityManager != null) {
                    try {
                        String packageName = reactApplicationContext.getPackageName();
                        if (packageName != null) {
                            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                                if (runningAppProcessInfo.processName.equals(packageName)) {
                                    int i = runningAppProcessInfo.importance;
                                    boolean z = i == 100 || i == 200;
                                    Log.d(TAG, "[IncomingCallService] App process importance: " + i + ", isForeground: " + z);
                                    return !z;
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "[IncomingCallService] Exception when getting package name: " + e.getMessage());
                        return true;
                    }
                }
                Log.d(TAG, "[IncomingCallService] Could not determine app state from ActivityManager, using Catalyst instance check");
                return !zHasActiveCatalystInstance;
            } catch (Exception e2) {
                Log.e(TAG, "[IncomingCallService] Exception when checking Catalyst instance: " + e2.getMessage());
                return true;
            }
        } catch (Exception e3) {
            Log.e(TAG, "[IncomingCallService] Error checking if app is in background: " + e3.getMessage(), e3);
            return true;
        }
    }

    private void createNotificationChannel(String str, String str2, String str3, String str4, boolean z) {
        String packageName;
        Uri actualDefaultRingtoneUri;
        try {
            ReactApplicationContext reactApplicationContext = reactContext;
            packageName = reactApplicationContext != null ? reactApplicationContext.getPackageName() : getApplicationContext().getPackageName();
        } catch (Exception e) {
            Log.e(TAG, "[IncomingCallService] Error getting package name: " + e.getMessage(), e);
            packageName = getApplicationContext().getPackageName();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (z) {
                try {
                    Context applicationContext = reactContext;
                    if (applicationContext == null) {
                        applicationContext = getApplicationContext();
                    }
                    int identifier = applicationContext.getResources().getIdentifier(str4, "raw", packageName);
                    if (identifier > 0) {
                        actualDefaultRingtoneUri = Uri.parse("android.resource://" + packageName + "/" + identifier);
                    } else {
                        Log.d(TAG, String.format("getAudioUri() %s.%s not found in bundle, using default ringtone.", str4, "mp3"));
                        actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(applicationContext, 1);
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "[IncomingCallService] Error getting ringtone URI for channel: " + e2.getMessage(), e2);
                    actualDefaultRingtoneUri = RingtoneManager.getActualDefaultRingtoneUri(getApplicationContext(), 1);
                }
            } else {
                actualDefaultRingtoneUri = null;
            }
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, 4);
            notificationChannel.setDescription(str3);
            if (!z || actualDefaultRingtoneUri == null) {
                notificationChannel.setSound(null, null);
            } else {
                notificationChannel.setSound(actualDefaultRingtoneUri, new AudioAttributes.Builder().setUsage(6).build());
            }
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(false);
            notificationChannel.setImportance(4);
            notificationChannel.setLockscreenVisibility(1);
            if (Build.VERSION.SDK_INT >= 29) {
                notificationChannel.setAllowBubbles(false);
            }
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    notificationChannel.setBypassDnd(true);
                } catch (Exception e3) {
                    Log.d(TAG, "[IncomingCallService] setBypassDnd not available: " + e3.getMessage());
                }
            }
            Log.d(TAG, "[IncomingCallService] Create notification channel with importance 4");
            Log.d(TAG, "[IncomingCallService] Create notification channel with importance (via getter) " + notificationChannel.getImportance());
            Log.d(TAG, "[IncomingCallService] Create notification channel with enableSound: " + z);
            NotificationChannel notificationChannel2 = CallkeepHelperModule.notificationManager.getNotificationChannel(str);
            if (notificationChannel2 != null) {
                boolean z2 = notificationChannel2.getSound() != null;
                if (z2 == z) {
                    Log.d(TAG, "[IncomingCallService] Channel already exists with correct settings, reusing");
                    return;
                }
                Log.d(TAG, "[IncomingCallService] Channel exists but settings don't match (sound: " + z2 + " vs " + z + ")");
                try {
                    CallkeepHelperModule.notificationManager.deleteNotificationChannel(str);
                    Log.d(TAG, "[IncomingCallService] Channel deleted successfully");
                } catch (SecurityException e4) {
                    Log.w(TAG, "[IncomingCallService] Cannot delete channel (may be in use by foreground service): " + e4.getMessage());
                    return;
                }
            }
            CallkeepHelperModule.notificationManager.createNotificationChannel(notificationChannel);
            Log.d(TAG, "[IncomingCallService] Notification channel created: " + str + ", sound enabled: " + z);
        }
    }

    private void requestAudioFocus() {
        try {
            AudioManager audioManager = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            this.audioManager = audioManager;
            if (audioManager == null) {
                Log.w(TAG, "[IncomingCallService] AudioManager is null");
                return;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(6).setContentType(4).build()).setAcceptsDelayedFocusGain(false).setWillPauseWhenDucked(false).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() { // from class: com.kavkom.phone.IncomingCallService.3
                    AnonymousClass3() {
                    }

                    @Override // android.media.AudioManager.OnAudioFocusChangeListener
                    public void onAudioFocusChange(int i) {
                        Log.d(IncomingCallService.TAG, "[IncomingCallService] Audio focus changed: " + i);
                    }
                }).build();
                this.audioFocusRequest = audioFocusRequestBuild;
                this.audioFocusResult = this.audioManager.requestAudioFocus(audioFocusRequestBuild);
            } else {
                this.audioFocusResult = this.audioManager.requestAudioFocus(null, 2, 1);
            }
            Log.d(TAG, "[IncomingCallService] Audio focus requested, result: " + this.audioFocusResult);
        } catch (Exception e) {
            Log.e(TAG, "[IncomingCallService] Failed to request audio focus: " + e.getMessage(), e);
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallService$3 */
    class AnonymousClass3 implements AudioManager.OnAudioFocusChangeListener {
        AnonymousClass3() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            Log.d(IncomingCallService.TAG, "[IncomingCallService] Audio focus changed: " + i);
        }
    }

    private void releaseAudioFocus() {
        AudioFocusRequest audioFocusRequest;
        try {
            if (this.audioManager != null) {
                if (Build.VERSION.SDK_INT >= 26 && (audioFocusRequest = this.audioFocusRequest) != null) {
                    this.audioManager.abandonAudioFocusRequest(audioFocusRequest);
                } else {
                    this.audioManager.abandonAudioFocus(null);
                }
                Log.d(TAG, "[IncomingCallService] Audio focus released");
            }
        } catch (Exception e) {
            Log.e(TAG, "[IncomingCallService] Failed to release audio focus: " + e.getMessage(), e);
        }
    }

    private void sendEvent(String str, WritableMap writableMap) {
        try {
            if (CallkeepHelperModule.reactContext != null && CallkeepHelperModule.reactContext.hasActiveCatalystInstance()) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) CallkeepHelperModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            } else {
                Log.w(TAG, "[IncomingCallService] Cannot send event " + str + " - React context not available or Catalyst instance inactive");
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.kavkom.phone.IncomingCallService.4
                    final /* synthetic */ String val$eventName;
                    final /* synthetic */ WritableMap val$params;

                    AnonymousClass4(String str2, WritableMap writableMap2) {
                        str = str2;
                        writableMap = writableMap2;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (CallkeepHelperModule.reactContext == null || !CallkeepHelperModule.reactContext.hasActiveCatalystInstance()) {
                                Log.w(IncomingCallService.TAG, "[IncomingCallService] Failed to send event " + str + " after retry - React context still not available");
                            } else {
                                ((DeviceEventManagerModule.RCTDeviceEventEmitter) CallkeepHelperModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
                            }
                        } catch (Exception e) {
                            Log.e(IncomingCallService.TAG, "[IncomingCallService] Error sending event " + str + " on retry: " + e.getMessage(), e);
                        }
                    }
                }, 1000L);
            }
        } catch (Exception e) {
            Log.e(TAG, "[IncomingCallService] Error sending event " + str2 + ": " + e.getMessage(), e);
        }
    }

    /* JADX INFO: renamed from: com.kavkom.phone.IncomingCallService$4 */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ String val$eventName;
        final /* synthetic */ WritableMap val$params;

        AnonymousClass4(String str2, WritableMap writableMap2) {
            str = str2;
            writableMap = writableMap2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (CallkeepHelperModule.reactContext == null || !CallkeepHelperModule.reactContext.hasActiveCatalystInstance()) {
                    Log.w(IncomingCallService.TAG, "[IncomingCallService] Failed to send event " + str + " after retry - React context still not available");
                } else {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) CallkeepHelperModule.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
                }
            } catch (Exception e) {
                Log.e(IncomingCallService.TAG, "[IncomingCallService] Error sending event " + str + " on retry: " + e.getMessage(), e);
            }
        }
    }
}
