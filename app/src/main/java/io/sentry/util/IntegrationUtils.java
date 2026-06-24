package io.sentry.util;

import io.sentry.SentryIntegrationPackageStorage;

/* JADX INFO: loaded from: classes3.dex */
public final class IntegrationUtils {
    public static void addIntegrationToSdkVersion(String str) {
        SentryIntegrationPackageStorage.getInstance().addIntegration(str);
    }
}
