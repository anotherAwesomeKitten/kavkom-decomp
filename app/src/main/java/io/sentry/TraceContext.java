package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class TraceContext implements JsonUnknown, JsonSerializable {
    private final String environment;
    private final String publicKey;
    private final String release;
    private final SentryId replayId;
    private final String sampleRate;
    private final String sampled;
    private final SentryId traceId;
    private final String transaction;
    private Map<String, Object> unknown;
    private final String userId;
    private final String userSegment;

    public static final class JsonKeys {
        public static final String ENVIRONMENT = "environment";
        public static final String PUBLIC_KEY = "public_key";
        public static final String RELEASE = "release";
        public static final String REPLAY_ID = "replay_id";
        public static final String SAMPLED = "sampled";
        public static final String SAMPLE_RATE = "sample_rate";
        public static final String TRACE_ID = "trace_id";
        public static final String TRANSACTION = "transaction";
        public static final String USER = "user";
        public static final String USER_ID = "user_id";
        public static final String USER_SEGMENT = "user_segment";
    }

    TraceContext(SentryId sentryId, String str) {
        this(sentryId, str, null, null, null, null, null, null, null);
    }

    TraceContext(SentryId sentryId, String str, String str2, String str3, String str4, String str5, String str6, String str7, SentryId sentryId2) {
        this(sentryId, str, str2, str3, str4, null, str5, str6, str7, sentryId2);
    }

    @Deprecated
    TraceContext(SentryId sentryId, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, SentryId sentryId2) {
        this.traceId = sentryId;
        this.publicKey = str;
        this.release = str2;
        this.environment = str3;
        this.userId = str4;
        this.userSegment = str5;
        this.transaction = str6;
        this.sampleRate = str7;
        this.sampled = str8;
        this.replayId = sentryId2;
    }

    private static String getUserId(SentryOptions sentryOptions, User user) {
        if (!sentryOptions.isSendDefaultPii() || user == null) {
            return null;
        }
        return user.getId();
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public String getRelease() {
        return this.release;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getUserId() {
        return this.userId;
    }

    @Deprecated
    public String getUserSegment() {
        return this.userSegment;
    }

    public String getTransaction() {
        return this.transaction;
    }

    public String getSampleRate() {
        return this.sampleRate;
    }

    public String getSampled() {
        return this.sampled;
    }

    public SentryId getReplayId() {
        return this.replayId;
    }

    @Deprecated
    private static final class TraceContextUser implements JsonUnknown {
        private String id;
        private String segment;
        private Map<String, Object> unknown;

        public static final class JsonKeys {
            public static final String ID = "id";
            public static final String SEGMENT = "segment";
        }

        /* synthetic */ TraceContextUser(String str, String str2, AnonymousClass1 anonymousClass1) {
            this(str, str2);
        }

        private TraceContextUser(String str, String str2) {
            this.id = str;
            this.segment = str2;
        }

        public String getId() {
            return this.id;
        }

        @Deprecated
        public String getSegment() {
            return this.segment;
        }

        @Override // io.sentry.JsonUnknown
        public Map<String, Object> getUnknown() {
            return this.unknown;
        }

        @Override // io.sentry.JsonUnknown
        public void setUnknown(Map<String, Object> map) {
            this.unknown = map;
        }

        public static final class Deserializer implements JsonDeserializer<TraceContextUser> {
            @Override // io.sentry.JsonDeserializer
            public TraceContextUser deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
                objectReader.beginObject();
                String strNextStringOrNull = null;
                String strNextStringOrNull2 = null;
                ConcurrentHashMap concurrentHashMap = null;
                while (objectReader.peek() == JsonToken.NAME) {
                    String strNextName = objectReader.nextName();
                    strNextName.hashCode();
                    if (strNextName.equals("id")) {
                        strNextStringOrNull = objectReader.nextStringOrNull();
                    } else if (strNextName.equals("segment")) {
                        strNextStringOrNull2 = objectReader.nextStringOrNull();
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                    }
                }
                TraceContextUser traceContextUser = new TraceContextUser(strNextStringOrNull, strNextStringOrNull2);
                traceContextUser.setUnknown(concurrentHashMap);
                objectReader.endObject();
                return traceContextUser;
            }
        }
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("trace_id").value(iLogger, this.traceId);
        objectWriter.name(JsonKeys.PUBLIC_KEY).value(this.publicKey);
        if (this.release != null) {
            objectWriter.name("release").value(this.release);
        }
        if (this.environment != null) {
            objectWriter.name("environment").value(this.environment);
        }
        if (this.userId != null) {
            objectWriter.name(JsonKeys.USER_ID).value(this.userId);
        }
        if (this.userSegment != null) {
            objectWriter.name(JsonKeys.USER_SEGMENT).value(this.userSegment);
        }
        if (this.transaction != null) {
            objectWriter.name("transaction").value(this.transaction);
        }
        if (this.sampleRate != null) {
            objectWriter.name(JsonKeys.SAMPLE_RATE).value(this.sampleRate);
        }
        if (this.sampled != null) {
            objectWriter.name(JsonKeys.SAMPLED).value(this.sampled);
        }
        if (this.replayId != null) {
            objectWriter.name("replay_id").value(iLogger, this.replayId);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<TraceContext> {
        @Override // io.sentry.JsonDeserializer
        public TraceContext deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String str;
            String id;
            String strNextName;
            objectReader.beginObject();
            TraceContextUser traceContextUser = null;
            String strNextStringOrNull = null;
            SentryId sentryIdDeserialize = null;
            String strNextString = null;
            String segment = null;
            String strNextStringOrNull2 = null;
            String strNextStringOrNull3 = null;
            String strNextStringOrNull4 = null;
            String strNextStringOrNull5 = null;
            String strNextStringOrNull6 = null;
            SentryId sentryIdDeserialize2 = null;
            ConcurrentHashMap concurrentHashMap = null;
            while (true) {
                TraceContextUser traceContextUser2 = traceContextUser;
                String str2 = strNextStringOrNull;
                SentryId sentryId = sentryIdDeserialize;
                if (objectReader.peek() != JsonToken.NAME) {
                    if (sentryId == null) {
                        throw missingRequiredFieldException("trace_id", iLogger);
                    }
                    if (strNextString == null) {
                        throw missingRequiredFieldException(JsonKeys.PUBLIC_KEY, iLogger);
                    }
                    if (traceContextUser2 != null) {
                        id = str2 == null ? traceContextUser2.getId() : str2;
                        if (segment == null) {
                            segment = traceContextUser2.getSegment();
                        }
                        str = segment;
                    } else {
                        str = segment;
                        id = str2;
                    }
                    TraceContext traceContext = new TraceContext(sentryId, strNextString, strNextStringOrNull2, strNextStringOrNull3, id, str, strNextStringOrNull4, strNextStringOrNull5, strNextStringOrNull6, sentryIdDeserialize2);
                    traceContext.setUnknown(concurrentHashMap);
                    objectReader.endObject();
                    return traceContext;
                }
                strNextName = objectReader.nextName();
                strNextName.hashCode();
                switch (strNextName) {
                    case "user_segment":
                        segment = objectReader.nextStringOrNull();
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "replay_id":
                        sentryIdDeserialize2 = new SentryId.Deserializer().deserialize(objectReader, iLogger);
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "user_id":
                        strNextStringOrNull = objectReader.nextStringOrNull();
                        traceContextUser = traceContextUser2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "environment":
                        strNextStringOrNull3 = objectReader.nextStringOrNull();
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "user":
                        traceContextUser = (TraceContextUser) objectReader.nextOrNull(iLogger, new TraceContextUser.Deserializer());
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "sample_rate":
                        strNextStringOrNull5 = objectReader.nextStringOrNull();
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "release":
                        strNextStringOrNull2 = objectReader.nextStringOrNull();
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "trace_id":
                        sentryIdDeserialize = new SentryId.Deserializer().deserialize(objectReader, iLogger);
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        break;
                    case "sampled":
                        strNextStringOrNull6 = objectReader.nextStringOrNull();
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "public_key":
                        strNextString = objectReader.nextString();
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    case "transaction":
                        strNextStringOrNull4 = objectReader.nextStringOrNull();
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, strNextName);
                        traceContextUser = traceContextUser2;
                        strNextStringOrNull = str2;
                        sentryIdDeserialize = sentryId;
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
