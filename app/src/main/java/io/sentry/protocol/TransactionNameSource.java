package io.sentry.protocol;

import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public enum TransactionNameSource {
    CUSTOM,
    URL,
    ROUTE,
    VIEW,
    COMPONENT,
    TASK;

    public String apiName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
