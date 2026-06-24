package io.sentry;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class DsnUtil {
    public static boolean urlContainsDsnHost(SentryOptions sentryOptions, String str) {
        String host;
        if (sentryOptions == null || str == null || sentryOptions.getDsn() == null || (host = sentryOptions.retrieveParsedDsn().getSentryUri().getHost()) == null) {
            return false;
        }
        return str.toLowerCase(Locale.ROOT).contains(host.toLowerCase(Locale.ROOT));
    }
}
