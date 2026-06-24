package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkt implements Runnable {
    private final /* synthetic */ zzks zza;

    zzkt(zzks zzksVar) {
        this.zza = zzksVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzks zzksVar = this.zza;
        zzksVar.zza = zzksVar.zzh;
    }
}
