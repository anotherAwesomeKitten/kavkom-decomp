package com.oney.WebRTCModule;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.core.util.Consumer;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.messaging.Constants;
import com.oney.WebRTCModule.videoEffects.ProcessorProvider;
import com.oney.WebRTCModule.videoEffects.VideoEffectProcessor;
import com.oney.WebRTCModule.videoEffects.VideoFrameProcessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaSource;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

/* JADX INFO: loaded from: classes2.dex */
class GetUserMediaImpl {
    private CameraEnumerator cameraEnumerator;
    private Promise displayMediaPromise;
    private Intent mediaProjectionPermissionResultData;
    private final ReactApplicationContext reactContext;
    private final Map<String, TrackPrivate> tracks = new HashMap();
    private final WebRTCModule webRTCModule;
    private static final String TAG = WebRTCModule.TAG;
    private static final int PERMISSION_REQUEST_CODE = (int) (Math.random() * 32767.0d);

    public interface BiConsumer<T, U> {
        void accept(T t, U u);
    }

    GetUserMediaImpl(WebRTCModule webRTCModule, ReactApplicationContext reactApplicationContext) {
        this.webRTCModule = webRTCModule;
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(new AnonymousClass1());
    }

    /* JADX INFO: renamed from: com.oney.WebRTCModule.GetUserMediaImpl$1, reason: invalid class name */
    class AnonymousClass1 extends BaseActivityEventListener {
        AnonymousClass1() {
        }

        @Override // com.facebook.react.bridge.BaseActivityEventListener, com.facebook.react.bridge.ActivityEventListener
        public void onActivityResult(final Activity activity, int i, int i2, Intent intent) {
            super.onActivityResult(activity, i, i2, intent);
            if (i == GetUserMediaImpl.PERMISSION_REQUEST_CODE) {
                if (i2 != -1) {
                    GetUserMediaImpl.this.displayMediaPromise.reject("DOMException", "NotAllowedError");
                    GetUserMediaImpl.this.displayMediaPromise = null;
                } else {
                    GetUserMediaImpl.this.mediaProjectionPermissionResultData = intent;
                    ThreadUtils.runOnExecutor(new Runnable() { // from class: com.oney.WebRTCModule.GetUserMediaImpl$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onActivityResult$0(activity);
                        }
                    });
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onActivityResult$0(Activity activity) {
            MediaProjectionService.launch(activity);
            GetUserMediaImpl.this.createScreenStream();
        }
    }

    private AudioTrack createAudioTrack(ReadableMap readableMap) {
        ReadableMap map = readableMap.getMap(MediaStreamTrack.AUDIO_TRACK_KIND);
        Log.d(TAG, "getUserMedia(audio): " + map);
        String string = UUID.randomUUID().toString();
        PeerConnectionFactory peerConnectionFactory = this.webRTCModule.mFactory;
        MediaConstraints mediaConstraintsConstraintsForOptions = this.webRTCModule.constraintsForOptions(map);
        checkMandatoryConstraints(mediaConstraintsConstraintsForOptions);
        AudioSource audioSourceCreateAudioSource = peerConnectionFactory.createAudioSource(mediaConstraintsConstraintsForOptions);
        AudioTrack audioTrackCreateAudioTrack = peerConnectionFactory.createAudioTrack(string, audioSourceCreateAudioSource);
        this.tracks.put(string, new TrackPrivate(audioTrackCreateAudioTrack, audioSourceCreateAudioSource, null, null));
        return audioTrackCreateAudioTrack;
    }

    private void checkMandatoryConstraints(MediaConstraints mediaConstraints) {
        ArrayList arrayList = new ArrayList(mediaConstraints.mandatory.size());
        for (MediaConstraints.KeyValuePair keyValuePair : mediaConstraints.mandatory) {
            if (keyValuePair.getValue() != null) {
                arrayList.add(keyValuePair);
            } else {
                Log.d(TAG, String.format("constraint %s is null, ignoring it", keyValuePair.getKey()));
            }
        }
        mediaConstraints.mandatory.clear();
        mediaConstraints.mandatory.addAll(arrayList);
    }

    private CameraEnumerator getCameraEnumerator() {
        if (this.cameraEnumerator == null) {
            if (Camera2Enumerator.isSupported(this.reactContext)) {
                Log.d(TAG, "Creating camera enumerator using the Camera2 API");
                this.cameraEnumerator = new Camera2Enumerator(this.reactContext);
            } else {
                Log.d(TAG, "Creating camera enumerator using the Camera1 API");
                this.cameraEnumerator = new Camera1Enumerator(false);
            }
        }
        return this.cameraEnumerator;
    }

    ReadableArray enumerateDevices() {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        String[] deviceNames = getCameraEnumerator().getDeviceNames();
        for (int i = 0; i < deviceNames.length; i++) {
            String str = deviceNames[i];
            try {
                boolean zIsFrontFacing = getCameraEnumerator().isFrontFacing(str);
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("facing", zIsFrontFacing ? "front" : "environment");
                writableMapCreateMap.putString("deviceId", "" + i);
                writableMapCreateMap.putString("groupId", "");
                writableMapCreateMap.putString(Constants.ScionAnalytics.PARAM_LABEL, str);
                writableMapCreateMap.putString("kind", "videoinput");
                writableArrayCreateArray.pushMap(writableMapCreateMap);
            } catch (Exception unused) {
                Log.e(TAG, "Failed to check the facing mode of camera");
            }
        }
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putString("deviceId", "audio-1");
        writableMapCreateMap2.putString("groupId", "");
        writableMapCreateMap2.putString(Constants.ScionAnalytics.PARAM_LABEL, "Audio");
        writableMapCreateMap2.putString("kind", "audioinput");
        writableArrayCreateArray.pushMap(writableMapCreateMap2);
        return writableArrayCreateArray;
    }

    MediaStreamTrack getTrack(String str) {
        TrackPrivate trackPrivate = this.tracks.get(str);
        if (trackPrivate == null) {
            return null;
        }
        return trackPrivate.track;
    }

    void getUserMedia(ReadableMap readableMap, final Callback callback, Callback callback2) {
        VideoTrack videoTrackCreateVideoTrack = null;
        AudioTrack audioTrackCreateAudioTrack = readableMap.hasKey(MediaStreamTrack.AUDIO_TRACK_KIND) ? createAudioTrack(readableMap) : null;
        if (readableMap.hasKey("video")) {
            ReadableMap map = readableMap.getMap("video");
            Log.d(TAG, "getUserMedia(video): " + map);
            Activity currentActivity = this.reactContext.getCurrentActivity();
            if (currentActivity == null) {
                callback2.invoke("Error", "No current Activity.");
                return;
            }
            videoTrackCreateVideoTrack = createVideoTrack(new CameraCaptureController(currentActivity, getCameraEnumerator(), map));
        }
        if (audioTrackCreateAudioTrack == null && videoTrackCreateVideoTrack == null) {
            callback2.invoke("DOMException", "AbortError");
        } else {
            createStream(new MediaStreamTrack[]{audioTrackCreateAudioTrack, videoTrackCreateVideoTrack}, new BiConsumer() { // from class: com.oney.WebRTCModule.GetUserMediaImpl$$ExternalSyntheticLambda4
                @Override // com.oney.WebRTCModule.GetUserMediaImpl.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GetUserMediaImpl.lambda$getUserMedia$0(callback, (String) obj, (ArrayList) obj2);
                }
            });
        }
    }

