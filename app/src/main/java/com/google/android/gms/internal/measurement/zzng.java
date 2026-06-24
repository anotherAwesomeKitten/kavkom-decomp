package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzng implements zznd {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhhVarZza.zza("measurement.consent_regional_defaults.client2", false);
        zzb = zzhhVarZza.zza("measurement.consent_regional_defaults.service", false);
        zzhhVarZza.zza("measurement.id.consent_regional_defaults.service", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }
}
