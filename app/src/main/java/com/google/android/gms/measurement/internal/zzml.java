package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzml implements Runnable {
    long zza;
    long zzb;
    final /* synthetic */ zzmm zzc;

    zzml(zzmm zzmmVar, long j, long j2) {
        this.zzc = zzmmVar;
        this.zza = j;
        this.zzb = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzl().zzb(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzmo
            @Override // java.lang.Runnable
            public final void run() {
                zzml zzmlVar = this.zza;
                zzmm zzmmVar = zzmlVar.zzc;
                long j = zzmlVar.zza;
                long j2 = zzmlVar.zzb;
                zzmmVar.zza.zzt();
                zzmmVar.zza.zzj().zzc().zza("Application going to the background");
                zzmmVar.zza.zzk().zzn.zza(true);
                zzmmVar.zza.zza(true);
                if (!zzmmVar.zza.zze().zzv()) {
                    if (zzmmVar.zza.zze().zza(zzbf.zzch)) {
                        zzmmVar.zza.zza(false, false, j2);
                        zzmmVar.zza.zzb.zzb(j2);
                    } else {
                        zzmmVar.zza.zzb.zzb(j2);
                        zzmmVar.zza.zza(false, false, j2);
                    }
                }
                zzmmVar.zza.zzj().zzn().zza("Application backgrounded at: timestamp_millis", Long.valueOf(j));
            }
        });
    }
}
