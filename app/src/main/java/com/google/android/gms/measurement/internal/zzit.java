package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzit {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    com.google.android.gms.internal.measurement.zzdo zzg;
    boolean zzh;
    Long zzi;
    String zzj;

    public zzit(Context context, com.google.android.gms.internal.measurement.zzdo zzdoVar, Long l) {
        this.zzh = true;
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzdoVar != null) {
            this.zzg = zzdoVar;
            this.zzb = zzdoVar.zzf;
            this.zzc = zzdoVar.zze;
            this.zzd = zzdoVar.zzd;
            this.zzh = zzdoVar.zzc;
            this.zzf = zzdoVar.zzb;
            this.zzj = zzdoVar.zzh;
            if (zzdoVar.zzg != null) {
                this.zze = Boolean.valueOf(zzdoVar.zzg.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
