package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzox implements zzoy {
    private static final zzgz<Boolean> zza;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zzhhVarZza.zza("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzhhVarZza.zza("measurement.sdk.collection.last_deep_link_referrer2", true);
        zza = zzhhVarZza.zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzhhVarZza.zza("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzoy
    public final boolean zza() {
        return zza.zza().booleanValue();
    }
}