    static /* synthetic */ void lambda$getUserMedia$0(Callback callback, String str, ArrayList arrayList) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushMap((WritableMap) it.next());
        }
        callback.invoke(str, writableArrayCreateArray);
    }

    void mediaStreamTrackSetEnabled(String str, boolean z) {
        TrackPrivate trackPrivate = this.tracks.get(str);
        if (trackPrivate == null || trackPrivate.videoCaptureController == null) {
            return;
        }
        if (z) {
            trackPrivate.videoCaptureController.startCapture();
        } else {
            trackPrivate.videoCaptureController.stopCapture();
        }
    }

    void disposeTrack(String str) {
        TrackPrivate trackPrivateRemove = this.tracks.remove(str);
        if (trackPrivateRemove != null) {
            trackPrivateRemove.dispose();
        }
    }

    void applyConstraints(String str, ReadableMap readableMap, final Promise promise) {
        TrackPrivate trackPrivate = this.tracks.get(str);
        if (trackPrivate != null && (trackPrivate.videoCaptureController instanceof AbstractVideoCaptureController)) {
            final AbstractVideoCaptureController abstractVideoCaptureController = trackPrivate.videoCaptureController;
            abstractVideoCaptureController.applyConstraints(readableMap, new Consumer<Exception>() { // from class: com.oney.WebRTCModule.GetUserMediaImpl.2
                @Override // androidx.core.util.Consumer
                public void accept(Exception exc) {
                    if (exc != null) {
                        promise.reject(exc);
                    } else {
                        promise.resolve(abstractVideoCaptureController.getSettings());
                    }
                }
            });
        } else {
            promise.reject(new Exception("Camera track not found!"));
        }
    }

    void getDisplayMedia(Promise promise) {
        if (this.displayMediaPromise != null) {
            promise.reject(new RuntimeException("Another operation is pending."));
            return;
        }
        final Activity currentActivity = this.reactContext.getCurrentActivity();
        if (currentActivity == null) {
            promise.reject(new RuntimeException("No current Activity."));
            return;
        }
        this.displayMediaPromise = promise;
        final MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) currentActivity.getApplication().getSystemService("media_projection");
        if (mediaProjectionManager != null) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.oney.WebRTCModule.GetUserMediaImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    currentActivity.startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), GetUserMediaImpl.PERMISSION_REQUEST_CODE);
                }
            });
        } else {
            promise.reject(new RuntimeException("MediaProjectionManager is null."));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createScreenStream() {
        VideoTrack videoTrackCreateScreenTrack = createScreenTrack();
        if (videoTrackCreateScreenTrack == null) {
            this.displayMediaPromise.reject(new RuntimeException("ScreenTrack is null."));
        } else {
            createStream(new MediaStreamTrack[]{videoTrackCreateScreenTrack}, new BiConsumer() { // from class: com.oney.WebRTCModule.GetUserMediaImpl$$ExternalSyntheticLambda0
                @Override // com.oney.WebRTCModule.GetUserMediaImpl.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.lambda$createScreenStream$1((String) obj, (ArrayList) obj2);
                }
            });
        }
        this.mediaProjectionPermissionResultData = null;
        this.displayMediaPromise = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createScreenStream$1(String str, ArrayList arrayList) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("streamId", str);
        if (arrayList.size() == 0) {
            this.displayMediaPromise.reject(new RuntimeException("No ScreenTrackInfo found."));
        } else {
            writableMapCreateMap.putMap("track", (ReadableMap) arrayList.get(0));
            this.displayMediaPromise.resolve(writableMapCreateMap);
        }
    }

    void createStream(MediaStreamTrack[] mediaStreamTrackArr, BiConsumer<String, ArrayList<WritableMap>> biConsumer) {
        String string = UUID.randomUUID().toString();
        MediaStream mediaStreamCreateLocalMediaStream = this.webRTCModule.mFactory.createLocalMediaStream(string);
        ArrayList<WritableMap> arrayList = new ArrayList<>();
        for (MediaStreamTrack mediaStreamTrack : mediaStreamTrackArr) {
            if (mediaStreamTrack != null) {
                boolean z = mediaStreamTrack instanceof AudioTrack;
                if (z) {
                    mediaStreamCreateLocalMediaStream.addTrack((AudioTrack) mediaStreamTrack);
                } else {
                    mediaStreamCreateLocalMediaStream.addTrack((VideoTrack) mediaStreamTrack);
                }
                WritableMap writableMapCreateMap = Arguments.createMap();
                String strId = mediaStreamTrack.id();
                writableMapCreateMap.putBoolean(ViewProps.ENABLED, mediaStreamTrack.enabled());
                writableMapCreateMap.putString("id", strId);
                writableMapCreateMap.putString("kind", mediaStreamTrack.kind());
                writableMapCreateMap.putString("readyState", "live");
                writableMapCreateMap.putBoolean("remote", false);
                if (mediaStreamTrack instanceof VideoTrack) {
                    writableMapCreateMap.putMap("settings", this.tracks.get(strId).videoCaptureController.getSettings());
                }
                if (z) {
                    WritableMap writableMapCreateMap2 = Arguments.createMap();
                    writableMapCreateMap2.putString("deviceId", "audio-1");
                    writableMapCreateMap2.putString("groupId", "");
                    writableMapCreateMap.putMap("settings", writableMapCreateMap2);
                }
                arrayList.add(writableMapCreateMap);
            }
        }
        Log.d(TAG, "MediaStream id: " + string);
        this.webRTCModule.localStreams.put(string, mediaStreamCreateLocalMediaStream);
        biConsumer.accept(string, arrayList);
    }

    private VideoTrack createScreenTrack() {
        DisplayMetrics displayMetrics = DisplayUtils.getDisplayMetrics(this.reactContext.getCurrentActivity());
        return createVideoTrack(new ScreenCaptureController(this.reactContext.getCurrentActivity(), displayMetrics.widthPixels, displayMetrics.heightPixels, this.mediaProjectionPermissionResultData));
    }

    VideoTrack createVideoTrack(AbstractVideoCaptureController abstractVideoCaptureController) {
        abstractVideoCaptureController.initializeVideoCapturer();
        VideoCapturer videoCapturer = abstractVideoCaptureController.videoCapturer;
        if (videoCapturer == null) {
            return null;
        }
        PeerConnectionFactory peerConnectionFactory = this.webRTCModule.mFactory;
        SurfaceTextureHelper surfaceTextureHelperCreate = SurfaceTextureHelper.create("CaptureThread", EglUtils.getRootEglBaseContext());
        if (surfaceTextureHelperCreate == null) {
            Log.d(TAG, "Error creating SurfaceTextureHelper");
            return null;
        }
        String string = UUID.randomUUID().toString();
        abstractVideoCaptureController.setCapturerEventsListener(new TrackCapturerEventsEmitter(this.webRTCModule, string));
        VideoSource videoSourceCreateVideoSource = peerConnectionFactory.createVideoSource(videoCapturer.isScreencast());
        videoCapturer.initialize(surfaceTextureHelperCreate, this.reactContext, videoSourceCreateVideoSource.getCapturerObserver());
        VideoTrack videoTrackCreateVideoTrack = peerConnectionFactory.createVideoTrack(string, videoSourceCreateVideoSource);
        videoTrackCreateVideoTrack.setEnabled(true);
        this.tracks.put(string, new TrackPrivate(videoTrackCreateVideoTrack, videoSourceCreateVideoSource, abstractVideoCaptureController, surfaceTextureHelperCreate));
        abstractVideoCaptureController.startCapture();
        return videoTrackCreateVideoTrack;
    }

    void setVideoEffects(String str, ReadableArray readableArray) {
        TrackPrivate trackPrivate = this.tracks.get(str);
        if (trackPrivate == null || !(trackPrivate.videoCaptureController instanceof CameraCaptureController)) {
            return;
        }
        VideoSource videoSource = (VideoSource) trackPrivate.mediaSource;
        SurfaceTextureHelper surfaceTextureHelper = trackPrivate.surfaceTextureHelper;
        if (readableArray != null) {
            videoSource.setVideoProcessor(new VideoEffectProcessor((List) readableArray.toArrayList().stream().filter(new Predicate() { // from class: com.oney.WebRTCModule.GetUserMediaImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return GetUserMediaImpl.lambda$setVideoEffects$2(obj);
                }
            }).map(new Function() { // from class: com.oney.WebRTCModule.GetUserMediaImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GetUserMediaImpl.lambda$setVideoEffects$3(obj);
                }
            }).filter(new Predicate() { // from class: com.oney.WebRTCModule.GetUserMediaImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Objects.nonNull((VideoFrameProcessor) obj);
                }
            }).collect(Collectors.toList()), surfaceTextureHelper));
        } else {
            videoSource.setVideoProcessor(null);
        }
    }

    static /* synthetic */ boolean lambda$setVideoEffects$2(Object obj) {
        return obj instanceof String;
    }

    static /* synthetic */ VideoFrameProcessor lambda$setVideoEffects$3(Object obj) {
        VideoFrameProcessor processor = ProcessorProvider.getProcessor((String) obj);
        if (processor == null) {
            Log.e(TAG, "no videoFrameProcessor associated with this name: " + obj);
        }
        return processor;
    }

    private static class TrackPrivate {
        private boolean disposed = false;
        public final MediaSource mediaSource;
        private final SurfaceTextureHelper surfaceTextureHelper;
        public final MediaStreamTrack track;
        public final AbstractVideoCaptureController videoCaptureController;

        public TrackPrivate(MediaStreamTrack mediaStreamTrack, MediaSource mediaSource, AbstractVideoCaptureController abstractVideoCaptureController, SurfaceTextureHelper surfaceTextureHelper) {
            this.track = mediaStreamTrack;
            this.mediaSource = mediaSource;
            this.videoCaptureController = abstractVideoCaptureController;
            this.surfaceTextureHelper = surfaceTextureHelper;
        }

        public void dispose() {
            if (this.disposed) {
                return;
            }
            AbstractVideoCaptureController abstractVideoCaptureController = this.videoCaptureController;
            if (abstractVideoCaptureController != null && abstractVideoCaptureController.stopCapture()) {
                this.videoCaptureController.dispose();
            }
            SurfaceTextureHelper surfaceTextureHelper = this.surfaceTextureHelper;
            if (surfaceTextureHelper != null) {
                surfaceTextureHelper.stopListening();
                this.surfaceTextureHelper.dispose();
            }
            this.mediaSource.dispose();
            this.track.dispose();
            this.disposed = true;
        }
    }
}
