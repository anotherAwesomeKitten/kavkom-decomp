package com.facebook.react.devsupport.perfmonitor;

import com.facebook.react.devsupport.interfaces.TracingState;
import kotlin.Metadata;

/* JADX INFO: compiled from: PerfMonitorInspectorTargetBinding.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorInspectorTargetBinding;", "", "getTracingState", "Lcom/facebook/react/devsupport/interfaces/TracingState;", "pauseAndAnalyzeBackgroundTrace", "", "resumeBackgroundTrace", "", "stopBackgroundTrace", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface PerfMonitorInspectorTargetBinding {
    TracingState getTracingState();

    boolean pauseAndAnalyzeBackgroundTrace();

    void resumeBackgroundTrace();

    void stopBackgroundTrace();
}
