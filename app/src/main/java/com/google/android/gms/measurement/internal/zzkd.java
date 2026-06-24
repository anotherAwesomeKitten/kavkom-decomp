package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkd implements Runnable {
    private final /* synthetic */ zzav zza;
    private final /* synthetic */ zziv zzb;

    zzkd(zziv zzivVar, zzav zzavVar) {
        this.zza = zzavVar;
        this.zzb = zzivVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.zzb.zzk().zza(this.zza)) {
            this.zzb.zzj().zzn().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(this.zza.zza()));
            return;
        }
        this.zzb.zzj().zzp().zza("Setting DMA consent(FE)", this.zza);
        if (this.zzb.zzo().zzan()) {
            this.zzb.zzo().zzai();
        } else {
            this.zzb.zzo().zza(false);
        }
    }
}
