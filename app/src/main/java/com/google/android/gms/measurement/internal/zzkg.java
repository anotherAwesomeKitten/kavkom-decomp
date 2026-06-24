package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkg implements Runnable {
    private final /* synthetic */ zzin zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzin zze;
    private final /* synthetic */ zziv zzf;

    zzkg(zziv zzivVar, zzin zzinVar, long j, long j2, boolean z, zzin zzinVar2) {
        this.zza = zzinVar;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = z;
        this.zze = zzinVar2;
        this.zzf = zzivVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzf.zza(this.zza);
        if (!com.google.android.gms.internal.measurement.zznk.zza() || !this.zzf.zze().zza(zzbf.zzcu)) {
            this.zzf.zza(this.zzb, false);
        }
        zziv.zza(this.zzf, this.zza, this.zzc, true, this.zzd);
        zziv.zza(this.zzf, this.zza, this.zze);
    }
}
