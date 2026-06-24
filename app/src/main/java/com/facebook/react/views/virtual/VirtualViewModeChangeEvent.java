package com.facebook.react.views.virtual;

import android.graphics.Rect;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VirtualViewModeChangeEvent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0017J\b\u0010\u0015\u001a\u00020\u000bH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/views/virtual/VirtualViewModeChangeEvent;", "Lcom/facebook/react/uimanager/events/Event;", "surfaceId", "", "viewTag", "mode", "Lcom/facebook/react/views/virtual/VirtualViewMode;", "targetRect", "Landroid/graphics/Rect;", "thresholdRect", "synchronous", "", "<init>", "(IILcom/facebook/react/views/virtual/VirtualViewMode;Landroid/graphics/Rect;Landroid/graphics/Rect;Z)V", "targetRectAsMap", "Lcom/facebook/react/bridge/ReadableMap;", "thresholdRectAsMap", "getEventName", "", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "experimental_isSynchronous", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VirtualViewModeChangeEvent extends Event<VirtualViewModeChangeEvent> {
    private final VirtualViewMode mode;
    private final boolean synchronous;
    private final ReadableMap targetRectAsMap;
    private final ReadableMap thresholdRectAsMap;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VirtualViewModeChangeEvent(int i, int i2, VirtualViewMode mode, Rect targetRect, Rect thresholdRect, boolean z) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(targetRect, "targetRect");
        Intrinsics.checkNotNullParameter(thresholdRect, "thresholdRect");
        this.mode = mode;
        this.synchronous = z;
        this.targetRectAsMap = VirtualViewModeChangeEventKt.toReadableMap(targetRect);
        this.thresholdRectAsMap = VirtualViewModeChangeEventKt.toReadableMap(thresholdRect);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "modeChange";
    }

    @Override // com.facebook.react.uimanager.events.Event
    public WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("mode", this.mode.getValue());
        writableMapCreateMap.putMap("targetRect", this.targetRectAsMap);
        writableMapCreateMap.putMap("thresholdRect", this.thresholdRectAsMap);
        return writableMapCreateMap;
    }

    @Override // com.facebook.react.uimanager.events.Event
    /* JADX INFO: renamed from: experimental_isSynchronous, reason: from getter */
    protected boolean getSynchronous() {
        return this.synchronous;
    }
}
