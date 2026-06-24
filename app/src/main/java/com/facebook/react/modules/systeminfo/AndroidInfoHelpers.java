package com.facebook.react.modules.systeminfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.facebook.react.R;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AndroidInfoHelpers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\b\u0010\u0015\u001a\u00020\u0005H\u0007J \u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0013H\u0007J\b\u0010\u0019\u001a\u00020\u0005H\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001a\u0010\u001b\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0015\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001dJ\b\u0010\u001e\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \n*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/facebook/react/modules/systeminfo/AndroidInfoHelpers;", "", "<init>", "()V", "EMULATOR_LOCALHOST", "", "GENYMOTION_LOCALHOST", "DEVICE_LOCALHOST", "METRO_HOST_PROP_NAME", "TAG", "kotlin.jvm.PlatformType", "metroHostPropValue", "isRunningOnGenymotion", "", "isRunningOnStockEmulator", "getServerHost", "port", "", "context", "Landroid/content/Context;", "getAdbReverseTcpCommand", "getFriendlyDeviceName", "getInspectorHostMetadata", "", "applicationContext", "getReactNativeVersionString", "getDevServerPort", "getServerIpAddress", "getDevServerNetworkIpAndPort", "getDevServerNetworkIpAndPort$ReactAndroid_release", "getMetroHostPropValue", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidInfoHelpers {
    public static final String DEVICE_LOCALHOST = "localhost";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2";
    public static final String METRO_HOST_PROP_NAME = "metro.host";
    private static String metroHostPropValue;
    public static final AndroidInfoHelpers INSTANCE = new AndroidInfoHelpers();
    private static final String TAG = "AndroidInfoHelpers";

    private AndroidInfoHelpers() {
    }

    private final boolean isRunningOnGenymotion() {
        String FINGERPRINT = Build.FINGERPRINT;
        Intrinsics.checkNotNullExpressionValue(FINGERPRINT, "FINGERPRINT");
        return StringsKt.contains$default((CharSequence) FINGERPRINT, (CharSequence) "vbox", false, 2, (Object) null);
    }

    private final boolean isRunningOnStockEmulator() {
        String FINGERPRINT = Build.FINGERPRINT;
        Intrinsics.checkNotNullExpressionValue(FINGERPRINT, "FINGERPRINT");
        if (StringsKt.contains$default((CharSequence) FINGERPRINT, (CharSequence) "generic", false, 2, (Object) null)) {
            return true;
        }
        String FINGERPRINT2 = Build.FINGERPRINT;
        Intrinsics.checkNotNullExpressionValue(FINGERPRINT2, "FINGERPRINT");
        return StringsKt.startsWith$default(FINGERPRINT2, "google/sdk_gphone", false, 2, (Object) null);
    }

    @JvmStatic
    public static final String getServerHost(int port) {
        return INSTANCE.getServerIpAddress(null, port);
    }

    @JvmStatic
    public static final String getServerHost(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AndroidInfoHelpers androidInfoHelpers = INSTANCE;
        return androidInfoHelpers.getServerIpAddress(context, androidInfoHelpers.getDevServerPort(context));
    }

    @JvmStatic
    public static final String getServerHost(Context context, int port) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.getServerIpAddress(context, port);
    }

    @JvmStatic
    public static final String getAdbReverseTcpCommand(int port) {
        return "adb reverse tcp:" + port + " tcp:" + port;
    }

    @JvmStatic
    public static final String getAdbReverseTcpCommand(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getAdbReverseTcpCommand(INSTANCE.getDevServerPort(context));
    }

    @JvmStatic
    public static final String getFriendlyDeviceName() {
        if (INSTANCE.isRunningOnGenymotion()) {
            String str = Build.MODEL;
            Intrinsics.checkNotNull(str);
            return str;
        }
        return Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }

    @JvmStatic
    public static final Map<String, String> getInspectorHostMetadata(Context applicationContext) {
        String packageName;
        String string;
        if (applicationContext != null) {
            ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
            int i = applicationInfo.labelRes;
            packageName = applicationContext.getPackageName();
            if (i == 0) {
                string = applicationInfo.nonLocalizedLabel.toString();
            } else {
                string = applicationContext.getString(i);
                Intrinsics.checkNotNull(string);
            }
        } else {
            packageName = null;
            string = null;
        }
        return MapsKt.mapOf(TuplesKt.to("appDisplayName", string), TuplesKt.to("appIdentifier", packageName), TuplesKt.to("platform", "android"), TuplesKt.to("deviceName", Build.MODEL), TuplesKt.to("reactNativeVersion", INSTANCE.getReactNativeVersionString()));
    }

    private final String getReactNativeVersionString() {
        Map<String, Object> map = ReactNativeVersion.VERSION;
        Object obj = map.get("major");
        Object obj2 = map.get("minor");
        Object obj3 = map.get("patch");
        Object obj4 = map.get("prerelease");
        String str = obj4 != null ? "-" + obj4 : null;
        if (str == null) {
            str = "";
        }
        return obj + "." + obj2 + "." + obj3 + str;
    }

    private final int getDevServerPort(Context context) {
        return context.getResources().getInteger(R.integer.react_native_dev_server_port);
    }

    private final String getServerIpAddress(Context context, int port) {
        String metroHostPropValue2;
        if (getMetroHostPropValue().length() > 0) {
            metroHostPropValue2 = getMetroHostPropValue();
        } else if (isRunningOnGenymotion()) {
            metroHostPropValue2 = GENYMOTION_LOCALHOST;
        } else {
            metroHostPropValue2 = isRunningOnStockEmulator() ? EMULATOR_LOCALHOST : DEVICE_LOCALHOST;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%s:%d", Arrays.copyOf(new Object[]{metroHostPropValue2, Integer.valueOf(port)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String getDevServerNetworkIpAndPort$ReactAndroid_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getResources().getString(R.string.react_native_dev_server_ip) + ":" + getDevServerPort(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0085 A[Catch: all -> 0x008e, TRY_ENTER, TryCatch #5 {, blocks: (B:68:0x0001, B:70:0x0005, B:83:0x0047, B:85:0x004c, B:102:0x007a, B:99:0x0074, B:109:0x0085, B:111:0x008a, B:112:0x008d), top: B:119:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x008a A[Catch: all -> 0x008e, TryCatch #5 {, blocks: (B:68:0x0001, B:70:0x0005, B:83:0x0047, B:85:0x004c, B:102:0x007a, B:99:0x0074, B:109:0x0085, B:111:0x008a, B:112:0x008d), top: B:119:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized java.lang.String getMetroHostPropValue() {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L8e
            if (r0 == 0) goto La
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L8e
            monitor-exit(r7)
            return r0
        La:
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L61
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L61
            java.lang.String r3 = "/system/bin/getprop"
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L61
            java.lang.String r3 = "metro.host"
            r4 = 1
            r2[r4] = r3     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L61
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L61
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            java.io.InputStream r4 = r1.getInputStream()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            java.lang.String r5 = "UTF-8"
            java.nio.charset.Charset r5 = java.nio.charset.Charset.forName(r5)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            java.io.Reader r3 = (java.io.Reader) r3     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L57
            java.lang.String r0 = ""
        L38:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L82
            if (r3 == 0) goto L45
            if (r3 != 0) goto L43
            java.lang.String r0 = ""
            goto L38
        L43:
            r0 = r3
            goto L38
        L45:
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r0     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L82
            r2.close()     // Catch: java.lang.Throwable -> L8e
            if (r1 == 0) goto L7a
        L4c:
            r1.destroy()     // Catch: java.lang.Throwable -> L8e
            goto L7a
        L50:
            r0 = move-exception
            goto L65
        L52:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L83
        L57:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L65
        L5c:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L83
        L61:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L65:
            java.lang.String r3 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.TAG     // Catch: java.lang.Throwable -> L82
            java.lang.String r4 = "Failed to query for metro.host prop:"
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch: java.lang.Throwable -> L82
            com.facebook.common.logging.FLog.w(r3, r4, r0)     // Catch: java.lang.Throwable -> L82
            java.lang.String r0 = ""
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r0     // Catch: java.lang.Throwable -> L82
            if (r2 == 0) goto L77
            r2.close()     // Catch: java.lang.Throwable -> L8e
        L77:
            if (r1 == 0) goto L7a
            goto L4c
        L7a:
            java.lang.String r0 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L8e
            if (r0 != 0) goto L80
            java.lang.String r0 = ""
        L80:
            monitor-exit(r7)
            return r0
        L82:
            r0 = move-exception
        L83:
            if (r2 == 0) goto L88
            r2.close()     // Catch: java.lang.Throwable -> L8e
        L88:
            if (r1 == 0) goto L8d
            r1.destroy()     // Catch: java.lang.Throwable -> L8e
        L8d:
            throw r0     // Catch: java.lang.Throwable -> L8e
        L8e:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L8e
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getMetroHostPropValue():java.lang.String");
    }
}
