package io.sentry;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public enum MonitorScheduleType {
    CRONTAB,
    INTERVAL;

    public String apiName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
