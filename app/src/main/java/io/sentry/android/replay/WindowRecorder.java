package io.sentry.android.replay;

import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.SentryOptions;
import io.sentry.android.replay.WindowRecorder;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.android.replay.util.MainLooperHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowRecorder.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 (2\u00020\u00012\u00020\u0002:\u0002()B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u001dH\u0016J\b\u0010#\u001a\u00020\u001dH\u0016J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u001dH\u0016R#\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0017\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0018j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lio/sentry/android/replay/WindowRecorder;", "Lio/sentry/android/replay/Recorder;", "Lio/sentry/android/replay/OnRootViewsChangedListener;", "options", "Lio/sentry/SentryOptions;", "screenshotRecorderCallback", "Lio/sentry/android/replay/ScreenshotRecorderCallback;", "mainLooperHandler", "Lio/sentry/android/replay/util/MainLooperHandler;", "(Lio/sentry/SentryOptions;Lio/sentry/android/replay/ScreenshotRecorderCallback;Lio/sentry/android/replay/util/MainLooperHandler;)V", "capturer", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "getCapturer", "()Ljava/util/concurrent/ScheduledExecutorService;", "capturer$delegate", "Lkotlin/Lazy;", "capturingTask", "Ljava/util/concurrent/ScheduledFuture;", "isRecording", "Ljava/util/concurrent/atomic/AtomicBoolean;", "recorder", "Lio/sentry/android/replay/ScreenshotRecorder;", "rootViews", "Ljava/util/ArrayList;", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "close", "", "onRootViewsChanged", "root", "added", "", "pause", "resume", ViewProps.START, "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "stop", "Companion", "RecorderExecutorServiceThreadFactory", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WindowRecorder implements Recorder, OnRootViewsChangedListener {
    private static final String TAG = "WindowRecorder";

    /* JADX INFO: renamed from: capturer$delegate, reason: from kotlin metadata */
    private final Lazy capturer;
    private ScheduledFuture<?> capturingTask;
    private final AtomicBoolean isRecording;
    private final MainLooperHandler mainLooperHandler;
    private final SentryOptions options;
    private ScreenshotRecorder recorder;
    private final ArrayList<WeakReference<View>> rootViews;
    private final ScreenshotRecorderCallback screenshotRecorderCallback;

    public WindowRecorder(SentryOptions options, ScreenshotRecorderCallback screenshotRecorderCallback, MainLooperHandler mainLooperHandler) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(mainLooperHandler, "mainLooperHandler");
        this.options = options;
        this.screenshotRecorderCallback = screenshotRecorderCallback;
        this.mainLooperHandler = mainLooperHandler;
        this.isRecording = new AtomicBoolean(false);
        this.rootViews = new ArrayList<>();
        this.capturer = LazyKt.lazy(new Function0<ScheduledExecutorService>() { // from class: io.sentry.android.replay.WindowRecorder$capturer$2
            @Override // kotlin.jvm.functions.Function0
            public final ScheduledExecutorService invoke() {
                return Executors.newSingleThreadScheduledExecutor(new WindowRecorder.RecorderExecutorServiceThreadFactory());
            }
        });
    }

    public /* synthetic */ WindowRecorder(SentryOptions sentryOptions, ScreenshotRecorderCallback screenshotRecorderCallback, MainLooperHandler mainLooperHandler, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sentryOptions, (i & 2) != 0 ? null : screenshotRecorderCallback, mainLooperHandler);
    }

    private final ScheduledExecutorService getCapturer() {
        return (ScheduledExecutorService) this.capturer.getValue();
    }

    @Override // io.sentry.android.replay.OnRootViewsChangedListener
    public void onRootViewsChanged(final View root, boolean added) {
        ScreenshotRecorder screenshotRecorder;
        Intrinsics.checkNotNullParameter(root, "root");
        if (added) {
            this.rootViews.add(new WeakReference<>(root));
            ScreenshotRecorder screenshotRecorder2 = this.recorder;
            if (screenshotRecorder2 != null) {
                screenshotRecorder2.bind(root);
                return;
            }
            return;
        }
        ScreenshotRecorder screenshotRecorder3 = this.recorder;
        if (screenshotRecorder3 != null) {
            screenshotRecorder3.unbind(root);
        }
        CollectionsKt.removeAll((List) this.rootViews, (Function1) new Function1<WeakReference<View>, Boolean>() { // from class: io.sentry.android.replay.WindowRecorder.onRootViewsChanged.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(WeakReference<View> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(Intrinsics.areEqual(it.get(), root));
            }
        });
        WeakReference weakReference = (WeakReference) CollectionsKt.lastOrNull((List) this.rootViews);
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null || Intrinsics.areEqual(root, view) || (screenshotRecorder = this.recorder) == null) {
            return;
        }
        screenshotRecorder.bind(view);
    }

    @Override // io.sentry.android.replay.Recorder
    public void start(ScreenshotRecorderConfig recorderConfig) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        if (this.isRecording.getAndSet(true)) {
            return;
        }
        this.recorder = new ScreenshotRecorder(recorderConfig, this.options, this.mainLooperHandler, this.screenshotRecorderCallback);
        ScheduledExecutorService capturer = getCapturer();
        Intrinsics.checkNotNullExpressionValue(capturer, "capturer");
        this.capturingTask = ExecutorsKt.scheduleAtFixedRateSafely(capturer, this.options, "WindowRecorder.capture", 100L, 1000 / ((long) recorderConfig.getFrameRate()), TimeUnit.MILLISECONDS, new Runnable() { // from class: io.sentry.android.replay.WindowRecorder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WindowRecorder.start$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(WindowRecorder this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScreenshotRecorder screenshotRecorder = this$0.recorder;
        if (screenshotRecorder != null) {
            screenshotRecorder.capture();
        }
    }

    @Override // io.sentry.android.replay.Recorder
    public void resume() {
        ScreenshotRecorder screenshotRecorder = this.recorder;
        if (screenshotRecorder != null) {
            screenshotRecorder.resume();
        }
    }

    @Override // io.sentry.android.replay.Recorder
    public void pause() {
        ScreenshotRecorder screenshotRecorder = this.recorder;
        if (screenshotRecorder != null) {
            screenshotRecorder.pause();
        }
    }

    @Override // io.sentry.android.replay.Recorder
    public void stop() {
        Iterator<T> it = this.rootViews.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            ScreenshotRecorder screenshotRecorder = this.recorder;
            if (screenshotRecorder != null) {
                screenshotRecorder.unbind((View) weakReference.get());
            }
        }
        ScreenshotRecorder screenshotRecorder2 = this.recorder;
        if (screenshotRecorder2 != null) {
            screenshotRecorder2.close();
        }
        this.rootViews.clear();
        this.recorder = null;
        ScheduledFuture<?> scheduledFuture = this.capturingTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.capturingTask = null;
        this.isRecording.set(false);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        stop();
        ScheduledExecutorService capturer = getCapturer();
        Intrinsics.checkNotNullExpressionValue(capturer, "capturer");
        ExecutorsKt.gracefullyShutdown(capturer, this.options);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: WindowRecorder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/sentry/android/replay/WindowRecorder$RecorderExecutorServiceThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "()V", "cnt", "", "newThread", "Ljava/lang/Thread;", "r", "Ljava/lang/Runnable;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class RecorderExecutorServiceThreadFactory implements ThreadFactory {
        private int cnt;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            StringBuilder sb = new StringBuilder("SentryWindowRecorder-");
            int i = this.cnt;
            this.cnt = i + 1;
            Thread thread = new Thread(r, sb.append(i).toString());
            thread.setDaemon(true);
            return thread;
        }
    }
}
