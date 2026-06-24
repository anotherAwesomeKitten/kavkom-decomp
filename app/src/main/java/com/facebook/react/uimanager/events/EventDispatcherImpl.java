package com.facebook.react.uimanager.events;

import android.util.LongSparseArray;
import android.view.Choreographer;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.systrace.Systrace;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: EventDispatcherImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\t\b\u0000\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0003@ABB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010)\u001a\u00020*2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0016J\b\u0010,\u001a\u00020*H\u0016J\b\u0010-\u001a\u00020*H\u0002J\u0010\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u00020\u0019H\u0016J\u0010\u00100\u001a\u00020*2\u0006\u0010/\u001a\u00020\u0019H\u0016J\u0010\u00101\u001a\u00020*2\u0006\u0010/\u001a\u00020\u001bH\u0016J\u0010\u00102\u001a\u00020*2\u0006\u0010/\u001a\u00020\u001bH\u0016J\b\u00103\u001a\u00020*H\u0016J\b\u00104\u001a\u00020*H\u0016J\b\u00105\u001a\u00020*H\u0016J\b\u00106\u001a\u00020*H\u0017J\b\u00107\u001a\u00020*H\u0002J\b\u00108\u001a\u00020*H\u0002J \u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\f2\u0006\u0010<\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u0010H\u0002J\u0014\u0010>\u001a\u00020*2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002J\b\u0010?\u001a\u00020*H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00060\u0012R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00060\u001dR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00150!X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"R\u000e\u0010#\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/facebook/react/uimanager/events/EventDispatcherImpl;", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "eventsStagingLock", "", "eventsToDispatchLock", "eventCookieToLastEventIdx", "Landroid/util/LongSparseArray;", "", "eventNameToEventId", "", "", "", "dispatchEventsRunnable", "Lcom/facebook/react/uimanager/events/EventDispatcherImpl$DispatchEventsRunnable;", "eventStaging", "Ljava/util/ArrayList;", "Lcom/facebook/react/uimanager/events/Event;", "Lkotlin/collections/ArrayList;", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/facebook/react/uimanager/events/EventDispatcherListener;", "postEventDispatchListeners", "Lcom/facebook/react/uimanager/events/BatchEventDispatchedListener;", "currentFrameCallback", "Lcom/facebook/react/uimanager/events/EventDispatcherImpl$ScheduleDispatchFrameCallback;", "hasDispatchScheduledCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "eventsToDispatch", "", "[Lcom/facebook/react/uimanager/events/Event;", "eventsToDispatchSize", "reactEventEmitter", "Lcom/facebook/react/uimanager/events/EventEmitterImpl;", "nextEventTypeId", "hasDispatchScheduled", "", "dispatchEvent", "", NotificationCompat.CATEGORY_EVENT, "dispatchAllEvents", "maybePostFrameCallbackFromNonUI", "addListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeListener", "addBatchEventDispatchedListener", "removeBatchEventDispatchedListener", "onHostResume", "onHostPause", "onHostDestroy", "onCatalystInstanceDestroyed", "stopFrameCallback", "moveStagedEventsToDispatchQueue", "getEventCookie", "", "viewTag", "eventName", "coalescingKey", "addEventToEventsToDispatch", "clearEventsToDispatch", "ScheduleDispatchFrameCallback", "DispatchEventsRunnable", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class EventDispatcherImpl implements EventDispatcher, LifecycleEventListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Comparator<Event<?>> EVENT_COMPARATOR = new Comparator() { // from class: com.facebook.react.uimanager.events.EventDispatcherImpl$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return EventDispatcherImpl.EVENT_COMPARATOR$lambda$5((Event) obj, (Event) obj2);
        }
    };
    private final ScheduleDispatchFrameCallback currentFrameCallback;
    private final DispatchEventsRunnable dispatchEventsRunnable;
    private final LongSparseArray<Integer> eventCookieToLastEventIdx;
    private final Map<String, Short> eventNameToEventId;
    private final ArrayList<Event<?>> eventStaging;
    private final Object eventsStagingLock;
    private Event<?>[] eventsToDispatch;
    private final Object eventsToDispatchLock;
    private int eventsToDispatchSize;
    private volatile boolean hasDispatchScheduled;
    private final AtomicInteger hasDispatchScheduledCount;
    private final CopyOnWriteArrayList<EventDispatcherListener> listeners;
    private short nextEventTypeId;
    private final CopyOnWriteArrayList<BatchEventDispatchedListener> postEventDispatchListeners;
    private final ReactApplicationContext reactContext;
    private final EventEmitterImpl reactEventEmitter;

    public EventDispatcherImpl(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.eventsStagingLock = new Object();
        this.eventsToDispatchLock = new Object();
        this.eventCookieToLastEventIdx = new LongSparseArray<>();
        this.eventNameToEventId = new LinkedHashMap();
        this.dispatchEventsRunnable = new DispatchEventsRunnable();
        this.eventStaging = new ArrayList<>();
        this.listeners = new CopyOnWriteArrayList<>();
        this.postEventDispatchListeners = new CopyOnWriteArrayList<>();
        this.currentFrameCallback = new ScheduleDispatchFrameCallback();
        this.hasDispatchScheduledCount = new AtomicInteger();
        this.eventsToDispatch = new Event[16];
        reactContext.addLifecycleEventListener(this);
        this.reactEventEmitter = new EventEmitterImpl(reactContext);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchEvent(Event<?> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!event.getIsInitialized()) {
            throw new IllegalArgumentException("Dispatched event hasn't been initialized".toString());
        }
        Iterator<EventDispatcherListener> it = this.listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        synchronized (this.eventsStagingLock) {
            this.eventStaging.add(event);
            Systrace.startAsyncFlow(0L, event.getEventName(), event.getUniqueID());
            Unit unit = Unit.INSTANCE;
        }
        maybePostFrameCallbackFromNonUI();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchAllEvents() {
        maybePostFrameCallbackFromNonUI();
    }

    private final void maybePostFrameCallbackFromNonUI() {
        this.currentFrameCallback.maybePostFromNonUI();
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
        maybePostFrameCallbackFromNonUI();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    @Deprecated(message = "Private API, should only be used when the concrete implementation is known.")
    public void onCatalystInstanceDestroyed() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcherImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.stopFrameCallback();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.currentFrameCallback.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveStagedEventsToDispatchQueue() {
        synchronized (this.eventsStagingLock) {
            synchronized (this.eventsToDispatchLock) {
                int size = this.eventStaging.size();
                for (int i = 0; i < size; i++) {
                    Event<?> event = this.eventStaging.get(i);
                    Intrinsics.checkNotNullExpressionValue(event, "get(...)");
                    Event<?> event2 = event;
                    if (!event2.canCoalesce()) {
                        addEventToEventsToDispatch(event2);
                    } else {
                        long eventCookie = getEventCookie(event2.getViewTag(), event2.getEventName(), event2.getCoalescingKey());
                        Integer num = this.eventCookieToLastEventIdx.get(eventCookie);
                        Event<?> event3 = null;
                        if (num == null) {
                            this.eventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.eventsToDispatchSize));
                        } else {
                            Event<?> event4 = this.eventsToDispatch[num.intValue()];
                            if (event4 == null) {
                                throw new IllegalStateException("Required value was null.".toString());
                            }
                            Event<?> eventCoalesce = event2.coalesce(event4);
                            if (eventCoalesce != event4) {
                                this.eventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.eventsToDispatchSize));
                                this.eventsToDispatch[num.intValue()] = null;
                                event3 = event4;
                                event2 = eventCoalesce;
                            } else {
                                event3 = event2;
                                event2 = null;
                            }
                        }
                        if (event2 != null) {
                            addEventToEventsToDispatch(event2);
                        }
                        if (event3 != null) {
                            event3.dispose();
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            this.eventStaging.clear();
            Unit unit2 = Unit.INSTANCE;
        }
    }

    private final long getEventCookie(int viewTag, String eventName, short coalescingKey) {
        short sShortValue;
        Short sh = this.eventNameToEventId.get(eventName);
        if (sh != null) {
            sShortValue = sh.shortValue();
        } else {
            short s = this.nextEventTypeId;
            this.nextEventTypeId = (short) (s + 1);
            this.eventNameToEventId.put(eventName, Short.valueOf(s));
            sShortValue = s;
        }
        return INSTANCE.getEventCookie(viewTag, sShortValue, coalescingKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: EventDispatcherImpl.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bJ\u0006\u0010\u000e\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/uimanager/events/EventDispatcherImpl$ScheduleDispatchFrameCallback;", "Landroid/view/Choreographer$FrameCallback;", "<init>", "(Lcom/facebook/react/uimanager/events/EventDispatcherImpl;)V", "isPosted", "", "shouldStop", "doFrame", "", "frameTimeNanos", "", "stop", "maybePost", "post", "maybePostFromNonUI", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    final class ScheduleDispatchFrameCallback implements Choreographer.FrameCallback {
        private volatile boolean isPosted;
        private boolean shouldStop;

        public ScheduleDispatchFrameCallback() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long frameTimeNanos) {
            UiThreadUtil.assertOnUiThread();
            if (this.shouldStop) {
                this.isPosted = false;
            } else {
                post();
            }
            Systrace.beginSection(0L, "ScheduleDispatchFrameCallback");
            try {
                EventDispatcherImpl.this.moveStagedEventsToDispatchQueue();
                if (!EventDispatcherImpl.this.hasDispatchScheduled) {
                    EventDispatcherImpl.this.hasDispatchScheduled = true;
                    Systrace.startAsyncFlow(0L, "ScheduleDispatchFrameCallback", EventDispatcherImpl.this.hasDispatchScheduledCount.get());
                    EventDispatcherImpl.this.reactContext.runOnJSQueueThread(EventDispatcherImpl.this.dispatchEventsRunnable);
                }
            } finally {
                Systrace.endSection(0L);
            }
        }

        public final void stop() {
            this.shouldStop = true;
        }

        public final void maybePost() {
            if (this.isPosted) {
                return;
            }
            this.isPosted = true;
            post();
        }

        public final void post() {
            ReactChoreographer.INSTANCE.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, EventDispatcherImpl.this.currentFrameCallback);
        }

        public final void maybePostFromNonUI() {
            if (this.isPosted) {
                return;
            }
            if (!EventDispatcherImpl.this.reactContext.isOnUiQueueThread()) {
                EventDispatcherImpl.this.reactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcherImpl$ScheduleDispatchFrameCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.maybePost();
                    }
                });
            } else {
                maybePost();
            }
        }
    }

    /* JADX INFO: compiled from: EventDispatcherImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/uimanager/events/EventDispatcherImpl$DispatchEventsRunnable;", "Ljava/lang/Runnable;", "<init>", "(Lcom/facebook/react/uimanager/events/EventDispatcherImpl;)V", "run", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class DispatchEventsRunnable implements Runnable {
        public DispatchEventsRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Systrace.beginSection(0L, "DispatchEventsRunnable");
            try {
                Systrace.endAsyncFlow(0L, "ScheduleDispatchFrameCallback", EventDispatcherImpl.this.hasDispatchScheduledCount.getAndIncrement());
                EventDispatcherImpl.this.hasDispatchScheduled = false;
                Object obj = EventDispatcherImpl.this.eventsToDispatchLock;
                EventDispatcherImpl eventDispatcherImpl = EventDispatcherImpl.this;
                synchronized (obj) {
                    if (eventDispatcherImpl.eventsToDispatchSize > 0) {
                        if (eventDispatcherImpl.eventsToDispatchSize > 1) {
                            Arrays.sort(eventDispatcherImpl.eventsToDispatch, 0, eventDispatcherImpl.eventsToDispatchSize, EventDispatcherImpl.EVENT_COMPARATOR);
                        }
                        int i = eventDispatcherImpl.eventsToDispatchSize;
                        for (int i2 = 0; i2 < i; i2++) {
                            Event event = eventDispatcherImpl.eventsToDispatch[i2];
                            if (event != null) {
                                Systrace.endAsyncFlow(0L, event.getEventName(), event.getUniqueID());
                                event.dispatchModern(eventDispatcherImpl.reactEventEmitter);
                                event.dispose();
                            }
                        }
                        eventDispatcherImpl.clearEventsToDispatch();
                        eventDispatcherImpl.eventCookieToLastEventIdx.clear();
                    }
                    Unit unit = Unit.INSTANCE;
                }
                Iterator it = EventDispatcherImpl.this.postEventDispatchListeners.iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0L);
            }
        }
    }

    private final void addEventToEventsToDispatch(Event<?> event) {
        int i = this.eventsToDispatchSize;
        Event<?>[] eventArr = this.eventsToDispatch;
        if (i == eventArr.length) {
            Object[] objArrCopyOf = Arrays.copyOf(eventArr, eventArr.length * 2);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
            this.eventsToDispatch = (Event[]) objArrCopyOf;
        }
        Event<?>[] eventArr2 = this.eventsToDispatch;
        int i2 = this.eventsToDispatchSize;
        this.eventsToDispatchSize = i2 + 1;
        eventArr2[i2] = event;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearEventsToDispatch() {
        Arrays.fill(this.eventsToDispatch, 0, this.eventsToDispatchSize, (Object) null);
        this.eventsToDispatchSize = 0;
    }

    /* JADX INFO: compiled from: EventDispatcherImpl.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/uimanager/events/EventDispatcherImpl$Companion;", "", "<init>", "()V", "EVENT_COMPARATOR", "Ljava/util/Comparator;", "Lcom/facebook/react/uimanager/events/Event;", "getEventCookie", "", "viewTag", "", "eventTypeId", "", "coalescingKey", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long getEventCookie(int viewTag, short eventTypeId, short coalescingKey) {
            return ((((long) eventTypeId) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((long) viewTag) | ((((long) coalescingKey) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48);
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int EVENT_COMPARATOR$lambda$5(Event event, Event event2) {
        if (event == null && event2 == null) {
            return 0;
        }
        if (event == null) {
            return -1;
        }
        if (event2 == null) {
            return 1;
        }
        long timestampMs = event.getTimestampMs() - event2.getTimestampMs();
        if (timestampMs == 0) {
            return 0;
        }
        return timestampMs < 0 ? -1 : 1;
    }
}
