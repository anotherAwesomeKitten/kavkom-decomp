package io.sentry.android.core.internal.util;

import android.content.Context;
import android.content.pm.PackageManager;
import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.android.core.BuildInfoProvider;
import io.sentry.util.Objects;
import java.io.File;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes3.dex */
public final class RootChecker {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private final ILogger logger;
    private final String[] rootFiles;
    private final String[] rootPackages;
    private final Runtime runtime;

    public RootChecker(Context context, BuildInfoProvider buildInfoProvider, ILogger iLogger) {
        this(context, buildInfoProvider, iLogger, new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su", "/su/bin", "/system/xbin/daemonsu"}, new String[]{"com.devadvance.rootcloak", "com.devadvance.rootcloakplus", "com.koushikdutta.superuser", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.noshufou.android.su"}, Runtime.getRuntime());
    }

    RootChecker(Context context, BuildInfoProvider buildInfoProvider, ILogger iLogger, String[] strArr, String[] strArr2, Runtime runtime) {
        this.context = (Context) Objects.requireNonNull(context, "The application context is required.");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "The BuildInfoProvider is required.");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "The Logger is required.");
        this.rootFiles = (String[]) Objects.requireNonNull(strArr, "The root Files are required.");
        this.rootPackages = (String[]) Objects.requireNonNull(strArr2, "The root packages are required.");
        this.runtime = (Runtime) Objects.requireNonNull(runtime, "The Runtime is required.");
    }

    public boolean isDeviceRooted() {
        return checkTestKeys() || checkRootFiles() || checkSUExist() || checkRootPackages(this.logger);
    }

    private boolean checkTestKeys() {
        String buildTags = this.buildInfoProvider.getBuildTags();
        return buildTags != null && buildTags.contains("test-keys");
    }

    private boolean checkRootFiles() {
        for (String str : this.rootFiles) {
            try {
            } catch (RuntimeException e) {
                this.logger.log(SentryLevel.ERROR, e, "Error when trying to check if root file %s exists.", str);
            }
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004b A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean checkSUExist() {
        /*
            r7 = this;
            r0 = 2
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r1 = "/system/xbin/which"
            r2 = 0
            r0[r2] = r1
            java.lang.String r1 = "su"
            r3 = 1
            r0[r3] = r1
            r1 = 0
            java.lang.Runtime r4 = r7.runtime     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            java.lang.Process r1 = r4.exec(r0)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            java.io.InputStream r5 = r1.getInputStream()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            java.nio.charset.Charset r6 = io.sentry.android.core.internal.util.RootChecker.UTF_8     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            java.lang.String r4 = r0.readLine()     // Catch: java.lang.Throwable -> L35
            if (r4 == 0) goto L2b
            goto L2c
        L2b:
            r3 = r2
        L2c:
            r0.close()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
            if (r1 == 0) goto L34
            r1.destroy()
        L34:
            return r3
        L35:
            r3 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L3a
            goto L3e
        L3a:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
        L3e:
            throw r3     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L4f
        L3f:
            r0 = move-exception
            io.sentry.ILogger r3 = r7.logger     // Catch: java.lang.Throwable -> L5e
            io.sentry.SentryLevel r4 = io.sentry.SentryLevel.DEBUG     // Catch: java.lang.Throwable -> L5e
            java.lang.String r5 = "Error when trying to check if SU exists."
            r3.log(r4, r5, r0)     // Catch: java.lang.Throwable -> L5e
            if (r1 == 0) goto L5d
        L4b:
            r1.destroy()
            goto L5d
        L4f:
            io.sentry.ILogger r0 = r7.logger     // Catch: java.lang.Throwable -> L5e
            io.sentry.SentryLevel r3 = io.sentry.SentryLevel.DEBUG     // Catch: java.lang.Throwable -> L5e
            java.lang.String r4 = "SU isn't found on this Device."
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L5e
            r0.log(r3, r4, r5)     // Catch: java.lang.Throwable -> L5e
            if (r1 == 0) goto L5d
            goto L4b
        L5d:
            return r2
        L5e:
            r0 = move-exception
            if (r1 == 0) goto L64
            r1.destroy()
        L64:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.util.RootChecker.checkSUExist():boolean");
    }

    private boolean checkRootPackages(ILogger iLogger) {
        BuildInfoProvider buildInfoProvider = new BuildInfoProvider(iLogger);
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager != null) {
            for (String str : this.rootPackages) {
                try {
                    if (buildInfoProvider.getSdkInfoVersion() >= 33) {
                        packageManager.getPackageInfo(str, PackageManager.PackageInfoFlags.of(0L));
                        return true;
                    }
                    packageManager.getPackageInfo(str, 0);
                    return true;
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }
        return false;
    }
}
