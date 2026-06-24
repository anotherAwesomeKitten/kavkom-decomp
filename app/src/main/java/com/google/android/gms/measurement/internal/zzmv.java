package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzmv extends zzat {
    private final /* synthetic */ zzmw zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzmv(zzmw zzmwVar, zzil zzilVar) {
        super(zzilVar);
        this.zza = zzmwVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzat
    public final void zzb() {
        this.zza.zzu();
        this.zza.zzj().zzp().zza("Starting upload from DelayedRunnable");
        this.zza.zzf.zzw();
    }
}
