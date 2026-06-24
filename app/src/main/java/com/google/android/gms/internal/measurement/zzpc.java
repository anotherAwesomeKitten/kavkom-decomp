package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpc implements zzoz {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Long> zzb;
    private static final zzgz<Double> zzc;
    private static final zzgz<Long> zzd;
    private static final zzgz<Long> zze;
    private static final zzgz<String> zzf;

    @Override // com.google.android.gms.internal.measurement.zzoz
    public final double zza() {
        return zzc.zza().doubleValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzoz
    public final long zzb() {
        return zzb.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzoz
    public final long zzc() {
        return zzd.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzoz
    public final long zzd() {
        return zze.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzoz
    public final String zze() {
        return zzf.zza();
    }

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhhVarZza.zza("measurement.test.boolean_flag", false);
        zzb = zzhhVarZza.zza("measurement.test.cached_long_flag", -1L);
        zzc = zzhhVarZza.zza("measurement.test.double_flag", -3.0d);
        zzd = zzhhVarZza.zza("measurement.test.int_flag", -2L);
        zze = zzhhVarZza.zza("measurement.test.long_flag", -1L);
        zzf = zzhhVarZza.zza("measurement.test.string_flag", "---");
    }

    @Override // com.google.android.gms.internal.measurement.zzoz
    public final boolean zzf() {
        return zza.zza().booleanValue();
    }
}
