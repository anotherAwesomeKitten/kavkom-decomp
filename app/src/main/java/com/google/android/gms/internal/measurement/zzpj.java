package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpj implements zzpk {
    private static final zzgz<Boolean> zza;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zzhhVarZza.zza("measurement.collection.enable_session_stitching_token.client.dev", true);
        zzhhVarZza.zza("measurement.collection.enable_session_stitching_token.first_open_fix", true);
        zza = zzhhVarZza.zza("measurement.session_stitching_token_enabled", false);
        zzhhVarZza.zza("measurement.link_sst_to_sid", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzpk
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpk
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }
}
