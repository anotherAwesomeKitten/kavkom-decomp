package com.facebook.react.devsupport.perfmonitor;

import android.app.Activity;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.interfaces.TracingState;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PerfMonitorOverlayManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0013\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0006J\u0006\u0010\u0016\u001a\u00020\u0006J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorOverlayManager;", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorUpdateListener;", "devHelper", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorDevHelper;", "onRequestOpenDevTools", "Lkotlin/Function0;", "", "<init>", "(Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorDevHelper;Lkotlin/jvm/functions/Function0;)V", ViewProps.ENABLED, "", "isEnabled", "()Z", "view", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorOverlayView;", "tracingState", "Lcom/facebook/react/devsupport/interfaces/TracingState;", "perfIssueCount", "", "enable", "disable", "startBackgroundTrace", "stopBackgroundTrace", "onRecordingStateChanged", SentryThread.JsonKeys.STATE, "onPerfIssueAdded", "name", "", "handleRecordingButtonPress", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PerfMonitorOverlayManager implements PerfMonitorUpdateListener {
    private final PerfMonitorDevHelper devHelper;
    private boolean enabled;
    private final Function0<Unit> onRequestOpenDevTools;
    private int perfIssueCount;
    private TracingState tracingState;
    private PerfMonitorOverlayView view;

    /* JADX INFO: compiled from: PerfMonitorOverlayManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TracingState.values().length];
            try {
                iArr[TracingState.ENABLEDINBACKGROUNDMODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TracingState.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TracingState.ENABLEDINCDPMODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PerfMonitorOverlayManager(PerfMonitorDevHelper devHelper, Function0<Unit> onRequestOpenDevTools) {
        Intrinsics.checkNotNullParameter(devHelper, "devHelper");
        Intrinsics.checkNotNullParameter(onRequestOpenDevTools, "onRequestOpenDevTools");
        this.devHelper = devHelper;
        this.onRequestOpenDevTools = onRequestOpenDevTools;
        this.tracingState = TracingState.ENABLEDINCDPMODE;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void enable() {
        if (this.enabled) {
            return;
        }
        this.enabled = true;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.perfmonitor.PerfMonitorOverlayManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PerfMonitorOverlayManager.enable$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enable$lambda$0(PerfMonitorOverlayManager perfMonitorOverlayManager) {
        Activity currentActivity = perfMonitorOverlayManager.devHelper.getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        if (perfMonitorOverlayManager.view == null) {
            perfMonitorOverlayManager.view = new PerfMonitorOverlayView(currentActivity, new PerfMonitorOverlayManager$enable$1$1(perfMonitorOverlayManager));
        }
        PerfMonitorOverlayView perfMonitorOverlayView = perfMonitorOverlayManager.view;
        if (perfMonitorOverlayView != null) {
            perfMonitorOverlayView.show();
        }
    }

    public final void disable() {
        this.enabled = false;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.perfmonitor.PerfMonitorOverlayManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PerfMonitorOverlayManager.disable$lambda$1(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void disable$lambda$1(PerfMonitorOverlayManager perfMonitorOverlayManager) {
        PerfMonitorOverlayView perfMonitorOverlayView = perfMonitorOverlayManager.view;
        if (perfMonitorOverlayView != null) {
            perfMonitorOverlayView.hide();
        }
    }

    public final void startBackgroundTrace() {
        PerfMonitorInspectorTarget inspectorTarget;
        if (this.enabled && (inspectorTarget = this.devHelper.getInspectorTarget()) != null) {
            inspectorTarget.resumeBackgroundTrace();
            onRecordingStateChanged(inspectorTarget.getTracingState());
        }
    }

    public final void stopBackgroundTrace() {
        PerfMonitorInspectorTarget inspectorTarget;
        if (this.enabled && (inspectorTarget = this.devHelper.getInspectorTarget()) != null) {
            inspectorTarget.stopBackgroundTrace();
            onRecordingStateChanged(inspectorTarget.getTracingState());
        }
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorUpdateListener
    public void onRecordingStateChanged(final TracingState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.tracingState = state;
        if (state != TracingState.DISABLED) {
            this.perfIssueCount = 0;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.perfmonitor.PerfMonitorOverlayManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PerfMonitorOverlayManager.onRecordingStateChanged$lambda$4(this.f$0, state);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onRecordingStateChanged$lambda$4(PerfMonitorOverlayManager perfMonitorOverlayManager, TracingState tracingState) {
        PerfMonitorOverlayView perfMonitorOverlayView = perfMonitorOverlayManager.view;
        if (perfMonitorOverlayView != null) {
            perfMonitorOverlayView.updateRecordingState(tracingState);
        }
        PerfMonitorOverlayView perfMonitorOverlayView2 = perfMonitorOverlayManager.view;
        if (perfMonitorOverlayView2 != null) {
            perfMonitorOverlayView2.updatePerfIssueCount(perfMonitorOverlayManager.perfIssueCount);
        }
        PerfMonitorOverlayView perfMonitorOverlayView3 = perfMonitorOverlayManager.view;
        if (perfMonitorOverlayView3 != null) {
            perfMonitorOverlayView3.show();
        }
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorUpdateListener
    public void onPerfIssueAdded(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.perfmonitor.PerfMonitorOverlayManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PerfMonitorOverlayManager.onPerfIssueAdded$lambda$5(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPerfIssueAdded$lambda$5(PerfMonitorOverlayManager perfMonitorOverlayManager) {
        PerfMonitorOverlayView perfMonitorOverlayView = perfMonitorOverlayManager.view;
        if (perfMonitorOverlayView != null) {
            int i = perfMonitorOverlayManager.perfIssueCount + 1;
            perfMonitorOverlayManager.perfIssueCount = i;
            perfMonitorOverlayView.updatePerfIssueCount(i);
        }
        PerfMonitorOverlayView perfMonitorOverlayView2 = perfMonitorOverlayManager.view;
        if (perfMonitorOverlayView2 != null) {
            perfMonitorOverlayView2.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleRecordingButtonPress() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.tracingState.ordinal()];
        if (i == 1) {
            PerfMonitorInspectorTarget inspectorTarget = this.devHelper.getInspectorTarget();
            if (inspectorTarget == null || inspectorTarget.pauseAndAnalyzeBackgroundTrace()) {
                return;
            }
            this.onRequestOpenDevTools.invoke();
            return;
        }
        if (i != 2) {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
        } else {
            PerfMonitorInspectorTarget inspectorTarget2 = this.devHelper.getInspectorTarget();
            if (inspectorTarget2 != null) {
                inspectorTarget2.resumeBackgroundTrace();
            }
        }
    }
}
