package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlo implements Runnable {
    private final /* synthetic */ zzbd zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzdg zzc;
    private final /* synthetic */ zzkx zzd;

    zzlo(zzkx zzkxVar, zzbd zzbdVar, String str, com.google.android.gms.internal.measurement.zzdg zzdgVar) {
        this.zza = zzbdVar;
        this.zzb = str;
        this.zzc = zzdgVar;
        this.zzd = zzkxVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            zzfl zzflVar = this.zzd.zzb;
            if (zzflVar == null) {
                this.zzd.zzj().zzg().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            byte[] bArrZza = zzflVar.zza(this.zza, this.zzb);
            this.zzd.zzaq();
            this.zzd.zzq().zza(this.zzc, bArrZza);
        } catch (RemoteException e) {
            this.zzd.zzj().zzg().zza("Failed to send event to the service to bundle", e);
        } finally {
            this.zzd.zzq().zza(this.zzc, (byte[]) null);
        }
    }
}
