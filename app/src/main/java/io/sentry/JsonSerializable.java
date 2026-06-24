package io.sentry;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface JsonSerializable {
    void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException;
}
