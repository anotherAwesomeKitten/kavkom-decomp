package com.facebook.react.bridge;

import android.os.SystemClock;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactMarker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\r\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003&'(B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0007J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0007J\b\u0010\u0010\u001a\u00020\rH\u0007J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0007J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0007J\b\u0010\u0013\u001a\u00020\rH\u0007J2\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0007J*\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\"\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0018H\u0007J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u001a\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007J\"\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u001a\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u001cH\u0007J\"\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J1\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cH\u0007¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cH\u0003¢\u0006\u0002\u0010\"J\u0019\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001cH\u0083 R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/facebook/react/bridge/ReactMarker;", "", "<init>", "()V", "nativeReactMarkerQueue", "Ljava/util/Queue;", "Lcom/facebook/react/bridge/ReactMarker$ReactMarkerRecord;", "listeners", "", "Lcom/facebook/react/bridge/ReactMarker$MarkerListener;", "fabricMarkerListeners", "Lcom/facebook/react/bridge/ReactMarker$FabricMarkerListener;", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeListener", "clearMarkerListeners", "addFabricListener", "removeFabricListener", "clearFabricMarkerListeners", "logFabricMarker", "name", "Lcom/facebook/react/bridge/ReactMarkerConstants;", "tag", "", "instanceKey", "", "timestamp", "", "counter", "logMarker", "time", "(Lcom/facebook/react/bridge/ReactMarkerConstants;Ljava/lang/String;ILjava/lang/Long;)V", "notifyNativeMarker", "(Lcom/facebook/react/bridge/ReactMarkerConstants;Ljava/lang/Long;)V", "nativeLogMarker", "markerName", "markerTime", "ReactMarkerRecord", "MarkerListener", "FabricMarkerListener", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactMarker {
    public static final ReactMarker INSTANCE = new ReactMarker();
    private static final Queue<ReactMarkerRecord> nativeReactMarkerQueue = new ConcurrentLinkedQueue();
    private static final List<MarkerListener> listeners = new CopyOnWriteArrayList();
    private static final List<FabricMarkerListener> fabricMarkerListeners = new CopyOnWriteArrayList();

    /* JADX INFO: compiled from: ReactMarker.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/bridge/ReactMarker$MarkerListener;", "", "logMarker", "", "name", "Lcom/facebook/react/bridge/ReactMarkerConstants;", "tag", "", "instanceKey", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface MarkerListener {
        void logMarker(ReactMarkerConstants name, String tag, int instanceKey);
    }

    @JvmStatic
    private static final native void nativeLogMarker(String markerName, long markerTime);

    private ReactMarker() {
    }

    @JvmStatic
    public static final void addListener(MarkerListener markerListener) {
        Intrinsics.checkNotNullParameter(markerListener, "listener");
        List<MarkerListener> list = listeners;
        if (list.contains(markerListener)) {
            return;
        }
        list.add(markerListener);
    }

    @JvmStatic
    public static final void removeListener(MarkerListener markerListener) {
        Intrinsics.checkNotNullParameter(markerListener, "listener");
        listeners.remove(markerListener);
    }

    @JvmStatic
    public static final void clearMarkerListeners() {
        listeners.clear();
    }

    @JvmStatic
    public static final void addFabricListener(FabricMarkerListener fabricMarkerListener) {
        Intrinsics.checkNotNullParameter(fabricMarkerListener, "listener");
        List<FabricMarkerListener> list = fabricMarkerListeners;
        if (list.contains(fabricMarkerListener)) {
            return;
        }
        list.add(fabricMarkerListener);
    }

    @JvmStatic
    public static final void removeFabricListener(FabricMarkerListener fabricMarkerListener) {
        Intrinsics.checkNotNullParameter(fabricMarkerListener, "listener");
        fabricMarkerListeners.remove(fabricMarkerListener);
    }

    @JvmStatic
    public static final void clearFabricMarkerListeners() {
        fabricMarkerListeners.clear();
    }

    @JvmStatic
    public static final void logFabricMarker(ReactMarkerConstants name, String tag, int instanceKey, long timestamp, int counter) {
        Intrinsics.checkNotNullParameter(name, "name");
        Iterator<FabricMarkerListener> it = fabricMarkerListeners.iterator();
        while (it.hasNext()) {
            it.next().logFabricMarker(name, tag, instanceKey, timestamp, counter);
        }
    }

    @JvmStatic
    public static final void logFabricMarker(ReactMarkerConstants name, String tag, int instanceKey, long timestamp) {
        Intrinsics.checkNotNullParameter(name, "name");
        Iterator<FabricMarkerListener> it = fabricMarkerListeners.iterator();
        while (it.hasNext()) {
            it.next().logFabricMarker(name, tag, instanceKey, timestamp, 0);
        }
    }

    @JvmStatic
    public static final void logFabricMarker(ReactMarkerConstants name, String tag, int instanceKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        logFabricMarker(name, tag, instanceKey, SystemClock.uptimeMillis(), 0);
    }

    @JvmStatic
    public static final void logMarker(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, (String) null);
    }

    @JvmStatic
    public static final void logMarker(String name, int instanceKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, (String) null, instanceKey);
    }

    @JvmStatic
    public static final void logMarker(String name, String tag) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, tag, 0);
    }

    @JvmStatic
    public static final void logMarker(String name, String tag, int instanceKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(ReactMarkerConstants.valueOf(name), tag, instanceKey);
    }

    @JvmStatic
    public static final void logMarker(ReactMarkerConstants name) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, (String) null, 0);
    }

    @JvmStatic
    public static final void logMarker(ReactMarkerConstants name, int instanceKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, (String) null, instanceKey);
    }

    @JvmStatic
    public static final void logMarker(ReactMarkerConstants name, String tag) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, tag, 0);
    }

    @JvmStatic
    public static final void logMarker(ReactMarkerConstants name, long time) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, null, 0, Long.valueOf(time));
    }

    @JvmStatic
    public static final void logMarker(ReactMarkerConstants name, String tag, int instanceKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        logMarker(name, tag, instanceKey, null);
    }

    @JvmStatic
    public static final void logMarker(ReactMarkerConstants name, String tag, int instanceKey, Long time) {
        Intrinsics.checkNotNullParameter(name, "name");
        logFabricMarker(name, tag, instanceKey);
        Iterator<MarkerListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().logMarker(name, tag, instanceKey);
        }
        notifyNativeMarker(name, time);
    }

    @JvmStatic
    private static final void notifyNativeMarker(ReactMarkerConstants name, Long time) {
        if (!name.getHasMatchingNameMarker()) {
            return;
        }
        long jLongValue = time != null ? time.longValue() : SystemClock.uptimeMillis();
        if (ReactNativeJniCommonSoLoader.isInitialized()) {
            nativeLogMarker(name.name(), jLongValue);
            while (true) {
                ReactMarkerRecord reactMarkerRecordPoll = nativeReactMarkerQueue.poll();
                if (reactMarkerRecordPoll == null) {
                    return;
                } else {
                    nativeLogMarker(reactMarkerRecordPoll.getMarkerName(), reactMarkerRecordPoll.getMarkerTime());
                }
            }
        } else {
            nativeReactMarkerQueue.add(new ReactMarkerRecord(name.name(), jLongValue));
        }
    }

    /* JADX INFO: compiled from: ReactMarker.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/facebook/react/bridge/ReactMarker$ReactMarkerRecord;", "", "markerName", "", "markerTime", "", "<init>", "(Ljava/lang/String;J)V", "getMarkerName", "()Ljava/lang/String;", "getMarkerTime", "()J", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class ReactMarkerRecord {
        private final String markerName;
        private final long markerTime;

        public ReactMarkerRecord(String markerName, long j) {
            Intrinsics.checkNotNullParameter(markerName, "markerName");
            this.markerName = markerName;
            this.markerTime = j;
        }

        public final String getMarkerName() {
            return this.markerName;
        }

        public final long getMarkerTime() {
            return this.markerTime;
        }
    }

    /* JADX INFO: compiled from: ReactMarker.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J2\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/bridge/ReactMarker$FabricMarkerListener;", "", "logFabricMarker", "", "name", "Lcom/facebook/react/bridge/ReactMarkerConstants;", "tag", "", "instanceKey", "", "timestamp", "", "counter", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface FabricMarkerListener {
        void logFabricMarker(ReactMarkerConstants name, String tag, int instanceKey, long timestamp);

        default void logFabricMarker(ReactMarkerConstants name, String tag, int instanceKey, long timestamp, int counter) {
            Intrinsics.checkNotNullParameter(name, "name");
            logFabricMarker(name, tag, instanceKey, timestamp);
        }
    }
}
