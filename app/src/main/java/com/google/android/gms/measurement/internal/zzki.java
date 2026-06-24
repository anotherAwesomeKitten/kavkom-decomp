package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzoj;
import com.google.firebase.messaging.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzki implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zziv zza;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    static /* synthetic */ void zza(zzki zzkiVar, boolean z, Uri uri, String str, String str2) {
        Bundle bundleZza;
        zzkiVar.zza.zzt();
        try {
            zznp zznpVarZzq = zzkiVar.zza.zzq();
            boolean z2 = zzoj.zza() && zzkiVar.zza.zze().zza(zzbf.zzcl);
            if (TextUtils.isEmpty(str2)) {
                bundleZza = null;
            } else if (str2.contains("gclid") || ((z2 && str2.contains("gbraid")) || str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium") || str2.contains("utm_id") || str2.contains("dclid") || str2.contains("srsltid") || str2.contains("sfmc_id"))) {
                bundleZza = zznpVarZzq.zza(Uri.parse("https://google.com/search?" + str2), z2);
                if (bundleZza != null) {
                    bundleZza.putString("_cis", "referrer");
                }
            } else {
                zznpVarZzq.zzj().zzc().zza("Activity created with data 'referrer' without required params");
                bundleZza = null;
            }
            if (z) {
                Bundle bundleZza2 = zzkiVar.zza.zzq().zza(uri, zzoj.zza() && zzkiVar.zza.zze().zza(zzbf.zzcl));
                if (bundleZza2 != null) {
                    bundleZza2.putString("_cis", "intent");
                    if (!bundleZza2.containsKey("gclid") && bundleZza != null && bundleZza.containsKey("gclid")) {
                        bundleZza2.putString("_cer", String.format("gclid=%s", bundleZza.getString("gclid")));
                    }
                    zzkiVar.zza.zzc(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundleZza2);
                    zzkiVar.zza.zza.zza(str, bundleZza2);
                }
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            zzkiVar.zza.zzj().zzc().zza("Activity created with referrer", str2);
            if (zzkiVar.zza.zze().zza(zzbf.zzbl)) {
                if (bundleZza != null) {
                    zzkiVar.zza.zzc(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundleZza);
                    zzkiVar.zza.zza.zza(str, bundleZza);
                } else {
                    zzkiVar.zza.zzj().zzc().zza("Referrer does not contain valid parameters", str2);
                }
                zzkiVar.zza.zza("auto", "_ldl", (Object) null, true);
                return;
            }
            if (!str2.contains("gclid") || (!str2.contains("utm_campaign") && !str2.contains("utm_source") && !str2.contains("utm_medium") && !str2.contains("utm_term") && !str2.contains("utm_content"))) {
                zzkiVar.zza.zzj().zzc().zza("Activity created with data 'referrer' without required params");
            } else {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                zzkiVar.zza.zza("auto", "_ldl", (Object) str2, true);
            }
        } catch (RuntimeException e) {
            zzkiVar.zza.zzj().zzg().zza("Throwable caught in handleReferrerForOnActivityCreated", e);
        }
    }

    zzki(zziv zzivVar) {
        this.zza = zzivVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onActivityCreated(android.app.Activity r9, android.os.Bundle r10) throws java.lang.Throwable {
        /*
            r8 = this;
            com.google.android.gms.measurement.internal.zziv r0 = r8.zza     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzp()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            java.lang.String r1 = "onActivityCreated"
            r0.zza(r1)     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            android.content.Intent r0 = r9.getIntent()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r0 != 0) goto L1f
            com.google.android.gms.measurement.internal.zziv r0 = r8.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzn()
            r0.zza(r9, r10)
            return
        L1f:
            android.net.Uri r1 = r0.getData()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r1 == 0) goto L2c
            boolean r2 = r1.isHierarchical()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r2 == 0) goto L2c
            goto L44
        L2c:
            android.os.Bundle r1 = r0.getExtras()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r1 == 0) goto L43
            java.lang.String r2 = "com.android.vending.referral_url"
            java.lang.String r1 = r1.getString(r2)     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r2 != 0) goto L43
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            goto L44
        L43:
            r1 = 0
        L44:
            r5 = r1
            if (r5 == 0) goto L86
            boolean r1 = r5.isHierarchical()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r1 != 0) goto L4e
            goto L86
        L4e:
            com.google.android.gms.measurement.internal.zziv r1 = r8.zza     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            r1.zzq()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            boolean r0 = com.google.android.gms.measurement.internal.zznp.zza(r0)     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r0 == 0) goto L5c
            java.lang.String r0 = "gs"
            goto L5e
        L5c:
            java.lang.String r0 = "auto"
        L5e:
            r6 = r0
            java.lang.String r0 = "referrer"
            java.lang.String r7 = r5.getQueryParameter(r0)     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            if (r10 != 0) goto L69
            r0 = 1
            goto L6a
        L69:
            r0 = 0
        L6a:
            r4 = r0
            com.google.android.gms.measurement.internal.zziv r0 = r8.zza     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzl()     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            com.google.android.gms.measurement.internal.zzkh r2 = new com.google.android.gms.measurement.internal.zzkh     // Catch: java.lang.Throwable -> L91 java.lang.RuntimeException -> L94
            r3 = r8
            r2.<init>(r3, r4, r5, r6, r7)     // Catch: java.lang.RuntimeException -> L84 java.lang.Throwable -> Laf
            r0.zzb(r2)     // Catch: java.lang.RuntimeException -> L84 java.lang.Throwable -> Laf
            com.google.android.gms.measurement.internal.zziv r0 = r3.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzn()
            r0.zza(r9, r10)
            return
        L84:
            r0 = move-exception
            goto L96
        L86:
            r3 = r8
            com.google.android.gms.measurement.internal.zziv r0 = r3.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzn()
            r0.zza(r9, r10)
            return
        L91:
            r0 = move-exception
            r3 = r8
            goto Lb0
        L94:
            r0 = move-exception
            r3 = r8
        L96:
            com.google.android.gms.measurement.internal.zziv r1 = r3.zza     // Catch: java.lang.Throwable -> Laf
            com.google.android.gms.measurement.internal.zzfw r1 = r1.zzj()     // Catch: java.lang.Throwable -> Laf
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzg()     // Catch: java.lang.Throwable -> Laf
            java.lang.String r2 = "Throwable caught in onActivityCreated"
            r1.zza(r2, r0)     // Catch: java.lang.Throwable -> Laf
            com.google.android.gms.measurement.internal.zziv r0 = r3.zza
            com.google.android.gms.measurement.internal.zzks r0 = r0.zzn()
            r0.zza(r9, r10)
            return
        Laf:
            r0 = move-exception
        Lb0:
            com.google.android.gms.measurement.internal.zziv r1 = r3.zza
            com.google.android.gms.measurement.internal.zzks r1 = r1.zzn()
            r1.zza(r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzki.onActivityCreated(android.app.Activity, android.os.Bundle):void");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzn().zza(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.zza.zzn().zzb(activity);
        zzmh zzmhVarZzp = this.zza.zzp();
        zzmhVarZzp.zzl().zzb(new zzmj(zzmhVarZzp, zzmhVarZzp.zzb().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zzmh zzmhVarZzp = this.zza.zzp();
        zzmhVarZzp.zzl().zzb(new zzmk(zzmhVarZzp, zzmhVarZzp.zzb().elapsedRealtime()));
        this.zza.zzn().zzc(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzn().zzb(activity, bundle);
    }
}
