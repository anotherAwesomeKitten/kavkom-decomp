package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhr implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzhn zzb;

    zzhr(zzhn zzhnVar, zzo zzoVar) {
        this.zza = zzoVar;
        this.zzb = zzhnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzr();
        zznc zzncVar = this.zzb.zza;
        zzo zzoVar = this.zza;
        zzncVar.zzl().zzt();
        zzncVar.zzs();
        Preconditions.checkNotEmpty(zzoVar.zza);
        zzncVar.zza(zzoVar);
    }
}
