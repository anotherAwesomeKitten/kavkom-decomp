package com.facebook.react;

import android.view.KeyEvent;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ViewProps;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactAndroidHWInputDeviceHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ,\u0010\u0010\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/ReactAndroidHWInputDeviceHelper;", "", "<init>", "()V", "lastFocusedViewId", "", "handleKeyEvent", "", "ev", "Landroid/view/KeyEvent;", "context", "Lcom/facebook/react/bridge/ReactContext;", "onFocusChanged", "newFocusedView", "Landroid/view/View;", "clearFocus", "dispatchEvent", "eventType", "", "targetViewId", "eventKeyAction", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactAndroidHWInputDeviceHelper {
    private static final Companion Companion = new Companion(null);
    private static final Map<Integer, String> KEY_EVENTS_ACTIONS = MapsKt.mapOf(TuplesKt.to(23, "select"), TuplesKt.to(66, "select"), TuplesKt.to(62, "select"), TuplesKt.to(85, "playPause"), TuplesKt.to(89, "rewind"), TuplesKt.to(90, "fastForward"), TuplesKt.to(86, "stop"), TuplesKt.to(87, "next"), TuplesKt.to(88, "previous"), TuplesKt.to(19, "up"), TuplesKt.to(22, ViewProps.RIGHT), TuplesKt.to(20, "down"), TuplesKt.to(21, "left"), TuplesKt.to(165, "info"), TuplesKt.to(82, "menu"), TuplesKt.to(166, "channelUp"), TuplesKt.to(167, "channelDown"));
    private int lastFocusedViewId = -1;

    public final void handleKeyEvent(KeyEvent ev, ReactContext context) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        Intrinsics.checkNotNullParameter(context, "context");
        int keyCode = ev.getKeyCode();
        int action = ev.getAction();
        if (action == 1 || action == 0) {
            Map<Integer, String> map = KEY_EVENTS_ACTIONS;
            if (map.containsKey(Integer.valueOf(keyCode))) {
                dispatchEvent(context, map.get(Integer.valueOf(keyCode)), this.lastFocusedViewId, action);
            }
        }
    }

    public final void onFocusChanged(View newFocusedView, ReactContext context) {
        Intrinsics.checkNotNullParameter(newFocusedView, "newFocusedView");
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.lastFocusedViewId == newFocusedView.getId()) {
            return;
        }
        int i = this.lastFocusedViewId;
        if (i != -1) {
            dispatchEvent$default(this, context, "blur", i, 0, 8, null);
        }
        this.lastFocusedViewId = newFocusedView.getId();
        dispatchEvent$default(this, context, "focus", newFocusedView.getId(), 0, 8, null);
    }

    public final void clearFocus(ReactContext context) {
        ReactAndroidHWInputDeviceHelper reactAndroidHWInputDeviceHelper;
        Intrinsics.checkNotNullParameter(context, "context");
        int i = this.lastFocusedViewId;
        if (i != -1) {
            reactAndroidHWInputDeviceHelper = this;
            dispatchEvent$default(reactAndroidHWInputDeviceHelper, context, "blur", i, 0, 8, null);
        } else {
            reactAndroidHWInputDeviceHelper = this;
        }
        reactAndroidHWInputDeviceHelper.lastFocusedViewId = -1;
    }

    static /* synthetic */ void dispatchEvent$default(ReactAndroidHWInputDeviceHelper reactAndroidHWInputDeviceHelper, ReactContext reactContext, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        reactAndroidHWInputDeviceHelper.dispatchEvent(reactContext, str, i, i2);
    }

    private final void dispatchEvent(ReactContext context, String eventType, int targetViewId, int eventKeyAction) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("eventType", eventType);
        writableNativeMap.putInt("eventKeyAction", eventKeyAction);
        if (targetViewId != -1) {
            writableNativeMap.putInt("tag", targetViewId);
        }
        context.emitDeviceEvent("onHWKeyEvent", writableNativeMap);
    }

    /* JADX INFO: compiled from: ReactAndroidHWInputDeviceHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/ReactAndroidHWInputDeviceHelper$Companion;", "", "<init>", "()V", "KEY_EVENTS_ACTIONS", "", "", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
