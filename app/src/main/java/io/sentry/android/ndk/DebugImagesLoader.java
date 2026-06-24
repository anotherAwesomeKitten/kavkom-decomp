package io.sentry.android.ndk;

import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.IDebugImagesLoader;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.protocol.DebugImage;
import io.sentry.util.Objects;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class DebugImagesLoader implements IDebugImagesLoader {
    private static List<DebugImage> debugImages;
    private static final Object debugImagesLock = new Object();
    private final NativeModuleListLoader moduleListLoader;
    private final SentryOptions options;

    public DebugImagesLoader(SentryAndroidOptions sentryAndroidOptions, NativeModuleListLoader nativeModuleListLoader) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryAndroidOptions, "The SentryAndroidOptions is required.");
        this.moduleListLoader = (NativeModuleListLoader) Objects.requireNonNull(nativeModuleListLoader, "The NativeModuleListLoader is required.");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042 A[Catch: all -> 0x0046, DONT_GENERATE, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:12:0x0042, B:11:0x0032, B:6:0x0007, B:8:0x000f), top: B:20:0x0003, inners: #0 }] */
    @Override // io.sentry.android.core.IDebugImagesLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<io.sentry.protocol.DebugImage> loadDebugImages() {
        /*
            r6 = this;
            java.lang.Object r0 = io.sentry.android.ndk.DebugImagesLoader.debugImagesLock
            monitor-enter(r0)
            java.util.List<io.sentry.protocol.DebugImage> r1 = io.sentry.android.ndk.DebugImagesLoader.debugImages     // Catch: java.lang.Throwable -> L46
            if (r1 != 0) goto L42
            io.sentry.android.ndk.NativeModuleListLoader r1 = r6.moduleListLoader     // Catch: java.lang.Throwable -> L31
            io.sentry.protocol.DebugImage[] r1 = r1.loadModuleList()     // Catch: java.lang.Throwable -> L31
            if (r1 == 0) goto L42
            java.util.List r1 = java.util.Arrays.asList(r1)     // Catch: java.lang.Throwable -> L31
            io.sentry.android.ndk.DebugImagesLoader.debugImages = r1     // Catch: java.lang.Throwable -> L31
            io.sentry.SentryOptions r1 = r6.options     // Catch: java.lang.Throwable -> L31
            io.sentry.ILogger r1 = r1.getLogger()     // Catch: java.lang.Throwable -> L31
            io.sentry.SentryLevel r2 = io.sentry.SentryLevel.DEBUG     // Catch: java.lang.Throwable -> L31
            java.lang.String r3 = "Debug images loaded: %d"
            java.util.List<io.sentry.protocol.DebugImage> r4 = io.sentry.android.ndk.DebugImagesLoader.debugImages     // Catch: java.lang.Throwable -> L31
            int r4 = r4.size()     // Catch: java.lang.Throwable -> L31
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L31
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch: java.lang.Throwable -> L31
            r1.log(r2, r3, r4)     // Catch: java.lang.Throwable -> L31
            goto L42
        L31:
            r1 = move-exception
            io.sentry.SentryOptions r2 = r6.options     // Catch: java.lang.Throwable -> L46
            io.sentry.ILogger r2 = r2.getLogger()     // Catch: java.lang.Throwable -> L46
            io.sentry.SentryLevel r3 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> L46
            java.lang.String r4 = "Failed to load debug images."
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L46
            r2.log(r3, r1, r4, r5)     // Catch: java.lang.Throwable -> L46
        L42:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L46
            java.util.List<io.sentry.protocol.DebugImage> r0 = io.sentry.android.ndk.DebugImagesLoader.debugImages
            return r0
        L46:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L46
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.ndk.DebugImagesLoader.loadDebugImages():java.util.List");
    }

    @Override // io.sentry.android.core.IDebugImagesLoader
    public void clearDebugImages() {
        synchronized (debugImagesLock) {
            try {
                this.moduleListLoader.clearModuleList();
                this.options.getLogger().log(SentryLevel.INFO, "Debug images cleared.", new Object[0]);
            } finally {
            }
            debugImages = null;
        }
    }

    List<DebugImage> getCachedDebugImages() {
        return debugImages;
    }
}
