package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import io.sentry.metrics.MetricsHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdq {
    private static volatile zzdq zzb;
    protected final Clock zza;
    private final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    private final List<Pair<com.google.android.gms.measurement.internal.zziu, zzb>> zzf;
    private int zzg;
    private boolean zzh;
    private String zzi;
    private volatile zzdb zzj;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    static class zzb extends zzdk {
        private final com.google.android.gms.measurement.internal.zziu zza;

        @Override // com.google.android.gms.internal.measurement.zzdh
        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        zzb(com.google.android.gms.measurement.internal.zziu zziuVar) {
            this.zza = zziuVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzdh
        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.onEvent(str, str2, bundle, j);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    static class zzc extends zzdk {
        private final com.google.android.gms.measurement.internal.zzir zza;

        @Override // com.google.android.gms.internal.measurement.zzdh
        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        zzc(com.google.android.gms.measurement.internal.zzir zzirVar) {
            this.zza = zzirVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzdh
        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.interceptEvent(str, str2, bundle, j);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    class zzd implements Application.ActivityLifecycleCallbacks {
        zzd() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzdq.this.zza(new zzey(this, bundle, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            zzdq.this.zza(new zzfd(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            zzdq.this.zza(new zzfc(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            zzdq.this.zza(new zzez(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzdc zzdcVar = new zzdc();
            zzdq.this.zza(new zzfe(this, activity, zzdcVar));
            Bundle bundleZza = zzdcVar.zza(50L);
            if (bundleZza != null) {
                bundle.putAll(bundleZza);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            zzdq.this.zza(new zzfa(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            zzdq.this.zza(new zzfb(this, activity));
        }
    }

    public final int zza(String str) {
        zzdc zzdcVar = new zzdc();
        zza(new zzen(this, str, zzdcVar));
        Integer num = (Integer) zzdc.zza(zzdcVar.zza(10000L), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
    abstract class zza implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;

        zza(zzdq zzdqVar) {
            this(true);
        }

        abstract void zza() throws RemoteException;

        protected void zzb() {
        }

        zza(boolean z) {
            this.zza = zzdq.this.zza.currentTimeMillis();
            this.zzb = zzdq.this.zza.elapsedRealtime();
            this.zzc = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (zzdq.this.zzh) {
                zzb();
                return;
            }
            try {
                zza();
            } catch (Exception e) {
                zzdq.this.zza(e, false, this.zzc);
                zzb();
            }
        }
    }

    public final long zza() {
        zzdc zzdcVar = new zzdc();
        zza(new zzei(this, zzdcVar));
        Long lZzb = zzdcVar.zzb(500L);
        if (lZzb == null) {
            long jNextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
            int i = this.zzg + 1;
            this.zzg = i;
            return jNextLong + ((long) i);
        }
        return lZzb.longValue();
    }

    public final Bundle zza(Bundle bundle, boolean z) {
        zzdc zzdcVar = new zzdc();
        zza(new zzeo(this, bundle, zzdcVar));
        if (z) {
            return zzdcVar.zza(MetricsHelper.FLUSHER_SLEEP_TIME_MS);
        }
        return null;
    }

    public final AppMeasurementSdk zzb() {
        return this.zze;
    }

    protected final zzdb zza(Context context, boolean z) {
        try {
            return zzde.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e) {
            zza((Exception) e, true, false);
            return null;
        }
    }

    public static zzdq zza(Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    public static zzdq zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzb == null) {
            synchronized (zzdq.class) {
                if (zzb == null) {
                    zzb = new zzdq(context, str, str2, str3, bundle);
                }
            }
        }
        return zzb;
    }

    public final Long zzc() {
        zzdc zzdcVar = new zzdc();
        zza(new zzep(this, zzdcVar));
        return zzdcVar.zzb(120000L);
    }

    public final Object zza(int i) {
        zzdc zzdcVar = new zzdc();
        zza(new zzes(this, zzdcVar, i));
        return zzdc.zza(zzdcVar.zza(15000L), Object.class);
    }

    public final String zzd() {
        return this.zzi;
    }

    public final String zze() {
        zzdc zzdcVar = new zzdc();
        zza(new zzeq(this, zzdcVar));
        return zzdcVar.zzc(120000L);
    }

    public final String zzf() {
        zzdc zzdcVar = new zzdc();
        zza(new zzef(this, zzdcVar));
        return zzdcVar.zzc(50L);
    }

    public final String zzg() {
        zzdc zzdcVar = new zzdc();
        zza(new zzek(this, zzdcVar));
        return zzdcVar.zzc(500L);
    }

    public final String zzh() {
        zzdc zzdcVar = new zzdc();
        zza(new zzeh(this, zzdcVar));
        return zzdcVar.zzc(500L);
    }

    public final String zzi() {
        zzdc zzdcVar = new zzdc();
        zza(new zzeg(this, zzdcVar));
        return zzdcVar.zzc(500L);
    }

    public final List<Bundle> zza(String str, String str2) {
        zzdc zzdcVar = new zzdc();
        zza(new zzdt(this, str, str2, zzdcVar));
        List<Bundle> list = (List) zzdc.zza(zzdcVar.zza(MetricsHelper.FLUSHER_SLEEP_TIME_MS), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzdc zzdcVar = new zzdc();
        zza(new zzej(this, str, str2, z, zzdcVar));
        Bundle bundleZza = zzdcVar.zza(MetricsHelper.FLUSHER_SLEEP_TIME_MS);
        if (bundleZza == null || bundleZza.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap(bundleZza.size());
        for (String str3 : bundleZza.keySet()) {
            Object obj = bundleZza.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                map.put(str3, obj);
            }
        }
        return map;
    }

    private zzdq(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzc(str2, str3)) {
            this.zzc = "FA";
        } else {
            this.zzc = str;
        }
        this.zza = DefaultClock.getInstance();
        this.zzd = zzcu.zza().zza(new zzec(this), 1);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        if (zzb(context) && !zzk()) {
            this.zzi = null;
            this.zzh = true;
            Log.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if (!zzc(str2, str3)) {
            this.zzi = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 == null)) {
                    Log.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://firebase.google.com/docs/analytics");
            }
        } else {
            this.zzi = str2;
        }
        zza(new zzdp(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzd());
        }
    }

    public final void zza(Exception exc, boolean z, boolean z2) {
        zzdq zzdqVar;
        Exception exc2;
        this.zzh |= z;
        if (z) {
            Log.w(this.zzc, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zzdqVar = this;
            exc2 = exc;
            zzdqVar.zza(5, "Error with data collection. Data lost.", exc2, (Object) null, (Object) null);
        } else {
            zzdqVar = this;
            exc2 = exc;
        }
        Log.w(zzdqVar.zzc, "Error with data collection. Data lost.", exc2);
    }

    public final void zzb(String str) {
        zza(new zzee(this, str));
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(new zzdu(this, str, str2, bundle));
    }

    public final void zzc(String str) {
        zza(new zzed(this, str));
    }

    public final void zza(String str, Bundle bundle) {
        zza(null, str, bundle, false, true, null);
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, Long.valueOf(j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zza(new zzex(this, l, str, str2, bundle, z, z2));
    }

    public final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zza(new zzem(this, false, 5, str, obj, null, null));
    }

    public final void zza(com.google.android.gms.measurement.internal.zziu zziuVar) {
        Preconditions.checkNotNull(zziuVar);
        synchronized (this.zzf) {
            for (int i = 0; i < this.zzf.size(); i++) {
                if (zziuVar.equals(this.zzf.get(i).first)) {
                    Log.w(this.zzc, "OnEventListener already registered.");
                    return;
                }
            }
            zzb zzbVar = new zzb(zziuVar);
            this.zzf.add(new Pair<>(zziuVar, zzbVar));
            if (this.zzj != null) {
                try {
                    this.zzj.registerOnMeasurementEventListener(zzbVar);
                    return;
                } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                    Log.w(this.zzc, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
                }
            }
            zza(new zzew(this, zzbVar));
        }
    }

    public final void zzj() {
        zza(new zzdz(this));
    }

    public final void zza(zza zzaVar) {
        this.zzd.execute(zzaVar);
    }

    public final void zza(Bundle bundle) {
        zza(new zzdr(this, bundle));
    }

    public final void zzb(Bundle bundle) {
        zza(new zzdx(this, bundle));
    }

    public final void zzc(Bundle bundle) {
        zza(new zzea(this, bundle));
    }

    public final void zza(Activity activity, String str, String str2) {
        zza(new zzdv(this, activity, str, str2));
    }

    public final void zza(boolean z) {
        zza(new zzer(this, z));
    }

    public final void zzd(Bundle bundle) {
        zza(new zzeu(this, bundle));
    }

    public final void zza(com.google.android.gms.measurement.internal.zzir zzirVar) {
        zzc zzcVar = new zzc(zzirVar);
        if (this.zzj != null) {
            try {
                this.zzj.setEventInterceptor(zzcVar);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                Log.w(this.zzc, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        zza(new zzel(this, zzcVar));
    }

    public final void zza(Boolean bool) {
        zza(new zzdy(this, bool));
    }

    public final void zza(long j) {
        zza(new zzeb(this, j));
    }

    public final void zza(Intent intent) {
        zza(new zzet(this, intent));
    }

    public final void zzd(String str) {
        zza(new zzdw(this, str));
    }

    public final void zzb(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(new zzds(this, str, str2, obj, z));
    }

    public final void zzb(com.google.android.gms.measurement.internal.zziu zziuVar) {
        Pair<com.google.android.gms.measurement.internal.zziu, zzb> pair;
        Preconditions.checkNotNull(zziuVar);
        synchronized (this.zzf) {
            int i = 0;
            while (true) {
                if (i >= this.zzf.size()) {
                    pair = null;
                    break;
                } else {
                    if (zziuVar.equals(this.zzf.get(i).first)) {
                        pair = this.zzf.get(i);
                        break;
                    }
                    i++;
                }
            }
            if (pair == null) {
                Log.w(this.zzc, "OnEventListener had not been registered.");
                return;
            }
            this.zzf.remove(pair);
            zzb zzbVar = (zzb) pair.second;
            if (this.zzj != null) {
                try {
                    this.zzj.unregisterOnMeasurementEventListener(zzbVar);
                    return;
                } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                    Log.w(this.zzc, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
                }
            }
            zza(new zzev(this, zzbVar));
        }
    }

    public final boolean zzc(String str, String str2) {
        return (str2 == null || str == null || zzk()) ? false : true;
    }

    private final boolean zzk() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics", false, getClass().getClassLoader());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static boolean zzb(Context context) {
        return new com.google.android.gms.measurement.internal.zzhd(context, com.google.android.gms.measurement.internal.zzhd.zza(context)).zza("google_app_id") != null;
    }
}
