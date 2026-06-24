package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpi implements zzpf {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;
    private static final zzgz<Boolean> zzc;
    private static final zzgz<Boolean> zzd;
    private static final zzgz<Boolean> zze;
    private static final zzgz<Boolean> zzf;
    private static final zzgz<Boolean> zzg;
    private static final zzgz<Boolean> zzh;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhhVarZza.zza("measurement.rb.attribution.ad_campaign_info", false);
        zzb = zzhhVarZza.zza("measurement.rb.attribution.client2", true);
        zzhhVarZza.zza("measurement.rb.attribution.dma_fix", true);
        zzc = zzhhVarZza.zza("measurement.rb.attribution.followup1.service", false);
        zzhhVarZza.zza("measurement.rb.attribution.index_out_of_bounds_fix", true);
        zzd = zzhhVarZza.zza("measurement.rb.attribution.registration_regardless_consent", false);
        zze = zzhhVarZza.zza("measurement.rb.attribution.service", true);
        zzf = zzhhVarZza.zza("measurement.rb.attribution.enable_trigger_redaction", true);
        zzg = zzhhVarZza.zza("measurement.rb.attribution.uuid_generation", true);
        zzhhVarZza.zza("measurement.id.rb.attribution.improved_retry", 0L);
        zzh = zzhhVarZza.zza("measurement.rb.attribution.improved_retry", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzd() {
        return zzc.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zze() {
        return zzd.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzf() {
        return zze.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzg() {
        return zzf.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzh() {
        return zzg.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzi() {
        return zzh.zza().booleanValue();
    }
}
