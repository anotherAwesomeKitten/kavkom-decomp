package io.sentry.protocol;

import com.google.common.base.Ascii;
import io.sentry.DateUtils;
import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.Span;
import io.sentry.SpanId;
import io.sentry.SpanStatus;
import io.sentry.metrics.LocalMetricsAggregator;
import io.sentry.protocol.MeasurementValue;
import io.sentry.protocol.MetricSummary;
import io.sentry.protocol.SentryId;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class SentrySpan implements JsonUnknown, JsonSerializable {
    private Map<String, Object> data;
    private final String description;
    private final Map<String, MeasurementValue> measurements;
    private final Map<String, List<MetricSummary>> metricsSummaries;
    private final String op;
    private final String origin;
    private final SpanId parentSpanId;
    private final SpanId spanId;
    private final Double startTimestamp;
    private final SpanStatus status;
    private final Map<String, String> tags;
    private final Double timestamp;
    private final SentryId traceId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String DESCRIPTION = "description";
        public static final String MEASUREMENTS = "measurements";
        public static final String METRICS_SUMMARY = "_metrics_summary";
        public static final String OP = "op";
        public static final String ORIGIN = "origin";
        public static final String PARENT_SPAN_ID = "parent_span_id";
        public static final String SPAN_ID = "span_id";
        public static final String START_TIMESTAMP = "start_timestamp";
        public static final String STATUS = "status";
        public static final String TAGS = "tags";
        public static final String TIMESTAMP = "timestamp";
        public static final String TRACE_ID = "trace_id";
    }

    public SentrySpan(Span span) {
        this(span, span.getData());
    }

    public SentrySpan(Span span, Map<String, Object> map) {
        Objects.requireNonNull(span, "span is required");
        this.description = span.getDescription();
        this.op = span.getOperation();
        this.spanId = span.getSpanId();
        this.parentSpanId = span.getParentSpanId();
        this.traceId = span.getTraceId();
        this.status = span.getStatus();
        this.origin = span.getSpanContext().getOrigin();
        Map<String, String> mapNewConcurrentHashMap = CollectionUtils.newConcurrentHashMap(span.getTags());
        this.tags = mapNewConcurrentHashMap == null ? new ConcurrentHashMap<>() : mapNewConcurrentHashMap;
        Map<String, MeasurementValue> mapNewConcurrentHashMap2 = CollectionUtils.newConcurrentHashMap(span.getMeasurements());
        this.measurements = mapNewConcurrentHashMap2 == null ? new ConcurrentHashMap<>() : mapNewConcurrentHashMap2;
        this.timestamp = span.getFinishDate() == null ? null : Double.valueOf(DateUtils.nanosToSeconds(span.getStartDate().laterDateNanosTimestampByDiff(span.getFinishDate())));
        this.startTimestamp = Double.valueOf(DateUtils.nanosToSeconds(span.getStartDate().nanoTimestamp()));
        this.data = map;
        LocalMetricsAggregator localMetricsAggregator = span.getLocalMetricsAggregator();
        if (localMetricsAggregator != null) {
            this.metricsSummaries = localMetricsAggregator.getSummaries();
        } else {
            this.metricsSummaries = null;
        }
    }

    public SentrySpan(Double d, Double d2, SentryId sentryId, SpanId spanId, SpanId spanId2, String str, String str2, SpanStatus spanStatus, String str3, Map<String, String> map, Map<String, MeasurementValue> map2, Map<String, List<MetricSummary>> map3, Map<String, Object> map4) {
        this.startTimestamp = d;
        this.timestamp = d2;
        this.traceId = sentryId;
        this.spanId = spanId;
        this.parentSpanId = spanId2;
        this.op = str;
        this.description = str2;
        this.status = spanStatus;
        this.origin = str3;
        this.tags = map;
        this.measurements = map2;
        this.metricsSummaries = map3;
        this.data = map4;
    }

    public boolean isFinished() {
        return this.timestamp != null;
    }

    public Double getStartTimestamp() {
        return this.startTimestamp;
    }

    public Double getTimestamp() {
        return this.timestamp;
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public SpanId getParentSpanId() {
        return this.parentSpanId;
    }

    public String getOp() {
        return this.op;
    }

    public String getDescription() {
        return this.description;
    }

    public SpanStatus getStatus() {
        return this.status;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setData(Map<String, Object> map) {
        this.data = map;
    }

    public String getOrigin() {
        return this.origin;
    }

    public Map<String, MeasurementValue> getMeasurements() {
        return this.measurements;
    }

    public Map<String, List<MetricSummary>> getMetricsSummaries() {
        return this.metricsSummaries;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("start_timestamp").value(iLogger, doubleToBigDecimal(this.startTimestamp));
        if (this.timestamp != null) {
            objectWriter.name("timestamp").value(iLogger, doubleToBigDecimal(this.timestamp));
        }
        objectWriter.name("trace_id").value(iLogger, this.traceId);
        objectWriter.name("span_id").value(iLogger, this.spanId);
        if (this.parentSpanId != null) {
            objectWriter.name("parent_span_id").value(iLogger, this.parentSpanId);
        }
        objectWriter.name("op").value(this.op);
        if (this.description != null) {
            objectWriter.name("description").value(this.description);
        }
        if (this.status != null) {
            objectWriter.name("status").value(iLogger, this.status);
        }
        if (this.origin != null) {
            objectWriter.name("origin").value(iLogger, this.origin);
        }
        if (!this.tags.isEmpty()) {
            objectWriter.name("tags").value(iLogger, this.tags);
        }
        if (this.data != null) {
            objectWriter.name("data").value(iLogger, this.data);
        }
        if (!this.measurements.isEmpty()) {
            objectWriter.name("measurements").value(iLogger, this.measurements);
        }
        Map<String, List<MetricSummary>> map = this.metricsSummaries;
        if (map != null && !map.isEmpty()) {
            objectWriter.name("_metrics_summary").value(iLogger, this.metricsSummaries);
        }
        Map<String, Object> map2 = this.unknown;
        if (map2 != null) {
            for (String str : map2.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    private BigDecimal doubleToBigDecimal(Double d) {
        return BigDecimal.valueOf(d.doubleValue()).setScale(6, RoundingMode.DOWN);
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<SentrySpan> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // io.sentry.JsonDeserializer
        public SentrySpan deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            Double dValueOf = null;
            Map map = null;
            SentryId sentryIdDeserialize = null;
            SpanId spanIdDeserialize = null;
            Map map2 = null;
            String strNextStringOrNull = null;
            Double dValueOf2 = null;
            SpanId spanId = null;
            String strNextStringOrNull2 = null;
            SpanStatus spanStatus = null;
            String strNextStringOrNull3 = null;
            Map mapNextMapOfListOrNull = null;
            Map map3 = null;
            while (true) {
                ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
                Double d = dValueOf;
                Map map4 = map;
                SentryId sentryId = sentryIdDeserialize;
                SpanId spanId2 = spanIdDeserialize;
                if (objectReader.peek() != JsonToken.NAME) {
                    if (d == null) {
                        throw missingRequiredFieldException("start_timestamp", iLogger);
                    }
                    if (sentryId == null) {
                        throw missingRequiredFieldException("trace_id", iLogger);
                    }
                    if (spanId2 == null) {
                        throw missingRequiredFieldException("span_id", iLogger);
                    }
                    if (strNextStringOrNull == null) {
                        throw missingRequiredFieldException("op", iLogger);
                    }
                    Map map5 = map4 == null ? new HashMap() : map4;
                    if (map2 == null) {
                        map2 = new HashMap();
                    }
                    SentrySpan sentrySpan = new SentrySpan(d, dValueOf2, sentryId, spanId2, spanId, strNextStringOrNull, strNextStringOrNull2, spanStatus, strNextStringOrNull3, map5, map2, mapNextMapOfListOrNull, map3);
                    sentrySpan.setUnknown(concurrentHashMap2);
                    objectReader.endObject();
                    return sentrySpan;
                }
                String strNextName = objectReader.nextName();
                strNextName.hashCode();
                byte b = -1;
                switch (strNextName.hashCode()) {
                    case -2011840976:
                        if (strNextName.equals("span_id")) {
                            b = 0;
                        }
                        break;
                    case -1757797477:
                        if (strNextName.equals("parent_span_id")) {
                            b = 1;
                        }
                        break;
                    case -1724546052:
                        if (strNextName.equals("description")) {
                            b = 2;
                        }
                        break;
                    case -1526966919:
                        if (strNextName.equals("start_timestamp")) {
                            b = 3;
                        }
                        break;
                    case -1008619738:
                        if (strNextName.equals("origin")) {
                            b = 4;
                        }
                        break;
                    case -892481550:
                        if (strNextName.equals("status")) {
                            b = 5;
                        }
                        break;
                    case -682561045:
                        if (strNextName.equals("_metrics_summary")) {
                            b = 6;
                        }
                        break;
                    case -362243017:
                        if (strNextName.equals("measurements")) {
                            b = 7;
                        }
                        break;
                    case 3553:
                        if (strNextName.equals("op")) {
                            b = 8;
                        }
                        break;
                    case 3076010:
                        if (strNextName.equals("data")) {
                            b = 9;
                        }
                        break;
                    case 3552281:
                        if (strNextName.equals("tags")) {
                            b = 10;
                        }
                        break;
                    case 55126294:
                        if (strNextName.equals("timestamp")) {
                            b = Ascii.VT;
                        }
                        break;
                    case 1270300245:
                        if (strNextName.equals("trace_id")) {
                            b = Ascii.FF;
                        }
                        break;
                }
                switch (b) {
                    case 0:
                        spanIdDeserialize = new SpanId.Deserializer().deserialize(objectReader, iLogger);
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        break;
                    case 1:
                        spanId = (SpanId) objectReader.nextOrNull(iLogger, new SpanId.Deserializer());
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 2:
                        strNextStringOrNull2 = objectReader.nextStringOrNull();
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 3:
                        try {
                            dValueOf = objectReader.nextDoubleOrNull();
                        } catch (NumberFormatException unused) {
                            Date dateNextDateOrNull = objectReader.nextDateOrNull(iLogger);
                            dValueOf = dateNextDateOrNull != null ? Double.valueOf(DateUtils.dateToSeconds(dateNextDateOrNull)) : null;
                        }
                        concurrentHashMap = concurrentHashMap2;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 4:
                        strNextStringOrNull3 = objectReader.nextStringOrNull();
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 5:
                        spanStatus = (SpanStatus) objectReader.nextOrNull(iLogger, new SpanStatus.Deserializer());
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 6:
                        mapNextMapOfListOrNull = objectReader.nextMapOfListOrNull(iLogger, new MetricSummary.Deserializer());
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 7:
                        map2 = objectReader.nextMapOrNull(iLogger, new MeasurementValue.Deserializer());
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 8:
                        strNextStringOrNull = objectReader.nextStringOrNull();
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 9:
                        map3 = (Map) objectReader.nextObjectOrNull();
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 10:
                        map = (Map) objectReader.nextObjectOrNull();
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 11:
                        try {
                            dValueOf2 = objectReader.nextDoubleOrNull();
                        } catch (NumberFormatException unused2) {
                            Date dateNextDateOrNull2 = objectReader.nextDateOrNull(iLogger);
                            dValueOf2 = dateNextDateOrNull2 != null ? Double.valueOf(DateUtils.dateToSeconds(dateNextDateOrNull2)) : null;
                        }
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                    case 12:
                        sentryIdDeserialize = new SentryId.Deserializer().deserialize(objectReader, iLogger);
                        concurrentHashMap = concurrentHashMap2;
                        dValueOf = d;
                        map = map4;
                        spanIdDeserialize = spanId2;
                        break;
                    default:
                        concurrentHashMap = concurrentHashMap2 == null ? new ConcurrentHashMap() : concurrentHashMap2;
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        dValueOf = d;
                        map = map4;
                        sentryIdDeserialize = sentryId;
                        spanIdDeserialize = spanId2;
                        break;
                }
            }
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, illegalStateException);
            return illegalStateException;
        }
    }
}
