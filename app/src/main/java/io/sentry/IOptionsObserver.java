package io.sentry;

import io.sentry.protocol.SdkVersion;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface IOptionsObserver {
    void setDist(String str);

    void setEnvironment(String str);

    void setProguardUuid(String str);

    void setRelease(String str);

    void setReplayErrorSampleRate(Double d);

    void setSdkVersion(SdkVersion sdkVersion);

    void setTags(Map<String, String> map);
}
