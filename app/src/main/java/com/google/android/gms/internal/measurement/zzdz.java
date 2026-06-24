package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdq;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdz extends zzdq.zza {
    private final /* synthetic */ zzdq zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdz(zzdq zzdqVar) {
        super(zzdqVar);
        this.zzc = zzdqVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdq.zza
    final void zza() throws RemoteException {
        ((zzdb) Preconditions.checkNotNull(this.zzc.zzj)).resetAnalyticsData(this.zza);
    }
}
