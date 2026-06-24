package io.sentry;

import io.sentry.exception.InvalidSentryTraceHeaderException;
import io.sentry.protocol.SentryId;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryTraceHeader {
    public static final String SENTRY_TRACE_HEADER = "sentry-trace";
    private final Boolean sampled;
    private final SpanId spanId;
    private final SentryId traceId;

    public SentryTraceHeader(SentryId sentryId, SpanId spanId, Boolean bool) {
        this.traceId = sentryId;
        this.spanId = spanId;
        this.sampled = bool;
    }

    public SentryTraceHeader(String str) throws InvalidSentryTraceHeaderException {
        String[] strArrSplit = str.split("-", -1);
        if (strArrSplit.length < 2) {
            throw new InvalidSentryTraceHeaderException(str);
        }
        if (strArrSplit.length == 3) {
            this.sampled = Boolean.valueOf("1".equals(strArrSplit[2]));
        } else {
            this.sampled = null;
        }
        try {
            this.traceId = new SentryId(strArrSplit[0]);
            this.spanId = new SpanId(strArrSplit[1]);
        } catch (Throwable th) {
            throw new InvalidSentryTraceHeaderException(str, th);
        }
    }

    public String getName() {
        return SENTRY_TRACE_HEADER;
    }

    public String getValue() {
        Boolean bool = this.sampled;
        if (bool != null) {
            return String.format("%s-%s-%s", this.traceId, this.spanId, bool.booleanValue() ? "1" : "0");
        }
        return String.format("%s-%s", this.traceId, this.spanId);
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public Boolean isSampled() {
        return this.sampled;
    }
}
