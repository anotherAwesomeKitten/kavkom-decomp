package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzmk implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzmh zzb;

    zzmk(zzmh zzmhVar, long j) {
        this.zza = j;
        this.zzb = zzmhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzmh.zzb(this.zzb, this.zza);
    }
}
