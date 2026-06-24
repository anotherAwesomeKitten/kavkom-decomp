package io.sentry;

import com.facebook.hermes.intl.Constants;
import com.google.android.gms.common.Scopes;
import io.sentry.cache.EnvelopeCache;

/* JADX INFO: loaded from: classes2.dex */
public enum DataCategory {
    All("__all__"),
    Default(Constants.COLLATION_DEFAULT),
    Error("error"),
    Session(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE),
    Attachment("attachment"),
    Monitor("monitor"),
    Profile(Scopes.PROFILE),
    MetricBucket("metric_bucket"),
    Transaction("transaction"),
    Replay("replay"),
    Span("span"),
    Security("security"),
    UserReport("user_report"),
    Unknown("unknown");

    private final String category;

    DataCategory(String str) {
        this.category = str;
    }

    public String getCategory() {
        return this.category;
    }
}
