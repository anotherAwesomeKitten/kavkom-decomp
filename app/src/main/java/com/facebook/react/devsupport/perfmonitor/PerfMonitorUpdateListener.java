package com.facebook.react.devsupport.perfmonitor;

import com.facebook.react.devsupport.interfaces.TracingState;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;

/* JADX INFO: compiled from: PerfMonitorUpdateListener.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/devsupport/perfmonitor/PerfMonitorUpdateListener;", "", "onRecordingStateChanged", "", SentryThread.JsonKeys.STATE, "Lcom/facebook/react/devsupport/interfaces/TracingState;", "onPerfIssueAdded", "name", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface PerfMonitorUpdateListener {
    void onPerfIssueAdded(String name);

    void onRecordingStateChanged(TracingState state);
}
