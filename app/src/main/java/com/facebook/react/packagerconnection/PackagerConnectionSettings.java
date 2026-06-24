package com.facebook.react.packagerconnection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PackagerConnectionSettings.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\f\b\u0016\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0017\u001a\u00020\u0018H\u0016J2\u0010\u0019\u001a\u00020\u00182*\u0010\u001a\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00100\u000fJ&\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00102\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u0010J\u0016\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\rX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u000e\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u0010\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\u0016R\u001d\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00108F¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;", "", "appContext", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "packageName", "", "getPackageName", "()Ljava/lang/String;", "_additionalOptionsForPackager", "", "_packagerOptionsUpdater", "Lkotlin/Function1;", "", "cachedHost", "host", "debugServerHost", "getDebugServerHost", "setDebugServerHost", "(Ljava/lang/String;)V", "resetDebugServerHost", "", "setPackagerOptionsUpdater", "queryMapper", "updatePackagerOptions", "options", "setAdditionalOptionForPackager", "key", "value", "additionalOptionsForPackager", "getAdditionalOptionsForPackager", "()Ljava/util/Map;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class PackagerConnectionSettings {
    private static final String PREFS_DEBUG_SERVER_HOST_KEY = "debug_http_host";
    private final Map<String, String> _additionalOptionsForPackager;
    private Function1<? super Map<String, String>, ? extends Map<String, String>> _packagerOptionsUpdater;
    private final Context appContext;
    private String cachedHost;
    private final String packageName;
    private final SharedPreferences preferences;
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "PackagerConnectionSettings";

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map _packagerOptionsUpdater$lambda$0(Map it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it;
    }

    public PackagerConnectionSettings(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        this.preferences = defaultSharedPreferences;
        String packageName = appContext.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        this.packageName = packageName;
        this._additionalOptionsForPackager = new LinkedHashMap();
        this._packagerOptionsUpdater = new Function1() { // from class: com.facebook.react.packagerconnection.PackagerConnectionSettings$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PackagerConnectionSettings._packagerOptionsUpdater$lambda$0((Map) obj);
            }
        };
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public String getDebugServerHost() {
        String str = this.cachedHost;
        if (str != null) {
            return str;
        }
        String string = this.preferences.getString(PREFS_DEBUG_SERVER_HOST_KEY, null);
        String str2 = string;
        if (str2 != null && str2.length() != 0) {
            return string;
        }
        String serverHost = AndroidInfoHelpers.getServerHost(this.appContext);
        if (Intrinsics.areEqual(serverHost, AndroidInfoHelpers.DEVICE_LOCALHOST)) {
            FLog.w(TAG, "You seem to be running on device. Run '" + AndroidInfoHelpers.getAdbReverseTcpCommand(this.appContext) + "' to forward the debug server's port to the device.");
        }
        this.cachedHost = serverHost;
        return serverHost;
    }

    public void setDebugServerHost(String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        if (host.length() == 0) {
            this.cachedHost = null;
        } else {
            this.cachedHost = host;
        }
    }

    public void resetDebugServerHost() {
        this.cachedHost = null;
    }

    public final void setPackagerOptionsUpdater(Function1<? super Map<String, String>, ? extends Map<String, String>> queryMapper) {
        Intrinsics.checkNotNullParameter(queryMapper, "queryMapper");
        this._packagerOptionsUpdater = queryMapper;
    }

    public final Map<String, String> updatePackagerOptions(Map<String, String> options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return this._packagerOptionsUpdater.invoke(options);
    }

    public final void setAdditionalOptionForPackager(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this._additionalOptionsForPackager.put(key, value);
    }

    public final Map<String, String> getAdditionalOptionsForPackager() {
        return this._additionalOptionsForPackager;
    }

    /* JADX INFO: compiled from: PackagerConnectionSettings.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/packagerconnection/PackagerConnectionSettings$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "PREFS_DEBUG_SERVER_HOST_KEY", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
