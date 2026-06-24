package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkv implements Runnable {
    private final /* synthetic */ zzkp zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzks zzc;

    zzkv(zzks zzksVar, zzkp zzkpVar, long j) {
        this.zza = zzkpVar;
        this.zzb = j;
        this.zzc = zzksVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza(this.zza, false, this.zzb);
        this.zzc.zza = null;
        this.zzc.zzo().zza((zzkp) null);
    }
}
