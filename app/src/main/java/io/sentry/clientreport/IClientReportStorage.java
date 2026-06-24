package io.sentry.clientreport;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IClientReportStorage {
    void addCount(ClientReportKey clientReportKey, Long l);

    List<DiscardedEvent> resetCountsAndGet();
}
