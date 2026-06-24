package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
class zzij implements zzil {
    protected final zzhj zzu;

    @Override // com.google.android.gms.measurement.internal.zzil
    @Pure
    public Context zza() {
        return this.zzu.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    @Pure
    public Clock zzb() {
        return this.zzu.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    @Pure
    public zzab zzd() {
        return this.zzu.zzd();
    }

    @Pure
    public zzag zze() {
        return this.zzu.zzf();
    }

    @Pure
    public zzax zzf() {
        return this.zzu.zzg();
    }

    @Pure
    public zzfr zzi() {
        return this.zzu.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    @Pure
    public zzfw zzj() {
        return this.zzu.zzj();
    }

    @Pure
    public zzgh zzk() {
        return this.zzu.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzil
    @Pure
    public zzhc zzl() {
        return this.zzu.zzl();
    }

    @Pure
    public zznp zzq() {
        return this.zzu.zzt();
    }

    zzij(zzhj zzhjVar) {
        Preconditions.checkNotNull(zzhjVar);
        this.zzu = zzhjVar;
    }

    public void zzr() {
        this.zzu.zzl().zzr();
    }

    public void zzs() {
        this.zzu.zzy();
    }

    public void zzt() {
        this.zzu.zzl().zzt();
    }
}
