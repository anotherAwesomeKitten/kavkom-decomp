package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznm implements zznj {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;
    private static final zzgz<Boolean> zzc;
    private static final zzgz<Boolean> zzd;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhhVarZza.zza("measurement.consent.stop_reset_on_storage_denied.client", true);
        zzb = zzhhVarZza.zza("measurement.consent.stop_reset_on_storage_denied.service", true);
        zzhhVarZza.zza("measurement.id.consent.stop_reset_on_storage_denied.service", 0L);
        zzc = zzhhVarZza.zza("measurement.consent.scrub_audience_data_analytics_consent", true);
        zzd = zzhhVarZza.zza("measurement.consent.fix_first_open_count_from_snapshot", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznj
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznj
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznj
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznj
    public final boolean zzd() {
        return zzc.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznj
    public final boolean zze() {
        return zzd.zza().booleanValue();
    }
}
