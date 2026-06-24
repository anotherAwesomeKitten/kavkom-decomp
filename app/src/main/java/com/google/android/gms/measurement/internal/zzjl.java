package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjl implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zziv zzb;

    zzjl(zziv zzivVar, long j) {
        this.zza = j;
        this.zzb = zzivVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzk().zzf.zza(this.zza);
        this.zzb.zzj().zzc().zza("Session timeout duration set", Long.valueOf(this.zza));
    }
}
