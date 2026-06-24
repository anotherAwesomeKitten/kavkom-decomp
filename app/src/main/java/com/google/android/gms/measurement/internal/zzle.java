package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzle implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzkx zzd;

    zzle(zzkx zzkxVar, AtomicReference atomicReference, zzo zzoVar, boolean z) {
        this.zza = atomicReference;
        this.zzb = zzoVar;
        this.zzc = z;
        this.zzd = zzkxVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfl zzflVar;
        synchronized (this.zza) {
            try {
                try {
                    zzflVar = this.zzd.zzb;
                } finally {
                    this.zza.notify();
                }
            } catch (RemoteException e) {
                this.zzd.zzj().zzg().zza("Failed to get all user properties; remote exception", e);
            }
            if (zzflVar == null) {
                this.zzd.zzj().zzg().zza("Failed to get all user properties; not connected to service");
                return;
            }
            Preconditions.checkNotNull(this.zzb);
            this.zza.set(zzflVar.zza(this.zzb, this.zzc));
            this.zzd.zzaq();
            this.zza.notify();
        }
    }
}
