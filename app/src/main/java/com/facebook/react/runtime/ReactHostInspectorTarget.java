package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.devsupport.interfaces.TracingState;
import com.facebook.react.devsupport.perfmonitor.PerfMonitorInspectorTarget;
import com.facebook.react.devsupport.perfmonitor.PerfMonitorUpdateListener;
import com.facebook.soloader.SoLoader;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.Closeable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactHostInspectorTarget.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0001\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0002%&B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0082 J\t\u0010\u0011\u001a\u00020\u0012H\u0086 J\t\u0010\u0013\u001a\u00020\u0014H\u0086 J\t\u0010\u0015\u001a\u00020\u0014H\u0086 J\t\u0010\u0016\u001a\u00020\u0012H\u0086 J\t\u0010\u0017\u001a\u00020\u0018H\u0086 J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\rH\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0012H\u0016J\b\u0010\u001f\u001a\u00020\u0012H\u0016J\u000e\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020\u0012H\u0016J\u0006\u0010$\u001a\u00020\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/facebook/react/runtime/ReactHostInspectorTarget;", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorInspectorTarget;", "Ljava/io/Closeable;", "reactHostImpl", "Lcom/facebook/react/runtime/ReactHostImpl;", "<init>", "(Lcom/facebook/react/runtime/ReactHostImpl;)V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getMHybridData$annotations", "()V", "perfMonitorListeners", "", "Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorUpdateListener;", "initHybrid", "executor", "Ljava/util/concurrent/Executor;", "sendDebuggerResumeCommand", "", "startBackgroundTrace", "", "stopAndMaybeEmitBackgroundTrace", "stopAndDiscardBackgroundTrace", "tracingStateAsInt", "", "getTracingState", "Lcom/facebook/react/devsupport/interfaces/TracingState;", "addPerfMonitorListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "pauseAndAnalyzeBackgroundTrace", "resumeBackgroundTrace", "stopBackgroundTrace", "handleNativePerfIssueAdded", "name", "", "close", "isValid", "Companion", "UIThreadConditionalSyncExecutor", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@UnstableReactNativeAPI
public final class ReactHostInspectorTarget implements PerfMonitorInspectorTarget, Closeable {
    private static final Companion Companion = new Companion(null);
    private final HybridData mHybridData;
    private final Set<PerfMonitorUpdateListener> perfMonitorListeners;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    private final native HybridData initHybrid(ReactHostImpl reactHostImpl, Executor executor);

    public final native void sendDebuggerResumeCommand();

    public final native boolean startBackgroundTrace();

    public final native void stopAndDiscardBackgroundTrace();

    public final native boolean stopAndMaybeEmitBackgroundTrace();

    public final native int tracingStateAsInt();

    public ReactHostInspectorTarget(ReactHostImpl reactHostImpl) {
        Intrinsics.checkNotNullParameter(reactHostImpl, "reactHostImpl");
        this.mHybridData = initHybrid(reactHostImpl, new UIThreadConditionalSyncExecutor());
        this.perfMonitorListeners = new LinkedHashSet();
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorInspectorTargetBinding
    public TracingState getTracingState() {
        return TracingState.getEntries().get(tracingStateAsInt());
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorEventDispatcher
    public void addPerfMonitorListener(PerfMonitorUpdateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.perfMonitorListeners.add(listener);
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorInspectorTargetBinding
    public boolean pauseAndAnalyzeBackgroundTrace() {
        boolean zStopAndMaybeEmitBackgroundTrace = stopAndMaybeEmitBackgroundTrace();
        Iterator<T> it = this.perfMonitorListeners.iterator();
        while (it.hasNext()) {
            ((PerfMonitorUpdateListener) it.next()).onRecordingStateChanged(TracingState.DISABLED);
        }
        return zStopAndMaybeEmitBackgroundTrace;
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorInspectorTargetBinding
    public void resumeBackgroundTrace() {
        startBackgroundTrace();
        Iterator<T> it = this.perfMonitorListeners.iterator();
        while (it.hasNext()) {
            ((PerfMonitorUpdateListener) it.next()).onRecordingStateChanged(TracingState.ENABLEDINBACKGROUNDMODE);
        }
    }

    @Override // com.facebook.react.devsupport.perfmonitor.PerfMonitorInspectorTargetBinding
    public void stopBackgroundTrace() {
        stopAndDiscardBackgroundTrace();
        Iterator<T> it = this.perfMonitorListeners.iterator();
        while (it.hasNext()) {
            ((PerfMonitorUpdateListener) it.next()).onRecordingStateChanged(TracingState.DISABLED);
        }
    }

    public final void handleNativePerfIssueAdded(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Iterator<T> it = this.perfMonitorListeners.iterator();
        while (it.hasNext()) {
            ((PerfMonitorUpdateListener) it.next()).onPerfIssueAdded(name);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mHybridData.resetNative();
    }

    public final boolean isValid() {
        return this.mHybridData.isValid();
    }

    /* JADX INFO: compiled from: ReactHostInspectorTarget.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/runtime/ReactHostInspectorTarget$Companion;", "", "<init>", "()V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("rninstance");
    }

    /* JADX INFO: compiled from: ReactHostInspectorTarget.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/facebook/react/runtime/ReactHostInspectorTarget$UIThreadConditionalSyncExecutor;", "Ljava/util/concurrent/Executor;", "<init>", "()V", "execute", "", "command", "Ljava/lang/Runnable;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class UIThreadConditionalSyncExecutor implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable command) {
            Intrinsics.checkNotNullParameter(command, "command");
            if (UiThreadUtil.isOnUiThread()) {
                command.run();
            } else {
                UiThreadUtil.runOnUiThread(command);
            }
        }
    }
}
