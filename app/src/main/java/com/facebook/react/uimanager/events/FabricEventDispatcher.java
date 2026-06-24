package com.facebook.react.uimanager.events;

import android.os.Handler;
import android.view.Choreographer;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.systrace.Systrace;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FabricEventDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u0000 )2\u00020\u00012\u00020\u0002:\u0002()B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0016J\u0014\u0010\u001a\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\rH\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\rH\u0016J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u000fH\u0016J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u000fH\u0016J\b\u0010\"\u001a\u00020\u0017H\u0016J\b\u0010#\u001a\u00020\u0017H\u0016J\b\u0010$\u001a\u00020\u0017H\u0016J\u0006\u0010%\u001a\u00020\u0017J\b\u0010&\u001a\u00020\u0017H\u0017J\b\u0010'\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00060\u0011R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/facebook/react/uimanager/events/FabricEventDispatcher;", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "fabricEventEmitter", "Lcom/facebook/react/uimanager/events/RCTModernEventEmitter;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;Lcom/facebook/react/uimanager/events/RCTModernEventEmitter;)V", "eventEmitter", "Lcom/facebook/react/uimanager/events/EventEmitterImpl;", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/facebook/react/uimanager/events/EventDispatcherListener;", "postEventDispatchListeners", "Lcom/facebook/react/uimanager/events/BatchEventDispatchedListener;", "currentFrameCallback", "Lcom/facebook/react/uimanager/events/FabricEventDispatcher$ScheduleDispatchFrameCallback;", "isDispatchScheduled", "", "dispatchEventsRunnable", "Ljava/lang/Runnable;", "dispatchEvent", "", NotificationCompat.CATEGORY_EVENT, "Lcom/facebook/react/uimanager/events/Event;", "dispatchSynchronous", "dispatchAllEvents", "scheduleDispatchOfBatchedEvents", "addListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeListener", "addBatchEventDispatchedListener", "removeBatchEventDispatchedListener", "onHostResume", "onHostPause", "onHostDestroy", "invalidate", "onCatalystInstanceDestroyed", "cancelDispatchOfBatchedEvents", "ScheduleDispatchFrameCallback", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FabricEventDispatcher implements EventDispatcher, LifecycleEventListener {
    private static final Companion Companion = new Companion(null);
    private static final Handler uiThreadHandler = UiThreadUtil.getUiThreadHandler();
    private final ScheduleDispatchFrameCallback currentFrameCallback;
    private final Runnable dispatchEventsRunnable;
    private final EventEmitterImpl eventEmitter;
    private boolean isDispatchScheduled;
    private final CopyOnWriteArrayList<EventDispatcherListener> listeners;
    private final CopyOnWriteArrayList<BatchEventDispatchedListener> postEventDispatchListeners;
    private final ReactApplicationContext reactContext;

    public FabricEventDispatcher(ReactApplicationContext reactContext, RCTModernEventEmitter fabricEventEmitter) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(fabricEventEmitter, "fabricEventEmitter");
        this.reactContext = reactContext;
        EventEmitterImpl eventEmitterImpl = new EventEmitterImpl(reactContext);
        this.eventEmitter = eventEmitterImpl;
        this.listeners = new CopyOnWriteArrayList<>();
        this.postEventDispatchListeners = new CopyOnWriteArrayList<>();
        this.currentFrameCallback = new ScheduleDispatchFrameCallback();
        this.dispatchEventsRunnable = new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FabricEventDispatcher.dispatchEventsRunnable$lambda$0(this.f$0);
            }
        };
        reactContext.addLifecycleEventListener(this);
        eventEmitterImpl.registerFabricEventEmitter(fabricEventEmitter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dispatchEventsRunnable$lambda$0(FabricEventDispatcher fabricEventDispatcher) {
        fabricEventDispatcher.isDispatchScheduled = false;
        Systrace.beginSection(0L, "BatchEventDispatchedListeners");
        try {
            Iterator<BatchEventDispatchedListener> it = fabricEventDispatcher.postEventDispatchListeners.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                it.next().onBatchEventDispatched();
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchEvent(Event<?> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Iterator<EventDispatcherListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        if (event.internal_experimental_isSynchronous$ReactAndroid_release()) {
            dispatchSynchronous(event);
        } else {
            event.dispatchModern(this.eventEmitter);
        }
        event.dispose();
        scheduleDispatchOfBatchedEvents();
    }

    private final void dispatchSynchronous(Event<?> event) {
        Systrace.beginSection(0L, "FabricEventDispatcher.dispatchSynchronous('" + event.getEventName() + "')");
        try {
            UIManager uIManager = UIManagerHelper.getUIManager(this.reactContext, 2);
            if (uIManager instanceof SynchronousEventReceiver) {
                ((SynchronousEventReceiver) uIManager).receiveEvent(event.getSurfaceId(), event.getViewTag(), event.getEventName(), event.canCoalesce(), event.internal_getEventData$ReactAndroid_release(), event.internal_getEventCategory$ReactAndroid_release(), true);
            } else {
                ReactSoftExceptionLogger.logSoftException("FabricEventDispatcher", new IllegalStateException("Fabric UIManager expected to implement SynchronousEventReceiver."));
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchAllEvents() {
        scheduleDispatchOfBatchedEvents();
    }

    private final void scheduleDispatchOfBatchedEvents() {
        if (ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            if (this.isDispatchScheduled) {
                return;
            }
            this.isDispatchScheduled = true;
            uiThreadHandler.postAtFrontOfQueue(this.dispatchEventsRunnable);
            return;
        }
        this.currentFrameCallback.maybeScheduleDispatchOfBatchedEvents();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addListener(EventDispatcherListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeListener(EventDispatcherListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addBatchEventDispatchedListener(BatchEventDispatchedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.postEventDispatchListeners.add(listener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.postEventDispatchListeners.remove(listener);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        scheduleDispatchOfBatchedEvents();
        if (ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            return;
        }
        this.currentFrameCallback.resume();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        cancelDispatchOfBatchedEvents();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        cancelDispatchOfBatchedEvents();
    }

    public final void invalidate() {
        this.eventEmitter.registerFabricEventEmitter(null);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.cancelDispatchOfBatchedEvents();
            }
        });
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    @Deprecated(message = "Private API, should only be used when the concrete implementation is known.")
    public void onCatalystInstanceDestroyed() {
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelDispatchOfBatchedEvents() {
        UiThreadUtil.assertOnUiThread();
        if (ReactNativeFeatureFlags.useOptimizedEventBatchingOnAndroid()) {
            this.isDispatchScheduled = false;
            uiThreadHandler.removeCallbacks(this.dispatchEventsRunnable);
        } else {
            this.currentFrameCallback.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: FabricEventDispatcher.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bJ\b\u0010\u000e\u001a\u00020\bH\u0002J\u0006\u0010\u000f\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/uimanager/events/FabricEventDispatcher$ScheduleDispatchFrameCallback;", "Landroid/view/Choreographer$FrameCallback;", "<init>", "(Lcom/facebook/react/uimanager/events/FabricEventDispatcher;)V", "isFrameCallbackDispatchScheduled", "", "shouldStop", "doFrame", "", "frameTimeNanos", "", "stop", "resume", "maybeDispatchBatchedEvents", "dispatchBatchedEvents", "maybeScheduleDispatchOfBatchedEvents", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    final class ScheduleDispatchFrameCallback implements Choreographer.FrameCallback {
        private volatile boolean isFrameCallbackDispatchScheduled;
        private boolean shouldStop;

        public ScheduleDispatchFrameCallback() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long frameTimeNanos) {
            UiThreadUtil.assertOnUiThread();
            if (this.shouldStop) {
                this.isFrameCallbackDispatchScheduled = false;
            } else {
                dispatchBatchedEvents();
            }
            Systrace.beginSection(0L, "BatchEventDispatchedListeners");
            try {
                Iterator it = FabricEventDispatcher.this.postEventDispatchListeners.iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0L);
            }
        }

        public final void stop() {
            this.shouldStop = true;
        }

        public final void resume() {
            this.shouldStop = false;
        }

        public final void maybeDispatchBatchedEvents() {
            if (this.isFrameCallbackDispatchScheduled) {
                return;
            }
            this.isFrameCallbackDispatchScheduled = true;
            dispatchBatchedEvents();
        }

        private final void dispatchBatchedEvents() {
            ReactChoreographer.INSTANCE.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, FabricEventDispatcher.this.currentFrameCallback);
        }

        public final void maybeScheduleDispatchOfBatchedEvents() {
            if (this.isFrameCallbackDispatchScheduled) {
                return;
            }
            if (!FabricEventDispatcher.this.reactContext.isOnUiQueueThread()) {
                FabricEventDispatcher.this.reactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher$ScheduleDispatchFrameCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.maybeDispatchBatchedEvents();
                    }
                });
            } else {
                maybeDispatchBatchedEvents();
            }
        }
    }

    /* JADX INFO: compiled from: FabricEventDispatcher.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/uimanager/events/FabricEventDispatcher$Companion;", "", "<init>", "()V", "uiThreadHandler", "Landroid/os/Handler;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
