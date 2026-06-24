package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkw implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzks zzb;

    zzkw(zzks zzksVar, long j) {
        this.zza = j;
        this.zzb = zzksVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzc().zza(this.zza);
        this.zzb.zza = null;
    }
}
