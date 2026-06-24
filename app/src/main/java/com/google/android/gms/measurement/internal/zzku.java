package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzku implements Runnable {
    private final /* synthetic */ zzkp zza;
    private final /* synthetic */ zzkp zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzks zze;

    zzku(zzks zzksVar, zzkp zzkpVar, zzkp zzkpVar2, long j, boolean z) {
        this.zza = zzkpVar;
        this.zzb = zzkpVar2;
        this.zzc = j;
        this.zzd = z;
        this.zze = zzksVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
