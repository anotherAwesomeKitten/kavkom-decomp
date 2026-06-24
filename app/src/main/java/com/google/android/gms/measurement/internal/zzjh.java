package com.google.android.gms.measurement.internal;

import android.util.SparseArray;
import com.google.common.util.concurrent.FutureCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjh implements FutureCallback<Object> {
    private final /* synthetic */ zzmu zza;
    private final /* synthetic */ zziv zzb;

    zzjh(zziv zzivVar, zzmu zzmuVar) {
        this.zza = zzmuVar;
        this.zzb = zzivVar;
    }

    @Override // com.google.common.util.concurrent.FutureCallback
    public final void onFailure(Throwable th) {
        this.zzb.zzt();
        this.zzb.zzh = false;
        if (!this.zzb.zze().zza(zzbf.zzcf)) {
            this.zzb.zzar();
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed with throwable", th);
            return;
        }
        this.zzb.zzal().add(this.zza);
        if (this.zzb.zzi > 64) {
            this.zzb.zzi = 1;
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed. May try later. App ID, throwable", zzfw.zza(this.zzb.zzg().zzad()), zzfw.zza(th.toString()));
            return;
        }
        this.zzb.zzj().zzu().zza("registerTriggerAsync failed. App ID, delay in seconds, throwable", zzfw.zza(this.zzb.zzg().zzad()), zzfw.zza(String.valueOf(this.zzb.zzi)), zzfw.zza(th.toString()));
        zziv zzivVar = this.zzb;
        zziv.zzb(zzivVar, zzivVar.zzi);
        this.zzb.zzi <<= 1;
    }

    @Override // com.google.common.util.concurrent.FutureCallback
    public final void onSuccess(Object obj) {
        this.zzb.zzt();
        if (this.zzb.zze().zza(zzbf.zzcf)) {
            SparseArray<Long> sparseArrayZzh = this.zzb.zzk().zzh();
            sparseArrayZzh.put(this.zza.zzc, Long.valueOf(this.zza.zzb));
            this.zzb.zzk().zza(sparseArrayZzh);
            this.zzb.zzh = false;
            this.zzb.zzi = 1;
            this.zzb.zzj().zzc().zza("Successfully registered trigger URI", this.zza.zza);
            this.zzb.zzar();
            return;
        }
        this.zzb.zzh = false;
        this.zzb.zzar();
        this.zzb.zzj().zzc().zza("registerTriggerAsync ran. uri", this.zza.zza);
    }
}
