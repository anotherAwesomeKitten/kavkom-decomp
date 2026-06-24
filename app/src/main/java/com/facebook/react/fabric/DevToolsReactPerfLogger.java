package com.facebook.react.fabric;

import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DevToolsReactPerfLogger.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0004\u001a\u001b\u001c\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nJ*\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J2\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0006H\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0007H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/fabric/DevToolsReactPerfLogger;", "Lcom/facebook/react/bridge/ReactMarker$FabricMarkerListener;", "<init>", "()V", "fabricCommitMarkers", "", "", "Lcom/facebook/react/fabric/DevToolsReactPerfLogger$FabricCommitPoint;", "devToolsReactPerfLoggerListeners", "", "Lcom/facebook/react/fabric/DevToolsReactPerfLogger$DevToolsReactPerfLoggerListener;", "addDevToolsReactPerfLoggerListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeDevToolsReactPerfLoggerListener", "logFabricMarker", "name", "Lcom/facebook/react/bridge/ReactMarkerConstants;", "tag", "", "instanceKey", "timestamp", "", "counter", "onFabricCommitEnd", "commitPoint", "DevToolsReactPerfLoggerListener", "FabricCommitPointData", "FabricCommitPoint", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DevToolsReactPerfLogger implements ReactMarker.FabricMarkerListener {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final LongStreamingStats streamingCommitStats = new LongStreamingStats();

    @Deprecated
    public static final LongStreamingStats streamingLayoutStats = new LongStreamingStats();

    @Deprecated
    public static final LongStreamingStats streamingDiffStats = new LongStreamingStats();

    @Deprecated
    public static final LongStreamingStats streamingTransactionEndStats = new LongStreamingStats();

    @Deprecated
    public static final LongStreamingStats streamingBatchExecutionStats = new LongStreamingStats();
    private final Map<Integer, FabricCommitPoint> fabricCommitMarkers = new LinkedHashMap();
    private final List<DevToolsReactPerfLoggerListener> devToolsReactPerfLoggerListeners = new ArrayList();

    /* JADX INFO: compiled from: DevToolsReactPerfLogger.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/fabric/DevToolsReactPerfLogger$DevToolsReactPerfLoggerListener;", "", "onFabricCommitEnd", "", "commitPoint", "Lcom/facebook/react/fabric/DevToolsReactPerfLogger$FabricCommitPoint;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface DevToolsReactPerfLoggerListener {
        void onFabricCommitEnd(FabricCommitPoint commitPoint);
    }

    /* JADX INFO: compiled from: DevToolsReactPerfLogger.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/facebook/react/fabric/DevToolsReactPerfLogger$FabricCommitPointData;", "", "timeStamp", "", "counter", "", "<init>", "(JI)V", "getTimeStamp", "()J", "getCounter", "()I", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class FabricCommitPointData {
        private final int counter;
        private final long timeStamp;

        public FabricCommitPointData(long j, int i) {
            this.timeStamp = j;
            this.counter = i;
        }

        public final int getCounter() {
            return this.counter;
        }

        public final long getTimeStamp() {
            return this.timeStamp;
        }
    }

    /* JADX INFO: compiled from: DevToolsReactPerfLogger.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b.\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0011J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010<\u001a\u00020=H\u0016R\u0011\u0010\u0002\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\bR\u0011\u0010\u001b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u001d\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\bR\u0011\u0010\u001f\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b \u0010\bR\u0011\u0010!\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\"\u0010\bR\u0011\u0010#\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b$\u0010\bR\u0011\u0010%\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b)\u0010\bR\u0011\u0010*\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b+\u0010\bR\u0011\u0010,\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b-\u0010\bR\u0011\u0010.\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b/\u0010\bR\u0011\u00100\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b1\u0010\bR\u0011\u00102\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b3\u0010\bR\u0011\u00104\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b5\u0010\bR\u0011\u00106\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b7\u0010\bR\u0011\u00108\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b9\u0010\bR\u0011\u0010:\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b;\u0010\b¨\u0006>"}, d2 = {"Lcom/facebook/react/fabric/DevToolsReactPerfLogger$FabricCommitPoint;", "", "commitNumber", "", "<init>", "(I)V", "", "getCommitNumber", "()J", "points", "", "Lcom/facebook/react/bridge/ReactMarkerConstants;", "Lcom/facebook/react/fabric/DevToolsReactPerfLogger$FabricCommitPointData;", "addPoint", "", "key", "data", "addPoint$ReactAndroid_release", "getTimestamp", "marker", "getCounter", "commitStart", "getCommitStart", "commitEnd", "getCommitEnd", "finishTransactionStart", "getFinishTransactionStart", "finishTransactionEnd", "getFinishTransactionEnd", "diffStart", "getDiffStart", "diffEnd", "getDiffEnd", "layoutStart", "getLayoutStart", "layoutEnd", "getLayoutEnd", "affectedLayoutNodesCount", "getAffectedLayoutNodesCount", "()I", "affectedLayoutNodesCountTime", "getAffectedLayoutNodesCountTime", "batchExecutionStart", "getBatchExecutionStart", "batchExecutionEnd", "getBatchExecutionEnd", "updateUIMainThreadStart", "getUpdateUIMainThreadStart", "updateUIMainThreadEnd", "getUpdateUIMainThreadEnd", "commitDuration", "getCommitDuration", "layoutDuration", "getLayoutDuration", "diffDuration", "getDiffDuration", "transactionEndDuration", "getTransactionEndDuration", "batchExecutionDuration", "getBatchExecutionDuration", "toString", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class FabricCommitPoint {
        private final long commitNumber;
        private final Map<ReactMarkerConstants, FabricCommitPointData> points = new LinkedHashMap();

        public FabricCommitPoint(int i) {
            this.commitNumber = i;
        }

        public final long getCommitNumber() {
            return this.commitNumber;
        }

        public final void addPoint$ReactAndroid_release(ReactMarkerConstants key, FabricCommitPointData data) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(data, "data");
            this.points.put(key, data);
        }

        private final long getTimestamp(ReactMarkerConstants marker) {
            FabricCommitPointData fabricCommitPointData = this.points.get(marker);
            if (fabricCommitPointData != null) {
                return fabricCommitPointData.getTimeStamp();
            }
            return -1L;
        }

        private final int getCounter(ReactMarkerConstants marker) {
            FabricCommitPointData fabricCommitPointData = this.points.get(marker);
            if (fabricCommitPointData != null) {
                return fabricCommitPointData.getCounter();
            }
            return 0;
        }

        public final long getCommitStart() {
            return getTimestamp(ReactMarkerConstants.FABRIC_COMMIT_START);
        }

        public final long getCommitEnd() {
            return getTimestamp(ReactMarkerConstants.FABRIC_COMMIT_END);
        }

        public final long getFinishTransactionStart() {
            return getTimestamp(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_START);
        }

        public final long getFinishTransactionEnd() {
            return getTimestamp(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_END);
        }

        public final long getDiffStart() {
            return getTimestamp(ReactMarkerConstants.FABRIC_DIFF_START);
        }

        public final long getDiffEnd() {
            return getTimestamp(ReactMarkerConstants.FABRIC_DIFF_END);
        }

        public final long getLayoutStart() {
            return getTimestamp(ReactMarkerConstants.FABRIC_LAYOUT_START);
        }

        public final long getLayoutEnd() {
            return getTimestamp(ReactMarkerConstants.FABRIC_LAYOUT_END);
        }

        public final int getAffectedLayoutNodesCount() {
            return getCounter(ReactMarkerConstants.FABRIC_LAYOUT_AFFECTED_NODES);
        }

        public final long getAffectedLayoutNodesCountTime() {
            return getTimestamp(ReactMarkerConstants.FABRIC_LAYOUT_AFFECTED_NODES);
        }

        public final long getBatchExecutionStart() {
            return getTimestamp(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START);
        }

        public final long getBatchExecutionEnd() {
            return getTimestamp(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END);
        }

        public final long getUpdateUIMainThreadStart() {
            return getTimestamp(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_START);
        }

        public final long getUpdateUIMainThreadEnd() {
            return getTimestamp(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END);
        }

        public final long getCommitDuration() {
            return getCommitEnd() - getCommitStart();
        }

        public final long getLayoutDuration() {
            return getLayoutEnd() - getLayoutStart();
        }

        public final long getDiffDuration() {
            return getDiffEnd() - getDiffStart();
        }

        public final long getTransactionEndDuration() {
            return getFinishTransactionEnd() - getFinishTransactionStart();
        }

        public final long getBatchExecutionDuration() {
            return getBatchExecutionEnd() - getBatchExecutionStart();
        }

        public String toString() {
            return "FabricCommitPoint{mCommitNumber=" + this.commitNumber + ", mPoints=" + this.points + "}";
        }
    }

    public final void addDevToolsReactPerfLoggerListener(DevToolsReactPerfLoggerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.devToolsReactPerfLoggerListeners.add(listener);
    }

    public final void removeDevToolsReactPerfLoggerListener(DevToolsReactPerfLoggerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.devToolsReactPerfLoggerListeners.remove(listener);
    }

    @Override // com.facebook.react.bridge.ReactMarker.FabricMarkerListener
    public void logFabricMarker(ReactMarkerConstants name, String tag, int instanceKey, long timestamp) {
        Intrinsics.checkNotNullParameter(name, "name");
        logFabricMarker(name, tag, instanceKey, timestamp, 0);
    }

    @Override // com.facebook.react.bridge.ReactMarker.FabricMarkerListener
    public void logFabricMarker(ReactMarkerConstants name, String tag, int instanceKey, long timestamp, int counter) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (Companion.isFabricCommitMarker(name)) {
            FabricCommitPoint fabricCommitPoint = this.fabricCommitMarkers.get(Integer.valueOf(instanceKey));
            if (fabricCommitPoint == null) {
                fabricCommitPoint = new FabricCommitPoint(instanceKey);
                this.fabricCommitMarkers.put(Integer.valueOf(instanceKey), fabricCommitPoint);
            }
            fabricCommitPoint.addPoint$ReactAndroid_release(name, new FabricCommitPointData(timestamp, counter));
            if (name != ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END || timestamp <= 0) {
                return;
            }
            onFabricCommitEnd(fabricCommitPoint);
            this.fabricCommitMarkers.remove(Integer.valueOf(instanceKey));
        }
    }

    private final void onFabricCommitEnd(FabricCommitPoint commitPoint) {
        Iterator<DevToolsReactPerfLoggerListener> it = this.devToolsReactPerfLoggerListeners.iterator();
        while (it.hasNext()) {
            it.next().onFabricCommitEnd(commitPoint);
        }
    }

    /* JADX INFO: compiled from: DevToolsReactPerfLogger.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u0010\u0010\u0004\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/fabric/DevToolsReactPerfLogger$Companion;", "", "<init>", "()V", "streamingCommitStats", "Lcom/facebook/react/fabric/LongStreamingStats;", "streamingLayoutStats", "streamingDiffStats", "streamingTransactionEndStats", "streamingBatchExecutionStats", "isFabricCommitMarker", "", "name", "Lcom/facebook/react/bridge/ReactMarkerConstants;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isFabricCommitMarker(ReactMarkerConstants name) {
            return name == ReactMarkerConstants.FABRIC_COMMIT_START || name == ReactMarkerConstants.FABRIC_COMMIT_END || name == ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_START || name == ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_END || name == ReactMarkerConstants.FABRIC_DIFF_START || name == ReactMarkerConstants.FABRIC_DIFF_END || name == ReactMarkerConstants.FABRIC_LAYOUT_START || name == ReactMarkerConstants.FABRIC_LAYOUT_END || name == ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START || name == ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END || name == ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_START || name == ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END || name == ReactMarkerConstants.FABRIC_LAYOUT_AFFECTED_NODES;
        }
    }
}
