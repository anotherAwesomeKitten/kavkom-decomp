package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzhk {
    private final boolean zza;

    public zzhk(zzhn zzhnVar) {
        Preconditions.checkNotNull(zzhnVar, "BuildInfo must be non-null");
        this.zza = !zzhnVar.zza();
    }

    public final boolean zza(String str) {
        Preconditions.checkNotNull(str, "flagName must not be null");
        if (this.zza) {
            return zzhm.zza.get().containsValue(str);
        }
        return true;
    }
}
