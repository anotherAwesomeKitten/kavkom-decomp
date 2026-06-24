package io.sentry;

/* JADX INFO: loaded from: classes2.dex */
public final class SentryNanotimeDateProvider implements SentryDateProvider {
    @Override // io.sentry.SentryDateProvider
    public SentryDate now() {
        return new SentryNanotimeDate();
    }
}
