package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzol implements zzom {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhhVarZza.zza("measurement.gbraid_campaign.gbraid.client.dev", false);
        zzb = zzhhVarZza.zza("measurement.gbraid_campaign.gbraid.service", false);
        zzhhVarZza.zza("measurement.id.gbraid_campaign.service", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzom
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzom
    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzom
    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }
}
