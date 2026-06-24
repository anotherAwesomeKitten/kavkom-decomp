package com.facebook.react.internal.interop;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogger;
import com.facebook.react.uimanager.events.Event;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InteropEvent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0000\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000e\u001a\u00020\u0003H\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0014R\u0013\u0010\u0002\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u00058G¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/facebook/react/internal/interop/InteropEvent;", "Lcom/facebook/react/uimanager/events/Event;", "interopEventName", "", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "surfaceId", "", "viewTag", "<init>", "(Ljava/lang/String;Lcom/facebook/react/bridge/WritableMap;II)V", "eventName", "()Ljava/lang/String;", "()Lcom/facebook/react/bridge/WritableMap;", "getEventName", "getEventData", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InteropEvent extends Event<InteropEvent> {
    private static final Companion Companion = new Companion(null);
    private final WritableMap eventData;
    private final String interopEventName;

    /* JADX INFO: renamed from: eventName, reason: from getter */
    public final String getInteropEventName() {
        return this.interopEventName;
    }

    /* JADX INFO: renamed from: eventData, reason: from getter */
    public final WritableMap getEventData() {
        return this.eventData;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InteropEvent(String interopEventName, WritableMap writableMap, int i, int i2) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(interopEventName, "interopEventName");
        this.interopEventName = interopEventName;
        this.eventData = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return this.interopEventName;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return this.eventData;
    }

    /* JADX INFO: compiled from: InteropEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/internal/interop/InteropEvent$Companion;", "", "<init>", "()V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        LegacyArchitectureLogger.assertLegacyArchitecture$default("InteropEvent", null, 2, null);
    }
}
