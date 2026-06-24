package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlg implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzno zzc;
    private final /* synthetic */ zzkx zzd;

    zzlg(zzkx zzkxVar, zzo zzoVar, boolean z, zzno zznoVar) {
        this.zza = zzoVar;
        this.zzb = z;
        this.zzc = zznoVar;
        this.zzd = zzkxVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzfl zzflVar = this.zzd.zzb;
        if (zzflVar == null) {
            this.zzd.zzj().zzg().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zzd.zza(zzflVar, this.zzb ? null : this.zzc, this.zza);
        this.zzd.zzaq();
    }
}
