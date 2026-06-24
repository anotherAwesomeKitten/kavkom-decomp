package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzmx extends zzmy {
    private boolean zza;

    zzmx(zznc zzncVar) {
        super(zzncVar);
        this.zzf.zzu();
    }

    protected abstract boolean zzc();

    protected final void zzal() {
        if (!zzan()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzam() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzc();
        this.zzf.zzt();
        this.zza = true;
    }

    final boolean zzan() {
        return this.zza;
    }
}
