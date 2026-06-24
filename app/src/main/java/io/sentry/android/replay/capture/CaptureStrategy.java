package io.sentry.android.replay.capture;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.Breadcrumb;
import io.sentry.DateUtils;
import io.sentry.Hint;
import io.sentry.IHub;
import io.sentry.IScope;
import io.sentry.ReplayRecording;
import io.sentry.ScopeCallback;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayEvent;
import io.sentry.android.replay.GeneratedVideo;
import io.sentry.android.replay.ReplayCache;
import io.sentry.android.replay.ScreenshotRecorderConfig;
import io.sentry.android.replay.capture.CaptureStrategy;
import io.sentry.protocol.SentryId;
import io.sentry.rrweb.RRWebBreadcrumbEvent;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebMetaEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: CaptureStrategy.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b`\u0018\u0000 @2\u00020\u0001:\u0002@AJ$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001f0#H&J\b\u0010$\u001a\u00020\u001fH&J\b\u0010%\u001a\u00020\u0000H&J\u0010\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(H&J\u0012\u0010)\u001a\u00020\u001f2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016JB\u0010,\u001a\u00020\u001f2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2,\u0010/\u001a(\u0012\u0004\u0012\u000201\u0012\u0013\u0012\u001102¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020\u001f00¢\u0006\u0002\b6H&J\u0010\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u000209H&J\b\u0010:\u001a\u00020\u001fH&J\b\u0010;\u001a\u00020\u001fH&J0\u0010<\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010=\u001a\u00020\t2\b\b\u0002\u0010>\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J\b\u0010?\u001a\u00020\u001fH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u00020\u0013X¦\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u0019X¦\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006B"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy;", "", "currentReplayId", "Lio/sentry/protocol/SentryId;", "getCurrentReplayId", "()Lio/sentry/protocol/SentryId;", "setCurrentReplayId", "(Lio/sentry/protocol/SentryId;)V", "currentSegment", "", "getCurrentSegment", "()I", "setCurrentSegment", "(I)V", "replayCacheDir", "Ljava/io/File;", "getReplayCacheDir", "()Ljava/io/File;", "replayType", "Lio/sentry/SentryReplayEvent$ReplayType;", "getReplayType", "()Lio/sentry/SentryReplayEvent$ReplayType;", "setReplayType", "(Lio/sentry/SentryReplayEvent$ReplayType;)V", "segmentTimestamp", "Ljava/util/Date;", "getSegmentTimestamp", "()Ljava/util/Date;", "setSegmentTimestamp", "(Ljava/util/Date;)V", "captureReplay", "", "isTerminating", "", "onSegmentSent", "Lkotlin/Function1;", "close", "convert", "onConfigurationChanged", "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "onScreenChanged", "screen", "", "onScreenshotRecorded", "bitmap", "Landroid/graphics/Bitmap;", "store", "Lkotlin/Function2;", "Lio/sentry/android/replay/ReplayCache;", "", "Lkotlin/ParameterName;", "name", "frameTimestamp", "Lkotlin/ExtensionFunctionType;", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "pause", "resume", ViewProps.START, RRWebVideoEvent.JsonKeys.SEGMENT_ID, "replayId", "stop", "Companion", "ReplaySegment", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface CaptureStrategy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void captureReplay(boolean isTerminating, Function1<? super Date, Unit> onSegmentSent);

    void close();

    CaptureStrategy convert();

    SentryId getCurrentReplayId();

    int getCurrentSegment();

    File getReplayCacheDir();

    SentryReplayEvent.ReplayType getReplayType();

    Date getSegmentTimestamp();

    void onConfigurationChanged(ScreenshotRecorderConfig recorderConfig);

    void onScreenChanged(String screen);

    void onScreenshotRecorded(Bitmap bitmap, Function2<? super ReplayCache, ? super Long, Unit> store);

    void onTouchEvent(MotionEvent event);

    void pause();

    void resume();

    void setCurrentReplayId(SentryId sentryId);

    void setCurrentSegment(int i);

    void setReplayType(SentryReplayEvent.ReplayType replayType);

    void setSegmentTimestamp(Date date);

    void start(ScreenshotRecorderConfig recorderConfig, int segmentId, SentryId replayId, SentryReplayEvent.ReplayType replayType);

    void stop();

    /* JADX INFO: compiled from: CaptureStrategy.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onScreenChanged(CaptureStrategy captureStrategy, String str) {
        }

        public static /* synthetic */ void start$default(CaptureStrategy captureStrategy, ScreenshotRecorderConfig screenshotRecorderConfig, int i, SentryId sentryId, SentryReplayEvent.ReplayType replayType, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: start");
            }
            if ((i2 & 2) != 0) {
                i = 0;
            }
            if ((i2 & 4) != 0) {
                sentryId = new SentryId();
            }
            if ((i2 & 8) != 0) {
                replayType = null;
            }
            captureStrategy.start(screenshotRecorderConfig, i, sentryId, replayType);
        }

        public static /* synthetic */ void onScreenshotRecorded$default(CaptureStrategy captureStrategy, Bitmap bitmap, Function2 function2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onScreenshotRecorded");
            }
            if ((i & 1) != 0) {
                bitmap = null;
            }
            captureStrategy.onScreenshotRecorded(bitmap, function2);
        }
    }

    /* JADX INFO: compiled from: CaptureStrategy.kt */
    @Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0086\u0001\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u008a\u0001\u0010#\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010\u0017\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!J;\u0010+\u001a\u00020,2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010-\u001a\u00020\u00042\u0016\b\u0002\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020,\u0018\u00010/H\u0000¢\u0006\u0002\b0R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u00061"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$Companion;", "", "()V", "BREADCRUMB_START_OFFSET", "", "currentEventsLock", "getCurrentEventsLock$sentry_android_replay_release", "()Ljava/lang/Object;", "buildReplay", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "options", "Lio/sentry/SentryOptions;", "video", "Ljava/io/File;", "currentReplayId", "Lio/sentry/protocol/SentryId;", "segmentTimestamp", "Ljava/util/Date;", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "", "height", "width", RRWebVideoEvent.JsonKeys.FRAME_COUNT, RRWebVideoEvent.JsonKeys.FRAME_RATE, "videoDuration", "replayType", "Lio/sentry/SentryReplayEvent$ReplayType;", "screenAtStart", "", SentryBaseEvent.JsonKeys.BREADCRUMBS, "", "Lio/sentry/Breadcrumb;", "events", "Ljava/util/LinkedList;", "Lio/sentry/rrweb/RRWebEvent;", "createSegment", "hub", "Lio/sentry/IHub;", "duration", "currentSegmentTimestamp", "replayId", "cache", "Lio/sentry/android/replay/ReplayCache;", "rotateEvents", "", "until", "callback", "Lkotlin/Function1;", "rotateEvents$sentry_android_replay_release", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private static final long BREADCRUMB_START_OFFSET = 100;
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Object currentEventsLock = new Object();

        private Companion() {
        }

        public final Object getCurrentEventsLock$sentry_android_replay_release() {
            return currentEventsLock;
        }

        /* JADX WARN: Type inference failed for: r7v1, types: [T, java.util.List] */
        public final ReplaySegment createSegment(IHub hub, SentryOptions options, long duration, Date currentSegmentTimestamp, SentryId replayId, int segmentId, int height, int width, SentryReplayEvent.ReplayType replayType, ReplayCache cache, int frameRate, String screenAtStart, List<Breadcrumb> breadcrumbs, LinkedList<RRWebEvent> events) {
            GeneratedVideo generatedVideoCreateVideoOf$default;
            List<Breadcrumb> list;
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(currentSegmentTimestamp, "currentSegmentTimestamp");
            Intrinsics.checkNotNullParameter(replayId, "replayId");
            Intrinsics.checkNotNullParameter(replayType, "replayType");
            Intrinsics.checkNotNullParameter(events, "events");
            if (cache == null || (generatedVideoCreateVideoOf$default = ReplayCache.createVideoOf$default(cache, duration, currentSegmentTimestamp.getTime(), segmentId, height, width, null, 32, null)) == null) {
                return ReplaySegment.Failed.INSTANCE;
            }
            File video = generatedVideoCreateVideoOf$default.getVideo();
            int frameCount = generatedVideoCreateVideoOf$default.getFrameCount();
            long duration2 = generatedVideoCreateVideoOf$default.getDuration();
            if (breadcrumbs == null) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = CollectionsKt.emptyList();
                if (hub != null) {
                    hub.configureScope(new ScopeCallback() { // from class: io.sentry.android.replay.capture.CaptureStrategy$Companion$$ExternalSyntheticLambda0
                        @Override // io.sentry.ScopeCallback
                        public final void run(IScope iScope) {
                            CaptureStrategy.Companion.createSegment$lambda$0(objectRef, iScope);
                        }
                    });
                }
                list = (List) objectRef.element;
            } else {
                list = breadcrumbs;
            }
            return buildReplay(options, video, replayId, currentSegmentTimestamp, segmentId, height, width, frameCount, frameRate, duration2, replayType, screenAtStart, list, events);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r0v2, types: [T, java.util.ArrayList] */
        public static final void createSegment$lambda$0(Ref.ObjectRef crumbs, IScope scope) {
            Intrinsics.checkNotNullParameter(crumbs, "$crumbs");
            Intrinsics.checkNotNullParameter(scope, "scope");
            crumbs.element = new ArrayList(scope.getBreadcrumbs());
        }

        private final ReplaySegment buildReplay(SentryOptions options, File video, SentryId currentReplayId, final Date segmentTimestamp, int segmentId, int height, int width, int frameCount, int frameRate, long videoDuration, SentryReplayEvent.ReplayType replayType, String screenAtStart, List<Breadcrumb> breadcrumbs, LinkedList<RRWebEvent> events) {
            RRWebEvent rRWebEventConvert;
            Date dateTime = DateUtils.getDateTime(segmentTimestamp.getTime() + videoDuration);
            Intrinsics.checkNotNullExpressionValue(dateTime, "getDateTime(segmentTimestamp.time + videoDuration)");
            SentryReplayEvent sentryReplayEvent = new SentryReplayEvent();
            sentryReplayEvent.setEventId(currentReplayId);
            sentryReplayEvent.setReplayId(currentReplayId);
            sentryReplayEvent.setSegmentId(segmentId);
            sentryReplayEvent.setTimestamp(dateTime);
            sentryReplayEvent.setReplayStartTimestamp(segmentTimestamp);
            sentryReplayEvent.setReplayType(replayType);
            sentryReplayEvent.setVideoFile(video);
            final ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = arrayList;
            RRWebMetaEvent rRWebMetaEvent = new RRWebMetaEvent();
            rRWebMetaEvent.setTimestamp(segmentTimestamp.getTime());
            rRWebMetaEvent.setHeight(height);
            rRWebMetaEvent.setWidth(width);
            arrayList2.add(rRWebMetaEvent);
            RRWebVideoEvent rRWebVideoEvent = new RRWebVideoEvent();
            rRWebVideoEvent.setTimestamp(segmentTimestamp.getTime());
            rRWebVideoEvent.setSegmentId(segmentId);
            rRWebVideoEvent.setDurationMs(videoDuration);
            rRWebVideoEvent.setFrameCount(frameCount);
            rRWebVideoEvent.setSize(video.length());
            rRWebVideoEvent.setFrameRate(frameRate);
            rRWebVideoEvent.setHeight(height);
            rRWebVideoEvent.setWidth(width);
            rRWebVideoEvent.setLeft(0);
            rRWebVideoEvent.setTop(0);
            arrayList2.add(rRWebVideoEvent);
            LinkedList linkedList = new LinkedList();
            for (Breadcrumb breadcrumb : breadcrumbs) {
                if (breadcrumb.getTimestamp().getTime() + BREADCRUMB_START_OFFSET >= segmentTimestamp.getTime() && breadcrumb.getTimestamp().getTime() < dateTime.getTime() && (rRWebEventConvert = options.getReplayController().getBreadcrumbConverter().convert(breadcrumb)) != null) {
                    arrayList2.add(rRWebEventConvert);
                    RRWebBreadcrumbEvent rRWebBreadcrumbEvent = rRWebEventConvert instanceof RRWebBreadcrumbEvent ? (RRWebBreadcrumbEvent) rRWebEventConvert : null;
                    if (Intrinsics.areEqual(rRWebBreadcrumbEvent != null ? rRWebBreadcrumbEvent.getCategory() : null, NotificationCompat.CATEGORY_NAVIGATION)) {
                        Map<String, Object> data = ((RRWebBreadcrumbEvent) rRWebEventConvert).getData();
                        Intrinsics.checkNotNull(data);
                        Object obj = data.get("to");
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                        linkedList.add((String) obj);
                    }
                }
            }
            if (screenAtStart != null && !Intrinsics.areEqual(CollectionsKt.firstOrNull((List) linkedList), screenAtStart)) {
                linkedList.addFirst(screenAtStart);
            }
            rotateEvents$sentry_android_replay_release(events, dateTime.getTime(), new Function1<RRWebEvent, Unit>() { // from class: io.sentry.android.replay.capture.CaptureStrategy$Companion$buildReplay$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RRWebEvent rRWebEvent) {
                    invoke2(rRWebEvent);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RRWebEvent event) {
                    Intrinsics.checkNotNullParameter(event, "event");
                    if (event.getTimestamp() >= segmentTimestamp.getTime()) {
                        arrayList.add(event);
                    }
                }
            });
            ReplayRecording replayRecording = new ReplayRecording();
            replayRecording.setSegmentId(Integer.valueOf(segmentId));
            replayRecording.setPayload(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: io.sentry.android.replay.capture.CaptureStrategy$Companion$buildReplay$lambda$6$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((RRWebEvent) t).getTimestamp()), Long.valueOf(((RRWebEvent) t2).getTimestamp()));
                }
            }));
            sentryReplayEvent.setUrls(linkedList);
            return new ReplaySegment.Created(sentryReplayEvent, replayRecording);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void rotateEvents$sentry_android_replay_release$default(Companion companion, LinkedList linkedList, long j, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                function1 = null;
            }
            companion.rotateEvents$sentry_android_replay_release(linkedList, j, function1);
        }

        public final void rotateEvents$sentry_android_replay_release(LinkedList<RRWebEvent> events, long until, Function1<? super RRWebEvent, Unit> callback) {
            Intrinsics.checkNotNullParameter(events, "events");
            synchronized (currentEventsLock) {
                RRWebEvent rRWebEventPeek = events.peek();
                while (rRWebEventPeek != null && rRWebEventPeek.getTimestamp() < until) {
                    if (callback != null) {
                        callback.invoke(rRWebEventPeek);
                    }
                    events.remove();
                    rRWebEventPeek = events.peek();
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: compiled from: CaptureStrategy.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "", "()V", "Created", "Failed", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Created;", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Failed;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class ReplaySegment {
        public static final int $stable = 0;

        public /* synthetic */ ReplaySegment(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: CaptureStrategy.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Failed;", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "()V", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Failed extends ReplaySegment {
            public static final int $stable = 0;
            public static final Failed INSTANCE = new Failed();

            private Failed() {
                super(null);
            }
        }

        private ReplaySegment() {
        }

        /* JADX INFO: compiled from: CaptureStrategy.kt */
        @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0019J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001e"}, d2 = {"Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Created;", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "replay", "Lio/sentry/SentryReplayEvent;", "recording", "Lio/sentry/ReplayRecording;", "(Lio/sentry/SentryReplayEvent;Lio/sentry/ReplayRecording;)V", "getRecording", "()Lio/sentry/ReplayRecording;", "getReplay", "()Lio/sentry/SentryReplayEvent;", "capture", "", "hub", "Lio/sentry/IHub;", "hint", "Lio/sentry/Hint;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "setSegmentId", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "toString", "", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final /* data */ class Created extends ReplaySegment {
            public static final int $stable = 8;
            private final ReplayRecording recording;
            private final SentryReplayEvent replay;

            public static /* synthetic */ Created copy$default(Created created, SentryReplayEvent sentryReplayEvent, ReplayRecording replayRecording, int i, Object obj) {
                if ((i & 1) != 0) {
                    sentryReplayEvent = created.replay;
                }
                if ((i & 2) != 0) {
                    replayRecording = created.recording;
                }
                return created.copy(sentryReplayEvent, replayRecording);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final SentryReplayEvent getReplay() {
                return this.replay;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final ReplayRecording getRecording() {
                return this.recording;
            }

            public final Created copy(SentryReplayEvent replay, ReplayRecording recording) {
                Intrinsics.checkNotNullParameter(replay, "replay");
                Intrinsics.checkNotNullParameter(recording, "recording");
                return new Created(replay, recording);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Created)) {
                    return false;
                }
                Created created = (Created) other;
                return Intrinsics.areEqual(this.replay, created.replay) && Intrinsics.areEqual(this.recording, created.recording);
            }

            public int hashCode() {
                return (this.replay.hashCode() * 31) + this.recording.hashCode();
            }

            public String toString() {
                return "Created(replay=" + this.replay + ", recording=" + this.recording + ')';
            }

            public final SentryReplayEvent getReplay() {
                return this.replay;
            }

            public final ReplayRecording getRecording() {
                return this.recording;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Created(SentryReplayEvent replay, ReplayRecording recording) {
                super(null);
                Intrinsics.checkNotNullParameter(replay, "replay");
                Intrinsics.checkNotNullParameter(recording, "recording");
                this.replay = replay;
                this.recording = recording;
            }

            public static /* synthetic */ void capture$default(Created created, IHub iHub, Hint hint, int i, Object obj) {
                if ((i & 2) != 0) {
                    hint = new Hint();
                }
                created.capture(iHub, hint);
            }

            public final void capture(IHub hub, Hint hint) {
                Intrinsics.checkNotNullParameter(hint, "hint");
                if (hub != null) {
                    SentryReplayEvent sentryReplayEvent = this.replay;
                    hint.setReplayRecording(this.recording);
                    Unit unit = Unit.INSTANCE;
                    hub.captureReplay(sentryReplayEvent, hint);
                }
            }

            public final void setSegmentId(int segmentId) {
                this.replay.setSegmentId(segmentId);
                List<? extends RRWebEvent> payload = this.recording.getPayload();
                if (payload != null) {
                    for (RRWebEvent rRWebEvent : payload) {
                        if (rRWebEvent instanceof RRWebVideoEvent) {
                            ((RRWebVideoEvent) rRWebEvent).setSegmentId(segmentId);
                        }
                    }
                }
            }
        }
    }
}
