package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhk implements Runnable {
    private final /* synthetic */ zzit zza;
    private final /* synthetic */ zzhj zzb;

    zzhk(zzhj zzhjVar, zzit zzitVar) {
        this.zza = zzitVar;
        this.zzb = zzhjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzhj.zza(this.zzb, this.zza);
        this.zzb.zza(this.zza.zzg);
    }
}
