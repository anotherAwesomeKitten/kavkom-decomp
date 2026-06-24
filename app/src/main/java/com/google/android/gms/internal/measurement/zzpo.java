package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpo implements zzpl {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zzhhVarZza.zza("measurement.client.sessions.background_sessions_enabled", true);
        zza = zzhhVarZza.zza("measurement.client.sessions.enable_fix_background_engagement", false);
        zzhhVarZza.zza("measurement.client.sessions.immediate_start_enabled_foreground", true);
        zzb = zzhhVarZza.zza("measurement.client.sessions.enable_pause_engagement_in_background", true);
        zzhhVarZza.zza("measurement.client.sessions.remove_expired_session_properties_enabled", true);
        zzhhVarZza.zza("measurement.client.sessions.session_id_enabled", true);
        zzhhVarZza.zza("measurement.id.client.sessions.enable_fix_background_engagement", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzpl
    public final boolean zza() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpl
    public final boolean zzb() {
        return zzb.zza().booleanValue();
    }
}
