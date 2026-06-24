package io.sentry;

/* JADX INFO: loaded from: classes2.dex */
public interface IPerformanceSnapshotCollector extends IPerformanceCollector {
    void collect(PerformanceCollectionData performanceCollectionData);

    void setup();
}
