package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;
import io.sentry.protocol.Message;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventEmitterImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001J\"\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0017J*\u0010\f\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J \u0010\u0014\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0017J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u0002JB\u0010\f\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001c\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/uimanager/events/EventEmitterImpl;", "Lcom/facebook/react/uimanager/events/RCTModernEventEmitter;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "legacyEventEmitter", "Lcom/facebook/react/uimanager/events/RCTEventEmitter;", "fabricEventEmitter", "registerFabricEventEmitter", "", "eventEmitter", "receiveEvent", "targetTag", "", "eventName", "", Message.JsonKeys.PARAMS, "Lcom/facebook/react/bridge/WritableMap;", "surfaceId", "receiveTouches", "touches", "Lcom/facebook/react/bridge/WritableArray;", "changedIndices", "ensureLegacyEventEmitter", "canCoalesceEvent", "", "customCoalesceKey", "category", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class EventEmitterImpl implements RCTModernEventEmitter {
    private static final String TAG = "ReactEventEmitter";
    private RCTModernEventEmitter fabricEventEmitter;
    private RCTEventEmitter legacyEventEmitter;
    private final ReactApplicationContext reactContext;

    public EventEmitterImpl(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
    }

    public final void registerFabricEventEmitter(RCTModernEventEmitter eventEmitter) {
        this.fabricEventEmitter = eventEmitter;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    @Deprecated(message = "Please use RCTModernEventEmitter instead", replaceWith = @ReplaceWith(expression = "RCTModernEventEmitter.receiveEvent(surfaceId, targetTag, eventName, params)", imports = {}))
    public void receiveEvent(int targetTag, String eventName, WritableMap params) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        receiveEvent(-1, targetTag, eventName, params);
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int surfaceId, int targetTag, String eventName, WritableMap params) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        receiveEvent(surfaceId, targetTag, eventName, false, 0, params, 2);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    @Deprecated(message = "Dispatch the TouchEvent using [EventDispatcher] instead")
    public void receiveTouches(String eventName, WritableArray touches, WritableArray changedIndices) {
        RCTEventEmitter rCTEventEmitterEnsureLegacyEventEmitter;
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(touches, "touches");
        Intrinsics.checkNotNullParameter(changedIndices, "changedIndices");
        if (touches.size() <= 0) {
            throw new IllegalStateException("Check failed.");
        }
        ReadableMap map = touches.getMap(0);
        if (ViewUtil.getUIManagerType(map != null ? map.getInt(TouchesHelper.TARGET_KEY) : 0) != 1 || (rCTEventEmitterEnsureLegacyEventEmitter = ensureLegacyEventEmitter()) == null) {
            return;
        }
        rCTEventEmitterEnsureLegacyEventEmitter.receiveTouches(eventName, touches, changedIndices);
    }

    private final RCTEventEmitter ensureLegacyEventEmitter() {
        if (this.legacyEventEmitter == null) {
            if (this.reactContext.hasActiveReactInstance()) {
                this.legacyEventEmitter = (RCTEventEmitter) this.reactContext.getJSModule(RCTEventEmitter.class);
            } else {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot get RCTEventEmitter without active Catalyst instance!"));
            }
        }
        return this.legacyEventEmitter;
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int surfaceId, int targetTag, String eventName, boolean canCoalesceEvent, int customCoalesceKey, WritableMap params, int category) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        int uIManagerType = ViewUtil.getUIManagerType(targetTag, surfaceId);
        if (uIManagerType == 1) {
            RCTEventEmitter rCTEventEmitterEnsureLegacyEventEmitter = ensureLegacyEventEmitter();
            if (rCTEventEmitterEnsureLegacyEventEmitter != null) {
                rCTEventEmitterEnsureLegacyEventEmitter.receiveEvent(targetTag, eventName, params);
                return;
            }
            return;
        }
        if (uIManagerType != 2) {
            return;
        }
        RCTModernEventEmitter rCTModernEventEmitter = this.fabricEventEmitter;
        if (rCTModernEventEmitter == null) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("No fabricEventEmitter registered, cannot dispatch event"));
        } else {
            rCTModernEventEmitter.receiveEvent(surfaceId, targetTag, eventName, canCoalesceEvent, customCoalesceKey, params, category);
        }
    }
}
