package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjw implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zziv zzb;

    zzjw(zziv zzivVar, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zzivVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zziv.zza(this.zzb, this.zza);
    }
}
