package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.firebase.messaging.Constants;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.replay.video.MuxerConfig;
import io.sentry.android.replay.video.SimpleVideoEncoder;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryStackTrace;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.LongProgression;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ReplayCache.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u0000 >2\u00020\u0001:\u0001>B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ)\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0002\b(J\"\u0010!\u001a\u00020\"2\u0006\u0010)\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0016J\b\u0010*\u001a\u00020\"H\u0016J:\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u0002002\b\b\u0002\u00103\u001a\u00020\u0019J\u0010\u00104\u001a\u00020\"2\u0006\u00105\u001a\u00020\u0019H\u0002J\u0010\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u000fH\u0002J\u0018\u00109\u001a\u00020\"2\u0006\u0010:\u001a\u00020\u00162\b\u0010;\u001a\u0004\u0018\u00010\u0016J\u0010\u0010<\u001a\u0004\u0018\u00010\u00162\u0006\u0010=\u001a\u00020&R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u00198@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u001d\u001a\u0004\b\u001f\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lio/sentry/android/replay/ReplayCache;", "Ljava/io/Closeable;", "options", "Lio/sentry/SentryOptions;", "replayId", "Lio/sentry/protocol/SentryId;", "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "(Lio/sentry/SentryOptions;Lio/sentry/protocol/SentryId;Lio/sentry/android/replay/ScreenshotRecorderConfig;)V", "encoder", "Lio/sentry/android/replay/video/SimpleVideoEncoder;", "encoderLock", "", SentryStackTrace.JsonKeys.FRAMES, "", "Lio/sentry/android/replay/ReplayFrame;", "getFrames$sentry_android_replay_release", "()Ljava/util/List;", "isClosed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "ongoingSegment", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "ongoingSegmentFile", "Ljava/io/File;", "getOngoingSegmentFile", "()Ljava/io/File;", "ongoingSegmentFile$delegate", "Lkotlin/Lazy;", "replayCacheDir", "getReplayCacheDir$sentry_android_replay_release", "replayCacheDir$delegate", "addFrame", "", "bitmap", "Landroid/graphics/Bitmap;", "frameTimestamp", "", "screen", "addFrame$sentry_android_replay_release", "screenshot", "close", "createVideoOf", "Lio/sentry/android/replay/GeneratedVideo;", "duration", Constants.MessagePayloadKeys.FROM, RRWebVideoEvent.JsonKeys.SEGMENT_ID, "", "height", "width", "videoFile", "deleteFile", "file", "encode", "", TypedValues.Attributes.S_FRAME, "persistSegmentValues", "key", "value", "rotate", "until", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ReplayCache implements Closeable {
    public static final String ONGOING_SEGMENT = ".ongoing_segment";
    public static final String SEGMENT_KEY_BIT_RATE = "config.bit-rate";
    public static final String SEGMENT_KEY_FRAME_RATE = "config.frame-rate";
    public static final String SEGMENT_KEY_HEIGHT = "config.height";
    public static final String SEGMENT_KEY_ID = "segment.id";
    public static final String SEGMENT_KEY_REPLAY_ID = "replay.id";
    public static final String SEGMENT_KEY_REPLAY_RECORDING = "replay.recording";
    public static final String SEGMENT_KEY_REPLAY_SCREEN_AT_START = "replay.screen-at-start";
    public static final String SEGMENT_KEY_REPLAY_TYPE = "replay.type";
    public static final String SEGMENT_KEY_TIMESTAMP = "segment.timestamp";
    public static final String SEGMENT_KEY_WIDTH = "config.width";
    private SimpleVideoEncoder encoder;
    private final Object encoderLock;
    private final List<ReplayFrame> frames;
    private final AtomicBoolean isClosed;
    private final LinkedHashMap<String, String> ongoingSegment;

    /* JADX INFO: renamed from: ongoingSegmentFile$delegate, reason: from kotlin metadata */
    private final Lazy ongoingSegmentFile;
    private final SentryOptions options;
    private final ScreenshotRecorderConfig recorderConfig;

    /* JADX INFO: renamed from: replayCacheDir$delegate, reason: from kotlin metadata */
    private final Lazy replayCacheDir;
    private final SentryId replayId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ReplayCache(SentryOptions options, SentryId replayId, ScreenshotRecorderConfig recorderConfig) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(replayId, "replayId");
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        this.options = options;
        this.replayId = replayId;
        this.recorderConfig = recorderConfig;
        this.isClosed = new AtomicBoolean(false);
        this.encoderLock = new Object();
        this.replayCacheDir = LazyKt.lazy(new Function0<File>() { // from class: io.sentry.android.replay.ReplayCache$replayCacheDir$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return ReplayCache.INSTANCE.makeReplayCacheDir(this.this$0.options, this.this$0.replayId);
            }
        });
        this.frames = new ArrayList();
        this.ongoingSegment = new LinkedHashMap<>();
        this.ongoingSegmentFile = LazyKt.lazy(new Function0<File>() { // from class: io.sentry.android.replay.ReplayCache$ongoingSegmentFile$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() throws IOException {
                if (this.this$0.getReplayCacheDir$sentry_android_replay_release() == null) {
                    return null;
                }
                File file = new File(this.this$0.getReplayCacheDir$sentry_android_replay_release(), ReplayCache.ONGOING_SEGMENT);
                if (!file.exists()) {
                    file.createNewFile();
                }
                return file;
            }
        });
    }

    public final File getReplayCacheDir$sentry_android_replay_release() {
        return (File) this.replayCacheDir.getValue();
    }

    public final List<ReplayFrame> getFrames$sentry_android_replay_release() {
        return this.frames;
    }

    private final File getOngoingSegmentFile() {
        return (File) this.ongoingSegmentFile.getValue();
    }

    public static /* synthetic */ void addFrame$sentry_android_replay_release$default(ReplayCache replayCache, Bitmap bitmap, long j, String str, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            str = null;
        }
        replayCache.addFrame$sentry_android_replay_release(bitmap, j, str);
    }

    public final void addFrame$sentry_android_replay_release(Bitmap bitmap, long frameTimestamp, String screen) throws IOException {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        if (getReplayCacheDir$sentry_android_replay_release() == null || bitmap.isRecycled()) {
            return;
        }
        File replayCacheDir$sentry_android_replay_release = getReplayCacheDir$sentry_android_replay_release();
        if (replayCacheDir$sentry_android_replay_release != null) {
            replayCacheDir$sentry_android_replay_release.mkdirs();
        }
        File file = new File(getReplayCacheDir$sentry_android_replay_release(), frameTimestamp + ".jpg");
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            FileOutputStream fileOutputStream2 = fileOutputStream;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream2);
            fileOutputStream2.flush();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
            addFrame(file, frameTimestamp, screen);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ void addFrame$default(ReplayCache replayCache, File file, long j, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        replayCache.addFrame(file, j, str);
    }

    public final void addFrame(File screenshot, long frameTimestamp, String screen) {
        Intrinsics.checkNotNullParameter(screenshot, "screenshot");
        this.frames.add(new ReplayFrame(screenshot, frameTimestamp, screen));
    }

    public static /* synthetic */ GeneratedVideo createVideoOf$default(ReplayCache replayCache, long j, long j2, int i, int i2, int i3, File file, int i4, Object obj) {
        int i5;
        File file2;
        if ((i4 & 32) != 0) {
            i5 = i;
            file2 = new File(replayCache.getReplayCacheDir$sentry_android_replay_release(), i5 + ".mp4");
        } else {
            i5 = i;
            file2 = file;
        }
        return replayCache.createVideoOf(j, j2, i5, i2, i3, file2);
    }

    public final GeneratedVideo createVideoOf(long duration, long from, int segmentId, int height, int width, File videoFile) throws Throwable {
        Object obj;
        int i;
        long duration2;
        Intrinsics.checkNotNullParameter(videoFile, "videoFile");
        if (videoFile.exists() && videoFile.length() > 0) {
            videoFile.delete();
        }
        if (this.frames.isEmpty()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "No captured frames, skipping generating a video segment", new Object[0]);
            return null;
        }
        Object obj2 = this.encoderLock;
        synchronized (obj2) {
            try {
                obj = obj2;
            } catch (Throwable th) {
                th = th;
                obj = obj2;
            }
            try {
                SimpleVideoEncoder simpleVideoEncoder = new SimpleVideoEncoder(this.options, new MuxerConfig(videoFile, width, height, this.recorderConfig.getFrameRate(), this.recorderConfig.getBitRate(), null, 32, null), null, 4, null);
                simpleVideoEncoder.start();
                this.encoder = simpleVideoEncoder;
                long frameRate = ((long) 1000) / ((long) this.recorderConfig.getFrameRate());
                ReplayFrame replayFrame = (ReplayFrame) CollectionsKt.first((List) this.frames);
                long j = from + duration;
                LongProgression longProgressionStep = RangesKt.step(RangesKt.until(from, j), frameRate);
                long first = longProgressionStep.getFirst();
                long last = longProgressionStep.getLast();
                long step = longProgressionStep.getStep();
                if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
                    i = 0;
                } else {
                    int i2 = 0;
                    while (true) {
                        Iterator<ReplayFrame> it = this.frames.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ReplayFrame next = it.next();
                            long j2 = first + frameRate;
                            long timestamp = next.getTimestamp();
                            if (first <= timestamp && timestamp <= j2) {
                                replayFrame = next;
                                break;
                            }
                            if (next.getTimestamp() > j2) {
                                break;
                            }
                        }
                        if (encode(replayFrame)) {
                            i2++;
                        }
                        if (first == last) {
                            break;
                        }
                        first += step;
                    }
                    i = i2;
                }
                if (i == 0) {
                    this.options.getLogger().log(SentryLevel.DEBUG, "Generated a video with no frames, not capturing a replay segment", new Object[0]);
                    deleteFile(videoFile);
                    return null;
                }
                synchronized (this.encoderLock) {
                    SimpleVideoEncoder simpleVideoEncoder2 = this.encoder;
                    if (simpleVideoEncoder2 != null) {
                        simpleVideoEncoder2.release();
                    }
                    SimpleVideoEncoder simpleVideoEncoder3 = this.encoder;
                    duration2 = simpleVideoEncoder3 != null ? simpleVideoEncoder3.getDuration() : 0L;
                    this.encoder = null;
                    Unit unit = Unit.INSTANCE;
                }
                rotate(j);
                return new GeneratedVideo(videoFile, i, duration2);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private final boolean encode(ReplayFrame frame) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(frame.getScreenshot().getAbsolutePath());
            synchronized (this.encoderLock) {
                SimpleVideoEncoder simpleVideoEncoder = this.encoder;
                if (simpleVideoEncoder != null) {
                    Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                    simpleVideoEncoder.encode(bitmap);
                    Unit unit = Unit.INSTANCE;
                }
            }
            bitmap.recycle();
            return true;
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.WARNING, "Unable to decode bitmap and encode it into a video, skipping frame", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteFile(File file) {
        try {
            if (file.delete()) {
                return;
            }
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete replay frame: %s", file.getAbsolutePath());
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, th, "Failed to delete replay frame: %s", file.getAbsolutePath());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String rotate(final long until) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        CollectionsKt.removeAll((List) this.frames, (Function1) new Function1<ReplayFrame, Boolean>() { // from class: io.sentry.android.replay.ReplayCache.rotate.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r5v3, types: [T, java.lang.String] */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ReplayFrame it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getTimestamp() < until) {
                    this.deleteFile(it.getScreenshot());
                    return true;
                }
                if (objectRef.element == null) {
                    objectRef.element = it.getScreen();
                }
                return false;
            }
        });
        return (String) objectRef.element;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.encoderLock) {
            SimpleVideoEncoder simpleVideoEncoder = this.encoder;
            if (simpleVideoEncoder != null) {
                simpleVideoEncoder.release();
            }
            this.encoder = null;
            Unit unit = Unit.INSTANCE;
        }
        this.isClosed.set(true);
    }

    public final synchronized void persistSegmentValues(String key, String value) {
        File ongoingSegmentFile;
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.isClosed.get()) {
            return;
        }
        if (this.ongoingSegment.isEmpty() && (ongoingSegmentFile = getOngoingSegmentFile()) != null) {
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(ongoingSegmentFile), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                Sequence<String> sequenceLineSequence = TextStreamsKt.lineSequence(bufferedReader);
                LinkedHashMap<String, String> linkedHashMap = this.ongoingSegment;
                Iterator<String> it = sequenceLineSequence.iterator();
                while (it.hasNext()) {
                    List listSplit$default = StringsKt.split$default((CharSequence) it.next(), new String[]{"="}, false, 2, 2, (Object) null);
                    Pair pair = TuplesKt.to((String) listSplit$default.get(0), (String) listSplit$default.get(1));
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                }
                CloseableKt.closeFinally(bufferedReader, null);
            } finally {
            }
        }
        if (value == null) {
            this.ongoingSegment.remove(key);
        } else {
            this.ongoingSegment.put(key, value);
        }
        File ongoingSegmentFile2 = getOngoingSegmentFile();
        if (ongoingSegmentFile2 != null) {
            Set<Map.Entry<String, String>> setEntrySet = this.ongoingSegment.entrySet();
            Intrinsics.checkNotNullExpressionValue(setEntrySet, "ongoingSegment.entries");
            FilesKt.writeText$default(ongoingSegmentFile2, CollectionsKt.joinToString$default(setEntrySet, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, null, null, 0, null, new Function1<Map.Entry<String, String>, CharSequence>() { // from class: io.sentry.android.replay.ReplayCache.persistSegmentValues.2
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(Map.Entry<String, String> entry) {
                    Intrinsics.checkNotNullParameter(entry, "<name for destructuring parameter 0>");
                    return entry.getKey() + '=' + entry.getValue();
                }
            }, 30, null), null, 2, null);
        }
    }

    /* JADX INFO: compiled from: ReplayCache.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J[\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142:\b\u0002\u0010\u0015\u001a4\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0016H\u0000¢\u0006\u0002\b\u001cJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lio/sentry/android/replay/ReplayCache$Companion;", "", "()V", "ONGOING_SEGMENT", "", "SEGMENT_KEY_BIT_RATE", "SEGMENT_KEY_FRAME_RATE", "SEGMENT_KEY_HEIGHT", "SEGMENT_KEY_ID", "SEGMENT_KEY_REPLAY_ID", "SEGMENT_KEY_REPLAY_RECORDING", "SEGMENT_KEY_REPLAY_SCREEN_AT_START", "SEGMENT_KEY_REPLAY_TYPE", "SEGMENT_KEY_TIMESTAMP", "SEGMENT_KEY_WIDTH", "fromDisk", "Lio/sentry/android/replay/LastSegmentData;", "options", "Lio/sentry/SentryOptions;", "replayId", "Lio/sentry/protocol/SentryId;", "replayCacheProvider", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "recorderConfig", "Lio/sentry/android/replay/ReplayCache;", "fromDisk$sentry_android_replay_release", "makeReplayCacheDir", "Ljava/io/File;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final File makeReplayCacheDir(SentryOptions options, SentryId replayId) {
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(replayId, "replayId");
            String cacheDirPath = options.getCacheDirPath();
            if (cacheDirPath == null || cacheDirPath.length() == 0) {
                options.getLogger().log(SentryLevel.WARNING, "SentryOptions.cacheDirPath is not set, session replay is no-op", new Object[0]);
                return null;
            }
            String cacheDirPath2 = options.getCacheDirPath();
            Intrinsics.checkNotNull(cacheDirPath2);
            File file = new File(cacheDirPath2, "replay_" + replayId);
            file.mkdirs();
            return file;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LastSegmentData fromDisk$sentry_android_replay_release$default(Companion companion, SentryOptions sentryOptions, SentryId sentryId, Function2 function2, int i, Object obj) {
            if ((i & 4) != 0) {
                function2 = null;
            }
            return companion.fromDisk$sentry_android_replay_release(sentryOptions, sentryId, function2);
        }

        /* JADX WARN: Removed duplicated region for block: B:92:0x0229  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final io.sentry.android.replay.LastSegmentData fromDisk$sentry_android_replay_release(io.sentry.SentryOptions r26, io.sentry.protocol.SentryId r27, kotlin.jvm.functions.Function2<? super io.sentry.protocol.SentryId, ? super io.sentry.android.replay.ScreenshotRecorderConfig, io.sentry.android.replay.ReplayCache> r28) {
            /*
                Method dump skipped, instruction units count: 621
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.ReplayCache.Companion.fromDisk$sentry_android_replay_release(io.sentry.SentryOptions, io.sentry.protocol.SentryId, kotlin.jvm.functions.Function2):io.sentry.android.replay.LastSegmentData");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean fromDisk$lambda$3(ReplayCache cache, File file, String name) {
            Intrinsics.checkNotNullParameter(cache, "$cache");
            Intrinsics.checkNotNullExpressionValue(name, "name");
            if (StringsKt.endsWith$default(name, ".jpg", false, 2, (Object) null)) {
                File file2 = new File(file, name);
                Long longOrNull = StringsKt.toLongOrNull(FilesKt.getNameWithoutExtension(file2));
                if (longOrNull != null) {
                    ReplayCache.addFrame$default(cache, file2, longOrNull.longValue(), null, 4, null);
                }
            }
            return false;
        }
    }
}
