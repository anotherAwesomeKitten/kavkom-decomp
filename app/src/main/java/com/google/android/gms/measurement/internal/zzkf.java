package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkf implements Runnable {
    private final /* synthetic */ zzin zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzin zzd;
    private final /* synthetic */ zziv zze;

    zzkf(zziv zzivVar, zzin zzinVar, long j, boolean z, zzin zzinVar2) {
        this.zza = zzinVar;
        this.zzb = j;
        this.zzc = z;
        this.zzd = zzinVar2;
        this.zze = zzivVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza(this.zza);
        zziv.zza(this.zze, this.zza, this.zzb, false, this.zzc);
        zziv.zza(this.zze, this.zza, this.zzd);
    }
}
