package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zznb implements Runnable {
    private final /* synthetic */ zznm zza;
    private final /* synthetic */ zznc zzb;

    zznb(zznc zzncVar, zznm zznmVar) {
        this.zza = zznmVar;
        this.zzb = zzncVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznc.zza(this.zzb, this.zza);
        this.zzb.zzv();
    }
}
