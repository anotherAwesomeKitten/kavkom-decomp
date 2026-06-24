package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.SystemClock;
import com.facebook.react.uimanager.events.Event;
import io.sentry.Session;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Event.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 :*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002:\u00029:B\t\b\u0014¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0003\u0010\u0007B\u0019\b\u0014\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0003\u0010\tJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u0006H\u0005J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0004J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0004J\b\u0010\u001b\u001a\u00020\u000bH\u0016J\u001c\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00002\f\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0000H\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001aH\u0016J\u0006\u0010!\u001a\u00020\u001aJ\b\u0010\"\u001a\u00020#H&J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020,H\u0017J\n\u0010-\u001a\u0004\u0018\u00010.H\u0014J\u000f\u0010/\u001a\u0004\u0018\u00010.H\u0000¢\u0006\u0002\b0J\b\u00101\u001a\u00020\u0006H\u0014J\r\u00102\u001a\u00020\u0006H\u0000¢\u0006\u0002\b3J\b\u00104\u001a\u00020\u000bH\u0014J\r\u00105\u001a\u00020\u000bH\u0000¢\u0006\u0002\b6J\u0010\u00107\u001a\u00020\u001a2\u0006\u0010+\u001a\u000208H\u0016R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010$\u001a\u00020#8G¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010'\u001a\u0004\u0018\u00010\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006;"}, d2 = {"Lcom/facebook/react/uimanager/events/Event;", "T", "", "<init>", "()V", "viewTag", "", "(I)V", "surfaceId", "(II)V", "value", "", "isInitialized", "()Z", "getSurfaceId", "()I", "getViewTag", "", "timestampMs", "getTimestampMs", "()J", "uniqueID", "getUniqueID", "eventAnimationDriverMatchSpecCached", "Lcom/facebook/react/uimanager/events/Event$EventAnimationDriverMatchSpec;", Session.JsonKeys.INIT, "", "canCoalesce", "coalesce", "otherEvent", "getCoalescingKey", "", "onDispose", "dispose", "getEventName", "", "eventName", "internal_getEventNameCompat", "()Ljava/lang/String;", "eventAnimationDriverMatchSpec", "getEventAnimationDriverMatchSpec", "()Lcom/facebook/react/uimanager/events/Event$EventAnimationDriverMatchSpec;", "dispatch", "rctEventEmitter", "Lcom/facebook/react/uimanager/events/RCTEventEmitter;", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "internal_getEventData", "internal_getEventData$ReactAndroid_release", "getEventCategory", "internal_getEventCategory", "internal_getEventCategory$ReactAndroid_release", "experimental_isSynchronous", "internal_experimental_isSynchronous", "internal_experimental_isSynchronous$ReactAndroid_release", "dispatchModern", "Lcom/facebook/react/uimanager/events/RCTModernEventEmitter;", "EventAnimationDriverMatchSpec", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class Event<T extends Event<T>> {
    private static final Companion Companion = new Companion(null);
    private static int uniqueIdCounter;
    private EventAnimationDriverMatchSpec eventAnimationDriverMatchSpecCached;
    private boolean isInitialized;
    private int surfaceId;
    private long timestampMs;
    private final int uniqueID;
    private int viewTag;

    /* JADX INFO: compiled from: Event.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/uimanager/events/Event$EventAnimationDriverMatchSpec;", "", "match", "", "viewTagRhs", "", "eventNameRhs", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface EventAnimationDriverMatchSpec {
        boolean match(int viewTagRhs, String eventNameRhs);
    }

    public boolean canCoalesce() {
        return true;
    }

    /* JADX INFO: renamed from: experimental_isSynchronous */
    protected boolean getSynchronous() {
        return false;
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    protected int getEventCategory() {
        return 2;
    }

    /* JADX INFO: renamed from: getEventData */
    protected WritableMap getExtraData() {
        return null;
    }

    public abstract String getEventName();

    public void onDispose() {
    }

    /* JADX INFO: renamed from: isInitialized, reason: from getter */
    public final boolean getIsInitialized() {
        return this.isInitialized;
    }

    public final int getSurfaceId() {
        return this.surfaceId;
    }

    public final int getViewTag() {
        return this.viewTag;
    }

    public final long getTimestampMs() {
        return this.timestampMs;
    }

    public final int getUniqueID() {
        return this.uniqueID;
    }

    protected Event() {
        int i = uniqueIdCounter;
        uniqueIdCounter = i + 1;
        this.uniqueID = i;
    }

    @Deprecated(message = "Use constructor with explicit surfaceId instead", replaceWith = @ReplaceWith(expression = "Event(surfaceId, viewTag)", imports = {}))
    protected Event(int i) {
        int i2 = uniqueIdCounter;
        uniqueIdCounter = i2 + 1;
        this.uniqueID = i2;
        init(i);
    }

    protected Event(int i, int i2) {
        int i3 = uniqueIdCounter;
        uniqueIdCounter = i3 + 1;
        this.uniqueID = i3;
        init(i, i2);
    }

    @Deprecated(message = "Use version with explicit surfaceId instead", replaceWith = @ReplaceWith(expression = "init(surfaceId, viewTag)", imports = {}))
    protected final void init(int i) {
        init(-1, i);
    }

    protected final void init(int i, int i2, long j) {
        this.surfaceId = i;
        this.viewTag = i2;
        this.timestampMs = j;
        this.isInitialized = true;
    }

    protected final void init(int i, int i2) {
        init(i, i2, SystemClock.uptimeMillis());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Event<?> coalesce(Event<?> otherEvent) {
        return this.timestampMs >= (otherEvent != null ? otherEvent.timestampMs : 0L) ? this : otherEvent;
    }

    public final void dispose() {
        this.isInitialized = false;
        onDispose();
    }

    public final String internal_getEventNameCompat() {
        return getEventName();
    }

    public EventAnimationDriverMatchSpec getEventAnimationDriverMatchSpec() {
        if (this.eventAnimationDriverMatchSpecCached == null) {
            this.eventAnimationDriverMatchSpecCached = new EventAnimationDriverMatchSpec(this) { // from class: com.facebook.react.uimanager.events.Event$eventAnimationDriverMatchSpec$1
                final /* synthetic */ Event<T> this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.facebook.react.uimanager.events.Event.EventAnimationDriverMatchSpec
                public boolean match(int viewTagRhs, String eventNameRhs) {
                    Intrinsics.checkNotNullParameter(eventNameRhs, "eventNameRhs");
                    return this.this$0.getViewTag() == viewTagRhs && Intrinsics.areEqual(this.this$0.internal_getEventNameCompat(), eventNameRhs);
                }
            };
        }
        return this.eventAnimationDriverMatchSpecCached;
    }

    @Deprecated(message = "Prefer to override getEventData instead")
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        Intrinsics.checkNotNullParameter(rctEventEmitter, "rctEventEmitter");
        rctEventEmitter.receiveEvent(this.viewTag, internal_getEventNameCompat(), getExtraData());
    }

    public final WritableMap internal_getEventData$ReactAndroid_release() {
        return getExtraData();
    }

    public final int internal_getEventCategory$ReactAndroid_release() {
        return getEventCategory();
    }

    public final boolean internal_experimental_isSynchronous$ReactAndroid_release() {
        return getSynchronous();
    }

    public void dispatchModern(RCTModernEventEmitter rctEventEmitter) {
        Intrinsics.checkNotNullParameter(rctEventEmitter, "rctEventEmitter");
        int i = this.surfaceId;
        if (i != -1) {
            rctEventEmitter.receiveEvent(i, this.viewTag, internal_getEventNameCompat(), canCoalesce(), getCoalescingKey(), getExtraData(), getEventCategory());
        } else {
            dispatch(rctEventEmitter);
        }
    }

    /* JADX INFO: compiled from: Event.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/uimanager/events/Event$Companion;", "", "<init>", "()V", "uniqueIdCounter", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
